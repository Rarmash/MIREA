# Создать список (коллекция фильмов), состоящий из словарей (фильмы). Словари должны содержать как минимум 5 полей
# (например, номер, название, год выхода...). В список добавить хотя бы 10 словарей.
# Конструкция вида:
# films = [{"id" : 123456, "title" : "Титаник", "year" : "1997",...} , {...}, {...}, ...]
# Реализовать функции:
# – вывода информации о всех фильмах;
# – вывода информации о фильме по введенному с клавиатуры номеру;
# – вывода количества фильмов, новее введённого года;
# – обновлении всей информации о фильме по введенному номеру;
# – удалении фильма по номеру.
# Провести тестирование функций.

def all_films(films):
    for film in films:
        print(f'{film[u"id"]}: {film[u"title"]} ({film[u"year"]}). Жанры: {film[u"genre"]}. Страны: {film[u"country"]}')

def find_film(id, films):
    s = ''
    for film in films:
        if film[u"id"] == id:
            s = f'{film[u"id"]}: {film[u"title"]} ({film[u"year"]}). Жанры: {film[u"genre"]}. Страны: {film[u"country"]}'
    return s

def newer_films(year, films):
    s = 0
    for film in films:
        if film[u"year"] > year:
            s += 1
    return s

def update_film(id, films):
    for film in films:
        if film[u"id"] == id:
            title = input('Введите новое название: ')
            year = int(input('Введите новый год выпуска: '))
            genres = input('Введите жанры: ')
            country = input("Введите страну/страны: ")
            film[u"title"] = title
            film[u"year"] = year
            film[u"genre"] = genres
            film[u"country"] = country
    all_films(films)

def delete_film(id, films):
    for film in films:
        if film[u"id"] == id:
            films.remove(film)
    all_films(films)

def menu():
    print("1. Вывод информации о всех фильмах")
    print("2. Вывод информации о фильме по введенному с клавиатуры номеру")
    print("3. Вывод количества фильмов, новее введённого года")
    print("4. Обновление всей информации о фильме по введенному номеру")
    print("5. Удаление фильма по номеру")
    n = int(input("Число: "))
    match n:
        case 1:
            all_films(films)
        case 2:
            identificator = int(input('Введите ID: '))
            print(find_film(identificator, films))
        case 3:
            year = int(input("Введите год: "))
            print(newer_films(year, films))
        case 4:
            identificator = int(input('Введите ID: '))
            update_film(identificator, films)
        case 5:
            identificator = int(input('Введите ID: '))
            delete_film(identificator, films)

films = [
    {
        "id": 1,
        "title": "Титаник",
        "year": 1997,
        "country": "США, Мексика",
        "genre": "мелодрама, история, триллер, драма"
    },
    {
        "id": 2,
        "title": "Чебурашка",
        "year": 2022,
        "country": "Россия",
        "genre": "семейный, комедия"
    },
    {
        "id": 3,
        "title": "Команда «А»",
        "year": 2010,
        "country": "США, Великобритания",
        "genre": "боевик, триллер, комедия, приключения"
    },
    {
        "id": 4,
        "title": "Поездка в Америку",
        "year": 1988,
        "country": "США",
        "genre": "мелодрама, комедия"
    },
    {
        "id": 5,
        "title": "Зеленая миля",
        "year": 1999,
        "country": "США",
        "genre": "драма, фэнтези, криминал"
    },
    {
        "id": 6,
        "title": "Форрест Гамп",
        "year": 1994,
        "country": "США",
        "genre": "драма, комедия, мелодрама, история, военный"
    },
    {
        "id": 7,
        "title": "Назад в будущее",
        "year": 1985,
        "country": "США",
        "genre": "фантастика, комедия, приключения"
    },
    {
        "id": 8,
        "title": "Бойцовский клуб",
        "year": 1999,
        "country": "США, Германия",
        "genre": "триллер, драма, криминал"
    },
    {
        "id": 9,
        "title": "ВАЛЛ·И",
        "year": 2008,
        "country": "США",
        "genre": "мультфильм, фантастика, приключения, семейный"
    },
    {
        "id": 10,
        "title": "Гарри Поттер и Принц-полукровка",
        "year": 2009,
        "country": "Великобритания, США",
        "genre": "фэнтези, приключения, семейный"
    }
]

menu()