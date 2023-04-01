#include <iostream>
#include <fstream>
#include <cmath>
#include <string>
#include <vector>

using namespace std;
int main()
{
    setlocale(LC_ALL, "Russian");
    ofstream fin("5.txt");
    string stringg;
    cin >> stringg;
    fin << stringg;
    fin.close();
    ifstream fout("5.txt");
    string fil, Textfile;
    while (getline(fout, Textfile))
    {
        fil = Textfile;
    }
    fout.close();
    int size = 30, i = 0;
    size = fil.size();
    string strBefcurr;
    char a[30];
    char temp;
    for (int i = 0; i < size; i++)
    {
        a[i] = fil[i];
    }
    for (int i = 0; i < size - 1; i++)
    {
        for (int j = 0; j < size - i - 1; j++)
        {
            if (tolower((int)a[j]) > tolower((int)a[j + 1]))
            {
                temp = a[j];
                a[j] = a[j + 1];
                a[j + 1] = temp;
            }
            else
            {
                continue;
            }
        }
    }
    for (int i = 0; i < size; i++)
    {
        cout << a[i];
    }
}