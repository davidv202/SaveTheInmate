package Main;

import java.io.*;

public class Config {

    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Config"));

            // FULL SCREEN
            if(gp.fullScreenOn == true) {
                bw.write("ON");
            }
            else {
                bw.write("OFF");
            }
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("Config"));

            String s = br.readLine();

            // FULL SCREEN
            if(s.equals("ON")) {
                gp.fullScreenOn = true;
            }
            if(s.equals("OFF")) {
                gp.fullScreenOn = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
