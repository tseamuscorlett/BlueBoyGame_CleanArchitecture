package main;

import object.ObjDoor;
import object.ObjKey;
import object.ObjPhotons;
import object.ObjPotion;

public class AssetSetter {
    private final GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new ObjKey();
        gp.obj[0].x = 5 * gp.tileSize;  // not WorldX
        gp.obj[0].y = 5 * gp.tileSize;

        gp.obj[1] = new ObjDoor();
        gp.obj[1].x = 14 * gp.tileSize;
        gp.obj[1].y = 10 * gp.tileSize;

        gp.obj[2] = new ObjPotion();
        gp.obj[2].x = 14 * gp.tileSize;
        gp.obj[2].y = 7 * gp.tileSize;

        gp.obj[3] = new ObjPhotons();
        gp.obj[3].x = 4 * gp.tileSize;
        gp.obj[3].y = 8 * gp.tileSize;
    }
}
