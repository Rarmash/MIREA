#include "ExpressionTree.h"

using namespace std;

template<typename DataType>
ExprTree<DataType>::ExprTree() {
    root = NULL; // Initialize the root pointer to NULL
}

template<typename DataType>
ExprTree<DataType>::ExprTree(const ExprTree& source) {
    *this = source;
}

template<typename DataType>
void ExprTree<DataType>::build() {
    buildHelper(root);
}

template<typename DataType>
void ExprTree<DataType>::buildHelper(ExprTreeNode *&node) {
    char c;
    cin >> c;
    node = new ExprTreeNode(c, NULL, NULL);

    if (c == '+' || c == '-' || c == '*' || c == '/') {
        buildHelper(node->left);
        buildHelper(node->right);
    }
}

template<typename DataType>
void ExprTree<DataType>::showStructure() const {
    if (root == NULL) {
        cout << "Empty tree" << endl;
    } else {
        cout << endl;
        showHelper(root, 1);
        cout << endl;
    }
}

template<typename DataType>
void ExprTree<DataType>::showHelper(ExprTree::ExprTreeNode *p, int level) const {
    int j; // Loop counter

    if (p != 0) {
        showHelper(p->right, level + 1); // Output right subtree
        for (j = 0; j < level; j++) // Tab over to level
            cout << "\t";
        cout << " " << p->dataItem; // Output dataItem
        if ((p->left != 0) && // Output "connector"
            (p->right != 0))
            cout << " <";
        else if (p->right != 0)
            cout << "/";
        else if (p->left != 0)
            cout << "\\";
        cout << endl;
        showHelper(p->left, level + 1); // Output left subtree
    }
}

template<typename DataType>
DataType ExprTree<DataType>::evaluate() const {
    return evaluateHelper(root);
}

template<typename DataType>
DataType ExprTree<DataType>::evaluateHelper(ExprTreeNode* node) const {
    if (node == nullptr) {
        // Empty subtree, return some default value (you may want to handle this differently)
        return DataType();
    }

    if (isdigit(node->dataItem)) {
        // Operand, return its value
        return static_cast<DataType>(node->dataItem - '0');
    } else {
        // Operator, perform the operation based on the operator
        DataType leftValue = evaluateHelper(node->left);
        DataType rightValue = evaluateHelper(node->right);

        switch (node->dataItem) {
            case '+':
                return leftValue + rightValue;
            case '-':
                return leftValue - rightValue;
            case '*':
                return leftValue * rightValue;
            case '/':
                // Handle division by zero if necessary
                if (rightValue != 0) {
                    return leftValue / rightValue;
                } else {
                    // Handle division by zero (you may want to handle this differently)
                    cout << "Error: Division by zero" << endl;
                    return DataType(); // Return some default value
                }
            default:
                // Handle unknown operator (you may want to handle this differently)
                cout << "Error: Unknown operator" << endl;
                return DataType(); // Return some default value
        }
    }
}

template<typename DataType>
ExprTree<DataType>::ExprTreeNode::ExprTreeNode(char elem, ExprTree::ExprTreeNode *leftPtr,
                                               ExprTree::ExprTreeNode *rightPtr) {
    dataItem = elem;
    left = leftPtr;
    right = rightPtr;
}