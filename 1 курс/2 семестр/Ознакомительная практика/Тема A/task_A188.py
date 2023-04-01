# Напишите функцию sum_cube(n, m), которая будет вычислять сумму кубов чисел в заданном диапазоне, 
# начиная с меньшего (но не включая его) до большего (включая). Первый аргумент не обязательно должен быть большим числом.
# Если оба числа совпадают, тогда диапазон пуст и результат должен быть 0. 
#
# Примеры:
# sum_cube(2,3) => 27 -> 3^3 = 27
# sum_cube(3,2) => 27 -> 3^3 = 27
# sum_cube(0,4) => 100 -> 1^3+2^3+3^3+4^3 = 100
# sum_cube(17, 14) => 12384 -> 15^3+16^3+17^3 = 12384


import traceback

def sum_cube(n, m):
    if n > m:
        m, n = n, m
    s = 0
    for i in range(n+1, m+1):
        s += i**3
    return s

# Тесты
try:
    assert sum_cube(2,3) == 27
    assert sum_cube(3,2) == 27
    assert sum_cube(0,4) == 100
    assert sum_cube(17, 14) == 12384
    assert sum_cube(9, 9) == 0
    assert sum_cube(5, 0) == 225
except AssertionError:
    print("TEST ERROR")
    traceback.print_exc()
else:
    print("TEST PASSED")