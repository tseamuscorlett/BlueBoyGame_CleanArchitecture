package controller_presenter;

import UI.MessageUI;
import UseCases.AssetSetter;
import UseCases.CollisionChecker;
import UseCases.SoundManager;
import UseCases.PlayerManager;

import entities.Maze;
import entities.Player;
import entities.object.SuperObject;
import entities.tile.Tiles;

import javax.swing.*;
import java.awt.*;

public class GameController extends JPanel implements Runnable {

    private final int FPS = 60;

    // GAME STATES  #13, 17
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;


    // initialize Entities => breaking rule
    private final Maze maze = new Maze();
    private final Tiles tiles = new Tiles(maze);  // #4
    private final Player player = new Player();


    // initialize UseCases => this is ok
    private final KeyHandler keyH = new KeyHandler(this);
    public CollisionChecker collisionChecker = new CollisionChecker(maze);  // #6
    public SoundManager soundM = new SoundManager();  // #9,10
    public final PlayerManager playerManager = new PlayerManager(collisionChecker, keyH, player, soundM);

    // what about UI? should also be ok
    public MessageUI mUI = new MessageUI(this, maze, player);  // for on-screen messages #10
    public Thread gameThread;  // for looping #2



    // Constructor with java.awt methods to set up screen
    public GameController() {
        // instead of defining a bunch of screen variables, move them to gamePanel entity
        this.setPreferredSize(new Dimension(maze.screenWidth, maze.screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);  // improve game speed
        this.setFocusable(true); // to receive key input

        this.addKeyListener(keyH); // to recognize key input "addKeyListener" is part of java.awt package
    }


    // initialise UseCases => use their methods
    public void setupGame () {
        AssetSetter assetSetter = new AssetSetter(maze);  // for setting objects #7
        assetSetter.setAssetsEasy();

        soundM.playMusic(0);  // background music #9

        gameState = titleState;   // #13, 17
    }

    // run the game & keep updating it
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Called by gameThread.start(); block to loop the game #2
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
                updatePlayer();  // helper method
                repaint();
                delta--;
            }
        }
    }

    // helper method for run()
    public void updatePlayer() {
        if (gameState == playState) {
            playerManager.update();
        }
        if (gameState == pauseState) {
            // nothing for now
        }
    }

    // Called by repaint()
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // Title Screen
        if(gameState == titleState) {
            mUI.drawScreen(g2);
        }
        // Play Screen
        else {
            tiles.draw(g2);  // draw tiles before player! #4
            // go through obj[] Array #7
            for (SuperObject superObject : maze.obj) {
                if (superObject != null) {
                    superObject.draw(g2, maze);
                }
            }
            player.draw(g2);
            mUI.drawScreen(g2);  // AFTER everything! #10
        }
        g2.dispose();
    }
}