from math import sqrt, ceil


# Самое популярное решение
def main(z, y, x):
    n = len(z)
    z.insert(0, 0)
    y.insert(0, 0)
    x.insert(0, 0)
    summa = 0
    for i in range(1, n + 1):
        step1 = y[n + 1 - ceil(i / 4)]
        step2 = z[n + 1 - i] ** 2 / 26
        step3 = 8 * x[n + 1 - i] ** 3
        summa += sqrt(step1 + step2 + step3) ** 7 / 65
    return 16 * summa
