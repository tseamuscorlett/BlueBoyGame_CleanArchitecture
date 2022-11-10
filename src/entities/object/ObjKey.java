// #7
package entities.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjKey extends SuperObject {
    public ObjKey() {
        setName("Key");
        try {
            setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/key1.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
