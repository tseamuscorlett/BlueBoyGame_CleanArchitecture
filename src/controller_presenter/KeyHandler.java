// handles keyboard input #2
package controller_presenter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Controller
public class KeyHandler implements KeyListener {
    // #13 link with Game Controller
    GameController gc;
    public KeyHandler(GameController gc) {
        this.gc = gc;
    }

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // titleState
        if (gc.gameState == gc.titleState) {
            if(code == KeyEvent.VK_ENTER) {
                gc.gameState = gc.playState;
            }
        }

        // playState
        if (gc.gameState == gc.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_SPACE) {   // #13
                gc.gameState = gc.pauseState;
                }
            }

        // pauseState
        else if (gc.gameState == gc.pauseState) {
            if (code == KeyEvent.VK_SPACE) {   // #13
                gc.gameState = gc.playState;
            }
        }

        if (gc.playerManager.staminaOut) {                // # 37 (Seamus' original implementation)
            if (code == KeyEvent.VK_ENTER) {
                gc.gameState = gc.titleState;
                gc.playerManager.staminaOut = false;
                gc.playerManager.setDefaultValues();
                // gc.music.stop();
                gc.mUI.playTime = 0;

                gc.setupGame();
                gc.startGameThread();
            }
        }

        if (gc.playerManager.stageClear) {                // # 37 (Seamus' original implementation)
            if (code == KeyEvent.VK_ENTER) {
                gc.gameState = gc.titleState;
                gc.playerManager.stageClear = false;
                gc.playerManager.setDefaultValues();
                // gc.music.stop();
                gc.mUI.playTime = 0;

                gc.setupGame();
                gc.startGameThread();
            }
        }
        }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
