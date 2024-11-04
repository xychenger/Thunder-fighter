package cn.tx;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * ClassName: HeroPlane
 * Package: cn.tx
 * Description:
 *
 * @Author 夏叶城二
 * @Create 2024/11/4 18:36
 * @Version 1.0
 */
public class HeroPlane extends Thread{
    int x = 160, y = 450;
    int width = 50, heigth = 50;

    int speed = 8;

    Vector<EnemyPlane> enemys;

    Image img = new ImageIcon("E:\\java_test_code\\IDEA javaSECode\\javaSECode\\" +
            "java_basic_projects\\Thunder fighter\\img\\10012.png").getImage();

    //定义方向键的标志
    boolean up, right, left, down;

    public HeroPlane(Vector<EnemyPlane> enemys) {
        this.enemys = enemys;
    }

    public HeroPlane(int x, int y, int width, int heigth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
    }

    public boolean hit() {
        Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.heigth);

        Rectangle rect = null;

        for (int i = 0; i < enemys.size(); i++) {
            EnemyPlane ep = enemys.get(i);
            System.out.println("teat hit");
            rect = new Rectangle(ep.x, ep.y - 1, ep.width, ep.heigth);

            if (myrect.intersects(rect)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        while (GameFrame.isFlag) {
            if (up) {
                y -= speed;
            }
            if (down) {
                y += speed;
            }
            if (right) {
                x += speed;
            }
            if (left){
                x -= speed;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (hit()) {
                System.out.println("hit .......................");
                this.speed = 0;
                this.img = new ImageIcon("E:\\java_test_code\\IDEA javaSECode" +
                        "\\javaSECode\\java_basic_projects\\Thunder fighter\\img\\300370.png").getImage();
                try {
                    this.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        GameFrame.isFlag = false;
    }
}
