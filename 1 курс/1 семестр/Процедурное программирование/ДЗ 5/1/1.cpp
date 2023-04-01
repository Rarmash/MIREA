#include <iostream>
using namespace std;

int NOD1(int a, int b)
{
    while (a != 0 && b != 0) {
        if (a > b) a %= b;
        else b %= a;
    }
    return a + b;
}

int NOD2(int a, int b)
{
    while (a != b)
    {
        if (a > b) a -= b;
        else b -= a;
    }
    return a;
}

int NOD3(int a, int b)
{
    if (a * b == 0) return a + b;
    if (a < b) return NOD3(a, b % a);
    else return NOD3(a % b, b);
}

int NOD4(int a, int b)
{
    if (a == b) return a;
    if (a < b) return NOD4(a, b - a);
    else return NOD4(a - b, b);
}

int main()
{
    setlocale(LC_ALL, "");
    int a, b;
    cout << "Введите два числа: ";
    cin >> a >> b;
    cout << "Деление: " << NOD1(a, b) << endl;
    cout << "Вычитание: " << NOD2(a, b) << endl;
    cout << "Рекурсия (деление): " << NOD3(a, b) << endl;
    cout << "Рекурсия (вычитание): " << NOD4(a, b) << endl;
}