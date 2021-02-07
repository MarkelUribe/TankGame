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
    private static Tank t = new Tank();
    private static int timer = 16;
    private boolean gridon = false;

    private static boolean t1Mov = false;
    private static boolean t2Mov = false;

    private static boolean t1Tiro = false;
    private static boolean t2Tiro = false;

    private static String t1MovCode = "";
    private static String t2MovCode = "";

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        t.TankeakMarraztu(g2d);
        map1.drawGrid(g2d, gridon); //True to display the grid

    }

    public TankGameMain() {
        KeyListener listener = new MyKeyListener();
        addKeyListener(listener);
        setFocusable(true);
    }

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("TankGame");

        frame.setSize(map1.getDimension().getX() * map1.getGrid() + (map1.getGrid()),
                map1.getDimension().getY() * map1.getGrid() + (map1.getGrid() * 3) - map1.getGrid() / 2);
        frame.add(new TankGameMain());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);

        // TIMER
        int movementcicle = 0;
        int tirocicle = 0;
        while (true) {
            frame.repaint();
            Thread.sleep(timer);

            //MUGIMENDUA
            if (movementcicle == 15) {
                if (t1Mov == true && t1MovCode == "a" && t.getT1d() == 9 && t.getT1X() - 1 >= 0) {
                    System.out.println("T1Ezkerra");
                    t.setT1Position(t.getT1X() - 1, t.getT1Y());
                } else if (t1Mov == true && t1MovCode == "d" && t.getT1d() == 3 && t.getT1X() + 1 < map1.getDimension().getX()) {
                    t.setT1Position(t.getT1X() + 1, t.getT1Y());
                    System.out.println("T1Eskubi");
                } else if (t1Mov == true && t1MovCode == "w" && t.getT1d() == 12 && t.getT1Y() - 1 >= 0) {
                    t.setT1Position(t.getT1X(), t.getT1Y() - 1);
                    System.out.println("T1Gora");
                } else if (t1Mov == true && t1MovCode == "s" && t.getT1d() == 6 && t.getT1Y() + 1 < map1.getDimension().getY()) {
                    t.setT1Position(t.getT1X(), t.getT1Y() + 1);
                    System.out.println("T1Behera");
                }

                t1Mov = false;
                t2Mov = false;
                t1Tiro = false;
                t2Tiro = false;
                movementcicle = 0;
            }

            //NORABIDEA
            if (t1MovCode == "a") {
                t.setT1d(9);
            }
            if (t1MovCode == "d") {
                t.setT1d(3);
            }
            if (t1MovCode == "w") {
                t.setT1d(12);
            }
            if (t1MovCode == "s") {
                t.setT1d(6);
            }

            //TIRO
            if (tirocicle == 60) {

            }

            tirocicle += 1;
            movementcicle += 1;
        }

    }

    public class MyKeyListener
            implements KeyListener {

        public void keyPressed(KeyEvent e) {
            Integer key = e.getKeyCode();
            //T1 KONTROLAK
            //T1Ezkerra
            if (key == KeyEvent.VK_A) {
                t1Mov = true;
                t1MovCode = "a";
            }
            //T1Eskubi
            if (key == KeyEvent.VK_D) {
                t1Mov = true;
                t1MovCode = "d";
            }
            //T1Gora
            if (key == KeyEvent.VK_W) {
                t1Mov = true;
                t1MovCode = "w";
            }
            //T1Behera
            if (key == KeyEvent.VK_S) {
                t1Mov = true;
                t1MovCode = "s";
            }
            //T1Tiro
            if (key == KeyEvent.VK_SHIFT) {
                System.out.println("T1Tiro");
            }

            //T2 KONTROLAK
            //T2Ezkerra
            if (key == KeyEvent.VK_LEFT) {
                System.out.println("T2Ezkerra");
                t.setT2Position(t.getT2X() - 1, t.getT2Y());
            }
            //T2Eskubi
            if (key == KeyEvent.VK_RIGHT) {
                t.setT2Position(t.getT2X() + 1, t.getT2Y());
                System.out.println("T2Eskubi");
            }
            //T2Gora
            if (key == KeyEvent.VK_UP) {
                t.setT2Position(t.getT2X(), t.getT2Y() - 1);
                System.out.println("T2Gora");
            }
            //T2Behera
            if (key == KeyEvent.VK_DOWN) {
                t.setT2Position(t.getT2X(), t.getT2Y() + 1);
                System.out.println("T2Behera");
            }
            //T2Tiro
            if (key == KeyEvent.VK_CONTROL) {
                System.out.println("T2Tiro");
            }

            //GRIDON
            if (key == KeyEvent.VK_G) {
                if (gridon) {
                    gridon = false;
                    System.out.println("Grid set to OFF");
                } else {
                    gridon = true;
                    System.out.println("Grid set to ON");
                }
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
