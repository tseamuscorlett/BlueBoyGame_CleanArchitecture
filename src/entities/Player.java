package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    private int life;
    private int maxLife;
    private int hasKey;

    public Player() {
        try {
            setUp1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_up_1.png")));
            setDown1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_1.png")));
            setLeft1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left_1.png")));
            setRight1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right_1.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

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
        // g2.fillRect(getX() + solidArea.x, getY() + solidArea.y, solidArea.width, solidArea.height);
        // to show collision rectangle
    }

    public int getLife(){
        return this.life;
    }
    public void setLife(int life){
        this.life = life;
    }

    public int getMaxLife(){
        return this.maxLife;
    }
    public void setMaxLife(int maxLife){
        this.maxLife = maxLife;
    }

    public int getHasKey(){
        return this.hasKey;
    }
    public void setHasKey(int key){
        this.hasKey = key;
    }
}
