# Задан список целых чисел arr и положительное число k.
# Написать функцию kth_smallest, которая возвращает k-ое число в порядке возрастания
#
# Пример:
# kth_smallest([4,6,3,8,5], 2) ==> 4


import traceback


def kth_smallest(arr, k):
    arr.sort()
    return arr[k-1]


# Тесты
try:
    assert kth_smallest([15,20,7,10,4,3],3) == 7
    assert kth_smallest([-102,-16,-1,-2,-367,-9],5) == -2
    assert kth_smallest([2,169,13,-5,0,-1],4) == 2
except AssertionError:
    print("TEST ERROR")
    traceback.print_exc()
else:
    print("TEST PASSED")