'''
Определить является ли заданное натуральное число простым.
'''

from math import sqrt

def func1(n):
    for i in range(2, int(sqrt(n))+1):
        if n % i == 0:
            return False
    return True

print(func1(int(input())))