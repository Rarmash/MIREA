# Написать функцию remove_duplicate_words, которая оставляет в строке только уникальные слова.
#
# Пример:
# remove_duplicate_words("aaa bbb aaa bbb ccc aaa") ==> "aaa bbb ccc"

import traceback


def remove_duplicate_words(s):
    words = s.split()
    unique_words = []
    for word in words:
        if word not in unique_words:
            unique_words.append(word)
    return " ".join(unique_words)


# Тесты
try:
    assert remove_duplicate_words("alpha beta beta gamma gamma gamma delta alpha beta beta gamma gamma gamma delta") == "alpha beta gamma delta"
    assert remove_duplicate_words("my cat is my cat fat") == "my cat is fat"
except AssertionError:
    print("TEST ERROR")
    traceback.print_exc()
else:
    print("TEST PASSED")