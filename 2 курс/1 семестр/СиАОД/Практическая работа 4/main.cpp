#include <iostream>
#include "ExpressionTree.cpp"

using namespace std;

int main() {
    while (true) {
        ExprTree<float> testExprTree;

        cout << "Enter an expression in prefix form: ";

        testExprTree.build();
        testExprTree.showStructure();

        cout << "Value in the left subtree: " << testExprTree.evaluateHelper(testExprTree.getRoot()->left) << endl;
        cout << "Value in the right subtree: " << testExprTree.evaluateHelper(testExprTree.getRoot()->right) << endl;
        cout << "Root of the tree: " << testExprTree.getRoot()->dataItem << endl;
        cout << "Value of the expression: " << testExprTree.evaluate() << endl;
    }
}