package src;

import java.awt.*;

public class Rectangle extends Shape {
    Rectangle(Color color, Position position, Graphics graphics) {
        super(color, position, graphics);
    }

    @Override
    public void draw() {
        int size = getPosition().getSize();
        getGraphics().drawRect(getPosition().getX(), getPosition().getY(), size, size);
        getGraphics().setColor(getColor());
        getGraphics().fillRect(getPosition().getX(), getPosition().getY(), size, size);

    }
}