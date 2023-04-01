#include <iostream>
using namespace std;

int main()
{
    setlocale(0, "");
    int x;
    cout << "Введите число: ";
    cin >> x;
    if (x >= 1) {
        for (int i = 1; i <= 10; i++) {
            cout << x << "\n";
            x = x + 1;
        }
    }
    else {
        cout << "Вы ввели ненатуральное число.";
    }
}