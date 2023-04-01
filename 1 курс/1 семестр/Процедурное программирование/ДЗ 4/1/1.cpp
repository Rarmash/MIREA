#include <iostream>
#include <fstream>


using namespace std;

int main()
{
    setlocale(LC_ALL, "RUS");
    ofstream fout;
    fout.open("chisla.txt");
    double l;
    double p = 0;
    cout << "Введите 10 чисел, сумму которых нужно найти. По очереди. \n\n";
    for (int i = 0; i < 10; i++)
    {
        cin >> l;
        fout << l << endl;
    }
    fout.close();
    double str;
    ifstream fin;
    fin.open("chisla.txt");
    while (fin >> str)
    {
        p += str;
    }
    fin.close();
    cout << "Сумма равна: " << p << endl;
}