package cn.tx;

import javax.swing.*;
import java.awt.*;

/**
 * ClassName: Bullet
 * Package: cn.tx
 * Description:
 *
 * @Author 夏叶城二
 * @Create 2024/11/4 19:05
 * @Version 1.0
 */
public class Bullet {

    int x, y;
    int width = 25, heigth = 25;

    int speed = 10;

    Image image = new ImageIcon("E:\\java_test_code\\IDEA javaSECode\\javaSECode" +
            "\\java_basic_projects\\Thunder fighter\\img\\30021.png").getImage();

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Bullet(int x, int y, int width, int heigth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", heigth=" + heigth +
                ", speed=" + speed +
                ", image=" + image +
                '}';
    }
}
