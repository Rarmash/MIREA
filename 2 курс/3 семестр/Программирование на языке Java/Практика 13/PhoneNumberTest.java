public class PhoneNumberTest extends PhoneNumber {
    public static void main(String[] args) {
        String phoneNumber1 = "+79175655655";
        String phoneNumber2 = "+104289652211";
        String phoneNumber3 = "89175655655";
        String phoneNumber4 = "1234567890"; // Некорректный формат

        System.out.println("Конвертированный номер телефона 1: " + convertNumber(phoneNumber1));
        System.out.println("Конвертированный номер телефона 2: " + convertNumber(phoneNumber2));
        System.out.println("Конвертированный номер телефона 3: " + convertNumber(phoneNumber3));
        System.out.println("Конвертированный номер телефона 4: " + convertNumber(phoneNumber4));
    }
}