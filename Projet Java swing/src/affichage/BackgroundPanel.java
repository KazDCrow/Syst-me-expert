package affichage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import javax.swing.ImageIcon;

public class BackgroundPanel extends Panel
{
    Image image;
    public BackgroundPanel()
    {
        image = new ImageIcon("src/images/Bunker-pour-milliardaires.jpg").getImage();
    }

    @Override
    public void paint(Graphics g) 
    {
        
        g.drawImage(image, 0,0,null);
        super.paint(g);
    }
}
