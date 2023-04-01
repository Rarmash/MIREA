#include <iostream>
#include <fstream>
#include <string.h>
using namespace std;

int main() {
	setlocale(LC_ALL, "RUS");
	ifstream fout;
	int min = 10000;
	char a[600];
	int count = 0;
	fout.open("lorem.txt");
	while (!fout.eof()) {
		fout.getline(a, 600);
		count = 0;
		string str = string(a);
		for (int i = 0; i < str.length(); i++) {
			if (str[i] != '.') {
				count++;
			}
			else {
				if (count < min) {
					min = count;
					count = 0;
				}
			}
		}

	}
	cout << "Минимальная длина предложения = ";
	cout << min << endl;
	return 0;
}