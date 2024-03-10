#include <iostream>
#include <fstream>
#include <cmath>
using namespace std;

void inFile(ofstream& fout, const char* name) {
    fout.open(name, ios::out | ios::trunc);
    for (int i = -15; i < 1; i++) {
        fout << i << endl;
    }
    if (fout.good()) {
        cout << "При создании файла ошибок не возникло.\n";
    }
    else {
        cout << "При создании файла возникли ошибки\n";
    }
    fout.close();
}

void fileOutput(const char* name) {
    int x;
    ifstream fout;
    fout.open(name, ios::in);
    while (fout >> x) {
        cout << x << " ";
    }
    cout << endl;
    fout.close();
}

void addNewLine(ofstream& fout, const char* name, int x) {
    fout.open(name, ios::out | ios::app);
    fout << x;
    if (fout.good()) {
        cout << "При добавлении записи в файл ошибок не возникло.\n";
    }
    else {
        cout << "При записи в файл возникла ошибка\n";
    }
    fout.close();
}

void findNumbers(const char* name, int nums) {
    ifstream fout;
    fout.open(name);
    int x;
    int i;
    cout << "> ";
    for (i = 1; (i < nums && (!fout.eof())); i++) {
        fout >> x;
        fout.get();
    }
    while (!fout.eof() && (i == nums)) {
        fout >> x;
        fout.get();
        cout << x;
        i++;
    }
    if (fout.good()) {
        cout << "В функции нахождения порядкового номера ошибок не возникло.\n";
    }
    fout.close();
}

void length_file(const char* name) {
    ifstream fout;
    fout.open(name);
    int n = 0;
    int x;
    cout << endl;
    while (!fout.eof()) {
        fout >> x;
        fout.get();
        n++;
    }
    cout << n << " чисел содержится в файле.\n";
    fout.close();
}

void personalTask(const char* a_file, const char* b_file) {
    ifstream first_file;
    first_file.open(a_file, ios::in);
    ofstream second_file;
    second_file.open(b_file, ios::out | ios::trunc);
    int x;
    int negativeSum = 0;
    int negativeCount = 0;

    while (first_file >> x) {
        if (x < 0) {
            negativeSum += x;
            negativeCount++;
        }
    }

    if (negativeCount > 0) {
        double negativeAverage = static_cast<double>(negativeSum) / negativeCount;
        second_file.close();
        second_file.open(b_file, ios::out | ios::app);
        first_file.clear();
        first_file.seekg(0);

        while (first_file >> x) {
            if (x < 0) {
                x += static_cast<int>(negativeAverage);
            }
            second_file << x << endl;
        }
    }

    if (second_file.good()) {
        cout << "Ошибок при вводе не найдено.\n";
    }

    first_file.close();
    second_file.close();

    ifstream output_second_file;
    output_second_file.open(b_file, ios::in);
    cout << "Вывод чисел нового файла:\n";
    while (output_second_file >> x) {
        cout << x << " ";
    }
    cout << endl;
}

int main() {
    setlocale(LC_ALL, "Russian");
    ofstream fout;
    if (!fout) {
        cout << "Не удаётся открыть файл";
        return -1;
    }

    string inputFileName;
    cout << "Введите имя первого текстового файла: ";
    cin >> inputFileName;

    inFile(fout, inputFileName.c_str());
    fileOutput(inputFileName.c_str());

    int x;
    cout << "Введите элемент, который хотите добавить: ";
    cin >> x;
    addNewLine(fout, inputFileName.c_str(), x);

    int nums;
    cout << "Введите порядковый номер в файле: ";
    cin >> nums;
    findNumbers(inputFileName.c_str(), nums);
    length_file(inputFileName.c_str());

    string outputFileName = "secondfile.txt";
    personalTask(inputFileName.c_str(), outputFileName.c_str());

    return 0;
}
