#include <iostream>
#include "stdlib.h"
#include <windows.h>
#include "math.h"
using namespace std;

void InputByUser(int arr[][100], const int n)//функция заполнения массива с входными параметрами: массив и ограничение
{
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			while (true) {
				cin >> arr[i][j];//ввод
				if (arr[i][j] > 100 || arr[i][j] < 1 || arr[i][j] != round(arr[i][j])) {
					cout << "It is advisable to enter numbers from 1 to 100" << endl;
					cout << "Enter the correct number for the remaining matrix!\n";
					cin.clear();
					while (cin.get() != '\n');
				}
				else break;
			}
		}
	}
}

void InputRandom(int arr[][100], const int n) {
	for (int i = 0; i < n; ++i, cout << endl)
		for (int j = 0; j < n; ++j) {
			arr[i][j] = rand() % 100;
		}
}

void Cout(int arr[][100], const int n)//функция вывода массива
{
	setlocale(LC_ALL, "rus");
	cout << "Our matrix \n";
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << arr[i][j] << " ";//вывод
		}
		cout << endl;
	}
}

void Sort(int arr[][100], const int n)//сортировка
{
	setlocale(LC_ALL, "rus");
	int i, j, key;
	for (i = 1; i < n * n; ++i) {
		key = arr[i / n][i % n];
		for (j = i - 1; j >= 0; j--) {
			if (key > arr[j / n][j % n])
				arr[(j + 1) / n][(j + 1) % n] = arr[j / n][j % n];
			else break;
		}
		arr[(j + 1) / n][(j + 1) % n] = key;
	}
	cout << "Ordered matrix" << endl;
	for (i = 0; i < n; ++i, cout << endl)
		for (j = 0; j < n; ++j)
			cout << arr[i][j] << ' ';
}

int main()
{
	setlocale(LC_ALL, "rus");
	int arr[100][100];
	float n = 0;//объявление массива и границы
	while (true)
	{
		cout << "Select the boundary of the matrix from among the 2, 4, 6 or 8: " << endl;
		cin >> n;
		if ((int)n % 2 == 1 || n > 8 || n < 2 || n != round(n))
		{
			cout << "The border is selected incorrectly, repeat the input!\n";
			cin.clear();
			while (cin.get() != '\n');
		}
		else break;
	}
	float result = 0;
	while (true) {
		cout << "Choose how to fill in the matrix: " << endl;
		cout << "1 - By yourself ;" << endl;
		cout << "2 - Automatically" << endl;
		cin >> result;
		if (result > 2 || result < 1 || result != round(result))
		{
			cout << "You need to choose a number, 1 or 2!\n";
			cin.clear();
			while (cin.get() != '\n');

		}
		else break;
	}

	if (result == 1) {
		InputByUser(arr, n);//считывание массива
		Cout(arr, n);//вывод массива
		Sort(arr, n);
	}
	else {
		InputRandom(arr, n);//считывание массива
		Cout(arr, n);//вывод массива
		Sort(arr, n);
	}
}