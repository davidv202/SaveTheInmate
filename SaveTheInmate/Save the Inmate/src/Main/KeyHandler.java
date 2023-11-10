package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, dashPressed, enterPressed, shotPressed, restartPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // TITLE STATE
        if(gp.gameState == gp.titleState) {

            if(code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }

            if(code == KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }

            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
                if(gp.ui.commandNum == 0) {
                    gp.player.newGame();
                    gp.gameState = gp.playState;
                    gp.player.createScoresTable();
                }
                if(gp.ui.commandNum == 1) {
                    gp.gameState = gp.playState;
                    gp.player.loadScoresFromDatabase();
                }
                if(gp.ui.commandNum == 2) {
//                    gp.player.saveScoresToDatabase();
                    gp.gameState = gp.saveState;
                    gp.ui.saveCommandNum = -1;
//                    System.exit(0);
                }
            }
        }

        // SAVE STATE
        if(gp.gameState == gp.saveState) {
            if (code == KeyEvent.VK_UP) {
                gp.ui.saveCommandNum--;
                if (gp.ui.saveCommandNum < 0) {
                    gp.ui.saveCommandNum = 1;
                }
            }
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.saveCommandNum++;
                if (gp.ui.saveCommandNum > 1) {
                    gp.ui.saveCommandNum = 0;
                }
            }

            if(code == KeyEvent.VK_ENTER) {
                enterPressed = true;

                if(gp.ui.saveCommandNum == 0) {
                    gp.player.saveScoresToDatabase();
                    System.out.println("Saved");
                    System.exit(0);
                }
                if(gp.ui.saveCommandNum == 1) {
                    System.exit(0);
                }
            }
        }

        // PLAY STATE
        if(gp.gameState == gp.playState) {
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }

            if(code == KeyEvent.VK_S){
                downPressed = true;
            }

            if(code == KeyEvent.VK_A){
                leftPressed = true;
            }

            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }

            if(code == KeyEvent.VK_SPACE && upPressed || code == KeyEvent.VK_SPACE && downPressed ||
               code == KeyEvent.VK_SPACE && rightPressed || code == KeyEvent.VK_SPACE && leftPressed) {
                dashPressed = true;
            }

//            if(code == KeyEvent.VK_ESCAPE){
//                gp.gameState = gp.pauseState;
//            }

            if(code == KeyEvent.VK_R) {
                gp.gameState = gp.restartLevelState;
                gp.ui.showMessage("   Are you sure you want to\n        restart game?\n\nR for yes        ENTER to exit");
            }

            if(code == KeyEvent.VK_F) {
                shotPressed = true;
            }

            if(code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.optionState;
            }

            if(code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }
        }

        // PAUSE STATE
        else if(gp.gameState == gp.pauseState) {
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
        }
        // DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState) {
            if(code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
            if(code == KeyEvent.VK_Y) {
                gp.restartGame();
            }
        }

        // RESTART LEVEL STATE
        else if(gp.gameState == gp.restartLevelState) {
            if(code == KeyEvent.VK_R) {
                gp.restartGame();
                restartPressed = true;
            }
            if(code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }

        // SCORE STATE
        else if(gp.gameState == gp.scoreState) {
            if(code == KeyEvent.VK_ENTER) {
                if (gp.currentMap == 0) {
                    gp.eHandler.teleport(1, 22, 44);
                    gp.gameState = gp.playState;
                }
                else if(gp.currentMap == 1) {
                    gp.eHandler.teleport(2, 24, 43);
                    gp.gameState = gp.playState;
                    gp.player.lightUpdated = true;
                }
                else if(gp.currentMap == 2) {
                    gp.eHandler.teleport(3, 24, 30);
                    gp.gameState = gp.playState;
                    gp.player.lightUpdated = false;
                }
            }
        }

        // OPTION STATE
        else if(gp.gameState == gp.optionState) {
            if(code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
            if(code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }

            int maxCommandNum = 0;
            switch (gp.ui.subState) {
                case 0: maxCommandNum = 3; break;
                case 3: maxCommandNum = 1; break;
            }

            if(code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = maxCommandNum;
                }
            }

            if(code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > maxCommandNum) {
                    gp.ui.commandNum = 0;
                }
            }
        }

        // GAME OVER STATE
        if(gp.gameState == gp.gameOverState) {
            if(code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.titleState;
                gp.ui.commandNum = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }

        if(code == KeyEvent.VK_S){
            downPressed = false;
        }

        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }

        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }

        if(code == KeyEvent.VK_SPACE){
            dashPressed = false;
        }

        if(code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }

        if(code == KeyEvent.VK_F) {
            shotPressed = false;
        }

        if(code == KeyEvent.VK_R) {
            restartPressed = false;
        }
    }
}
