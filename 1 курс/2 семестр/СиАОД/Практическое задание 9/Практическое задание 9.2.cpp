#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <random>
#include <chrono>
#include <ctime>
#include <cstdlib>
using namespace std;

struct record {
    int key;
    char name[20];
    int year;
};

int main()
{
    setlocale(LC_ALL, "RUS");

    srand(time(NULL));
    for (int N = 100; N <= 10000; N *= 10)
    {
        char alphavite[27] = "abcdefghijklmnopqrstuvwxyz";
        record* mas = new record[N];
        vector<int> keys(N);
        record last;
        int bytes = 0;

        ofstream file("data.bin", ios::binary);


        for (int i = 0; i < N; i++)
            keys[i] = i + 1;
        random_device rd;
        mt19937 g(rd());
        shuffle(keys.begin(), keys.end(), g);
        for (int i = 0; i < N; i++)
        {
            char temp1[20];
            char temp2[20];
            mas[i].key = keys[i];
            for (int j = 0; j < 20; j++)
            {
                temp1[j] = alphavite[rand() % 26];
                temp2[j] = alphavite[rand() % 26];
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
        cout << "Последняя запись: key=" << last.key << ", name=" << last.name << ", value=" << last.year << endl;

        int key;
        cout << "Введите ключ для поиска: ";
        cin >> key;

        while (key != 0) {

            ifstream infile("data.bin", ios::binary);
            if (!infile)
            {
                cout << "Ошибка открытия файла!" << endl;
                return 1;
            }

            record temp;
            bool found = false;
            clock_t start = clock();
            while (infile.read((char*)&temp, sizeof(temp)))
            {
                if (temp.key == key)
                {
                    cout << "\nНайдена запись: key=" << temp.key << ", name=" << temp.name << ", value=" << temp.year;
                    found = true;
                    break;
                }
            }
            clock_t end = clock();
            if (!found)
                cout << "Запись с таким ключом не найдена!" << endl;

            double duration_ms = 1000.0 * (end - start) / CLOCKS_PER_SEC;

            cout << "\nВремя выполнения = " << duration_ms << endl;
            infile.close();

            cout << "\nВведите ключ для поиска: ";
            cin >> key;
        }
    }
    return 0;
}
