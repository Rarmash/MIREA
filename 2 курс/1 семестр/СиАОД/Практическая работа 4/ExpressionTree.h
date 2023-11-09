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

    // Arithmetic expression output
    void expression() const;

    // Calculating the value of an expression
    DataType evaluate() const;

    // Exchanging child operator nodes
    void commute();

    // Checking the equivalence of two trees
    bool isEquivalent(const ExprTree& source) const;

    // Displaying the tree structure
    void showStructure() const;

private:
    // Expression tree node definition
    class ExprTreeNode {
    public:
        // Node constructor
        ExprTreeNode(char elem, ExprTreeNode* leftPtr, ExprTreeNode* rightPtr);

        char dataItem; // Expression tree data item
        ExprTreeNode* left; // Pointer to the left child
        ExprTreeNode* right; // Pointer to the right child
    };

    // Displaying the tree structure (helper function)
    void showHelper(ExprTreeNode* p, int level) const;

    // Building the tree (helper function)
    void buildHelper(ExprTreeNode*& node);

    // Pointer to the root of the tree
    ExprTreeNode* root;
};


#endif //__4_EXPRESSIONTREE_H
