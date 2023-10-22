#ifndef __3_BINARIES_AND_HASHTABLES_H
#define __3_BINARIES_AND_HASHTABLES_H

#include <iostream>
#include <vector>
#include <list>
#include <string>
#include <fstream>
#include "struct.h"
#include "hash_tables.h"
using namespace std;

void insertDataToHashTable(string binaryFilename);
int findPosByKey(string key);
string findLineByPos(string binaryFilename, int pos);
void addNewLine(string newLine, string binaryFilename);
void removeKey(string key, string binaryFilename);


#endif //__3_BINARIES_AND_HASHTABLES_H
