package PaooGame.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage soil;
    public static BufferedImage grass;
    public static BufferedImage mountain;
    public static BufferedImage water;
    public static BufferedImage tree;
    public static BufferedImage treeTop;
    public static BufferedImage chest;
    public static BufferedImage wall1;
    public static BufferedImage wall2;
    public static BufferedImage door;
    public static BufferedImage window;
    public static BufferedImage hero;
    public static BufferedImage roof1;
    public static BufferedImage roof2;
    public static BufferedImage roof3;
    public static BufferedImage roof4;
    public static BufferedImage roof5;
    public static BufferedImage roof6;
    public static BufferedImage roof7;
    public static BufferedImage roof8;
    public static BufferedImage roof9;
    public static BufferedImage fence1;
    public static BufferedImage fence2;


    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/home/teo/Desktop/PAOO/1210A_IurascuTeodor_MisiuneaLuiGratiel/res/textures/ClassicRPG_Sheetv3.png"));


        /// Se obtin subimaginile corespunzatoare elementelor necesare.
            grass = sheet.crop(5, 2);
            tree = sheet.crop(8, 6);
            treeTop=sheet.crop(8,5);
            soil=sheet.crop(1,5);
            chest=sheet.crop(19,2);
            wall1=sheet.crop(17,0);
            wall2=sheet.crop(16,0);
            door=sheet.crop(19,1);
            window=sheet.crop(15,0);
            hero=sheet.crop(1,0);
            roof1=sheet.crop(15,1);
            roof2=sheet.crop(15,2);
            roof3=sheet.crop(18,1);
            roof4=sheet.crop(18,2);
            roof5=sheet.crop(17,4);
            roof6=sheet.crop(15,4);
            roof7=sheet.crop(14,4);
            roof8=sheet.crop(13,2);
            roof9=sheet.crop(13,1);
            fence1=sheet.crop(4,5);
            fence2=sheet.crop(3,5);


    }
}
