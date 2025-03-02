package Task6;

public class ThrowsDemo2 {
    public static void printMessage(String key) {
        String message = getDetails(key);
        System.out.println(message);
    }

    public static String getDetails(String key) {
        if (key == null) {
            throw new NullPointerException("null key in getDetails");
        }
        return "data for " + key;
    }

    public static void main(String[] args) {
        printMessage("Hello");
        try {
            printMessage(null);
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException" + e.getMessage());
        }
    }
}
