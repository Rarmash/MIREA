#ifndef __3_STRUCT_H
#define __3_STRUCT_H

#include "hash_tables.h"

using namespace std;

struct FriendRecord {
    char dateOfBirth[11];
    char name[50];
};

void itFile(const std::string& filename);
void textFileToBinaryFile(string textFilename, string binaryFilename);
void binaryFileToTextFile(string binaryFilename, string textFilename);
void printBinaryFile(string binaryFilename);
void removeRecordByDate(string binaryFilename, string date);

#endif //__3_STRUCT_H
