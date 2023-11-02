public class AddressTest {
    public static void main(String[] args) {
        String input1 = "Россия,Московская область,Реутов,ул Советская,37,-,110";
        String input2 = "США;Калифорния.Лос-Анджелес;Sunset Boulevard;123;A;456";
        String input3 = "Канада.Альберта;Калгари.Даунтаун.5.Б.7";
        String input4 = "Великобритания;Англия,Лондон,Оксфорд стрит,25,,1001";

        Address address1 = new Address(input1, "[,.;]");
        Address address2 = new Address(input2, "[,.;]");
        Address address3 = new Address(input3, "[,.;]");
        Address address4 = new Address(input4, "[,.;]");

        System.out.println(address1);
        System.out.println(address2);
        System.out.println(address3);
        System.out.println(address4);
    }
}