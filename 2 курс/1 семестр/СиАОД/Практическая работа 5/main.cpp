#include "BST.h"
#include <sstream>
#include <vector>
#include <iterator>
#include <iostream>
#include <algorithm>
#include <fstream>
#include "BinaryFile.h"
#include "AVL.h"
using namespace std;
extern double counter;

int main()
{
	setlocale(LC_ALL, "rus");
    locale::global(std::locale("en_US.UTF-8"));
    BST bst;
    AVL avl;
    const char* txt_name = "file.txt";
    const char* bin_name = "bin.dat";
    ifstream txt(txt_name, ios::out);
    ofstream bin(bin_name, ios::binary | ios::out | ios::trunc);
    if (!txt)
    {
        cout << "Ошибка при открытии текстового файла\n";
        return -1;
    }
    else if (!bin)
    {
        cout << "Ошибка при открытии бинарного файла\n";
        return -1;
    }
    txt_to_bin(txt, bin);
	while (true)
	{
		cout << "1. Функция для чтения записей из двоичного файла и вставки их в БДП и вывод его" << endl;
        cout << "2. Функция для удаления записи из БДП и из файла" << endl;
        cout << "3. Функция для нахождения записи в файле по ключу с помощью БДП" << endl;
        cout << "4. Вывод БДП" << endl;
        cout << "5. Построение AVL-дерева" << endl;
        int n;
		cin >> n;
        switch (n) {
        case 1: {
            bst.BuildFromeBinFile(bin_name, bst);
            cout << "    ";
            bst.PrintTree(bst.root, 0);
            break;
        }
        case 2: {
            char key[11];
            cout << "Введите ключ для удаления: ";
            cin >> key;
            bst.deleteNode(bst.root, key);
            break;
        }
        case 3: {
            char key[11];
            cout << "Введите ключ для поиска: ";
            cin >> key;
            bst.searchNode(bst.root, key);
            break;
        }
        case 4: {
            bst.PrintTree(bst.root, 0);
            break;
        }
        case 5: {
            BuildAVLFromBinFile(avl, bin_name);
            PrintAVLTreeStructure(avl);
            cout << "Rotation count: " << RotationCounter;
        }
        }
	}
}
