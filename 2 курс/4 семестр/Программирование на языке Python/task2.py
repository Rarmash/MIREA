from math import acos

# Самое популярное решение
'''
def main(x):
    if x < 7:
        return 81 * acos(x) ** 4 - 45 * abs(x) ** 2 - 97
    elif 7 <= x < 26:
        return 21 * (61 * x ** 3) ** 5
    elif 26 <= x < 60:
        return 30 * x ** 7 + 30 * x ** 4
    elif 60 <= x < 124:
        return (x ** 2 / 17) ** 5
    elif x >= 124:
        return x ** 4


"{0:.2e}".format(main(70))
'''


# 2-е по популярности решение (Когнитивная сложность кода превышена (11 > 10))
'''
def main(x):
    return (
        81 * acos(x) ** 4 - 45 * abs(x) ** 2 - 97 if x < 7 else
        21 * (61 * x ** 3) ** 5 if x in range(7, 25) else
        30 * x ** 7 + 30 * x ** 4 if x in range(26, 59) else
        (x ** 2 / 17) ** 5 if x in range(60, 123) else
        x ** 4
    )


"{0:.2e}".format(main(70))
'''

# 3-е по популярности решение
'''
def main(z):
    conditions = {
        z < 7: lambda: 81 * acos(z) ** 4 - 45 * abs(z) ** 2 - 97,
        7 <= z < 26: lambda: 21 * (61 * z ** 3) ** 5,
        26 <= z < 60:
            lambda:
            30 * z ** 7 + 30 * z ** 4,
        60 <= z < 124: lambda: (z ** 2 / 17) ** 5,
        z >= 124: lambda: z ** 4
    }
    for condition, expression in conditions.items():
        if condition:
            return expression()


"{0:.2e}".format(main(70))
'''
