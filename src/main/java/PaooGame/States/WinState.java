package PaooGame.States;

import PaooGame.Drawer;
import PaooGame.GameWindow.GameWindow;

import java.io.IOException;

public class WinState extends State{
    @Override
    public State nextState() {
        return null;
    }

    @Override
    public State update(GameWindow wnd) {
        try {
            Drawer.DrawWin(wnd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
