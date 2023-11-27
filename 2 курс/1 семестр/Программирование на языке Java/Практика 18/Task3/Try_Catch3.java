package Task3;

import java.util.Scanner;

public class Try_Catch3 {
    public static void exceptionDemo() {
        Scanner myScanner = new Scanner(System.in);
        try {
            System.out.println("Enter an integer");
            String intString = myScanner.next();
            int i = Integer.parseInt(intString);
            System.out.println(2 / i);
//            В Java, при обработке исключений, catch блоки должны быть упорядочены таким образом,
//            чтобы наиболее специфичные исключения обрабатывались перед более общими.
        } catch (Exception e) {
            System.out.println("Exception error");
        }
//        catch (NumberFormatException e) {
//            System.out.println("NumberFormatException: " + e.getMessage());
//        } catch (ArithmeticException e) {
//            System.out.println("ArithmeticException: " + e.getMessage());
//        }
    }


    public static void main(String[] args) {
//        java.lang.NumberFormatException: Это исключение выбрасывается, когда вы пытаетесь преобразовать строку в число
//        с использованием Integer.parseInt(intString), а введенная строка не является допустимым целым числом

//        java.lang.ArithmeticException: Это исключение выбрасывается, когда вы пытаетесь выполнить деление на ноль
        exceptionDemo();
    }
}
