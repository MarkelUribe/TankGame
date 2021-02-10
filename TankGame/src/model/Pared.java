package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Pared {

    private static MyRectangle pareta;

    public Pared(Map m) {
        pareta = new MyRectangle((int) (Math.random() * (m.getDimension().getX() - 1 + 1)), (int) (Math.random() * ((m.getDimension().getY() - 1) - 1 + 1)), 1, 1);
    }

    public int getPX() {
        return pareta.getTopLeft().getX();
    }

    public int getPY() {
        return pareta.getTopLeft().getY();
    }

    public MyPoint getPared() {
        return pareta.getTopLeft();
    }

    public static void marraztuPared(Graphics2D g, Map m, ArrayList<Pared> paretak) {
        for (int i = 0; i < paretak.size(); i++) {
            g.setColor(Color.WHITE);
            g.fillRect(pareta.getTopLeft().getX() * m.getGrid(), pareta.getTopLeft().getY() * m.getGrid(),
                    pareta.getBottomRight().getX() * m.getGrid(), pareta.getBottomRight().getY() * m.getGrid());
        }
    }

}
