public class Circle extends Shape
{
    private double radius;

    public Circle(){
        radius = 1.0f;
        color = "RED";
        filled = false;
    }
    public Circle(double radius){
        this.radius = radius;
        color = "RED";
        filled = false;
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
    public double getArea() {
        return Math.PI * radius * radius;
    }
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    @Override
    public String toString() {
        return "Rectangle: (radius:"+radius+", filled:"+filled+", color:"+color+")";
    }
}