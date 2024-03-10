#include "BinaryFile.h"
#include "FileByHash.h"
#include "HashTable.h"
int main()
{
    setlocale(LC_ALL, "RUS");
    locale::global(locale("en_US.UTF-8"));
    const char* txt_name = "file1.txt";
    const char* bin_name = "bin.dat";
    ifstream txt(txt_name, ios::out);
    ofstream bin(bin_name, ios::binary | ios::out | ios::trunc);
    HashTable hashtab(10);
    if (!txt)
    {
        cout << "Ошибка при открытии текстового файла\n";
        return -1;
    }
    else if (!bin)
    {
        cout << "Ошибка при открытии бинарного файла\n";
        return -1;
    }
    txt_to_bin(txt, bin);
    int choose = 1;
    cout << "1. Прочитать запись из файла и вставить элемент в таблицу\n"
         << "2. Удалить запись из таблицы при заданном значении ключа и соответственно из файла\n"
         << "3. Найти запись в файле по значению ключа\n"
         << "4. Вывести хеш-таблицу на экран\n"
         << "0. Выход\n";
    string key;
    while (choose != 0)
    {
        cout << "Ваш выбор: ";
        cin >> choose;
        switch (choose)
        {
            case 1:
                cout << "Введите номер записи\n";
                int n;
                cin >> n;
                fromFileToHashTab(hashtab, n, bin_name);
                break;
            case 2:
                cout << "Введите значение ключа\n";
                cin >> key;
                deleteFromTabnFile(hashtab, hashtab.hashFunc(key), bin_name);
                break;
            case 3:
            {
                cout << "Введите значение ключа\n";
                cin >> key;
                findRecordInFile(hashtab, hashtab.hashFunc(key), bin_name);
                break;
            }
            case 4:
                hashtab.printHashTable();
                break;
            case 0:
                return 0;
        }
    }
}