#include <iostream>
#include <vector>
#define Rows 50
#define Cols 50
using namespace std;

void MatrixFillSelf(auto arr, int size)
{
    for (int i = 0; i < size; i++)
    {
        for (int k = 0; k < size; k++)
        {
            cin >> arr[i][k];
        }
    }
}

void MatrixFillAuto(auto arr, int size)
{
    srand(time(0));
    for (int i = 0; i < size; i++)
    {
        for (int k = 0; k < size; k++)
        {
            arr[i][k] = 1 + rand() % 99;
        }
    }
}

void MatrixOutput(auto arr, int size)
{
    for (int i = 0; i < size; i++)
    {
        for (int k = 0; k < size; k++)
        {
            cout.width(3);
            cout << arr[i][k] << " ";
        }
        cout << endl;
    }
}

void MatrixCircle(auto arr, int size)
{
    int a = 0, b = 1, c = 2;
    for (int i = 0; i < size; i++) {
        for (int j = a; j < size - a; j++) {
            cout << arr[a][j] << " ";
        }
        for (int j = b; j < size - a; j++) {
            cout << arr[j][size - b] << " ";
        }
        for (int j = size - c; j >= a; j--) {
            cout << arr[size - b][j] << " ";
        }
        for (int j = size - c; j > a; j--) {
            cout << arr[j][a] << " ";
        }
        a++;
        b++;
        c++;
    }
}

void PointsFilling(vector<vector<int>>& points, int size)
{
    for (int i = 0; i < size; i++)
    {
        for (int k = 0; k < 2; k++)
        {
            points[i].resize(2);
            cin >> points[i][k];
        }
    }
}

void PrintPoints(vector<vector<int>> points)
{
    for (int i = 0; i < points.size(); i++)
    {
        for (int j = 0; j < points[i].size(); j++)
            cout << points[i][j] << " ";
        cout << '\n';
    }
}

vector<vector<int>> TriangleFill(vector<int> points1, vector<int> points2, vector<int> points3) {
    vector<vector<int>> triangle;
    triangle.resize(3);
    for (int i = 0; i < 3; i++) {
        triangle[i].resize(2);
    }
    triangle[0][0] = points1[0];
    triangle[0][1] = points1[1];
    triangle[1][0] = points2[0];
    triangle[1][1] = points2[1];
    triangle[2][0] = points3[0];
    triangle[2][1] = points3[1];
    return triangle;
}

bool PointCheck(vector<vector<int>> triangle, int x, int y) {
    int x1 = triangle[0][0];
    int x2 = triangle[1][0];
    int x3 = triangle[2][0];
    int y1 = triangle[0][1];
    int y2 = triangle[1][1];
    int y3 = triangle[2][1];
    if (((x1 - x) * (y2 - y1) - (x2 - x1) * (y1 - y)) > 0 && ((x2 - x) * (y3 - y2) - (x3 - x2) * (y2 - y)) > 0 && ((x3 - x) * (y1 - y3) - (x1 - x3) * (y3 - y)) > 0) {
        return true;
    }
    else {
        return false;
    }
}

int vibor1() {
    int menuChoice;
    cout << "1. Первое задание. Статический" << endl;
    cout << "2. Второе задание. Динамический" << endl;
    cout << "3. Третье задание. Вектор" << endl;
    cout << "Выберите задание и тип хранилища: "; cin >> menuChoice;
    cout << endl;
    return menuChoice;
}

int main()
{
    // Меню выбора типа массива
    setlocale(LC_ALL, "Russian");
    cout << "Лабораторная работа №2 по теме Двумерный массив. Гришин Андрей" << endl;
    int size;
    int num = vibor1();
    if (num == 1 || num == 2)
    {
        while (true)
        {
            cout << "Введите размер квадратной матрицы: ";
            cin >> size;
            if (size > 1)
            {
                if (num == 1 && size > Rows)
                {
                    continue;
                }
                break;
            }
        }
    }

    int taskChoice;
    switch (num)
    {
    case 1: {
        int arr[Rows][Cols];

        while (true)
        {
            cout << "\n1. Заполнить квадратную матрицу вручную" << endl;
            cout << "2. Заполнить квадратную матрицу автоматически" << endl;
            cout << "Выберите способ: "; cin >> taskChoice;
            cout << endl;

            switch (taskChoice)
            {
            case 1: {
                cout << "Заполните матрицу: " << endl;
                MatrixFillSelf(arr, size);
                cout << endl;
                break;
            }
            case 2: {
                MatrixFillAuto(arr, size);
                break;
            }
            }

            MatrixOutput(arr, size);
            MatrixCircle(arr, size);
        }
        break;
    }

    case 2: {
        int** arr = new int* [size];
        for (int i = 0; i < size; i++)
            arr[i] = new int[size];

        while (true)
        {
            cout << "\n1. Заполнить квадратную матрицу вручную" << endl;
            cout << "2. Заполнить квадратную матрицу автоматически" << endl;
            cout << "Выберите способ: "; cin >> taskChoice;
            cout << endl;

            switch (taskChoice)
            {
            case 1: {
                cout << "Заполните матрицу: " << endl;
                MatrixFillSelf(arr, size);
                cout << endl;
                break;
            }
            case 2: {
                MatrixFillAuto(arr, size);
                break;
            }
            }

            MatrixOutput(arr, size);
            MatrixCircle(arr, size);
        }
        break;
    }

    case 3: {
        while (true)
        {
            int size1, size2;
            while (true)
            {
                cout << "Введите количество точек первого множества: ";
                cin >> size1;
                if (size1 < 3)
                {
                    continue;
                }
                break;
            }
            while (true)
            {
                cout << "Введите количество точек второго множества: ";
                cin >> size2;
                if (size2 < 3)
                {
                    continue;
                }
                break;
            }

            vector<vector<int>> points1;
            vector<vector<int>> points2;
            points1.resize(size1);
            points2.resize(size2);
            int pointsCounter = 0;

            cout << "Введите координаты точек первого вектора: " << endl;
            PointsFilling(points1, size1);
            cout << "Введите координаты точек второго вектора: " << endl;
            PointsFilling(points2, size2);
            
            for (int a = 0; a < points1.size(); a++) {
                for (int b = 0; b < points1.size(); b++) {
                    for (int c = 0; c < points1.size(); c++) {
                        if (points1[a] != points1[b] && points1[b] != points1[c] && points1[a] != points1[c]) {
                            auto triangle = TriangleFill(points1[a], points1[b], points1[c]);
                            int c1 = 0, c2 = 0;
                            for (int i = 0; i < size1; i++) {
                                if (PointCheck(triangle, points1[i][0], points1[i][1])) {
                                    c1++;
                                }
                            }
                            for (int i = 0; i < size2; i++) {
                                if (PointCheck(triangle, points2[i][0], points2[i][1])) {
                                    c2++;
                                }
                            }
                            if (c1 == c2 && c1 != 0 && c2 != 0) {
                                cout << "Подходящий треугольник: " << endl;
                                PrintPoints(triangle);
                                return 0;
                            }

                        }
                    }
                }
            }
            cout << "Не найдено подходящего треугольника." << endl;
        }
        break;
    }

    default:
        system("cls");
    }
}
