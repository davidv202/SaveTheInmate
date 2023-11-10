package Main;

        import java.awt.*;
        import java.awt.image.BufferedImage;
        import java.io.File;
        import java.io.IOException;
        import java.io.InputStream;
        import java.sql.*;

        import Object.Arrow;

        import javax.imageio.ImageIO;

public class UI {

    private GamePanel gp;
    private Graphics2D g2;
    private Font greenScr;
    BufferedImage arrowImage, triggerArrow, dash, nodash, pressedDash, dizzyEnemyImage;
    public int commandNum = 0;
    public int saveCommandNum = 0;
    public String message = "";
    public boolean messageOn = false;

    // VARIABILE FOLOSITE PENTRU PUNCTE
    public final int arrowPoints = 50;
    public final int shotPoints = 100;
    public final int dizzyPlayerPoints = -10;
    public int level1Score = 0;
    public int level2Score = 0;
    public int level3Score = 0;
    public int finalScore;

    // SUBSTATE FOLOSIT PENTRU MENIUL DE OPTIUNI
    public int subState = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/Greenscr.ttf");
            greenScr = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Arrow arrow = new Arrow();
        arrowImage = arrow.image2;
        triggerArrow = arrow.image3;

        getDashBar();
        getDizzyEnemy();
    }

    public void getDashBar() {

        try {
            dash = ImageIO.read(getClass().getResourceAsStream("/objects/dashbar/dashbar.png"));
            nodash = ImageIO.read(getClass().getResourceAsStream("/objects/dashbar/dashbar2.png"));
            pressedDash = ImageIO.read(getClass().getResourceAsStream("/objects/dashbar/dashbarapasat.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getDizzyEnemy() {

        try{
            BufferedImage sprite = ImageIO.read(getClass().getResourceAsStream("/enemy/yellowEnemyDizzy.png"));
            dizzyEnemyImage = sprite.getSubimage(0, 0, 16, 16);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void updateScore() {

        int shotScore1 = 0, shotScore2 = 0, shotScore3 = 0;
        for (int i = 0; i < gp.enemy[1].length; i++) {
            if (gp.enemy[0][i] != null) {
                shotScore1 += gp.enemy[0][i].shotScore;
            } else if (gp.enemy[1][i] != null) {
                shotScore2 += gp.enemy[1][i].shotScore;
            } else if (gp.enemy[2][i] != null) {
                shotScore3 += gp.enemy[2][i].shotScore;
            }
        }

        if (gp.currentMap == 0) {
            level1Score = gp.player.arrowScore * arrowPoints + shotScore1 * shotPoints + gp.player.dizzyScore * dizzyPlayerPoints;
        } else if (gp.currentMap == 1) {
            level2Score = gp.player.arrowScore * arrowPoints + shotScore2 * shotPoints + gp.player.dizzyScore * dizzyPlayerPoints;
        } else if (gp.currentMap == 2) {
            level3Score = gp.player.arrowScore * arrowPoints + shotScore3 * shotPoints + gp.player.dizzyScore * dizzyPlayerPoints;
        }
        finalScore = level1Score + level2Score + level3Score;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(greenScr);
        g2.setColor(Color.white);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }

        // SCORE STATE
        if(gp.gameState == gp.scoreState) {
            drawScoreScreen();
        }

        // RESTART LEVEL STATE
        if(gp.gameState == gp.restartLevelState) {
            drawDialogueScreen();
        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            g2.drawImage(nodash, gp.tileSize * 16, 380, gp.tileSize * 3, gp.tileSize, null);

            if (gp.keyH.dashPressed && gp.player.dashCounter != 0) {
                g2.drawImage(pressedDash, gp.tileSize * 16, 380, gp.tileSize * 3, gp.tileSize, null);
            }

            if (gp.player.dashCooldown == 0) {
                g2.drawImage(dash, gp.tileSize * 16, 380, gp.tileSize * 3, gp.tileSize, null);
            }

            g2.drawImage(arrowImage, gp.tileSize * 17, 460, gp.tileSize, gp.tileSize, null);
            g2.setFont(greenScr);
            g2.setFont(g2.getFont().deriveFont(30F));
//            g2.setColor(new Color(29, 183, 29));
            g2.drawString(String.valueOf(gp.player.hasArrow), (gp.tileSize * 18) - gp.tileSize/2 - 6, 450);

            updateScore(); // dezactivez pentru a merge
        }

        // SCORE STATE
        if(gp.gameState == gp.scoreState) {

            // SCORUL PENTRU SAGETI
            int x = gp.tileSize * 8 - 6;
            int y = gp.tileSize * 4 + gp.tileSize/2 + 6;

            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(String.valueOf(gp.player.arrowScore), x, y);

            // SCORUL PENTRU LOVITURI IN INAMICI
            y += gp.tileSize;

            int shotScore1 = 0, shotScore2 = 0 , shotScore3 = 0;
            for(int i = 0; i < gp.enemy[1].length; i++) {
                if(gp.enemy[0][i] != null) {
                    shotScore1 += gp.enemy[0][i].shotScore;
                }
                else if(gp.enemy[1][i] != null) {
                    shotScore2 += gp.enemy[1][i].shotScore;
                }
                else if(gp.enemy[2][i] != null) {
                    shotScore3 += gp.enemy[2][i].shotScore;
                }
            }
            if(gp.currentMap == 0) {
                g2.drawString(String.valueOf(shotScore1), x, y);
            }
            else if(gp.currentMap == 1) {
                g2.drawString(String.valueOf(shotScore2), x, y);
            }
            else if(gp.currentMap == 2) {
                g2.drawString(String.valueOf(shotScore3), x, y);
            }
            // DACA RESTARTAM NIVELUL, SCORUL VA FI 0 DIN NOU
            if(gp.gameState == gp.restartLevelState) {
                if(gp.keyH.restartPressed == true) {
                    shotScore1 = 0;
                    shotScore2 = 0;
                    shotScore3 = 0;
                }
            }
            // SCORUL PENTRU LOVITURI PLAYER IN LASERE
            y += gp.tileSize;

            g2.drawString(String.valueOf(gp.player.dizzyScore), x, y);

            // DESENARE PUNCTE PE ECRAN

            // PUNCTE PENTRU SAGETI
            x = gp.tileSize * 10 - 6;
            y = gp.tileSize * 4 + gp.tileSize/2 + 6;

            g2.drawString(String.valueOf(arrowPoints), x, y);

            // PUNCTE PENTRU LOVITURI IN INAMICI
            y += gp.tileSize;

            g2.drawString(String.valueOf(shotPoints), x, y);

            // PUNCTE PENTRU LOVITURI IN LASERE
            y += gp.tileSize;

            g2.drawString(String.valueOf(dizzyPlayerPoints), x, y);

            updateScore();

            // DESENARE REZULTATE SCORURI
            // REZULTAT PENTRU SCOR SAGETI
            x = gp.tileSize * 13;
            y = gp.tileSize * 4 + gp.tileSize/2 + 6;

            g2.drawString(String.valueOf(gp.player.arrowScore * arrowPoints), x, y);


            y += gp.tileSize;
            if(gp.currentMap == 0) {
                g2.drawString(String.valueOf(shotScore1 * shotPoints), x, y);
            }
            else if(gp.currentMap == 1) {
                g2.drawString(String.valueOf(shotScore2 * shotPoints), x, y);
            }
            else if(gp.currentMap == 2) {
                g2.drawString(String.valueOf(shotScore3 * shotPoints), x, y);
            }

            y += gp.tileSize;
            g2.drawString(String.valueOf(gp.player.dizzyScore * dizzyPlayerPoints), x, y);

            y += gp.tileSize;

            if(gp.currentMap == 0) {
                g2.drawString(String.valueOf(level1Score), x, y);
            }
            else if(gp.currentMap == 1) {
                g2.drawString(String.valueOf(level2Score), x, y);
            }
            else if(gp.currentMap == 2) {
                g2.drawString(String.valueOf(level3Score), x, y);
            }
        }

        // OPTION STATE
        if(gp.gameState == gp.optionState) {
            drawOptionScreen();
        }

        // SAVE STATE
        if(gp.gameState == gp.saveState) {
            drawSaveScreen();
        }

        // GAME OVER STATE
        if(gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }

        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2 - 20;

        x += gp.tileSize;
        y += gp.tileSize;

        if (messageOn) {

            g2.setFont(g2.getFont().deriveFont(26F));

            for(String line : message.split("\n")) {
                g2.drawString(line, x, y);
                y += gp.tileSize/2;
            }

            if(gp.gameState == gp.playState) {
                messageOn = false;
            }
        }
    }

    public void drawDialogueScreen() {

        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize/2 - 20;
        int width = gp.screenWidth - (gp.tileSize * 8) + 20;
        int height = gp.tileSize * 5;

        drawSubWindow(x, y, width, height);
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(1, 1, 14, 247);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(234, 230, 230);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

        c = new Color(29, 183, 29);
        g2.setColor(c);
    }

    public int getXforCenteredText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // LUNGIMEA TEXTULUI

        int x = gp.screenWidth/2 - length/2; // MIJLOCUL ECRANULUI

        return x;
    }

    public void drawGameOverScreen() {

        BufferedImage backgroundImage;
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/objects/backgroundTitleState.png"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        g2.drawImage(backgroundImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.setFont(g2.getFont().deriveFont(36F));
        g2.setColor(new Color(29, 183, 29));
        String text = "Congratulations!";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 4;
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(28F));
        text = "You have saved the Inmate!";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);

        text = "Press ENTER to go back to Main Menu";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);

        text = "Final Score: ";
        x = gp.tileSize * 7;
        y = gp.tileSize * 7;
        g2.drawString(text, x, y);

        x += gp.tileSize*5;
        g2.drawString(String.valueOf(finalScore), x, y);

    }

    public void drawSaveScreen() {

        BufferedImage backgroundImage;
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/objects/backgroundTitleState.png"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        g2.drawImage(backgroundImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.setFont(g2.getFont().deriveFont(32F));
        g2.setColor(new Color(29, 183, 29));
        String text = "Do you want to save progress ?";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(26F));
        y = gp.tileSize * 8;
        text = "YES";
        x = getXforCenteredText(text);
        g2.drawString(text, x, y);
        if(saveCommandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        y += gp.tileSize;
        text = "NO";
        x = getXforCenteredText(text);
        g2.drawString(text, x, y);
        if(saveCommandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawTitleScreen() {

        BufferedImage backgroundImage;
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/objects/backgroundTitleState.png"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        g2.drawImage(backgroundImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 52F));
        String text = "Save The Inmate";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        // MAIN COLOR
        g2.setColor(Color.green);
        g2.drawString(text, x, y);

        // CHARACTER IMAGE
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2 - 30;
        g2.drawImage(gp.player.idle, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4 - 30;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawScoreScreen() {

        // WINDOW
        int x = gp.tileSize * 4;
        int y = gp.tileSize/2 + gp.tileSize;
        int width = gp.screenWidth - (gp.tileSize*9);
        int height = gp.tileSize * 9;

        drawSubWindow(x, y, width, height);

        // SCORUL PENTRU SAGETI COLECTATE
        x = 6 * gp.tileSize;
        y =  4 * gp.tileSize - 6;
        g2.drawImage(arrowImage, x, y, gp.tileSize, gp.tileSize, null);

        // SCORUL PENTRU INAMICI LOVITI CU SAGETI
        y += gp.tileSize;
        g2.drawImage(dizzyEnemyImage, x, y, gp.tileSize, gp.tileSize, null);

        // SCORUL PENTRU PLAYER LOVIT DE LASER
        y += gp.tileSize;
        g2.drawImage(gp.player.dizzy1, x, y, gp.tileSize, gp.tileSize, null);
    }

    public void drawOptionScreen() {

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        // SUB WINDOW
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch(subState) {
            case 0: options_top(frameX, frameY); break;
            case 1: options_fullScreenNotification(frameX, frameY); break;
            case 2: options_controls(frameX, frameY); break;
            case 3: options_endGameConfirmation(frameX, frameY); break;
        }
        gp.keyH.enterPressed = false;
    }
    public void options_top(int frameX, int frameY) {
        int textX;
        int textY;

        // TITLE
        String text = "OPTIONS";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // FULL SCREEN ON/OFF
        g2.setFont(g2.getFont().deriveFont(24F));
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Full Screen", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                if(gp.fullScreenOn == false) {
                    gp.fullScreenOn = true;
                }
                else if(gp.fullScreenOn == true) {
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }

        // CONTROLS
        textY += gp.tileSize * 2;
        g2.drawString("Controls", textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 2;
                commandNum = 0;
            }
        }

        // END GAME
        textY += gp.tileSize * 2;
        g2.drawString("Exit to Main Menu", textX, textY);
        if(commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 3;
                commandNum = 0;
            }
        }

        // BACK
        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        // FULL SCREEN CHECK BOX
        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);
        }

        gp.config.saveConfig();
    }
    public void options_fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        message = "The change will take \neffect after you \nrestart the game!";

        for(String line: message.split("\n")) {
            g2.setFont(g2.getFont().deriveFont(22F));
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // BACK
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
            }
        }
    }
    public void options_endGameConfirmation(int frameX, int frameY) {

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        message = "Quit the game and \nreturn to the main \nmenu?";

        for(String line: message.split("\n")) {
            g2.setFont(g2.getFont().deriveFont(22F));
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // YES
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
                gp.gameState = gp.titleState;
            }
        }

        // NO
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 2;
            }
        }
    }
    public void options_controls(int frameX, int frameY) {

        int textX;
        int textY;

        // TITLE
        String text = "Controls";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        g2.setFont(g2.getFont().deriveFont(22F));
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Move", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Shoot", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Dash", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Pause", textX, textY);
        textY+= gp.tileSize;
        g2.drawString("Options", textX, textY);

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*3;
        g2.drawString("WASD", textX, textY);
        textY += gp.tileSize;
        g2.drawString("F", textX, textY);
        textY += gp.tileSize;
        g2.drawString("SPACE", textX, textY);
        textY += gp.tileSize;
        g2.drawString("P", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);

        // BACK
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("BACK", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 1;
            }
        }
    }
}
