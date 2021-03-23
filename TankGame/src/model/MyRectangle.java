
package model;


import java.io.Serializable;
import model.MyPoint;

public class MyRectangle implements Serializable {
    private MyPoint topLeft;
    private MyPoint bottomRight;
    
    public MyRectangle(MyPoint topLeft, MyPoint bottomRight){
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public MyPoint getTopLeft() {
        return topLeft;
    }

    public MyPoint getBottomRight() {
        return bottomRight;
    }
    public MyRectangle(int p1, int p2,  int p3, int p4){
        this.topLeft = new MyPoint(p1, p2);
        this.bottomRight = new MyPoint(p3, p4);
    }
    public double getAzalera(){
        return Math.abs(topLeft.getX()-bottomRight.getX()) * Math.abs(topLeft.getY()-bottomRight.getY());
    }

    public double getPerimetroa(){
        return Math.abs(topLeft.getX()-bottomRight.getX())*2 + 2*Math.abs(topLeft.getY()-bottomRight.getY());
    }
    
    @Override
    public String toString() {
        return topLeft+ " " + bottomRight;  // author.toString()
    }
}
