package Task2;

import java.util.Scanner;

public class Try_Catch2 {
    public static void exceptionDemo() {
        Scanner myScanner = new Scanner(System.in);
        try {
            System.out.println("Enter an integer");
            String intString = myScanner.next();
            int i = Integer.parseInt(intString);
            System.out.println(2 / i);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
//        java.lang.NumberFormatException: Это исключение выбрасывается, когда вы пытаетесь преобразовать строку в число
//        с использованием Integer.parseInt(intString), а введенная строка не является допустимым целым числом

//        java.lang.ArithmeticException: Это исключение выбрасывается, когда вы пытаетесь выполнить деление на ноль
        exceptionDemo();
    }
}
