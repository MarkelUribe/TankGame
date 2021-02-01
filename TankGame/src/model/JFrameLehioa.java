package model;

import javax.swing.JFrame;
import exec.*;

public class JFrameLehioa extends JFrame {
    
    //Lehioaren tamaina Grid unitatetan
    public Map map1 = new Map(40, 30);

    public JFrameLehioa() {
        this.setSize(map1.getDimension().getX() * map1.getGrid(),
                map1.getDimension().getY() * map1.getGrid());
    }
}
