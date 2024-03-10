package Work_7;

public class MovableTest {
    public static void main(String[] args) {
        MovablePoint point = new MovablePoint(1,2,3,4);
        MovableCircle circle = new MovableCircle(5, 6, 7, 8, 9);
        MovableRectangle rect = new MovableRectangle(11, 1, 21, 11, 6, 5);

        System.out.println("Тест MovablePoint:");
        System.out.println(point);
        point.MoveUp();
        point.MoveRight();
        System.out.println(point);

        System.out.println("\nТест MovableCircle:");
        System.out.println(circle);
        circle.MoveLeft();
        System.out.println(circle);

        System.out.println("\nТест MovableRectangle:");
        rect.MoveUp();
        rect.MoveLeft();
        rect.checkSameSpeed();
        System.out.println(rect);
    }
}
