import modules.Ball;
import modules.Book;

public class Test {
    public static void main(String[] args) {
        // Test Book
        Book b1 = new Book("War and Peace", 1863);
        Book b2 = new Book("Fight Club", 1800);
        b2.setYearofRelease(1996);
        System.out.println(b1);
        System.out.println(b2);
        
        // Test Ball
        Ball ball1 = new Ball("SuperBalls", 60);
        System.out.println(ball1);
    }
}
