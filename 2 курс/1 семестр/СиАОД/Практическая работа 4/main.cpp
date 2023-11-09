#include <iostream>
#include "ExpressionTree.cpp"

using namespace std;

int main() {
    cout << "Binary tree test" << endl;
    while (true) {
        ExprTree<float> testExprTree;

        cout << "Enter an expression in prefix form : ";

        testExprTree.build();
        testExprTree.showStructure();
    }
    return 0;
}