#include <iostream>
#include <chrono>
using namespace std;
using namespace chrono;

void selection_sort(int arr[], int n, long long &moves, long long &comps) {
    int i, j, min_idx;

    for (i = 0; i < n - 1; i++) {
        min_idx = i;
        for (j = i + 1; j < n; j++) {
            comps++;
            if (arr[j] < arr[min_idx]) {
                min_idx = j;
            }
        }
        if (min_idx != i) {
            swap(arr[i], arr[min_idx]);
            moves += 3;
        }
    }
}

void heapify(int arr[], int n, int i, long long& comps, long long& moves) {
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;

    comps++;
    if (l < n && arr[l] > arr[largest])
        largest = l;

    comps++;
    if (r < n && arr[r] > arr[largest])
        largest = r;

    if (largest != i) {
        moves += 3;
        swap(arr[i], arr[largest]);
        heapify(arr, n, largest, comps, moves);
    }
}

void heapSort(int arr[], int n, long long& comps, long long& moves) {
    for (int i = n / 2 - 1; i >= 0; i--) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        comps++;
        if (l < n && arr[l] > arr[largest])
            largest = l;

        comps++;
        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            moves += 3;
            swap(arr[i], arr[largest]);
            heapify(arr, n, largest, comps, moves);
        }
    }

    for (int i = n - 1; i >= 0; i--) {
        moves += 3;
        swap(arr[0], arr[i]);

        heapify(arr, i, 0, comps, moves);
    }
}



void Merge(int arr[], int left, int medium, int right, long long& comps, long long& moves)
{
    int leftHalfSize = medium - left + 1;
    int rightHalfSize = right - medium;

    int* leftHalf = new int[leftHalfSize];
    int* rightHalf = new int[rightHalfSize];

    for (int i = 0; i < leftHalfSize; i++)
    {
        leftHalf[i] = arr[left + i];
    }

    for (int j = 0; j < rightHalfSize; j++)
    {
        rightHalf[j] = arr[medium + 1 + j];
    }

    int i = 0, j = 0, k = left;
    while (i < leftHalfSize && j < rightHalfSize)
    {
        if (leftHalf[i] <= rightHalf[j])
        {
            arr[k++] = leftHalf[i++];
        }
        else
        {
            arr[k++] = rightHalf[j++];
        }

        moves++;
        comps++;
    }

    while (i < leftHalfSize)
    {
        arr[k++] = leftHalf[i++];
        moves++;
    }

    while (j < rightHalfSize)
    {
        arr[k++] = rightHalf[j++];
        moves++;
    }
}

void MergeSort(int arr[], int left, int right, long long& comps, long long& moves)
{
    if (left < right)
    {
        int medium = left + (right - left) / 2;
        MergeSort(arr, left, medium, comps, moves);
        MergeSort(arr, medium + 1, right, comps, moves);
        Merge(arr, left, medium, right, comps, moves);
    }
}


void Reverse(int arr[], int size)
{
    for (int i = 0; i < size / 2; i++) {
        int temp = arr[i];
        arr[i] = arr[size - i - 1];
        arr[size - i - 1] = temp;
    }
}

void CopyArray(int* arr, int* temp, int size)
{
    for (int i = 0; i < size; i++)
    {
        arr[i] = temp[i];
    }
}


int main()
{
    setlocale(LC_ALL, "rus");

    long long comps = 0, moves = 0;

    for (int size = 60000; size <= 100000; size += 10000)
    {
        int* arr = new int[size];
        int* temp = new int[size];

        for (int j = 0; j < size; j++)
        {
            arr[j] = rand();
            temp[j] = arr[j];
        }

        cout << "Количество элементов: " << size << endl;

        auto start = steady_clock::now();
        selection_sort(arr, size, comps, moves);
        auto end = steady_clock::now();
        auto difference = duration_cast<duration<float>>(end - start).count();
        cout << "Сортировка простого выбора средний случай: " << difference << "; Сравнений (C): " << comps << "; Перестановок (M): " << moves << endl;

        comps = 0, moves = 0;
        start = steady_clock::now();
        selection_sort(arr, size, comps, moves);
        end = steady_clock::now();
        difference = duration_cast<duration<float>>(end - start).count();
        cout << "Сортировка простого выбора лучший случай: " << fixed << difference << "; Сравнений (C): " << comps << "; Перестановок (M): " << moves << endl;

        comps = 0, moves = 0;
        Reverse(arr, size);
        start = steady_clock::now();
        selection_sort(arr, size, comps, moves);
        end = steady_clock::now();
        difference = duration_cast<duration<float>>(end - start).count();
        cout << "Сортировка простого выбора худший случай: " << difference << "; Сравнений (C): " << comps << "; Перестановок (M): " << moves << endl << endl;



        CopyArray(arr, temp, size);
        comps = 0, moves = 0;
        start = steady_clock::now();
        heapSort(arr, size, comps, moves);
        end = steady_clock::now();
        difference = duration_cast<duration<float>>(end - start).count();
        cout << "Пирамидальная сортировка средний случай: " << difference << "; Сравнений (C): " << comps << "; Перестановок (M): " << moves << endl;

        comps = 0, moves = 0;
        start = steady_clock::now();
        heapSort(arr, size, comps, moves);
        end = steady_clock::now();
        difference = duration_cast<duration<float>>(end - start).count();
        cout << "Пирамидальная сортировка лучший случай: " << fixed << difference << "; Сравнений (C): " << comps << "; Перестановок (M): " << moves << endl;

        comps = 0, moves = 0;
        Reverse(arr, size);
        start = steady_clock::now();
        heapSort(arr, size, comps, moves);
        end = steady_clock::now();
        difference = duration_cast<duration<float>>(end - start).count();
        cout << "Пирамидальная сортировка худший случай: " << difference << "; Сравнений (C): " << comps << "; Перестановок (M): " << moves << endl << endl;



        CopyArray(arr, temp, size);
        comps = 0, moves = 0;
        start = steady_clock::now();
        MergeSort(arr, 0, size - 1, comps, moves);
        end = steady_clock::now();
        difference = duration_cast<duration<float>>(end - start).count();
        cout << "Сортировка слиянием средний случай: " << difference << "; Сравнений (C): " << comps << "; Перестановок (M): " << moves << endl;

        comps = 0, moves = 0;
        start = steady_clock::now();
        MergeSort(arr, 0, size - 1, comps, moves);
        end = steady_clock::now();
        difference = duration_cast<duration<float>>(end - start).count();;
        cout << "Сортировка слиянием лучший случай: " << difference << "; Сравнений (C): " << comps << "; Перестановок (M): " << moves << endl;

        comps = 0, moves = 0;
        Reverse(arr, size);
        start = steady_clock::now();
        MergeSort(arr, 0, size - 1, comps, moves);
        end = steady_clock::now();
        difference = duration_cast<duration<float>>(end - start).count();
        cout << "Сортировка слиянием худший случай: " << difference << "; Сравнений (C): " << comps << "; Перестановок (M): " << moves << endl << endl;
        cout << endl;
    }
}
