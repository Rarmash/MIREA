#define _USE_MATH_DEFINES
#include <iostream>
#include <cmath>
using namespace std;

float krug(double r) {
    return M_PI * pow(r, 2);
}

float prya(double a, double b) {
    return a * b;
}

float treu(double a, double b, double c) {
    double p = (a + b + c) / 2;
    return sqrt(p * (p - a) * (p - b) * (p - c));
}

int main()
{
    setlocale(0, "");
    system("cls||clear");
    int n;
    cout << "Выберите фигуру (введите число)" << endl << "1. Круг" << endl << "2. Прямоугольник" << endl << "3. Треугольник" << endl << "Число: ";
    cin >> n;
    switch (n)
    {
    case 1: {
        system("cls||clear");
        cout << "Введите радиус круга: ";
        double r;
        cin >> r;
        if (r < 0) {
            cout << "Числа не могут быть отрицательными";
            exit(0);
        }
        cout << "Ответ: " << krug(r);
        break;
    }
    case 2: {
        system("cls||clear");
        cout << "Введите стороны A и B: ";
        double a, b;
        cin >> a >> b;
        if (a < 0 || b < 0) {
            cout << "Числа не могут быть отрицательными";
            exit(0);
        }
        cout << "Ответ: " << prya(a, b);
        break;
    }
    case 3: {
        system("cls||clear");
        int k = 0;
        while (k == 0) {
            cout << "Введите три стороны треугольника: ";
            double a, b, c;
            cin >> a >> b >> c;
            if (a < 0 || b < 0 || c < 0) {
                cout << "Числа не могут быть отрицательными";
                exit(0);
            }
            if (a + b < c || b + c < a || c + a < b) {
                cout << "Треугольник с такими сторонами не существует" << endl;
            }
            else {
                cout << "Ответ: " << treu(a, b, c);
                k = 1;
            }
        }
        break;
    }
    }
}