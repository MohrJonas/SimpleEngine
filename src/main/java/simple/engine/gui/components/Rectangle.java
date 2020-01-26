package simple.engine.gui.components;

import java.awt.*;

public class Rectangle extends StaticWidget {

    private final Color color;

    public Rectangle(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(color);
        g.draw(rectangle);
    }
}
