#include <iostream>
#include <fstream>
#include <bitset>
#include <ctime>
#include <cstdlib>
#include <chrono>
using namespace std;

void Task2()
{
    const long int size = 10000000;  // Размер std::bitset (10^7 бит)
    bitset<size>* numbers = new bitset<size>;  // Используем std::bitset для хранения битов

    long int input;
    ofstream outfile("sortedList.txt");

    cout << "Введите по очереди семизначные числа (0 для завершения):\n";
    while (true) {
        cin >> input;
        if (input == 0) {
            break;
        }
        if (input >= size / 10 && input <= 9999999) {
            numbers->set(input - 1, 1);
        }
        else {
            cout << "Только семизначные числа!\n";
        }
    }

    char spam;

    cout << "Нужно ли дополнительно ввести 1000 семизначных чисел? (y/n): ";
    cin >> spam;

    if (spam == 'y' || spam == 'Y') {
        srand(static_cast<unsigned int>(time(nullptr)));
        for (long int i = 999; i > 1; i--) {
            long int random_input = ((size / 10) * (1 + rand() % (8))) + ((rand() % (i + 1)) * (990));
            numbers->set(random_input, 1);
        }
    }

    auto start_time = chrono::high_resolution_clock::now();
    for (long int i = 0; i < size; i++) {
        if (numbers->test(i)) {
            outfile << i + 1 << endl;
        }
    }
    auto end_time = chrono::high_resolution_clock::now();
    auto exec_time = chrono::duration_cast<chrono::milliseconds>(end_time - start_time);
    outfile.close();
    cout << "Результат сортировки был записан в файл.\n";
    cout << "На выполнение затрачено " << exec_time.count() << " миллисекунд." << endl;
}

int main() {
    setlocale(LC_ALL, "RUS");
    Task2();
    return 0;
}
