#include <iostream>
#include <fstream>
using namespace std;

void gen(int m, int b, int c, int n) {
    int s = 0;
    for (int i = 1; i <= n; i++) {
        s = (m * s + b) % c;
        cout << s << " ";
    }
}

int main()
{
    setlocale(0, "");
    cout << "Выберите вариант генерации (1 / 2): ";
    int num;
    cin >> num;
    int m, b, c;
    if (num == 1) {
        m = 37;
        b = 3;
        c = 64;
    } else if (num == 2) {
        m = 25173;
        b = 13849;
        c = 65537;
    }
    int n;
    cout << "Введите кол-во чисел, необходимое для генерации: ";
    cin >> n;
    gen(m, b, c, n);
}