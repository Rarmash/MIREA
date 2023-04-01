#include <iostream>
#include <cmath>
using namespace std;

unsigned int factorial(unsigned int n)
{
	if (n == 0)
		return 1;
	return n * factorial(n - 1);
}

int main()
{
	setlocale(LC_ALL, "Russian");
	int n = 0, k = 0;
	double eps = 10e-6;
	double s = 0, x;
	cout << "Введите X: "; cin >> x;
	for (k = 0; k <= n; k++) {
		s += factorial(x + k) / (pow(3, k) * (3*k+2));
	}
	cout << s << endl;
}