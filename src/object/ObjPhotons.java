package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjPhotons extends SuperObject {
    public ObjPhotons() {
        name = "Photons";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/light1.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}