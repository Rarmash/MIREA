package task3;

public class test {
    public static void main(String[] args) {
        // Пример использования
        Expression expression = new Subtract(new Multiply(new Const(2), new Variable("x")), new Const(3));
        int result = expression.evaluate(5);
        System.out.println(result);  // Ожидаемый результат: 7
    }
}
