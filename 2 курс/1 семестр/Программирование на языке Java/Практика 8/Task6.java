public class Task6 {
    private static boolean isPrime(int n, int del) {
        if (n <= 2) {
            return (n == 2);
        }
        if (n % del == 0) {
            return false;
        }
        if (del * del > n) {
            return true;
        }
        return isPrime(n, del+1);
    }

    public static void main(String[] args) {
        if(isPrime(10, 2)){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
