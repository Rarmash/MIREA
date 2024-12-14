import java.time.LocalDate

class Expense {
    val expenseAmount: Double
    val category: String
    val date: LocalDate

    constructor(expenseAmount: Double, category: String, date: LocalDate) {
        this.expenseAmount = expenseAmount
        this.category = category
        this.date = date
    }

    fun displayInfo() {
        println("Сумма расхода: $expenseAmount\nКатегория: $category\nДата: $date")
    }
}