package modules;
import modules.Rectangle;

public class Square extends Rectangle{
    public Square(){};
    public Square(double side){
        this.width = side;
        this.length = side;
    }

    public Square(double side, String color, boolean filled){
        this.width = side;
        this.length = side;
        this.color = color;
        this.filled = filled;
    }

    public double getSide(){
        return width;
    }

    public void setSide(double side){
        this.width = side;
        this.length = side;
    }

    @Override
    public void setWidth(double side){
        this.width = side;
    }

    @Override
    public void setLength(double side){
        this.length = side;
    }
    
    @Override
    public String toString() {
        return "Filled - " + this.filled + ", color - " + this.color + ", side - " + this.width;
    }

}