package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Object.ArrowProjectile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Player extends Entity {

    private KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasArrow = 9;
    public boolean lightUpdated = false;

    // VARIABILE PENTRU SQLITE DATABASE
    private Connection connection;
    String dbFilePath = "db/tiobe2.db";
    String absolutePath = new File(dbFilePath).getAbsolutePath();

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.keyH = keyH;

        // POZITIONAM PLAYER-UL PE MIJLOCUL ECRANULUI
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        // PARTEA SOLIDA A PLAYERULUI CARE VA INTRA IN COLIZIUNE CU PERETI, OBIECTE, ETC.
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + absolutePath);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        if(gp.currentMap == 0) {
            worldX = gp.tileSize * 34;
            worldY = gp.tileSize * 30;
        }
        if(gp.currentMap == 1) {
            worldX = gp.tileSize * 22;
            worldY = gp.tileSize * 44;
        }
        if(gp.currentMap == 2) {
            worldX = gp.tileSize * 24;
            worldY = gp.tileSize * 43;
        }
        if (gp.currentMap == 3) {
            worldX = gp.tileSize * 23;
            worldY = gp.tileSize * 15;
        }
        speed = 3;
//        speed = 5;
        direction = "down";
        projectile = new ArrowProjectile(gp);
    }

    public void newGame() {

        gp.currentMap = 0;
        gp.aSetter.setObject();
        gp.aSetter.setEnemy();
        worldX = gp.tileSize * 34;
        worldY = gp.tileSize * 30;
        hasArrow = 0;
        isDizzy = false;
        dashCooldown = 0;
        shotCounter = 50;
        isShot = false;

        // RESETAM PROIECTILELE
        for(int i = 0; i < gp.projectileList.size(); i++) {
            if(gp.projectileList.get(i) != null) {
                gp.projectileList.get(i).alive = false;
            }
        }

        // RESETAM SCORURI
        arrowScore = 0;
        for(int i = 0; i < gp.enemy[1].length; i++) {
            if(gp.enemy[gp.currentMap][i] != null) {
                gp.enemy[gp.currentMap][i].shotScore = 0;
            }
        }
        dizzyScore = 0;
        gp.ui.level1Score = 0;
        gp.ui.level2Score = 0;
        gp.ui.level3Score = 0;
        gp.ui.finalScore = 0;
    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            // VERIFICARE COLIZIUNE CU PERETI SI OBIECTE
            collisionOn = false;

            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            setButtonBack(objIndex);
            interactComputer(objIndex);

            // VERIFICARE COLIZIUNE CU INAMICI
            int enemyIndex = gp.cChecker.checkEntity(this, gp.enemy);
            interactEnemy(enemyIndex);

            gp.eHandler.checkEvent();

            // DACA COLIZIUNEA SAU STAREA "DIZZY" SUNT FALSE, CARACTERUL SE POATE MISCA
            if (collisionOn == false && isDizzy == false && isShot == false) {

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
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        // DASH SI COLIZIUNI CU DASH
        if(isDizzy == false) {
            if (!isDashing && keyH.dashPressed && dashCooldown <= 0) {
                isDashing = true;
                dashCounter = 0;
                dashCooldown = 120;
            }
            if (dashCooldown > 0) {
                dashCooldown--;
            }

            if (isDashing) {
                dashCounter++;
                if (dashCounter >= dashDuration) {
                    isDashing = false;
                    dashCounter = 0;
                }
                // JUCATORUL POATE FOLOSI DASH-UL DOAR CAND NU ESTE AMETIT
                if (isDizzy == false && isShot == false) {

                    switch (direction) {
                        case "up":
                            for (int i = 0; i < 5; i++) {
                                int prevWorldY = worldY;
                                worldY -= speed;
                                collisionOn = false;
                                gp.cChecker.checkTile(this);
                                gp.cChecker.checkObject(this, true);
                                if (collisionOn) {
                                    worldY = prevWorldY;
                                    break;
                                }
                            }
                            break;
                        case "down":
                            for (int i = 0; i < 5; i++) {
                                int prevWorldY = worldY;
                                worldY += speed;
                                collisionOn = false;
                                gp.cChecker.checkTile(this);
                                gp.cChecker.checkObject(this, true);
                                if (collisionOn) {
                                    worldY = prevWorldY;
                                    break;
                                }
                            }
                            break;
                        case "left":
                            for (int i = 0; i < 5; i++) {
                                int prevWorldX = worldX;
                                worldX -= speed;
                                collisionOn = false;
                                gp.cChecker.checkTile(this);
                                gp.cChecker.checkObject(this, true);
                                if (collisionOn) {
                                    worldX = prevWorldX;
                                    break;
                                }
                            }
                            break;
                        case "right":
                            for (int i = 0; i < 5; i++) {
                                int prevWorldX = worldX;
                                worldX += speed;
                                collisionOn = false;
                                gp.cChecker.checkTile(this);
                                gp.cChecker.checkObject(this, true);
                                if (collisionOn) {
                                    worldX = prevWorldX;
                                    break;
                                }
                            }
                            break;
                    }
                }
            }
        }

        // DIZZY
        if(isDizzy == true) {
            dizzyTimer--;
            if(dizzyTimer == 1) {
                dizzyScore++;
            }
            if (dizzyTimer <= 0) {
                dizzyTimer = 120;
                isDizzy = false;
            }
            if(dizzyTimer > 90) {
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

        // LOVIT DE PROIECTILUL INAMICULUI
        if(isShot == true) {
            shotTimer--;
            if(shotTimer == 1) {
                dizzyScore++;
            }
            if(shotTimer <= 0) {
                shotTimer = 120;
                isShot = false;
            }
        }

        // PROJECTILE
        if(isDizzy == false && isShot == false) {
            if (gp.keyH.shotPressed == true && projectile.alive == false && shotCounter == 50) {

                if (hasArrow > 0) {
                    projectile.set(worldX, worldY, direction, true, this);

                    gp.projectileList.add(projectile);

                    shotCounter = 0;

                    hasArrow--;
                }
            }
        }
        if(shotCounter < 50) {
            shotCounter++;
        }
    }

    public void pickUpObject(int i) {

        if(i != 999){

            String objectName = gp.obj[gp.currentMap][i].name;

            switch(objectName) {
                case "Arrow":
                    hasArrow++;
                    arrowScore++;
                    gp.obj[gp.currentMap][i] = null;
                    break;
                case "Button":
                    gp.obj[gp.currentMap][i].image = gp.obj[gp.currentMap][i].image2;
                    break;
            }
        }
    }

    public void setButtonBack(int i) {

        // NIVEL 1
       if(gp.currentMap == 0) {
           if (i != 3) {
               gp.obj[gp.currentMap][3].image = gp.obj[gp.currentMap][3].auximg;
           }

           if (i != 6) {
               gp.obj[gp.currentMap][6].image = gp.obj[gp.currentMap][6].auximg;
           }
       }
       // NIVEL 2
       else if(gp.currentMap == 1) {
           if (i != 26) {
               gp.obj[gp.currentMap][26].image = gp.obj[gp.currentMap][26].auximg;
           }

           if (i != 29) {
               gp.obj[gp.currentMap][29].image = gp.obj[gp.currentMap][29].auximg;
           }
       }
       // NIVEL 3
       else if(gp.currentMap == 2) {
           if (i != 43) {
               gp.obj[gp.currentMap][43].image = gp.obj[gp.currentMap][43].auximg;
           }

           if (i != 46) {
               gp.obj[gp.currentMap][46].image = gp.obj[gp.currentMap][46].auximg;
           }

           if (i != 59) {
               gp.obj[gp.currentMap][59].image = gp.obj[gp.currentMap][59].auximg;
           }
       }
    }

    public void interactComputer(int i) {

        if(gp.currentMap == 0) {
            if (i == 1) {
                gp.gameState = gp.dialogueState;
                gp.ui.showMessage("Don't let the guards see you.\nPress WASD to move. Find the\nteleporter to next floor.\nPress R to restart. Press\nENTER to exit this window.");
            }

            if (i == 2) {
                gp.gameState = gp.dialogueState;
                gp.ui.showMessage("Lasers will incapacitate anyone\nwho touches them. They also\nblock sight lines. Press ENTER\nto exit this window.");
            }

            if (i == 7) {
                gp.gameState = gp.dialogueState;
                gp.ui.showMessage("Press SPACEBAR to dash. It can\nbe used to get out of sight\nquickly. Be careful as it has\na cooldown. Press ENTER to\nexit this window.");
            }

            if (i == 11) {
                gp.gameState = gp.dialogueState;
                gp.ui.showMessage("You can pickup arrows and shoot\nthem by pressing F. If you hit\nan enemy it will become dizzy\nand you can pass him easier.\nIt also gives you points. Press\nENTER to exit this window.");
            }
        }
        if(gp.currentMap == 1) {

            if (i == 13) {
                gp.gameState = gp.dialogueState;
                gp.ui.showMessage("Be careful as White Enemy can\nshoot a projectile at you which\nwill make you dizzy. You can\nshoot them to pass easier.\nPress ENTER to exit.");
            }

            if (i == 24) {
                gp.gameState = gp.dialogueState;
                gp.ui.showMessage("You can use arrows to enable or\ndisable lasers by shooting to\nthe button. Press ENTER to exit\nthis window.");
            }
        }

        if(gp.currentMap == 2) {
            if (i == 40) {
                gp.gameState = gp.dialogueState;
                gp.ui.showMessage("          3th Floor\n\n  Walls limit visibility and\n  lights are disabled due to\n  security threat! Press ENTER\n  to exit this window.");
            }

            if (i == 44 || i == 45) {
                gp.gameState = gp.dialogueState;
                gp.ui.showMessage("Use button to deactivate one of\nthe lasers that locks your way\nto save the kid. Find the other\none if you did not yet.\nGood Luck!");
            }

            if (i == 57) {
                gp.gameState = gp.dialogueState;
                gp.ui.showMessage("       Excellent work!\n\n\n   This trap was difficult.");
            }

            if (i == 60) {
                gp.gameState = gp.dialogueState;
                gp.ui.showMessage("\n\n\n      SECRET BUTTON !!!");
            }
        }
    }

    public void interactEnemy(int i) {

        if(gp.currentMap == 0 || gp.currentMap == 1 || gp.currentMap == 2) {
            if (i != 999) {
                // RESTART LEVEL
                gp.gameState = gp.restartLevelState;
                gp.ui.showMessage("  You got caught by guards!\n\n          Game Over!\n     Press R to restart!");
            }
        }
        else if(gp.currentMap == 3) {
            if(i != 999) {
                gp.gameState = gp.gameOverState;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
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
        }

        if(isDizzy == true || isShot == true) {

            BufferedImage[] images = {dizzy1, dizzy2, dizzy3};

            int interval = 100;

            int currentImageIndex = (int) ((System.currentTimeMillis() / interval) % images.length);

            if(gp.gameState == gp. pauseState) {
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

    public void getPlayerImage() {

        try {
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/player/playersprites.png"));
            BufferedImage dizzySprite = ImageIO.read(getClass().getResourceAsStream("/player/dizzyplayer.png"));

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
            idle = ImageIO.read(getClass().getResourceAsStream("/player/playeridle.png"));
            dizzy1 = dizzySprite.getSubimage(0, 0, spriteWidth, spriteHeight);
            dizzy2 = dizzySprite.getSubimage(spriteWidth, 0, spriteWidth, spriteHeight);
            dizzy3 = dizzySprite.getSubimage(2 * spriteWidth, 0, spriteWidth, spriteHeight);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void createScoresTable() {
        try {
            Statement statement = connection.createStatement();

//            String query = "DROP TABLE IF EXISTS player";
//            statement.executeUpdate(query);
//            System.out.println("Table deleted successfully.");

            String query = "CREATE TABLE IF NOT EXISTS player ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "currentMap INTEGER,"
                    + "worldX INTEGER,"
                    + "worldY INTEGER,"
                    + "hasArrow INTEGER,"
                    + "lightUpdated INTEGER,"
                    + "level1score INTEGER,"
                    + "level2Score INTEGER,"
                    + "level3Score INTEGER,"
                    + "finalScore INTEGER"
                    + ")";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveScoresToDatabase() {
        try {
            String insertQuery = "INSERT INTO player (currentMap, worldX, worldY, hasArrow, lightUpdated, level1score, level2score, level3score, finalScore) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

            insertStatement.setInt(1, gp.currentMap);
            insertStatement.setInt(2, worldX);
            insertStatement.setInt(3, worldY);
            insertStatement.setInt(4, hasArrow);
            insertStatement.setInt(5, lightUpdated ? 1 : 0);
            insertStatement.setInt(6, gp.ui.level1Score);
            insertStatement.setInt(7, gp.ui.level2Score);
            insertStatement.setInt(8, gp.ui.level3Score);
            insertStatement.setInt(9, gp.ui.finalScore);

            // Execute the INSERT statement
            insertStatement.executeUpdate();

            // Close the statement
            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadScoresFromDatabase() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM player ORDER BY id DESC LIMIT 1");

            if (resultSet.next()) {
                gp.currentMap = resultSet.getInt("currentMap");
                worldX = resultSet.getInt("worldX");
                worldY = resultSet.getInt("worldY");
                hasArrow = resultSet.getInt("hasArrow");
                lightUpdated = resultSet.getInt("lightUpdated") == 1;
                gp.ui.level1Score = resultSet.getInt("level1score");
                gp.ui.level2Score = resultSet.getInt("level2score");
                gp.ui.level3Score = resultSet.getInt("level3score");
                gp.ui.finalScore = resultSet.getInt("finalscore");
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
