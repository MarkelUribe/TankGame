package exec;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import static model.MyPoint.tiroHit;

@SuppressWarnings("serial")

public class TankGameMain extends JPanel {

    private static Map map1 = new Map(42, 30, 20);
    private static Tank t = new Tank(map1);
    private static int timer = 60;
    private boolean gridOn = false;

    private static ArrayList<Pared> paretak = Pared.InicializePared(map1, 15);

    private static boolean t1Tiro = false;
    private static boolean t2Tiro = false;

    private static boolean t1TiroDraw = false;
    private static boolean t2TiroDraw = false;

    private static boolean t1TiroCool = false;
    private static boolean t2TiroCool = false;

    private static boolean t1MoveCool = false;
    private static boolean t2MoveCool = false;

    private static MyPoint t1TiroPos = new MyPoint(-100, -100);
    private static MyPoint t2TiroPos = new MyPoint(-99, -99);

    private static int t1TiroDir;
    private static int t2TiroDir;

    private static String t1MovCode = "";
    private static String t2MovCode = "";

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        t.TankeakMarraztu(g2d, map1);
        Pared.marraztuPared(g2d, map1, paretak);
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
        JProgressBar progressBarT1 = new JProgressBar();
        JProgressBar progressBarT2 = new JProgressBar();
        frame.setSize((map1.getDimension().getX() + 1) * map1.getGrid(),
                (map1.getDimension().getY() + 5) * map1.getGrid());
        frame.setBackground(Color.BLACK);
        frame.add(new TankGameMain());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        

