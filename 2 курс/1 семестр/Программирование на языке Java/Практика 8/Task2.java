public class Task2 {
    private static void counter(int k, int n) {
        if (n == k) {
            System.out.print(k + " ");
            return;
        }
        System.out.print(k + " ");
        counter(k+1, n);
    }

    public static void main(String[] args) {
        counter(1, 10);
    }
}
