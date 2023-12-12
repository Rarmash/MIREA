package task3;

public class testMain {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <value_for_x>");
            return;
        }

        try {
            int x = Integer.parseInt(args[0]);

            // Создаем выражение x^2 - 2x + 1
            Expression expression = new Add(
                    new Subtract(
                            new Multiply(new Variable("x"), new Variable("x")),
                            new Multiply(new Const(2), new Variable("x"))
                    ),
                    new Const(1)
            );

            // Вычисляем результат для заданного x
            int result = expression.evaluate(x);

            // Выводим результат
            System.out.println("Result for x = " + x + ": " + result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer for x.");
        }
    }
}
