package src;

import java.awt.*;

public class Circle extends Shape {
    Circle(Color color, Position position, Graphics graphics) {
        super(color, position, graphics);
    }

    @Override
    public void draw() {
        int size = getPosition().getSize();
        this.getGraphics().drawOval(getPosition().getX(), getPosition().getY(), size,size);
        getGraphics().setColor(getColor());
        getGraphics().fillOval(getPosition().getX(), getPosition().getY(), size, size);
    }
}