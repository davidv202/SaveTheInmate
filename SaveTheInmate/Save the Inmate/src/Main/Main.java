package Main;

import Tile.TileManagerException;

import javax.swing.*;

public class Main {

   private static Main instance;
   public static JFrame window;

   private Main() throws TileManagerException {

      try {
         window = new JFrame();
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         window.setResizable(false);
         window.setTitle("SaveTheInmate");

         GamePanel gamePanel = new GamePanel();
         window.add(gamePanel);

         gamePanel.config.loadConfig();
         if (gamePanel.fullScreenOn == true) {
            window.setUndecorated(true);
         }

         window.pack();

         window.setLocationRelativeTo(null);
         window.setVisible(true);

         gamePanel.setupGame();
         gamePanel.startGameThread();
      } catch (Exception e) {
         e.printStackTrace();
         throw new TileManagerException();
      }
   }

   // SABLON DE PROIECTARE SINGLETONE
   public static Main getInstance() throws TileManagerException {

      if(instance == null) {
         instance = new Main();
      }
      return instance;
   }

    public static void main(String[] args) throws TileManagerException {
          Main.getInstance();
    }
}
