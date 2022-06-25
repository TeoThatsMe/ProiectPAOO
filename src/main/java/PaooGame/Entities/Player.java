package PaooGame.Entities;

import PaooGame.Drawer;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static java.lang.Math.abs;
import static java.lang.Math.round;

public class Player extends Entity {
    static public String nume="";
    static public int scor;
    private int direction=1;
    private double counter=0;
    private int[][] matrice=new int[50][50];
    private Group inamici;
    private File copaci = new File("/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Lvl1/untitled_Copaci.csv");
    static private Player instance=null;
    private Player() throws IOException {
        BufferedReader br=new BufferedReader(new FileReader(copaci));
        String linie;
        for(int i=0;(linie=br.readLine())!=null;++i) {
            String[] l = linie.split(",");
            for (int j = 0; j < l.length; ++j) {
                matrice[j][i]=Integer.parseInt(l[j]);
            }
        }
        x=1*48;
        y=11*48;
        speed=4;
        health=100;
    }
    static public  Player getInstance() throws IOException {
        if(instance==null)
            instance=new Player();
        return instance;
    }
    public void setFile(int opt) throws IOException {
        // setare fisieri de coliziuni in functie de nivel
        switch (opt)
        {
            case 1:
                copaci = new File("/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Lvl1/untitled_Copaci.csv");
                inamici= Drawer.inamiciLvl1;
                break;
            case 2:
                copaci = new File("/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Lvl2/_Collisions.csv");
                inamici=Drawer.inamiciLvl2;
                break;
        }
        BufferedReader br=new BufferedReader(new FileReader(copaci));
        String linie;
        for(int i=0;(linie=br.readLine())!=null;++i) {
            String[] l = linie.split(",");
            for (int j = 0; j < l.length; ++j) {
                matrice[j][i]=Integer.parseInt(l[j]);
            }
        }

    }
    public void Update(GameWindow wnd)
    {
        //miscare cu detectare coliziuni
        //System.out.println("(" + wnd.mouse.punct.x + "," + wnd.mouse.punct.y + ")");
        if(wnd.taste.WPressed) {
            if (y>0 && (matrice[round(x / 48)][round((y+25) / 48)] == -1 && matrice[round((x+40) / 48)][round((y+25) / 48)] == -1) ){
                y -= speed;
            }
            if (direction == 0) {
                counter += 0.2;
            } else {
                direction = 0;
                counter = 0;
            }
        }
        if(wnd.taste.SPressed){
            if (y<49*48 && matrice[round(x / 48)][round((y+48) / 48)] == -1 && matrice[round((x+40) / 48)][round((y+48) / 48)] == -1) {
                y += speed;
            }
            if(direction==1)
            {
                counter+=0.2;
            }
            else {
                direction = 1;
                counter=0;
            }
        }
        if(wnd.taste.DPressed) {
            if (x<50*48 && matrice[round((x+44) / 48)][round((y+47) / 48)] == -1) {
                x += speed;
            }
            if(direction==2)
            {
                counter+=0.2;
            }
            else {
                direction = 2;
                counter=0;
            }
        }
        if(wnd.taste.APressed) {
            if (x>0 && matrice[round((x-3) / 48)][round((y+47) / 48)] == -1) {
                x -= speed;
            }
            if(direction==3)
            {
                counter+=0.2;
            }
            else {
                direction = 3;
                counter=0;
            }
        }
        if(wnd.taste.caracter=='l')
        {
            wnd.taste.caracter='|';
            attack();
        }
    }
    public void Draw(Graphics g)
    {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/textures/ClassicRPG_Sheetv3.png"));
        BufferedImage imagine=null;
        //desenare si determinare frame din animatie
        switch (direction)
        {
            case 0:
                imagine=sheet.crop((int)((1+counter)%4),3);
                break;
            case 1:
                imagine=sheet.crop((int) ((1+counter)%4),0);
                break;
            case 2:
                imagine=sheet.crop((int) ((1+counter)%4),2);
                break;
            case 3:
                imagine=sheet.crop((int) ((1+counter)%4),1);
                break;

        }
        g.drawImage(imagine,x,y,48,48,null );
    }
    private void attack()
    {
        for(int i=0;i<inamici.inamici.length;++i)
        {
            if(abs(inamici.inamici[i].x-x)<=60 && abs(inamici.inamici[i].y-y)<=60 )
            {
                inamici.inamici[i].health-=20;
                if(inamici.inamici[i].health==0)
                    scor+=50;
            }
        }
    }
}
