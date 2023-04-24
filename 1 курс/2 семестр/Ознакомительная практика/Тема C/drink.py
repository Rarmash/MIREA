from item import Item

class Drink(Item):
    def __init__(self, name, price, volume, menu_category):
        super().__init__(name, price)
        self.volume = volume
        self.menu_category = menu_category
        self.ingredients = {}

    def add_ingredient(self, name, amount):
        if name in self.ingredients:
            self.ingredients[name] += amount
        else:
            self.ingredients[name] = amount

    def remove_ingredient(self, name):
        if name in self.ingredients:
            del self.ingredients[name]
        else:
            raise ValueError("Ингридиент не найден")

    def print_ingredients(self):
        for name, amount in self.ingredients.items():
            print(f"{name}: {amount}")

    def __str__(self):
        return f"{self.name} ({self.menu_category}, {self.volume}мл, {self.price}руб)"
