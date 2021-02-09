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

    private static Map map1 = new Map(40, 40, 10);
    private static Tank t = new Tank(map1);
    private static int timer = 16;
    private boolean gridOn = false;

    private static boolean t1Tiro = false;
    private static boolean t2Tiro = false;

    private static boolean t1TiroDraw = false;
    private static boolean t2TiroDraw = false;

    private static boolean t1TiroCool = false;
    private static boolean t2TiroCool = false;

    private static MyPoint t1TiroPos = new MyPoint(1, 1);
    private static MyPoint t2TiroPos = new MyPoint(0, 0);

    private static int t1TiroDir;
    private static int t2TiroDir;

    private static String t1MovCode = "";
    private static String t2MovCode = "";

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        t.TankeakMarraztu(g2d, map1);
        map1.drawGrid(g2d, gridOn); //True to display the grid
        t.Tank1Tiro(g2d, map1, t1TiroDraw, t1TiroPos, t1TiroDir);
        t.Tank2Tiro(g2d, map1, t2TiroDraw, t2TiroPos, t2TiroDir);

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
        int T1TiroCicle = 0;
        int T2TiroCicle = 0;
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

                movementcicle = 0;
            }

            //TIRO
            if (t1Tiro) {
                System.out.println("T1Tiro");
                t1TiroDraw = true;
                t1TiroPos = t.getTank1();
                t1TiroDir = t.getT1d();
                t1TiroCool = true;
                t1Tiro = false;
            }
            if (t2Tiro) {
                System.out.println("T2Tiro");
                t2TiroDraw = true;
                t2TiroPos = t.getTank2();
                t2TiroDir = t.getT2d();
                t2TiroCool = true;
                t2Tiro = false;
            }

            if (t1TiroCool) {
                T1TiroCicle += 1;
            }
            if (T1TiroCicle == 60) {
                T1TiroCicle = 0;
                t1TiroCool = false;
            }
            
            if (t2TiroCool) {
                T2TiroCicle += 1;
            }
            if (T2TiroCicle == 60) {
                T2TiroCicle = 0;
                t2TiroCool = false;
            }

            //TIRO1 KALKULUA
            if (t1TiroDraw = true) {
                if (t1TiroDir == 3) {
                    t1TiroPos = new MyPoint(t1TiroPos.getX() + 1, t1TiroPos.getY());
                }
                if (t1TiroDir == 6) {
                    t1TiroPos = new MyPoint(t1TiroPos.getX(), t1TiroPos.getY() + 1);
                }
                if (t1TiroDir == 9) {
                    t1TiroPos = new MyPoint(t1TiroPos.getX() - 1, t1TiroPos.getY());
                }
                if (t1TiroDir == 12) {
                    t1TiroPos = new MyPoint(t1TiroPos.getX(), t1TiroPos.getY() - 1);
                }
            }

            if (t1TiroPos.getX() < 0 || t1TiroPos.getX() > map1.getDimension().getX()
                    || t1TiroPos.getY() < 0 || t1TiroPos.getY() > map1.getDimension().getY()) {
                t1TiroDraw = false;
            }

            
            //TIRO2 KALKULUA
            if (t2TiroDraw = true) {
                if (t2TiroDir == 3) {
                    t2TiroPos = new MyPoint(t2TiroPos.getX() + 1, t2TiroPos.getY());
                }
                if (t2TiroDir == 6) {
                    t2TiroPos = new MyPoint(t2TiroPos.getX(), t2TiroPos.getY() + 1);
                }
                if (t2TiroDir == 9) {
                    t2TiroPos = new MyPoint(t2TiroPos.getX() - 1, t2TiroPos.getY());
                }
                if (t2TiroDir == 12) {
                    t2TiroPos = new MyPoint(t2TiroPos.getX(), t2TiroPos.getY() - 1);
                }
//                System.out.println(t2TiroPos);
            }

            if (t2TiroPos.getX() < 0 || t2TiroPos.getX() > map1.getDimension().getX()
                    || t2TiroPos.getY() < 0 || t2TiroPos.getY() > map1.getDimension().getY()) {
                t2TiroDraw = false;
            }
            
            //KOLISIOAK
            if (t.getTank1().equals(t.getTank2())) {
                System.out.println("KOLISIOA!!");
            }
            if (t1TiroPos.equals(t2TiroPos)) {
                System.out.println("Balek elkarren aurka jo dute!");
                t1TiroDraw = false;
                t2TiroDraw = false;
            }
            //T1
            if (t1TiroPos.equals(t.getTank2())) {
                System.out.println("Tank1-ek Tank2-ri jo dio");
                t1TiroDraw = false;
            }
            //T2
            if (t2TiroPos.equals(t.getTank1())) {
                System.out.println("Tank2-k Tank1-i jo dio");
                t2TiroDraw = false;
            }

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
                if (t1TiroCool == false) {
                    t1Tiro = true;
                }
            }

            //T2 KONTROLAK
            //T2Ezkerra
            if (key == KeyEvent.VK_J) {
                t2MovCode = "a";
            }
            //T2Eskubi
            if (key == KeyEvent.VK_L) {
                t2MovCode = "d";
            }
            //T2Gora
            if (key == KeyEvent.VK_I) {
                t2MovCode = "w";
            }
            //T2Behera
            if (key == KeyEvent.VK_K) {
                t2MovCode = "s";
            }
            //T2Tiro
            if (key == KeyEvent.VK_SPACE) {
                if (t2TiroCool == false) {
                    t2Tiro = true;
                }
            }

            //GRIDON
            if (key == KeyEvent.VK_G) {
                if (gridOn) {
                    gridOn = false;
                    System.out.println("Grid set to OFF");
                } else {
                    gridOn = true;
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
