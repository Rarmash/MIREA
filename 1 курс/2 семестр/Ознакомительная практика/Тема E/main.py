import tkinter as tk
from tkinter import ttk
from datetime import *
import matplotlib
import matplotlib.pyplot as plt

import urllib.request
import xml.dom.minidom


plt.rcParams.update({'font.size': 8})
dateNow = datetime.date(datetime.today())

# Заполняем недели
weeks = []
# День.Месяц.Год
currentDate = dateNow.strftime('%d.%m.%Y')
previousDate = dateNow - timedelta(weeks = 1)
weeks.append(previousDate.strftime('%d.%m.%Y') + " - " + currentDate)
for i in range(3):
    newDate = previousDate - timedelta(weeks = 1)
    weeks.append(newDate.strftime('%d.%m.%Y') + " - " + (previousDate - timedelta(days = 1)).strftime('%d.%m.%Y'))
    previousDate = newDate

# Заполняем месяцы
months = []
currentDate = dateNow.strftime('%b %Y')
previousDate = dateNow
months.append(currentDate)
for i in range(3):
    previousDate = previousDate - timedelta(weeks = 4)
    months.append(previousDate.strftime('%b %Y'))

# Заполняем кварталы
quarters = []
currentDate = dateNow.strftime('%d.%m.%Y')
previousDate = dateNow - timedelta(weeks=12)
quarters.append(previousDate.strftime('%d.%m.%Y') + " - " + currentDate)
for i in range(3):
    newDate = previousDate
    previousDate = previousDate - timedelta(weeks=12)
    quarters.append(previousDate.strftime('%d.%m.%Y') + " - " + newDate.strftime('%d.%m.%Y'))

# Заполняем года
years = []
currentDate = dateNow
years.append(currentDate.strftime('%Y'))
for i in range(3):
    currentDate = currentDate - timedelta(weeks = 52)
    years.append(currentDate.strftime('%Y'))

# Заполнение списка периодов данными
def DateBoxCreator():
    choice = date.get()

    if(choice == 1):
        graphDateBox.config(values = weeks)
    elif(choice == 2):
        graphDateBox.config(values = months)
    elif(choice == 3):
        graphDateBox.config(values = quarters)
    elif(choice == 4):
        graphDateBox.config(values = years)

    graphDateBox.current(0)
    graphDateBox.grid(column = 2, row = choice)


# Конвентор валют
def CurrencyConventor():
    title = pageTable.get()
    pageTable.delete(0, 'end')

    fisrtKey = firstCurrencyBox.get()
    secondKey = secondCurrencyBox.get()

    firstData = DataKeys.index(fisrtKey)
    secondData = DataKeys.index(secondKey)
    conventCurrency = float(title) * DataValues[firstData] / DataValues[secondData]

    newCurrencyLabel.config(text = conventCurrency)


# Словарь для возврата названия/номера месяца
monthsInfoDict = {
    "Jan": 1, "Feb": 2, "Mar": 3,
    "Apr": 4, "May": 5, "Jun": 6,
    "Jul": 7, "Aug": 8, "Sep": 9,
    "Oct": 10, "Nov": 11, "Dec": 12,

    1: "янв", 2: "фев", 3: "март",
    4: "апр", 5: "май", 6: "июнь",
    7: "июль", 8: "авг", 9: "сен",
    10: "окт", 11: "ноя", 12: "дек"
}

# Генератор графика за определнный период
def GraphCreator():
    choice = date.get()
    daysDates = []
    data = []

    # Выбрана неделя
    if(choice == 1):
        fullDate = (graphDateBox.get()).split()   
        secondDate = datetime.strptime(fullDate[0], '%d.%m.%Y')

        for i in range(7):
            daysDates.append(secondDate.strftime('%d'))

            url = f"http://www.cbr.ru/scripts/XML_daily.asp?date_req={secondDate.strftime('%d/%m/%Y')}"
            data.append(SearchData(url)) 
            secondDate += timedelta(days = 1)

    # Выбран месяц
    elif(choice == 2):
        fullDate = graphDateBox.get()
        month, year = fullDate.split()
        firstDate = datetime(year = int(year), month = monthsInfoDict[month], day = 1)
        i = 1

        # Выравнивание номера месяца
        if monthsInfoDict[month] < 10:
            temp = '0' + str(monthsInfoDict[month])
        else:
            temp = str(monthsInfoDict[month])

        # Если не текущий месяц
        if month != dateNow.strftime('%b'):
            while firstDate.strftime('%m') == temp:
                daysDates.append(i)

                url = f"http://www.cbr.ru/scripts/XML_daily.asp?date_req={firstDate.strftime('%d.%m.%Y')}"
                data.append(SearchData(url)) 
                
                firstDate += timedelta(days = 1)
                i += 1
        # Если текущий месяц
        else:
            while i != int(dateNow.strftime('%d')):
                daysDates.append(i)

                url = f"http://www.cbr.ru/scripts/XML_daily.asp?date_req={firstDate.strftime('%d.%m.%Y')}"
                data.append(SearchData(url)) 

                firstDate += timedelta(days = 1)
                i += 1  

    # Выбран квартал
    elif(choice == 3):
        fullDate = (graphDateBox.get()).split()   
        secondDate = datetime.strptime(fullDate[0], '%d.%m.%Y')

        for i in range(13):
            daysDates.append(secondDate.strftime('%d.%m'))

            url = f"http://www.cbr.ru/scripts/XML_daily.asp?date_req={secondDate.strftime('%d/%m/%Y')}"
            data.append(SearchData(url)) 
            secondDate += timedelta(weeks = 1)

    # Выбран год
    elif(choice == 4):
        fullDate = graphDateBox.get()
        yearsDifference = int(fullDate) - int(dateNow.strftime('%Y'))

        # Выбран текущий год
        if yearsDifference == 0:
            monthCount = int(dateNow.strftime('%m'))
        # Выбран другой год
        else:
            monthCount = 12

        for i in range(monthCount):
            daysDates.append(monthsInfoDict[i+1])

            if i < 9:
                url = f"http://www.cbr.ru/scripts/XML_daily.asp?date_req=01/0{i+1}/{fullDate}"
            else:
                url = f"http://www.cbr.ru/scripts/XML_daily.asp?date_req=01/{i+1}/{fullDate}"
                
            data.append(SearchData(url)) 

    fig = plt.figure()
    canvas = matplotlib.backends.backend_tkagg.FigureCanvasTkAgg(fig, master = secondTab)
    plot_widget = canvas.get_tk_widget()
    fig.clear()

    plt.plot(daysDates, data)
    plt.grid()
    plot_widget.grid(row = 10, column = 10)


