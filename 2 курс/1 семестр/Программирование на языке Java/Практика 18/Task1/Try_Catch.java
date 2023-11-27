package Task1;

public class Try_Catch {
    public static void exceptionDemo() {
//        Такое поведение характерно для стандарта IEEE 754 для чисел с плавающей запятой.

//        В Java, при попытке выполнить целочисленное деление на ноль, будет сгенерировано
//        исключение ArithmeticException, поскольку в целых числах деление на ноль не имеет определения в математике.
        System.out.println(2.0 / 0.0);
    }

    public static void exception1() {
        try {
            System.out.println(2 / 0);
        } catch (ArithmeticException e) {
            System.out.println("Attempted division by zero");
        }
    }


    public static void main(String[] args) {
//        exceptionDemo();
        exception1();
    }
}
