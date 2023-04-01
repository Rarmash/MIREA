#include <iostream>
#include <fstream>
#include <cmath>
#include <string>
#include <vector>

using namespace std;
int main()
{
    setlocale(LC_ALL, "Russian");
    string z;
    float x, y, A, H, R;
    //ofstream fin("4.txt");
    //fin << "1jhjkjjhjk2\n33jkhkjh hjkh hjkhkj44\n\n5 ^ &*^66 % $$ gj ^ &^&555\n\n7 8\n7 8\n\n900000!!";
    //fin.close();
    ifstream fout("4.txt");
    string fil, Textfile;
    while (getline(fout, Textfile))
    {
        fil = fil + "|" + Textfile;
    }
    fout.close();
    int siize = fil.size(), i = 0;
    bool fs = true;
    bool Befwasnotdig = false;
    for (i; i < siize; i++)
    {
        if (isdigit(fil[i]))
        {
            cout << fil[i];
            Befwasnotdig = true;
            fs = false;
        }
        else
        {
            if (fil[i] == ' ' && Befwasnotdig && not fs)
            {
                cout << " ";
                Befwasnotdig = false;
                fs = false;
            }
            else
            {
                fs = false;
                if (Befwasnotdig)
                {
                    cout << " ";
                    Befwasnotdig = false;
                }
            }
        }
        if (fil[i] == '|')
        {
            cout << "\n";
        }
    }
}