package Tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    public GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;
    public int[][][] collisionMap;

    public TileManager(GamePanel gp) throws TileManagerException {

        this.gp = gp;
        tile = new Tile [20];

        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        collisionMap = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();

        loadMap("/maps/map01.txt", 0);
        loadMap("/maps/map02.txt", 1);
        loadMap("/maps/map03.txt", 2);
        loadMap("/maps/map04.txt", 3);

        generateCollisionMap();
    }

    public void getTileImage() throws TileManagerException {

        try {
            BufferedImage tileSheet = ImageIO.read(getClass().getResourceAsStream("/tiles/tileset.png"));

            int tileWidth = gp.originalTileSize;
            int tileHeight = gp.originalTileSize;

            int numRows = 5;
            int numCols = 4;

            for(int row = 0; row < numRows; row++) {
                for(int col = 0; col < numCols; col++) {
                    int tileIndex = row * numCols + col;
                    int x = col * tileWidth;
                    int y = row * tileHeight;

                    tile[tileIndex] = new Tile();
                    tile[tileIndex].image = tileSheet.getSubimage(x, y, tileWidth, tileHeight);

                    if (tileIndex == 0) {
                        tile[tileIndex].collision = false;
                    } else {
                        tile[tileIndex].collision = true;
                    }
                }
            }

        } catch (IOException e) {
            throw new TileManagerException();
        }
    }

    public void loadMap(String filePath, int map) {

        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while(col < gp.maxWorldCol) {

                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }

                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

            br.close();
        } catch (IOException e) {
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // "DESENAM" TILE-URILE DOAR DACA ACESTEA SE AFLA IN CADRUL VISUAL AL PLAYER-ULUI(pentru o mai buna eficienta a renderingului)
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public void generateCollisionMap() {
        for (int map = 0; map < gp.maxMap; map++) {
            for (int col = 0; col < gp.maxWorldCol; col++) {
                for (int row = 0; row < gp.maxWorldRow; row++) {
                    int tileNum = mapTileNum[map][col][row];
                    collisionMap[map][col][row] = tile[tileNum].collision ? 1 : 0;
                }
            }
        }
    }

}
