#include <iostream>
using namespace std;

int main()
{
    setlocale(0, "");
    cout << "Введите два числа:\n";
    float a, b;
    cin >> a >> b;
    cout << "Сумма: " << a + b << "\nРазность: " << a - b << "\nПроизведение: " << a * b;
    if (b != 0) {
        float x = a / b;
        cout << "\nЧастное: " << x;
    }
}