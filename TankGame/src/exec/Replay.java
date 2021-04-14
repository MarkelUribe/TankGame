package exec;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")

public class Replay extends JPanel {

    public static String fitxategia = "";

    private static Map map1;
    private static Tank t;
    private static int timer;

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
        t.Tank1Tiro(g2d, map1, t1TiroDraw, t1TiroPos, t1TiroDir);
        t.Tank2Tiro(g2d, map1, t2TiroDraw, t2TiroPos, t2TiroDir);

    }

    public Replay() {
        setFocusable(true);
    }

    public static void setState(TicState tic) {
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

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {

        TicState tic = null;
        FileInputStream fin = null;
        ObjectInputStream inStream = null;

        try {
            fin = new FileInputStream("db/" + fitxategia);
            inStream = new ObjectInputStream(fin);
            tic = (TicState) inStream.readObject();
            setState(tic);
        } catch (FileNotFoundException ex) {
            System.out.println("Fitxategia ez dago bere lekuan.");
        } catch (IOException ex) {
            System.out.println("Ez dago objektu gehiagorik.");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound Salbuespena gertatu da.");
        }

//        int ticId = 0;
//        TicState tic = null;
//        try {
//            tic = PartidaGorde.ticIrakurri(fitxategia, ticId);
//            setState(tic);
//        } catch (Exception e) {
//            System.out.println("Fitxategi hori ez da existitzen.");
//            System.exit(0);
//        }
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
        frame.add(new Replay());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            try {
                setState(tic = (TicState) inStream.readObject());
            } catch (FileNotFoundException ex) {
                System.out.println("Fitxategia ez dago bere lekuan.");
                System.exit(0);
            } catch (IOException ex) {
                System.out.println("Ez dago objektu gehiagorik.");
                System.exit(0);
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFound Salbuespena gertatu da.");
                System.exit(0);
            }

            if (map1.getDimension().getX() % 2 == 0) {
                progressBarT1.setValue(t.getHP1());
                progressBarT1.setBounds(((map1.getDimension().getX() / 2) + 1) * map1.getGrid(), ((map1.getDimension().getY() + 1) * map1.getGrid()), ((map1.getDimension().getX() / 2) - 2) * map1.getGrid(), 2 * map1.getGrid());

                progressBarT2.setValue(t.getHP2());
                progressBarT2.setBounds(1 * map1.getGrid(), ((map1.getDimension().getY() + 1) * map1.getGrid()), ((map1.getDimension().getX() / 2) - 2) * map1.getGrid(), 2 * map1.getGrid());

            } else {
                progressBarT1.setValue(t.getHP1());
                progressBarT1.setBounds(((map1.getDimension().getX() / 2) + 2) * map1.getGrid(), ((map1.getDimension().getY() + 1) * map1.getGrid()), ((map1.getDimension().getX() / 2) - 2) * map1.getGrid(), 2 * map1.getGrid());

                progressBarT2.setValue(t.getHP2());
                progressBarT2.setBounds(1 * map1.getGrid(), ((map1.getDimension().getY() + 1) * map1.getGrid()), ((map1.getDimension().getX() / 2) - 2) * map1.getGrid(), 2 * map1.getGrid());
            }

//            ticId ++;
            frame.repaint();
            Thread.sleep(1000 / timer);
        }

    }

}
