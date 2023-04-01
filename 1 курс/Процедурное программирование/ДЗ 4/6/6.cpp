#include <iostream>
#include <string>
#include <vector>
using namespace std;


bool CheckTheThree(string num)
{
	for (int i = 0; i < num.length(); i++)
	{
		return (num[i] == num[i + 1]) && (num[i] == num[i + 2]) && (num[i] == num[i + 3]) ? false : true;
	}
}

bool CheckExisting(string num)
{
	vector<string> errorNums = {
		"","VX","VIV","VIIX","VIIV","VIIXL","IIIV","IIV","IIII","IIX","XIIII","XIIIIX",
		"XIIIIX","XXXX","LL","CLC","CLL","DLD","LDD","LLI","MMMCMXCX","AXX","LXA","XXIXX",
		"VVX","VXV","IVI","XLX","LCL","IVV","IXX","XLL","XCC","CDD","CMM","LXL","DCD",
		"MDXCLXIVIL","MDCILXIVIIX","MDMMCLXIVICI"
	};

	if (std::find(errorNums.begin(), errorNums.end(), num) != errorNums.end())
	{
		cout << "Данное римское число не существует!" << endl;
		return false;
	}

	return true;
}

bool ToRimNumbers(string num, int arr[])
{
	for (int i = 0; i < num.length(); i++)
	{
		switch (num[i])
		{
		case 'I':
			arr[i] = 1;
			break;
		case 'V':
			arr[i] = 5;
			break;
		case 'X':
			arr[i] = 10;
			break;
		case 'L':
			arr[i] = 50;
			break;
		case 'C':
			arr[i] = 100;
			break;
		case 'D':
			arr[i] = 500;
			break;
		case 'M':
			arr[i] = 1000;
			break;

		default:
			cout << "Нужно вводить ТОЛЬКО римские цифры!" << endl;
			return false;
		}
	}

	return true;
}

void main()
{
	setlocale(0, "");
	string rimNum;
	int rimskNums[20]{};
	cout << "Введите римское число: ";
	cin >> rimNum;

	if (!CheckTheThree(rimNum))
	{
		cout << "Нельзя вносить больше трёх цифр!" << endl;
		return;
	}
	if (!CheckExisting(rimNum)) return;
	if (!ToRimNumbers(rimNum, rimskNums)) return;

	int sum = 0;
	for (int i = 0; i < rimNum.length(); i++)
	{
		if (rimskNums[i] == 0) {
			cout << "Неправильный ввод";
			return;
		}
		if (rimskNums[i] < rimskNums[i + 1])
		{
			sum += rimskNums[i + 1] - rimskNums[i];
			i++;
			continue;
		}
		sum += rimskNums[i];
	}

	cout << "Число: " << sum << endl;
}