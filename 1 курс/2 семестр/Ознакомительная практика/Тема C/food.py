from item import Item

class Food(Item):
    def __init__(self, name, price, weight, preparation_time):
        super().__init__(name, price)
        self.weight = weight
        self.preparation_time = preparation_time
        self.ingredients = []

    def change_preparation_time(self, new_time):
        self.preparation_time = new_time

    def add_ingredient(self, name):
        self.ingredients.append(name)

    def remove_ingredient(self, name):
        if name in self.ingredients:
            self.ingredients.remove(name)
        else:
            raise ValueError("Ингридиент не найден")

    def change_ingredient(self, old_name, new_name):
        if old_name in self.ingredients:
            index = self.ingredients.index(old_name)
            self.ingredients[index] = new_name
        else:
            raise ValueError("Ингридиент не найден")

    def __str__(self):
        return f"{self.name} ({self.weight}г, {self.price}руб, {self.preparation_time}мин)"
