// #7
package entities.object;
// this was deleted in #21, moving everything to Entity

import entities.Entity;
import entities.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject extends Entity {

    private BufferedImage image;
    private String name;
    public boolean collision = false;
    private int x, y;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);  // #8 let's NOT change this

    public void draw(Graphics2D g2, GamePanel gp) {
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public BufferedImage getImage() {return this.image;}

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
