#ifndef __4_EXPRESSIONTREE_H
#define __4_EXPRESSIONTREE_H

#include <stdexcept>
#include <iostream>

template <typename DataType>
class ExprTree {
public:
    // Default constructor
    ExprTree();

    // Copy constructor
    ExprTree(const ExprTree& source);

    // Building the tree
    void build();

    // Calculating the value of an expression
    DataType evaluate() const;

    // Displaying the tree structure
    void showStructure() const;

    // Get the root of the tree
    class ExprTreeNode;
    // Expression tree node definition
    class ExprTreeNode {
    public:
        // Node constructor
        ExprTreeNode(char elem, ExprTreeNode* leftPtr, ExprTreeNode* rightPtr);

        char dataItem; // Expression tree data item
        ExprTreeNode* left; // Pointer to the left child
        ExprTreeNode* right; // Pointer to the right child
    };

    // Calculating the value of an expression (helper function)
    DataType evaluateHelper(ExprTreeNode* node) const;

    ExprTreeNode* getRoot() const {
        return root;
    }

private:
    // Displaying the tree structure (helper function)
    void showHelper(ExprTreeNode* p, int level) const;

    // Building the tree (helper function)
    void buildHelper(ExprTreeNode*& node);

    // Pointer to the root of the tree
    ExprTreeNode* root;
};

#endif //__4_EXPRESSIONTREE_H
