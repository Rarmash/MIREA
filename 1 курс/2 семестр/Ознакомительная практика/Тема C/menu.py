from item import Item
from drink import Drink
from food import Food


class Menu:
    def __init__(self, restaurant_name, address, drinks=None, foods=None):
        self.restaurant_name = restaurant_name
        self.address = address
        self.drinks = drinks if drinks else []
        self.foods = foods if foods else []

    def __str__(self):
        return f'{self.restaurant_name}\n{self.address}\n\nНапитки:\n{self._print_items(self.drinks)}\n\nБлюда:\n{self._print_items(self.foods)}'

    def __len__(self):
        return len(self.drinks) + len(self.foods)

    def __getitem__(self, index):
        if index < len(self.drinks):
            return self.drinks[index]
        else:
            return self.foods[index - len(self.drinks)]

    def __setitem__(self, index, item):
        if index < len(self.drinks):
            self.drinks[index] = item
        else:
            self.foods[index - len(self.drinks)] = item

    def __delitem__(self, index):
        if index < len(self.drinks):
            del self.drinks[index]
        else:
            del self.foods[index - len(self.drinks)]

    def __add__(self, other):
        if isinstance(other, Drink):
            self.drinks.append(other)
        elif isinstance(other, Food):
            self.foods.append(other)
        else:
            raise TypeError("В меню можно добавить лишь напитки либо блюда")

        return self

    def __sub__(self, other):
        if isinstance(other, Drink):
            self.drinks.remove(other)
        elif isinstance(other, Food):
            self.foods.remove(other)
        else:
            raise TypeError("В меню можно добавить лишь напитки либо блюда")

        return self

    def create_txt_file(self, file_path):
        with open(file_path, 'w', encoding='utf8') as f:
            f.write(str(self))
            for drink in self.drinks:
                f.write('\n\n' + str(drink))
            for food in self.foods:
                f.write('\n\n' + str(food))

    def _print_items(self, items):
        return '\n'.join([str(item) for item in items])
