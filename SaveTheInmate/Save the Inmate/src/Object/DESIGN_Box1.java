package Object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DESIGN_Box1 extends SuperObject{

    SuperObject obj;

    public DESIGN_Box1() {

        name = "DesignBox1";

        try {
            BufferedImage bI = ImageIO.read(getClass().getResourceAsStream("/objects/design/design1.png"));

            image = bI.getSubimage(2 * spriteWidth, 0, spriteWidth, spriteHeight);

        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }

    @Override
    public SuperObject CreateObject(Objects type) {
        return null;
    }
}
