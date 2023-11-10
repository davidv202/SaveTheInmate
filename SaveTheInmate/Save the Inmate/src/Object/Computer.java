package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Computer extends SuperObject {

    public Computer() {

        name = "Computer";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/computer/computer.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }

    @Override
    public SuperObject CreateObject(Objects type) {
        return null;
    }
}