#include <iostream>
#include <utility>
#include <fstream>
#include <string>
#include <vector>

using namespace std;

// ����������� ��������� FriendBirthday
struct FriendBirthday {
    string dateOfBirth;
    string name;
    bool openKey;
    bool isDeleted;

    FriendBirthday()
    {
        openKey = true;
        isDeleted = false;
    }

    FriendBirthday(string dateOfBirth, string name)
    {
        this->dateOfBirth = std::move(dateOfBirth);
        this->name = name;
        openKey = true;
        isDeleted = false;
    }
};

// ����������� ��������� HashTable
struct HashTable {
    int M;
    int N;
    struct KeyValuePair {
        string key;
        FriendBirthday value;
        int numberRecord;
    };
    KeyValuePair* KeyValues;

    HashTable(int M, int N)
    {
        this->M = M;
        this->N = N;
        this->KeyValues = new KeyValuePair[M];
        for (int i = 0; i < M; i++) {
            this->KeyValues[i].key = "";
        }
    }
};

int hashFunc(const string& key, int M)
{
    int sum = 0;
    for (char c : key) {
        sum += c;
    }
    return sum % M;
}
void rehashTable(HashTable& hashtable); // �������� ������� rehashTable

void insertInHashTable(const string& dateOfBirth, const string& name, int i, HashTable& hashTable)
{
    string key = dateOfBirth;
    int id = hashFunc(key, hashTable.M);
    int originalId = id;

    do {
        if (hashTable.KeyValues[id].key == "") {
            hashTable.KeyValues[id].key = key;
            hashTable.KeyValues[id].numberRecord = i;
            FriendBirthday friendInfo(dateOfBirth, name);
            hashTable.KeyValues[id].value = friendInfo;
            hashTable.N++;
            if (static_cast<double>(hashTable.N) / hashTable.M > 0.7) {
                rehashTable(hashTable);
            }
            return;
        }
        id = (id + 1) % hashTable.M;
    } while (id != originalId);
    cout << "���-������� ���������, ������� ����������." << endl;
}

void deleteInHashTable(const string& dateOfBirth, HashTable& hashtable)
{
    int id = hashFunc(dateOfBirth, hashtable.M);
    while (hashtable.KeyValues[id].key != dateOfBirth) {
        id++;
        if (id >= hashtable.M) {
            id = 0;
        }
    }
    hashtable.KeyValues[id].key = "";
    hashtable.N--;
    hashtable.KeyValues[id].value.isDeleted = true;
    hashtable.KeyValues[id].value.openKey = true;
}

int findInHashTable(HashTable& hashtable, const string& dateOfBirth)
{
    int id = hashFunc(dateOfBirth, hashtable.M);
    int originalId = id;

    do {
        if (hashtable.KeyValues[id].key == dateOfBirth) {
            return id;
        }
        id = (id + 1) % hashtable.M;
    } while (id != originalId);
    return -1;
}

void rehashTable(HashTable& hashtable)
{
    int oldM = hashtable.M;
    hashtable.M = oldM * 2;
    HashTable newTable(hashtable.M, 0);
    for (int i = 0; i < oldM; i++) {
        if (hashtable.KeyValues[i].key != "") {
            insertInHashTable(hashtable.KeyValues[i].key, hashtable.KeyValues[i].value.name, hashtable.KeyValues[i].numberRecord, newTable);
        }
    }
    delete[] hashtable.KeyValues;
    hashtable.KeyValues = newTable.KeyValues;
}

void printHashTable(const HashTable& hashtable)
{
    cout << "���������� ���-�������: " << endl;
    for (int i = 0; i < hashtable.M; i++) {
        if (hashtable.KeyValues[i].key != "") {
            cout << "������ " << i << ": ���� ��������=" << hashtable.KeyValues[i].key << ", ���=" << hashtable.KeyValues[i].value.name << endl;
        }
        else if (hashtable.KeyValues[i].value.isDeleted) {
            cout << "������ " << i << ": �������" << endl;
        }
        else {
            cout << "������ " << i << ": �����" << endl;
        }
    }
}

