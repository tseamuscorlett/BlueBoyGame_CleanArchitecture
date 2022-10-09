// #7
package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjDoor extends SuperObject {
    public ObjDoor() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/door.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}