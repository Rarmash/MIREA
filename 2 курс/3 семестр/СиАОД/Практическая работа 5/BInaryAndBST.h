#pragma once
#include "BST.h"
#include "BinaryFile.h"

void ReadRecordsFromFileToBST(BST& tree, string filename) {
    FriendRecord record;
    ifstream file(filename, ios::binary);
    if (!file.is_open()) {
        cout << "Ошибка открытия бинарного файла" << endl;
        return;
    }
    int i = 0;
    while (file.read(reinterpret_cast<char*>(&record), sizeof(FriendRecord))) {
        AddToTree(tree, record.dateOfBirth, i * sizeof(FriendRecord));
        i++;
    }
    PrintTree(tree);
}

void deleteRecordFromTreeAndFile(BST& tree, const char* filename, const char* key) {
    DeleteNodeFromTree(tree.root, key);
    delete_by_key(filename, key);
}