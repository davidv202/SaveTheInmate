package Object;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject {

    public BufferedImage image, image2, image3, image4, image5, image6, image7, image8, auximg, auximg2, auximg3, auximg4, laserbutton;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public int spriteWidth = 16;
    public int spriteHeight = 16;

    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public void drawLaser(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        BufferedImage images[] = {image, image2};

        // Intervalul de timp intre care se schimba imaginile - in milisecunde
        int interval = 100;

        int currentImageIndex = (int) ((System.currentTimeMillis() / interval) % images.length);

        if(gp.gameState == gp.pauseState || gp.gameState == gp.dialogueState ||
           gp.gameState == gp. restartLevelState || gp.gameState == gp.scoreState) {

            images[currentImageIndex] = image;
        }

        g2.drawImage(images[currentImageIndex], screenX, screenY, gp.tileSize, gp.tileSize * 3, null);
    }

    public void drawLaser2(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(gp.currentMap == 0) {
            BufferedImage[] images = {image3, image4};

            // Intervalul de timp intre care se schimba imaginile - in milisecunde
            int interval = 100;

            int currentImageIndex = (int) ((System.currentTimeMillis() / interval) % images.length);

            if (gp.gameState == gp.pauseState || gp.gameState == gp.dialogueState ||
                    gp.gameState == gp.restartLevelState || gp.gameState == gp.scoreState) {
                images[currentImageIndex] = image3;
            }

            g2.drawImage(images[currentImageIndex], screenX, screenY, gp.tileSize * 2, gp.tileSize, null);
        }

        else if(gp.currentMap == 1) {
            BufferedImage[] images = {image5, image6};

            // Intervalul de timp intre care se schimba imaginile - in milisecunde
            int interval = 100;

            int currentImageIndex = (int) ((System.currentTimeMillis() / interval) % images.length);

            if (gp.gameState == gp.pauseState || gp.gameState == gp.dialogueState ||
                gp.gameState == gp.restartLevelState || gp.gameState == gp.scoreState) {
                images[currentImageIndex] = image5;
            }

            g2.drawImage(images[currentImageIndex], screenX, screenY, gp.tileSize * 2, gp.tileSize, null);
        }

        else if(gp.currentMap == 2) {
            BufferedImage[] images = {image7, image8};

            // Intervalul de timp intre care se schimba imaginile - in milisecunde
            int interval = 100;

            int currentImageIndex = (int) ((System.currentTimeMillis() / interval) % images.length);

            if (gp.gameState == gp.pauseState || gp.gameState == gp.dialogueState ||
                    gp.gameState == gp.restartLevelState || gp.gameState == gp.scoreState) {
                images[currentImageIndex] = image7;
            }

            g2.drawImage(images[currentImageIndex], screenX, screenY, gp.tileSize * 2, gp.tileSize, null);
        }
    }

    public void drawLaserButtons(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.drawImage(laserbutton, screenX, screenY, gp.tileSize * 2, gp.tileSize, null);
    }

    public void drawTeleporter(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        BufferedImage[] images = {image, image2, image3, image4, image5, image6};

        // Intervalul de timp intre care se schimba imaginile - in milisecunde
        int interval = 120;

        int currentImageIndex = (int) ((System.currentTimeMillis() / interval) % images.length);

        if(gp.gameState == gp.pauseState || gp.gameState == gp.dialogueState ||
           gp.gameState == gp. restartLevelState || gp.gameState == gp.scoreState) {

            images[currentImageIndex] = image4;
        }

        g2.drawImage(images[currentImageIndex], screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    abstract public SuperObject CreateObject(Objects type);
}
