package PaooGame.States;

import PaooGame.Drawer;
import PaooGame.GameWindow.GameWindow;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class PauseState extends State {
    State state;
    PauseState(State state)
    {
        this.state=state;
    }
    @Override
    public State nextState() {
        return state;
    }

    @Override
    public State update(GameWindow wnd) {
        Graphics g=wnd.GetCanvas().getBufferStrategy().getDrawGraphics();
        int ret=0;
        try {
            ret= Drawer.DrawPause(wnd,state);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        switch (ret)
        {
            case 1:
                return nextState();
            case -1:
                System.exit(0);
        }
        return this;
    }
}
