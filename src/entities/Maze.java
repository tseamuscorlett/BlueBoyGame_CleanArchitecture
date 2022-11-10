package entities;

import entities.object.SuperObject;
import entities.tile.Tiles;

public class Maze {
    // PANEL SETTINGS　
    private final int originalTileSize = 16;
    private final int scale = 3;
    public final int tileSize = originalTileSize * scale;  // public for Player to access #3
    public final int maxScreenCol = 16;  // public for TileManager to access #4
    public final int maxScreenRow = 12;  // public #4
    public final int screenWidth = tileSize * maxScreenCol;  // public #4
    public final int screenHeight = tileSize * maxScreenRow;  // public #4


    // variables
    public SuperObject[] obj = new SuperObject[10];
    public Tiles tiles = new Tiles(this);  // #4
}
