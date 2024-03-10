#pragma once
#include <iostream>
#include "BinaryFile.h"
using namespace std;

struct TreeNode
{
	char key[11]; // ����
	int number; // ������ �� ������ � ����� (���������� ����� ������)
	TreeNode* left; // ��������� �� ����� �����
	TreeNode* right; // ��������� �� ������ �����
};


struct BST
{
	TreeNode* root = nullptr;

	// ������� �������� ����
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

	// ������� ���������� ���� � ������
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

	//������� ���������� ��������� � ������
	void AddSubTreeToTree(BST& tree, TreeNode* subtreeRoot)
	{
		if (subtreeRoot == nullptr) {
			return;
		}

		tree.root = AddToSubTree(tree.root, subtreeRoot);
	}

	// ������� ������ ���� �� �����
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



	// ������� �������� ���� �� �����
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
			// ������ 1: ���� ��� �������� ��� � ����� ��������
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

			// ������ 2: ���� � ����� ���������
			// ����� ���������� ������� � ������ ��������� (������� � ������ ���������)
			TreeNode* temp = findMin(root->right);

			// ����������� �������� ������������ �������� � ������� ����
			strcpy(root->key, temp->key);

			// ������� ����������� ������� �� ������� ���������
			root->right = deleteNode(root->right, temp->key);
		}
		return root;
	}

	// ��������������� ������� ������ ����������� �������� � ���������
	TreeNode* findMin(TreeNode* node)
	{
		while (node->left != nullptr)
		{
			node = node->left;
		}
		return node;
	}

	// ������� ������ ������ �� �����
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

	// ������� ���������� ������ �� ������� ��������� �����
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
			cerr << "������ � �������� ��������� �����\n";
		}
	}
};

