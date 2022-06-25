package PaooGame;

import PaooGame.Entities.Enemy;
import PaooGame.Entities.Group;
import PaooGame.Entities.Player;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.ImageLoader;
import PaooGame.States.PauseState;
import PaooGame.States.State;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

public class Drawer {
    static private Player jucator;
    static public float aux=0;
    public static Group inamiciLvl1;
    public static Group inamiciLvl2;
    public static BufferedImage imagine= ImageLoader.LoadImage("/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/textures/cwr8F7U.png");

    static {
        try {
            inamiciLvl1 = new Group(new Enemy[]{new Enemy(10 * 48, 11 * 48),new Enemy(37*48,15*48),new Enemy(31*48,27*48),new Enemy(7*48,28*48),new Enemy(32*48,42*48)});
            inamiciLvl2 = new Group(new Enemy[]{new Enemy(24*48,10*48),new Enemy(20*48,20*48),new Enemy(26*48,30*48),new Enemy(17*48,42*48),new Enemy(20*48,42*48),new Enemy(17*48,45*48),new Enemy(20*48,45*48)});
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jucator = Player.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        inamiciLvl1.setFile(1);
        inamiciLvl2.setFile(2);
    }

    public Drawer() throws IOException {
    }

    static public int DrawLvl1(GameWindow wnd) throws IOException
    {

        Graphics g= wnd.GetCanvas().getBufferStrategy().getDrawGraphics();
        //miscarea camerei
        int camerax=jucator.x-1920/2,cameray=jucator.y-1080/2;
        if(camerax<0)
            camerax=0;
        else if(camerax>480)
            camerax=480;
        if(cameray<0)
            cameray=0;
        else if(cameray>1344)
            cameray=1344;
        //System.out.println("{" + camerax+ ", " + cameray + "}");

        g.translate(-camerax, -cameray);

        //desenare harta pe layer-e
        String file="/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Lvl1/untitled_Ground.csv";
        BufferedReader br=new BufferedReader(new FileReader(file));
        String linie;
        for(int i=0;(linie=br.readLine())!=null;++i)
        {
            String[] l=linie.split(",");
            for(int j=0;j<l.length;++j)
                DrawTile(j,i,Integer.parseInt(l[j]),g);
        }
        file="/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Lvl1/untitled_Copaci.csv";
        br=new BufferedReader(new FileReader(file));
        for(int i=0;(linie=br.readLine())!=null;++i) {
            String[] l = linie.split(",");
            for (int j = 0; j < l.length; ++j) {
                DrawTile(j, i, Integer.parseInt(l[j]),g);

            }
        }
        file="/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Lvl1/untitled_Decor.csv";
        br=new BufferedReader(new FileReader(file));
        for(int i=0;(linie=br.readLine())!=null;++i)
        {
            String[] l=linie.split(",");
            for(int j=0;j<l.length;++j)
                DrawTile(j,i,Integer.parseInt(l[j]),g);
        }

        //miscare jucator si inamic
        jucator.Update(wnd);
        jucator.Draw(g);
        inamiciLvl1.Update();
        inamiciLvl1.Draw(g);
        file="/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Lvl1/untitled_CopaciUp.csv";
        br=new BufferedReader(new FileReader(file));
        for(int i=0;(linie=br.readLine())!=null;++i)
        {
            String[] l=linie.split(",");
            for(int j=0;j<l.length;++j)
                DrawTile(j,i,Integer.parseInt(l[j]),g);
        }

        //desenare overlay
        g.setColor(Color.BLACK);
        g.fillRect(5+camerax,25+cameray,142,60);
        g.setFont(g.getFont().deriveFont(Font.BOLD,22F));
        g.setColor(Color.red);
        g.drawString("Health: " + jucator.health,10+camerax,50+cameray);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + jucator.scor,10+camerax,75+cameray);

        //detectare apasare escape
        if(wnd.taste.escapePressed)
        {
            return 1;
        }
        return 0;
    }

