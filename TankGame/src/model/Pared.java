package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Pared implements Serializable {

    private MyRectangle pareta;
    private static int albo1;
    private static int albo2;

    public Pared(Map m) {
        Random rnd = new Random();
        pareta = new MyRectangle(rnd.nextInt(m.getDimension().getX()), rnd.nextInt(m.getDimension().getY()), 1, 1);
    }

    public Pared(MyRectangle r) {
        Random rnd = new Random();
        pareta = r;
        albo1  = rnd.nextInt(3);
        albo2  = rnd.nextInt(3);
    }

    public static ArrayList<Pared> InicializePared(Map m, int zenbat) {
        Random rnd = new Random();
        ArrayList<Pared> paretak = new ArrayList<Pared>();
        for (int i = 0; i < zenbat; i++) {
            paretak.add(new Pared(new MyRectangle(rnd.nextInt(m.getDimension().getX()), rnd.nextInt(m.getDimension().getY()), 1, 1)));
           
            
            if (paretak.get(i).getAlbo1() == 0) {
                paretak.add(new Pared(new MyRectangle(paretak.get(i).getPared().getX(), paretak.get(i).getPared().getY()-1, 1, 1)));
            }else if (paretak.get(i).getAlbo1() == 1) {
                paretak.add(new Pared(new MyRectangle(paretak.get(i).getPared().getX() +1, paretak.get(i).getPared().getY(), 1, 1)));
            }else if (paretak.get(i).getAlbo1() == 2) {
                paretak.add(new Pared(new MyRectangle(paretak.get(i).getPared().getX(), paretak.get(i).getPared().getY()+1, 1, 1)));
            }else if (paretak.get(i).getAlbo1() == 3) {
                paretak.add(new Pared(new MyRectangle(paretak.get(i).getPared().getX() - 1, paretak.get(i).getPared().getY(), 1, 1)));
            }
            
            if (paretak.get(i).getAlbo2() == 0) {
                paretak.add(new Pared(new MyRectangle(paretak.get(i).getPared().getX(), paretak.get(i).getPared().getY()-1, 1, 1)));
            }else if (paretak.get(i).getAlbo2() == 1) {
                paretak.add(new Pared(new MyRectangle(paretak.get(i).getPared().getX() +1, paretak.get(i).getPared().getY(), 1, 1)));
            }else if (paretak.get(i).getAlbo2() == 2) {
                paretak.add(new Pared(new MyRectangle(paretak.get(i).getPared().getX(), paretak.get(i).getPared().getY()+1, 1, 1)));
            }else if (paretak.get(i).getAlbo2() == 3) {
                paretak.add(new Pared(new MyRectangle(paretak.get(i).getPared().getX() - 1, paretak.get(i).getPared().getY(), 1, 1)));
            }
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
    
    public static void marraztuPared(Graphics2D g, Map m, Pared pareta) {
            g.setColor(Color.WHITE);
            g.fillRect(pareta.getPared().getX() * m.getGrid(), pareta.getPared().getY() * m.getGrid(),
                    pareta.getParetaBottomRight().getX() * m.getGrid(), pareta.getParetaBottomRight().getY() * m.getGrid());
    }

    public static void marraztuPared(Graphics2D g, Map m, ArrayList<Pared> paretak) {
        Random rnd = new Random();
        for (int i = 0; i < paretak.size(); i++) {
            g.setColor(Color.WHITE);
            g.fillRect(paretak.get(i).getPared().getX() * m.getGrid(), paretak.get(i).getPared().getY() * m.getGrid(),
                    paretak.get(i).getParetaBottomRight().getX() * m.getGrid(), paretak.get(i).getParetaBottomRight().getY() * m.getGrid());
            
        }
    }

    public static int getAlbo1() {
        return albo1;
    }

    public static int getAlbo2() {
        return albo2;
    }


}
