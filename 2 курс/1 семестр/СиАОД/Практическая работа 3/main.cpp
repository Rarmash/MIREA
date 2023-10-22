#include "struct.h"
#include "binaries_and_hashtables.h"

#include <iostream>
#include <string>
#include <chrono>
#include <iomanip>

using namespace std;

int main() {
    string filename;
    string dateOfBirth;
    string name;

    cout << "Enter filename: ";
    cin >> filename;
    itFile(filename);

    while (true) {
        cout << "1. Converting text file to binary file" << endl;
        cout << "2. Converting binary file to text file" << endl;
        cout << "3. Print data from binary file" << endl;
        cout << "4. Insert data to hash table" << endl;
        cout << "5. Find data in hash table by key" << endl;
        cout << "6. Add new line to binary file and hash table" << endl;
        cout << "7. Remove line from binary file and hash table" << endl;
        cout << "0. Exit" << endl;

        cout << "Enter command: ";
        int command;
        cin >> command;

        switch (command) {
        case 1: {
            string binaryFilename = filename.substr(0, filename.find_last_of('.')) + ".dat";
            textFileToBinaryFile(filename, binaryFilename);
            cout << "Text file converted to binary file" << endl;

            break;
        }
        case 2: {
            string binaryFilename = filename.substr(0, filename.find_last_of('.')) + ".dat";
            binaryFileToTextFile(binaryFilename, filename);
            cout << "Binary file converted to text file" << endl;

            break;
        }
        case 3: {
            string binaryFilename = filename.substr(0, filename.find_last_of('.')) + ".dat";
            printBinaryFile(binaryFilename);

            break;
        }
        case 4: {
            string binaryFilename = filename.substr(0, filename.find_last_of('.')) + ".dat";
            insertDataToHashTable(binaryFilename);
            cout << "Data inserted to hash table" << endl;

            break;
        }
        case 5: {
            string binaryFilename = filename.substr(0, filename.find_last_of('.')) + ".dat";
            string key;
            cout << "Enter key: ";
            cin >> key;

            auto start = chrono::high_resolution_clock::now();

            int pos = findPosByKey(key) - 1;

            auto end = chrono::high_resolution_clock::now();
            chrono::duration<double> duration = end - start;

            if (pos >= 0) {
                cout << findLineByPos(binaryFilename, pos) << endl;
                cout << "Time: " << fixed << setprecision(8) << duration.count() << " seconds" << endl;
            }
            else {
                cout << "Not found" << endl;
            }
            cout << endl;
            break;
        }
        case 6: {
            string binaryFilename = filename.substr(0, filename.find_last_of('.')) + ".dat";
            string newLine;
            cout << "Enter new line: ";
            cin >> dateOfBirth >> name;
            newLine = string() + dateOfBirth + " " + name;

            cout << "Your line: " << newLine << endl;

            addNewLine(newLine, binaryFilename);

            break;
        }
        case 7: {
            string binaryFilename = filename.substr(0, filename.find_last_of('.')) + ".dat";
            string key;
            cout << "Enter key: ";
            cin >> key;

            removeKey(key, binaryFilename);
            cout << "Line removed" << endl;

            break;
        }
        case 0: {
            return 0;
        }
        }
    }
}