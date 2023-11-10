package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class LaserButtons extends SuperObject{

    public LaserButtons() {

        name = "LaserButton";

        try{
            laserbutton = ImageIO.read(getClass().getResourceAsStream("/objects/laserbuton/laserbuton.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SuperObject CreateObject(Objects type) {
        return null;
    }
}
