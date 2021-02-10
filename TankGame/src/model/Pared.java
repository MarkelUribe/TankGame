package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Pared {

    private static MyRectangle pareta;

    public Pared(Map m) {
        pareta = new MyRectangle((int) (Math.random() * (m.getDimension().getX() - 1 + 1)), (int) (Math.random() * ((m.getDimension().getY() - 1) - 1 + 1)), 1, 1);
    }

    public Pared(MyRectangle r) {
        pareta = r;
    }

    public ArrayList<Pared> InicializePared(Map m, int zenbat) {
        ArrayList<Pared> paretak = new ArrayList<Pared>();
        for (int i = 0; i < zenbat; i++) {
            paretak.add(new Pared(new MyRectangle((int) (Math.random() * (m.getDimension().getX() - 1 + 1)), (int) (Math.random() * ((m.getDimension().getY() - 1) - 1 + 1)), 1, 1)));
        }
        return paretak;
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

    public MyPoint getParetaBottomRight() {
        return pareta.getBottomRight();
    }

    public static void marraztuPared(Graphics2D g, Map m, ArrayList<Pared> paretak) {
        for (int i = 0; i < paretak.size(); i++) {
            g.setColor(Color.WHITE);
            g.fillRect(paretak.get(i).getPared().getX() * m.getGrid(), paretak.get(i).getPared().getY() * m.getGrid(),
                    paretak.get(i).getParetaBottomRight().getX() * m.getGrid(), paretak.get(i).getParetaBottomRight().getY() * m.getGrid());
        }
    }

}
