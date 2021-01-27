
package pruebas;


import pruebas.MyPoint;

public class MyRectangle {
    private MyPoint topLeft;
    private MyPoint bottomRight;
    
    public MyRectangle(MyPoint topLeft, MyPoint bottomRight){
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
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
