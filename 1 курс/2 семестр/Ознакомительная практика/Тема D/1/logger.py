import pickle
import datetime

LOG_FILE = 'log.txt'


def log_entry(key, comment):
    timestamp = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    log_line = f"{key} --- {timestamp} --- {comment}"
    with open(LOG_FILE, 'a', encoding='utf8') as file:
        file.write(log_line + '\n')


class MyClass:
    def __init__(self):
        log_entry('CRE', 'создано')

    def modify(self):
        log_entry('INF', 'изменено')

    def delete(self):
        log_entry('INF', 'удалено')

    def add(self):
        log_entry('INF', 'добавлено')

    def print_info(self):
        log_entry('INF', 'распечатано')


# Создание экземпляров класса и запись их в файл
obj1 = MyClass()
obj2 = MyClass()

with open('objects.pkl', 'wb') as file:
    pickle.dump(obj1, file)
    pickle.dump(obj2, file)
