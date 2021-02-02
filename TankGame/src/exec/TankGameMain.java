package exec;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.*;

@SuppressWarnings("serial")

public class TankGameMain extends JPanel {

    private static Map map1 = new Map(40, 30);
    private Tank t = new Tank();
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        
       
        g2d.setColor(Color.WHITE);
        t.TankeakMarraztu(g2d);
        map1.drawGrid(g2d, false);

    }
    
    
    public static void main(String[] args) throws InterruptedException {
        /*
            JFramearen konfigurazioa
         */
        JFrame frame = new JFrame("TankGame");
        
        frame.setSize(map1.getDimension().getX() * map1.getGrid() + map1.getGrid()-3,
                map1.getDimension().getY() * map1.getGrid() +(map1.getGrid()*2));
        frame.add(new TankGameMain());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        
        
        
        while (true) {
            Thread.sleep(1000);
            
                    
            
                    
            
            
            frame.repaint();
        }
        
    }
}