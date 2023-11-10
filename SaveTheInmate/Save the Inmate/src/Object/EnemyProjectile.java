package Object;

import Entity.Projectile;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EnemyProjectile extends Projectile {

    GamePanel gp;

    public EnemyProjectile(GamePanel gp) {

        super(gp);

        this.gp = gp;

        speed = 2;
        maxLife = 120;
        life = maxLife;
        alive = false;

        getImage();
    }

    public void getImage() {
        try {
            BufferedImage enemyProjectile = ImageIO.read(getClass().getResourceAsStream("/projectiles/enemyProjectile.png"));

            int spriteWidth = gp.originalTileSize;
            int spriteHeight = gp.originalTileSize;

            up1 = enemyProjectile.getSubimage(0, 0, spriteWidth, spriteHeight);
            up2 = enemyProjectile.getSubimage(spriteWidth, 0, spriteWidth, spriteHeight);
            down1 = enemyProjectile.getSubimage(2 * spriteWidth, 0, spriteWidth, spriteHeight);
            down2 = enemyProjectile.getSubimage(3 * spriteWidth, 0, spriteWidth, spriteHeight);
            right1 = enemyProjectile.getSubimage(4 * spriteWidth, 0, spriteWidth, spriteHeight);
            right2 = enemyProjectile.getSubimage(5 * spriteWidth, 0, spriteWidth, spriteHeight);
            left1 = enemyProjectile.getSubimage(6 * spriteWidth, 0, spriteWidth, spriteHeight);
            left2 = enemyProjectile.getSubimage(7 * spriteWidth, 0, spriteWidth, spriteHeight);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
