'''
В данном массиве найдите наибольшую серию подряд идущих элементов, расположенных по возрастанию.
'''

list2 = [1, 2, -3, 4, 5, -1, -1, 2, 3, -3, 4]

def func3(list2):
    c = 1
    a = []
    maxa = []
    for i in range(1, len(list2)):
        if list2[i-1] < list2[i]:
            if c == 1:
                a.append(list2[i-1])
            c += 1
            a.append(list2[i])
        else:
            c, maxa, a = 1, a, []
    return f'{maxa}'

print(func3(list2))