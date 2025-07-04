def format_employee(employee):
    return f"""ID: {employee['_id']}
ФИО: {employee['name']}
Должность: {employee['position']}
Паспорт: {employee.get('passport', {}).get('серия', '-') or '-'} {employee.get('passport', {}).get('номер', '-') or '-'}
{'-' * 30}"""

def format_salary(salary):
    return f"""ID: {salary['_id']}
Сотрудник ID: {salary['employee_id']}
Месяц: {salary['month']}
Сумма: {salary['amount']}
{'-' * 30}"""

def format_department(department):
    return f"""ID: {department['_id']}
Название: {department['name']}
Начальник: {department['head']}
{'-' * 30}"""

def format_document(doc):
    return f"""ID: {doc['_id']}
Тип: {doc['type']}
Дата: {doc['date']}
Ответственный ID: {doc['responsible_id']}
{'-' * 30}"""

def format_report(rep):
    return f"""ID: {rep['_id']}
Тип: {rep['type']}
Период: {rep['period']}
Автор ID: {rep['author_id']}
{'-' * 30}"""

def print_employees(data):
    for item in data:
        print(format_employee(item))

def print_salaries(data):
    for item in data:
        print(format_salary(item))

def print_departments(data):
    for item in data:
        print(format_department(item))

def print_documents(data):
    for item in data:
        print(format_document(item))

def print_reports(data):
    for item in data:
        print(format_report(item))
