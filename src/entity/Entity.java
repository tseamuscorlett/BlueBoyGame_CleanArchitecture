package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

// #3
public class Entity {
    public int x, y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;  // store image files #3
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;  // #6
    public int solidAreaDefaultX, solidAreaDefaultY;  // #8
    public boolean collisionOn = false;  // #6

}
