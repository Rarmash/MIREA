#include "StackList.h"
#include "StackArray.h"
#include <iostream>
using namespace std;

bool isOperator(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/';
}

bool isOperand(char c) {
    return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
}

int precedence(char op) {
    if (op == '+' || op == '-') {
        return 1;
    }
    else if (op == '*' || op == '/') {
        return 2;
    }
    else {
        return 0;
    }
}

char* infixToPostfix(const char* infix) {
    StackList<char> stack;
    int length = 0;
    for (size_t i = 0; infix[i]; i++) {
        char c = infix[i];
        if (isOperand(c)) {
            length++;
        }
        else if (isOperator(c)) {
            length += 2;
            stack.push(c);
        }
        else if (c == '(') {
            stack.push(c);
        }
        else if (c == ')') {
            while (!stack.isEmpty() && stack.top() != '(') {
                length++;
                stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }
    }
    while (!stack.isEmpty()) {
        length++;
        stack.pop();
    }
    char* postfix = new char[length + 1];
    int index = 0;
    for (size_t i = 0; infix[i]; i++) {
        char c = infix[i];
        if (isOperand(c)) {
            postfix[index++] = c;
        }
        else if (isOperator(c)) {
            while (!stack.isEmpty() && stack.top() != '(' && precedence(c) <= precedence(stack.top())) {
                postfix[index++] = stack.top();
                stack.pop();
            }
            stack.push(c);
        }
        else if (c == '(') {
            stack.push(c);
        }
        else if (c == ')') {
            while (!stack.isEmpty() && stack.top() != '(') {
                postfix[index++] = stack.top();
                stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }
    }
    while (!stack.isEmpty()) {
        postfix[index++] = stack.top();
        stack.pop();
    }
    postfix[index] = '\0';
    return postfix;
}

int vibor() {
    int n;
    cout << "Практическое задание 7 по теме Применение стека и очереди при преобразовании и вычислении арифметических выражений. Гришин Андрей.\n";
    cout << "Выберите задание: \n"
        "1. Преобразовать инфиксную форму в постфиксную\n"
        "2. Выполнить операции со стеком (однонаправленный список)\n"
        "3. Выполнить операции со стеком (статический массив)\n";
    cin >> n;
    return n;
}

int main() {
    setlocale(LC_ALL, "RUS");
    switch (vibor()) {
    case 1: {
        cout << "Введите инфиксное выражение без пробелов (разрешённые операции: +, -, *, /)\n";
        char s[100];
        cin >> s;
        cout << "Постфиксная форма:\n" << infixToPostfix(s);
        break;
    }
    case 2: {
        cout << "Стек хранит значения int\n";
        StackList<int> stack;
        int a = -1;
        while (a != 0) {
            cout << "Выберите действие: \n"
                "1. Добавить элемент в стек\n"
                "2. Удалить элемент из стека\n"
                "3. Напечатать верхний элемент\n"
                "4. Очистить стек\n"
                "5. Пуст ли стек\n"
                "0. Выход\n";
            cin >> a;
            switch (a) {
            case 1: {
                int value;
                cout << "Введите элемент\n";
                cin >> value;
                stack.push(value);
                stack.print();
                break;
            }
            case 2: {
                stack.pop();
                stack.print();
                break;
            }
            case 3: {
                if (!stack.isEmpty())
                    cout << "Верхний элемент " << stack.top() << "\n";
                else
                    stack.print();
                break;
            }
            case 4: {
                stack.clear();
                stack.print();
                break;
            }
            case 5: {
                if (stack.isEmpty())
                    cout << "Стек пуст\n";
                else
                    cout << "Стек не пуст\n";
                break;
            }
            }
        }
        break;
    }
    case 3: {
        cout << "Стек хранит значения int (макс. размер = 100)\n";
        StackArray<int, 100> stack;
        int a = -1;
        while (a != 0) {
            cout << "Выберите действие: \n"
                "1. Добавить элемент в стек\n"
                "2. Удалить элемент из стека\n"
                "3. Напечатать верхний элемент\n"
                "4. Очистить стек\n"
                "5. Пуст ли стек\n"
                "0. Выход\n";
            cin >> a;
            switch (a) {
            case 1: {
                int value;
                cout << "Введите элемент\n";
                cin >> value;
                stack.push(value);
                stack.print();
                break;
            }
            case 2: {
                stack.pop();
                stack.print();
                break;
            }
            case 3: {
                if (!stack.isEmpty())
                    cout << "Верхний элемент " << stack.top() << "\n";
                else
                    stack.print();
                break;
            }
            case 4: {
                stack.clear();
                stack.print();
                break;
            }
            case 5: {
                if (stack.isEmpty())
                    cout << "Стек пуст\n";
                else
                    cout << "Стек не пуст\n";
                break;
            }
            }
        }
        break;
    }
    }
}
