# Написать функцию order, которая отсортирует заданную строку. Каждое слово в строке
# содержит одну цифру. Эта цифра - позиция, которую слово должно занимать в результате.
#
# Пример:
# order("is2 Thi1s T4est 3a")  ==>  "Thi1s is2 3a T4est"


import traceback


def order(sentence):
    words = sentence.split()
    return ' '.join(sorted(words, key=lambda w: int(''.join(filter(str.isdigit, w)))))

# Тесты
try:
    assert order("is2 Thi1s T4est 3a") == "Thi1s is2 3a T4est"
    assert order("beli7eve Eve1rything if4 jus6t i2s y5ou 3possible") == "Eve1rything i2s 3possible if4 y5ou jus6t beli7eve"
    assert order("") == ""
except AssertionError:
    print("TEST ERROR")
    traceback.print_exc()
else:
    print("TEST PASSED")
