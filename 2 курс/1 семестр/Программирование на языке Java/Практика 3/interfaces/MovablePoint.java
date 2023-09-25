package interfaces;
import interfaces.Movable;

public class MovablePoint implements Movable{
    int x;
    int y;
    int xSpeed;
    int ySpeed;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed){
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }    
    
    @Override
    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }

    @Override
    public void moveUp(){
        y += ySpeed;
    };

    @Override
    public void moveDown(){
        y -= ySpeed;
    }

    @Override
    public void moveRight(){
        x += xSpeed;
    };

    @Override
    public void moveLeft(){
        x -= xSpeed;
    }
}