package Entity;

import Main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public GamePanel gp;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    // VARIABILE PENTRU POZITIONAREA ENTITATILOR
    public int worldX, worldY;
    public int speed;
    public boolean canMove = true;

    // VARIABILE PENTRU IMAGINILE ENTITATILOR
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, idle, dizzy1, dizzy2, dizzy3;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    // VARIABILE PENTRU AMETEALA ENTITATILOR
    public boolean isDizzy = false;
    public int dizzyTimer = 120;
    public int dizzyEnemyTimer = 400;
    public String pushBackDirection;
    public boolean isDizzyEnemy = false;
    public boolean isShotEnemy = false;

    // VARIABILE PENTRU DASH-UL ENTITATILOR
    public int dashCounter = 0;
    public int dashDuration = 6;
    public boolean isDashing = false;
    public int dashCooldown = 0;

    // VARIABILE PENTRU COLIZIUNEA ENTITATILOR CU PERETI SI OBIECTE
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public boolean collisionOn = false;

    // TIPUL ENTITATILOR
    public int type; // 0 - player, 1 - white enemy, 2 - yellow enemy, 3 - red enemy, 4 - inmate

    // COUNTER FOLOSIT PENTRU AI-ul INAMICILOR(CAT DE DES POT SCHIMBA EI DIRECTIA RANDOM)
    public int actionLockCounter = 0;

    // VARIABILA FOLOSITA PENTRU A STI CAND INAMICII URMARESC CARACTERUL
    public boolean onPath;

    // VARIBILE FOLOSITE PENTRU PROIECTILE
    public boolean alive = false;
    public int maxLife;
    public int life;
    public Projectile projectile;
    public int shotCounter = 0;
    public boolean isShot = false;
    public int shotTimer = 120;

    // VARIABLIE PENTRU STOCAREA SCORULUI
    public int arrowScore = 0;
    public int shotScore = 0;
    public int dizzyScore = 0;

    public void draw(Graphics2D g2) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        BufferedImage image = null;

        // "DESENAM" INAMICUL DOAR DACA ACESTA SE AFLA IN CADRUL VISUAL AL PLAYER-ULUI(pentru o mai buna eficienta a renderingului)
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch(direction) {
                case "up":
                    if(spriteNum == 1) {
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum == 1) {
                        image = down1;
                    }
                    if(spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1) {
                        image = left1;
                    }
                    if(spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if(spriteNum == 1) {
                        image = right1;
                    }
                    if(spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }

            if(isDizzyEnemy == true || isShotEnemy == true) {

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
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        }
    }

    public void setAction() {}

    public void checkCollision() {

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if((this.type == 1 || this.type == 2 || this.type == 3) && contactPlayer == true) {
            // RESTART LEVEL
            gp.gameState = gp.restartLevelState;
            gp.ui.showMessage("  You got caught by guards!\n\n          Game Over!\n     Press R to restart!");
        }
    }

    public void update() {

        setAction();
        checkCollision();
        gp.eHandler.checkEvent();

        // DACA COLIZIUNEA ESTE FALSE, INAMICUL SE POATE MISCA
        if (canMove == true && collisionOn == false && isDizzyEnemy == false && isShotEnemy == false) {

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

        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if(isDizzyEnemy == true) {
            dizzyEnemyTimer--;
            if (dizzyEnemyTimer <= 0) {
                dizzyEnemyTimer = 400;
                isDizzyEnemy = false;
            }
            if(dizzyEnemyTimer > 370) {
                switch(pushBackDirection) {
                    case "up":
                        worldY -= 1;
                        break;
                    case "down":
                        worldY += 1;
                        break;
                    case "left":
                        worldX -= 1;
                        break;
                    case "right":
                        worldX += 1;
                        break;
                }
            }
        }

        if(isShotEnemy == true) {
            dizzyEnemyTimer--;
            if(dizzyEnemyTimer == 399) {
                shotScore++;
            }
            if (dizzyEnemyTimer <= 0) {
                dizzyEnemyTimer = 120;
                isShotEnemy = false;
            }
        }

    }

    public void searchPath(int goalCol, int goalRow) {

        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);

        if(gp.pFinder.search() == true) {

            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                if(enLeftX > nextX) {
                    direction = "left";
                }
                if(enLeftX < nextX) {
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX) {
                direction = "up";
                checkCollision();
                if(collisionOn == true) {
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX) {
                direction = "up";
                checkCollision();
                if(collisionOn == true) {
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX) {
                direction = "down";
                checkCollision();
                if(collisionOn == true) {
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX) {
                direction = "down";
                checkCollision();
                if(collisionOn == true) {
                    direction = "right";
                }
            }

            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            if(nextCol == goalCol && nextRow == goalRow) {
                onPath = false;
            }
        }
    }

    public boolean hasLineOfSight() {

        int enemyCol = (worldX + solidArea.x) / gp.tileSize;
        int enemyRow = (worldY + solidArea.y) / gp.tileSize;
        int playerCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
        int playerRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

        if (enemyCol == playerCol) {
            // Check vertical line of sight
            int startRow = Math.min(enemyRow, playerRow);
            int endRow = Math.max(enemyRow, playerRow);
            for (int row = startRow + 1; row < endRow; row++) {
                if (gp.tileM.collisionMap[gp.currentMap][enemyCol][row] == 1) {
                    return false;
                }
            }
        } else if (enemyRow == playerRow) {
            // Check horizontal line of sight
            int startCol = Math.min(enemyCol, playerCol);
            int endCol = Math.max(enemyCol, playerCol);
            for (int col = startCol + 1; col < endCol; col++) {
                if (gp.tileM.collisionMap[gp.currentMap][col][enemyRow] == 1) {
                    return false;
                }
            }
        } else {

            int colDiff = Math.abs(enemyCol - playerCol);
            int rowDiff = Math.abs(enemyRow - playerRow);
            int colStep = (enemyCol < playerCol) ? 1 : -1;
            int rowStep = (enemyRow < playerRow) ? 1 : -1;

            int col = enemyCol;
            int row = enemyRow;

            while (col != playerCol && row != playerRow) {
                col += colStep;
                row += rowStep;

                if (gp.tileM.collisionMap[gp.currentMap][col][row] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

}

