package PaooGame.States;

import PaooGame.Drawer;
import PaooGame.GameWindow.GameWindow;
import PaooGame.PlayerGetInstanceException;

import java.io.IOException;

public class HelpState extends State{
    @Override
    public State nextState() {
        try {
            return new Lvl1State();
        } catch (PlayerGetInstanceException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public State update(GameWindow wnd) throws PlayerGetInstanceException {
        int rez=0;
        try {
            rez= Drawer.DrawHelp(wnd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (rez)
        {

            case 1:
                return nextState();
            default:
                return this;
        }
    }
}
