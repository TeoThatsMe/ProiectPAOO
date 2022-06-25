package PaooGame.States;

import PaooGame.Drawer;
import PaooGame.Entities.Player;
import PaooGame.GameWindow.GameWindow;
import PaooGame.PlayerGetInstanceException;

import java.awt.*;

public abstract class State {
    Player p;
    public abstract State nextState();
    public abstract State update(GameWindow wnd) throws PlayerGetInstanceException;
}
