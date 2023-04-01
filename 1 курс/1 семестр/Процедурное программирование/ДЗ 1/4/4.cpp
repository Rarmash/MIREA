#include <iostream>
#include <cmath>
using namespace std;

int main()
{
    setlocale(0, "");
    float a, b, c;
    cin >> a >> b >> c;
    float d = pow(b, 2) - 4 * a * c;
    if (d < 0) {
        cout << "Корней нет";
    }
    else if (d == 0) {
        float x = (-b) / (2 * a);
        cout << x;
    }
    else {
        float x1 = (-b + sqrt(d)) / (2 * a);
        float x2 = (-b - sqrt(d)) / (2 * a);
        cout << x1 << "\n" << x2 << "\n";
    }
}