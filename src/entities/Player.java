package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    public Player() {
        try {
            setUp1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("x/boy_up_1.png")));
            setUp2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("x/boy_up_2.png")));
            setDown1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("x/boy_down_1.png")));
            setDown2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("x/boy_down_2.png")));
            setLeft1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("x/boy_left_1.png")));
            setLeft2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("x/boy_left_2.png")));
            setRight1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("x/boy_right_1.png")));
            setRight2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("x/boy_right_2.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        Maze maze = new Maze();
        BufferedImage image = null;
        switch (getDirection()) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = getUp1();
                }
                if (spriteNum == 2) {
                    image = getUp2();
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = getDown1();
                }
                if (spriteNum == 2) {
                    image = getDown2();
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = getLeft1();
                }
                if (spriteNum == 2) {
                    image = getLeft2();
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = getRight1();
                }
                if (spriteNum == 2) {
                    image = getRight2();
                }
            }
        }
        g2.drawImage(image, getX(), getY(), maze.tileSize, maze.tileSize, null);
         g2.fillRect(getX() + solidArea.x, getY() + solidArea.y, solidArea.width, solidArea.height);
        // to show collision rectangle
    }

}
