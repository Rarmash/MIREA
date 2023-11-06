import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите строку:");
        String input = scanner.nextLine();

        System.out.println("Введите регулярное выражение:");
        String regex = scanner.nextLine();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        int matchCount = 0;

        System.out.println("Найденные совпадения:");

        while (matcher.find()) {
            matchCount++;
            System.out.println("Совпадение " + matchCount + ": " + matcher.group());
        }

        if (matchCount == 0) {
            System.out.println("Совпадений не найдено.");
        }

        scanner.close();
    }
}