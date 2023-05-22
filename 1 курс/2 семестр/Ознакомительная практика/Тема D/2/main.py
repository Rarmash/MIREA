from math import *

def func1(k, d, z, x):
    a = log(k*x) + ((1-0.5*k) / (4*sqrt(abs(d*z**3-2)))) - 0.025 + d * log(x, 3)
    return a

def func2 (x, y):
    y = (sin(x+28)**(1/3) + cos(x))/(pi*x + 4.15 * y**4 * exp(abs(x-y)))
    return y

print(func1(1, 1, 1, 1))
print(func2(5, 5))