package model;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class PartidaGorde {
    
    public static ObjectOutputStream sortu(String fitxategia){
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
        try {
            fin = new FileInputStream("db/"+fitxategia);
            ObjectInputStream inStream = new ObjectInputStream(fin);
            TicState t;
            while (true) {
                t = (TicState) inStream.readObject(); 
                return t;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fitxategia ez dago bere lekuan.");
        } catch (IOException ex) {
            System.out.println("Ez dago objektu gehiagorik.");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound Salbuespena gertatu da.");
        } finally {
            try {
                fin.close();
            } catch (Exception ex) {
                Logger.getLogger(PartidaGorde.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
//   public static Connection connect(String fitxategia) {
//        Connection conn = null;
//        try {
//            String url = "jdbc:sqlite:db/"+fitxategia;
//            conn = DriverManager.getConnection(url);
//
//            System.out.println("Connection to SQLite has been established.");
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            
//        }
//        return conn;
//    }
//   
//   private static String toString( Serializable o ) throws IOException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream( baos );
//        oos.writeObject( o );
//        oos.close();
//        return Base64.getEncoder().encodeToString(baos.toByteArray()); 
//    }
//   
//   public static void ticGehitu(TicState t, String fitxategia) {
//        String sql = "INSERT INTO Tics(tic) VALUES(?)";
//
//        try (Connection conn = connect(fitxategia);
//                PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            try {
//                pstmt.setString(1, toString(t));
//            } catch (IOException ex) {
//                Logger.getLogger(PartidaGorde.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
