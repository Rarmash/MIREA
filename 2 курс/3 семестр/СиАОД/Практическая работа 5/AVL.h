#pragma once
#include <iostream>
#include <cstringt.h>
#include <regex>
#include <string>
#include <fstream>
#include "BinaryFile.h"
struct TreeNodeA
{
	char key[11]; // Ключ записи (дата рождения)
	int data; // Номер записи с этим ключом в файле
	int height; // Высота
	TreeNodeA* left, * right; // Указатели на правую и на левую ветки
	TreeNodeA(const char* dateOfBirth, int fileRecordNumber) :
		data(fileRecordNumber), height(1), left(nullptr), right(nullptr) {
		strncpy(key, dateOfBirth, 11);
		key[10] = '\0'; // Убедимся, что строка ключа заканчивается нулевым символом
	} // Конструктор
};

extern int RotationCounter;
struct AVL
{
	TreeNodeA* root = nullptr;
	void deleteTree() {
		// Вызываем вспомогательную функцию для удаления всех узлов
		deleteTreeHelper(root);
		root = nullptr; // Устанавливаем корень в nullptr
	}
	// Рекурсивная функция для удаления узлов дерева
	void deleteTreeHelper(TreeNodeA* node) {
		if (node == nullptr) {
			return;
		}
		// Сначала рекурсивно удаляем левое и правое поддерево
		deleteTreeHelper(node->left);
		deleteTreeHelper(node->right);
		// Затем удаляем текущий узел
		delete node;
	}
};

int RotationCounter = 0;
// Функция получения высот дерева
int getHeightTree(TreeNodeA* node)
{
	if (node == nullptr)
		return 0;
	return node->height;
}
// Функция получения Баланса дерева
int getBalanceFactor(TreeNodeA* node)
{
	if (node == nullptr)
		return 0;
	return getHeightTree(node->left) - getHeightTree(node->right);
}
// Функция обновления высоты
void updateHeight(TreeNodeA* node)
{
	if (node != nullptr)
	{
		node->height = 1 + (max(getHeightTree(node->left), getHeightTree(node -> right)));
	}
}
// Функция поворота дерева направо
TreeNodeA* rightRotateTree(TreeNodeA* node)
{
	TreeNodeA* LeftSubTree = node->left;
	TreeNodeA* RightSubTreeOfLeftSubTree = LeftSubTree->right;
	LeftSubTree->right = node;
	node->left = RightSubTreeOfLeftSubTree;
	updateHeight(node);
	updateHeight(LeftSubTree);
	RotationCounter++;
	return LeftSubTree;
}
// Функция поворота дерева налево
TreeNodeA* leftRotateTree(TreeNodeA* node)
{
	TreeNodeA* RightSubTree = node->right;
	TreeNodeA* LeftSubTreeOfRightSubTree = RightSubTree->left;
	RightSubTree->left = node;
	node->right = LeftSubTreeOfRightSubTree;
	updateHeight(node);
	updateHeight(RightSubTree);
	RotationCounter++;
	return RightSubTree;
}

TreeNodeA* AddToSubAVLTree(TreeNodeA* node, TreeNodeA* newNode);

