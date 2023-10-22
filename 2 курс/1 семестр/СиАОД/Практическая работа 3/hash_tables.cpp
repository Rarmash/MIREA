#include "hash_tables.h"
using namespace std;

HashTableItem::HashTableItem(const string& k, int p, bool occupied) : key(k), pos(p), isOccupied(occupied) {}

HashTable::HashTable() {
    TABLE_SIZE = 1000;
    table.resize(TABLE_SIZE, HashTableItem("", -1, false));
}

int HashTable::hash(const string& key) {
    unsigned int sum = 0;
    for (char c : key) {
        sum += static_cast<int>(c);
    }
    return sum % TABLE_SIZE;
}

void HashTable::insert(const string& key, int pos) {
    int index = hash(key);
    int originalIndex = index;

    while (table[index].isOccupied) {
        index = (index + 1) % TABLE_SIZE;
        if (index == originalIndex) {
            return;
        }
    }

    table[index].key = key;
    table[index].pos = pos;
    table[index].isOccupied = true;
}

int HashTable::find(const string& key) {
    int index = hash(key);
    int originalIndex = index;

    while (table[index].isOccupied) {
        if (table[index].key == key) {
            return table[index].pos;
        }
        index = (index + 1) % TABLE_SIZE;
        if (index == originalIndex) {
            break;
        }
    }
    return -1;
}

void HashTable::remove(const string& key) {
    int index = find(key);
    if (index != -1) {
        table[index].isOccupied = false;
    }
}
