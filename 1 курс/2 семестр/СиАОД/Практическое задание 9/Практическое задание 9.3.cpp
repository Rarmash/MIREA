#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <random>
#include <ctime>
#include <cstdlib>
using namespace std;

struct record {
    int key;
    char name[20];
    int year;
};

struct searchrecord
{
    int key;
    int pos;
};

void bubbleSort(searchrecord* table, int n)
{
    for (int i = 0; i < n - 1; i++)
    {
        for (int j = 0; j < n - i - 1; j++)
        {
            if (table[j].key > table[j + 1].key)
            {
                searchrecord temp = table[j];
                table[j] = table[j + 1];
                table[j + 1] = temp;
            }
        }
    }
}

void createSearchTable(searchrecord*& table, int N, FILE* fp)
{
    table = new searchrecord[N];
    record temp;
    int i = 1;
    while (i <= N)
    {
        size_t r = fread(&temp, sizeof(record), 1, fp);
        table[i - 1].key = temp.key;
        table[i - 1].pos = i - 1;
        i++;
    }
    bubbleSort(table, N);
}

searchrecord binarySearchInTable(searchrecord* table, int N, int key)
{
    int left = 0;
    int right = N - 1;
    while (left <= right)
    {
        int mid = left + (right - left) / 2;
        if (table[mid].key == key)
            return table[mid];
        else if (table[mid].key < key)
            left = mid + 1;
        else
            right = mid - 1;
    }
    return table[0];
}

record getRecord(FILE* fp, int pos)
{
    fseek(fp, pos * sizeof(record), SEEK_SET);
    record temp;
    fread(&temp, sizeof(record), 1, fp);
    return temp;
}

int main()
{
    setlocale(LC_ALL, "RUS");

    srand(time(NULL));
    for (int N = 100; N <= 10000; N *= 10)
    {
        cout << "Для N равной " << N << endl;
        char alphabet[27] = "abcdefghijklmnopqrstuvwxyz";
        record* mas = new record[N];
        vector<int> keys(N);
        record last;
        int bytes = 0;

        ofstream file("data.bin");

        for (int i = 1; i < N; i++)
            keys[i] = i;
        random_device rd;
        mt19937 g(rd());
        shuffle(keys.begin(), keys.end(), g);
        for (int i = 0; i < N; i++)
        {
            char temp1[20];
            char temp2[20];
            mas[i].key = keys[i];
            for (int j = 0; j < 19; j++)
            {
                temp1[j] = alphabet[rand() % 26];
                temp2[j] = alphabet[rand() % 26];
            }

            temp1[19] = '\0';
            temp2[19] = '\0';

            strcpy(mas[i].name, temp1);
            mas[i].year = rand() % (2023 - 1980 + 1) + 1980;

            last.key = mas[i].key;
            strcpy(last.name, mas[i].name);
            last.year = mas[i].year;
            bytes += sizeof(mas[i]);
        }

        file.write((char*)mas, bytes);
        file.close();
        cout << "Чисел: " << N << endl;
        cout << "Байтов записано: " << bytes << endl;
        cout << "Последняя запись: key=" << last.key << ", name=" << last.name << ", year=" << last.year << endl;

        FILE* fp = fopen("data.bin", "rb");
        int key;
        cin >> key;
        while (key != 0)
        {
            searchrecord* table = NULL;
            clock_t start1 = clock();
            createSearchTable(table, N, fp);
            clock_t end1 = clock();
            double duration_ms1 = 1000.0 * (end1 - start1) / CLOCKS_PER_SEC;
            clock_t start2 = clock();
            searchrecord sr = binarySearchInTable(table, N, key);
            cout << sr.key << " is in " << sr.pos << endl;
            record r = getRecord(fp, sr.pos);
            cout << r.name << " " << r.year << endl;
            clock_t end2 = clock();
            double duration_ms2 = 1000.0 * static_cast<double>(end2 - start2) / CLOCKS_PER_SEC;
            cout << "Время создания дополнительной структуры: " << duration_ms1 << endl;
            cout << "Время выполнения поиска: " << duration_ms2 << endl;
            delete[] table;
            cin >> key;
        }

        fclose(fp);
    }
    return 0;
}
