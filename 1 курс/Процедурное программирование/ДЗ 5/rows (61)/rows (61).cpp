#include <iostream>
#include <iomanip>
#include <ctime>
using namespace std;

void set(int** a, int m, int n)
{
    cout << "Заполните матрицу " << m << "x" << n << ":\n";
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            cin >> a[i][j];
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

int countElementsInRange(int** a, int m, int n)
{
    int count = 0;
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            if (a[i][j] >= 5 && a[i][j] <= 9) count++;
    return count;
}

int main()
{
    setlocale(LC_ALL, "Russian");
    srand((int)time(0));
    int m, n, r = 0, c = 0;
    cout << "Введите размеры матрицы:\n";
    cin >> m >> n;

    int** a = new int* [m];
    for (int i = 0; i < m; i++)
        a[i] = new int[n];

    set(a, m, n);

    for (int i = 0; i < m; i++)
        if (is_RowSameEl(a[i], n)) { r = 1; cout << "Ряд " << i + 1 << ":  Значение=" << a[i][0] << "\n"; }

    if (r == 0) cout << "Матрица не содержит ряды с одинаковыми значениями\n";

    for (int j = 0; j < n; j++)
        if (is_ColSameEl(a, m, j)) { c = 1; cout << "Столбец " << j + 1 << ":  Значение=" << a[0][j] << "\n"; }

    if (c == 0) cout << "Матрица не содержит столбцы с одинаковыми значениями\n";

    cout << "Всего значений в промежутке [5,9]: " << countElementsInRange(a, m, n) << "\n";

    for (int i = 0; i < m; i++)
        delete[]a[i];
    delete[]a;
    return 0;
}