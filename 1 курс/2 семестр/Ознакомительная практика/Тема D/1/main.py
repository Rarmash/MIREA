import pickle
from logger import MyClass

# Десериализация объектов
with open('objects.pkl', 'rb') as file:
    obj1 = pickle.load(file)
    obj2 = pickle.load(file)

# Применение методов к десериализованным объектам
obj1.modify()
obj2.print_info()
