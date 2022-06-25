package PaooGame.Tiles;

import PaooGame.Entities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 1000;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie
    public static Tile grassTile        = new GrassTile(45);     /*!< Dala de tip iarba*/
    public static Tile mountainTile     = new MountainTile(1);  /*!< Dala de tip munte/piatra*/
    public static Tile waterTile        = new WaterTile(2);     /*!< Dala de tip apa*/
    public static Tile treeTile         = new TreeTile(128);      /*!< Dala de tip copac*/
    public static Tile treeTopTile      = new TreeTopTile(108);
    public static Tile soilTile         = new SoilTile(101);      /*!< Dala de tip sol/pamant*/
    public static Tile windowTile       =new WindowTile(15);
    public static Tile doorTile         =new DoorTile(39);
    public static Tile chestTile        = new ChestTile(59);
    public static Tile wall1Tile        = new Wall1Tile(17);
    public static Tile wall2Tile        = new Wall2Tile(16);
    public static Tile roof1Tile        = new Roof1Tile(35);
    public static Tile roof2Tile        = new Roof2Tile(56);
    public static Tile roof3Tile        = new Roof3Tile(38);
    public static Tile roof4Tile        = new Roof4Tile(58);
    public static Tile roof5Tile        = new Roof5Tile(97);
    public static Tile roof6Tile        = new Roof6Tile(95);
    public static Tile roof7Tile        = new Roof7Tile(94);
    public static Tile roof8Tile        = new Roof8Tile(53);
    public static Tile roof9Tile        = new Roof9Tile(33);
    public static Tile fence1Tile       = new Fence1Tile(104);
    public static Tile fence2Tile       = new Fence2Tile(103);

    public static final int TILE_WIDTH  = 48;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 48;                       /*!< Inaltimea unei dale.*/

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y)
    {
            /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }
}
