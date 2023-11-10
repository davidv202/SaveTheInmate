package Object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Teleporter extends SuperObject {

    public Teleporter(){

        name = "Teleporter";

        try{
            BufferedImage portalTileSet = ImageIO.read(getClass().getResourceAsStream("/objects/portal/portalTileSet.png"));

            image =  portalTileSet.getSubimage(0, 0, spriteWidth, spriteHeight);
            image2 = portalTileSet.getSubimage(spriteWidth, 0, spriteWidth, spriteHeight);
            image3 = portalTileSet.getSubimage(spriteWidth * 2, 0, spriteWidth, spriteHeight);
            image4 = portalTileSet.getSubimage(spriteWidth * 3, 0, spriteWidth, spriteHeight);
            image5 = portalTileSet.getSubimage(spriteWidth * 4, 0, spriteWidth, spriteHeight);
            image6 = portalTileSet.getSubimage(spriteWidth * 5, 0, spriteWidth, spriteHeight);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SuperObject CreateObject(Objects type) {
        return null;
    }
}
