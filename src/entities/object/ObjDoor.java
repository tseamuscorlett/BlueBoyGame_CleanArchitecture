// #7
package entities.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjDoor extends SuperObject {
    public ObjDoor() {
        setName("Door");
        try {
            setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/blackhole.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}