    static public int DrawLvl2(GameWindow wnd) throws IOException
    {

        Graphics g= wnd.GetCanvas().getBufferStrategy().getDrawGraphics();
        int camerax=jucator.x-1920/2,cameray=jucator.y-1080/2;
        if(camerax<0)
            camerax=0;
        else if(camerax>480)
            camerax=480;
        if(cameray<0)
            cameray=0;
        else if(cameray>1344)
            cameray=1344;
        //System.out.println("{" + camerax+ ", " + cameray + "}");

        g.translate(-camerax, -cameray);
        String file="/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Lvl2/_Ground.csv";
        BufferedReader br=new BufferedReader(new FileReader(file));
        String linie;
        for(int i=0;(linie=br.readLine())!=null;++i)
        {
            String[] l=linie.split(",");
            for(int j=0;j<l.length;++j)
                DrawTile(j,i,Integer.parseInt(l[j]),g);
        }
        file="/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Lvl2/_Collisions.csv";
        br=new BufferedReader(new FileReader(file));
        for(int i=0;(linie=br.readLine())!=null;++i) {
            String[] l = linie.split(",");
            for (int j = 0; j < l.length; ++j) {
                DrawTile(j, i, Integer.parseInt(l[j]),g);

            }
        }
        jucator.Update(wnd);
        jucator.Draw(g);
        inamiciLvl2.Update();
        inamiciLvl2.Draw(g);
        file="/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Lvl2/_Trees Up.csv";
        br=new BufferedReader(new FileReader(file));
        for(int i=0;(linie=br.readLine())!=null;++i)
        {
            String[] l=linie.split(",");
            for(int j=0;j<l.length;++j)
                DrawTile(j,i,Integer.parseInt(l[j]),g);
        }

        g.setColor(Color.BLACK);
        g.fillRect(5+camerax,25+cameray,142,60);
        g.setFont(g.getFont().deriveFont(Font.BOLD,22F));
        g.setColor(Color.red);
        g.drawString("Health: " + jucator.health,10+camerax,50+cameray);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + jucator.scor,10+camerax,75+cameray);

