package PaooGame.States;

import PaooGame.Drawer;
import PaooGame.Entities.Player;
import PaooGame.GameWindow.GameWindow;
import com.sun.jdi.event.ExceptionEvent;

import java.io.IOException;
import java.sql.*;

public class SavesState extends State{
    State state=new MenuState();
    @Override
    public State nextState() {
        return state;
    }

    @Override
    public State update(GameWindow wnd) {
        int rez=0;
        try {
            rez=Drawer.DrawSaves(wnd);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(rez==0) {
            return this;
        }
        if(rez==-1) {
            Drawer.aux = 0;
            return new MenuState();
        }
        ResultSet res = null;
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");

            c = DriverManager.getConnection("jdbc:sqlite:bazadedate.db");
            Statement statement = c.createStatement();
            res = statement.executeQuery("SELECT * FROM saves WHERE ID=" + (rez));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        try {
            Player.nume=res.getString("Nume");
            Player.scor=res.getInt("Score");
            switch (res.getString("State"))
            {
                case "Lvl1":
                    state=new Lvl1State();
                    break;
                case "Lvl2":
                    state=new Lvl2State();
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }
}
