from crud import (
    add_employee, add_salary, add_department,
    add_document, add_report
)

# Очистка всех коллекций
from models import employees, salaries, departments, documents, reports
employees.delete_many({})
salaries.delete_many({})
departments.delete_many({})
documents.delete_many({})
reports.delete_many({})

print("⚙️  Начинается автозаполнение...")

# Добавляем сотрудников и сохраняем их ID
emp_ids = []
emp_ids.append(add_employee("Иванов Иван", "Бухгалтер", {"серия": "1234", "номер": "567890"}).inserted_id)
emp_ids.append(add_employee("Петрова Анна", "Кассир", {"серия": "5678", "номер": "123456"}).inserted_id)
emp_ids.append(add_employee("Сидоров Юрий", "Программист", {"серия": "9999", "номер": "111111"}).inserted_id)

print("✅ Сотрудники добавлены")

# Зарплаты
add_salary(emp_ids[0], "2025-04", 50000)
add_salary(emp_ids[0], "2025-05", 52000)
add_salary(emp_ids[1], "2025-05", 43000)
add_salary(emp_ids[2], "2025-05", 80000)

print("✅ Зарплаты добавлены")

# Отделы
add_department("Бухгалтерия", "Иванов Иван")
add_department("IT", "Сидоров Юрий")
add_department("Касса", "Петрова Анна")

print("✅ Отделы добавлены")

# Документы
add_document("Накладная", "2025-05-01", emp_ids[0])
add_document("Акт", "2025-05-02", emp_ids[1])
add_document("Счёт", "2025-05-03", emp_ids[2])

print("✅ Документы добавлены")

# Отчёты
add_report("Финансовый", "Апрель 2025", emp_ids[0])
add_report("Расчётный", "Май 2025", emp_ids[1])
add_report("Технический", "Май 2025", emp_ids[2])

print("✅ Отчёты добавлены")

print("\n🎉 Автозаполнение завершено.")
