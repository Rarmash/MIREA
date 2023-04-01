#include <iostream>
#include <fstream>
#include <iomanip>
#include <ctime>
#include <format>
#include <string>
using namespace std;

void set(int** a, int m, int n)
{
    ofstream File("matrix.txt", ios::app);
    cout << "Заполните матрицу " << m << "x" << n << ":\n";
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++) {
            cin >> a[i][j];
            File << a[i][j];
            if (j == n - 1) {
                File << "\n";
            }
            else {
                File << " ";
            }
        }
    File.close();
}

bool is_RowSameEl(int* a, int n)
{
    for (int j = 0; j < n - 1; j++)
        if (a[j] != a[j + 1]) return false;
    return true;
}

bool is_ColSameEl(int** a, int m, int idx)
{
    for (int i = 0; i < m - 1; i++)
        if (a[i][idx] != a[i + 1][idx]) return false;
    return true;
}

int main()
{
    setlocale(LC_ALL, "Russian");
    ofstream File("matrix.txt"); File.close();
    srand((int)time(0));
    int m, n, l;
    cout << "Введите размеры матриц:\n";
    cin >> m >> n;
    int k = 0;
    for (l=1; l <= 10; l++) {
        k += 1;
        int r = 0, c = 0;
        int** a = new int* [m];
        for (int i = 0; i < m; i++)
            a[i] = new int[n];

        set(a, m, n);

        ofstream File("matrixresult.txt", ios::app);
        File << "Матрица " << k << ":\n";
        for (int i = 0; i < m; i++)
            if (is_RowSameEl(a[i], n)) {
                r = 1; cout << "Ряд " << i + 1 << ":  Значение=" << a[i][0] << "\n";
                File << "Ряд " << i + 1 << ":  Значение=" << a[i][0] << "\n";
            }

        if (r == 0) {
            cout << "Матрица не содержит ряды с одинаковыми значениями\n";
            File << "Матрица не содержит ряды с одинаковыми значениями\n";
        }

        for (int j = 0; j < n; j++)
            if (is_ColSameEl(a, m, j)) { 
                c = 1; cout << "Столбец " << j + 1 << ":  Значение=" << a[0][j] << "\n";
                File << "Столбец " << j + 1 << ":  Значение=" << a[0][j] << "\n";
            }

        if (c == 0) {
            cout << "Матрица не содержит столбцы с одинаковыми значениями\n";
            File << "Матрица не содержит столбцы с одинаковыми значениями\n";
        }

        for (int i = 0; i < m; i++)
            delete[]a[i];
        delete[]a;

        File.close();
    }
    return 0;
}