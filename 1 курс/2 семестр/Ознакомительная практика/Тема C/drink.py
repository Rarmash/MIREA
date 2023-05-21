from item import Item

class Drink(Item):
    def __init__(self, name, price, volume, menu_category):
        super().__init__(name, price)
        self.volume = volume
        self.menu_category = menu_category

    def __str__(self):
        return f"{self.name} ({self.menu_category}, {self.volume}мл, {self.price}руб)"
