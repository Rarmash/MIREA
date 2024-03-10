package Work_7;

public class MovableRectangle implements Movable{
    private MovablePoint topLeft;
    private MovablePoint bottomRight;

    public MovableRectangle(int x1, int y1, int x2, int y2, int xSpeed, int ySpeed) {
        topLeft = new MovablePoint(x1, y1, xSpeed, ySpeed);
        bottomRight = new MovablePoint(x2, y2, xSpeed, ySpeed);
    }

    @Override
    public void MoveUp() {
        topLeft.MoveUp();
        bottomRight.MoveUp();
    }

    @Override
    public void MoveDown() {
        topLeft.MoveDown();
        bottomRight.MoveDown();
    }

    @Override
    public void MoveLeft() {
        topLeft.MoveLeft();
        bottomRight.MoveLeft();
    }

    @Override
    public void MoveRight() {
        topLeft.MoveRight();
        bottomRight.MoveRight();
    }

    public void checkSameSpeed() {
        if (!(topLeft.xSpeed != bottomRight.xSpeed || topLeft.ySpeed != bottomRight.ySpeed)) {
            System.out.println("Скорости точек совпадают!");
        }
        else {
            System.out.println("Скорости точек отличаются!");
        }
    }

    public MovablePoint getTopLeft() {
        return topLeft;
    }

    @Override
    public String toString() {
        return "Изначальная позиция: " +this.topLeft+ "\nНовая позиция: " +this.bottomRight;
    }
}
