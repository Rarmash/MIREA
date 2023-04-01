#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main()
{
    setlocale(LC_ALL, "RUS"); 
    int n;
    ifstream File("lorem.txt");
    string TextFile;
    string str;
    while (getline(File, TextFile)) {
        str += TextFile;
    }
    int len = str.length();
    while (true) {
        cout << "Выберите вариант поиска: " << endl;
        cout << "1. Найти максимальное по длине слово" << endl;
        cout << "2. Найти минимальное по длине слово" << endl;
        cout << "Номер: "; cin >> n;
        string res{ "" }, tmp{ "" };
        switch(n) {
            case 1:
                res = ""; tmp = "";
                for (int i{ 0 }; i < len; ++i) {
                    if (str[i] != ' ' || str[i] != ',') tmp += str[i];
                    if (str[i] == ' ' || str[i] == ',' || i == len - 1) {
                        if (res.length() < tmp.length()) res = tmp;
                        tmp = "";
                    }
                }
                cout << res;
                exit(0);
            case 2:
                res = str; tmp = "";
                for (int i{ 0 }; i < len; ++i) {
                    if (str[i] != ' ' || str[i] != ',') tmp += str[i];
                    if ((str[i] == ' ' || str[i] == ',' || i == len - 1) && tmp != "" && tmp != " ") {
                        if (res.length() > tmp.length()) res = tmp;
                        tmp = "";
                    }
                }
                cout << res;
                exit(0);
            default:
                cout << "Такого номера нет!" << endl; continue;
        }
    }
}