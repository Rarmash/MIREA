from item import Item
from drink import Drink
from food import Food


class Menu:
    def __init__(self, restaurant_name, address, drinks, foods):
        self.restaurant_name = restaurant_name
        self.address = address
        self.drinks = drinks
        self.foods = foods

    def __str__(self):
        menu_info = f"Restaurant Name: {self.restaurant_name}\nAddress: {self.address}\n\nDrinks:\n"
        for drink in self.drinks:
            menu_info += str(drink) + "\n\n"
        menu_info += "Foods:\n"
        for food in self.foods:
            menu_info += str(food) + "\n\n"
        return menu_info

    def __len__(self):
        return len(self.drinks) + len(self.foods)

    def get_item(self, index):
        if index < len(self.drinks):
            return self.drinks[index]
        elif index < len(self):
            return self.foods[index - len(self.drinks)]
        else:
            raise IndexError("Menu index out of range")

    def change_item(self, index, new_item):
        if index < len(self.drinks):
            self.drinks[index] = new_item
        elif index < len(self):
            self.foods[index - len(self.drinks)] = new_item
        else:
            raise IndexError("Menu index out of range")

    def remove_item(self, index):
        if index < len(self.drinks):
            del self.drinks[index]
        elif index < len(self):
            del self.foods[index - len(self.drinks)]
        else:
            raise IndexError("Menu index out of range")

    def __add__(self, item):
        if isinstance(item, Drink):
            self.drinks.append(item)
        elif isinstance(item, Food):
            self.foods.append(item)
        else:
            raise TypeError("Invalid item type for menu")
        return self

    def __sub__(self, item):
        if isinstance(item, Drink):
            if item in self.drinks:
                self.drinks.remove(item)
            else:
                raise ValueError("Drink not found in menu")
        elif isinstance(item, Food):
            if item in self.foods:
                self.foods.remove(item)
            else:
                raise ValueError("Food not found in menu")
        else:
            raise TypeError("Invalid item type for menu")
        return self

    def create_txt_file(self, filename):
        with open(filename, "w") as file:
            file.write(str(self))
            for drink in self.drinks:
                file.write(f"Drink: {drink.name}\n")
                file.write(drink.format_ingredients())
            for food in self.foods:
                file.write(f"Food: {food.name}\n")
                file.write("\n".join(food.ingredients))
                file.write("\n\n")
