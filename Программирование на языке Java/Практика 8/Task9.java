public class Task9 {
    private static int sequenceCount = 0;

    public static void generateCombinations(int a, int b, StringBuilder current) {
        if (a == 0 && b == 0) {
            if (!current.toString().contains("00")) {
                System.out.println(current);
                sequenceCount++;
            }
            return;
        }

        if (a > 0) {
            current.append('0');
            generateCombinations(a - 1, b, current);
            current.deleteCharAt(current.length() - 1);
        }

        if (b > 0) {
            current.append('1');
            generateCombinations(a, b - 1, current);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        int a = 3; // Количество нулей
        int b = 1; // Количество единиц

        generateCombinations(a, b, new StringBuilder());
        System.out.println("Количество последовательностей без двух нулей: " + sequenceCount);
    }
}
