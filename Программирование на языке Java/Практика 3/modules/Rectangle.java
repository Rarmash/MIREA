package modules;
import modules.Shape;

public class Rectangle extends Shape{
    protected double width;
    protected double length;

    public Rectangle(){};
    public Rectangle(double width, double length){
        this.width = width;
        this.length = length;
    }

    public Rectangle(double width, double length, String color, boolean filled){
        this.width = width;
        this.length = length;
        this.color = color;
        this.filled = filled;
    }

    public double getWidth(){
        return width;
    }

    public void setWidth(double width){
        this.width = width;
    }

    public double getLength(){
        return length;
    }

    public void setLength(double length){
        this.length = length;
    }

    @Override
    public double getArea(){
        return length*width;
    }

    @Override
    public double getPerimeter(){
        return 2*(length+width);
    }
    
    @Override
    public String toString(){
        return "Filled - " + this.filled + ", color - " + this.color + ", width -" + this.width +"length - " + this.length;
    }
}