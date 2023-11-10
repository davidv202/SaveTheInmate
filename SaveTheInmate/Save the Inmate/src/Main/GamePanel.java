package Main;

import AI.Pathfinder;
import Entity.Player;
import Environment.EnvironmentManager;
import Tile.TileManager;
import Object.SuperObject;
import Entity.Entity;
import Tile.TileManagerException;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{

    // SETARI ECRAN
    public final int originalTileSize = 16;
    private final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenLine = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenLine;

    // SETARI HARTA
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 10;
    public int currentMap = 0;

    // PENTRU FULL-SCREEN
    private int screenWidth2 = screenWidth;
    private int screenHeight2 = screenHeight;
    private BufferedImage tempScreen;
    private Graphics2D g2;
    public boolean fullScreenOn = false;

    // FPS
    private final int FPS = 60;

    // SISTEM
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public EventHandler eHandler = new EventHandler(this);
    public UI ui = new UI(this);
    public Pathfinder pFinder = new Pathfinder(this);
    public EnvironmentManager eManager = new EnvironmentManager(this);
    public Config config = new Config(this);
    private Thread gameThread;

    // ENTITY si OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject[][] obj = new SuperObject[maxMap][70];
    public Entity[][] enemy = new Entity[maxMap][40];
    public ArrayList<Entity> projectileList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int restartLevelState = 4;
    public final int scoreState = 5;
    public final int optionState = 6;
    public final int saveState = 7;
    public final int gameOverState = 8;

    public GamePanel() throws TileManagerException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObject();
        aSetter.setEnemy();
        eManager.setup();

        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();
        if(fullScreenOn == true) {
            setFullScreen();
        }
    }

    public void restartGame() {

        // RESETAM JUCATORUL, OBIECTELE SI INAMICII
        aSetter.setObject();
        aSetter.setEnemy();
        player.setDefaultValues();

        // RESETAM GAMESTATE-UL
        gameState = playState;

        // RESETAM NUMARUL DE SAGETI
        player.hasArrow = 0;

        // RESETAM STARILE JUCATORULUI(AMETEALA, DASH sau PROIECTIL)
        player.isDizzy = false;
        player.dashCooldown = 0;
        player.shotCounter = 50;
        player.isShot = false;

        // RESETAM PROIECTILELE
        for(int i = 0; i < projectileList.size(); i++) {
            if(projectileList.get(i) != null) {
                projectileList.get(i).alive = false;
            }
        }

        // RESETAM SCORURI
        player.arrowScore = 0;
        for(int i = 0; i < enemy[1].length; i++) {
            if(enemy[currentMap][i] != null) {
                enemy[currentMap][i].shotScore = 0;
            }
        }
        player.dizzyScore = 0;
    }

    public void setFullScreen() {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double)1000000000/FPS; // intervalul la care se "deseneaza" pe ecran, adica o data la 0.1666666...666 secunde
        double nextDrawTime = System.nanoTime() + drawInterval; // acesta este momentul cand va incepe GameLoop-ul

        while (gameThread != null) {

            update();
            drawToTempScreen(); // desenam totul intr-un BufferedImage
            drawToScreen(); // desenam BufferedImage-ul anterior pe ecran

            // masuram cat timp a ramas dupa apelare update() si repaint(), pentru a-l pune pe sleep
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; // transformam timpul ramas din nanosecunde in milisecunde

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                System.out.println(ui.level1Score);
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval; // urmatorul moment in care se va "desena", adica dupa 0.01666...666 secunde
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {

        if(gameState == playState){
            // PLAYER
            player.update();
            // ENEMY
            for(int i = 0; i < enemy[1].length; i++) {
                if(enemy[currentMap][i] != null) {
                    enemy[currentMap][i].update();
                }
            }

            for(int i = 0; i < projectileList.size(); i++) {

                if(projectileList.get(i) != null) {
                    if(projectileList.get(i).alive == true) {
                        projectileList.get(i).update();
                    }
                    if(projectileList.get(i).alive == false) {
                        projectileList.remove(i);
                    }
                }
            }
            eManager.update();
        }
        if(gameState == pauseState){
            // TBD
        }
    }

    public void drawToTempScreen() {

        if(gameState == titleState) {
            ui.draw(g2);
        }
        else {
            tileM.draw(g2);

            for(int i = 0; i < obj[1].length; i++) {

                if(obj[currentMap][i] != null) {

                    obj[currentMap][i].drawLaserButtons(g2, this);

                    if(obj[currentMap][i].name != "Laser" && obj[currentMap][i].name != "Teleporter") {
                        obj[currentMap][i].draw(g2, this);
                    }
                    else if(i != 8 && i != 10 && i != 19 && i != 21 && i != 62 && i != 64 && i != 68) {
                        obj[currentMap][i].drawLaser(g2, this); // desenare laser vertical
                    }
                    else if(i != 10 && i != 19 && i != 68) {
                        obj[currentMap][i].drawLaser2(g2, this); // desenare laser orizontal
                    }
                    else {
                        obj[currentMap][i].drawTeleporter(g2, this);
                    }
                }
            }

            for(int i = 0; i < enemy[1].length; i++) {

                if(enemy[currentMap][i] != null) {
                    enemy[currentMap][i].draw(g2);
                }
            }

            for(int i = 0; i < projectileList.size(); i++) {

                if(projectileList.get(i) != null) {
                    projectileList.get(i).draw(g2);
                }
            }

            player.draw(g2);

            eManager.draw(g2);

            ui.draw(g2);
        }
    }

    public void drawToScreen() {

        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }
}
