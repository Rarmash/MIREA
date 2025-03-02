public class Task7 {
    private static void recursion(int n, int divisor) {
        if (n <= 1) {
            return;
        }
        if (n % divisor == 0) {
            System.out.print(divisor + " ");
            recursion(n / divisor, divisor);
        } else {
            recursion(n, divisor + 1);
        }
    }

    public static void main(String[] args) {
        recursion(36, 2);
    }
}
