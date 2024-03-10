import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
    public static void Num_2() {
        String input1 = "abcdefghijklmnopqrstuv18340";
        String input2 = "abcdefghijklmnoasdfasdpqrstuv18340";

//       ^ (каретка) - символ используется для указания начала строки. Если ^ стоит в начале регулярного выражения, то оно будет искать соответствие только в начале строки
//       Например, ^abc будет соответствовать строке только в том случае, если она начинается с "abc".
//       $ - символ используется для указания конца строки.
        String regex = "^abcdefghijklmnopqrstuv18340$";

//      Pattern - это класс в Java, который представляет собой компилированное регулярное выражение
//      compile - Компилирует заданное регулярное выражение в шаблон.
        Pattern pattern = Pattern.compile(regex);

        boolean isMatch1 = pattern.matcher(input1).matches();
        boolean isMatch2 = pattern.matcher(input2).matches();

        System.out.println("Запись 1 совпадает?: " + isMatch1);
        System.out.println("Запись 2 совпадает?: " + isMatch2);
    }

    public static void Num_3() {
        String text = "Текст с ценами в рублях: 500 RUB, 1000.75 RUB, 25 RUB.\nТекст с ценами в Евро: 10.00 EU, 15.99 EU, 5.5 EU.";

//      \\d+ - это регулярное выражение, которое соответствует одной или более цифрам в тексте
//      + - означает "один или более раз"
//      \\. - сопоставляет точку
//      {1,2} - указывает, что допустимо 1 или 2 знака после точки
//      ? - в конце делает эту часть десятичной частью числа необязательной
        Pattern patternRUB = Pattern.compile("(\\d+(\\.\\d{1,2})?) RUB");
        Pattern patternEU = Pattern.compile("(\\d+(\\.\\d{1,2})?) EU");

//      Matcher предназначен для выполнения поиска и сопоставления текста с заданным регулярным выражением
        Matcher matcherRUB = patternRUB.matcher(text);
        Matcher matcherEU = patternEU.matcher(text);

        while (matcherRUB.find() && matcherEU.find()) {
            String priceInRUB = matcherRUB.group(1);
            String priceInEU = matcherEU.group(1);
            System.out.println("Цена в RUB: " + priceInRUB + "\nЦена в EU: " + priceInEU);
        }
    }

    public static void Num_4() {
        String text = "(1 + 8) – 9 / 4";
        String regex = "\\d(?!\\+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            System.out.println("В тексте есть цифры, за которыми не стоит знак '+'");
        } else {
            System.out.println("В тексте нет цифр, за которыми не стоит знак '+'");
        }
    }

    public static void Num_5() {
        String date1 = "27/11/2004";
        String date2 = "30-04-2003";

//      (0[1-9]|[12][0-9]|3[01]): Эта часть шаблона соответствует дню месяца. Здесь допустимы дни с 01 до 31
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19\\d{2}|[2-9]\\d{3})$";

        String s1 = date1.matches(regex) ? date1 + " - корректная дата" : date1 + " - некорректная дата";
        String s2 = date2.matches(regex) ? date2 + " - корректная дата" : date2 + " - некорректная дата";

        System.out.println(s1);
        System.out.println(s2);
    }

    public static void Num_6() {
        String[] validEmails = {"user@example.com", "root@localhost"};
        String[] invalidEmails = {"myhost@@@com.ru", "@my.ru", "Julia String"};

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);

        System.out.println("Правильные адреса электронной почты:");
        for (String email : validEmails) {
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                System.out.println(email);
            }
        }

        System.out.println("\nНеправильные адреса электронной почты:");
        for (String email : invalidEmails) {
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                System.out.println(email);
            }
        }
    }

    public static void Num_7() {
        String[] validPasswords = {"F032_Password"};
        String[] invalidPasswords = {"smart_pass", "A007"};

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d_]{8,}$";
        Pattern pattern = Pattern.compile(regex);

        System.out.println("Правильные пароли:");
        for (String password : validPasswords) {
            Matcher matcher = pattern.matcher(password);
            if (matcher.matches()) {
                System.out.println(password);
            }
        }

        System.out.println("\nНеправильные пароли:");
        for (String password : invalidPasswords) {
            Matcher matcher = pattern.matcher(password);
            if (!matcher.matches()) {
                System.out.println(password);
            }
        }
    }

//    T - это обобщенный (generic) тип в Java. Обобщения (generics) позволяют создавать классы,
//    интерфейсы и методы, которые могут работать с разными типами данных,
//    сохраняя безопасность типов во время компиляции.
    public static <T> T[] filter(T[] array, Filter<T> filter) {
        List<T> resultList = new ArrayList<>();

        for (T item : array) {
            if (filter.apply(item)) {
                resultList.add(item);
            }
        }

        // Преобразуем список в массив
        return resultList.toArray(Arrays.copyOf(array, 0));

    }
    public static void Num_8() {
        String[] stringArray = {"apple", "banana", "cherry", "date", "fig"};
        Filter<String> startsWithAFilter = new Filter<>() {
            @Override
            public boolean apply(String s) {
                return s.startsWith("f");
            }
        };

        // Фильтруем строковый массив
        String[] filteredStrings = filter(stringArray, startsWithAFilter);

        // Выводим результат
        for (String str : filteredStrings) {
            System.out.println(str);
        }
    }

    public static void Num_9(){
        String text = "Текст проверка выполнения программы";

        // Удаление пробелов, знаков препинания и приведение к нижнему регистру
        text = text.replaceAll("[^а-яА-Яa-zA-Z]", "").toLowerCase();

        // Создание словаря для подсчета частоты букв

//        HashMap в Java - это коллекция, представляющая собой реализацию интерфейса Map.
//        Она предназначена для хранения пар "ключ-значение" и обеспечивает эффективное извлечение и вставку данных на основе ключей.
        Map<Character, Integer> letterFrequency = new HashMap<>();

        // Регулярное выражение для поиска букв
        Pattern pattern = Pattern.compile("[а-яА-Яa-zA-Z]");

        // Поиск букв и подсчет их частоты
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            char letter = matcher.group().charAt(0);
            letterFrequency.put(letter, letterFrequency.getOrDefault(letter, 0) + 1);
        }

        // Вывод результатов
        System.out.println("Частота букв:");
        for (Map.Entry<Character, Integer> entry : letterFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
/*
        Num_2();
        Num_3();
        Num_4();
        Num_5();
        Num_6();
        Num_7();
        Num_8();
*/
        Num_9();
    }
}