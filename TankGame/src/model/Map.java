
package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;


public class Map implements Serializable {

        private MyPoint dimension;
        private int grid = 15;

        public Map(int p1, int p2, int grid) {
            this.grid = grid;
            MyPoint point = new MyPoint(p1, p2);
            dimension = point;
        }


        public int getGrid() {
            return grid;
        }

        public void setDimension(MyPoint dimension) {
        this.dimension = dimension;
    }

    public void setGrid(int grid) {
        this.grid = grid;
    }

        public MyPoint getDimension() {
            return dimension;
        }

        @Override
        public String toString() {
            return "map{" + "XY= " + dimension.getXY() + ", grid=" + grid + '}';
        }
        
        public void drawGrid(Graphics2D g, boolean piztuta){
             g.setColor(Color.WHITE);
            if (piztuta) {
                // bertikalak
                MyPoint a = new MyPoint(1, 0);
                MyPoint b= new MyPoint(1, dimension.getY());
                for (int y = 0; y < dimension.getX(); y++) {
                    g.drawLine(a.getX()*grid, a.getY()*grid, b.getX()*grid, b.getY()*grid);
                    a.setX(a.getX()+1);
                    b.setX(b.getX()+1);
                }
                // horizontalak
                a = new MyPoint(0, 1);
                b = new MyPoint(dimension.getX(), 1);
                for (int y = 0; y < dimension.getY(); y++) {
                    g.drawLine(b.getX()*grid, b.getY()*grid, a.getX()*grid, a.getY()*grid);
                    a.setY(a.getY()+1);
                    b.setY(b.getY()+1);
                }
            }
        }
    }