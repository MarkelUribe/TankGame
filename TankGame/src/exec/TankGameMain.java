package exec;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import static model.MyPoint.tiroHit;

@SuppressWarnings("serial")

public class TankGameMain extends JPanel {

    private static Map map1 = new Map(40, 30, 20);
    private static Tank t = new Tank(map1);
    private static int timer = 60;
    private boolean gridOn = false;
    private static int randomBg = (int) Math.round(Math.random());

    private static boolean record = false;
    private static SimpleDateFormat sdf = new SimpleDateFormat("ss-mm-dd-M-yyyy");
    private static String fitxategia = sdf.format(new Date());

    private static ArrayList<Pared> paretak = Pared.InicializePared(map1, 15);

    private static boolean t1Tiro = false;
    private static boolean t2Tiro = false;

    private static boolean t1TiroDraw = false;
    private static boolean t2TiroDraw = false;

    private static JFrame frameT = new JFrame("TankGame");
    private static JFrame frameM = new JFrame("Menu");
    private static JFrame frameR = new JFrame("ReplayMenu");
    private static int option;

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

    public static void menu() {
        JPanel panelM = new JPanel();
        String BGruta;
        if (randomBg == 1) {
            BGruta = "BGblue.png";
        } else {
            BGruta = "BGOrange.png";
        }
        ImageIcon img = new ImageIcon("bg/" + BGruta);
        JLabel background = new JLabel("", img, JLabel.CENTER);

        background.setSize(500, 500);
        background.setVisible(true);
        background.setBounds(0, 0, 500, 500);
        panelM.add(background);

        JLabel title = new JLabel("TANK GAME");
        JButton hasiB = new JButton("Start Game!");
        JButton repB = new JButton("Whatch Replays!");
        hasiB.setBounds(250, 80, 180, 50);
        repB.setBounds(240, 150, 200, 50);
        title.setBounds(250, 10, 180, 50);
        hasiB.setBackground(Color.BLACK);
        repB.setBackground(Color.BLACK);
        hasiB.setForeground(Color.WHITE);
        repB.setForeground(Color.WHITE);
        title.setForeground(Color.WHITE);
        hasiB.setFont(new Font("Dialog", Font.BOLD, 20));
        repB.setFont(new Font("Dialog", Font.BOLD, 20));
        title.setFont(new Font("Dialog", Font.BOLD, 30));
        background.add(hasiB);
        background.add(repB);
        background.add(title);

        frameM.add(panelM);
        frameM.setSize(500, 550);
        frameM.setMaximumSize(new Dimension(500, 550));
        frameM.setMinimumSize(new Dimension(500, 550));
        panelM.setBackground(Color.BLACK);
        frameM.setLocationRelativeTo(null);
        frameM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameM.setVisible(true);

        hasiB.addActionListener(e -> {
            frameM.setVisible(false);
            frameT.setVisible(true);
            option = 0;
            record = true;
        });

        repB.addActionListener(e -> {
            frameM.setVisible(false);
            replayMenu();
            option = 0;
        });
    }

