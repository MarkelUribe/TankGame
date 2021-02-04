package exec;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;  
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.*;

@SuppressWarnings("serial")

public class TankGameMain extends JPanel {

    private static Map map1 = new Map(40, 30);
    private Tank t = new Tank();
    private static int timer = 16;
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        
       
        g2d.setColor(Color.WHITE);
        t.TankeakMarraztu(g2d);
        map1.drawGrid(g2d, true); //True to display the grid

    }
    
    
    public static void main(String[] args) throws InterruptedException {
        /*
            JFramearen konfigurazioa
         */
        JFrame frame = new JFrame("TankGame");
        
        frame.setSize(map1.getDimension().getX() * map1.getGrid() + (map1.getGrid()),
                map1.getDimension().getY() * map1.getGrid() +(map1.getGrid()*3)-map1.getGrid()/2);
        frame.add(new TankGameMain());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        
        // TIMER
        while (true) {
           Thread.sleep(timer);
            frame.repaint();
        }
        
        
        
    }
    public class KeyInput extends KeyAdapter { 
        
        public void keyPressed(KeyEvent e) {
        Integer key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            t.setT1Position(t.getT1X() + 1, t.getT1Y());
        }
        if (key == KeyEvent.VK_RIGHT) {
            System.out.println("Pito");
        }
        
        repaint();
    }
        
    }
}