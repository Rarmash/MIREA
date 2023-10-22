#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <unordered_set>
#include <windows.h>
#include "struct.h"
using namespace std;

void itFile(const string& filename)
{
	ifstream file(filename);
	if (!file.good())
	{
		cout << "Error opening file " << filename << endl;
		exit(1);
	}
	else {
		cout << "File " << filename << " opened successfully" << endl;
	}
}


void textFileToBinaryFile(string textFilename, string binaryFilename) {
	ifstream textFile(textFilename);
	ofstream binaryFile(binaryFilename, ios::binary);

	if (!textFile.is_open()) {
		cout << "Error opening file " << textFilename << endl;
		return;
	}

	if (!binaryFile.is_open()) {
		cout << "Error opening file " << binaryFilename << endl;
		return;
	}

	FriendRecord record;
	string line;
	while (getline(textFile, line)) {
		istringstream iss(line);
		iss >> record.dateOfBirth >> record.name;
		binaryFile.write(reinterpret_cast<char*>(&record), sizeof(FriendRecord));
	}
	textFile.close();
	binaryFile.close();
}

void binaryFileToTextFile(string binaryFilename, string textFilename) {
	ifstream binaryFile(binaryFilename, ios::binary);
	ofstream textFile(textFilename);

	if (!binaryFile.is_open()) {
		cout << "Error opening file " << binaryFilename << endl;
		return;
	}

	if (!textFile.is_open()) {
		cout << "Error opening file " << textFilename << endl;
		return;
	}

	FriendRecord record;
	while (binaryFile.read(reinterpret_cast<char*>(&record), sizeof(FriendRecord))) {
		textFile << record.dateOfBirth << " " << record.name << endl;
	}

	binaryFile.close();
	textFile.close();
}

void printBinaryFile(string binaryFilename) {
	ifstream binaryFile(binaryFilename, ios::binary);

	if (!binaryFile.is_open()) {
		cout << "Error opening file " << binaryFilename << endl;
		return;
	}

	FriendRecord record;
	while (binaryFile.read(reinterpret_cast<char*>(&record), sizeof(FriendRecord))) {
		cout << record.dateOfBirth << " " << record.name << endl;
	}
	cout << endl;
	binaryFile.close();
}

void removeRecordByDate(string binaryFilename, string date) {
	fstream binaryFile(binaryFilename, ios::binary | ios::in | ios::out);

	if (!binaryFile.is_open()) {
		cout << "Error opening file " << binaryFilename << endl;
		return;
	}

	FriendRecord record;
	int recordIndex = 0;
	bool recordExists = false;

	while (binaryFile.read(reinterpret_cast<char*>(&record), sizeof(FriendRecord))) {
		recordIndex++;
		if (strcmp(record.dateOfBirth, date.c_str()) == 0) {
			recordExists = true;
			break;
		}
	}

	if (!recordExists) {
		cerr << "Record not found." << endl;
		binaryFile.close();
		return;
	}

	binaryFile.clear();
	binaryFile.seekg(0);
	int currentIndex = 0;
	while (currentIndex < recordIndex - 1) {
		binaryFile.read((char*)&record, sizeof(FriendRecord));
		currentIndex++;
	}
	streampos deletePos = binaryFile.tellg();
	binaryFile.seekg(0, ios::end);
	streampos endPos = binaryFile.tellg();
	binaryFile.seekg(-static_cast<streamoff>(sizeof(FriendRecord)), ios::cur);
	binaryFile.read((char*)&record, sizeof(FriendRecord));
	binaryFile.seekg(deletePos);
	binaryFile.write((char*)&record, sizeof(FriendRecord));
	binaryFile.seekp(endPos - static_cast<streamoff>(sizeof(FriendRecord)));
	FriendRecord emptyrecord;
	memset(emptyrecord.dateOfBirth, 0, sizeof(emptyrecord.dateOfBirth));
	memset(emptyrecord.name, 0, sizeof(emptyrecord.name));
	binaryFile.write((char*)&emptyrecord, sizeof(emptyrecord));
	binaryFile.close();
}