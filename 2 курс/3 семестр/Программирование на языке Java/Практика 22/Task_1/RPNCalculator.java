package Task_1;

import java.util.Scanner;
import java.util.Stack;

public class RPNCalculator {
    public static double evaluateRPN(String[] tokens) {
//        Класс Stack представляет собой стек объектов, хранящихся в режиме "последний-первый-выход" (LIFO).
//        Он расширяет класс Vector пятью операциями, которые позволяют рассматривать вектор как стек.
        Stack<Double> stack = new Stack<>();

        for (String token : tokens) {
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Недостаточно операндов для оператора: " + token);
                }
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result = performOperation(token, operand1, operand2);
                stack.push(result);
            } else {
                throw new IllegalArgumentException("Недопустимый токен: " + token);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Недостаточно операторов в выражении");
        }
        return stack.pop();
    }

    private static boolean isNumeric(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return "+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token);
    }

    private static double performOperation(String operator, double operand1, double operand2) {
        return switch (operator) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> {
                if (operand2 == 0) {
                    throw new ArithmeticException("Деление на ноль");
                }
                yield operand1 / operand2;
            }
            default -> throw new IllegalArgumentException("Недопустимый оператор: " + operator);
        };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение в обратной польской нотации: ");
        String input = scanner.nextLine();
        scanner.close();

        String[] rpnExpression = input.split(" ");
        double result = evaluateRPN(rpnExpression);
        System.out.println("Результат: " + result);
    }
}
