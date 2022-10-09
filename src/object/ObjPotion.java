package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjPotion extends SuperObject {
    public ObjPotion() {
        name = "Potion";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/potion_red.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
