#ifndef __3_HASH_TABLES_H
#define __3_HASH_TABLES_H

#include <string>
#include <vector>
using namespace std;

class HashTableItem {
public:
    string key;
    int pos;
    bool isOccupied;

    HashTableItem(const string& k, int p, bool occupied = false);
};

class HashTable {
private:
    int TABLE_SIZE;

    int hash(const string& key);
public:
    vector<HashTableItem> table;
    HashTable();
    void insert(const string& key, int pos);
    int find(const string& key);
    void remove(const string& key);
};

#endif //__3_HASH_TABLES_H
