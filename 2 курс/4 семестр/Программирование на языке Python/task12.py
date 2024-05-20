from struct import unpack_from, calcsize


class Types:
    double = "d"
    int32 = "i"
    int16 = "h"
    uint16 = "H"
    uint8 = "B"
    int8 = "b"
    int64 = "q"
    uint32 = "I"
    char = "s"
    uint64 = "Q"
    float = "f"


class BinaryReader:
    def __init__(self, data, offset, order=">"):
        self.data = data
        self.offset = offset
        self.order = order

    def jump_to(self, offset):
        reader = BinaryReader(self.data, offset, self.order)
        return reader

    def read(self, frmt):
        data = unpack_from(self.order + frmt, self.data, self.offset)
        self.offset += calcsize(frmt)
        return data[0]


def read_e(reader):
    e1 = reader.read(Types.uint8)
    e2 = reader.read(Types.uint16)
    e3 = reader.read(Types.uint16)
    return dict(E1=e1, E2=e2, E3=e3)


def read_d(reader):
    d1 = reader.read(Types.float)
    d2 = reader.read(Types.int64)
    d3 = reader.read(Types.int8)
    d4 = [reader.read(Types.int16) for _ in range(6)]
    d5 = [reader.read(Types.int16) for _ in range(4)]
    d6 = [reader.read(Types.uint64) for _ in range(2)]
    d7 = reader.read(Types.uint32)
    return dict(D1=d1, D2=d2, D3=d3, D4=d4, D5=d5, D6=d6, D7=d7)


def read_c(reader):
    c1 = reader.read(Types.double)
    c2_size = reader.read(Types.uint16)
    c2_offset = reader.read(Types.uint32)
    c2_reader = reader.jump_to(c2_offset)
    c2 = [read_d(c2_reader) for _ in range(c2_size)]
    c3 = reader.read(Types.uint8)
    e_offset = reader.read(Types.uint16)
    e_reader = reader.jump_to(e_offset)
    c4 = read_e(e_reader)
    return dict(C1=c1, C2=c2, C3=c3, C4=c4)


def read_b(reader):
    b1 = reader.read(Types.uint32)
    b2_s = reader.read(Types.uint16)
    b2_offset = reader.read(Types.uint32)
    b2_reader = reader.jump_to(b2_offset)
    b2 = [b2_reader.read(Types.int32) for _ in range(b2_s)]
    return dict(B1=b1, B2=b2)


def read_a(reader):
    b_offset = reader.read(Types.uint16)
    b_reader = reader.jump_to(b_offset)
    a1 = read_b(b_reader)
    a2 = reader.read(Types.uint64)
    c_offset = reader.read(Types.uint32)
    c_reader = reader.jump_to(c_offset)
    a3 = read_c(c_reader)
    return dict(A1=a1, A2=a2, A3=a3)


def main(data):
    return read_a(BinaryReader(data, 5))


main((b"SNON\x83\x00'\xcaWkk\x08\x08\x92\xc2\x00\x00\x00"
      b'\xa0\xb6`*1\xa4\xcf\xcd\x8f\xbd\x1e;\xed\xe9\xb7\xc7\t]='
      b'\xdfn\xca\xce\xf5R\x00\x05\x00\x00\x00\x13\xbe\xf1\xf5'
      b'\xd3\xf9^\x90[\xe9\xdbj\r]mtK\x96Jc1m\xc64\xf4l\xe4\xda'
      b'\x9cI\xc6\xdcQ,\x1cST\xcc\x96\x15^\xbfIm\xb1\xd3\xc7\xfe'
      b'\xa7\xef\x83\x8e\xdd\xed\xbfB7R\xcfnw\xebs\x0f-G\xf4d'
      b'\x8e\xfc\x8b\xc2\xf8\xd1\x17E\x0f\xa9\x1f\x1b0q\x80\xc7'
      b'\xd0\x0f\x7f\xeeb\xfb\x08\x80\xcb\xe8<\xe2\x86\xd1\xdb'
      b'\x970l\x93\xacIm\xaa6[F0\xa1\xbf\xeaH\xc5C\x8b\x9a.'
      b'\x00\x02\x00\x00\x001\xa6\x00\x9b'))
