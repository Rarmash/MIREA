package src;

import javax.swing.*;
import java.awt.*;

public abstract class Shape extends JComponent {
    private Color color;

    private Position position;

    private Graphics graphics;

    Shape(Color color, Position position, Graphics graphics) {
        this.color = color;
        this.position = position;
        this.graphics = graphics;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public abstract void draw();
}