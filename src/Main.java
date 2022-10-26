import UI.GameScreenUI;

public class Main {
    public static void main(String[] args) {
        setupGameScreen();
    }

    private static void setupGameScreen() {
        GameScreenUI gameScreenUI = new GameScreenUI();   // 1. Instantiate GamePanelUI
        gameScreenUI.createGameScreenUI();                // 2. Call its method to create GameScreenUI
    }
}


// this pattern is standard!!
// 1. Instantiate lower-level
// 2. Call its method
