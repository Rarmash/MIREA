#include <iostream>
#include "Employees.h"
using namespace std;

int main()
{
    setlocale(LC_ALL, "Russian");
    cout << "Лабораторная работа №4 по теме Структуры. Гришин Андрей" << endl;
    int choice;
    cout << "1. Работа с пустой таблицей" << endl;
    cout << "2. Работа с таблицей, заполненной автоматически тестовыми данными" << endl;
    cout << "Выберите пункт меню: "; cin >> choice;
    Employee* employeesTable;
    Employee employee;
    Position* positions;
    int rowsCount = 0;
    int posCount = 0;
    switch (choice) {
    case 2: {
        rowsCount = 5;
        posCount = 5;
        employeesTable = new Employee[rowsCount]
        {
            {101, "Ivanov", "I. I.", 21, "01.01.2023"},
            {102, "Vasilyev", "V. A.", 32, "05.06.2021"},
            {103, "Nosov", "D. R.", 32, "18.10.2000"},
            {104, "Gasanov", "A. B.", 44, "31.01.2005"},
            {105, "Putin", "V. V.", 75, "31.12.1999"}
        };
        positions = new Position[posCount]{
            {21, "Manager"},
            {32, "Cashier"},
            {44, "Security"},
            {75, "Store Director"},
            {12, "Cleaner"}
        };
        break;
    }
    default: {
        employeesTable = new Employee[rowsCount];
        cout << endl;
        cout << "Заполните справочник должностей: " << endl;
        cout << "Введите количество должностей: "; cin >> posCount;
        positions = new Position[posCount];
    }
    }
    while (true) {
        cout << endl << endl;
        PrintEmployees(employeesTable, rowsCount, posCount, positions);
        cout << "\n1. Добавить нового сотрудника в конец таблицы" << endl;
        cout << "2. Добавить нового сотрудника так, чтобы запись разместилась после последней записи с такой же должностью" << endl;
        cout << "3. Заменить у всех сотрудников код заданной должности на новый код (перевели на другую должность)" << endl;
        cout << "4. Удалить сотрудников, занимающих заданную должность" << endl;
        cout << "Выберите действие с таблицей: "; cin >> choice;
        cin.ignore();
        switch (choice)
        {
        case 1: {
            FillNewEmployee(employee);
            rowsCount++;
            employeesTable = (Employee*)realloc(employeesTable, (rowsCount) * sizeof(Employee));
            AddEmployeeTableEnd(employee, employeesTable, rowsCount);
            break;
        }
        case 2: {
            FillNewEmployee(employee);
            rowsCount++;
            employeesTable = (Employee*)realloc(employeesTable, (rowsCount) * sizeof(Employee));
            AddEmployeeAfterSameHeld(employee, employeesTable, rowsCount);
            break;
        }
        case 3: {
            int num1, num2;
            cout << "Введите код занимаемой должности, который надо изменить: "; cin >> num1;
            cout << "Введите код занимаемой должности, НА который надо заменить: "; cin >> num2;
            ChangeCodeofHeld(employeesTable, rowsCount, num1, num2);
            break;
        }
        case 4: {
            int code;
            cout << "Введите код занимаемой должности для удаления: "; cin >> code;
            DeleteEmployeesByCode(employeesTable, rowsCount, code);
            employeesTable = (Employee*)realloc(employeesTable, (rowsCount) * sizeof(Employee));
            break;
        }
        }
    }
}