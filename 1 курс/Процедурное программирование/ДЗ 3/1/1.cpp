#include <iostream>
#include <cmath>
using namespace std;

int main()
{
    setlocale(0, "");
    double S, p, n, r;
    cout << "Введите сумму займа: ";
    cin >> S;
    cout << "Введите процент: ";
    cin >> p;
    cout << "Введите количество лет: ";
    cin >> n;
    if (S <= 0 || p <= 0 || n <= 0) {
        cout << "Ни одна переменная не должна равняться или быть меньше нуля.";
        return(0);
    }
    r = p / 100;
    double m = (S * r * pow((1 + r), n)) / (12 * (pow((1 + r), n) - 1));
    cout << m;
}