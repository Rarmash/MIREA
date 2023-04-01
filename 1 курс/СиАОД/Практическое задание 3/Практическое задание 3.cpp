#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
using namespace std;

void WordsOutput(char** words) {
    for (int i = 0; i < 100; i++) {
        if (words[i] == NULL) break;
        cout << words[i] << " ";
    }
    cout << endl;
}

void WordsOutput(vector<string> words) {
    for (const auto& t : words) {
        cout << t << " ";
    }
    cout << endl;
}

// Редактирование null терминальной строки
void CharEditingWords(auto& sentence)
{
    char* words[100];
    char razd[] = " ,";
    int wordscount = 0;
    words[0] = strtok(sentence, razd);
    while (words[wordscount] != NULL) {
        words[++wordscount] = strtok(NULL, razd);
    }
    for (int j = 0; j < wordscount; j++) {
        auto curword = words[j];
        int len = strlen(curword);
        char temp = curword[len - 1];
        for (int l = len - 1; l > 0; l--) {
            curword[l] = curword[l - 1];
        }
        curword[0] = temp;
        words[j] = curword;
        int r = 0, len2 = len;
        for (int l = len - 1; l > 0; l--) {
            for (int k = l - 1; k >= 0; k--) {
                if (curword[l] == curword[k]) {
                    for (int m = k; m < len - 1; m++) {
                        curword[m] = curword[m + 1];
                    }
                    len--;
                    r++;
                }
            }
        }
        curword[len2 - r] = '\0';
        words[j] = curword;
    }
    WordsOutput(words);
}

// Редактирование строки типа string
void StringEditingWords(string& sentence) {
    sentence.erase(remove_if(sentence.begin(), sentence.end(), ::ispunct), sentence.end());
    stringstream ss(sentence);
    string token;
    vector<string> words;
    while (ss >> token) {
        words.push_back(token);
    }
    for (auto& word : words) {
        if (!word.empty()) {
            char lastChar = word.back();
            word.pop_back();
            word.insert(0, 1, lastChar);
            string uniqueChars;
            for (char c : word) {
                if (uniqueChars.find(c) == string::npos) {
                    uniqueChars += c;
                }
            }
            word = uniqueChars;
        }
    }
    WordsOutput(words);
}

int vibor1() {
    int menuChoice;
    cout << "1. Реализация задания с помощью нуль терминальной строки" << endl;
    cout << "2. Реализация задания с помощью string строки" << endl;
    cout << "Выберите реализацию задания: "; cin >> menuChoice;
    cout << endl;
    return menuChoice;
}

int main()
{
    setlocale(LC_ALL, "Russian");
    int menuChoice;
    cout << "Лабораторная работа №3 по теме Строки. Гришин Андрей" << endl;
    int num = vibor1();
    cin.ignore();
    switch (num)
    {
    case 1:
    {
        while (true)
        {
            cout << "Введите предложение: ";
            char sentence[100];


            gets_s(sentence);

            CharEditingWords(sentence);
        }
        break;
    }
    case 2:
    {
        while (true)
        {
            cout << "Введите предложение: ";
            string sentence;

            getline(cin, sentence);
            
            StringEditingWords(sentence);
        }
        break;
    }
    }
}