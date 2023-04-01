#pragma once
using namespace std;

struct Employee {
	int ID;
	char LastName[50];
	char Initials[50];
	int CodeofHeld;
	char StartDate[50];
};

struct Position {
	int CodeofHeld;
	char Name[50];
};

void PrintEmployees(Employee* employeesTable, int rowsCount, int posCount, Position* positions) {
    cout << "=====================================================================================================" << endl;
	cout << "| Табельный номер |" << "    Фамилия    |" << " Инициалы |" << "  Код должн. |" << "      Должность      |" << " Дата поступления |" << endl;
	cout << "=====================================================================================================" << endl;
	int size = 0;

	for (int i = 0; i < rowsCount; i++)
	{
		cout.width(0);
		cout << "| " << employeesTable[i].ID;
		cout.width(14);
		cout << "|" << employeesTable[i].LastName;
		cout.width(16 - strlen(employeesTable[i].LastName));

		cout << "|" << employeesTable[i].Initials;
		cout.width(6);
		cout << "|" << employeesTable[i].CodeofHeld;
		cout.width(14 - 2);

		for (int j = 0; j < posCount; j++) {
			if (employeesTable[i].CodeofHeld == positions[j].CodeofHeld) {
				cout << "|" << positions[j].Name;
				cout.width(22 - strlen(positions[j].Name));
				break;
			}
		}

		cout << "|" << employeesTable[i].StartDate;
		cout.width(19 - strlen(employeesTable[i].StartDate)); cout << "|" << endl;
	};
	cout << "=====================================================================================================" << endl;
}

void FillDirectory(Position* positions, int num) {
	for (int i = 0; i < num; i++) {
		Position pos;
		cout << "Введите код должности: "; cin >> pos.CodeofHeld;
		cout << "Введите название этой должности: "; gets_s(pos.Name);
		cout << endl << endl;
	}
}

void FillNewEmployee(Employee& employee) {
    cout << endl;
    cout << "Заполнение записи для сотрудника" << endl;
    cout << "Введите табельный номер (трёхзначное число): "; cin >> employee.ID;
	cin.ignore();
    cout << "Введите фамилию: "; gets_s(employee.LastName);
    cout << "Введите инициалы: "; gets_s(employee.Initials);
    cout << "Введите код занимаемой должности (двузначное число): "; cin >> employee.CodeofHeld;
	cin.ignore();
	cout << "Введите дату поступления на работу: "; gets_s(employee.StartDate);
}

void AddEmployeeTableEnd(Employee employee, Employee* employeesTable, int rowsCount) {
	employeesTable[rowsCount - 1] = employee;
}

void AddEmployeeAfterSameHeld(Employee employee, Employee* employeesTable, int rowsCount) {
	for (int i = 0; i < rowsCount; i++) {
		if (i == rowsCount - 1) {
			employeesTable[rowsCount - 1] = employee;
			break;
		}
		if (employeesTable[i].CodeofHeld == employee.CodeofHeld && employeesTable[i + 1].CodeofHeld != employee.CodeofHeld) {
			for (int j = rowsCount - 1; j > i; j--) {
				employeesTable[j] = employeesTable[j - 1];
			}
			employeesTable[i + 1] = employee;
			break;
		}
	}
}

void ChangeCodeofHeld(Employee* employeesTable, int rowsCount, int num1, int num2) {
	for (int i = 0; i < rowsCount; i++) {
		if (employeesTable[i].CodeofHeld == num1) {
			employeesTable[i].CodeofHeld = num2;
		}
	}
}

void DeleteEmployeesByCode(Employee* employeesTable, int& rowsCount, int code) {
	for (int i = 0; i < rowsCount; i++) {
		if (employeesTable[i].CodeofHeld == code) {
			for (int j = i; j < rowsCount; j++) {
				employeesTable[j] = employeesTable[j + 1];
			}
			rowsCount--;
			i--;
		}
	}
}