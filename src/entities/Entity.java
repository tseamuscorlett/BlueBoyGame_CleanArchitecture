package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

// #3
public abstract class Entity implements Drawable {
    private int x, y;
    private int speed;

    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;  // store image files #3
    private String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;  // #6
    private int solidAreaDefaultX, solidAreaDefaultY;  // #8
    private boolean collisionOn = false;  // #6

    // stamina  #18
    private int maxLife;
    private int life;
    private int hasKey = 0;  // #8 object pickup

    @Override
    public void draw(Graphics2D g2) {
    }

    public void setUp1(BufferedImage up1) {this.up1 = up1;}
    public void setUp2(BufferedImage up2) {this.up2 = up2;}
    public void setDown1(BufferedImage down1) {this.down1 = down1;}
    public void setDown2(BufferedImage down2) {this.down2 = down2;}
    public void setLeft1(BufferedImage left1) {this.left1 = left1;}
    public void setLeft2(BufferedImage left2) {this.left2 = left2;}
    public void setRight1(BufferedImage right1) {this.right1 = right1;}
    public void setRight2(BufferedImage right2) {this.right2 = right2;}

    public BufferedImage getUp1() {return up1;}
    public BufferedImage getUp2() {return up2;}
    public BufferedImage getDown1() {return down1;}
    public BufferedImage getDown2() {return down2;}
    public BufferedImage getLeft1() {return left1;}
    public BufferedImage getLeft2() {return left2;}
    public BufferedImage getRight1() {return right1;}
    public BufferedImage getRight2() {return right2;}

    public String getDirection() {return direction;}
    public void setDirection(String direction) {this.direction = direction;}

    public boolean getCollisionOn() {return collisionOn;}
    public void setCollisionOn(boolean bool) {this.collisionOn = bool;}

    public int getX() {return x;}
    public void setX(int x) {this.x = x;}
    public int getY() {return y;}
    public void setY(int y) {this.y = y;}

    public int getHasKey() {return hasKey;}
    public void setHasKey(int key) {this.hasKey = key;}

    public int getsolidAreaDefaultX() {return solidAreaDefaultX;}
    public void setsolidAreaDefaultX(int x) {this.solidAreaDefaultX = x;}
    public int getsolidAreaDefaultY() {return solidAreaDefaultY;}
    public void setsolidAreaDefaultY(int y) {this.solidAreaDefaultY = y;}

    public int getMaxLife() {return maxLife;}
    public void setMaxLife(int maxLife) {this.maxLife = maxLife;}

    public int getLife() {return life;}
    public void setLife(int life) {this.life = life;}

    public int getSpeed() {return speed;}
    public void setSpeed(int speed) {this.speed = speed;}


}
