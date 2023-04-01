#include <string>
#include <iostream>
using namespace std;


void main()
{
	setlocale(0, "");
	double a[3][4]{}, b[4][2]{}, c[3][2];
	cout << "Введите матрицу А: " << endl;
	for (int i = 0; i < 3; i++)
	{
		cin >> a[i][0] >> a[i][1] >> a[i][2] >> a[i][3];
		if (a[i][0] < 0 || a[i][1] < 0 || a[i][2] < 0 || a[i][3] < 0)
		{
			cout << "Значения должны быть положительными!" << endl;
			return;
		}
		cout << endl;
	}
	cout << "Введите матрицу B: " << endl;
	for (int i = 0; i < 4; i++)
	{
		cin >> b[i][0] >> b[i][1];
		if (b[i][0] < 0 || b[i][1] < 0)
		{
			cout << "Значения должны быть положительными!" << endl;
			return;
		}
		cout << endl;
	}

	for (int k = 0; k <= 2; k++)
	{
		for (int i = 0; i <= 1; i++)
		{
			c[k][i] = a[k][0] * b[0][i] + a[k][1] * b[1][i] + a[k][2] * b[2][i] + a[k][3] * b[3][i];
		}
	}
	cout << "Матрица С (A*B): " << endl;
	for (int k = 0; k <= 2; k++)
	{
		for (int i = 0; i <= 1; i++)
		{
			cout << c[k][i] << " ";
		}
		cout << endl;
	}
	double maxtrade = max(c[0][0], max(c[1][0], c[2][0]));
	double mintrade = min(c[0][0], min(c[1][0], c[2][0]));
	for (int i = 0; i < 3; i++)
	{
		if (maxtrade == c[i][0])
			cout << "Продавец, который больше всего выручил с продажи: " << i + 1 << endl;
		else if (mintrade == c[i][0])
			cout << "Продавец, который меньше всего выручил с продажи: " << i + 1 << endl;
	}

	cout << endl;
	double maxcom = max(c[0][1], max(c[1][1], c[2][1]));
	double mincom = min(c[0][1], min(c[1][1], c[2][1]));
	for (int i = 0; i < 3; i++)
	{
		if (maxcom == c[i][1])
			cout << "Продавец с наибольшими комиссионными: " << i + 1 << endl;
		else if (mincom == c[i][1])
			cout << "Продавец с наименьшими комиссионными: " << i + 1 << endl;
	}

	cout << endl;
	cout << endl;
	cout << "Всего денег потрачено на товары: " << c[0][0] + c[1][0] + c[2][0] << endl;
	cout << "Всего комиссионных у продавцов: " << c[0][1] + c[1][1] + c[2][1] << endl;
	cout << "Всего денег прошло через продавцов: "
		<< c[0][0] + c[1][0] + c[2][0] - (c[0][1] + c[1][1] + c[2][1]) << endl;
}