import java.time.LocalDate

fun main() {
    val expensesList = ExpensesList()

    expensesList.addExpense(Expense(
        208.0, "Фастфуд", LocalDate.of(2024, 7, 31)
    ))
    expensesList.addExpense(Expense(
        60.0, "Проезд в транспорте", LocalDate.of(2023, 8, 31)
    ))
    expensesList.addExpense(Expense(
        1325.99, "Проезд в транспорте", LocalDate.of(2022, 8, 31)
    ))
    expensesList.addExpense(Expense(
        327.0, "Фастфуд", LocalDate.of(2023, 7, 31)
    ))

    expensesList.printExpenses()

    expensesList.printTotalByCategory()
}