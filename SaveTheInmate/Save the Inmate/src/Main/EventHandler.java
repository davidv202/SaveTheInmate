package Main;

public class EventHandler {

    public GamePanel gp;
    public EventRect[][][] eventRect;

    private int prevEventX, prevEventY;
    private boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {

        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;

        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {

            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 17;
            eventRect[map][col][row].height = 17;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;

                if(row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent() {

        // VERIFICAM DACA JUCATORUL S-A MISCAT CEL PUTIN 1 TILE DUPA CE S-A PETRECUT ULTIMUL EVENT
        int xDistance = Math.abs(gp.player.worldX - prevEventX);
        int yDistance = Math.abs(gp.player.worldY - prevEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent == true) {
            // NIVEL 1
            if(gp.currentMap == 0) {
                // INTERACTIUNE JUCATOR CU BUTOANE
                // PENTRU PRIMUL NIVEL
                if (gp.obj[gp.currentMap][4].image != null && gp.obj[gp.currentMap][4].image2 != null) {
                    if (hit(0, 31, 20, "any")) {
                        turnoffVeticalLaser(4, gp.playState);
                    }
                } else {
                    if (hit(0, 31, 20, "any")) {
                        turnOnVerticalLaser(4, gp.playState);
                    }
                }

                if (gp.obj[gp.currentMap][5].image != null && gp.obj[gp.currentMap][5].image2 != null) {
                    if (hit(0, 29, 15, "any")) {
                        turnoffVeticalLaser(5, gp.playState);
                    }
                } else {
                    if (hit(0, 29, 15, "any")) {
                        turnOnVerticalLaser(5, gp.playState);
                    }
                }

                if (gp.obj[gp.currentMap][8].image3 == null && gp.obj[gp.currentMap][8].image4 == null) {
                    if (hit(0, 31, 20, "any")) {
                        turnOnHorizontalLaser(8, gp.playState);
                    }
                } else {
                    if (hit(0, 31, 20, "any")) {
                        turnOffHorizontalLaser(8, gp.playState);
                    }
                }

                // INTERACTIUNE JUCATOR CU LASERE
                // PENTRU PRIMUL NIVEL
                if (hit(0, 29, 20, "left") || hit(0, 29, 21, "left")) {

                    if (gp.obj[gp.currentMap][4].image != null && gp.obj[gp.currentMap][4].image2 != null) {
                        gp.player.isDizzy = true;
                        gp.player.pushBackDirection = "right";
                    }
                }

                if (hit(0, 29, 20, "right") || hit(0, 29, 21, "right")) {

                    if (gp.obj[gp.currentMap][4].image != null && gp.obj[gp.currentMap][4].image2 != null) {
                        gp.player.isDizzy = true;
                        gp.player.pushBackDirection = "left";
                    }
                }

                if (hit(0, 28, 22, "down") || hit(0, 27, 22, "down")) {
                    if (gp.obj[gp.currentMap][8].image3 != null && gp.obj[gp.currentMap][8].image4 != null) {
                        gp.player.isDizzy = true;
                        gp.player.pushBackDirection = "up";
                    }
                }

                if (hit(0, 28, 22, "up") || hit(0, 27, 22, "up")) {
                    if (gp.obj[gp.currentMap][8].image3 != null && gp.obj[gp.currentMap][8].image4 != null) {
                        gp.player.isDizzy = true;
                        gp.player.pushBackDirection = "down";
                    }
                }

                if (hit(0, 28, 17, "left") || hit(0, 28, 18, "left")) {

                    if (gp.obj[gp.currentMap][5].image != null && gp.obj[gp.currentMap][5].image2 != null) {
                        gp.player.isDizzy = true;
                        gp.player.pushBackDirection = "right";
                    }
                }

                if (hit(0, 28, 17, "right") || hit(0, 28, 18, "right")) {

                    if (gp.obj[gp.currentMap][5].image != null && gp.obj[gp.currentMap][5].image2 != null) {
                        gp.player.isDizzy = true;
                        gp.player.pushBackDirection = "left";
                    }
                }

                // INTERACTIUNE INAMICI  CU LASERE
//                if (hitEnemy(0, 29, 20, "left") || hitEnemy(0, 29, 21, "left")) {
//
//                    if (gp.obj[gp.currentMap][4].image != null && gp.obj[gp.currentMap][4].image2 != null) {
//                        for (int i = 0; i < gp.enemy[1].length; i++) {
//                            if (gp.enemy[gp.currentMap][i] != null) {
//                                gp.enemy[gp.currentMap][i].isDizzyEnemy = true;
//                                gp.enemy[gp.currentMap][i].pushBackDirection = "right";
//
//                            }
//                        }
//                    }
//                }
//
//                if (hitEnemy(0, 29, 20, "right") || hitEnemy(0, 29, 21, "right")) {
//
//                    if (gp.obj[gp.currentMap][4].image != null && gp.obj[gp.currentMap][4].image2 != null) {
//                        for (int i = 0; i < gp.enemy[1].length; i++) {
//                            if (gp.enemy[gp.currentMap][i] != null) {
//                                gp.enemy[gp.currentMap][i].isDizzyEnemy = true;
//                                gp.enemy[gp.currentMap][i].pushBackDirection = "left";
//
//                            }
//                        }
//                    }
//                }
//
//                if (hitEnemy(0, 28, 22, "down") || hitEnemy(0, 27, 22, "down")) {
//                    if (gp.obj[gp.currentMap][8].image3 != null && gp.obj[gp.currentMap][8].image4 != null) {
//                        for (int i = 0; i < gp.enemy[1].length; i++) {
//                            if (gp.enemy[gp.currentMap][i] != null) {
//                                gp.enemy[gp.currentMap][i].isDizzyEnemy = true;
//                                gp.enemy[gp.currentMap][i].pushBackDirection = "up";
//
//                            }
//                        }
//                    }
//                }
//
//                if (hitEnemy(0, 28, 22, "up") || hitEnemy(0, 27, 22, "up")) {
//                    if (gp.obj[gp.currentMap][8].image3 != null && gp.obj[gp.currentMap][8].image4 != null) {
//                        for (int i = 0; i < gp.enemy[1].length; i++) {
//                            if (gp.enemy[gp.currentMap][i] != null) {
//                                gp.enemy[gp.currentMap][i].isDizzyEnemy = true;
//                                gp.enemy[gp.currentMap][i].pushBackDirection = "down";
//
//                            }
//                        }
//                    }
//                }
//
//                if (hitEnemy(0, 28, 17, "left") || hitEnemy(0, 28, 18, "left")) {
//                    if (gp.obj[gp.currentMap][5].image != null && gp.obj[gp.currentMap][5].image2 != null) {
//                        for (int i = 0; i < gp.enemy[1].length; i++) {
//                            if (gp.enemy[gp.currentMap][i] != null) {
//                                gp.enemy[gp.currentMap][i].isDizzyEnemy = true;
//                                gp.enemy[gp.currentMap][i].pushBackDirection = "right";
//
//                            }
//                        }
//                    }
//                }
//
//                if (hitEnemy(0, 28, 17, "right") || hitEnemy(0, 28, 18, "right")) {
//                    if (gp.obj[gp.currentMap][5].image != null && gp.obj[gp.currentMap][5].image2 != null) {
//                        for (int i = 0; i < gp.enemy[1].length; i++) {
//                            if (gp.enemy[gp.currentMap][i] != null) {
//                                gp.enemy[gp.currentMap][i].isDizzyEnemy = true;
//                                gp.enemy[gp.currentMap][i].pushBackDirection = "left";
//
//                            }
//                        }
//                    }
//                }

                int index = -1;
                for(int i = 0; i < gp.enemy[1].length; i++) {
                    if (gp.enemy[gp.currentMap][i] != null) {
                        if (gp.obj[gp.currentMap][5].image != null && gp.obj[gp.currentMap][5].image2 != null) {
                            if (hitEnemy(0, 29, 20, "right") || hitEnemy(0, 29, 21, "right")) {
                                index = i;
                            }
                        }
                    }
                }
                if(index != -1) {

                    gp.enemy[gp.currentMap][index].isDizzyEnemy = true;
                    gp.enemy[gp.currentMap][index].pushBackDirection = "left";
                    index = -1;
                }


                // INTERACTIUNE PLAYER CU TELEPORTER
                if (hit(0, 35, 14, "any") == true) {
                    gp.gameState = gp.scoreState;
                    gp.ui.showMessage("\n\n\n      You have completed LEVEL 1\n\n            qty    points\n\n                 x       =\n\n                 x       =\n\n                 x       =\n       -------------------------\n     LEVEL 1 SCORE\n\n\n     Press ENTER to continue...");
                }
            }

            // NIVEL 2
            else if(gp.currentMap == 1) {

                // INTERACTIUNE PROIECTILE CU BUTOANE
                if(gp.obj[gp.currentMap][21].image5 != null && gp.obj[gp.currentMap][21].image6 != null) {
                    if(hitProjectile(1, 13, 16)) {
                        gp.obj[gp.currentMap][21].image5 = null;
                        gp.obj[gp.currentMap][21].image6 = null;
                    }
                }
                else {
                    if(hitProjectile(1, 13, 16)) {
                        gp.obj[gp.currentMap][21].image5 = gp.obj[gp.currentMap][21].auximg3;
                        gp.obj[gp.currentMap][21].image6 = gp.obj[gp.currentMap][21].auximg4;
                    }
                }


                // INTERACTIUNE PLAYER CU BUTOANE
                if (gp.obj[gp.currentMap][23].image != null && gp.obj[gp.currentMap][23].image2 != null) {
                    if (hit(1, 27, 4, "any")) {
                        turnoffVeticalLaser(23, gp.playState);
                    }
                } else {
                    if (hit(1, 27, 4, "any")) {
                        turnOnVerticalLaser(23, gp.playState);
                    }
                }

                if(gp.obj[gp.currentMap][21].image5 != null && gp.obj[gp.currentMap][21].image6 != null) {
                    if(hit(1, 13, 16, "any")) {
                        turnOffHorizontalLaser(21, gp.playState);
                    }
                } else {
                    if(hit(1, 13, 16, "any")) {
                        turnOnHorizontalLaser(21, gp.playState);
                    }
                }

                // INTERACTIUNE PLAYER CU LASERE
                if (hit(1, 12, 18, "down") || hit(1, 13, 18, "down")) {
                    if (gp.obj[gp.currentMap][21].image5 != null && gp.obj[gp.currentMap][21].image6 != null) {
                        gp.player.isDizzy = true;
                        gp.player.pushBackDirection = "up";
                    }
                }

                if (hit(1, 12, 18, "up") || hit(1, 13, 18, "up")) {
                    if (gp.obj[gp.currentMap][21].image5 != null && gp.obj[gp.currentMap][21].image6 != null) {
                        gp.player.isDizzy = true;
                        gp.player.pushBackDirection = "down";
                    }
                }

                if (hit(1, 24, 11, "left") || hit(1, 24, 12, "left")) {

                    if (gp.obj[gp.currentMap][23].image != null && gp.obj[gp.currentMap][23].image2 != null) {
                        gp.player.isDizzy = true;
                        gp.player.pushBackDirection = "right";
                    }
                }

                if (hit(1, 24, 11, "right") || hit(1, 24, 12, "right")) {

                    if (gp.obj[gp.currentMap][23].image != null && gp.obj[gp.currentMap][23].image2 != null) {
                        gp.player.isDizzy = true;
                        gp.player.pushBackDirection = "left";
                    }
                }

                // INTERACTIUNE PLAYER CU TELEPORTER
                if (hit(1, 37, 10, "any") == true) {
                    gp.gameState = gp.scoreState;
                    gp.ui.showMessage("\n\n\n      You have completed LEVEL 2\n\n            qty    points\n\n                 x       =\n\n                 x       =\n\n                 x       =\n       -------------------------\n     LEVEL 2 SCORE\n\n\n     Press ENTER to continue...");
                }
            }

            // NIVEL 3
            else if(gp.currentMap == 2) {

                // INTERACTIUNE PLAYER CU BUTOANE
                if(gp.obj[gp.currentMap][62].image7 != null && gp.obj[gp.currentMap][62].image8 != null) {
                    if(hit(2, 18, 36, "any")) {
                        turnOffHorizontalLaser(62, gp.playState);
                    }
                } else {
                    if(hit(2, 18, 36, "any")) {
                        turnOnHorizontalLaser(62, gp.playState);
                    }
                }

                if(gp.obj[gp.currentMap][62].image7 != null && gp.obj[gp.currentMap][62].image8 != null) {
                    if(hit(2, 31, 44, "any")) {
                        turnOffHorizontalLaser(64, gp.playState);
                    }
                } else {
                    if(hit(2, 31, 44, "any")) {
                        turnOnHorizontalLaser(64, gp.playState);
                    }
                }

                if(gp.obj[gp.currentMap][65].image != null && gp.obj[gp.currentMap][65].image2 != null) {
                    if(hit(2, 18, 44, "any")) {
                        turnoffVeticalLaser(65, gp.playState);
                    }
                } else {
                    if(hit(2, 18, 44, "any")) {
                        turnOnVerticalLaser(65, gp.playState);
                    }
                }

                // INTERACTIUNE PLAYER CU LASERE
                if (hit(2, 37, 13, "up") || hit(2, 38, 13, "up")) {
                    if (gp.obj[gp.currentMap][62].image7 != null && gp.obj[gp.currentMap][62].image8 != null) {
                        gp.player.isDizzy = true;
                        gp.player.pushBackDirection = "down";
                    }
                }
                if (hit(2, 11, 8, "up") || hit(2, 12, 8, "up")) {
                    if (gp.obj[gp.currentMap][64].image7 != null && gp.obj[gp.currentMap][64].image8 != null) {
                        gp.player.isDizzy = true;
                        gp.player.pushBackDirection = "down";
                    }
                }
                if (hit(2, 16, 6, "right") || hit(2, 16, 7, "right")) {
                    if (gp.obj[gp.currentMap][65].image != null && gp.obj[gp.currentMap][65].image2 != null) {
                        gp.player.isDizzy = true;
                        gp.player.pushBackDirection = "left";
                    }
                }

                // INTERACTIUNE PLAYER CU TELEPORTER
                if (hit(2, 19, 18, "any") == true) {
                    gp.gameState = gp.scoreState;
                    gp.ui.showMessage("\n\n\n      You have completed LEVEL 3\n\n            qty    points\n\n                 x       =\n\n                 x       =\n\n                 x       =\n       -------------------------\n     LEVEL 3 SCORE\n\n\n     Press ENTER to continue...");
                }
            }
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection) {

        boolean hit = false;

        if(map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    prevEventX = gp.player.worldX;
                    prevEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }

    public boolean hitEnemy(int map, int col, int row, String reqDirection) {

        boolean hitEnemy = false;

        if(map == gp.currentMap) {
            for (int i = 0; i < gp.enemy[1].length; i++) {

                if (gp.enemy[gp.currentMap][i] != null) {

                    gp.enemy[gp.currentMap][i].solidArea.x = gp.enemy[gp.currentMap][i].worldX + gp.enemy[gp.currentMap][i].solidArea.x;
                    gp.enemy[gp.currentMap][i].solidArea.y = gp.enemy[gp.currentMap][i].worldY + gp.enemy[gp.currentMap][i].solidArea.y;
                    eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
                    eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

                    if (gp.enemy[gp.currentMap][i].solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                        if (gp.enemy[gp.currentMap][i].direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                            hitEnemy = true;

                            prevEventX = gp.enemy[gp.currentMap][i].worldX;
                            prevEventY = gp.enemy[gp.currentMap][i].worldY;
                        }
                    }

                    gp.enemy[gp.currentMap][i].solidArea.x = gp.enemy[gp.currentMap][i].solidAreaDefaultX;
                    gp.enemy[gp.currentMap][i].solidArea.y = gp.enemy[gp.currentMap][i].solidAreaDefaultY;
                    eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
                    eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
                }
            }
        }
        return hitEnemy;
    }

    public boolean hitProjectile(int map, int col, int row) {

        boolean hitProjectile = false;

        if(map == gp.currentMap) {
            for (int i = 0; i < gp.projectileList.size(); i++) {

                if (gp.projectileList.get(i) != null) {

                    gp.projectileList.get(i).solidArea.x = gp.projectileList.get(i).worldX + gp.projectileList.get(i).solidArea.x;
                    gp.projectileList.get(i).solidArea.y = gp.projectileList.get(i).worldY + gp.projectileList.get(i).solidArea.y;
                    eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
                    eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

                    if (gp.projectileList.get(i).solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                        hitProjectile = true;
                        prevEventX = gp.projectileList.get(i).worldX;
                        prevEventY = gp.projectileList.get(i).worldY;

                    }

                    gp.projectileList.get(i).solidArea.x = gp.projectileList.get(i).solidAreaDefaultX;
                    gp.projectileList.get(i).solidArea.y = gp.projectileList.get(i).solidAreaDefaultY;
                    eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
                    eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
                }
            }
        }
        return hitProjectile;
    }

    public void turnOnVerticalLaser(int i, int gameState) {

        gp.gameState = gameState;
        gp.obj[gp.currentMap][i].image = gp.obj[gp.currentMap][i].auximg;
        gp.obj[gp.currentMap][i].image2 = gp.obj[gp.currentMap][i].auximg2;
        canTouchEvent = false;
    }

    public void turnoffVeticalLaser(int i, int gameState) {

        gp.gameState = gameState;
        gp.obj[gp.currentMap][i].image = null;
        gp.obj[gp.currentMap][i].image2 = null;
        canTouchEvent = false;
    }

    public void turnOnHorizontalLaser(int i, int gameState) {

        gp.gameState = gameState;
        if(gp.currentMap == 0) {
            gp.obj[gp.currentMap][i].image3 = gp.obj[gp.currentMap][i].auximg3;
            gp.obj[gp.currentMap][i].image4 = gp.obj[gp.currentMap][i].auximg4;
        }
        else if(gp.currentMap == 1) {
            gp.obj[gp.currentMap][i].image5 = gp.obj[gp.currentMap][i].auximg3;
            gp.obj[gp.currentMap][i].image6 = gp.obj[gp.currentMap][i].auximg4;
        }
        else if(gp.currentMap == 2) {
            gp.obj[gp.currentMap][i].image7 = gp.obj[gp.currentMap][i].auximg3;
            gp.obj[gp.currentMap][i].image8 = gp.obj[gp.currentMap][i].auximg4;
        }
        canTouchEvent = false;
    }

    public void turnOffHorizontalLaser(int i, int gameState) {

        gp.gameState = gameState;
        if(gp.currentMap == 0) {
            gp.obj[gp.currentMap][i].image3 = null;
            gp.obj[gp.currentMap][i].image4 = null;
        }
        else if(gp.currentMap == 1) {
            gp.obj[gp.currentMap][i].image5 = null;
            gp.obj[gp.currentMap][i].image6 = null;
        }
        else if(gp.currentMap == 2) {
            gp.obj[gp.currentMap][i].image7 = null;
            gp.obj[gp.currentMap][i].image8 = null;
        }
        canTouchEvent = false;
    }

    public void teleport(int map, int col, int row) {
        gp.currentMap = map;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
        gp.player.direction = "down";
        prevEventX = gp.player.worldX;
        prevEventY = gp.player.worldY;

        // RESETARE SCOR
        gp.player.arrowScore = 0;
        gp.player.dizzyScore = 0;

        // RESETARE SAGETI SI DASH
        gp.player.hasArrow = 0;
        gp.player.dashCooldown = 0;
        canTouchEvent = false;
    }
}
