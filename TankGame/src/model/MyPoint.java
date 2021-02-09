/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author uribe.markel
 */
public class MyPoint {

    private int x;
    private int y;

    public MyPoint() {
    }

    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance() {
        return Math.sqrt(this.x * this.x + this.y + this.y);
    }

    public double distance(MyPoint p) {
        return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
    }

    public double distance(int x, int y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int[] getXY() {
        int[] points = {this.x, this.y};
        return points;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean equals(MyPoint a){
        if(x == a.x && y == a.y){
            return true;
        }
        return false;
    }
    public static boolean tiroHit(MyPoint t, MyPoint b, int dir){
        if(t.equals(b)) {
            return true;
        }else if (dir == 3 || dir == 9) {
            MyPoint xt = new MyPoint(t.getX()-1, t.getY());
            MyPoint xb = new MyPoint(b.getX()-1, b.getY());
            if (xt.equals(xb)) {
                return true;
            }
        }else if(dir == 12 || dir == 6){
            MyPoint yt = new MyPoint(t.getY()-1, t.getX());
            MyPoint yb = new MyPoint(b.getY()-1, b.getX());
            if (yt.equals(yb)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + x + " " + y + ")";  // author.toString()
    }
}
