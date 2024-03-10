package modules;
import modules.Shape;

public class Circle extends Shape{
    protected double radius;

    public Circle(){};
    public Circle(double radius){
        this.radius = radius;
    }

    public Circle(double radius, String color, boolean filled){
        this.radius = radius;
        this.color = color;
        this.filled = filled;
    }

    public double getRadius(){
        return radius;
    }

    public void setRadius(double radius){
        this.radius = radius;
    }

    @Override
    public double getArea(){
        return Math.PI*radius*radius;
    }

    @Override
    public double getPerimeter(){
        return Math.PI*2*radius;
    }

    public String toString(){
        return "Filled - " + this.filled + ", color - " + this.color + ", radius - " + this.radius;
    }

}