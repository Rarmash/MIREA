#include <iostream>
#include <cmath>

using namespace std;

int main() {
	setlocale(0, "");
	double S, p, n, m;
	cout << "Введите переменные S, m, n: ";
	cin >> S >> m >> n;
	if (S < 0 || n < 0)
	{
		cout << "Ошибка!" << endl;
	}
	else
	{
		p = -100;
		double r = p / 100;
		double m1 = (S * r * pow(1 + r, n)) / (12 * (pow(1 + r, n) - 1));

		while ((S * r * pow(1 + r, n)) / (12 * (pow(1 + r, n) - 1)) <= m) {
			p += 0.01;
			r = p / 100;
		}

		if (p > 0.1)
		{
			cout << "Процент: " << p - 0.01;
		}
		else
		{
			if (p < -0.0001)
			{

				cout << "Процент: " << p;
			}
			else
			{
				cout << "Процент: " << floor(p);
			}
		}
	}
}