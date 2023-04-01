#include <iostream>
#include <math.h>
using namespace std;

int main() {
	setlocale(LC_ALL, "Russian");
	int m, n;
	cout << "Введите крайние точки промежутка: ";
	cin >> m >> n;
	if (m > n) {
		cout << "Вы что-то перепутали. Точки должны быть по возрастанию. Меняю местами." << endl;
		int tmp = n;
		n = m;
		m = tmp;
	}
	for (int i = m; i <= n; i++) {
		for (int j = m; j <= n; j++) {
			for (int k = m; k <= n; k++) {
				if (i * i + j * j == k * k && i < j) 
				{ 
					cout << "{" << i << ";" << j << ";" << k << "}" << endl; 
				}
			}
		}
	}
}