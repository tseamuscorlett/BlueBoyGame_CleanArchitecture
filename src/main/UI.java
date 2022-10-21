// #10, called "UI" in original playlist
package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.ObjPhotons;

    public class UI implements Drawable {
    private final GamePanel gp;
    private final Font arial_40, arial_80B;
    private final BufferedImage photonsImage;
    public boolean gameFinished = false;  // encapsulate!!
    private double playTime;
    private final DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public boolean staminaOut = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        ObjPhotons photons = new ObjPhotons();
        photonsImage = photons.image;
    }

    public void draw(Graphics2D g2) {
        if (gp.gameState == gp.titleState) {
            drawTitleScreen(g2);
        }

        if (gp.gameState == gp.pauseState) {  // #13 add Pause gimmick
            drawPauseScreen(g2);
        }

        if (gp.gameState == gp.playState) {
            if (staminaOut) {
                g2.setFont(arial_80B);
                g2.setColor(Color.white);

                String text = "Out of Photons!";
                int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                int x = gp.screenWidth / 2 - textLength / 2;
                int y = gp.screenHeight / 2 - (gp.tileSize * 3);
                g2.drawString(text, x, y);

                gp.gameThread = null;

            } else if (gameFinished) {
                // end screen
                g2.setFont(arial_80B);
                g2.setColor(Color.white);

                // separate declaration to have multiple text messages
                String text;
                int x;
                int y;

                // end message 1
                text = "Stage Clear!";
                x = getXForCenteredText(g2, text);
                y = gp.screenHeight / 2 - (gp.tileSize * 3);
                g2.drawString(text, x, y);

                // end message 2
                text = "Your time: " + decimalFormat.format(playTime);
                x = getXForCenteredText(g2, text);
                y = gp.screenHeight / 2 - (gp.tileSize);
                g2.drawString(text, x, y);

                gp.gameThread = null;

            } else {
                // play screen message
                g2.setFont(arial_40);
                g2.setColor(Color.white);
                g2.drawImage(photonsImage, 16, 0, gp.tileSize, gp.tileSize, null);
                g2.drawString("= " + gp.player.life, 72, 40);

                // TIME
                playTime += (double) 1 / 60;
                g2.drawString("Time: " + decimalFormat.format(playTime), gp.tileSize * 11, 40);
            }
        }

    }


        // some helpers for different GAME STATES #13
        private void drawPauseScreen(Graphics2D g2) {
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
            String text = "PAUSED";
            int x = getXForCenteredText(g2, text);
            int y = gp.screenHeight/2;
            g2.drawString(text, x, y);
        }

        private int getXForCenteredText(Graphics2D g2, String text) {
            int x;
            int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - length/2;
            return x;
        }

        // Title Screen #17
        private void drawTitleScreen(Graphics2D g2) {
            String text;
            int x;
            int y;

            // title
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
            text = "Blue Boy Adventure";
            x = getXForCenteredText(g2, text);
            y = gp.tileSize*3;
            // shadow
            g2.setColor(Color.gray);
            g2.drawString(text, x+5, y+5);
            // main font color
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // PRESS ENTER
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
            text = "PRESS ENTER TO PLAY";
            x = getXForCenteredText(g2, text);
            y = gp.tileSize*5;
            g2.drawString(text, x, y);

            // blue boy image
            x = gp.screenWidth/2 - (gp.tileSize*2)/2;
            y += gp.tileSize*2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
        }
    }
