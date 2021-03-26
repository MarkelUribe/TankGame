package model;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PartidaGorde {
    
    public static ObjectOutputStream idatziSortu(String fitxategia){
        try{
            FileOutputStream fout = new FileOutputStream("db/"+fitxategia+".dat");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            return out;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static void ticGehitu(TicState t, ObjectOutputStream out){
        try {
                out.writeObject(t);
                //closing the stream    
                //out.close();
                //System.out.println("Datuak fitxategian idatzi dira.");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
 
    public static TicState ticIrakurri(String fitxategia, int id){
        FileInputStream fin = null;
        TicState t = null;
        t.setId(-1);
        try {
            fin = new FileInputStream("db/"+fitxategia);
            ObjectInputStream inStream = new ObjectInputStream(fin);
            
            while (t.getId() != id) {
                t = (TicState) inStream.readObject();
                if(t.getId() == id){
                    return t; 
                }
               
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fitxategia ez dago bere lekuan.");
        } catch (IOException ex) {
            System.out.println("Ez dago objektu gehiagorik.");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound Salbuespena gertatu da.");
        } finally {
            try {
                return t;
            } catch (Exception ex) {
                Logger.getLogger(PartidaGorde.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    }
    
    public static void main(String[] args) {
       TicState tic = ticIrakurri("55-26-3-2021.dat");
        System.out.println(tic);
    }
    
}
