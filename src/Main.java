import UI.GameScreenUI;

public class Main {

    private static void setupGamePanel() {
        // 1. Instantiate GamePanelUI
        GameScreenUI gamePanelUI = new GameScreenUI();
        // 2. Call its method to create GameScreenUI
        gamePanelUI.createGameScreenUI();
    }

    public static void main(String[] args) {
        setupGamePanel();
    }
}



// this pattern is standard!!
// 1. Instantiate lower-level
// 2. Call its method
