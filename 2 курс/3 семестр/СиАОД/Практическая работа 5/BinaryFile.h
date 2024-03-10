#ifndef _3_BINARYFILE_H
#define _3_BINARYFILE_H

#include <iostream>
#include <fstream>
#include <string>
using namespace std;

// Определение структуры для хранения данных о друзьях
struct FriendRecord
{
    char dateOfBirth[11];
    char name[50];
};

// Функция для конвертации данных из текстового файла в двоичный
void txt_to_bin(ifstream& txt, ofstream& bin)
{
    if (!bin.is_open() || !txt.is_open())
    {
        cout << "Ошибка открытия текстового файла\n";
        return;
    }

    FriendRecord friendRecord;

    // Чтение данных из текстового файла и запись их в двоичный файл
    while (txt.getline(friendRecord.dateOfBirth, sizeof(friendRecord.dateOfBirth)) &&
        txt.getline(friendRecord.name, sizeof(friendRecord.name)))
    {
        bin.write((char*)&friendRecord, sizeof(friendRecord));
        txt.ignore(); // Пропустить символ новой строки
    }
    txt.close();
    bin.close();
    cout << "Перевод прошел успешно\n";
}

// Функция для конвертации данных из двоичного файла в текстовый
void bin_to_txt(ifstream& binn, ofstream& txtt)
{
    FriendRecord friendRecord;

    if (!txtt.is_open())
    {
        cout << "Ошибка открытия текстового файла\n";
        return;
    }

    // Чтение данных из двоичного файла и запись их в текстовый файл
    while (binn.read((char*)&friendRecord, sizeof(FriendRecord)))
    {
        txtt << friendRecord.dateOfBirth << endl << friendRecord.name << endl;
    }

    // Проверка на ошибки чтения из двоичного файла
    if (!binn.eof() && binn.fail())
    {
        cout << "Ошибка чтения из двоичного файла" << endl;
    }
    cout << "Перевод прошел успешно\n";
}

// Функция для вывода данных из двоичного файла на экран
void print_from_bin(ifstream& bin)
{
    FriendRecord friendRecord;
    bin.read((char*)&friendRecord, sizeof(FriendRecord));

    // Вывод данных из двоичного файла на экран
    while (!bin.eof())
    {
        cout << friendRecord.dateOfBirth << endl << friendRecord.name << endl;
        bin.read((char*)&friendRecord, sizeof(FriendRecord));
    }
    bin.close();
}

// Функция для удаления записи по указанному ключу
void delete_by_key(const char* bin_name, int n)
{
    fstream bin(bin_name, ios::binary | ios::in);

    // Проверка на успешное открытие файла
    if (!bin.is_open())
    {
        cerr << "Ошибка открытия файла\n";
        return;
    }

    fstream temp("temp_file.dat", ios::binary | ios::out);

    // Проверка на успешное открытие временного файла
    if (!temp.is_open())
    {
        cerr << "Ошибка открытия временного файла\n";
        bin.close();
        return;
    }

    FriendRecord friendRecord;
    int key = 1;

    // Копирование данных из исходного файла во временный, исключая запись с указанным ключом
    while (bin.read((char*)&friendRecord, sizeof(FriendRecord)))
    {
        if (key != n)
        {
            temp.write((char*)&friendRecord, sizeof(FriendRecord));
        }
        key++;
    }
    bin.close();
    temp.close();

    // Удаление исходного файла и переименование временного файла
    remove(bin_name);
    rename("temp_file.dat", bin_name);
}

// Функция для поиска записи по указанному ключу
FriendRecord find_by_key(const char* bin_name, int n)
{
    ifstream bin(bin_name, ios::binary);
    FriendRecord friendRecord;

    // Проверка на успешное открытие файла
    if (!bin)
    {
        cerr << "Не удалось открыть файл для записи";
        return FriendRecord();
    }

    // Поиск и вывод на экран записи по указанному ключу
    size_t friendSize = sizeof(FriendRecord);
    bin.seekg((n - 1) * friendSize, ios::beg);
    bin.read((char*)&friendRecord, sizeof(FriendRecord));
    cout << friendRecord.dateOfBirth << endl << friendRecord.name << endl;

    // Проверка на ошибки вывода
    if (bin.good())
    {
        cout << "Ошибок вывода не обнаружено\n";
    }
    else
    {
        cout << "Обнаружена ошибка вывода\n";
    }
    bin.close();
    return friendRecord;
}

#endif //_3_BINARYFILE_H