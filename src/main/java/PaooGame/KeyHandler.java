package PaooGame;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyHandler implements KeyListener {
    public boolean WPressed,SPressed,APressed,DPressed,enterPressed,escapePressed,backspacePressed;
    public char caracter='|';
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //setare caracter apasat
        caracter=e.getKeyChar();
        //System.out.println((int)caracter);
        int code= e.getKeyCode();
        if(code==KeyEvent.VK_W)
        {
            WPressed=true;
        }
        if(code==KeyEvent.VK_S)
        {
            SPressed=true;
        }
        if(code==KeyEvent.VK_A)
        {
            APressed=true;
        }
        if(code==KeyEvent.VK_D)
        {
            DPressed=true;
        }
        if(code==KeyEvent.VK_ENTER)
        {
            enterPressed=true;
        }
        if(code==KeyEvent.VK_ESCAPE)
        {
            escapePressed = true;
        }
        if(code==KeyEvent.VK_BACK_SPACE)
        {
            backspacePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code= e.getKeyCode();
        if(code==KeyEvent.VK_W)
        {
            WPressed=false;
        }
        if(code==KeyEvent.VK_S)
        {
            SPressed=false;
        }
        if(code==KeyEvent.VK_A)
        {
            APressed=false;
        }
        if(code==KeyEvent.VK_D)
        {
            DPressed=false;
        }
        if(code==KeyEvent.VK_ENTER)
        {
            enterPressed=false;
        }
        if(code==KeyEvent.VK_ESCAPE)
        {
            escapePressed = false;
        }
        if(code==KeyEvent.VK_BACK_SPACE)
        {
            backspacePressed = false;
        }
    }
}
