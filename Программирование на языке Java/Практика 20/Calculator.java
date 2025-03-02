public class Calculator {
    public static <T extends Number, U extends Number> double sum(T num1, U num2) {
        return num1.doubleValue() + num2.doubleValue();
    }

    public static <T extends Number, U extends Number> double multiply(T num1, U num2) {
        return num1.doubleValue() * num2.doubleValue();
    }

    public static <T extends Number, U extends Number> double divide(T num1, U num2) {
        if (num2.doubleValue() == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return num1.doubleValue() / num2.doubleValue();
    }

    public static <T extends Number, U extends Number> double subtract(T num1, U num2) {
        return num1.doubleValue() - num2.doubleValue();
    }

    public static void main(String[] args) {
        int intNum = 10;
        double doubleNum = 5.5;

        double sumResult = Calculator.sum(intNum, doubleNum);
        double multiplyResult = Calculator.multiply(intNum, doubleNum);
        double divideResult = Calculator.divide(intNum, doubleNum);
        double subtractResult = Calculator.subtract(intNum, doubleNum);

        System.out.println("Sum: " + sumResult);
        System.out.println("Multiply: " + multiplyResult);
        System.out.println("Divide: " + divideResult);
        System.out.println("Subtract: " + subtractResult);
    }
}
