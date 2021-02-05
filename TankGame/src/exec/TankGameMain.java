package exec;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;  
import java.awt.event.KeyListener;
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
    
    public TankGameMain(){
    KeyListener listener = new MyKeyListener();
    addKeyListener(listener);
    setFocusable(true);
    }
    
    
    public static void main(String[] args) throws InterruptedException{

        JFrame frame = new JFrame("TankGame");
        TankGameMain game = new TankGameMain();
        
        frame.setSize(map1.getDimension().getX() * map1.getGrid() + (map1.getGrid()),
                map1.getDimension().getY() * map1.getGrid() +(map1.getGrid()*3)-map1.getGrid()/2);
        frame.add(new TankGameMain());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        
        // TIMER
        while (true) {
            game.repaint();
            Thread.sleep(timer);
        }
           
    }
    public class MyKeyListener 
            implements KeyListener {
        
        public void keyPressed(KeyEvent e) {
        Integer key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            System.out.println("Puta");
        }
        
        if (key == KeyEvent.VK_RIGHT) {
            t.setT1Position(t.getT1X() - 1, t.getT1Y());
        }
    }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
           
        }
        
    }
}