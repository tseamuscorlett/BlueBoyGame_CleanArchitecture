package UI;

import controller_presenter.GameController;

import javax.swing.*;

public class GameScreenUI {

    public void createGameScreenUI() {
        GameController gc = new GameController();  // 1. Instantiate GameController

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("207: A Space Odyssey");
        window.add(gc);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // 2. Call its methods
        gc.setupGame();  // BEFORE starting the game thread! #7
        gc.startGameThread();
    }
}
