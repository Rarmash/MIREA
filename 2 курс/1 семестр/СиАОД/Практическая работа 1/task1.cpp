#include <iostream>
#include <bitset>
using namespace std;

void FirstTask() {
    cout << "Упражение 1" << endl;
    int var1 = 0x75;
    int mask = 0x1E00;
    var1 |= mask;
    cout << "Результат (2 СС): " << bitset<sizeof(int) * 8>(var1) << endl;
    cout << "Результат (10 СС): " << var1 << endl;
}

void SecondTask() {
    cout << "Упражнение 2" << endl;
    int var2;
    cin >> var2;
    unsigned int mask = 0xFFFD7FFD; // 11111111111111010111111111111101 в бинарной
    var2 &= mask;
    cout << "Результат (2 СС): " << bitset<sizeof(int) * 8>(var2) << endl;
    cout << "Результат (10 СС): " << var2 << endl;
}

void ThirdTask() {
    cout << "Упражнение 3" << endl;
    int var3;
    cin >> var3;
    var3 = var3 << 10;
    cout << "Результат (2 СС): " << bitset<sizeof(int) * 8>(var3) << endl;
    cout << "Результат (10 СС): " << var3 << endl;
}

void FourthTask() {
    cout << "Упражнение 4" << endl;
    int var4;
    cin >> var4;
    var4 = var4 >> 10;
    cout << "Результат (2 СС): " << bitset<sizeof(int) * 8>(var4) << endl;
    cout << "Результат (10 СС): " << var4 << endl;
}

void FifthTask() {
    cout << "Упражнение 5" << endl;
    int var5;
    int mask = 1;
    cin >> var5;
    var5 |= mask;
    cout << "Результат (2 СС): " << bitset<sizeof(int) * 8>(var5) << endl;
    cout << "Результат (10 СС): " << var5 << endl;
}

int main() {
    setlocale(LC_ALL, "RUS");
    FirstTask();
    SecondTask();
    ThirdTask();
    FourthTask();
    FifthTask();
    return 0;
}