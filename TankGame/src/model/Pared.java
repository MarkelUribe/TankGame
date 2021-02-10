package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Pared {

    private static MyRectangle pareta;

    public Pared(Map m) {
        Random rnd = new Random();
        pareta = new MyRectangle(rnd.nextInt(m.getDimension().getX()), rnd.nextInt(m.getDimension().getY()), 1, 1);
    }

    public Pared(MyRectangle r) {
        pareta = r;
    }

    public static ArrayList<Pared> InicializePared(Map m, int zenbat) {
        Random rnd = new Random();
        ArrayList<Pared> paretak = new ArrayList<Pared>();
        for (int i = 0; i < zenbat; i++) {
            paretak.add(new Pared(new MyRectangle(rnd.nextInt(m.getDimension().getX()), rnd.nextInt(m.getDimension().getY()), 1, 1)));
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
