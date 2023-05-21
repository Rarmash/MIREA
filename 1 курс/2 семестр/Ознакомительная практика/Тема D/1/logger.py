import pickle
import datetime

LOG_FILE = "log.txt"

def log_event(key, comment):
    timestamp = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    log_entry = f"{key} --- {timestamp} --- {comment}\n"
    with open(LOG_FILE, "a", encoding='utf8') as file:
        file.write(log_entry)

class ClassA:
    def __init__(self):
        log_event("CRE", "создан экземпляр ClassA")
    
    def modify(self):
        log_event("INF", "изменение в ClassA")
    
    def print_info(self):
        log_event("INF", "вывод информации из ClassA")

class ClassB:
    def __init__(self):
        log_event("CRE", "создан экземпляр ClassB")
    
    def delete(self):
        log_event("INF", "удаление экземпляра ClassB")
    
    def add(self):
        log_event("INF", "добавление в ClassB")
    
    def print_info(self):
        log_event("INF", "вывод информации из ClassB")
