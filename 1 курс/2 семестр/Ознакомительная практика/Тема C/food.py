from item import Item

class Food(Item):
    def __init__(self, name, price, weight, preparation_time):
        super().__init__(name, price)
        self.weight = weight
        self.preparation_time = preparation_time

    def change_preparation_time(self, new_time):
        self.preparation_time = new_time

    def __str__(self):
        return f"{self.name} ({self.weight}г, {self.price}руб, {self.preparation_time}мин)"
