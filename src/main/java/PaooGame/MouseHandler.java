package PaooGame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    public boolean m1,m2;
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("a");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("a");
        if(e.getButton()==MouseEvent.BUTTON1)
            m1=true;
        if(e.getButton()==MouseEvent.BUTTON2)
            m2=true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("a");
        if(e.getButton()==MouseEvent.BUTTON1)
            m1=false;
        if(e.getButton()==MouseEvent.BUTTON2)
            m2=false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
