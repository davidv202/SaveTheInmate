package Object;

import Entity.Projectile;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ArrowProjectile extends Projectile {

    GamePanel gp;

    public ArrowProjectile(GamePanel gp) {
        super(gp);

        this.gp = gp;

        speed = 9;
        maxLife = 40;
        life = maxLife;
        alive = false;

        getImage();
    }

    public void getImage() {
        try {
            BufferedImage arrowProjectileSprite = ImageIO.read(getClass().getResourceAsStream("/projectiles/sageataProiectil.png"));

            int spriteWidth = gp.originalTileSize;
            int spriteHeight = gp.originalTileSize;

            up1 = arrowProjectileSprite.getSubimage(0, 0, spriteWidth, spriteHeight);
            up2 = arrowProjectileSprite.getSubimage(0, 0, spriteWidth, spriteHeight);
            down1 = arrowProjectileSprite.getSubimage(spriteWidth, 0, spriteWidth, spriteHeight);
            down2 = arrowProjectileSprite.getSubimage(spriteWidth, 0, spriteWidth, spriteHeight);
            right1 = arrowProjectileSprite.getSubimage(2 * spriteWidth, 0, spriteWidth, spriteHeight);
            right2 = arrowProjectileSprite.getSubimage(2 * spriteWidth, 0, spriteWidth, spriteHeight);
            left1 = arrowProjectileSprite.getSubimage(3 * spriteWidth, 0, spriteWidth, spriteHeight);
            left2 = arrowProjectileSprite.getSubimage(3 * spriteWidth, 0, spriteWidth, spriteHeight);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
