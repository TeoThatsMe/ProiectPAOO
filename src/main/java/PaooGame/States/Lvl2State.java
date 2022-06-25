package PaooGame.States;

import PaooGame.PlayerGetInstanceException;
import PaooGame.Drawer;
import PaooGame.Entities.Enemy;
import PaooGame.Entities.Group;
import PaooGame.Entities.Player;
import PaooGame.GameWindow.GameWindow;

import java.io.IOException;

public class Lvl2State extends State{
    public Lvl2State()
    {
        try {
            Drawer.inamiciLvl2 = new Group(new Enemy[]{new Enemy(22*48,10*48),new Enemy(17*48,20*48),new Enemy(20*48,30*48),new Enemy(17*48,42*48),new Enemy(20*48,42*48),new Enemy(17*48,45*48),new Enemy(20*48,45*48)});
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.p=Player.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.x=24*48;
        p.y=0*48;
        try {
            p.setFile(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Player.getInstance().health=100;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawer.inamiciLvl2.setFile(2);

    }
    public Lvl2State(int Health) throws PlayerGetInstanceException {
        try {
            Drawer.inamiciLvl2 = new Group(new Enemy[]{new Enemy(24*48,10*48),new Enemy(20*48,20*48),new Enemy(26*48,30*48),new Enemy(17*48,42*48),new Enemy(20*48,42*48),new Enemy(17*48,45*48),new Enemy(20*48,45*48)});
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.p=Player.getInstance();
        } catch (IOException e) {
            throw new PlayerGetInstanceException("Player get instance esuat");
        }
        p.x=24*48;
        p.y=0*48;
        try {
            p.setFile(2);
            Drawer.inamiciLvl2.setFile(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Player.getInstance().health=Health;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public State nextState() {
        return null;
    }

    @Override
    public State update(GameWindow wnd) throws PlayerGetInstanceException {
        int rez=0;
        try {
            rez=Drawer.DrawLvl2(wnd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(Player.getInstance().health<=0)
                return new DeadState();
        } catch (IOException e) {
            throw new PlayerGetInstanceException("Get PlayerInstance esuat");
        }
        if(rez==1)
        {
            return new PauseState(this);
        }
        Boolean viata=true;
        for(int i=0;i<Drawer.inamiciLvl2.inamici.length;++i)
        {
            viata&=Drawer.inamiciLvl2.inamici[i].isDead;
        }
        System.out.println(viata);
        if(viata)
            return new WinState();
        return this;
    }
    public String toString()
    {
        return "Lvl2";
    }
}
