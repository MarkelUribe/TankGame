
package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Pared {
    private MyRectangle pared1;
    private MyRectangle pared2;
    private MyRectangle pared3;
    private MyRectangle pared4;
    
    public Pared(Map m) {
        pared1 = new MyRectangle((int)(Math.random() * (m.getDimension().getX() - 1 + 1)),(int)(Math.random() * ((m.getDimension().getY()-1) - 1 + 1)),1,1);
        pared2 = new MyRectangle((int)(Math.random() * (m.getDimension().getX() - 1 + 1)),(int)(Math.random() * ((m.getDimension().getY()-1) - 1 + 1)),1,1);
        pared3 = new MyRectangle((int)(Math.random() * (m.getDimension().getX() - 1 + 1)),(int)(Math.random() * ((m.getDimension().getY()-1) - 1 + 1)),1,1);
        pared4 = new MyRectangle((int)(Math.random() * (m.getDimension().getX() - 1 + 1)),(int)(Math.random() * ((m.getDimension().getY()-1) - 1 + 1)),1,1);
    }

    public int getP1X() {
        return pared1.getTopLeft().getX();
    }
    
    public int getP2X() {
        return pared2.getTopLeft().getX();
    }
    
    public int getP3X() {
        return pared3.getTopLeft().getX();
    }
    
    public int getP4X() {
        return pared4.getTopLeft().getX();
    }
    
    public int getP1Y() {
        return pared1.getTopLeft().getY();
    }
    
    public int getP2Y() {
        return pared2.getTopLeft().getY();
    }
    
    public int getP3Y() {
        return pared3.getTopLeft().getY();
    }
    
    public int getP4Y() {
        return pared4.getTopLeft().getY();
    }
    
    
    public MyPoint getPared1() {
        return pared1.getTopLeft();
    }

    public MyPoint getPared2() {
        return pared2.getTopLeft();
    }
    
    public MyPoint getPared3() {
        return pared3.getTopLeft();
    }

    public MyPoint getPared4() {
        return pared4.getTopLeft();
    }

    
    public void marraztuPared(Graphics2D g,Map m){
        g.setColor(Color.WHITE);
        g.fillRect(pared1.getTopLeft().getX() * m.getGrid(), pared1.getTopLeft().getY() * m.getGrid(),
            pared1.getBottomRight().getX() * m.getGrid(), pared1.getBottomRight().getY() * m.getGrid());
        
        g.setColor(Color.WHITE);
        g.fillRect(pared2.getTopLeft().getX() * m.getGrid(), pared2.getTopLeft().getY() * m.getGrid(),
            pared2.getBottomRight().getX() * m.getGrid(), pared2.getBottomRight().getY() * m.getGrid());
        
        g.setColor(Color.WHITE);
        g.fillRect(pared3.getTopLeft().getX() * m.getGrid(), pared3.getTopLeft().getY() * m.getGrid(),
            pared3.getBottomRight().getX() * m.getGrid(), pared3.getBottomRight().getY() * m.getGrid());
        
        g.setColor(Color.WHITE);
        g.fillRect(pared4.getTopLeft().getX() * m.getGrid(), pared4.getTopLeft().getY() * m.getGrid(),
            pared4.getBottomRight().getX() * m.getGrid(), pared4.getBottomRight().getY() * m.getGrid());
    }
    


}

