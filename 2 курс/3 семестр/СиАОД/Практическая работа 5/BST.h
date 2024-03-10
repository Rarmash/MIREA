#pragma once
#include <iostream>
#include "BinaryFile.h"
using namespace std;

struct TreeNode
{
	char key[11]; // Ключ
	int number; // Ссылка на запись в файле (порядковый номер записи)
	TreeNode* left; // Указатели на левую ветку
	TreeNode* right; // Указатели на правую ветку
};


struct BST
{
	TreeNode* root = nullptr;

	// Функция создания узла
	TreeNode* createNode(const char* new_key, int new_number)
	{
		TreeNode* node = new TreeNode;
		strncpy(node->key, new_key, sizeof(node->key) - 1);
		node->key[sizeof(node->key) - 1] = '\0';
		node->number = new_number;
		node->left = nullptr;
		node->right = nullptr;
		return node;
	}

	// Функция добавления узла к дереву
	TreeNode* AddToSubTree(TreeNode* root_node, TreeNode* newNode)
	{
		if (newNode == nullptr)
		{
			return root_node;
		}

		if (root_node == nullptr)
		{
			return createNode(newNode->key, newNode->number);
		}

		int compareResult = strcmp(newNode->key, root_node->key);

		if (compareResult < 0)
		{
			root_node->left = AddToSubTree(root_node->left, newNode);
		}
		else if (compareResult > 0)
		{
			root_node->right = AddToSubTree(root_node->right, newNode);
		}

		return root_node;
	}

	//Функция добавления поддерева к дереву
	void AddSubTreeToTree(BST& tree, TreeNode* subtreeRoot)
	{
		if (subtreeRoot == nullptr) {
			return;
		}

		tree.root = AddToSubTree(tree.root, subtreeRoot);
	}

	// Функция поиска узла по ключу
	TreeNode* searchNode(TreeNode* root, const char* target)
	{
		if (root == nullptr || strcmp(root->key, target) == 0)
		{
			return root;
		}
		else if (strcmp(target, root->key) < 0)
		{
			return searchNode(root->left, target);
		}
		else if (strcmp(target, root->key) > 0)
		{
			return searchNode(root->right, target);
		}
		return nullptr;
	}



	// Функция удаления узла по ключу
	TreeNode* deleteNode(TreeNode* root, const char* target)
	{
		if (root == nullptr)
		{
			return root;
		}

		int compareResult = strcmp(target, root->key);

		if (compareResult < 0)
		{
			root->left = deleteNode(root->left, target);
		}
		else if (compareResult > 0)
		{
			root->right = deleteNode(root->right, target);
		}
		else
		{
			// Случай 1: Узел без потомков или с одним потомком
			if (root->left == nullptr) {
				TreeNode* temp = root->right;
				delete root;
				return temp;
			}
			else if (root->right == nullptr) {
				TreeNode* temp = root->left;
				delete root;
				return temp;
			}

			// Случай 2: Узел с двумя потомками
			// Найти наименьший элемент в правом поддереве (минимум в правом поддереве)
			TreeNode* temp = findMin(root->right);

			// Скопировать значение минимального элемента в текущий узел
			strcpy(root->key, temp->key);

			// Удалить минимальный элемент из правого поддерева
			root->right = deleteNode(root->right, temp->key);
		}
		return root;
	}

	// Вспомогательная функция поиска наименьшего элемента в поддереве
	TreeNode* findMin(TreeNode* node)
	{
		while (node->left != nullptr)
		{
			node = node->left;
		}
		return node;
	}

	// Функция вывода дерева на экран
	void PrintTree(TreeNode* node, int level)
	{
		if (node)
		{
			PrintTree(node->left, level + 1);
			for (int i = 0; i < level; i++)
			{
				cout << "       ";
			}
			cout << node->key << endl;
			PrintTree(node->right, level + 1);
		}
	}

	// Функция построения дерева из записей двоичного файла
	void BuildFromeBinFile(const char* binfile, BST& tree)
	{
		ifstream bin(binfile, ios::binary);
		if (bin.is_open())
		{
			FriendRecord record;
			int recordNumber = 0;
			while (bin.read(reinterpret_cast<char*>(&record), sizeof(FriendRecord)))
			{
				TreeNode* newNode = tree.createNode(record.dateOfBirth, recordNumber);
				tree.AddSubTreeToTree(tree, newNode);
				recordNumber++;
			}
			bin.close();
		}
		else
		{
			cerr << "Ошибка в открытии двоичного файла\n";
		}
	}
};

