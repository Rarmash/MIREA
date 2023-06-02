#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <regex>

using namespace std;

int FindMinimumLength(string pattern, string text, int& comparisons) {
    int m = pattern.length(), n = text.length();
    vector<int> pi(m);
    int j = 0;
    for (int i = 1; i < m; i++) {
        while (j > 0 && pattern[j] != pattern[i]) {
            j = pi[j - 1];
            comparisons++;
        }
        if (pattern[j] == pattern[i]) {
            j++;
        }
        pi[i] = j;
    }
    int k = 0;
    j = 0;
    while (k < n) {
        while (j > 0 && pattern[j] != text[k]) {
            j = pi[j - 1];
            comparisons++;
        }
        if (pattern[j] == text[k]) {
            j++;
            comparisons++;
        }
        k++;
    }
    return (n - (m - j))/5;
}

bool IsPasswordValid(const string& password)
{
    regex passwordRegex("^[A-Za-z0-9]+$");
    return regex_match(password, passwordRegex);
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

            int minLen = FindMinimumLength(subStr, text, comp);

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
