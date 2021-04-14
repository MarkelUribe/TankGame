package model;

import exec.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JProgressBar;

public class TicState implements Serializable {

    Map map1;
    Tank t;
    int timer;

    ArrayList<Pared> paretak;

    boolean t1TiroDraw;
    boolean t2TiroDraw;

    MyPoint t1TiroPos;
    MyPoint t2TiroPos;

    int t1TiroDir;
    int t2TiroDir;

    public TicState(int id, Map m, Tank t, int timer, ArrayList<Pared> paretak,
            boolean t1TiroDraw, boolean t2TiroDraw, MyPoint t1TiroPos,
            MyPoint t2TiroPos, int t1TiroDir, int t2TiroDir) {
        map1 = m;
        this.t = t;
        this.timer = timer;
        this.paretak = paretak;
        this.t1TiroDraw = t1TiroDraw;
        this.t2TiroDraw = t2TiroDraw;
        this.t1TiroPos = t1TiroPos;
        this.t2TiroPos = t2TiroPos;
        this.t1TiroDir = t1TiroDir;
        this.t2TiroDir = t2TiroDir;
    }

    public void setMap1(Map map1) {
        this.map1 = map1;
    }

    public void setT(Tank t) {
        this.t = t;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setParetak(ArrayList<Pared> paretak) {
        this.paretak = paretak;
    }

    public void setT1TiroDraw(boolean t1TiroDraw) {
        this.t1TiroDraw = t1TiroDraw;
    }

    public void setT2TiroDraw(boolean t2TiroDraw) {
        this.t2TiroDraw = t2TiroDraw;
    }

    public void setT1TiroPos(MyPoint t1TiroPos) {
        this.t1TiroPos = t1TiroPos;
    }

    public void setT2TiroPos(MyPoint t2TiroPos) {
        this.t2TiroPos = t2TiroPos;
    }

    public void setT1TiroDir(int t1TiroDir) {
        this.t1TiroDir = t1TiroDir;
    }

    public void setT2TiroDir(int t2TiroDir) {
        this.t2TiroDir = t2TiroDir;
    }

    public Map getMap1() {
        return map1;
    }

    public Tank getT() {
        return t;
    }

    public int getTimer() {
        return timer;
    }

    public ArrayList<Pared> getParetak() {
        return paretak;
    }

    public boolean isT1TiroDraw() {
        return t1TiroDraw;
    }

    public boolean isT2TiroDraw() {
        return t2TiroDraw;
    }

    public MyPoint getT1TiroPos() {
        return t1TiroPos;
    }

    public MyPoint getT2TiroPos() {
        return t2TiroPos;
    }

    public int getT1TiroDir() {
        return t1TiroDir;
    }

    public int getT2TiroDir() {
        return t2TiroDir;
    }

}
