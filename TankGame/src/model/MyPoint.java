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

    @Override
    public String toString() {
        return "(" + x + " " + y + ")";  // author.toString()
    }

    public static ArrayList<MyPoint> arrayList(int luzera) {
        ArrayList<MyPoint> puntuak = new ArrayList<MyPoint>();
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < luzera; i++) {
            System.out.println("idatzi x:");
            int x = sc.nextInt();
            System.out.println("idatzi y:");
            int y = sc.nextInt();
            MyPoint pt = new MyPoint(x, y);
            puntuak.add(pt);
        }

        MyPoint mempt = new MyPoint(0, 0);
        for (int j = 0; j < puntuak.size(); j++) {
            for (int i = 0; i < puntuak.size() - 1; i++) {
                if (puntuak.get(i).getY() < puntuak.get(i + 1).getY()) {
                    mempt = puntuak.get(i);
                    puntuak.set(i, puntuak.get(i + 1));
                    puntuak.set(i + 1, mempt);
                }
            }
        }
        return puntuak;
    }

    public static void grafikoa(ArrayList<MyPoint> puntuak) {

        System.out.println(puntuak);
        for (int y = 0; y < 10; y++) {
            System.out.print(y + " |");
            for (int x = 0; x < 10; x++) {
                MyPoint n = new MyPoint(x, y);
                boolean pointcheck = false;
                for (int i = 0; i < puntuak.size(); i++) {
                    if (puntuak.get(i).getX() == n.getX() && puntuak.get(i).getY() == n.getY()) {
                        pointcheck = true;
                    }
                }
                if (pointcheck == true) {
                    System.out.print("#  ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("   ");
        for (int i = 0; i < 10; i++) {
            System.out.print("---");
        }
        System.out.print("\n   ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + "  ");
        }
    }

    public static void ausaGrafikoa(int zenbat) {
        Random rand = new Random();
        ArrayList<MyPoint> puntuak = new ArrayList<MyPoint>();
        for (int i = 1; i <= zenbat; i++) {
            MyPoint pt = new MyPoint(rand.nextInt(10), rand.nextInt(10));
            puntuak.add(pt);
        }

        MyPoint.grafikoa(puntuak);
    }
}
