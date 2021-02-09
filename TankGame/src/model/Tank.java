package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;

public class Tank {

    private MyRectangle tank1;
    private MyRectangle tank2;
    private int t1d = 6; //Erlojuko zenbakiekin bezala
    private int t2d = 12;

    public Tank(Map m) {
        tank1 = new MyRectangle(1, 1, 1, 1);
        tank2 = new MyRectangle(m.getDimension().getX() - 2, m.getDimension().getY() - 2, 1, 1);
    }

    public void setT1d(int t1d) {
        this.t1d = t1d;
    }

    public void setT2d(int t2d) {
        this.t2d = t2d;
    }

    public int getT1d() {
        return t1d;
    }

    public int getT2d() {
        return t2d;
    }

    public MyPoint getTank1() {
        return tank1.getTopLeft();
    }

    public MyPoint getTank2() {
        return tank2.getTopLeft();
    }

    public void setTank1(MyRectangle tank1) {
        this.tank1 = tank1;
    }

    public void setTank2(MyRectangle tank2) {
        this.tank2 = tank2;
    }

    public void setT1Position(int x, int y) {
        tank1 = new MyRectangle(x, y, 1, 1);
    }

    public void setT2Position(int x, int y) {
        tank2 = new MyRectangle(x, y, 1, 1);
    }

    public int getT1X() {
        return tank1.getTopLeft().getX();
    }

    public int getT1Y() {
        return tank1.getTopLeft().getY();
    }

    public int getT2X() {
        return tank2.getTopLeft().getX();
    }

    public int getT2Y() {
        return tank2.getTopLeft().getY();
    }
    

    public void Tank1Tiro(Graphics2D g, Map m, boolean draw, MyPoint position, int direction) {
        int tamaina = ((m.getGrid() / 5) * 2);
        MyPoint bt = tank1.getBottomRight();
        g.setColor(Color.BLUE);
        if (draw) {
            if (direction == 3) {
                g.fillRect((position.getX()) * m.getGrid(), position.getY() * m.getGrid() + tamaina,
                        bt.getX() * m.getGrid(), bt.getY() * m.getGrid() / 5);
            } else if (direction == 6) {
                g.fillRect(position.getX() * m.getGrid() + tamaina, (position.getY()) * m.getGrid(),
                        bt.getX() * m.getGrid() / 5, bt.getY() * m.getGrid());
            } else if (direction == 9) {
                g.fillRect((position.getX()) * m.getGrid(), position.getY() * m.getGrid() + tamaina,
                        bt.getX() * m.getGrid(), bt.getY() * m.getGrid() / 5);
            } else if (direction == 12) {
                g.fillRect(position.getX() * m.getGrid() + tamaina, (position.getY()) * m.getGrid(),
                        bt.getX() * m.getGrid() / 5, bt.getY() * m.getGrid());
            }
        }

    }
    
    public void Tank2Tiro(Graphics2D g, Map m, boolean draw, MyPoint position, int direction) {
        int tamaina = ((m.getGrid() / 5) * 2);
        MyPoint bt = tank2.getBottomRight();
        g.setColor(Color.ORANGE);
        if (draw) {
            if (direction == 3) {
                g.fillRect((position.getX()) * m.getGrid(), position.getY() * m.getGrid() + tamaina,
                        bt.getX() * m.getGrid(), bt.getY() * m.getGrid() / 5);
            } else if (direction == 6) {
                g.fillRect(position.getX() * m.getGrid() + tamaina, (position.getY()) * m.getGrid(),
                        bt.getX() * m.getGrid() / 5, bt.getY() * m.getGrid());
            } else if (direction == 9) {
                g.fillRect((position.getX()) * m.getGrid(), position.getY() * m.getGrid() + tamaina,
                        bt.getX() * m.getGrid(), bt.getY() * m.getGrid() / 5);
            } else if (direction == 12) {
                g.fillRect(position.getX() * m.getGrid() + tamaina, (position.getY()) * m.getGrid(),
                        bt.getX() * m.getGrid() / 5, bt.getY() * m.getGrid());
            }
        }

    }

    public void TankeakMarraztu(Graphics2D g, Map m) {
        int tamaina = ((m.getGrid() / 5) * 2);
        //1.Tankea
        g.setColor(Color.BLUE);
        g.fillRect(tank1.getTopLeft().getX() * m.getGrid(), tank1.getTopLeft().getY() * m.getGrid(),
                tank1.getBottomRight().getX() * m.getGrid(), tank1.getBottomRight().getY() * m.getGrid());
        
        //pistola1
        if (t1d == 3) {
            g.fillRect((tank1.getTopLeft().getX() + 1) * m.getGrid(), tank1.getTopLeft().getY() * m.getGrid() + tamaina,
                    tank1.getBottomRight().getX() * m.getGrid(), tank1.getBottomRight().getY() * m.getGrid() / 5);
        }

        if (t1d == 6) {
            g.fillRect(tank1.getTopLeft().getX() * m.getGrid() + tamaina, (tank1.getTopLeft().getY() + 1) * m.getGrid(),
                    tank1.getBottomRight().getX() * m.getGrid() / 5, tank1.getBottomRight().getY() * m.getGrid());
        }

        if (t1d == 9) {
            g.fillRect((tank1.getTopLeft().getX() - 1) * m.getGrid(), tank1.getTopLeft().getY() * m.getGrid() + tamaina,
                    tank1.getBottomRight().getX() * m.getGrid(), tank1.getBottomRight().getY() * m.getGrid() / 5);
        }

        if (t1d == 12) {
            g.fillRect(tank1.getTopLeft().getX() * m.getGrid() + tamaina, (tank1.getTopLeft().getY() - 1) * m.getGrid(),
                    tank1.getBottomRight().getX() * m.getGrid() / 5, tank1.getBottomRight().getY() * m.getGrid());
        }

        //2.Tankea
        g.setColor(Color.ORANGE);
        g.fillRect(tank2.getTopLeft().getX() * m.getGrid(), tank2.getTopLeft().getY() * m.getGrid(),
                tank2.getBottomRight().getX() * m.getGrid(), tank2.getBottomRight().getY() * m.getGrid());

        //pistola2
        if (t2d == 3) {
            g.fillRect((tank2.getTopLeft().getX() + 1) * m.getGrid(), tank2.getTopLeft().getY() * m.getGrid() + tamaina,
                    tank2.getBottomRight().getX() * m.getGrid(), tank2.getBottomRight().getY() * m.getGrid() / 5);
        }

        if (t2d == 6) {
            g.fillRect(tank2.getTopLeft().getX() * m.getGrid() + tamaina, (tank2.getTopLeft().getY() + 1) * m.getGrid(),
                    tank2.getBottomRight().getX() * m.getGrid() / 5, tank2.getBottomRight().getY() * m.getGrid());
        }

        if (t2d == 9) {
            g.fillRect((tank2.getTopLeft().getX() - 1) * m.getGrid(), tank2.getTopLeft().getY() * m.getGrid() + tamaina,
                    tank2.getBottomRight().getX() * m.getGrid(), tank2.getBottomRight().getY() * m.getGrid() / 5);
        }

        if (t2d == 12) {
            g.fillRect(tank2.getTopLeft().getX() * m.getGrid() + tamaina, (tank2.getTopLeft().getY() - 1) * m.getGrid(),
                    tank2.getBottomRight().getX() * m.getGrid() / 5, tank2.getBottomRight().getY() * m.getGrid());
        }
    }

}
