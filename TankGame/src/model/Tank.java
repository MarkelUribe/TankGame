
package model;

import java.awt.Graphics;

public class Tank {

        private MyRectangle tank1 = new MyRectangle(1, 1, 2, 2);
        private MyRectangle tank2 = new MyRectangle(27, 26, 28, 27);

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
        
        public void TankeakMarraztu(Graphics g){
        //1.Tankea
        
        //Ezker bertikala
        g.drawLine(tank1.getTopLeft().getX(), tank1.getTopLeft().getY(), tank1.getBottomRight().getX(), tank1.getBottomRight().getY());
    }

    }