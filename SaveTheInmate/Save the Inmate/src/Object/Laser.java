package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Laser extends SuperObject {

    GamePanel gp;

    public Laser(GamePanel gp) {

        this.gp = gp;

        name = "Laser";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/laser/laser1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/laser/laser2.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/laser/laser3.png"));
            image4 = ImageIO.read(getClass().getResourceAsStream("/objects/laser/laser4.png"));
            auximg = image;
            auximg2 = image2;
            auximg3 = image3;
            auximg4 = image4;
            image5 = image3;
            image6 = image4;
            image7 = image3;
            image8 = image4;

        }catch (IOException e) {
            e.printStackTrace();
        }

        setDefaultLaser2();
    }

    public void setDefaultLaser2() {

        if(gp.currentMap == 0) {
            image3 = null;
            image4 = null;
        }
    }

    @Override
    public SuperObject CreateObject(Objects type) {
        return null;
    }
}
