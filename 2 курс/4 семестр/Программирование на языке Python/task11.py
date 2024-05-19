# Самое популярное решение
# ---------------------------------------------
class MealyError(Exception):
    def __init__(self, message):
        super().__init__(message)


class Mealy:
    def __init__(self):
        self.state = 'A'

    def skew(self):
        if self.state == 'A':
            self.state = 'B'
            return 0
        if self.state == 'E':
            self.state = 'F'
            return 5
        if self.state == 'F':
            self.state = 'G'
            return 8
        else:
            raise MealyError('skew')

    def widen(self):
        if self.state == 'B':
            self.state = 'C'
            return 1
        if self.state == 'E':
            self.state = 'A'
            return 7
        if self.state == 'G':
            self.state = 'H'
            return 10
        if self.state == 'H':
            self.state = 'E'
            return 11
        else:
            raise MealyError('widen')

    def scrub(self):
        if self.state == 'C':
            self.state = 'D'
            return 3
        if self.state == 'D':
            self.state = 'E'
            return 4
        if self.state == 'E':
            self.state = 'C'
            return 6
        if self.state == 'F':
            self.state = 'D'
            return 9
        if self.state == 'B':
            self.state = 'G'
            return 2
        else:
            raise MealyError('scrub')


def main():
    return Mealy()


def test():
    o = main()
    try:
        o.widen()  # MealyError
    except MealyError:
        pass
    try:
        o.scrub()  # MealyError
    except MealyError:
        pass

    assert o.skew() == 0  # A -> B

    try:
        o.skew()  # MealyError
    except MealyError:
        pass
    assert o.scrub() == 2  # B -> G

    try:
        o.skew()  # MealyError
    except MealyError:
        pass
    assert o.widen() == 10  # G -> H

    assert o.widen() == 11  # H -> E

    assert o.widen() == 7  # E -> A
    assert o.skew() == 0  # A -> B
    assert o.widen() == 1  # B -> C

    try:
        o.skew()  # MealyError
    except MealyError:
        pass
    try:
        o.widen()  # MealyError
    except MealyError:
        pass
    assert o.scrub() == 3  # C -> D

    assert o.scrub() == 4  # D -> E
    assert o.scrub() == 6  # E -> C
    assert o.scrub() == 3  # C -> D

    try:
        o.skew()  # MealyError
    except MealyError:
        pass
    try:
        o.widen()  # MealyError
    except MealyError:
        pass
    assert o.scrub() == 4  # D -> E
    assert o.skew() == 5  # E -> F

    assert o.scrub() == 9  # F -> D

    try:
        o.widen()  # MealyError
    except MealyError:
        pass
    assert o.scrub() == 4  # D -> E
    assert o.scrub() == 6  # E -> C
    assert o.scrub() == 3  # C -> D
    assert o.scrub() == 4  # D -> E
    assert o.skew() == 5  # E -> F
    assert o.skew() == 8  # F -> G
    assert o.widen() == 10  # G -> H


test()
