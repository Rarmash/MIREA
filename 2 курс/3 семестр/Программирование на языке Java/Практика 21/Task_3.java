public class Task_3 {
    public static <T> T getElementAtIndex(T[] array, int index) {
        if (index >= 0 && index < array.length) {
            return array[index];
        }
        return null;
    }

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] stringArray = {"apple", "banana", "cherry"};

        Integer intValue = getElementAtIndex(intArray, 2);
        String stringValue = getElementAtIndex(stringArray, 1);

        System.out.println("Integer value at index 2: " + intValue);
        System.out.println("String value at index 1: " + stringValue);
    }
}
