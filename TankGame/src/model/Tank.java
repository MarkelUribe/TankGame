package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;

public class Tank {

    private MyRectangle tank1 = new MyRectangle(4, 4, 1, 1);
    private MyRectangle tank2 = new MyRectangle(38, 28, 1, 1);
    private int t1d = 6; //Erlojuko zenbakiekin bezala
    private int t2d = 9;

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

    public MyRectangle getTank1() {
        return tank1;
    }

    public MyRectangle getTank2() {
        return tank2;
    }

    public void setTank1(MyRectangle tank1) {
        this.tank1 = tank1;
    }

    public void setTank2(MyRectangle tank2) {
        this.tank2 = tank2;
    }

    public void TankeakMarraztu(Graphics2D g) {
        Map m = new Map(1, 1);
        //1.Tankea
        g.fillRect(tank1.getTopLeft().getX() * m.getGrid(), tank1.getTopLeft().getY() * m.getGrid(),
                tank1.getBottomRight().getX() * m.getGrid(), tank1.getBottomRight().getY() * m.getGrid());
        

        //2.Tankea
        g.fillRect(tank2.getTopLeft().getX() * m.getGrid(), tank2.getTopLeft().getY() * m.getGrid(),
                tank2.getBottomRight().getX() * m.getGrid(), tank2.getBottomRight().getY() * m.getGrid());
        
        
        if(t1d == 3){
        g.fillRect((tank1.getTopLeft().getX()+1) * m.getGrid(), tank1.getTopLeft().getY() * m.getGrid(),
                tank1.getBottomRight().getX() * m.getGrid(), tank1.getBottomRight().getY() * m.getGrid()/5);
        }
        
        if(t1d == 6){
        g.fillRect(tank1.getTopLeft().getX() * m.getGrid()+ ((m.getGrid()/5)*2), (tank1.getTopLeft().getY()+1) * m.getGrid(),
                tank1.getBottomRight().getX() * m.getGrid()/5, tank1.getBottomRight().getY() * m.getGrid());
        }
        
        if(t1d == 9){
        g.fillRect((tank1.getTopLeft().getX()-1) * m.getGrid(), tank1.getTopLeft().getY() * m.getGrid(),
                tank1.getBottomRight().getX() * m.getGrid(), tank1.getBottomRight().getY() * m.getGrid()/5);
        }
        
        if(t1d == 12){
        g.fillRect(tank1.getTopLeft().getX() * m.getGrid(), (tank1.getTopLeft().getY()-1) * m.getGrid(),
                tank1.getBottomRight().getX() * m.getGrid()/5, tank1.getBottomRight().getY() * m.getGrid());
        }
    }

}