# Получение данных из Xml по url
def DataGetter(url):
    response = urllib.request.urlopen(url)
    xml_string = response.read().decode('windows-1251')
    domObject = xml.dom.minidom.parseString(xml_string)
    data = {}

    # Проходим по каждому элементу Valute и добавляем данные в словарь данных data
    for valute in domObject.getElementsByTagName('Valute'):
        name = valute.getElementsByTagName('Name')[0].childNodes[0].nodeValue
        value = float(
            valute.getElementsByTagName('Value')[0].childNodes[0].nodeValue.replace(',', '.')) / int(
            valute.getElementsByTagName('Nominal')[0].childNodes[0].nodeValue)
        
        data[name] = value

    data["Российский рубль"] = 1.0

    return data

# Получение определенных данных по url
def SearchData(url):
    data = DataGetter(url)
    return data[thirdCurrencyBox.get()]


# Заполнение первоначальных данных
url = f"http://www.cbr.ru/scripts/XML_daily.asp?date_req={dateNow.strftime('%d/%m/%Y')}"
data = DataGetter(url)

# Массивы для хранений названий валют и их значения
DataKeys = []
DataValues = []

for key in data.keys():
    DataKeys.append(key)
for value in data.values():
    DataValues.append(value)


# Создание интерфейса
window = tk.Tk()
window.title("Конвертер валют")
window.geometry("1280x800")

# Создание хедера с двумя вкладками
windowHead = ttk.Notebook(window)
fisrtTab = ttk.Frame(windowHead)
secondTab = ttk.Frame(windowHead)
windowHead.add(fisrtTab, text = "Калькулятор валют")
windowHead.add(secondTab, text = "Динамика курса")

# Первая вкладка
fisrtTab.grid_columnconfigure(0, minsize = 200)
fisrtTab.grid_columnconfigure(1, minsize = 150)
fisrtTab.grid_rowconfigure(0, minsize = 100)

# Создание и размещение списков валют в интерфейсе
firstCurrencyBox = ttk.Combobox(fisrtTab, values = DataKeys)
firstCurrencyBox.current(0)
firstCurrencyBox.grid(row = 0, column = 0)
secondCurrencyBox = ttk.Combobox(fisrtTab, values = DataKeys)
secondCurrencyBox.current(0)
secondCurrencyBox.grid(row = 3, column = 0)

# Блок ввода
pageTable = tk.Entry(fisrtTab)
pageTable.grid(row = 0, column = 1)
# Кнопка конвертации
convertButton = tk.Button(fisrtTab, text = "Конвертировать", command = CurrencyConventor)
convertButton.grid(row = 0, column = 2)
# Блок вывода данных
newCurrencyLabel = tk.Label(fisrtTab,text = "")
newCurrencyLabel.grid(row = 3, column= 1)


# Вторая вкладка
secondTab.grid_columnconfigure(0, minsize = 200)
secondTab.grid_columnconfigure(1, minsize = 200)

# Надпись над списком валют
currencyLabel = tk.Label(secondTab, text = "Валюта")
currencyLabel.grid(row = 0, column = 0)
# Создание и размещение списка всех валют
thirdCurrencyBox = ttk.Combobox(secondTab, values = DataKeys)
thirdCurrencyBox.current(0)
thirdCurrencyBox.grid(row = 1, column = 0)

# Надпись над списком периодов
dateLabel = tk.Label(secondTab,text = "Период")
dateLabel.grid(row = 0, column = 1)
# Создание и размещение видов периода
date = tk.IntVar()
tk.Radiobutton(secondTab, text = 'Неделя', variable = date, value = 1, command = DateBoxCreator).grid(row = 1, column = 1)
tk.Radiobutton(secondTab, text = 'Месяц', variable = date, value = 2, command = DateBoxCreator).grid(row = 2, column = 1)
tk.Radiobutton(secondTab, text = 'Квартал', variable = date, value = 3, command = DateBoxCreator).grid(row = 3, column = 1)
tk.Radiobutton(secondTab, text = 'Год', variable = date, value = 4, command = DateBoxCreator).grid(row = 4, column = 1)

# Надпись над списком выбора периода
dateChoiceLabel = tk.Label(secondTab, text = "Выбор периода")
dateChoiceLabel.grid(row = 0, column= 2)
# Создание списка периодов
graphDateBox = ttk.Combobox(secondTab)

# Создание кнопки построения графика
graphCreatorButton = tk.Button(secondTab, text = "Построить график", command = GraphCreator)
graphCreatorButton.grid(row = 4, column = 0)


#Запуск программы
windowHead.pack(expand = 1, fill = 'both')
window.mainloop()