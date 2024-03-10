#!/bin/bash

# Хеш-таблица
declare -A duplicats

# Рекурсивная функция для поиска и вывода дубликатов
findDuplicates() 
{
    local dir="$1"
    
    # Перебираем все файлы и подкаталоги в данном каталоге
    for file in "$dir"/*; do
        # Если файл
        if [[ -f "$file" ]]; then
            # оставляем только имя файла
            file=$(basename "$file")
            if [ duplicats["$file"] ]; then
                # Добавляем +1
                duplicats["$file"]=$((duplicats["$file"] + 1))
            else
                duplicats["$file"]=1
            fi

            if [ "${duplicats["$file"]}" -eq 2 ]; then
                echo "Файл-дубликат - '$file'"
            fi
        # Если подкаталог
        elif [[ -d "$file" ]]; then
            # Рекурсивно вызываем данную функцию
            findDuplicates "$file"
        fi
    done
}

findDuplicates "."