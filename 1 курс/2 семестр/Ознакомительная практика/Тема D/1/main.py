import pickle
from logger import ClassA, ClassB

def serialize_objects():
    obj1 = ClassA()
    obj2 = ClassB()

    with open("objects.pkl", "wb") as file:
        pickle.dump(obj1, file)
        pickle.dump(obj2, file)

def deserialize_objects():
    with open("objects.pkl", "rb") as file:
        obj1 = pickle.load(file)
        obj2 = pickle.load(file)

    obj1.modify()
    obj1.print_info()

    obj2.delete()
    obj2.add()
    obj2.print_info()

if __name__ == "__main__":
    serialize_objects()
    deserialize_objects()
