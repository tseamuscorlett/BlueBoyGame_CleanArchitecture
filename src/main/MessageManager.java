// #10, called "UI" in original playlist
package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.ObjPhotons;

    public class MessageManager implements Drawable {
    private final GamePanel gp;
    private final Font arial_40, arial_80B;
    private final BufferedImage photonsImage;
    public boolean gameFinished = false;  // encapsulate!!
    private double playTime;
    private final DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public MessageManager(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        ObjPhotons photons = new ObjPhotons();
        photonsImage = photons.image;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (gameFinished) {
            // end screen
            g2.setFont(arial_80B);
            g2.setColor(Color.white);

            // separate declaration to have multiple text messages
            String text;
            int textLength;
            int x;
            int y;

            // end message 1
            text = "Stage Clear!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            // end message 2
            text = "Your time: " + decimalFormat.format(playTime);
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        } else {
            // play screen message (before end)
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(photonsImage, 16, 0, gp.tileSize, gp.tileSize, null);
            g2.drawString("= 100", 72, 40);

            // TIME
            playTime +=(double)1/60;
            g2.drawString("Time: " + decimalFormat.format(playTime), gp.tileSize*11, 40);
        }
    }
}