        if(wnd.taste.escapePressed)
        {
            return 1;
        }
        return 0;
    }

    public static void DrawTile(int x, int y, int id,Graphics g)
    {
        switch(id)
        {
            case 15:
                Tile.windowTile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 17:
                Tile.wall1Tile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 16:
                Tile.wall2Tile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 35:
                Tile.roof1Tile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 39:
                Tile.doorTile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 45:
                Tile.grassTile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 56:
                Tile.roof2Tile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 38:
                Tile.roof3Tile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 58:
                Tile.roof4Tile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 97:
                Tile.roof5Tile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 95:
                Tile.roof6Tile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 94:
                Tile.roof7Tile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 53:
                Tile.roof8Tile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 33:
                Tile.roof9Tile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 59:
                Tile.chestTile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 128:
                Tile.treeTile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 101:
                Tile.soilTile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 108:
                Tile.treeTopTile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 103:
                Tile.fence2Tile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
            case 104:
                Tile.fence1Tile.Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                break;
        }
    }

    public static int DrawNaming(GameWindow wnd) throws IOException {
        Graphics g = wnd.GetCanvas().getBufferStrategy().getDrawGraphics();
        //harta plina cu copaci
        String file="/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Menu/untitled_Copaci.csv";
        BufferedReader br=new BufferedReader(new FileReader(file));
        String linie;
        for(int i=0;(linie=br.readLine())!=null;++i)
        {
            String[] l=linie.split(",");
            for(int j=0;j<l.length;++j)
                DrawTile(j,i,Integer.parseInt(l[j]),g);
        }
        //desenare imagine si text
        g.drawImage(imagine,0,0,null);
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.BOLD,96F));
        g.setFont(new Font("TimesRoman",Font.PLAIN,50));

        //citire litere
        g.drawString("Nume: " + Player.nume,690,500);
        if(wnd.taste.caracter>='a' && wnd.taste.caracter<='z' || wnd.taste.caracter>='A' && wnd.taste.caracter<='Z')
        {
            Player.nume+=wnd.taste.caracter;
            wnd.taste.caracter='|';
        }
        //stergere caracter
        if (wnd.taste.caracter==8 && !Player.nume.isEmpty())
        {
            Player.nume = Player.nume.substring(0,Player.nume.length()-1);
            wnd.taste.caracter='|';
        }
        //continuare joc
        if(wnd.taste.caracter==10 && !Player.nume.isEmpty()) {
            wnd.taste.caracter='|';
            return 1;
        }
        return 0;
    }

    public static int DrawHelp(GameWindow wnd) throws IOException {
        Graphics g = wnd.GetCanvas().getBufferStrategy().getDrawGraphics();
        //harta plina cu copaci
        String file="/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Menu/untitled_Copaci.csv";
        BufferedReader br=new BufferedReader(new FileReader(file));
        String linie;
        for(int i=0;(linie=br.readLine())!=null;++i)
        {
            String[] l=linie.split(",");
            for(int j=0;j<l.length;++j)
                DrawTile(j,i,Integer.parseInt(l[j]),g);
        }
        //desenare imagine si text
        g.drawImage(imagine,0,0,null);
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.BOLD,96F));
        g.setFont(new Font("TimesRoman",Font.PLAIN,50));

        g.drawString("Controls:",690,200);
        g.drawString("W,A,S,D - movement",690,300);
        g.drawString("L - attack",690,400);
        g.drawString("ESC- Pause",690,500);
        g.drawString("Press enter to continue...",690,900);



        //continuare joc
        if(wnd.taste.caracter==10) {
            wnd.taste.caracter = '|';
            return 1;
        }
        return 0;
    }

    public static int DrawMenu(GameWindow wnd) throws IOException {
        Graphics g = wnd.GetCanvas().getBufferStrategy().getDrawGraphics();
        String file="/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Menu/untitled_Copaci.csv";
        BufferedReader br=new BufferedReader(new FileReader(file));
        String linie;
        for(int i=0;(linie=br.readLine())!=null;++i)
        {
            String[] l=linie.split(",");
            for(int j=0;j<l.length;++j)
                DrawTile(j,i,Integer.parseInt(l[j]),g);
        }
        g.drawImage(imagine,0,0,null);
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.BOLD,96F));
        g.setFont(new Font("TimesRoman",Font.PLAIN,50));

        g.drawString("Misiunea lui Gratiel",690,200);
        String []text={"New Game","Load Game","Quit"};
        for(int i=0;i<3;++i)
        {
            //detectare optiune aleasa
            if(((int)aux)%3==i)
            {
                g.drawString(">" + text[i],690,(i*100+600));
            }
            else {
                g.drawString(text[i], 690, (i * 100 + 600));
            }
        }
        //schimbare optiune aleasa
        if(wnd.taste.caracter=='w')
        {
            wnd.taste.caracter='|';
            aux-=1;
        }
        if(wnd.taste.caracter=='s')
        {
            wnd.taste.caracter='|';
            aux+=1;
        }
        if(aux<0)
        {
            aux=2;
        }
        if(((int)aux)%3==0 && wnd.taste.caracter==10)
        {
            wnd.taste.caracter='|';
            return 1;
        }
        if(((int)aux)%3==1 && wnd.taste.caracter==10) {
            wnd.taste.caracter='|';
            return 2;
        }
        if(((int)aux)%3==2 && wnd.taste.caracter==10)
        {
            wnd.taste.caracter='|';
            return -1;
        }
        return 0;
    }

    public static int DrawPause(GameWindow wnd, State state) throws IOException, ClassNotFoundException, SQLException {
        Connection c;
        Graphics g = wnd.GetCanvas().getBufferStrategy().getDrawGraphics();
        String file = "/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Menu/untitled_Copaci.csv";
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linie;
        for (int i = 0; (linie = br.readLine()) != null; ++i) {
            String[] l = linie.split(",");
            for (int j = 0; j < l.length; ++j)
                DrawTile(j, i, Integer.parseInt(l[j]), g);
        }
        g.drawImage(imagine,0,0,null);
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 96F));
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));

        g.drawString("Paused", 690, 200);
        String[] text = {"Continue", "Save Game", "Quit"};
        for (int i = 0; i < 3; ++i) {
            if (((int) aux) % 3 == i) {
                g.drawString(">" + text[i], 690, (i * 100 + 600));
            } else {
                g.drawString(text[i], 690, (i * 100 + 600));
            }
        }
        if(wnd.taste.caracter=='w')
        {
            wnd.taste.caracter='|';
            aux-=1;
        }
        if(wnd.taste.caracter=='s')
        {
            wnd.taste.caracter='|';
            aux+=1;
        }
        if (((int) aux) % 3 == 0 && wnd.taste.enterPressed) {
            return 1;
        }
        if (((int) aux) % 3 == 1 && wnd.taste.caracter==10) {
            wnd.taste.caracter='|';
            Class.forName("org.sqlite.JDBC");
            c= DriverManager.getConnection("jdbc:sqlite:bazadedate.db");
            Statement statement = c.createStatement();
            String save = "CREATE TABLE IF NOT EXISTS \"saves\" (\n" +
                    "\t\"ID\"\tINTEGER UNIQUE,\n" +
                    "\t\"Nume\"\tTEXT NOT NULL,\n" +
                    "\t\"State\"\tINTEGER NOT NULL,\n" +
                    "\t\"Score\"\tINTEGER,\n" +
                    "\tPRIMARY KEY(\"ID\" AUTOINCREMENT)\n" +
                    ")";
            statement.execute(save);
            statement.executeUpdate("INSERT INTO saves (Nume,State,Score) VALUES ('" + Player.nume + "' , '" + state.toString() + "' ," + Player.scor + ")");
            g.drawString("Game saved",690,900);
        }
        if (((int) aux) % 3 == 2 && wnd.taste.enterPressed) {
            return -1;
        }
        return 0;
    }

    public static int DrawSaves(GameWindow wnd) throws IOException, ClassNotFoundException, SQLException {
        Graphics g = wnd.GetCanvas().getBufferStrategy().getDrawGraphics();
        String file="/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Menu/untitled_Copaci.csv";
        BufferedReader br=new BufferedReader(new FileReader(file));
        String linie;
        for(int i=0;(linie=br.readLine())!=null;++i)
        {
            String[] l=linie.split(",");
            for(int j=0;j<l.length;++j)
                DrawTile(j,i,Integer.parseInt(l[j]),g);
        }
        g.drawImage(imagine,0,0,null);
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.BOLD,96F));
        g.setFont(new Font("TimesRoman",Font.PLAIN,50));

        g.drawString("Saves",690,200);
        //conectare la baza de date
        Class.forName("org.sqlite.JDBC");
        Connection c= DriverManager.getConnection("jdbc:sqlite:bazadedate.db");
        Statement statement = c.createStatement();
        //selectare in ordine inversa a save-urilor
        ResultSet res=statement.executeQuery("SELECT * from saves ORDER BY ID DESC");
        int i=0;
        String []saves=new String[5];
        Integer []ids=new Integer[5];
        //alegere a ultimelor 5 save-uri
        while (res.next() && i<5 )
        {
            saves[i]=res.getString("Nume") + "     " + res.getString("State");
            ids[i]=res.getInt("ID");
            ++i;
        }
        int j;
        for(j=0;j<i;++j)
        {
            if(Math.round(aux)%(i+1)==j)
                g.drawString(">" + saves[j],690,400+j*100);
            else
                g.drawString(saves[j],690,400+j*100);
        }
        if(Math.round(aux)%(i+1)==j)
        {
            g.drawString(">Back",690,400+j*100);
        }
        else
        {
            g.drawString("Back",690,400+j*100);
        }
        if(wnd.taste.caracter=='w')
        {
            wnd.taste.caracter='|';
            aux-=1;
        }
        if(wnd.taste.caracter=='s')
        {
            wnd.taste.caracter='|';
            aux+=1;
        }
        if(aux<0)
            aux=j;

        if(Math.round(aux)==j && wnd.taste.enterPressed) {
            wnd.taste.caracter = '|';
            return -1;
        }
        else if (wnd.taste.enterPressed)
        {
            wnd.taste.caracter='|';
            return ids[(int) aux];
        }
        return 0;
    }

    public static int DrawDead(GameWindow wnd) throws IOException
    {
        Graphics g = wnd.GetCanvas().getBufferStrategy().getDrawGraphics();
        String file="/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Menu/untitled_Copaci.csv";
        BufferedReader br=new BufferedReader(new FileReader(file));
        String linie;
        for(int i=0;(linie=br.readLine())!=null;++i)
        {
            String[] l=linie.split(",");
            for(int j=0;j<l.length;++j)
                DrawTile(j,i,Integer.parseInt(l[j]),g);
        }
        g.drawImage(imagine,0,0,null);
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.BOLD,96F));
        g.setFont(new Font("TimesRoman",Font.PLAIN,50));

        g.drawString("You have died",690,200);
        String []text={"Load Game","Quit"};
        for(int i=0;i<2;++i)
        {
            if(((int)aux)%2==i)
            {
                g.drawString(">" + text[i],690,(i*100+600));
            }
            else {
                g.drawString(text[i], 690, (i * 100 + 600));
            }
        }
        if(wnd.taste.caracter=='w')
        {
            wnd.taste.caracter='|';
            aux-=1;
        }
        if(wnd.taste.caracter=='s')
        {
            wnd.taste.caracter='|';
            aux+=1;
        }
        if(aux<0)
        {
            aux=1;
        }
        if(((int)aux)%2==0 && wnd.taste.caracter==10)
        {
            wnd.taste.caracter='|';
            return 1;
        }
        if(((int)aux)%2==1 && wnd.taste.caracter==10) {
            wnd.taste.caracter='|';
            return 2;
        }
        return 0;
    }
    static public void DrawWin(GameWindow wnd) throws IOException {
        Graphics g = wnd.GetCanvas().getBufferStrategy().getDrawGraphics();
        String file="/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Menu/untitled_Copaci.csv";
        BufferedReader br=new BufferedReader(new FileReader(file));
        String linie;
        for(int i=0;(linie=br.readLine())!=null;++i)
        {
            String[] l=linie.split(",");
            for(int j=0;j<l.length;++j)
                DrawTile(j,i,Integer.parseInt(l[j]),g);
        }
        g.drawImage(imagine,0,0,null);
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.BOLD,96F));
        g.setFont(new Font("TimesRoman",Font.PLAIN,50));

        g.drawString("Felicitari!",690,350);
        g.drawString("Score: " + Player.scor,690,400);
        g.drawString("Gratiel si-a recuperat cutia!",690,450);

    }
}
