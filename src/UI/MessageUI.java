// #10, called "UI" in original playlist
package UI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import entities.GamePanel;
import entities.Player;
import entities.object.ObjPhotons;
import controller_presenter.GameController;

public class MessageUI {
    private final GameController gp;
    private final GamePanel gamePanel;
    private final Player player;
    private final Font arial_40, arial_80B;
    private final BufferedImage photonsImage;
    public double playTime;
    private final DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public MessageUI(GameController gp, GamePanel gamePanel, Player player) {
        this.gp = gp;
        this.gamePanel = gamePanel;
        this.player = player;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        ObjPhotons photons = new ObjPhotons();
        photonsImage = photons.getImage();
    }

    public void drawScreen(Graphics2D g2) {
        if (gp.gameState == gp.titleState) {
            drawTitleScreen(g2);
        }

        if (gp.gameState == gp.pauseState) {  // #13 add Pause gimmick
            drawPauseScreen(g2);
        }

        if (gp.gameState == gp.playState) {
            if (gp.playerManager.staminaOut) {
                drawStaminaOutScreen(g2);
                gp.gameThread = null;

            } else if (gp.playerManager.stageClear) {
                drawStageClearScreen(g2);
                gp.gameThread = null;

            } else {
                // play screen message
                g2.setFont(arial_40);
                g2.setColor(Color.white);
                g2.drawImage(photonsImage, 16, 0, gamePanel.tileSize, gamePanel.tileSize, null);
                g2.drawString("= " + player.getLife(), 72, 40);

                // TIME
                playTime += (double) 1 / 60;
                g2.drawString("Time: " + decimalFormat.format(playTime), gamePanel.tileSize * 11, 40);
            }
        }
    }

        private void drawStageClearScreen(Graphics2D g2) {
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
            y = gamePanel.screenHeight / 2 - (gamePanel.tileSize * 3);
            g2.drawString(text, x, y);

            // end message 2
            text = "Your time: " + decimalFormat.format(playTime);
            x = getXForCenteredText(g2, text);
            y = gamePanel.screenHeight / 2 - (gamePanel.tileSize);
            g2.drawString(text, x, y);

            // end message 3
            g2.setFont(arial_40);
            text = "Press Enter to Replay";
            x = getXForCenteredText(g2, text);
            y = gamePanel.tileSize * 7;
            g2.drawString(text, x, y);
        }

        private void drawStaminaOutScreen(Graphics2D g2) {
            g2.setFont(arial_80B);
            g2.setColor(Color.white);
            String text;
            int x;
            int y;

            // top message
            text = "Out of Photons!";
            x = getXForCenteredText(g2, text);
            y = gamePanel.screenHeight / 2 - (gamePanel.tileSize * 3);
            g2.drawString(text, x, y);

            // bottom message
            g2.setFont(arial_40);
            text = "Press Enter to Replay";
            x = getXForCenteredText(g2, text);
            y = gamePanel.screenHeight / 2 - (gamePanel.tileSize);
            g2.drawString(text, x, y);
        }


        // some helpers for different GAME STATES #13
        private void drawPauseScreen(Graphics2D g2) {
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
            String text = "PAUSED";
            int x = getXForCenteredText(g2, text);
            int y = gamePanel.screenHeight/2;
            g2.drawString(text, x, y);
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
            y = gamePanel.tileSize*3;
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
            y = gamePanel.tileSize*5;
            g2.drawString(text, x, y);

            // blue boy image
            x = gamePanel.screenWidth/2 - (gamePanel.tileSize*2)/2;
            y += gamePanel.tileSize*2;
            g2.drawImage(player.getDown1(), x, y, gamePanel.tileSize*2, gamePanel.tileSize*2, null);
        }

    private int getXForCenteredText(Graphics2D g2, String text) {
        int x;
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gamePanel.screenWidth/2 - length/2;
        return x;
    }
}
