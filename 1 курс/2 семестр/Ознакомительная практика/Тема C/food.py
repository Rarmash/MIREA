from item import Item
class Food(Item):
    def __init__(self, name, price, weight, preparation_time, ingredients):
        super().__init__(name, price)
        self.weight = weight
        self.preparation_time = preparation_time
        self.ingredients = ingredients

    def change_preparation_time(self, new_time):
        self.preparation_time = new_time

    def add_ingredient(self, ingredient):
        self.ingredients.append(ingredient)

    def remove_ingredient(self, ingredient):
        if ingredient in self.ingredients:
            self.ingredients.remove(ingredient)

    def modify_ingredient(self, old_ingredient, new_ingredient):
        if old_ingredient in self.ingredients:
            index = self.ingredients.index(old_ingredient)
            self.ingredients[index] = new_ingredient

    def __str__(self):
        return f"Name: {self.name}\nWeight: {self.weight}\nPrice: {self.price}\nPreparation Time: {self.preparation_time}"
