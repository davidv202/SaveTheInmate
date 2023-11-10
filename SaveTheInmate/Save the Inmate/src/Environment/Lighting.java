package Environment;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lighting {

    private GamePanel gp;
    private BufferedImage darknessFilter;

    public Lighting(GamePanel gp) {
        this.gp = gp;
        setLightSource();
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(darknessFilter, 0, 0, null);
    }

    public void setLightSource() {

        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        if(gp.currentMap == 0 || gp.currentMap == 1 || gp.currentMap == 3) {
            //g2.setColor(new Color(0,0,0,0.31f));
            int centerX = gp.player.screenX + (gp.tileSize)/2;
            int centerY = gp.player.screenY + (gp.tileSize)/2;

            Color color[] = new Color[5];
            float fraction[] = new float[5];

            color[0] = new Color(0, 0, 0, 0f);
            color[1] = new Color(0, 0, 0, 0.25f);
            color[2] = new Color(0, 0, 0, 0.5f);
            color[3] = new Color(0, 0, 0, 0.75f);
            color[4] = new Color(0, 0, 0, 0.98f);

            fraction[0] = 0f;
            fraction[1] = 0.25f;
            fraction[2] = 0.5f;
            fraction[3] = 0.75f;
            fraction[4] = 1f;

            // create a graduation paint settings for the light circle
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.tileSize*35, fraction, color);

            // set the gradient data on g2
            g2.setPaint(gPaint);
        }
        else if(gp.currentMap == 2) {
            // get the center x and y of the light circle
            int centerX = gp.player.screenX + (gp.tileSize)/2;
            int centerY = gp.player.screenY + (gp.tileSize)/2;

            // create a graduation effect within the light circle
            Color color[] = new Color[5];
            float fraction[] = new float[5];

            color[0] = new Color(0, 0, 0, 0f);
            color[1] = new Color(0, 0, 0, 0.25f);
            color[2] = new Color(0, 0, 0, 0.5f);
            color[3] = new Color(0, 0, 0, 0.75f);
            color[4] = new Color(0, 0, 0, 0.98f);

            fraction[0] = 0f;
            fraction[1] = 0.25f;
            fraction[2] = 0.5f;
            fraction[3] = 0.75f;
            fraction[4] = 1f;

            // create a graduation paint settings for the light circle
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, 170, fraction, color);

            // set the gradient data on g2
            g2.setPaint(gPaint);
        }

        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.dispose();
    }

    public void update() {
        if(gp.player.lightUpdated == true) {
            setLightSource();
//            gp.player.lightUpdated = false;
        }
        else {
            setLightSource();
        }
    }
}
