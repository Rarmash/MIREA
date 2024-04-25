# Самое популярное решение
# ----------------------------------
# def zero(items, left, right):
#     if items[0] == 1991:
#         return left
#     if items[0] == 1980:
#         return right
#
#
# def one(items, left, midd, right):
#     if items[1] == "TEX":
#         return left
#     if items[1] == "LASSO":
#         return midd
#     if items[1] == "NIM":
#         return right
#
#
# def two(items, left, right):
#     if items[2] == 1963:
#         return left
#     if items[2] == 1992:
#         return right
#
#
# def three(items, left, right):
#     if items[3] == "HYPHY":
#         return left
#     if items[3] == "M4":
#         return right
#
#
# def four(items, left, midd, right):
#     if items[4] == "CLICK":
#         return left
#     if items[4] == "NIX":
#         return midd
#     if items[4] == "HTML":
#         return right
#
#
# def main(items):
#     result = zero(
#         items,
#         four(
#             items,
#             two(
#                 items,
#                 three(
#                     items,
#                     0,
#                     1
#                 ),
#                 2
#             ),
#             3,
#             4
#         ),
#         four(
#             items,
#             5,
#             three(
#                 items,
#                 two(
#                     items,
#                     6,
#                     7
#                 ),
#                 one(
#                     items,
#                     8,
#                     9,
#                     10
#                 )
#             ),
#             11
#         )
#     )
#     return result

# Второе по популярности решение
# -------------------------------
# s = (
#     {1991, 'CLICK', 1963, 'HYPHY'},
#     {1991, 'CLICK', 1963, 'M4'},
#     {1991, 'CLICK', 1992},
#     {1991, 'NIX'},
#     {1991, 'HTML'},
#     {1980, 'CLICK'},
#     {1980, 'NIX', 'HYPHY', 1963},
#     {1980, 'NIX', 'HYPHY', 1992},
#     {1980, 'NIX', 'M4', 'TEX'},
#     {1980, 'NIX', 'M4', 'LASSO'},
#     {1980, 'NIX', 'M4', 'NIM'},
#     {1980, 'HTML'}
# )
#
#
# def main(r):
#     s1 = set(r)
#     return [i for i in range(len(s)) if not (len(s[i] - s1))][0]

# Третье по популярности
# -----------------------
# def zero(x, left, right):
#     match x[0]:
#         case 1991:
#             return left
#         case 1980:
#             return right
#
#
# def one(x, left, midd, right):
#     match x[1]:
#         case "TEX":
#             return left
#         case "LASSO":
#             return midd
#         case "NIM":
#             return right
#
#
# def two(x, left, right):
#     match x[2]:
#         case 1963:
#             return left
#         case 1992:
#             return right
#
#
# def three(x, left, right):
#     match x[3]:
#         case "HYPHY":
#             return left
#         case "M4":
#             return right
#
#
# def four(x, left, midd, right):
#     match x[4]:
#         case "CLICK":
#             return left
#         case "NIX":
#             return midd
#         case "HTML":
#             return right
#
#
# def main(x):
#     result = zero(
#         x,
#         four(
#             x,
#             two(
#                 x,
#                 three(
#                     x,
#                     0,
#                     1
#                 ),
#                 2
#             ),
#             3,
#             4
#         ),
#         four(
#             x,
#             5,
#             three(
#                 x,
#                 two(
#                     x,
#                     6,
#                     7
#                 ),
#                 one(
#                     x,
#                     8,
#                     9,
#                     10
#                 )
#             ),
#             11
#         )
#     )
#     return result

# Четвёртое по популярности
# --------------------------
# s = [
#     {1991, 'CLICK', 1963, 'HYPHY'},
#     {1991, 'CLICK', 1963, 'M4'},
#     {1991, 'CLICK', 1992},
#     {1991, 'NIX'},
#     {1991, 'HTML'},
#     {1980, 'CLICK'},
#     {1980, 'NIX', 'HYPHY', 1963},
#     {1980, 'NIX', 'HYPHY', 1992},
#     {1980, 'NIX', 'M4', 'TEX'},
#     {1980, 'NIX', 'M4', 'LASSO'},
#     {1980, 'NIX', 'M4', 'NIM'},
#     {1980, 'HTML'}
# ]
#
#
# def main(r):
#     s1 = set(r)
#     for i, v in enumerate(s):
#         if not (len(v - s1)):
#             return i

# Пятое по популярности
# ----------------------
# class tree():
#     def __init__(self, number, top, topcon, mid, midcon, down, downcon):
#         self.number = number
#         self.top = top
#         self.mid = mid
#         self.down = down
#         self.topCon = topcon
#         self.midCon = midcon
#         self.downCon = downcon
#
#     def find(self, mas: list, ):
#         if self.top == mas[self.number]:
#             if type(self.topCon) == int:
#                 return self.topCon
#             return self.topCon.find(mas)
#         if self.mid == mas[self.number]:
#             if type(self.midCon) == int:
#                 return self.midCon
#             return self.midCon.find(mas)
#         if self.down == mas[self.number]:
#             if type(self.downCon) == int:
#                 return self.downCon
#             return self.downCon.find(mas)
#
#
# def main(mas):
#     x31 = tree(
#         3, 'HYPHY', 0, None, None, 'M4', 1
#     )
#     x1 = tree(
#         1, 'TEX', 8, 'LASSO', 9, 'NIM', 10
#     )
#     x21 = tree(
#         2, 1963, x31, None, None, 1992, 2
#     )
#     x22 = tree(
#         2, 1963, 6, None, None, 1992, 7
#     )
#     x32 = tree(
#         3, 'HYPHY', x22, None, None, 'M4', x1
#     )
#     x41 = tree(
#         4, 'CLICK', x21, 'NIX', 3, 'HTML', 4
#     )
#     x42 = tree(
#         4, 'CLICK', 5, 'NIX', x32, 'HTML', 11
#     )
#     x0 = tree(
#         0, 1991, x41, None, None, 1980, x42
#     )
#     return x0.find(mas)
