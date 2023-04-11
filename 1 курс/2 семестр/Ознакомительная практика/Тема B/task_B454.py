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
from operator import itemgetter

def wiki_function():
    linesList = []
    string = ""

    for iter in range(1, 8):
        match iter:
            case 1:
                with open("article.txt", 'r') as f:
                    for line in f:
                        linesList.append(line)

            # Удаление всех символы, кроме букв и пробелов
            case 2:
                temp = ""
                for i in range(len(linesList)):
                    for x in linesList[i]:
                        if x.isalpha() or x == ' ':
                            temp += x
                    linesList[i] = temp
                    temp = ""
                    
            case 3:
                string = " ".join(linesList) + " "

            # Создание словаря, где ключ - слово, а значение - количество раз, которое это слово встречается в тексте.
            case 4:
                dict = {}
                word = ""
                for i in range(len(string)):
                    if string[i] != " ":
                        word += string[i]
                    else:
                        if word in dict:
                            dict[word] += 1
                        else:
                            if word != "":
                                dict[word] = 1
                        word = ""

            # Сортировка словаря и вывод топ-10 самый частых слов
            case 5:
                linesList = []
                a = dict.items()
                a = sorted(a, key=itemgetter(1), reverse=True)
                wordCount = 0

                for i in a:
                    wordCount += 1
                    linesList.append(i[0])
                    print(f'{wordCount} место: {i[0]} - встречается {i[1]} раз')
                    if wordCount == 10:
                        break

            case 6:
                # Замена слов из словаря на PYTHON
                newStr = ""
                word = ""
                for i in range(len(string)):
                    if string[i] != " ":
                        word += string[i]
                    else:
                        if word in linesList:
                            newStr += "PYTHON "
                        else:
                            newStr += word
                            newStr += " "
                        word = ""
                string = newStr
                
                # Запись строк в файл output.txt
                # При этом перенося слова на новые строки
            case 7:
                with open("output.txt", 'w') as file:
                    words = string.split()
                    line = ''
                    for word in words:
                        if len(line + word) + 1 > 100:
                            file.write(line.strip() + '\n')
                            line = ''
                        line += word + ' '
                    file.write(line.strip())


wiki_function()