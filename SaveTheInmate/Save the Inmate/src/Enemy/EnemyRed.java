package Enemy;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EnemyRed extends Entity {

    public EnemyRed(GamePanel gp) {
        super(gp);

        type = 3;
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
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/enemy/redEnemyTileSet.png"));
            BufferedImage dizzySprite = ImageIO.read(getClass().getResourceAsStream("/enemy/redEnemyDizzy.png"));

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
            idle = ImageIO.read(getClass().getResourceAsStream("/enemy/redEnemyIdle.png"));


        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;

        if (tileDistance < 3) {
            if (hasLineOfSight()) {
                onPath = true;
                speed = 3;
            } else {
                onPath = false;
            }
        }
        if (onPath == true && tileDistance > 3) {
            onPath = false;
            super.update();
        }
    }

    public void setAction() {

        if (onPath == true) {

            if( hasLineOfSight() ) {

                int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
                int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

                searchPath(goalCol, goalRow);
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(onPath == false) {
            g2.drawImage(idle, screenX, screenY, gp.tileSize, gp.tileSize, null);
            canMove = false;
        }
        else {
            super.draw(g2);
            canMove = true;
        }
    }
}
