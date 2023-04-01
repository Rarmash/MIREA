#include <iostream>
#include <Windows.h>
#include <conio.h>
#include <stdio.h>
using namespace std;

int main() {
	for (int i = 0; i < 13; i++) {
		if (i == 0) {
			for (int x = 0; x < 40; x++) {
				if (x < 8) {
					cout << "- ";
				}
				else {
					cout << "-";
				}
			}
			cout << endl;
		}
		else if (i % 2 == 0 && i > 0 && i < 7) {
			for (int y = 0; y < 40; y++) {
				if (y < 8) {
					cout << "* ";
				}
				else {
					cout << "-";
				}
			}
			cout << endl;
		}
		else if (i % 2 == 1 && i > 0 && i < 6) {
			for (int y = 0; y < 40; y++) {
				if (y < 8) {
					cout << "* ";
				}
				else {
					cout << "-";
				}
			}
			cout << endl;
		}
		else if (i % 2 == 1 && i > 6) {
			for (int r = 0; r < 48; r++) {
				cout << "-";
			}
			cout << endl;
		}
		else if (i % 2 == 0 && i > 6) {
			for (int r = 0; r < 48; r++) {
				cout << "-";
			}
			cout << endl;
		}
	}

}