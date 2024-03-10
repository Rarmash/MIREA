#!/bin/bash

text=$*
length=${#text}

# Формирование символов рамки от 1 до длины слова + 2 пробела
for _ in $(seq 1 $((length + 2))); do
    line+="-"
done

echo "+${line}+"
echo "| ${text} |"
echo "+${line}+"