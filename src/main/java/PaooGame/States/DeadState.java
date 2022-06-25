package PaooGame.States;

import PaooGame.Drawer;
import PaooGame.Entities.Player;
import PaooGame.GameWindow.GameWindow;

import java.io.IOException;

public class DeadState extends State{
    @Override
    public State nextState() {
        return new SavesState();
    }

    @Override
    public State update(GameWindow wnd) {
        int ret = 0;
        try {
            ret = Drawer.DrawDead(wnd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (ret) {
            case 1:
                try {
                    Player.getInstance().health=100;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return nextState();
            case 2:
                System.exit(0);
            default:
                return this;
        }
    }
}
