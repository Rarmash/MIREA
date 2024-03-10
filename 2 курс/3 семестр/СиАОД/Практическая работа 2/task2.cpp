#include <iostream>
#include <fstream>
#include <cstring>
#include <vector>

using namespace std;

struct FriendRecord {
    char dateOfBirth[11]; // Дата рождения (формат: дд.мм.гггг)
    char name[50];        // Имя друга
};

void ConvertToBinary(const char* textFileName, const char* binaryFileName) {
    ifstream inputFile(textFileName);
    if (!inputFile.is_open()) {
        cerr << "Ошибка открытия текстового файла" << endl;
        return;
    }

    ofstream outputFile(binaryFileName, ios::binary);
    if (!outputFile.is_open()) {
        cerr << "Ошибка создания двоичного файла" << endl;
        return;
    }

    FriendRecord record;
    while (inputFile >> record.dateOfBirth >> record.name) {
        outputFile.write(reinterpret_cast<const char*>(&record), sizeof(FriendRecord));
    }

    inputFile.close();
    outputFile.close();

    cout << "Преобразование завершено" << endl;
}

void SaveToText(const char* binaryFileName, const char* textFileName) {
    ifstream inputFile(binaryFileName, ios::binary);
    if (!inputFile.is_open()) {
        cerr << "Ошибка открытия двоичного файла" << endl;
        return;
    }

    ofstream outputFile(textFileName);
    if (!outputFile.is_open()) {
        cerr << "Ошибка создания текстового файла" << endl;
        return;
    }

    FriendRecord record;
    while (inputFile.read(reinterpret_cast<char*>(&record), sizeof(FriendRecord))) {
        outputFile << record.dateOfBirth << " " << record.name << endl;
    }

    inputFile.close();
    outputFile.close();

    cout << "Сохранение в текстовый файл завершено" << endl;
}

void PrintAllRecords(const char* binaryFileName) {
    ifstream inputFile(binaryFileName, ios::binary);
    if (!inputFile.is_open()) {
        cerr << "Ошибка открытия двоичного файла" << endl;
        return;
    }

    FriendRecord record;
    while (inputFile.read(reinterpret_cast<char*>(&record), sizeof(FriendRecord))) {
        cout << "Дата рождения: " << record.dateOfBirth << ", Имя: " << record.name << endl;
    }

    inputFile.close();
}

void AccessRecordByIndex(const char* binaryFileName, int index) {
    ifstream inputFile(binaryFileName, ios::binary);
    if (!inputFile.is_open()) {
        cerr << "Ошибка открытия двоичного файла" << endl;
        return;
    }

    FriendRecord record;
    inputFile.seekg(index * sizeof(FriendRecord));

    if (inputFile.read(reinterpret_cast<char*>(&record), sizeof(FriendRecord))) {
        cout << "Дата рождения: " << record.dateOfBirth << ", Имя: " << record.name << endl;
    }
    else {
        cout << "Запись с указанным номером не найдена" << endl;
    }

    inputFile.close();
}

void DeleteRecordByName(const char* binaryFileName, const char* nameToDelete) {
    ifstream inputFile(binaryFileName, ios::binary);
    if (!inputFile.is_open()) {
        cerr << "Ошибка открытия двоичного файла" << endl;
        return;
    }

    ofstream tempFile("temp.dat", ios::binary);
    if (!tempFile.is_open()) {
        cerr << "Ошибка создания временного файла" << endl;
        return;
    }

    FriendRecord record;
    bool recordFound = false;
    while (inputFile.read(reinterpret_cast<char*>(&record), sizeof(FriendRecord))) {
        if (strcmp(record.name, nameToDelete) == 0) {
            recordFound = true;
        }
        else {
            tempFile.write(reinterpret_cast<const char*>(&record), sizeof(FriendRecord));
        }
    }

    inputFile.close();
    tempFile.close();

    if (recordFound) {
        remove(binaryFileName);
        rename("temp.dat", binaryFileName);
        cout << "Запись с именем '" << nameToDelete << "' удалена" << endl;
    }
    else {
        remove("temp.dat");
        cout << "Запись с именем '" << nameToDelete << "' не найдена" << endl;
    }
}

