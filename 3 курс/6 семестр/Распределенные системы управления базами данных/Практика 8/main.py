from crud import *
from pretty_print import *

def menu():
    while True:
        print("\n--- МЕНЮ ---")
        print("1. Сотрудники")
        print("2. Зарплаты")
        print("3. Отделы")
        print("4. Документы")
        print("5. Отчёты")
        print("0. Выход")

        match input("Выбор: "):
            case "1":
                employee_menu()
            case "2":
                salary_menu()
            case "3":
                department_menu()
            case "4":
                document_menu()
            case "5":
                report_menu()
            case "0":
                break

def employee_menu():
    while True:
        print("\n-- Сотрудники --")
        print("1. Добавить")
        print("2. Показать всех")
        print("3. Найти по имени")
        print("4. Удалить")
        print("5. Сортировать")
        print("0. Назад")
        match input("Выбор: "):
            case "1":
                name = input("ФИО: ")
                pos = input("Должность: ")
                passport = {"серия": input("Серия паспорта: "), "номер": input("Номер паспорта: ")}
                add_employee(name, pos, passport)
            case "2":
                print_employees(list_employees())
            case "3":
                name = input("Поиск по имени: ")
                print_employees(find_employee_by_name(name))
            case "4":
                emp_id = input("ID сотрудника: ")
                delete_employee(emp_id)
            case "5":
                print("Сортировать по:\n1. Имени\n2. Должности")
                ch = input("Выбор: ")
                if ch == "1":
                    print_employees(sort_employees_by("name"))
                elif ch == "2":
                    print_employees(sort_employees_by("position"))
            case "0":
                return

def salary_menu():
    while True:
        print("\n-- Зарплаты --")
        print("1. Добавить")
        print("2. Показать все")
        print("3. Удалить")
        print("4. Сортировать")
        print("0. Назад")
        match input("Выбор: "):
            case "1":
                emp_id = input("ID сотрудника: ")
                month = input("Месяц: ")
                amount = float(input("Сумма: "))
                add_salary(emp_id, month, amount)
            case "2":
                print_salaries(list_salaries())
            case "3":
                salary_id = input("ID зарплаты: ")
                delete_salary(salary_id)
            case "4":
                print("Сортировать по:\n1. Сумме\n2. Месяцу")
                ch = input("Выбор: ")
                if ch == "1":
                    print_salaries(sort_salaries_by("amount"))
                elif ch == "2":
                    print_salaries(sort_salaries_by("month"))
            case "0":
                return

def department_menu():
    while True:
        print("\n-- Отделы --")
        print("1. Добавить")
        print("2. Показать все")
        print("3. Удалить")
        print("4. Сортировать")
        print("0. Назад")
        match input("Выбор: "):
            case "1":
                name = input("Название отдела: ")
                head = input("ФИО начальника: ")
                add_department(name, head)
            case "2":
                print_departments(list_departments())
            case "3":
                dept_id = input("ID отдела: ")
                delete_department(dept_id)
            case "4":
                print_departments(sort_departments_by("name"))
            case "0":
                return

def document_menu():
    while True:
        print("\n-- Документы --")
        print("1. Добавить")
        print("2. Показать все")
        print("3. Удалить")
        print("4. Сортировать")
        print("0. Назад")
        match input("Выбор: "):
            case "1":
                t = input("Тип документа: ")
                date = input("Дата (ГГГГ-ММ-ДД): ")
                resp_id = input("ID ответственного: ")
                add_document(t, date, resp_id)
            case "2":
                print_documents(list_documents())
            case "3":
                doc_id = input("ID документа: ")
                delete_document(doc_id)
            case "4":
                print("Сортировать по:\n1. Дате\n2. Типу")
                ch = input("Выбор: ")
                if ch == "1":
                    print_documents(sort_documents_by("date"))
                elif ch == "2":
                    print_documents(sort_documents_by("type"))
            case "0":
                return

def report_menu():
    while True:
        print("\n-- Отчёты --")
        print("1. Добавить")
        print("2. Показать все")
        print("3. Удалить")
        print("4. Сортировать")
        print("0. Назад")
        match input("Выбор: "):
            case "1":
                t = input("Тип отчёта: ")
                period = input("Период: ")
                author_id = input("ID автора: ")
                add_report(t, period, author_id)
            case "2":
                print_reports(list_reports())
            case "3":
                rep_id = input("ID отчёта: ")
                delete_report(rep_id)
            case "4":
                print("Сортировать по:\n1. Периоду\n2. Типу")
                ch = input("Выбор: ")
                if ch == "1":
                    print_reports(sort_reports_by("period"))
                elif ch == "2":
                    print_reports(sort_reports_by("type"))
            case "0":
                return

if __name__ == "__main__":
    menu()
