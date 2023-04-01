# Написать функцию highest_rank(arr), которая возвращает самое часто встречающееся число в списке,
# если таких чисел несколько - вернуть наибольшее.
#
# Пример:
# highest_rank([1,0,1,0,1,0,1) => 1


import traceback


def highest_rank(arr):
    c = 0
    num = arr[0]
    for i in arr:
        curr_freq = arr.count(i)
        if curr_freq > c:
            c = curr_freq
            num = i
        elif curr_freq == c:
            c = curr_freq
            if i > num:
                num = i
    return num

# Тесты
try:
    assert highest_rank([12, 10, 8, 12, 7, 6, 4, 10, 12]) == 12
    assert highest_rank([2, 10, 8, 2, 7, 6, 4, 10, 2, 10]) == 10
    assert highest_rank([12, 10, 8, 8, 3, 3, 3, 3, 2, 4, 10, 12, 10]) == 3
    assert highest_rank([1, 2, -3, 1, 2, -3, 1, 2, -3, 1, 2, -3]) == 2
except AssertionError:
    print("TEST ERROR")
    traceback.print_exc()
else:
    print("TEST PASSED")