package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// #3
public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    int hasKey = 0;  // #8 object pickup

    public Player (GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        solidArea = new Rectangle(8, 16, 32, 32);  // #6 collision
        solidAreaDefaultX = solidArea.x;  // #7
        solidAreaDefaultY = solidArea.y;  // #7
    }

    // set starting position & stamina of Player on the map
    public void setDefaultValues(){
        x = gp.tileSize;
        y = gp.tileSize;
        speed = 4;
        direction = "down";

        maxLife = 1000;
        life = maxLife;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // I don't stop the sprite when it's not moving - a "tinkering" effect for light #3
    // this is Controller?
    public void update(){  // this gets called 60 times / sec
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            // #6 check tile collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // #7 check object collision
            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);


            // player can move if collisionON = false:
            // added life
            if(!collisionOn){
                switch (direction) {
                    case "up" -> {
                        y -= speed;
                        life -= 1;
                    }
                    case "down" -> {
                        y += speed;
                        life -= 1;
                    }
                    case "left" -> {
                        x -= speed;
                        life -= 1;
                    }
                    case "right" -> {
                        x += speed;
                        life -= 1;
                    }
                }
            }
            if(life <= 0) {
                gp.messagePresenter.staminaOut = true;  // out of stamina
            }
        }

        // add oscillating animation #3
        spriteCounter ++;
        if (spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            } else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    // pickup object #8
    public void pickUpObject(int i) {
        if(i != 999) {
            String objectName = gp.obj[i].name;
            switch(objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    break;
                case "Door":
                    if(hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                        gp.messagePresenter.gameFinished = true;  // ends the game #10
                    }
                    break;
                case "Potion":
                    gp.playSE(1);
                    speed += 10;
                    gp.obj[i] = null;
                    break;
            }

        }

    }

    // need interface for "dependency inversion" => Presenter draws
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        // g2.fillRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height)
        // to show collision rectangle
    }
}
