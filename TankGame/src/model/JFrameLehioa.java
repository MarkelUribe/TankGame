package model;

import javax.swing.JFrame;
import exec.*;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Color;

public class JFrameLehioa extends JFrame {
    
    //Lehioaren tamaina Grid unitatetan
    public Map map1 = new Map(40, 30);

    public JFrameLehioa() {
        setSize(map1.getDimension().getX() * map1.getGrid(),
                map1.getDimension().getY() * map1.getGrid());
        setMinimumSize(new Dimension(map1.getDimension().getX() * map1.getGrid(),
                map1.getDimension().getY() * map1.getGrid()));
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("TankGame");
        
//        this.getContentPane().setBackground(Color.BLACK);

        konponenteakHasi();
        
    }
    private void konponenteakHasi(){
        JPanel panel = new JPanel();
        
        panel.setBackground(Color.BLACK);
        
        this.getContentPane().add(panel);
        
        
        
    }
}
