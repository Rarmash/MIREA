public class PhoneNumber {
    public static String convertNumber(String phoneNumber) {
        // Убираем все пробелы и знаки минуса из исходного номера
        phoneNumber = phoneNumber.replaceAll("[-\\s]+", "");


        // Проверяем, начинается ли строка с символа '+'
        if (phoneNumber.startsWith("+")) {
            // Формат +<Код страны><Номер 10 цифр>
            if (phoneNumber.length() == 12 && phoneNumber.substring(1).matches("\\d+")) {
                String countryCode = phoneNumber.substring(1, 3);
                String numberPart = phoneNumber.substring(3);
                return "+" + countryCode + "-" + numberPart.substring(0, 3) + "-" + numberPart.substring(3, 6) + "-" + numberPart.substring(6);
            }
            // "+104289652211"
            else if (phoneNumber.length() == 13) {
                String countryCode = phoneNumber.substring(1, 3);
                String numberPart = phoneNumber.substring(3);
                return "+" + countryCode + "-" + numberPart.substring(0, 3) + "-" + numberPart.substring(3, 6) + "-" + numberPart.substring(6);
            }
        } else if (phoneNumber.startsWith("8") && phoneNumber.length() == 11 && phoneNumber.substring(1).matches("\\d+")) {
            // Формат 8<Номер 10 цифр> для России
            String numberPart = phoneNumber.substring(1);
            return "+7-" + numberPart.substring(0, 3) + "-" + numberPart.substring(3, 6) + "-" + numberPart.substring(6);
        }

        // Если номер не подходит ни под один из форматов, возвращаем исходную строку
        return phoneNumber;
    }
}