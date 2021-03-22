package model;

import exec.*;
import java.io.Serializable;
import java.util.ArrayList;

public class TicState implements Serializable{
    private static Map map1;
    private static Tank t = new Tank(map1);
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

    public TicState(Map m, Tank t, int timer, ArrayList<Pared> paretak,
                boolean t1TiroDraw, boolean t2TiroDraw, MyPoint t1TiroPos, 
                MyPoint t2TiroPos, int t1TiroDir, int t2TiroDir) {
        map1 = m;
        t = t;
        this.timer = timer;
        this.paretak = paretak;
        this.t1TiroDraw = t1TiroDraw;
        this.t2TiroDraw = t2TiroDraw;
        this.t1TiroPos = t1TiroPos;
        this.t2TiroPos = t2TiroPos;
        this.t1TiroDir = t1TiroDir;
        this.t2TiroDir = t2TiroDir;
    }

    public static void setMap1(Map map1) {
        TicState.map1 = map1;
    }

    public static void setT(Tank t) {
        TicState.t = t;
    }

    public static void setTimer(int timer) {
        TicState.timer = timer;
    }

    public void setGridOn(boolean gridOn) {
        this.gridOn = gridOn;
    }

    public static void setParetak(ArrayList<Pared> paretak) {
        TicState.paretak = paretak;
    }

    public static void setT1Tiro(boolean t1Tiro) {
        TicState.t1Tiro = t1Tiro;
    }

    public static void setT2Tiro(boolean t2Tiro) {
        TicState.t2Tiro = t2Tiro;
    }

    public static void setT1TiroDraw(boolean t1TiroDraw) {
        TicState.t1TiroDraw = t1TiroDraw;
    }

    public static void setT2TiroDraw(boolean t2TiroDraw) {
        TicState.t2TiroDraw = t2TiroDraw;
    }

    public static void setT1TiroPos(MyPoint t1TiroPos) {
        TicState.t1TiroPos = t1TiroPos;
    }

    public static void setT2TiroPos(MyPoint t2TiroPos) {
        TicState.t2TiroPos = t2TiroPos;
    }

    public static void setT1TiroDir(int t1TiroDir) {
        TicState.t1TiroDir = t1TiroDir;
    }

    public static void setT2TiroDir(int t2TiroDir) {
        TicState.t2TiroDir = t2TiroDir;
    }

    public static Map getMap1() {
        return map1;
    }

    public static Tank getT() {
        return t;
    }

    public static int getTimer() {
        return timer;
    }

    public boolean isGridOn() {
        return gridOn;
    }

    public static ArrayList<Pared> getParetak() {
        return paretak;
    }

    public static boolean isT1Tiro() {
        return t1Tiro;
    }

    public static boolean isT2Tiro() {
        return t2Tiro;
    }

    public static boolean isT1TiroDraw() {
        return t1TiroDraw;
    }

    public static boolean isT2TiroDraw() {
        return t2TiroDraw;
    }

    public static MyPoint getT1TiroPos() {
        return t1TiroPos;
    }

    public static MyPoint getT2TiroPos() {
        return t2TiroPos;
    }

    public static int getT1TiroDir() {
        return t1TiroDir;
    }

    public static int getT2TiroDir() {
        return t2TiroDir;
    }

    
}
