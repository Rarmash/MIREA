#include <iostream>

using namespace std;

template <class T>
void sign(T& a)
{
    if (a > 0)
    {
        a = 1;
    }
    if (a == 0)
    {
        a = 0;
    }
    if (a < 0)
    {
        a = -1;
    }
}

int main()
{
    setlocale(LC_ALL, "RUS");
    cout << "Введите число для определения его знака. (0 - \"0\", 1 - \"+\", -1 - \"-\")" << endl;
    double q;
    cin >> q;
    sign(q);
    cout << q << endl;
}