package Work_7;

public class MovableCircle implements Movable {
    private int radius;

    private MovablePoint center;

    public MovableCircle(int x, int y, int xSpeed, int ySpeed, int radius) {
        this.center = new MovablePoint(x, y, xSpeed, ySpeed);
        this.radius = radius;
    }

    @Override
    public void MoveUp() {
        center.MoveUp();
    }

    @Override
    public void MoveDown() {
        center.MoveDown();
    }

    @Override
    public void MoveLeft() {
        center.MoveLeft();
    }

    @Override
    public void MoveRight() {
        center.MoveRight();
    }

    @Override
    public String toString() {
        return "MovableCircle: Центр = " + this.center + ", Радиус = " + this.radius;
    }
}
