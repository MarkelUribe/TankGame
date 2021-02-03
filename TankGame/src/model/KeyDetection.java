
package model;

import java.awt.event.KeyEvent;

public class KeyDetection {
        public void keyPressed(KeyEvent e,Tank t) {
        Integer key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            t.setT1Position(t.getT1X() + 1, t.getT1Y());
        }
        if (key == KeyEvent.VK_RIGHT) {
            System.out.println("Pito");
        }
    }
}
