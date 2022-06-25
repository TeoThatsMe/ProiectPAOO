package PaooGame.States;

import PaooGame.PlayerGetInstanceException;
import PaooGame.Entities.Enemy;
import PaooGame.Entities.Group;
import PaooGame.Entities.Player;
import PaooGame.Drawer;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Tiles.Tile;

import java.io.IOException;

public class Lvl1State extends State{
    public Lvl1State() throws PlayerGetInstanceException {
        //reinitializare inamici
        try {
            Drawer.inamiciLvl1 = new Group(new Enemy[]{new Enemy(10 * 48, 11 * 48),new Enemy(37*48,15*48),new Enemy(31*48,27*48),new Enemy(7*48,28*48),new Enemy(32*48,42*48)});
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.p=Player.getInstance();
        } catch (IOException e) {
            throw new PlayerGetInstanceException("Get Player instance esuat");
        }
        //coordonate start player
        p.x=1*48;
        p.y=11*48;
        //setare coliziuni player si inamici
        try {
            p.setFile(1);
            Drawer.inamiciLvl1.setFile(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public State nextState() {
        try {
            return new Lvl2State(Player.getInstance().health);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PlayerGetInstanceException e) {
            e.printStackTrace();
        }
        return new Lvl2State();
    }

    @Override
    public State update(GameWindow wnd) {
        int rez=0;
        try {
            rez=Drawer.DrawLvl1(wnd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //detectare moarte
        try {
            if(Player.getInstance().health<=0)
                return new DeadState();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //pauza
        if(rez==1)
        {
            return new PauseState(this);
        }
        //hitbox iesire de pe harta si trecere la urmatorul nivel
        if(p.x>31* Tile.TILE_WIDTH && p.x < 34* Tile.TILE_HEIGHT && p.y ==49*Tile.TILE_WIDTH)
        {
            return nextState();
        }
        return this;
    }
    //functie pentru translatarea starii in text pentru save-state-uri
    public String toString()
    {
        return "Lvl1";
    }
}
