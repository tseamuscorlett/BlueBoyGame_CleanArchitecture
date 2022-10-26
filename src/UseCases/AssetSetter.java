package UseCases;

import entities.Maze;
import entities.object.ObjDoor;
import entities.object.ObjKey;
import entities.object.ObjPhotons;
import entities.object.ObjPotion;

public class AssetSetter {
    private final Maze maze;
    public AssetSetter(Maze maze) {
        this.maze = maze;
    }

    public void setObject() {
        maze.obj[0] = new ObjKey();
        maze.obj[0].setX(5 * maze.tileSize); // 5*48 = 240
        maze.obj[0].setY(5 * maze.tileSize);

        maze.obj[1] = new ObjDoor();
        maze.obj[1].setX(14 * maze.tileSize);
        maze.obj[1].setY(10 * maze.tileSize);

        maze.obj[2] = new ObjPotion();
        maze.obj[2].setX(14 * maze.tileSize);
        maze.obj[2].setY(7 * maze.tileSize);

        maze.obj[3] = new ObjPhotons();
        maze.obj[3].setX(4 * maze.tileSize);
        maze.obj[3].setY(8 * maze.tileSize);
    }
}
