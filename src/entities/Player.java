package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    // stamina  #18
    private int maxLife;
    private int life;
    private int hasKey = 0;  // #8 object pickup

    public Player() {
        try {
            setUp1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_up_1.png")));
            setUp2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_up_2.png")));
            setDown1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_1.png")));
            setDown2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_2.png")));
            setLeft1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left_1.png")));
            setLeft2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left_2.png")));
            setRight1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right_1.png")));
            setRight2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right_2.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public int getHasKey() {return hasKey;}
    public void setHasKey(int key) {this.hasKey = key;}

    public int getMaxLife() {return maxLife;}
    public void setMaxLife(int maxLife) {this.maxLife = maxLife;}

    public int getLife() {return life;}
    public void setLife(int life) {this.life = life;}

    public void draw(Graphics2D g2){
        Maze maze = new Maze();
        BufferedImage image = null;
        switch (getDirection()) {
            case "up" -> {
                image = getUp1();
            }
            case "down" -> {
                image = getDown1();
            }
            case "left" -> {
                image = getLeft1();
            }
            case "right" -> {
                image = getRight1();
                }
            }
        g2.drawImage(image, getX(), getY(), maze.tileSize, maze.tileSize, null);
//         g2.fillRect(getX() + solidArea.x, getY() + solidArea.y, solidArea.width, solidArea.height);
        // to show collision rectangle
    }

}
