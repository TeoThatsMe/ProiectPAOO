package PaooGame.States;

import PaooGame.Drawer;
import PaooGame.Entities.Player;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.IOException;

public class MenuState extends State {
    public MenuState()
    {
    }
    @Override
    public State nextState() {
        return new NamingState();
    }

    @Override
    public State update(GameWindow wnd) {
        Graphics g=wnd.GetCanvas().getBufferStrategy().getDrawGraphics();
        int ret=0;
        try {
            ret=Drawer.DrawMenu(wnd);
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (ret)
        {
            case 2:
                Drawer.aux=0;
                return new SavesState();
            case 1:
                Drawer.aux=0;
                return nextState();
            case -1:
                System.exit(0);
        }
        return this;
    }
}
