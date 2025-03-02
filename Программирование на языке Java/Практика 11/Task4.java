import java.util.Calendar;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите год (например, 2023): ");
        int year = scanner.nextInt();
        System.out.print("Введите месяц (1-12): ");
        int month = scanner.nextInt();
        System.out.print("Введите день (1-31): ");
        int day = scanner.nextInt();
        System.out.print("Введите часы (0-23): ");
        int hours = scanner.nextInt();
        System.out.print("Введите минуты (0-59): ");
        int minutes = scanner.nextInt();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hours, minutes); // Месяцы в Calendar начинаются с 0 (январь)

        System.out.println("Созданный Calendar: " + calendar.getTime());

        scanner.close();
    }
}