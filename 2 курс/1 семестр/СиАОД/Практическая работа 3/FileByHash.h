#ifndef _3_FILEBYHASH_H
#define _3_FILEBYHASH_H

#include "BinaryFile.h"
#include "HashTable.h"

// Функция для переноса данных из файла в хеш-таблицу
void fromFileToHashTab(HashTable& hashtab, int n, const char* bin_name)
{
    cout << "Содержание записи: \n";

    // Вычисление хеша по дате рождения и добавление записи в хеш-таблицу
    int key = hashtab.hashFunc(find_by_key(bin_name, n).dateOfBirth);
    hashtab.insertInHashTable(key, n);
}

// Функция для удаления данных из хеш-таблицы и файла по ключу
void deleteFromTabnFile(HashTable hashtab, int key, const char* bin_name)
{
    // Поиск номера записи по ключу в хеш-таблице
    int n = hashtab.findKey(key).key;

    // Удаление ключа из хеш-таблицы и соответствующей записи из файла
    hashtab.deleteKey(key);
    delete_by_key(bin_name, n);
    hashtab.printHashTable();
}

// Функция для поиска записи в файле по ключу из хеш-таблицы
void findRecordInFile(HashTable hashtab, int key, const char* bin_name)
{
    ifstream file(bin_name, ios::binary);

    // Поиск номера записи в файле по ключу из хеш-таблицы
    int n = hashtab.findKey(key).numberRecord;
    if (n < 1)
        return; // Если запись не найдена, выход из функции

    // Поиск и вывод записи из файла по номеру записи
    find_by_key(bin_name, n);
}

#endif //_3_FILEBYHASH_H