package exec;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.*;
import java.util.ArrayList;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")

public class Replay extends JPanel {

    private static Map map1;
    private static Tank t;
    private static int timer;
    private boolean gridOn = false;

    private static ArrayList<Pared> paretak;

    private static boolean t1Tiro;
    private static boolean t2Tiro;

    private static boolean t1TiroDraw;
    private static boolean t2TiroDraw;

    private static MyPoint t1TiroPos;
    private static MyPoint t2TiroPos;

    private static int t1TiroDir;
    private static int t2TiroDir;
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        t.TankeakMarraztu(g2d, map1);
        Pared.marraztuPared(g2d, map1, paretak);
        map1.drawGrid(g2d, gridOn); //True to display the grid
        t.Tank1Tiro(g2d, map1, t1TiroDraw, t1TiroPos, t1TiroDir);
        t.Tank2Tiro(g2d, map1, t2TiroDraw, t2TiroPos, t2TiroDir);

    }

    public Replay() {
        setFocusable(true);
    }
    
    public static void setState(TicState tic){
        map1 = tic.getMap1();
        t = tic.getT();
        timer = tic.getTimer();
        paretak = tic.getParetak();
        t1Tiro = tic.isT1Tiro();
        t2Tiro = tic.isT2Tiro();
        t1TiroDraw = tic.isT1TiroDraw();
        t2TiroDraw = tic.isT2TiroDraw();
        t1TiroPos = tic.getT1TiroPos();
        t2TiroPos = tic.getT2TiroPos();
        t1TiroDir = tic.getT1TiroDir();
        t2TiroDir = tic.getT2TiroDir();
    }

    public static void main(String[] args) throws InterruptedException {
        TicState tic = PartidaGorde.ticIrakurri("1_proba.dat", 1);
        
        setState(tic);
        

        JFrame frame = new JFrame("TankGame");
        JProgressBar progressBarT1 = new JProgressBar();
        JProgressBar progressBarT2 = new JProgressBar();
        frame.setSize((map1.getDimension().getX() + 1) * map1.getGrid(),
                (map1.getDimension().getY() + 5) * map1.getGrid());

        frame.setBackground(Color.BLACK);
        progressBarT1.setForeground(Color.ORANGE);
        progressBarT2.setForeground(Color.BLUE);
        frame.add(progressBarT1);
        frame.add(progressBarT2);
        frame.add(new TankGameMain());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            t.setT1Position(t.getT1X()+1, t.getT1Y());
            tic.setT(t);
            
            setState(tic);
            
            
            frame.repaint();
            Thread.sleep(1000 / timer);
        }

    }

}
