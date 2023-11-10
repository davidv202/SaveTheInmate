package Enemy;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.util.Random;

public class EnemyYellow extends Entity {

    public EnemyYellow(GamePanel gp) {
        super(gp);

        type = 2;
        direction = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        getEnemyImage();
    }

    public void getEnemyImage() {

        try {
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/enemy/yellowEnemyTileSet.png"));
            BufferedImage dizzySprite = ImageIO.read(getClass().getResourceAsStream("/enemy/yellowEnemyDizzy.png"));

            int spriteWidth = gp.originalTileSize;
            int spriteHeight = gp.originalTileSize;

            down1 = spriteSheet.getSubimage(0, 0, spriteWidth, spriteHeight);
            down2 = spriteSheet.getSubimage(spriteWidth, 0, spriteWidth, spriteHeight);
            up1 = spriteSheet.getSubimage(2 * spriteWidth, 0, spriteWidth, spriteHeight);
            up2 = spriteSheet.getSubimage(3 * spriteWidth, 0, spriteWidth, spriteHeight);
            left1 = spriteSheet.getSubimage(4 * spriteWidth, 0, spriteWidth, spriteHeight);
            left2 = spriteSheet.getSubimage(5 * spriteWidth, 0, spriteWidth, spriteHeight);
            right1 = spriteSheet.getSubimage(6 * spriteWidth, 0, spriteWidth, spriteHeight);
            right2 = spriteSheet.getSubimage(7 * spriteWidth, 0, spriteWidth, spriteHeight);
            dizzy1 = dizzySprite.getSubimage(0, 0, spriteWidth, spriteHeight);
            dizzy2 = dizzySprite.getSubimage(spriteWidth, 0, spriteWidth, spriteHeight);
            dizzy3 = dizzySprite.getSubimage(2 * spriteWidth, 0, spriteWidth, spriteHeight);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if (tileDistance < 5) {
            if (hasLineOfSight()) {
                int i = new Random().nextInt(100) + 1;
                if(i > 50) {
                    onPath = true;
                    speed = 2;
                }
            } else {
                onPath = false;
                speed = 1;
            }
        }
        if (onPath == true && tileDistance > 3) {
            onPath = false;
            speed = 1;
        }
    }

    public void setAction() {

        if (onPath == true) {

            if( hasLineOfSight() ) {
                int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
                int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
                searchPath(goalCol, goalRow);
            }
        } else {
            actionLockCounter++;

            if (actionLockCounter == 50) {
                Random random = new Random();

                int i = random.nextInt(100) + 1; // alege un numar de la 1 la 100

                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75) {
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        super.draw(g2);
    }
}
