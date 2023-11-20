package Work_7;

public class WorkWithStringTest implements WorkWithString{

    @Override
    public int countCharacters(String s) {
        return s.length();
    }

    @Override
    public String getOddPositionCharacters(String s) {
//        StringBuilder - это класс в Java, предназначенный для манипуляции строками,
//        особенно для выполнения множественных операций вставки, удаления или модификации символов в строке.
//        Он является частью стандартной библиотеки Java и предоставляет эффективный способ работы со строками,
//        особенно когда вам нужно выполнять множество операций изменения строки,
//        так как он позволяет избежать создания большого числа временных строк, что экономит память и улучшает производительность.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i += 2) {
            result.append(s.charAt(i));
        }
        return result.toString();
    }

    @Override
    public String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static void main(String[] args) {
        WorkWithString workWithString = new WorkWithStringTest();
        String input = "Hello, World!";

        // Подсчет символов
        int charCount = workWithString.countCharacters(input);
        System.out.println("Количество символов в строке: " + charCount);

        // Получение строки с символами на нечетных позициях
        String oddChars = workWithString.getOddPositionCharacters(input);
        System.out.println("Символы на нечетных позициях: " + oddChars);

        // Инвертирование строки
        String reversed = workWithString.reverseString(input);
        System.out.println("Инвертированная строка: " + reversed);
    }
}