package Enemy;

import Entity.Entity;
import Main.GamePanel;
import Object.EnemyProjectile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EnemyWhite extends Entity {

    public EnemyWhite(GamePanel gp) {
        super(gp);

        type = 1;
        direction = "down";
        speed = 1;
        shotCounter = 120;

        projectile = new EnemyProjectile(gp);

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

            BufferedImage dizzySprite = ImageIO.read(getClass().getResourceAsStream("/enemy/whiteEnemyDizzy.png"));

            int spriteWidth = gp.originalTileSize;
            int spriteHeight = gp.originalTileSize;

            dizzy1 = dizzySprite.getSubimage(0, 0, spriteWidth, spriteHeight);
            dizzy2 = dizzySprite.getSubimage(spriteWidth, 0, spriteWidth, spriteHeight);
            dizzy3 = dizzySprite.getSubimage(2 * spriteWidth, 0, spriteWidth, spriteHeight);
            idle = ImageIO.read(getClass().getResourceAsStream("/enemy/whiteEnemy.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        setAction();

        if(isShotEnemy == true) {
            dizzyTimer--;
            if(dizzyTimer == 119) {
                shotScore++;
            }
            if (dizzyTimer <= 0) {
                dizzyTimer = 120;
                isShotEnemy = false;
            }
        }

        if(shotCounter < 200) {
            shotCounter++;
        }
    }

    public void setAction() {

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if (hasLineOfSight() == true && tileDistance < 5 && projectile.alive == false && shotCounter == 200) {

            if(gp.currentMap == 1 && gp.enemy[gp.currentMap][6].isShotEnemy == false && gp.enemy[gp.currentMap][13].isShotEnemy == false) {
                projectile.set(worldX, worldY, direction, true, this);
                gp.projectileList.add(projectile);
                shotCounter = 0;
            }
            if(gp.currentMap == 2 && gp.enemy[gp.currentMap][18].isShotEnemy == false && gp.enemy[gp.currentMap][22].isShotEnemy == false && gp.enemy[gp.currentMap][24].isShotEnemy == false && gp.enemy[gp.currentMap][26].isShotEnemy == false && gp.enemy[gp.currentMap][27].isShotEnemy == false && gp.enemy[gp.currentMap][29].isShotEnemy == false && gp.enemy[gp.currentMap][31].isShotEnemy == false) {
                projectile.set(worldX, worldY, direction, true, this);
                gp.projectileList.add(projectile);
                shotCounter = 0;
            }

        }


    }

    @Override
    public void draw(Graphics2D g2) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(isShotEnemy == true) {

            BufferedImage[] images = {dizzy1, dizzy2, dizzy3};

            int interval = 100;

            int currentImageIndex = (int) ((System.currentTimeMillis() / interval) % images.length);

            if(gp.gameState == gp.pauseState) {
                images[currentImageIndex] = dizzy1;
            }

            if(gp.gameState == gp.restartLevelState) {
                images[currentImageIndex] = dizzy1;
            }

            g2.drawImage(images[currentImageIndex], screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
        else {
            g2.drawImage(idle, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
