#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <regex>

using namespace std;

vector<int> PrefixFunction(string str)
{
    int size = str.length();
    vector<int> prefix(size, 0);

    for (int i = 1; i < size; i++) 
    {
        int j = prefix[i - 1];

        while (j > 0 && str[i] != str[j]) 
        {
            j = prefix[j - 1];
        }

        if (str[i] == str[j]) 
        {
            j++;
        }

        prefix[i] = j;
    }

    return prefix;
}

int FindMinimumLength(const string& text, const string& subStr, int& comp) {
    int textSize = text.length();
    int subSize = subStr.length();
    vector<int> prefix = PrefixFunction(subStr);
    int j = 0;

    for (int i = 0; i < textSize; i++) {
        while (j > 0 && text[i] != subStr[j]) {
            j = prefix[j - 1];
            comp++;
        }

        if (text[i] == subStr[j]) {
            j++;
            comp++;
        }

        if (j == subSize) {
            return i - j + 1;
        }
    }

    return textSize / 5;
}

bool IsPasswordValid(const string& password)
{
    for (char ch : password)
    {
        if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')))
        {
            return false;
        }
    }

    return true;
}

void CheckPasswords(const string& fileName)
{
    ifstream inputFile(fileName);

    string password;
    vector<string> validPasswords;

    while (inputFile >> password)
    {
        if (IsPasswordValid(password))
        {
            validPasswords.push_back(password);
        }
    }

    inputFile.close();

    cout << "Корректные пароли:" << endl;
    for (const string& validPassword : validPasswords)
    {
        cout << validPassword << endl;
    }
}

int main() 
{
    setlocale(LC_ALL, "rus");
    cout << "1. Задание 1" << endl;
    cout << "2. Задание 2" << endl;
    int choice{};
    cin >> choice;

    switch (choice)
    {
    case 1:
    {
        string name = "words";
        string subStr;

        cout << "\nВведите строку: ";
        cin >> subStr;

        for (int i = 1; i <= 5; i++)
        {
            int comp = 0;
            string task = name + to_string(i);
            ifstream inputFile(task + ".txt");

            string text;
            string sent;

            while (getline(inputFile, sent))
            {
                text += sent;
            }

            inputFile.close();

            int minLen = FindMinimumLength(text, subStr, comp);

            cout << "Минимально возможная длина исходной строки S: " << minLen << endl;
            cout << "Количество сравнений: " << comp << endl;
        }

        break;
    }
    case 2:
    {
        string path = "passwords.txt";
        CheckPasswords(path);
    }
    }

    return 0;
}
