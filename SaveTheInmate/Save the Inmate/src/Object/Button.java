package Object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Button extends SuperObject {

    public Button() {

        name = "Button";
        solidArea.x = 8;
        solidArea.y = 8;
        solidArea.width = 32;
        solidArea.height = 32;

        try {
            BufferedImage butonTileSet = ImageIO.read(getClass().getResourceAsStream("/objects/buton/butonTileSet.png"));

            image = butonTileSet.getSubimage(0, 0, spriteWidth, spriteHeight);
            image2 = butonTileSet.getSubimage(spriteWidth, 0, spriteWidth, spriteHeight);
            auximg = image;

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SuperObject CreateObject(Objects type) {
        return null;
    }
}
