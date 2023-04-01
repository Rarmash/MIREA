#include <iostream>
#include <cmath>
using namespace std;

int main()
{
    setlocale(0, "");
    float x, a;
    cout << "Введите X: ";
    cin >> x;
    cout << "Введите A: ";
    cin >> a;
    if (a < pow(x, 2) || abs(x) == 0) {
        cout << "Неопределено";
    }
    else if (abs(x) < 1) {
        float w = a * log(abs(x));
        cout << w;
    }
    else {
        float w = sqrt(a - pow(x, 2));
        cout << w;
    }
}