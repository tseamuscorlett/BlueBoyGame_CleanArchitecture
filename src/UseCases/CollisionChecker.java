package UseCases;

import entities.Entity;
import entities.Maze;

public class CollisionChecker {
    Maze maze;
    public CollisionChecker(Maze maze){this.maze = maze;}

    // #6
    public void checkTile(Entity entity){
        // collision coordinates
        // define entity collision areas; we don't need World, just Screen
        int entityLeftX = entity.getX() + entity.solidArea.x;
        int entityRightX = entity.getX() + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.getY() + entity.solidArea.y;
        int entityBottomY = entity.getY() + entity.solidArea.y + entity.solidArea.height;

        // define col & row numbers
        int entityLeftCol = entityLeftX/ maze.tileSize;
        int entityRightCol = entityRightX/ maze.tileSize;
        int entityTopRow = entityTopY/ maze.tileSize;
        int entityBottomRow = entityBottomY/ maze.tileSize;

        int tileNum1, tileNum2;  // only need to check 2 tiles

        switch(entity.getDirection()){
            case "up":
                entityTopRow = (entityTopY - entity.getSpeed())/ maze.tileSize;
                tileNum1 = maze.tiles.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = maze.tiles.mapTileNum[entityRightCol][entityTopRow];
                if(maze.tiles.tile[tileNum1].collision || maze.tiles.tile[tileNum2].collision){
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.getSpeed())/ maze.tileSize;
                tileNum1 = maze.tiles.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = maze.tiles.mapTileNum[entityRightCol][entityBottomRow];
                if(maze.tiles.tile[tileNum1].collision || maze.tiles.tile[tileNum2].collision){
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.getSpeed())/ maze.tileSize;
                tileNum1 = maze.tiles.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = maze.tiles.mapTileNum[entityLeftCol][entityBottomRow];
                if(maze.tiles.tile[tileNum1].collision || maze.tiles.tile[tileNum2].collision){
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.getSpeed())/ maze.tileSize;
                tileNum1 = maze.tiles.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = maze.tiles.mapTileNum[entityRightCol][entityBottomRow];
                if(maze.tiles.tile[tileNum1].collision || maze.tiles.tile[tileNum2].collision) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }
    // #8
    public int checkObject(Entity entity, boolean player){
        int index = - 1;
        for(int i = 0; i < maze.obj.length; i++){
            if(maze.obj[i] != null) {
                // entity's solid area position x & y
                entity.solidArea.x = entity.getX() + entity.solidArea.x;
                entity.solidArea.y = entity.getY() + entity.solidArea.y;
                // object's solid area position x & y
                maze.obj[i].solidArea.x = maze.obj[i].getX() + maze.obj[i].solidArea.x;
                maze.obj[i].solidArea.y = maze.obj[i].getY() + maze.obj[i].solidArea.y;

                switch(entity.getDirection()) {
                    case "up":
                        entity.solidArea.y -= entity.getSpeed();
                        if(entity.solidArea.intersects(maze.obj[i].solidArea)) {
                            if(maze.obj[i].collision) {
                                entity.setCollisionOn(true);
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.getSpeed();
                        if(entity.solidArea.intersects(maze.obj[i].solidArea)) {
                            if(maze.obj[i].collision) {
                                entity.setCollisionOn(true);
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.getSpeed();
                        if(entity.solidArea.intersects(maze.obj[i].solidArea)) {
                            if(maze.obj[i].collision) {
                                entity.setCollisionOn(true);
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.getSpeed();
                        if(entity.solidArea.intersects(maze.obj[i].solidArea)) {
                            if(maze.obj[i].collision) {
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
                maze.obj[i].solidArea.x = maze.obj[i].getsolidAreaDefaultX();
                maze.obj[i].solidArea.y = maze.obj[i].getsolidAreaDefaultY();
            }
        }
        return index;
    }
}
