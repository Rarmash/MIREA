public class Task3 {
    private static void recursion(int a, int b) {
        if (a < b) {
            System.out.print(a + " ");
            recursion(a+1, b);
        } else if (a > b) {
            System.out.print(a + " ");
            recursion(a-1, b);
        } else if (a == b) {
            System.out.print(a);
        }
    }

    public static void main(String[] args) {
        recursion(20, 1);
    }
}
