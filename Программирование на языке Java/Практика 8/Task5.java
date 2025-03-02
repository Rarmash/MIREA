public class Task5 {
    private static int recursion(int n, int sum) {
        if (n < 1) {
            return sum;
        }
        sum += n % 10;
        return recursion(n / 10, sum);
    }

    public static void main(String[] args) {
        int sum = recursion(753, 0);
        System.out.println("Сумма чисел равна " + sum);
    }
}
