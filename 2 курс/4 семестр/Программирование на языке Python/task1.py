from math import sqrt, atan

# Самое популярное решение
'''
def main(z):
    return sqrt(47 * z - (abs(z ** 2 - z - z ** 3)) ** 4) +\
        (50 * (z - 97 * z ** 3 - 62) ** 4 + atan(z)) / (z ** 5)


"{0:.2e}".format(main(0.1))
'''

# 3-е по популярности решение
'''
def main(z):
    return pow(sqrt(47 * z - (abs(pow(z, 2) - z - pow(z, 3)))), 4) +\
        (50 * pow((z - 97 * pow(z, 3) - 62), 4) + atan(z)) / (pow(z, 5))


"{0:.2e}".format(main(0.1))
'''
