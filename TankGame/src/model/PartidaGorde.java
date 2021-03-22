package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import exec.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PartidaGorde {
   public static Connection connect(String fitxategia) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:db/"+fitxategia;
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        }
        return conn;
    }
   
   private static String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray()); 
    }
   
   public static void ticGehitu(TicState t, String fitxategia) {
        String sql = "INSERT INTO Tics(tic) VALUES(?)";

        try (Connection conn = connect(fitxategia);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try {
                pstmt.setString(1, toString(t));
            } catch (IOException ex) {
                Logger.getLogger(PartidaGorde.class.getName()).log(Level.SEVERE, null, ex);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
