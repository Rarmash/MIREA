#include <iostream>
using namespace std;

int main()
{
    setlocale(0, "");
    bool den, shtori, lamp;
    cout << "День? (1 или 0)\n";
    cin >> den;
    cout << "Шторы открыты? (1 или 0)\n";
    cin >> shtori;
    cout << "Лампа включена? (1 или 0)\n";
    cin >> lamp;
    if (lamp == true) {
        cout << "В комнате светло.";
    }
    else if (shtori == true && den == true) {
        cout << "В комнате светло.";
    }
    else {
        cout << "В комнате темно.";
    }
}