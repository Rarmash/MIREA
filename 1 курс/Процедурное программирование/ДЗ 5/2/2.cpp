#include <iostream>
#include <new>
#include <locale>
#include <fstream>

using namespace std;



int main() {
    setlocale(LC_CTYPE, "russian");

    int i, j, S;
    int N;
    bool* mas;

    cout << "Введите число: ";
    cin >> S;
    N = S + 1;

    mas = new bool[N];

    for (i = 2; i <= S; i++) mas[i] = true;

    for (i = 2; ((i * i) <= S); i++)
        if (mas[i])
            for (j = (i * i); j <= S; j += i)
                if (mas[j]) mas[j] = false;

    int border = 99;
    cout << "\nПростые числа: ";
    for (i = 2; i <= S; i++)
    {
        if (i > border)
        {
            cout << "\n"; border += 100;
        }
        if (mas[i])
        {
            cout << i << " ";
        }
    }
}