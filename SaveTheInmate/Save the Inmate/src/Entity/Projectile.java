package Entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Projectile extends Entity {

    public Entity user;

    public Projectile(GamePanel gp) {
        super(gp);

        solidArea = new Rectangle();
        solidArea.width = 16;
        solidArea.height = 16;
        solidArea.x = (gp.tileSize - solidArea.width)/2;
        solidArea.y = (gp.tileSize - solidArea.height)/2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {

        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    public void update() {

        if(user == gp.player) {
            int enemyIndex = gp.cChecker.checkEntity(this, gp.enemy);
            if(enemyIndex != 999) {
                gp.enemy[gp.currentMap][enemyIndex].isShotEnemy = true;
                alive = false;
            }
        }
        else {
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if(contactPlayer == true) {
                gp.player.isShot = true;
                alive = false;
            }

            super.update();
            int xDistance = Math.abs(worldX - gp.player.worldX);
            int yDistance = Math.abs(worldY - gp.player.worldY);
            int tileDistance = (xDistance + yDistance)/gp.tileSize;

            if(onPath == false && tileDistance < 5) {
                onPath = true;
            }
        }

        collisionOn = false;
        gp.cChecker.checkTile(this);
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);
        gp.player.setButtonBack(objIndex);

        gp.eHandler.checkEvent();

        if(collisionOn == false) {

            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
        else {
            alive = false;
        }

        life--;
        if(life <= 0) {
            alive = false;
        }

        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            }
            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public void pickUpObject(int i) {

        if(i != 999){

            String objectName = gp.obj[gp.currentMap][i].name;

            switch(objectName) {
                case "Button":
                    gp.obj[gp.currentMap][i].image = gp.obj[gp.currentMap][i].image2;
                    alive = false;
                    break;
            }
        }
    }

    public void setAction() {

        if (onPath == true) {

            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

            searchPath(goalCol, goalRow);
        }
    }
}
