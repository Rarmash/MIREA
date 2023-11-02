public class Shirt {
    private final String code;
    private final String description;
    private final String color;
    private final String size;

    public Shirt(String code, String description, String color, String size) {
        this.code = code;
        this.description = description;
        this.color = color;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Code: " + code + "\nDescription: " + description + "\nColor: " + color + "\nSize: " + size;
    }
}