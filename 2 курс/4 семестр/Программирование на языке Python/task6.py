import math


def calc(i):
    answ = 0
    for alph in i:
        answ += math.ceil(alph / 7)
    return answ


def main(input_set):
    H = {a for a in input_set if (a <= 11) != (a > -69)}
    psi = {4 * eta for eta in H if -93 <= eta < 85}
    omega = {abs(a) - math.floor(a / 2) for a in input_set
             if (a <= 57 or a > -86)}
    i = {omega_lower ** 3 + abs(omega_lower) for omega_lower in omega
         if not (-45 < omega_lower <= 52)}

    answ = len({(a, b) for a in psi for b in i}) - calc(i)

    return answ