//Функция добавления узла в АВЛ дерево
void AddToAVLTree(AVL& tree, const char* k, int d)
{
	TreeNodeA* node = new TreeNodeA(k, d);
	tree.root = AddToSubAVLTree(tree.root, node);
}
// Функция добавления узла в АВЛ поддерево
TreeNodeA* AddToSubAVLTree(TreeNodeA* node, TreeNodeA* newnode)
{
	if (node == nullptr)
	{
		return newnode;
	}
	if (newnode->key < node->key)
		node->left = AddToSubAVLTree(node->left, newnode);
	else if (newnode->key > node->key)
		node->right = AddToSubAVLTree(node->right, newnode);
	else
	{
		delete newnode;
		return node;
	}
	updateHeight(node);
	int BalanceFactor = getBalanceFactor(node);
	if (BalanceFactor > 1)
	{
		if (newnode->key < node->left->key)
			return rightRotateTree(node);
		if (newnode->key > node->left->key)
		{
			node->left = leftRotateTree(node->left);
			return rightRotateTree(node);
		}
	}
	if (BalanceFactor < -1)
	{
		if (newnode->key > node->right->key)
			return leftRotateTree(node);
		if (newnode->key < node->right->key)
		{
			node->right = rightRotateTree(node->right);
			return leftRotateTree(node);
		}
	}
	return node;
}
// Функция вывода структурированного АВЛ поддерева(алгоритв КЛП)
void PrintSubAVLTreeStructure(TreeNodeA* node, int depth, bool isRoot = false)
{
	if (node == nullptr) return;
	if (isRoot) {
		printf("%s(%i,%i)\n", node->key, node->data, getBalanceFactor(node));
	}
	else
	{
		for (int i = 0; i < depth; i++)
			printf("| ");
		printf("\b\b\b---%s(%i,%i)\n", node->key, node->data, getBalanceFactor(node));
	}
	PrintSubAVLTreeStructure(node->left, depth + 1);
	PrintSubAVLTreeStructure(node->right, depth + 1);
}
// Функция вывода структурированного АВЛ дерева(алгоритм КЛП)
void PrintAVLTreeStructure(AVL tree)
{
	PrintSubAVLTreeStructure(tree.root, 0, true);
}
// Функция поиска в АВЛ поддереве
int SearchInAVLSubTree(TreeNodeA* node, const char* key) {
	if (node == nullptr)
		return -1;

	int compareResult = strcmp(key, node->key);

	if (compareResult == 0)
		return node->data;
	else if (compareResult < 0)
		return SearchInAVLSubTree(node->left, key);
	else
		return SearchInAVLSubTree(node->right, key);
}

// Функция поиска в АВЛ дереве
int SearchInAVLTree(AVL tree, const char* key)
{
	return SearchInAVLSubTree(tree.root, key);
}
// Функция для поиска замещающего узла
TreeNodeA* SubstituteNodeSearch(TreeNodeA* node) {
	if (node->left == nullptr) {
		return node;
	}
	return SubstituteNodeSearch(node->left);
}

// Функция для удаления узла из АВЛ дерева
TreeNodeA* DeleteNodeFromAVL(TreeNodeA* root, const char* key) {
	if (root == nullptr) return root;
	int compareResult = strcmp(key, root->key);
	if (compareResult < 0) root->left = DeleteNodeFromAVL(root->left, key);
	else if (compareResult > 0) root->right = DeleteNodeFromAVL(root->right, key);
	else {
		if (root->left == nullptr || root->right == nullptr) {
			TreeNodeA* temp;
			if (root->left != nullptr)
				temp = root->left;
			else
				temp = root->right;

			if (temp == nullptr) {
				temp = root;
				root = nullptr;
			}
			else {
				*root = *temp;
			}
			delete temp;
		}
		else {
			TreeNodeA* temp = SubstituteNodeSearch(root->right);
			strncpy(root->key, temp->key, 11);
			root->data = temp->data;
			root->right = DeleteNodeFromAVL(root->right, temp->key);
		}
	}
	if (root == nullptr) return root;
	updateHeight(root);
	int BalanceFactor = getBalanceFactor(root);
	if (BalanceFactor > 1) {
		if (getBalanceFactor(root->left) >= 0) return rightRotateTree(root);
		else {
			root->left = leftRotateTree(root->left);
			return rightRotateTree(root);
		}
	}
	if (BalanceFactor < -1) {
		if (getBalanceFactor(root->right) <= 0) return leftRotateTree(root);
		else {
			root->right = rightRotateTree(root->right);
			return leftRotateTree(root);
		}
	}
	return root;
}


void BuildAVLFromBinFile(AVL& avl, const char* binfile) {
	ifstream bin(binfile, ios::binary);
	if (!bin.is_open()) {
		cerr << "Ошибка при открытии бинарного файла" << endl;
		return;
	}
	FriendRecord record;
	int recordNumber = 0;
	while (bin.read(reinterpret_cast<char*>(&record), sizeof(FriendRecord))) {
		AddToAVLTree(avl, record.dateOfBirth, recordNumber);
	}
	bin.close();
}