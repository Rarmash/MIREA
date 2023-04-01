#include <iostream>
#include <string>
#include <conio.h>
#include <cstring>

using namespace std;


string reverse(string const& s)
{
	string rev;
	for (int i = s.size() - 1; i >= 0; i--) {
		rev = rev.append(1, s[i]);
	}
	return rev;
}

int main()
{
	setlocale(LC_ALL, "RUS");
	cout <<  "Число: ";
	string p;
	cin >> p;
	cout << "Система счисления: ";
	int ss;
	cin >> ss;
	int as = 0;
	while (as != 1) {
		for (int i = 0; i < p.size(); i++)
		{
			if (p[i] == '2' && ss <= 2 || p[i] == '3' && ss <= 3 || p[i] == '4' && ss <= 4 || p[i] == '5' && ss <= 5 || p[i] == '6' && ss <= 6 || p[i] == '7' && ss <= 7 || p[i] == '8' && ss <= 8 || p[i] == '9' && ss <= 9 || p[i] == 'A' && ss <= 10 || p[i] == 'B' && ss <= 11 || p[i] == 'C' && ss <= 12 || p[i] == 'D' && ss <= 13 || p[i] == 'F' && ss <= 14 || p[i] == 'E' && ss <= 15) {
				as = 0;
				break;
			}
			else {
				as = 1;
			}
		}
		if (as == 0) {
			cout << "Введите верную систему счисления: ";
			cin >> ss;
			cout << endl;
		}
	}
	cout << "Введите основание, в котором оно должно быть в итоге: ";
	int oss;
	cin >> oss;
	int D = 0;
	int n;
	int i = 0;
	int a = 0;
	n = p.size();
	string otvet;

	char q;
	if (ss != oss) {
		if (ss < 11)
		{
			while (n > 0) {
				q = p[i];
				a = q - '0';
				D = D + a * pow(ss, n - 1);
				i++;
				n--;
			}
		}
		else
		{
			while (n > 0) {
				if (p[i] == 'A' || p[i] == 'B' || p[i] == 'C' || p[i] == 'D' || p[i] == 'E' || p[i] == 'F')
				{
					if (p[i] == 'A')
					{
						a = 10;
					}
					if (p[i] == 'B')
					{
						a = 11;
					}
					if (p[i] == 'C')
					{
						a = 12;
					}
					if (p[i] == 'D')
					{
						a = 13;
					}
					if (p[i] == 'E')
					{
						a = 14;
					}
					if (p[i] == 'F')
					{
						a = 15;
					}
				}
				else
				{
					q = p[i];
					a = q - '0';
				}
				D = D + a * pow(ss, n - 1);
				i++;
				n--;
			}
		}
		while (D > 0)
		{
			int p = D % oss;
			if (p == 0) {
				otvet += '0';
			}
			if (p == 1) {
				otvet += '1';
			}
			if (p == 2) {
				otvet += '2';
			}
			if (p == 3) {
				otvet += '3';
			}
			if (p == 4) {
				otvet += '4';
			}
			if (p == 5) {
				otvet += '5';
			}
			if (p == 6) {
				otvet += '6';
			}
			if (p == 7) {
				otvet += '7';
			}
			if (p == 8) {
				otvet += '8';
			}
			if (p == 9) {
				otvet += '9';
			}
			if (p == 10) {
				otvet += 'A';
			}
			if (p == 11) {
				otvet += 'B';
			}
			if (p == 12) {
				otvet += 'C';
			}
			if (p == 13) {
				otvet += 'D';
			}
			if (p == 14) {
				otvet += 'F';
			}
			if (p == 15) {
				otvet += 'E';
			}
			D = D / oss;
		}
		string S = reverse(otvet);
		cout << S << endl;

	}
	else
	{
		cout << p;
	}
}