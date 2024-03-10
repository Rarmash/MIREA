#!/bin/bash

for file in *.c *.js *.py; do
    # Чтение первой строки с начала
    line=$(head -n 1 "$file")
    if [[ $line == "#"* || $line == "//"* || $line == "/*"* ]]; then
        echo "Файл $file начинается с комментария"
    else
        echo "Файл $file не начинается с комментария"
    fi
done