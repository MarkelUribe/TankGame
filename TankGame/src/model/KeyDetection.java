
package model;

import java.awt.event.KeyEvent;

public class KeyDetection {
    public void KeyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_F5)
            System.out.println("F5 pressed");
    }
}
