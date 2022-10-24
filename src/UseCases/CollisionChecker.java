package UseCases;

import entities.Entity;
import entities.GamePanel;

public class CollisionChecker {
    GamePanel gamePanel;
    public CollisionChecker(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    // #6
    public void checkTile(Entity entity){
        // collision coordinates
        // define entity collision areas; we don't need World, just Screen
        int entityLeftX = entity.getX() + entity.solidArea.x;
        int entityRightX = entity.getX() + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.getY() + entity.solidArea.y;
        int entityBottomY = entity.getY() + entity.solidArea.y + entity.solidArea.height;

        // define col & row numbers
        int entityLeftCol = entityLeftX/gamePanel.tileSize;
        int entityRightCol = entityRightX/gamePanel.tileSize;
        int entityTopRow = entityTopY/gamePanel.tileSize;
        int entityBottomRow = entityBottomY/gamePanel.tileSize;

        int tileNum1, tileNum2;  // only need to check 2 tiles

        switch(entity.getDirection()){
            case "up":
                entityTopRow = (entityTopY - entity.getSpeed())/gamePanel.tileSize;
                tileNum1 = gamePanel.tiles.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tiles.mapTileNum[entityRightCol][entityTopRow];
                if(gamePanel.tiles.tile[tileNum1].collision || gamePanel.tiles.tile[tileNum2].collision){
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.getSpeed())/gamePanel.tileSize;
                tileNum1 = gamePanel.tiles.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tiles.mapTileNum[entityRightCol][entityBottomRow];
                if(gamePanel.tiles.tile[tileNum1].collision || gamePanel.tiles.tile[tileNum2].collision){
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.getSpeed())/gamePanel.tileSize;
                tileNum1 = gamePanel.tiles.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tiles.mapTileNum[entityLeftCol][entityBottomRow];
                if(gamePanel.tiles.tile[tileNum1].collision || gamePanel.tiles.tile[tileNum2].collision){
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.getSpeed())/gamePanel.tileSize;
                tileNum1 = gamePanel.tiles.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tiles.mapTileNum[entityRightCol][entityBottomRow];
                if(gamePanel.tiles.tile[tileNum1].collision || gamePanel.tiles.tile[tileNum2].collision) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }
    // #8
    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int i = 0; i < gamePanel.obj.length; i++){
            if(gamePanel.obj[i] != null) {
                // entity's solid area position
                entity.solidArea.y = entity.getY() + entity.solidArea.y;
                // object's solid area position
                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].getX() + gamePanel.obj[i].solidArea.x;
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].getY() + gamePanel.obj[i].solidArea.y;

                switch(entity.getDirection()) {
                    case "up":
                        entity.solidArea.y -= entity.getSpeed();
                        if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if(gamePanel.obj[i].collision) {
                                entity.setCollisionOn(true);
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.getSpeed();
                        if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if(gamePanel.obj[i].collision) {
                                entity.setCollisionOn(true);
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.getSpeed();
                        if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if(gamePanel.obj[i].collision) {
                                entity.setCollisionOn(true);
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.getSpeed();
                        if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if(gamePanel.obj[i].collision) {
                                entity.setCollisionOn(true);
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.getsolidAreaDefaultX();
                entity.solidArea.y = entity.getsolidAreaDefaultY();
                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].getsolidAreaDefaultX();
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].getsolidAreaDefaultY();
            }
        }
        return index;
    }
}
