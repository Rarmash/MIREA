import modules.Author;
import modules.Ball;

public class Test {
    public static void main(String[] args) {
        // Test Ball
        Ball ball = new Ball(100, 100);
        System.out.println(ball);
        ball.move(30, 15);
        System.out.println(ball);

        // Test Author
        Author author = new Author("Ivan Popov", "nobodysemail@nothing.com", 'M');
        System.out.println(author);
        author.setEmail("ivPopov@somewhere.com");
        System.out.println(author);
    }
}
