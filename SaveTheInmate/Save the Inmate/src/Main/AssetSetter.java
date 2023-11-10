package Main;

import Enemy.EnemyRed;
import Enemy.EnemyWhite;
import Enemy.EnemyYellow;
import Entity.Inmate;
import Object.Arrow;
import Object.Computer;
import Object.Button;
import Object.Laser;
import Object.LaserButtons;
import Object.Teleporter;
import Object.SuperObjectDesign;

import static Object.Objects.box1;
import static Object.Objects.box2;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        // PRIMUL NIVEL
        int mapNum = 0;
        int i = 0;

        // 0
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;

        // 1
        gp.obj[mapNum][i] = new Computer();
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 28 * gp.tileSize;
        i++;

        // 2
        gp.obj[mapNum][i] = new Computer();
        gp.obj[mapNum][i].worldX = 36 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        // 3
        gp.obj[mapNum][i] = new Button();
        gp.obj[mapNum][i].worldX = 31 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 20 * gp.tileSize;
        i++;

        // 4
        gp.obj[mapNum][i] = new Laser(gp);
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        // 5
        gp.obj[mapNum][i] = new Laser(gp);
        gp.obj[mapNum][i].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 16 * gp.tileSize;
        i++;

        // 6
        gp.obj[mapNum][i] = new Button();
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 15 * gp.tileSize;
        i++;

        // 7
        gp.obj[mapNum][i] = new Computer();
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 23 * gp.tileSize;
        i++;

        // 8
        gp.obj[mapNum][i] = new Laser(gp);
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 22 * gp.tileSize;
        i++;

        // 9
        gp.obj[mapNum][i] = new LaserButtons();
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 22 * gp.tileSize;
        i++;

        // 10
        gp.obj[mapNum][i] = new Teleporter();
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
        i++;

        // 11
        gp.obj[mapNum][i] = new Computer();
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 17 * gp.tileSize;
        i++;

        // 12
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
        i++;

        // AL DOILEA NIVEL
        mapNum = 1;

        // 13
        gp.obj[mapNum][i] = new Computer();
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 43 * gp.tileSize;
        i++;

        // 14
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 44 * gp.tileSize;
        i++;

        // 15
        gp.obj[mapNum][i] = new SuperObjectDesign().CreateObject(box1);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 41 * gp.tileSize;
        i++;

        // 16
        gp.obj[mapNum][i] = new SuperObjectDesign().CreateObject(box1);
        gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 41 * gp.tileSize;
        i++;

        // 17
        gp.obj[mapNum][i] = new SuperObjectDesign().CreateObject(box1);
        gp.obj[mapNum][i].worldX = 36 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 44 * gp.tileSize;
        i++;

        // 18
        gp.obj[mapNum][i] = new SuperObjectDesign().CreateObject(box1);
        gp.obj[mapNum][i].worldX = 38 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 44 * gp.tileSize;
        i++;

        // 19
        gp.obj[mapNum][i] = new Teleporter();
        gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        // 20
        gp.obj[mapNum][i] = new Computer();
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 31 * gp.tileSize;
        i++;

        // 21
        gp.obj[mapNum][i] = new Laser(gp);
        gp.obj[mapNum][i].worldX = 12 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;

        // 22
        gp.obj[mapNum][i] = new LaserButtons();
        gp.obj[mapNum][i].worldX = 12 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;

        // 23
        gp.obj[mapNum][i] = new Laser(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        // 24
        gp.obj[mapNum][i] = new Computer();
        gp.obj[mapNum][i].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        // 25
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 21 * gp.tileSize;
        i++;

        // 26
        gp.obj[mapNum][i] = new Button();
        gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 16 * gp.tileSize;
        i++;

        // 27
        gp.obj[mapNum][i] = new SuperObjectDesign().CreateObject(box1);
        gp.obj[mapNum][i].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 4 * gp.tileSize;
        i++;

        // 28
        gp.obj[mapNum][i] = new SuperObjectDesign().CreateObject(box2);
        gp.obj[mapNum][i].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 5 * gp.tileSize;
        i++;

        // 29
        gp.obj[mapNum][i] = new Button();
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 4 * gp.tileSize;
        i++;

        // 30
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 5 * gp.tileSize;
        i++;

        // 31
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        // 32
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 30 * gp.tileSize;
        i++;

        // 33
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 30 * gp.tileSize;
        i++;

         // 34
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 20 * gp.tileSize;
        i++;

        // 35
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        // 36
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        // 37
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        // 38
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        // 39
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 41 * gp.tileSize;
        i++;

        mapNum = 2;

        // 40
        gp.obj[mapNum][i] = new Computer();
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 41 * gp.tileSize;
        i++;

        // 41
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 39 * gp.tileSize;
        i++;

        // 42
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 32 * gp.tileSize;
        i++;

        // 43
        gp.obj[mapNum][i] = new Button();
        gp.obj[mapNum][i].worldX = 31 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 44 * gp.tileSize;
        i++;

        // 44
        gp.obj[mapNum][i] = new Computer();
        gp.obj[mapNum][i].worldX = 31 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 42 * gp.tileSize;
        i++;

        // 45
        gp.obj[mapNum][i] = new Computer();
        gp.obj[mapNum][i].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 42 * gp.tileSize;
        i++;

        // 46
        gp.obj[mapNum][i] = new Button();
        gp.obj[mapNum][i].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 44 * gp.tileSize;
        i++;

        // 47
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 31 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 30 * gp.tileSize;
        i++;

        // 48
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 31 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 35 * gp.tileSize;
        i++;

        // 49
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;

        // 50
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;

        // 51
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;

        // 52
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;

        // 53
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        // 54
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        // 55
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        // 56
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        // 57
        gp.obj[mapNum][i] = new Computer();
        gp.obj[mapNum][i].worldX = 26 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        // 58
        gp.obj[mapNum][i] = new Arrow();
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        // 59
        gp.obj[mapNum][i] = new Button();
        gp.obj[mapNum][i].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 36 * gp.tileSize;
        i++;

        // 60
        gp.obj[mapNum][i] = new Computer();
        gp.obj[mapNum][i].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 38 * gp.tileSize;
        i++;

        // 61
        gp.obj[mapNum][i] = new LaserButtons();
        gp.obj[mapNum][i].worldX =  37 * gp.tileSize;
        gp.obj[mapNum][i].worldY =  13 * gp.tileSize;
        i++;

        // 62
        gp.obj[mapNum][i] = new Laser(gp);
        gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        // 63
        gp.obj[mapNum][i] = new LaserButtons();
        gp.obj[mapNum][i].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        // 64
        gp.obj[mapNum][i] = new Laser(gp);
        gp.obj[mapNum][i].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        // 65
        gp.obj[mapNum][i] = new Laser(gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 5 * gp.tileSize;
        i++;

        // 66
        gp.obj[mapNum][i] = new SuperObjectDesign().CreateObject(box1);
        gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 17 * gp.tileSize;
        i++;

        // 67
        gp.obj[mapNum][i] = new SuperObjectDesign().CreateObject(box2);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 17 * gp.tileSize;
        i++;

        // 68
        gp.obj[mapNum][i] = new Teleporter();
        gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;
    }

    public void setEnemy() {

        int mapNum = 0;
        int i = 0;

        // 0
        gp.enemy[mapNum][i] = new EnemyYellow(gp);
        gp.enemy[mapNum][i].worldX = 35 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        // 1
        gp.enemy[mapNum][i] = new EnemyYellow(gp);
        gp.enemy[mapNum][i].worldX = 25 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 29 * gp.tileSize;
        i++;

        // 2
        gp.enemy[mapNum][i] = new EnemyRed(gp);
        gp.enemy[mapNum][i].worldX = 24 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 23 * gp.tileSize;
        i++;

        // 3
        gp.enemy[mapNum][i] = new EnemyYellow(gp);
        gp.enemy[mapNum][i].worldX = 13 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 32 * gp.tileSize;
        i++;

        // 4
        gp.enemy[mapNum][i] = new EnemyRed(gp);
        gp.enemy[mapNum][i].worldX = 31 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 23 * gp.tileSize;
        i++;

        // 5
        gp.enemy[mapNum][i] = new EnemyYellow(gp);
        gp.enemy[mapNum][i].worldX = 23 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 18 * gp.tileSize;
        i++;

        mapNum = 1;

        // 6
        gp.enemy[mapNum][i] = new EnemyWhite(gp);
        gp.enemy[mapNum][i].worldX = 33 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 39 * gp.tileSize;
        i++;

        // 7
        gp.enemy[mapNum][i] = new EnemyYellow(gp);
        gp.enemy[mapNum][i].worldX = 27 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 40 * gp.tileSize;
        i++;

        // 8
        gp.enemy[mapNum][i] = new EnemyRed(gp);
        gp.enemy[mapNum][i].worldX = 31 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 31 * gp.tileSize;
        i++;

        // 9
        gp.enemy[mapNum][i] = new EnemyRed(gp);
        gp.enemy[mapNum][i].worldX = 34 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 31 * gp.tileSize;
        i++;

        // 10
        gp.enemy[mapNum][i] = new EnemyYellow(gp);
        gp.enemy[mapNum][i].worldX = 23 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 23 * gp.tileSize;
        i++;

        // 11
        gp.enemy[mapNum][i] = new EnemyYellow(gp);
        gp.enemy[mapNum][i].worldX = 30 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 23 * gp.tileSize;
        i++;

        // 12
        gp.enemy[mapNum][i] = new EnemyYellow(gp);
        gp.enemy[mapNum][i].worldX = 20 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        // 13
        gp.enemy[mapNum][i] = new EnemyWhite(gp);
        gp.enemy[mapNum][i].worldX = 30 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        // 14
        gp.enemy[mapNum][i] = new EnemyRed(gp);
        gp.enemy[mapNum][i].worldX = 29 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 4 * gp.tileSize;
        i++;

        mapNum = 2;

        // 15
        gp.enemy[mapNum][i] = new EnemyYellow(gp);
        gp.enemy[mapNum][i].worldX = 21 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 34 * gp.tileSize;
        i++;

        // 16
        gp.enemy[mapNum][i] = new EnemyYellow(gp);
        gp.enemy[mapNum][i].worldX = 24 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 27 * gp.tileSize;
        i++;

        // 17
        gp.enemy[mapNum][i] = new EnemyRed(gp);
        gp.enemy[mapNum][i].worldX = 34 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 37 * gp.tileSize;
        i++;

        // 18
        gp.enemy[mapNum][i] = new EnemyWhite(gp);
        gp.enemy[mapNum][i].worldX = 11 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 37 * gp.tileSize;
        i++;

        // 19
        gp.enemy[mapNum][i] = new EnemyYellow(gp);
        gp.enemy[mapNum][i].worldX = 34 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 27 * gp.tileSize;
        i++;

        // 20
        gp.enemy[mapNum][i] = new EnemyYellow(gp);
        gp.enemy[mapNum][i].worldX = 32 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 22 * gp.tileSize;
        i++;

        // 21
        gp.enemy[mapNum][i] = new EnemyYellow(gp);
        gp.enemy[mapNum][i].worldX = 17 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 22 * gp.tileSize;
        i++;

        // 22
        gp.enemy[mapNum][i] = new EnemyWhite(gp);
        gp.enemy[mapNum][i].worldX = 30 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        // 23
        gp.enemy[mapNum][i] = new EnemyRed(gp);
        gp.enemy[mapNum][i].worldX = 30 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        // 24
        gp.enemy[mapNum][i] = new EnemyWhite(gp);
        gp.enemy[mapNum][i].worldX = 35 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        // 25
        gp.enemy[mapNum][i] = new EnemyRed(gp);
        gp.enemy[mapNum][i].worldX = 35 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 15 * gp.tileSize;
        i++;

        // 26
        gp.enemy[mapNum][i] = new EnemyWhite(gp);
        gp.enemy[mapNum][i].worldX = 29 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 9 * gp.tileSize;
        i++;

        // 27
        gp.enemy[mapNum][i] = new EnemyWhite(gp);
        gp.enemy[mapNum][i].worldX = 26 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        // 28
        gp.enemy[mapNum][i] = new EnemyRed(gp);
        gp.enemy[mapNum][i].worldX = 28 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        // 29
        gp.enemy[mapNum][i] = new EnemyWhite(gp);
        gp.enemy[mapNum][i].worldX = 14 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        // 30
        gp.enemy[mapNum][i] = new EnemyYellow(gp);
        gp.enemy[mapNum][i].worldX = 21 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        // 31
        gp.enemy[mapNum][i] = new EnemyWhite(gp);
        gp.enemy[mapNum][i].worldX = 23 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        // 32
        gp.enemy[mapNum][i] = new EnemyYellow(gp);
        gp.enemy[mapNum][i].worldX = 16 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 27 * gp.tileSize;
        i++;

        // 33
        gp.enemy[mapNum][i] = new EnemyRed(gp);
        gp.enemy[mapNum][i].worldX = 16 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 32 * gp.tileSize;
        i++;

        mapNum = 3;

        // 34
        gp.enemy[mapNum][i] = new Inmate(gp);
        gp.enemy[mapNum][i].worldX = 24 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 15 * gp.tileSize;
    }
}
