#pragma once
#include "BinaryFile.h"
#include "BST.h"

// Поиск записи в файле с использование БДП
FriendRecord FindWithBST(ifstream& bin, const char* target, BST tree)
{
    int recordNumber = tree.searchNode(tree.root, target)->number; // С помощью БДП находим по ключу порядковый номер записи
    return find_by_key("bin.dat", recordNumber);
}
