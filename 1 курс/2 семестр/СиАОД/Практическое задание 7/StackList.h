#pragma once
#include <iostream>

using namespace std;

template <typename T>
class StackList {
private:
    struct Node {
        T data;
        Node* next;
    };
    Node* topNode;
public:
    StackList() {
        topNode = nullptr;
    }

    ~StackList() {
        while (!isEmpty()) {
            pop();
        }
    }

    void push(T value) {
        Node* newNode = new Node;
        newNode->data = value;
        newNode->next = topNode;
        topNode = newNode;
    }

    void pop() {
        if (!isEmpty()) {
            Node* tempNode = topNode;
            topNode = topNode->next;
            delete tempNode;
        }
    }

    T top() const {
        if (!isEmpty()) {
            return topNode->data;
        }
        return T();
    }

    bool isEmpty() const {
        return topNode == nullptr;
    }

    void clear() {
        while (!isEmpty()) {
            pop();
        }
    }

    void print() const {
        if (isEmpty()) {
            cout << "Стек пуст\n";
        }
        else {
            cout << "Стек (сверху вниз):\n";
            Node* node = topNode;
            while (node != nullptr) {
                cout << node->data;
                if (node->next != nullptr)
                    cout << "\n|\n";
                else
                    cout << "\n";
                node = node->next;
            }
        }
    }
};
