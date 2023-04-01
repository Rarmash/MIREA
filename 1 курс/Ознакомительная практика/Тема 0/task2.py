'''
В одномерном списке, состоящем из целых чисел, вычислите сумму элементов списка, 
расположенных между первым и последним отрицательными
'''

list1 = [1, 2, -3, 4, 5, -1, -1, 2, 3, -3, 4]

def func2(list1):
    x1, x2 = None, None
    for i in range(1, len(list1)//2+1):
        if list1[i - 1] < 0 and x1 is None:
            x1 = i - 1
        if list1[-1-i] < 0 and x2 is None:
            x2 = i
    return sum(list1[x1+1:-1-x2])

print(func2(list1))