#pragma once
#include <iostream>

using namespace std;

template <typename T, int capacity>
class StackArray {
private:
    T data[capacity];
    int topIndex;
public:
    StackArray() {
        topIndex = -1;
    }

    ~StackArray() = default;

    void push(T value) {
        if (topIndex < capacity - 1) {
            topIndex++;
            data[topIndex] = value;
        }
        else {
            cout << "Ошибка: переполнение стека\n";
        }
    }

    void pop() {
        if (topIndex >= 0) {
            topIndex--;
        }
    }

    T top() const {
        if (topIndex >= 0) {
            return data[topIndex];
        }
        return T();
    }

    bool isEmpty() const {
        return topIndex == -1;
    }

    void clear() {
        topIndex = -1;
    }

    void print() const {
        if (isEmpty())
            cout << "Стек пуст\n";
        else {
            cout << "Стек (сверху вниз):\n";
            for (size_t i = topIndex; i > 0; --i) {
                cout << data[i] << "\n|\n";
            }
            cout << data[0] << "\n";
        }
    }
};
