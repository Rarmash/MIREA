#include <iostream>
#include <cmath>
using namespace std;

int main()
{
    setlocale(0, "");
    const double PI = acos(-1.0);
    int h, R, r, l;
    cout << "Введите высоту: ";
    cin >> h;
    cout << "Введите больший радиус: ";
    cin >> R;
    cout << "Введите меньший радиус: ";
    cin >> r;
    cout << "Введите образующую: ";
    cin >> l;
    float V = (PI * h * (pow(R, 2) + R * r + pow(r, 2))) / 3;
    float S = PI * (pow(R, 2) + (R * r) * l + pow(r, 2));
    cout << "Объём равен " << V;
    cout << "\nПлощадь равна " << S;
}