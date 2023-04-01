#include <iostream>
#include <cmath>
using namespace std;

int main()
{
    setlocale(0, "");
    float x;
    cout << "x            y\n";
    for (x = (-4); x <= 4; x += 0.5) {
        if (x == 1) {
            cout << x << "\tНеопределено\n";
        }
        else {
            double y = (pow(x, 2) - 2 * x + 2) / (x - 1);
            cout << x << "\t" << y << "\n";
        }
    }
}