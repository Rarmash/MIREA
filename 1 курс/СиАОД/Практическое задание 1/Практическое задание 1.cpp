#include <iostream>
#include <vector>
using namespace std;

int digitroot(int n) {
    if (n < 10) {
        return n;
    }
    else {
        int s = 0;
        while (n != 0) {
            s += n % 10;
            n /= 10;
        }
        return digitroot(s);
    }
}

void pasteelement(int n, int x[], int num, int pos)
{
    int i;
    for (i = n; i > pos; i--) {
        x[i] = x[i - 1];
    }
    x[pos] = num;
}

vector<int> vectorpasteelement(vector<int> x, int num, int pos)
{
    x.emplace(x.begin() + pos, num);
    return x;
}

vector<int> vectordeleteelement(vector<int> x)
{
    bool k = true;
    while (k) {
        k = false;
        for (int i = 0; i < x.size(); i++) {
            if (digitroot(x[i]) == 7) {
                x.erase(x.begin() + i);
                k = true;
                break;
            }
        }
    }
    return x;
}

void deleteelement(int x[], int n, int pos)
{
    for (int i = pos; i < n; ++i)
    {
        x[i] = x[i + 1];
    }
}

template <typename ArrayType1>
void PrintArray(ArrayType1 arr, int size) {
    for (int i = 0; i < size; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
}

void ArrayFilling(int* arr, int size)
{
    cout << "Заполните массив: ";
    for (int i = 0; i < size; i++)
    {
        cin >> arr[i];
    }
}

void ArrayFilling(vector<int>& arr, int size)
{
    int num;
    cout << "Заполните массив: ";
    for (int i = 0; i < size; i++)
    {
        cin >> num;
        arr.push_back(num);
    }
}


int vibor0() {
    cout << "---" << endl;
    cout << "Выберите тип массива:\n1. Статический\n2. Динамический\n3. С помощью <vector>" << endl;
    cout << "---" << endl;
    int n;
    cin >> n;
    return n;
}

int vibor()
{
    int n;
    cout << "---" << endl;
    cout << "Выберите номер задания" << endl;
    cout << "1. Найти индекс элемента массива, цифровой корень которого равен 7." << endl;
    cout << "2. Вставить новый элемент перед элементом, цифровой корень которого равен 7. Считать, что такое число одно." << endl;
    cout << "3. Удалить элементы массива цифровой корень которых равен 7." << endl;
    cout << "---" << endl;
    cin >> n;
    return n;
}

int main()
{
    setlocale(LC_ALL, "RU");
    cout << "Лабораторная работа №1 по теме Простые алгоритмы сортировки массивов. Гришин Андрей" << endl;
    switch (vibor0()) {
    case 1: {
        while (true) {
            const int ARRSIZE = 10;
            int x[ARRSIZE];
            ArrayFilling(x, ARRSIZE);
            switch (vibor()) {
            case 1: {
                bool k = false;
                for (int i = 0; i < ARRSIZE; i++) {
                    if (digitroot(x[i]) == 7) {
                        cout << "Цифровой корень равен 7 у элемента под индексом " << i;
                        cout << endl;
                        k = true;
                    }
                }
                if (k == false) {
                    cout << "Элементы с цифровым корнем 7 отсутствуют.";
                    cout << endl;
                }
                break;
            }
                  break;
            case 2: {
                int y;
                int r = 1000000;
                int k = 0;
                for (int i = 0; i < ARRSIZE; i++) {
                    if (digitroot(x[i]) == 7) {
                        if (i < r) {
                            r = i;
                        }
                        k++;
                    }
                }
                if (k == 0) {
                    r = 0;
                }
                cout << "Введите элемент для вставки: "; cin >> y;
                if (r != 1000000) {
                    pasteelement(ARRSIZE, x, y, r);
                    int d = ARRSIZE + 1;
                    PrintArray(x, d);
                    continue;
                }
                else {
                    cout << "Ошибка. Введите другой массив." << endl;
                }
                break;
            }
                  break;
            case 3: {
                int k = 0;
                for (int i = 0; i < ARRSIZE; i++) {
                    if (digitroot(x[i]) == 7) {
                        deleteelement(x, ARRSIZE, i);
                        k++;
                    }
                }
                if (k != 0) {
                    PrintArray(x, ARRSIZE - k);
                }
                else {
                    cout << "Ошибка удаления элементов." << endl;
                }
                break;
            }
            }
        }
    }
    break;
    case 2: {
        while (true)
        {
            int ARRSIZE;
            cout << "Введите размерность массива: ";
            cin >> ARRSIZE;
            int* x = new int[ARRSIZE];
            ArrayFilling(x, ARRSIZE);
            switch (vibor()) {
            case 1: {
                bool k = false;
                for (int i = 0; i < ARRSIZE; i++) {
                    if (digitroot(x[i]) == 7) {
                        cout << "Цифровой корень равен 7 у элемента под индексом " << i << endl;
                        k = true;
                    }
                }
                if (k == false) {
                    cout << "Элементы с цифровым корнем 7 отсутствуют.";
                }
                cout << endl;
                break;
            }
                  break;
            case 2: {
                int y;
                int r = 1000000;
                for (int i = 0; i < ARRSIZE; i++) {
                    if (digitroot(x[i]) == 7) {
                        if (i < r) {
                            r = i;
                        }
                    }
                }
                cout << "Введите элемент для вставки: "; cin >> y;
                if (r != 1000000) {
                    x = (int*)realloc(x, sizeof(int) * (ARRSIZE + 1));
                    pasteelement(ARRSIZE, x, y, r);
                    int d = ARRSIZE + 1;
                    PrintArray(x, d);
                }
                else {
                    cout << "Ошибка. Введите другой массив." << endl;
                }
                break;
            }
                  break;
            case 3: {
                int k = 0;
                for (int i = 0; i < ARRSIZE; i++) {
                    if (digitroot(x[i]) == 7) {
                        deleteelement(x, ARRSIZE, i);
                        k++;
                    }
                    x = (int*)realloc(x, sizeof(int) * ARRSIZE-k);
                }
                if (k != 0) {
                    PrintArray(x, ARRSIZE - k);
                }
                else {
                    cout << "Ошибка удаления элементов." << endl;
                }
                break;
            }
            }
        }
    }
    break; 
    case 3: {
        while (true)
        {
            int ARRSIZE;
            cout << "Введите размерность массива: ";
            cin >> ARRSIZE;
            vector<int> x;
            ArrayFilling(x, ARRSIZE);
            switch (vibor()) {
            case 1: {
                bool k = false;
                for (int i = 0; i < ARRSIZE; i++) {
                    if (digitroot(x[i]) == 7) {
                        cout << "Цифровой корень равен 7 у элемента под индексом " << i;
                        k = true;
                        cout << endl;
                    }
                }
                if (k == false) {
                    cout << "Элементы с цифровым корнем 7 отсутствуют.";
                    cout << endl;
                }
                break;
            }
                  break;
            case 2: {
                int y;
                int r = 1000000;
                for (int i = 0; i < ARRSIZE; i++) {
                    if (digitroot(x[i]) == 7) {
                        if (i < r) {
                            r = i;
                        }
                    }
                }
                cout << "Введите элемент для вставки: "; cin >> y;
                if (r != 1000000) {
                    x = vectorpasteelement(x, y, r);
                    PrintArray(x, x.size());
                }
                else {
                    cout << "Ошибка. Введите другой массив." << endl;
                }
                break;
            }
            break;
            case 3: {
                int k = 0;
                for (int i = 0; i < ARRSIZE; i++) {
                    x = vectordeleteelement(x);
                    k++;
                }
                if (k != 0) {
                    PrintArray(x, x.size());
                }
                else {
                    cout << "Ошибка удаления элементов." << endl;
                }
                break;
            }
            }
        }
    }
    break;
    default: {
        cout << "Вы ввели неверный номер.";
        break;
    }
    }
}