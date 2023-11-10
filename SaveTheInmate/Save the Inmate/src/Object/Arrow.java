package Object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Arrow extends SuperObject {

    public Arrow() {

        name = "Arrow";
        try{
            BufferedImage sageataTileSet = ImageIO.read(getClass().getResourceAsStream("/objects/sageata/sageataTileSet.png"));
            image = sageataTileSet.getSubimage(0, 0, spriteWidth, spriteHeight);
            image2 = sageataTileSet.getSubimage(spriteWidth, 0, spriteWidth, spriteHeight);
            image3 = sageataTileSet.getSubimage(spriteWidth * 2, 0, spriteWidth, spriteHeight);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SuperObject CreateObject(Objects type) {
        return null;
    }
}
