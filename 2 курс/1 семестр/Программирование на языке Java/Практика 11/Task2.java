import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите дату в формате ГГГГ-ММ-ДД (например, 2023-09-25): ");
        String userInput = scanner.nextLine();

        // LocalDate - это класс в пакете java.time, представляющий собой дату без времени в
        // современном календаре Грегорианского календаря
        LocalDate userDate = LocalDate.parse(userInput);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        System.out.println("Текущая дата: " + currentDate.format(formatter));
        System.out.println("Введенная дата: " + userDate.format(formatter));

        if (userDate.isEqual(currentDate)) {
            System.out.println("Введенная дата совпадает с текущей датой.");
        } else if (userDate.isBefore(currentDate)) {
            System.out.println("Введенная дата ранее текущей даты.");
        } else {
            System.out.println("Введенная дата позже текущей даты.");
        }
        scanner.close();
    }
}