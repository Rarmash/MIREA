from math import cos

# Самое популярное решение
'''
def main(n):
    values = [-0.42]

    for i in range(1, n + 1):
        values.append(cos(values[i - 1]) ** 3 / 8 + 1 + values[i - 1])

    return values[n]


"{0:.2e}".format(main(4))
"{0:.2e}".format(main(5))
"{0:.2e}".format(main(8))
"{0:.2e}".format(main(3))
"{0:.2e}".format(main(7))
'''

# 2-е по популярности решение
'''
def main(n):
    a = -0.42
    for _ in range(n):
        a = cos(a) ** 3 / 8 + 1 + a
    return a


"{0:.2e}".format(main(4))
"{0:.2e}".format(main(5))
"{0:.2e}".format(main(8))
"{0:.2e}".format(main(3))
"{0:.2e}".format(main(7))
'''

# 3-е по популярности решение
'''
def main(n):
    if n == 0:
        return -0.42
    elif n >= 1:
        return cos(main(n - 1)) ** 3 / 8 + 1 + main(n - 1)


"{0:.2e}".format(main(4))
"{0:.2e}".format(main(5))
"{0:.2e}".format(main(8))
"{0:.2e}".format(main(3))
"{0:.2e}".format(main(7))
'''

# 5-е по популярности решение
'''
def main(n):
    return (
        -0.42 if n == 0 else
        (lambda x: (cos(x[0]) ** 3 / 8 + 1 + x[0]))([main(n - 1)])
    )


"{0:.2e}".format(main(4))
"{0:.2e}".format(main(5))
"{0:.2e}".format(main(8))
"{0:.2e}".format(main(3))
"{0:.2e}".format(main(7))
'''
