#include "binaries_and_hashtables.h"
#include <iostream>

#include <string>
#include <sstream>
#include <fstream>

#include "struct.h"
#include "hash_tables.h"

HashTable hashTable;
int pos = 1;

void insertDataToHashTable(string binaryFilename) {
    ifstream binaryFile(binaryFilename, ios::binary);
    if (!binaryFile.is_open()) {
        cout << "Error opening file " << binaryFilename << endl;
        return;
    }

    FriendRecord record;

    while (binaryFile.read(reinterpret_cast<char*>(&record), sizeof(FriendRecord))) {
        string result = (record.dateOfBirth);
        hashTable.insert(result, pos);

        pos++;
    }

    cout << endl;
    binaryFile.close();
}

int findPosByKey(string key) {
    return hashTable.find(key);
}

string findLineByPos(string binaryFilename, int pos) {
    ifstream binaryFile(binaryFilename, ios::binary);

    binaryFile.seekg(pos * sizeof(FriendRecord), ios::beg);
    FriendRecord record;
    binaryFile.read(reinterpret_cast<char*>(&record), sizeof(FriendRecord));
    binaryFile.close();
    return string() + record.dateOfBirth + " " + record.name;
}

void addNewLine(string newLine, string binaryFilename) {
    ofstream binaryFile(binaryFilename, ios::binary | ios::app);
    if (!binaryFile.is_open()) {
        cout << "Error opening file " << binaryFilename << endl;
        return;
    }

    FriendRecord record;
    istringstream iss(newLine);
    iss >> record.dateOfBirth >> record.name;

    binaryFile.write(reinterpret_cast<char*>(&record), sizeof(FriendRecord));
    string result = record.dateOfBirth;
    hashTable.insert(result, pos);
    pos++;
    binaryFile.close();
}

void removeKey(string key, string binaryFilename) {
    int pos = hashTable.find(key);
    removeRecordByDate(binaryFilename, key);
    hashTable.remove(key);
}

void printHashTable() {
    for (int i = 0; i < hashTable.table.size(); i++) {
        if (hashTable.table[i].isOccupied) {
            cout << "Index " << i << ": Key = " << hashTable.table[i].key << ", Pos = " << hashTable.table[i].pos << endl;
        }
    }
}