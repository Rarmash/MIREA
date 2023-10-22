public class Task1 {
    private static void recursion(int k, int n) {
        if (k == n + 1) {
            return;
        }

        for (int i = 0; i < k; i++) {
            System.out.print(k + " ");
        }
        recursion(k + 1, n);
    }

    public static void main(String[] args) {
        recursion(1, 10);
    }
}