        // TIMER
        int T2MoveCicle = 0;
        int T1MoveCicle = 0;
        int T1TiroCicle = 0;
        int T2TiroCicle = 0;
        while (true) {

            if (map1.getDimension().getX() % 2 == 0) {
                progressBarT1.setValue(t.getHP1());
                progressBarT1.setBounds(((map1.getDimension().getX() / 2) + 1) * map1.getGrid(), ((map1.getDimension().getY() + 1) * map1.getGrid()), ((map1.getDimension().getX() / 2) - 2) * map1.getGrid(), 2 * map1.getGrid());
                frame.add(progressBarT1);
                progressBarT1.setForeground(Color.ORANGE);

                progressBarT2.setValue(t.getHP2());
                progressBarT2.setBounds(1 * map1.getGrid(), ((map1.getDimension().getY() + 1) * map1.getGrid()), ((map1.getDimension().getX() / 2) - 2) * map1.getGrid(), 2 * map1.getGrid());
                frame.add(progressBarT2);
                progressBarT2.setForeground(Color.BLUE);
            } else {
                progressBarT1.setValue(t.getHP1());
                progressBarT1.setBounds(((map1.getDimension().getX() / 2) + 2) * map1.getGrid(), ((map1.getDimension().getY() + 1) * map1.getGrid()), ((map1.getDimension().getX() / 2) - 2) * map1.getGrid(), 2 * map1.getGrid());
                frame.add(progressBarT1);
                progressBarT1.setForeground(Color.ORANGE);

                progressBarT2.setValue(t.getHP2());
                progressBarT2.setBounds(1 * map1.getGrid(), ((map1.getDimension().getY() + 1) * map1.getGrid()), ((map1.getDimension().getX() / 2) - 2) * map1.getGrid(), 2 * map1.getGrid());
                frame.add(progressBarT2);
                progressBarT2.setForeground(Color.BLUE);
            }

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
            if (t1MoveCool == false) {
                //T1
                if (t1MovCode == "a" && t.getT1d() == 9 && t.getT1X() - 1 >= 0) {
                    t1MoveCool = true;
                    System.out.println("T1Ezkerra");
                    t.setT1Position(t.getT1X() - 1, t.getT1Y());
                    t1MovCode = "";
                } else if (t1MovCode == "d" && t.getT1d() == 3 && t.getT1X() + 1 < map1.getDimension().getX()) {
                    t1MoveCool = true;
                    t.setT1Position(t.getT1X() + 1, t.getT1Y());
                    System.out.println("T1Eskubi");
                    t1MovCode = "";
                } else if (t1MovCode == "w" && t.getT1d() == 12 && t.getT1Y() - 1 >= 0) {
                    t1MoveCool = true;
                    t.setT1Position(t.getT1X(), t.getT1Y() - 1);
                    System.out.println("T1Gora");
                    t1MovCode = "";
                } else if (t1MovCode == "s" && t.getT1d() == 6 && t.getT1Y() + 1 < map1.getDimension().getY()) {
                    t1MoveCool = true;
                    t.setT1Position(t.getT1X(), t.getT1Y() + 1);
                    System.out.println("T1Behera");
                    t1MovCode = "";
                }
            }

            if (t2MoveCool == false) {
                //T2
                if (t2MovCode == "a" && t.getT2d() == 9 && t.getT2X() - 1 >= 0) {
                    t2MoveCool = true;
                    System.out.println("T2Ezkerra");
                    t.setT2Position(t.getT2X() - 1, t.getT2Y());
                    t2MovCode = "";
                } else if (t2MovCode == "d" && t.getT2d() == 3 && t.getT2X() + 1 < map1.getDimension().getX()) {
                    t2MoveCool = true;
                    t.setT2Position(t.getT2X() + 1, t.getT2Y());
                    System.out.println("T2Eskubi");
                    t2MovCode = "";
                } else if (t2MovCode == "w" && t.getT2d() == 12 && t.getT2Y() - 1 >= 0) {
                    t2MoveCool = true;
                    t.setT2Position(t.getT2X(), t.getT2Y() - 1);
                    System.out.println("T2Gora");
                    t2MovCode = "";
                } else if (t2MovCode == "s" && t.getT2d() == 6 && t.getT2Y() + 1 < map1.getDimension().getY()) {
                    t2MoveCool = true;
                    t.setT2Position(t.getT2X(), t.getT2Y() + 1);
                    System.out.println("T2Behera");
                    t2MovCode = "";

                }
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

            if (t1MoveCool) {
                T1MoveCicle += 1;
            }
            if (T1MoveCicle == 15) {
                T1MoveCicle = 0;
                t1MoveCool = false;
            }

            if (t2MoveCool) {
                T2MoveCicle += 1;
            }
            if (T2MoveCicle == 15) {
                T2MoveCicle = 0;
                t2MoveCool = false;
            }

            //TIRO1 KALKULUA
            if (t1TiroDraw) {
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

            if (t1TiroPos.getX() < 0 || t1TiroPos.getX() >= map1.getDimension().getX()
                    || t1TiroPos.getY() < 0 || t1TiroPos.getY() >= map1.getDimension().getY()) {
                t1TiroDraw = false;
                t1TiroPos = new MyPoint(-100, -100);
            }

            //TIRO2 KALKULUA
            if (t2TiroDraw) {
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
                t2TiroPos = new MyPoint(-99, -99);
            }

            //KOLISIOAK
            if (t.getTank1().equals(t.getTank2())) {
                System.out.println("KOLISIOA!!");
                t.setHP1(t.getHP1() - t.getDMG2());
                t.setHP2(t.getHP2() - t.getDMG1());
            }

            if (tiroHit(t1TiroPos, t2TiroPos, t1TiroDir)) {
                System.out.println("Balek elkarren aurka jo dute!");
                t1TiroDraw = false;
                t2TiroDraw = false;
                t1TiroPos = new MyPoint(-100, -100);
                t2TiroPos = new MyPoint(-99, -99);
            }
            //T1
            if (t1TiroPos.equals(t.getTank2())) {
                System.out.println("Tank1-ek Tank2-ri jo dio");
                t1TiroDraw = false;
                t.setHP1(t.getHP1() - t.getDMG2());
                t1TiroPos = new MyPoint(-100, -100);

            }
            //T2
            if (t2TiroPos.equals(t.getTank1())) {
                System.out.println("Tank2-k Tank1-i jo dio");
                t2TiroDraw = false;
                t.setHP2(t.getHP2() - t.getDMG1());
                t2TiroPos = new MyPoint(-99, -99);
            }

            if (t.getHP1() == 0) {
                int option;
                option = JOptionPane.showConfirmDialog(null, "Try Again?", "Tank 2 Wins!", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    t.setHP1(100);
                    t.setHP2(100);
                    t.setT1Position(1,1);
                    t.setT2Position(map1.getDimension().getX() - 2, map1.getDimension().getY() - 2);
                }
                if (option == 1) {
                    System.exit(0);
                }
            }

            if (t.getHP2() == 0) {
                int option;
                option = JOptionPane.showConfirmDialog(null, "Try Again?", "Tank 2 Wins!", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    t.setHP1(100);
                    t.setHP2(100);
                    t.setT1Position(1,1);
                    t.setT2Position(map1.getDimension().getX() - 2, map1.getDimension().getY() - 2);
                }
                if (option == 1) {
                    System.exit(0);
                }
            }

            if (t2TiroPos.equals(paretak)) {
                t2TiroDraw = false;
                t2TiroPos = new MyPoint(-100, -100);
            }

            if (t1TiroPos.equals(paretak)) {
                t1TiroDraw = false;
                t1TiroPos = new MyPoint(-99, -99);
            }

            if (t.getTank1().equals(paretak)) {

                //tank1
                if (t.getT1d() == 3) {
                    t.setT1Position(t.getT1X() - 1, t.getT1Y());

                }
                if (t.getT1d() == 6) {
                    t.setT1Position(t.getT1X(), t.getT1Y() - 1);

                }

                if (t.getT1d() == 9) {
                    t.setT1Position(t.getT1X() + 1, t.getT1Y());

                }

                if (t.getT1d() == 12) {
                    t.setT1Position(t.getT1X(), t.getT1Y() + 1);

                }
            }

            //tank2
            if (t.getTank2().equals(paretak)) {

                if (t.getT2d() == 3) {
                    t.setT2Position(t.getT2X() - 1, t.getT2Y());
                }
                if (t.getT2d() == 6) {
                    t.setT2Position(t.getT2X(), t.getT2Y() - 1);
                }

                if (t.getT2d() == 9) {
                    t.setT2Position(t.getT2X() + 1, t.getT2Y());
                }

                if (t.getT2d() == 12) {
                    t.setT2Position(t.getT2X(), t.getT2Y() + 1);

                }
            }

            frame.repaint();
            Thread.sleep(1000 / timer);
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
