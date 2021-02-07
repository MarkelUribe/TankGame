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

    private static Map map1 = new Map(40, 30, 30);
    private static Tank t = new Tank();
    private static int timer = 16;
    private boolean gridon = false;

    private static boolean t1Tiro = false;
    private static boolean t2Tiro = false;

    private static String t1MovCode = "";
    private static String t2MovCode = "";

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        t.TankeakMarraztu(g2d, map1);
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
            //NORABIDEA
            //T1
            if (t1MovCode == "a" && t.getT1d() != 9) {
                t.setT1d(9);
                t1MovCode = "";
            }
            if (t1MovCode == "d" && t.getT1d() != 3) {
                t.setT1d(3);
                t1MovCode = "";
            }
            if (t1MovCode == "w" && t.getT1d() != 12) {
                t.setT1d(12);
                t1MovCode = "";
            }
            if (t1MovCode == "s" && t.getT1d() != 6) {
                t.setT1d(6);
                t1MovCode = "";
            }
            //T2
            if (t2MovCode == "a" && t.getT2d() != 9) {
                t.setT2d(9);
                t2MovCode = "";
            }
            if (t2MovCode == "d" && t.getT2d() != 3) {
                t.setT2d(3);
                t2MovCode = "";
            }
            if (t2MovCode == "w" && t.getT2d() != 12) {
                t.setT2d(12);
                t2MovCode = "";
            }
            if (t2MovCode == "s" && t.getT2d() != 6) {
                t.setT2d(6);
                t2MovCode = "";
            }
            //MUGIMENDUA
            if (movementcicle == 15) {
                //T1
                if (t1MovCode == "a" && t.getT1d() == 9 && t.getT1X() - 1 >= 0) {
                    System.out.println("T1Ezkerra");
                    t.setT1Position(t.getT1X() - 1, t.getT1Y());
                    t1MovCode = "";
                } else if (t1MovCode == "d" && t.getT1d() == 3 && t.getT1X() + 1 < map1.getDimension().getX()) {
                    t.setT1Position(t.getT1X() + 1, t.getT1Y());
                    System.out.println("T1Eskubi");
                    t1MovCode = "";
                } else if (t1MovCode == "w" && t.getT1d() == 12 && t.getT1Y() - 1 >= 0) {
                    t.setT1Position(t.getT1X(), t.getT1Y() - 1);
                    System.out.println("T1Gora");
                    t1MovCode = "";
                } else if (t1MovCode == "s" && t.getT1d() == 6 && t.getT1Y() + 1 < map1.getDimension().getY()) {
                    t.setT1Position(t.getT1X(), t.getT1Y() + 1);
                    System.out.println("T1Behera");
                    t1MovCode = "";
                }
                //T2
                if (t2MovCode == "a" && t.getT2d() == 9 && t.getT2X() - 1 >= 0) {
                    System.out.println("T2Ezkerra");
                    t.setT2Position(t.getT2X() - 1, t.getT2Y());
                    t2MovCode = "";
                } else if (t2MovCode == "d" && t.getT2d() == 3 && t.getT2X() + 1 < map1.getDimension().getX()) {
                    t.setT2Position(t.getT2X() + 1, t.getT2Y());
                    System.out.println("T2Eskubi");
                    t2MovCode = "";
                } else if (t2MovCode == "w" && t.getT2d() == 12 && t.getT2Y() - 1 >= 0) {
                    t.setT2Position(t.getT2X(), t.getT2Y() - 1);
                    System.out.println("T2Gora");
                    t2MovCode = "";
                } else if (t2MovCode == "s" && t.getT2d() == 6 && t.getT2Y() + 1 < map1.getDimension().getY()) {
                    t.setT2Position(t.getT2X(), t.getT2Y() + 1);
                    System.out.println("T2Behera");
                    t2MovCode = "";
                }
                
                t1Tiro = false;
                t2Tiro = false;
                movementcicle = 0;
            }

            
            
            
            //TIRO
            if (tirocicle == 60) {

                tirocicle = 0;
            }

            //KOLISIOA// Ez dabil
            if (t.checkCollision()) {
                System.out.println("KOLISIOA!!");
            }
            
            
            tirocicle += 1;
            movementcicle += 1;

            frame.repaint();
            Thread.sleep(timer);
        }

    }

    public class MyKeyListener
            implements KeyListener {

        public void keyPressed(KeyEvent e) {
            Integer key = e.getKeyCode();
            //T1 KONTROLAK
            //T1Ezkerra
            if (key == KeyEvent.VK_A) {
                t1MovCode = "a";
            }
            //T1Eskubi
            if (key == KeyEvent.VK_D) {
                t1MovCode = "d";
            }
            //T1Gora
            if (key == KeyEvent.VK_W) {
                t1MovCode = "w";
            }
            //T1Behera
            if (key == KeyEvent.VK_S) {
                t1MovCode = "s";
            }
            //T1Tiro
            if (key == KeyEvent.VK_SHIFT) {
                System.out.println("T1Tiro");
            }

            //T2 KONTROLAK
            //T2Ezkerra
            if (key == KeyEvent.VK_LEFT) {
                t2MovCode = "a";
            }
            //T2Eskubi
            if (key == KeyEvent.VK_RIGHT) {
                t2MovCode = "d";
            }
            //T2Gora
            if (key == KeyEvent.VK_UP) {
                t2MovCode = "w";
            }
            //T2Behera
            if (key == KeyEvent.VK_DOWN) {
                t2MovCode = "s";
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
