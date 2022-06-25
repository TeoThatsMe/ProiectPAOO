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

public class Enemy extends Entity{
    private int direction=1;
    private double counter=0;
    private int attacked=0;
    private int[][] matrice=new int[50][50];
    private File copaci = new File("/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Lvl1/untitled_Copaci.csv");
    public boolean isDead;

    public Enemy(int x,int y) throws IOException {
        isDead=false;
        BufferedReader br=new BufferedReader(new FileReader(copaci));
        String linie;
        for(int i=0;(linie=br.readLine())!=null;++i) {
            String[] l = linie.split(",");
            for (int j = 0; j < l.length; ++j) {
                matrice[j][i]=Integer.parseInt(l[j]);
            }
        }
        this.x=x;
        this.y=y;
        speed=3;
        health=100;
    }
    public Enemy(Enemy inamic) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader(copaci));
        String linie;
        for(int i=0;(linie=br.readLine())!=null;++i) {
            String[] l = linie.split(",");
            for (int j = 0; j < l.length; ++j) {
                matrice[j][i]=Integer.parseInt(l[j]);
            }
        }
        x=inamic.x;
        y=inamic.y;
        speed=2;
        health=100;
    }
    public void setFile(int opt) throws IOException {
        switch (opt) {
            case 1:
                copaci = new File("/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Lvl1/untitled_Copaci.csv");
                break;
            case 2:
                copaci = new File("/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/levels/Lvl2/_Collisions.csv");
                break;
        }
        BufferedReader br = new BufferedReader(new FileReader(copaci));
        String linie;
        for (int i = 0; (linie = br.readLine()) != null; ++i) {
            String[] l = linie.split(",");
            for (int j = 0; j < l.length; ++j) {
                matrice[j][i] = Integer.parseInt(l[j]);
            }
        }
    }

        public void Draw(Graphics g)
        {
            if(health>0) {
                SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/textures/enemy.png"));
                BufferedImage imagine = null;
                switch (direction) {
                    case 0:
                        imagine = sheet.crop((int) ((1 + counter) % 4), 3);
                        break;
                    case 1:
                        imagine = sheet.crop((int) ((1 + counter) % 4), 0);
                        break;
                    case 2:
                        imagine = sheet.crop((int) ((1 + counter) % 4), 2);
                        break;
                    case 3:
                        imagine = sheet.crop((int) ((1 + counter) % 4), 1);
                        break;
                }


                g.drawImage(imagine, x, y, 48, 48, null);
            }
        }
        public void Update(Group inamici)
        {
            if(health>0) {
                Move(inamici);
                attack();
            }
            else
                isDead=true;
        }
        public void Move(Group inamici)
        {
            int x=0,y=0;
            try {
                x=Player.getInstance().x;
                y=Player.getInstance().y;
            } catch (IOException e) {
                e.printStackTrace();
            }
            Boolean canMove=true;
            for(int i=0;i<inamici.inamici.length && inamici.inamici[i]!=this && canMove;++i)
            {
                canMove=isNear(inamici.inamici[i]);
            }
            if(abs(this.x-x)<=5*48 && abs(this.y-y)<=5*48 && (abs(this.x-x)>48 || abs(this.y-y)>48) && canMove)
            {

                if (this.x < x && matrice[round((this.x+44) / 48)][round((this.y+47) / 48)] == -1) {
                    this.x += speed;
                    if(direction==2)
                        counter+=0.1;
                    else {
                        direction = 2;
                        counter=0;
                    }
                }
                if (this.x > x && matrice[round((this.x-3) / 48)][round((this.y+47) / 48)] == -1) {
                    this.x -= speed;
                    if(direction==3)
                        counter+=0.1;
                    else {
                        direction = 3;
                        counter=0;
                    }
                }
                if (this.y < y && matrice[round(this.x / 48)][round((this.y+48) / 48)] == -1 && matrice[round((this.x+40) / 48)][round((this.y+48) / 48)] == -1) {
                    this.y += speed;
                    if(direction==1)
                        counter+=0.1;
                    else {
                        direction = 1;
                        counter=0;
                    }
                }
                if (this.y > y && (matrice[round(this.x / 48)][round((this.y+25) / 48)] == -1 && matrice[round((this.x+40) / 48)][round((this.y+25) / 48)] == -1)) {
                    this.y -= speed;
                    if(direction==0)
                        counter+=0.1;
                    else {
                        direction = 0;
                        counter=0;
                    }
                }
            }
        }
        private void attack()
        {
            try {
                if(abs(x-Player.getInstance().x)<=58 && abs(y-Player.getInstance().y)<=58 && attacked<=0)
                {
                    Player.getInstance().health-=5;
                    attacked=100;
                }
                else
                {
                    attacked-=2;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private Boolean isNear(Enemy e)
        {
            if(abs(x-e.x)>=48 || abs(y-e.y)>=48)
                return true;
            return false;
        }
    }
