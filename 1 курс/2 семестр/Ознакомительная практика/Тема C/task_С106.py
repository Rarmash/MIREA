"""
Каждый класс реализовать в отдельном модуле, импортируя их в производные модули.
Создать класс Item с полями название, цена. Добавить конструктор класса.
Создать производный от Item класс Drink. Новые поля: объем напитка, раздел меню, состав напитка
    (словарь вида название ингредиента: количество). Определить конструктор, с вызовом родительского конструктора.
    Определить функции добавления и удаления ингредиента, форматированной печати состава напитка. Переопределить
    метод преобразования в строку для печати основной информации (название, раздел меню, объем, цена).
Создать производный от Item класс Food. Новые поля: масса блюда, время приготовления, состав блюда (список
    ингредиентов). Определить конструктор, с вызовом родительского конструктора. Определить функции изменения
    времени приготовления, добавления, удаления и изменения списка ингредиентов. Переопределить метод
    преобразования в строку для печати основной информации (название, масса, цена, время приготовления).
Создать класс Menu. Поля: название ресторана, адрес, список напитков (список экземпляров класса Drink), список
    горячих блюд (список экземпляров класса Food). Определить конструктор. Переопределить метод преобразования
    в строку для печати всей информации о меню (с использованием переопределения в классах Drink и Food).
    Переопределить методы получения количества пунктов меню функцией len, получения напитка/блюда по индексу,
    изменения по индексу, удаления по индексу (пусть вначале идут индексы напитков, затем горячих блюд).
    Переопределить операции + и - для добавления или удаления пункта меню. Добавить функцию создания txt-файла
    и записи всей информации в него (в том числе списков ингредиентов напитков и блюд).
Предусмотреть хотя бы в 3 местах обработку возможных исключений.
В каждом модуле провести подробное тестирование всех создаваемых объектов и функций.
"""

from item import Item
from drink import Drink
from food import Food
from menu import Menu

item = Item("Item 1", 10)
print(item)

drink = Drink("Drink 1", 5, "200ml", "Beverages", {"Water": "100ml", "Sugar": "10g"})
print(drink)
drink.add_ingredient("Ice", "50g")
print(drink.format_ingredients())
drink.remove_ingredient("Sugar")
print(drink.format_ingredients())

food = Food("Food 1", 15, "250g", "30 minutes", ["Ingredient 1", "Ingredient 2"])
print(food)
food.change_preparation_time("45 minutes")
print(food)
food.add_ingredient("Ingredient 3")
print(food.ingredients)
food.remove_ingredient("Ingredient 2")
print(food.ingredients)
food.modify_ingredient("Ingredient 1", "New Ingredient 1")
print(food.ingredients)

drink1 = Drink("Drink A", 3, "300ml", "Beverages", {"Water": "200ml", "Sugar": "20g"})
drink2 = Drink("Drink B", 4, "400ml", "Beverages", {"Water": "300ml", "Sugar": "30g"})
food1 = Food("Food X", 10, "500g", "40 minutes", ["Ingredient X"])
food2 = Food("Food Y", 12, "600g", "50 minutes", ["Ingredient Y"])
menu = Menu("Restaurant", "123 Main Street", [drink1, drink2], [food1, food2])
print(menu)
print(len(menu))
menu[1]
menu.__setitem__(1, food)
print(menu)
del menu[1]
print(menu)
menu + food
menu + drink
print(menu)
menu - drink
print(menu)
menu.create_txt_file("menu.txt")
