from math import ceil

# Самое популярное решение
'''
def main(m, a, n, b):
    s1 = sum(
        81 * ceil(c) ** 6
        for c in range(1, m+1)
    )
    s2 = sum(
        (61 - i - 4 * k ** 3) ** 4 + c ** 18
        for i in range(1, b+1)
        for c in range(1, n+1)
        for k in range(1, a+1)
    )
    return s1 - s2


"{0:.2e}".format(main(4, 2, 6, 6))
'''
