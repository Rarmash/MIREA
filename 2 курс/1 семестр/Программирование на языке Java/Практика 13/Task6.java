import java.io.*;
import java.util.*;

public class Task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String filePath = scanner.nextLine();

        try {
            List<String> words = readWordsFromFile("D:\\Intellij Idea\\Projects\\itproger-java\\src\\Work_13_num5\\" + filePath);
            String result = getLine(words);
            System.out.println("Результат: " + result);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    // Метод для чтения слов из файла
    public static List<String> readWordsFromFile(String filePath) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineWords = line.split("\\s+");
                words.addAll(Arrays.asList(lineWords));
            }
        }
        return words;
    }

    // Метод для перестановки слов в нужном порядке
    public static String getLine(List<String> words) {
        if (words == null || words.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        result.append(words.get(0));
        words.remove(0);

        while (!words.isEmpty()) {
            String lastWord = result.substring(result.length() - 1).toLowerCase();
            boolean found = false;

            for (int i = 0; i < words.size(); i++) {
                String word = words.get(i);
                if (word.toLowerCase().startsWith(lastWord)) {
                    result.append(" ").append(word);
                    words.remove(i);
                    found = true;
                    break;
                }
            }

            if (!found) {
                break;
            }
        }

        return result.toString();
    }
}