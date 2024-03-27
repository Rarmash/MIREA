def main(x):
    x = int(x, 16)
    b1 = (x >> 0) & 0b1111111
    b2 = (x >> 7) & 0b111111
    b3 = (x >> 13) & 0b11111111
    b4 = (x >> 21) & 0b111
    b5 = (x >> 24) & 0b11
    return int((b4 << 23) | (b2 << 17) | (b1 << 10) | (b5 << 8) | (b3 << 0))


main('0x1e8a638')
main('0x3e9a9fc')
main('0x1a51ae3')
main('0x5f018a')
