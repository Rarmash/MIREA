from item import Item
class Drink(Item):
    def __init__(self, name, price, volume, menu_section, ingredients):
        super().__init__(name, price)
        self.volume = volume
        self.menu_section = menu_section
        self.ingredients = ingredients

    def add_ingredient(self, ingredient, quantity):
        self.ingredients[ingredient] = quantity

    def remove_ingredient(self, ingredient):
        if ingredient in self.ingredients:
            del self.ingredients[ingredient]

    def format_ingredients(self):
        formatted_ingredients = ""
        for ingredient, quantity in self.ingredients.items():
            formatted_ingredients += f"{ingredient}: {quantity}\n"
        return formatted_ingredients

    def __str__(self):
        return f"Name: {self.name}\nMenu Section: {self.menu_section}\nVolume: {self.volume}\nPrice: {self.price}"

