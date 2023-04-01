"""
Создать txt-файл, вставить туда любую англоязычную статью из Википедии.
Реализовать одну функцию, которая выполняет следующие операции:
- прочитать файл построчно;
- непустые строки добавить в список;
- удалить из каждой строки все цифры, знаки препинания, скобки, кавычки и т.д. (остаются латинские буквы и пробелы);
- объединить все строки из списка в одну, используя метод join и пробел, как разделитель;
- создать словарь вида {“слово”: количество, “слово”: количество, … } для подсчета количества разных слов,
  где ключом будет уникальное слово, а значением - количество;
- вывести в порядке убывания 10 наиболее популярных слов, используя форматирование
  (вывод примерно следующего вида: “ 1 place --- sun --- 15 times \n....”);
- заменить все эти слова в строке на слово “PYTHON”;
- создать новый txt-файл;
- записать строку в файл, разбивая на строки, при этом на каждой строке записывать не более 100 символов
  при этом не делить слова.
"""


def wiki_function(filename):
    # Считываем файл построчно
    with open(filename, 'r', encoding='utf-8') as file:
        lines = file.readlines()

    # Создаем список слов без знаков препинания и цифр
    words = []
    for line in lines:
        # Убираем символ переноса строки и разбиваем строку на слова
        line_words = line.strip().split()
        # Добавляем слова в список без знаков препинания и цифр
        for word in line_words:
            clean_word = ''.join(filter(lambda x: x.isalpha() or x.isspace(), word))
            if clean_word:
                words.append(clean_word.lower())

    # Создаем словарь для подсчета количества слов
    word_counts = {}
    for word in words:
        if word in word_counts:
            word_counts[word] += 1
        else:
            word_counts[word] = 1

    # Сортируем слова по количеству упоминаний
    sorted_word_counts = sorted(word_counts.items(), key=lambda x: x[1], reverse=True)

    # Заменяем наиболее часто встречающиеся слова на "PYTHON"
    popular_words = [word[0] for word in sorted_word_counts[:10]]
    replaced_text = ' '.join(['PYTHON' if word in popular_words else word for word in words])

    # Записываем результат в новый файл
    with open('output.txt', 'w', encoding='utf-8') as file:
        # Разбиваем строку на строки не длиннее 100 символов
        lines = [replaced_text[i:i+100] for i in range(0, len(replaced_text), 100)]
        file.write('\n'.join(lines))

    # Выводим 10 наиболее часто встречающихся слов
    print('10 наиболее часто встречающихся слов:')
    for i, word_count in enumerate(sorted_word_counts[:10]):
        print(f'{i+1} место --- {word_count[0]} --- {word_count[1]} раз')



# Вызов функции
wiki_function('article.txt')