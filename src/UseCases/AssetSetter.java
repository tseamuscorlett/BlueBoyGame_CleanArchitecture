package UseCases;

import entities.GamePanel;
import entities.object.ObjDoor;
import entities.object.ObjKey;
import entities.object.ObjPhotons;
import entities.object.ObjPotion;

public class AssetSetter {
    private final GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.obj[0] = new ObjKey();
        gamePanel.obj[0].setX(5 * gamePanel.tileSize);  // not WorldX
        gamePanel.obj[0].setY(5 * gamePanel.tileSize);

        gamePanel.obj[1] = new ObjDoor();
        gamePanel.obj[1].setX(14 * gamePanel.tileSize);
        gamePanel.obj[1].setY(10 * gamePanel.tileSize);

        gamePanel.obj[2] = new ObjPotion();
        gamePanel.obj[2].setX(14 * gamePanel.tileSize);
        gamePanel.obj[2].setY(7 * gamePanel.tileSize);

        gamePanel.obj[3] = new ObjPhotons();
        gamePanel.obj[3].setX(4 * gamePanel.tileSize);
        gamePanel.obj[3].setY(8 * gamePanel.tileSize);
    }
}