void ListFriendsByMonth(const char* binaryFileName, const char* month) {
    ifstream inputFile(binaryFileName, ios::binary);
    if (!inputFile.is_open()) {
        cerr << "Ошибка открытия двоичного файла" << endl;
        return;
    }

    FriendRecord record;
    bool found = false;
    while (inputFile.read(reinterpret_cast<char*>(&record), sizeof(FriendRecord))) {
        char recordMonth[3];
        strncpy_s(recordMonth, record.dateOfBirth + 3, 2);
        recordMonth[2] = '\0';
        if (strcmp(recordMonth, month) == 0) {
            cout << "Дата рождения: " << record.dateOfBirth << ", Имя: " << record.name << endl;
            found = true;
        }
    }

    inputFile.close();

    if (!found) {
        cout << "Друзей, родившихся в указанном месяце, не найдено" << endl;
    }
}

void FindFriendByDate(const char* binaryFileName, const char* dateToFind) {
    ifstream inputFile(binaryFileName, ios::binary);
    if (!inputFile.is_open()) {
        cerr << "Ошибка открытия двоичного файла" << endl;
        return;
    }

    FriendRecord record;
    bool found = false;
    while (inputFile.read(reinterpret_cast<char*>(&record), sizeof(FriendRecord))) {
        if (strcmp(record.dateOfBirth, dateToFind) == 0) {
            cout << "Имя: " << record.name << endl;
            found = true;
            break; // Поиск завершен, так как ключ уникален
        }
    }

    inputFile.close();

    if (!found) {
        cout << "Друга с указанной датой рождения не найдено" << endl;
    }
}

int main() {
    setlocale(LC_ALL, "RUS");
    const char* binaryFileName = "friends.dat";
    char textFileName[100]; // Буфер для хранения имени текстового файла
    cout << "Введите имя текстового файла: ";
    cin >> textFileName;
    int choice;
    while (true) {
        cout << "Выберите операцию:" << endl;
        cout << "1. Преобразовать текстовый файл в двоичный" << endl;
        cout << "2. Сохранить двоичный файл в текстовый" << endl;
        cout << "3. Вывести все записи из двоичного файла" << endl;
        cout << "4. Доступ к записи по номеру" << endl;
        cout << "5. Удалить запись по имени" << endl;
        cout << "6. Сформировать список друзей, родившихся в указанном месяце" << endl;
        cout << "7. Указать имя друга, родившегося в заданную дату" << endl;
        cout << "8. Выход" << endl;
        cout << "Введите номер операции: ";
        cin >> choice;
        cout << endl;
        switch (choice) {
        case 1:
            ConvertToBinary(textFileName, binaryFileName);
            break;
        case 2:
            SaveToText(binaryFileName, textFileName);
            break;
        case 3:
            PrintAllRecords(binaryFileName);
            break;
        case 4:
            int index;
            cout << "Введите номер записи: ";
            cin >> index;
            AccessRecordByIndex(binaryFileName, index);
            break;
        case 5:
            char nameToDelete[50];
            cout << "Введите имя для удаления: ";
            cin.ignore(); // Очистка буфера ввода перед чтением строки
            cin.getline(nameToDelete, sizeof(nameToDelete));
            DeleteRecordByName(binaryFileName, nameToDelete);
            break;
        case 6:
            char month[3];
            cout << "Введите месяц (две цифры): ";
            cin >> month;
            ListFriendsByMonth(binaryFileName, month);
            break;
        case 7:
            char dateToFind[11];
            cout << "Введите дату рождения (дд.мм.гггг): ";
            cin >> dateToFind;
            FindFriendByDate(binaryFileName, dateToFind);
            break;
        case 8:
            return 0;
        default:
            cout << "Неверный выбор. Пожалуйста, выберите действие из списка." << endl;
            break;
        }
        cout << endl;
    }
    return 0;
}
