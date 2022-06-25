package PaooGame.States;

import PaooGame.PlayerGetInstanceException;
import PaooGame.Drawer;
import PaooGame.GameWindow.GameWindow;

import java.io.IOException;

public class NamingState extends State {
    @Override
    public State nextState() {
        return new HelpState();
    }

    @Override
    public State update(GameWindow wnd) {
        int rez=0;
        try {
            rez=Drawer.DrawNaming(wnd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (rez)
        {
            case 1:
                return nextState();
            default: return this;
        }
    }
}
