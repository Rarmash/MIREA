import modules.Ball;
import modules.Book;

public class Test {
    public static void main(String[] args) {
        // Test Book
        Book b1 = new Book("War and Peace", 1863);
        Book b2 = new Book("Harry Potter and the Philosopher's Stone", 1800);
        b2.setYearofRelease(2000);
        System.out.println(b1);
        System.out.println(b2);
        
        // Test Ball
        Ball ball1 = new Ball("ONLYTOP", 60);
        System.out.println(ball1);
    }
}
