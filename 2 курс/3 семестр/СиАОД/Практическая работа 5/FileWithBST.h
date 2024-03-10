#pragma once
#include "BinaryFile.h"
#include "BST.h"

// ����� ������ � ����� � ������������� ���
FriendRecord FindWithBST(ifstream& bin, const char* target, BST tree)
{
    int recordNumber = tree.searchNode(tree.root, target)->number; // � ������� ��� ������� �� ����� ���������� ����� ������
    return find_by_key("bin.dat", recordNumber);
}
