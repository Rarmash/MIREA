import java.util.StringTokenizer;

public class Address {
    private String country;
    private String region;
    private String city;
    private String street;
    private String house;
    private String building;
    private String apartment;

    public Address(String input, String delimiters) {
//        Создает строковый токенизатор для указанной строки. Символы в аргументе delim являются разделителями для разделения лексем.
//        Сами символы-разделители не будут рассматриваться как лексемы.
        StringTokenizer tokenizer = new StringTokenizer(input, delimiters);

        if (tokenizer.hasMoreTokens()) {
//         nextToken - возвращает следующий токен из данного токенизатора строк.
            this.country = tokenizer.nextToken().trim();
        }
        if (tokenizer.hasMoreTokens()) {
            this.region = tokenizer.nextToken().trim();
        }
        if (tokenizer.hasMoreTokens()) {
            this.city = tokenizer.nextToken().trim();
        }
        if (tokenizer.hasMoreTokens()) {
            this.street = tokenizer.nextToken().trim();
        }
        if (tokenizer.hasMoreTokens()) {
            this.house = tokenizer.nextToken().trim();
        }
        if (tokenizer.hasMoreTokens()) {
            this.building = tokenizer.nextToken().trim();
        }
        if (tokenizer.hasMoreTokens()) {
            this.apartment = tokenizer.nextToken().trim();
        }
    }

    @Override
    public String toString() {
        return "Address {" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", building='" + building + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}