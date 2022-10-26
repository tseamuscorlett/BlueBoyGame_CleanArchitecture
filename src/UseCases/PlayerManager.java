package UseCases;

import controller_presenter.KeyHandler;
import entities.Maze;
import entities.Player;

import java.awt.*;


// #3
public class PlayerManager {
    KeyHandler keyH;
    CollisionChecker collisionChecker;
    Player player;
    SoundManager soundM;

    public boolean stageClear = false;
    public boolean staminaOut = false;


    public PlayerManager(CollisionChecker collisionChecker, KeyHandler keyH, Player player, SoundManager soundM){
        this.collisionChecker = collisionChecker;
        this.keyH = keyH;
        this.player = player;
        this.soundM = soundM;

        setDefaultValues();

        player.solidArea = new Rectangle(8, 16, 32, 32);  // #6 collision
        player.setsolidAreaDefaultX(player.solidArea.x);  // #7
        player.setsolidAreaDefaultY(player.solidArea.y);  // #7
    }

    // set default values of Player
    public void setDefaultValues(){
        player.setX(48);
        player.setY(48);
        player.setSpeed(4);
        player.setDirection("down");

        player.setMaxLife(500);
        player.setLife(player.getMaxLife());

        player.setHasKey(0);
    }

    // I don't stop the sprite when it's not moving - a "tinkering" effect for light #3
    public void update(){  // this gets called 60 times / sec
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                player.setDirection("up");
            } else if (keyH.downPressed) {
                player.setDirection("down");
            } else if (keyH.leftPressed) {
                player.setDirection("left");
            } else if (keyH.rightPressed) {
                player.setDirection("right");
            }

            // #6 check tile collision
            player.setCollisionOn(false);
            collisionChecker.checkTile(player);

            // #7 check object collision
            int objIndex = collisionChecker.checkObject(player, true);
            pickUpObject(objIndex);

            // player can move if collisionON = false:
            // reduce stamina
            if(!player.getCollisionOn()){
                switch (player.getDirection()) {
                    case "up" -> {
                        player.setY(player.getY() - player.getSpeed());
                        player.setLife(player.getLife() - 1);
                    }
                    case "down" -> {
                        player.setY(player.getY() + player.getSpeed());
                        player.setLife(player.getLife() - 1);
                    }
                    case "left" -> {
                        player.setX(player.getX() - player.getSpeed());
                        player.setLife(player.getLife() - 1);
                    }
                    case "right" -> {
                        player.setX(player.getX() + player.getSpeed());
                        player.setLife(player.getLife() - 1);
                    }
                }
            }
            if(player.getLife() <= 0) {
                staminaOut = true;  // out of stamina
            }
        }

        // add oscillating animation #3
        player.spriteCounter ++;
        if (player.spriteCounter > 12){
            if(player.spriteNum == 1){
                player.spriteNum = 2;
            } else if(player.spriteNum == 2){
                player.spriteNum = 1;
            }
            player.spriteCounter = 0;
        }
    }

    // pickup object #8
    public void pickUpObject(int i) {
        if(i != 999) {
            String objectName = collisionChecker.maze.obj[i].getName();
            switch(objectName) {
                case "Key":
                    soundM.playSE(1);
                    player.setHasKey(player.getHasKey() + 1);
                    collisionChecker.maze.obj[i] = null;
                    break;
                case "Door":
                    if(player.getHasKey() > 0) {
                        collisionChecker.maze.obj[i] = null;
                        player.setHasKey(player.getHasKey() - 1);
                        stageClear = true;  // ends the game #10
                    }
                    break;
                case "Potion":
                    soundM.playSE(1);
                    player.setSpeed(player.getSpeed() + 10);
                    collisionChecker.maze.obj[i] = null;
                    break;
                case "Photons":
                    soundM.playSE(1);
                    player.setLife(player.getLife() + 100);
                    collisionChecker.maze.obj[i] = null;
                    break;
            }
        }
    }
}
