#include <iostream>
#include "NodeList.h"
using namespace std;

int main()
{
    setlocale(LC_ALL, "Rus");

    Node* L_root = nullptr;
    Node* A[10];
    for (int i = 0; i < 10; i++)
        A[i] = nullptr;

    int input;
    int choice;

    while (true)
    {
        cout << "Лабораторная работа №5 по теме Однонаправленный список. Гришин Андрей" << endl << endl;
        cout << "Вывод списка L: ";
        if (L_root == nullptr)
        {
            cout << "Список пустой";
        }
        else
        {
            ListOutput(L_root);
        }

        cout << endl;
        cout << endl;
        cout << "1. Добавить новый узел (если список не создан, то создается корневой узел списка)" << endl;
        cout << "2. Первое задание: проверка на наличие двух одинаковых элементов в списке L" << endl;
        cout << "3. Второе задание: удаление из списка L максимального значения" << endl;
        cout << "4. Третье задание: вставка нового значения перед каждым узлом в четной позиции в списке L" << endl;
        cout << "Ваш выбор: "; cin >> choice;
        cout << endl;

        switch (choice)
        {
        case 1:
        {
            cout << "Введите значение для нового узла: ";
            cin >> input;
            AddNewNode(L_root, input);
            cout << endl;
            break;
        }
        case 2:
        {
            for (int i = 0; i < 10; i++)
                A[i] = nullptr;
            if (FirstTask(A, L_root)) {
                cout << "В списке L есть как минимум 2 одинаковых элемента";
            }
            else {
                cout << "В списке L нет одинаковых элементов";
            }
            cout << endl;
            break;
        }
        case 3:
        {
            SecondTask(L_root);
            break;
        }
        case 4:
        {
            int newValue;
            cout << "Введите значение для вставки : "; cin >> newValue;
            ThirdTask(L_root, newValue);
            break;
        }
        }
    }
}
