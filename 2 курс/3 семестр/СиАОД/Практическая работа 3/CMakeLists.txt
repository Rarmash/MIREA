cmake_minimum_required(VERSION 3.26)
project(__3)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(__3
        BinaryFile.h
        FileByHash.h
        HashTable.h
        main.cpp)
