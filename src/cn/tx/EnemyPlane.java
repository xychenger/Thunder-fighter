package cn.tx;

import javax.swing.*;
import java.awt.*;

/**
 * ClassName: EnemyPlane
 * Package: cn.tx
 * Description:
 *
 * @Author 夏叶城二
 * @Create 2024/11/4 19:37
 * @Version 1.0
 */
public class EnemyPlane extends Thread{
    public GameFrame gf;

    public int x, y;
    public int width = 50;
    public int heigth = 50;
    public int speed = 2;

    public Image image = new ImageIcon("E:\\java_test_code\\IDEA javaSECode" +
            "\\javaSECode\\java_basic_projects\\Thunder fighter\\img\\10032.png").getImage();

    public EnemyPlane(int x, int y, GameFrame gf) {
        this.x = x;
        this.y = y;
        this.gf = gf;
    }

    public EnemyPlane(int x, int y, int width, int heigth, GameFrame gf) {
        this.x = x;
        this.y = y;
        this.gf = gf;
        this.heigth = heigth;
        this.width = width;
    }

    @Override
    public void run() {
        while (GameFrame.isFlag) {

            if (hit()) {
                System.out.println("hit .....................");
                this.speed = 0;
                this.image = new ImageIcon("E:\\java_test_code\\IDEA javaSECode" +
                        "\\javaSECode\\java_basic_projects\\Thunder fighter\\img\\300370.png").getImage();
                try {
                    this.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gf.enemys.remove(this);
                break;
            }

            if (this.y >= 760) {
                break;
            }
            try {
                this.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean hit() {
        Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.heigth);

        Rectangle rect = null;

        for (int i = 0; i < gf.bullets.size(); i++) {
            Bullet bullet = gf.bullets.get(i);
            System.out.println("test hit");
            rect = new Rectangle(bullet.x, bullet.y - 1, bullet.width, bullet.heigth);

            if (myrect.intersects(rect)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "EnemyPlane{" +
                "gf=" + gf +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", heigth=" + heigth +
                ", speed=" + speed +
                ", image=" + image +
                '}';
    }
}