    public static void replayMenu() {

        frameR.setSize(500, 550);
        frameR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameR.setVisible(true);
        frameR.setMaximumSize(new Dimension(500, 550));
        frameR.setMinimumSize(new Dimension(500, 550));

        JPanel panelR = new JPanel();
        panelR.setSize(500, 550);

        JLabel cnLabel = new JLabel();
        cnLabel.setText("Click the file then change the name");
        cnLabel.setBounds(155, 400, 200, 50);

        JTextField changeName = new JTextField();
        changeName.setText("");
        changeName.setBounds(170, 450, 150, 50);

        JButton cnButton = new JButton("Change");
        cnButton.setBounds(316, 450, 80, 49);

        JButton backMenu = new JButton("Menu");
        backMenu.setBounds(10, 450, 80, 49);

        JButton playReplay = new JButton("Play");
        playReplay.setBounds(85, 450, 80, 49);

        frameR.add(changeName);
        frameR.add(playReplay);
        frameR.add(cnButton);
        frameR.add(backMenu);
        frameR.add(cnLabel);
        frameR.add(panelR);

        backMenu.addActionListener(e -> {
            frameR.setVisible(false);
            frameM.setVisible(true);
            option = 0;
        });

        ArrayList<JButton> saves = new ArrayList<>();
        File path = new File("db\\");
        File[] listOfFiles = path.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                saves.add(new JButton(listOfFiles[i].getName()));

            }
        }
        for (int i = 0; i < saves.size(); i++) {

            String buttomname = saves.get(i).getText();
            int buttomint = i;

            saves.get(buttomint).addActionListener(e -> {
                changeName.setText(saves.get(buttomint).getText());

                playReplay.addActionListener(k -> {
                    frameR.setVisible(false);
                    frameT.setVisible(true);
                    try {
                        Replay.main(null);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TankGameMain.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(TankGameMain.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(TankGameMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    option = 0;
                    Replay.fitxategia =  saves.get(buttomint).getText();
                });

                cnButton.addActionListener(j -> {
                    Path source = Paths.get("db\\" + buttomname);
                    try {
                        saves.get(buttomint).setText(changeName.getText());
                        Files.move(source, source.resolveSibling(changeName.getText()), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ex) {
                        Logger.getLogger(TankGameMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            });

            panelR.add(saves.get(i));
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        menu();
        JProgressBar progressBarT1 = new JProgressBar();
        JProgressBar progressBarT2 = new JProgressBar();
        frameT.setSize((map1.getDimension().getX() + 1) * map1.getGrid(),
                (map1.getDimension().getY() + 5) * map1.getGrid());
        frameT.setBackground(Color.BLACK);
        progressBarT1.setForeground(Color.ORANGE);
        progressBarT2.setForeground(Color.BLUE);
        frameT.add(progressBarT1);
        frameT.add(progressBarT2);
        frameT.add(new TankGameMain());
        frameT.setLocationRelativeTo(null);
        frameT.setVisible(false);
        frameT.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TIMER
        int id = 0;
        int T2MoveCicle = 0;
        int T1MoveCicle = 0;
        int T1TiroCicle = 0;
        int T2TiroCicle = 0;

        ObjectOutputStream out = null;
        boolean eioasdfl = true;

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

            if (t.getHP2() == 0 && t.getHP1() == 0) {
                option = JOptionPane.showConfirmDialog(null, "Try Again?", "No one wins Tank Wins!", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    t.setHP1(100);
                    t.setHP2(100);
                    t.setT1Position(1, 1);
                    t.setT2Position(map1.getDimension().getX() - 2, map1.getDimension().getY() - 2);
                    out.close();
                    eioasdfl = true;
                    fitxategia = sdf.format(new Date());
                }
                if (option == 1) {
                    option = 2;
                    t.setHP1(100);
                    t.setHP2(100);
                    t.setT1Position(1, 1);
                    t.setT2Position(map1.getDimension().getX() - 2, map1.getDimension().getY() - 2);
                    frameM.setVisible(true);
                    frameT.setVisible(false);
                    out.close();
                    eioasdfl = true;
                    fitxategia = sdf.format(new Date());
                    record = false;
                }
            } else if (t.getHP1() == 0 && t.getHP2() != 0) {
                option = JOptionPane.showConfirmDialog(null, "Try Again?", " Blue Tank Wins!", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    t.setHP1(100);
                    t.setHP2(100);
                    t.setT1Position(1, 1);
                    t.setT2Position(map1.getDimension().getX() - 2, map1.getDimension().getY() - 2);
                    out.close();
                    eioasdfl = true;
                    fitxategia = sdf.format(new Date());
                }
                if (option == 1) {
                    option = 2;
                    t.setHP1(100);
                    t.setHP2(100);
                    t.setT1Position(1, 1);
                    t.setT2Position(map1.getDimension().getX() - 2, map1.getDimension().getY() - 2);
                    frameM.setVisible(true);
                    frameT.setVisible(false);
                    out.close();
                    eioasdfl = true;
                    fitxategia = sdf.format(new Date());
                    record = false;
                }
            } else if (t.getHP2() == 0 && t.getHP1() != 0) {
                option = JOptionPane.showConfirmDialog(null, "Try Again?", "Yellow Tank Wins!", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    t.setHP1(100);
                    t.setHP2(100);
                    t.setT1Position(1, 1);
                    t.setT2Position(map1.getDimension().getX() - 2, map1.getDimension().getY() - 2);
                    out.close();
                    eioasdfl = true;
                    fitxategia = sdf.format(new Date());
                }
                if (option == 1) {
                    option = 2;
                    t.setHP1(100);
                    t.setHP2(100);
                    t.setT1Position(1, 1);
                    t.setT2Position(map1.getDimension().getX() - 2, map1.getDimension().getY() - 2);
                    frameM.setVisible(true);
                    frameT.setVisible(false);
                    out.close();
                    eioasdfl = true;
                    fitxategia = sdf.format(new Date());
                    record = false;
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

            //BIZI BARRAK
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

            //Grabazioa
            if (record) {
                if (eioasdfl) {
                    out = PartidaGorde.idatziSortu(fitxategia);
                    eioasdfl = false;
                }
                try {

                    PartidaGorde.ticGehitu(new TicState(id, map1, t, timer, paretak,
                            t1TiroDraw, t2TiroDraw, t1TiroPos,
                            t2TiroPos, t1TiroDir, t2TiroDir), out);
                    id++;
                    if (id % 1 == 0) {
                        out.reset();
                    }
                } catch (Exception e) {
                    System.out.println("Grabatzeko arazoak");
                }
            }

            frameT.repaint();
            Thread.sleep(1000 / timer);
        }

    }

    public class MyKeyListener
            implements KeyListener {

        @Override
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
