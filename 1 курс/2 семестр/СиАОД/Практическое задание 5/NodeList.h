#pragma once
#include <iostream>
using namespace std;

struct Node
{
    int value;
    Node* Next = nullptr;
};

void ListOutput(auto NodeList) {
    if (NodeList->Next != nullptr) {
        cout << NodeList->value << " -> ";
        ListOutput(NodeList->Next);
    }
    else {
        cout << NodeList->value;
    }
}

void AddNewNode(auto& ListRoot, int newValue) {
    if (ListRoot == nullptr) {
        ListRoot = new Node{ newValue };
    }
    else {
        Node* current = ListRoot;
        while (current->Next != nullptr) current = current->Next;
        current->Next = new Node{ newValue };
    }
}

bool FirstTask(auto& A, Node* ListNode) {
    while (ListNode != nullptr) {
        Node* curr = ListNode->Next;
        while (curr != nullptr) {
            if (ListNode->value == curr->value) {
                return true;
            }
            curr = curr->Next;
        }
        ListNode = ListNode->Next;
    }
    return false;
}

void SecondTask(Node*& NodeList) {
    if (NodeList == nullptr) {
        return;
    }
    Node* maxNode = NodeList;
    Node* prevMaxNode = nullptr;
    Node* curr = NodeList->Next;
    while (curr != nullptr) {
        if (curr->value > maxNode->value) {
            maxNode = curr;
            prevMaxNode = NodeList;
        }
        else if (curr->value == maxNode->value) {
            prevMaxNode = maxNode;
        }
        curr = curr->Next;
    }
    if (maxNode == NodeList) {
        NodeList = NodeList->Next;
    }
    else if (prevMaxNode && prevMaxNode->Next != maxNode) {
        prevMaxNode = prevMaxNode->Next;
    }
    if (prevMaxNode) {
        prevMaxNode->Next = maxNode->Next;
    }
    else {
        NodeList = maxNode->Next;
    }
    delete maxNode;
}

void ThirdTask(Node*& L, int newValue) {
    Node* current = L;
    int count = 1;

    while (current != nullptr) {
        if (count % 2 == 0) {
            Node* newNode = new Node;
            newNode->value = newValue;
            newNode->Next = current;
            Node* prev = L;
            while (prev->Next != current) {
                prev = prev->Next;
            }
            prev->Next = newNode;
        }
        current = current->Next;
        count++;
    }
}
