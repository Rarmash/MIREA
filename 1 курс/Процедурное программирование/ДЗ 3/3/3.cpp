#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main()
{
    ofstream File("3.txt");
    File << "Hello world!";
    File.close();
    string TextFile;
    ifstream NewFile("3.txt");
    while (getline(NewFile, TextFile)) {
        cout << TextFile;
    }
    NewFile.close();
}