package Task8;

import java.util.Scanner;

public class ThrowsDemo4 {
    public static void getKey() {
        Scanner myScanner = new Scanner(System.in);
        String key = myScanner.next();
        printDetails(key);
    }

    public static void printDetails(String key) {
        try {
            String message = getDetails(key);
            System.out.println(message);
        } catch (Exception e) {
            System.out.println("Caught Exception: " + e.getMessage());
            // Добавим цикл для предоставления пользователю второго шанса
            System.out.println("Please enter the key again:");
            getKey();
        }
    }

    private static String getDetails(String key) {
        try {
            if (key.equals("")) {
                throw new Exception("Key set to empty string");
            }
            return "data for " + key;
        } catch (Exception e) {
            System.out.println("Caught Exception: " + e.getMessage());
            // Теперь метод getDetails возвращает null, чтобы показать ошибку,
            // исключение было обработано в printDetails
            return null;
        }
    }

    public static void main(String[] args) {
        getKey();
    }
}
