package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS　#1
    private final int originalTileSize = 16;  // private for enclosure
    private final int scale = 3;  // private for enclosure
    public final int tileSize = originalTileSize * scale;  // public for Player to access #3
    public final int maxScreenCol = 16;  // public for TileManager to access #4
    public final int maxScreenRow = 12;  // public #4
    public final int screenWidth = tileSize * maxScreenCol;  // public #4
    public final int screenHeight = tileSize * maxScreenRow;  // public #4

    private final int FPS = 60;  // private for enclosure

    // initialize game components
    TileManager tileM = new TileManager(this);  // #4
    private final KeyHandler keyH = new KeyHandler();
    private final SoundManager music = new SoundManager();  // #9,10
    private final SoundManager se = new SoundManager();  // #9/10
    public CollisionChecker collisionChecker = new CollisionChecker(this);  // #6
    private final AssetSetter assetSetter = new AssetSetter(this);  // for setting objects #7
    public MessageManager messagePresenter = new MessageManager(this);  // for on-screen messages #10
    Thread gameThread;  // for looping #2

    // ENTITY & OBJECT
    private final Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[10];  // #7

    // Constructor with java.awt methods to set up screen
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);  // improve game speed
        this.addKeyListener(keyH); // to recognize key input "addKeyListener" is part of java.awt package
        this.setFocusable(true); // to receive key input
    }

    // to be called by Main
    public void setupGame () {
        assetSetter.setObject();
        playMusic(0);  // background music #9
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // block to loop the game #2
    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }

    // this is for Presenter??
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);  // draw tiles before player! #4
        // go through obj[] Array #7
        for (SuperObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(g2, this);
            }
        }
        player.draw(g2);
        messagePresenter.draw(g2);  // AFTER everything! #10
        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(int i) {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}