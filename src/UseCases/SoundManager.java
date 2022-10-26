package UseCases;

import entities.Sound;

public class SoundManager {
    Sound music = new Sound();
    Sound se = new Sound();

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}




