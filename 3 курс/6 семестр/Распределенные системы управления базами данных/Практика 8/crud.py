from models import employees, salaries, departments, documents, reports
from bson.objectid import ObjectId


# ----- EMPLOYEES -----

def add_employee(name, position, passport):
    return employees.insert_one({"name": name, "position": position, "passport": passport})

def list_employees():
    return list(employees.find())

def delete_employee(emp_id):
    return employees.delete_one({"_id": ObjectId(emp_id)})

def find_employee_by_name(name):
    return list(employees.find({"name": {"$regex": name, "$options": "i"}}))


# ----- SALARIES -----

def add_salary(employee_id, month, amount):
    return salaries.insert_one({
        "employee_id": ObjectId(employee_id),
        "month": month,
        "amount": amount
    })

def list_salaries():
    return list(salaries.find())

def delete_salary(salary_id):
    return salaries.delete_one({"_id": ObjectId(salary_id)})


# ----- DEPARTMENTS -----

def add_department(name, head):
    return departments.insert_one({"name": name, "head": head})

def list_departments():
    return list(departments.find())

def delete_department(dept_id):
    return departments.delete_one({"_id": ObjectId(dept_id)})


# ----- DOCUMENTS -----

def add_document(doc_type, date, responsible_id):
    return documents.insert_one({
        "type": doc_type,
        "date": date,
        "responsible_id": ObjectId(responsible_id)
    })

def list_documents():
    return list(documents.find())

def delete_document(doc_id):
    return documents.delete_one({"_id": ObjectId(doc_id)})


# ----- REPORTS -----

def add_report(report_type, period, author_id):
    return reports.insert_one({
        "type": report_type,
        "period": period,
        "author_id": ObjectId(author_id)
    })

def list_reports():
    return list(reports.find())

def delete_report(report_id):
    return reports.delete_one({"_id": ObjectId(report_id)})


# --- SORTING ---

def sort_employees_by(field, reverse=False):
    return list(employees.find().sort(field, -1 if reverse else 1))

def sort_salaries_by(field, reverse=False):
    return list(salaries.find().sort(field, -1 if reverse else 1))

def sort_departments_by(field, reverse=False):
    return list(departments.find().sort(field, -1 if reverse else 1))

def sort_documents_by(field, reverse=False):
    return list(documents.find().sort(field, -1 if reverse else 1))

def sort_reports_by(field, reverse=False):
    return list(reports.find().sort(field, -1 if reverse else 1))