// ������� ������ ������ � �������� ����
void writeToBinaryFile(const HashTable& hashtable, const string& filename)
{
    ofstream outFile(filename, ios::binary | ios::out);
    if (!outFile) {
        cout << "������ �������� �����." << endl;
        return;
    }

    // ���������� ������ ������� � ���������� �������
    outFile.write(reinterpret_cast<const char*>(&hashtable.M), sizeof(hashtable.M));
    outFile.write(reinterpret_cast<const char*>(&hashtable.N), sizeof(hashtable.N));

    // ���������� ������ ������
    for (int i = 0; i < hashtable.M; ++i) {
        int keySize = hashtable.KeyValues[i].key.size();
        outFile.write(reinterpret_cast<const char*>(&keySize), sizeof(keySize));
        outFile.write(hashtable.KeyValues[i].key.c_str(), keySize);

        outFile.write(reinterpret_cast<const char*>(&hashtable.KeyValues[i].value), sizeof(FriendBirthday));
        outFile.write(reinterpret_cast<const char*>(&hashtable.KeyValues[i].numberRecord), sizeof(hashtable.KeyValues[i].numberRecord));
    }

    outFile.close();
}

// ������� ������ ������ �� ��������� �����
void readFromBinaryFile(HashTable& hashtable, const string& filename)
{
    ifstream inFile(filename, ios::binary | ios::in);
    if (!inFile) {
        cout << "������ �������� �����." << endl;
        return;
    }

    int M, N;
    inFile.read(reinterpret_cast<char*>(&M), sizeof(M));
    inFile.read(reinterpret_cast<char*>(&N), sizeof(N));

    HashTable newTable(M, N);

    for (int i = 0; i < M; ++i) {
        int keySize;
        inFile.read(reinterpret_cast<char*>(&keySize), sizeof(keySize));

        char* keyBuffer = new char[keySize + 1];
        inFile.read(keyBuffer, keySize);
        keyBuffer[keySize] = '\0';
        newTable.KeyValues[i].key = keyBuffer;
        delete[] keyBuffer;

        inFile.read(reinterpret_cast<char*>(&newTable.KeyValues[i].value), sizeof(FriendBirthday));
        inFile.read(reinterpret_cast<char*>(&newTable.KeyValues[i].numberRecord), sizeof(newTable.KeyValues[i].numberRecord));
    }

    inFile.close();
    delete[] hashtable.KeyValues;
    hashtable = newTable;
}

int main()
{
    setlocale(0, "");
    int M = 5;
    int N = 0;
    vector<string> datesOfBirth = { "01-01-1990", "02-02-1991", "03-03-1992", "04-04-1993", "05-05-1994" };
    vector<string> names = { "Alice", "Bob", "Charlie", "David", "Eva" };

    HashTable hashTable(M, N);
    int choice;

    while (true) {
        cout << "����:" << endl;
        cout << "1. �������� �����" << endl;
        cout << "2. ������� �����" << endl;
        cout << "3. ����� �����" << endl;
        cout << "4. ������� ���-�������" << endl;
        cout << "5. �������� ������ � �������� ����" << endl;
        cout << "6. �������� ������ �� ��������� �����" << endl;
        cout << "7. �����" << endl;
        cout << "������� ��� �����: ";
        cin >> choice;

        switch (choice) {
        case 1: {
            for (int i = 0; i < 5; i++) {
                insertInHashTable(datesOfBirth[i], names[i], i, hashTable);
            }
            break;
        }
        case 2: {
            string dateOfBirth;
            cout << "������� ���� �������� ����� ��� ��������: ";
            cin >> dateOfBirth;
            int index = findInHashTable(hashTable, dateOfBirth);
            if (index != -1) {
                deleteInHashTable(dateOfBirth, hashTable);
                cout << "���� ������." << endl;
            }
            else {
                cout << "���� �� ������." << endl;
            }
            break;
        }
        case 3: {
            string dateOfBirth;
            cout << "������� ���� �������� ����� ��� ������: ";
            cin >> dateOfBirth;
            int index = findInHashTable(hashTable, dateOfBirth);
            if (index != -1) {
                cout << "�������: ���� ��������=" << hashTable.KeyValues[index].key << ", ���=" << hashTable.KeyValues[index].value.name << endl;
            }
            else {
                cout << "���� �� ������." << endl;
            }
            break;
        }
        case 4:
            printHashTable(hashTable);
            break;
        case 5:
            writeToBinaryFile(hashTable, "friends.bin");
            cout << "������ ��������� � ����." << endl;
            break;
        case 6:
            readFromBinaryFile(hashTable, "friends.bin");
            cout << "������ ��������� �� �����." << endl;
            break;
        case 7:
            return 0;
        default:
            cout << "�������� �����. ����������, ���������� ��� ���." << endl;
        }
    }

    return 0;
}
