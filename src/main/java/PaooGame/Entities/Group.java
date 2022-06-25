package PaooGame.Entities;

import java.awt.*;
import java.io.IOException;

public class Group {
    public Enemy []inamici;
    public Group(Enemy[] inamici)
    {
        this.inamici=new Enemy[inamici.length];
        for(int i=0;i<inamici.length;++i)
        {
            try {
                this.inamici[i]=new Enemy(inamici[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void Update()
    {
        for(int i=0;i<inamici.length;++i)
        {
            inamici[i].Update(this);
        }
    }
    public void Draw(Graphics g)
    {
        for(int i=0;i<inamici.length;++i)
        {
            inamici[i].Draw(g);
        }
    }
    public void setFile(int f)
    {
        for(int i=0;i<inamici.length;++i) {
            try {
                inamici[i].setFile(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
