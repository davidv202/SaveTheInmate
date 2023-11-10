package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Inmate extends Entity {

    public Inmate(GamePanel gp) {
        super(gp);

        type = 4;
        direction = "down";

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        getInmateImage();
    }

    public void getInmateImage() {

        try {
            idle = ImageIO.read(getClass().getResourceAsStream("/inmate/inmate.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.drawImage(idle, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
