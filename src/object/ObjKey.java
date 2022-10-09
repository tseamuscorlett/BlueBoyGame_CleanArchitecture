// #7
package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjKey extends SuperObject {
    public ObjKey() {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/key.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
