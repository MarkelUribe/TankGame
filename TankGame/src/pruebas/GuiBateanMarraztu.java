
package pruebas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.*;


@SuppressWarnings("serial")

public class GuiBateanMarraztu extends JPanel {

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
                
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawString("Testu bat idazi dut", TOP_ALIGNMENT, TOP_ALIGNMENT);

        g2d.setColor(Color.RED);
        int erradioa = 20;
        g2d.fillOval(0, 0, erradioa*2, erradioa);

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawLine(100,0 ,100,300);
        

    }
    

    public GuiBateanMarraztu() {
          System.out.println("JFramea sortua, baina momentuz ez dago ikusgai.");  
    }

    public static void main(String[] args) throws InterruptedException {
        Map map1 = new Map(40, 30);
        JFrame frame = new JFrame("JFramearen Izenburua");
        frame.add(new GuiBateanMarraztu());
        frame.setSize(map1.getDimension().getX()*map1.getGrid(), map1.getDimension().getY()*map1.getGrid());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    

    }
}