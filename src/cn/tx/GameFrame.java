package cn.tx;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Vector;

/**
 * ClassName: GrameFrame
 * Package: cn.tx
 * Description:
 *
 * @Author 夏叶城二
 * @Create 2024/11/4 17:26
 * @Version 1.0
 */
public class GameFrame extends JFrame {
    public static boolean isFlag = true;
    HeroPlane heroPlane;

    //定义子弹的集合，线程安全->vector
    Vector<Bullet> bullets = new Vector<>();

    //定义敌机集合
    Vector<EnemyPlane> enemys = new Vector<>();

    GameFrame frame;
    public GameFrame() {

        frame = this;
        // 创建英雄机
        heroPlane = new HeroPlane(enemys);

        heroPlane.start();


        // 设置窗口的大小
        this.setSize(380, 580);
        // 设置窗口的名称
        this.setTitle("雷霆战机");
        // 将窗口设置为不可改变大小
        this.setResizable(false);
        // 将窗口设置为点击关闭按钮退出程序
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置窗口可见
        this.setVisible(true);
        // 设置窗口显示在屏幕中心
        this.setLocationRelativeTo(null);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            Random r = new Random();
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                while (isFlag) {
                    EnemyPlane ep = new EnemyPlane(r.nextInt(380), 0, frame);
                    ep.start();
                    enemys.add(ep);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
//E:\java_test_code\IDEA javaSECode\javaSECode\java_basic_projects\Thunder fighter
    /**
     * 在窗口上画内容，paint这个方法在窗口初始化时会默认执行
     * @param g the specified Graphics window
     */
    @Override
    public void paint(Graphics g) {
        //System.out.println("绘制画板");
        BufferedImage image = (BufferedImage) this.createImage(this.getSize().width, this.getSize().height);
        Graphics bi =  image.getGraphics();

        bi.drawImage(new ImageIcon("E:\\java_test_code\\IDEA javaSECode\\javaSECode" +
                "\\java_basic_projects\\Thunder fighter\\img\\img.png").getImage(), 0, 0, null);
        bi.drawImage(heroPlane.img, heroPlane.x, heroPlane.y, heroPlane.width, heroPlane.heigth, null);
        for (int i = 0; i < bullets.size(); i++) {
            System.out.println(bullets);
            Bullet bullet = bullets.get(i);
            if (bullet.y > 0) {
                bi.drawImage(bullet.image, bullet.x, bullet.y -= bullet.speed, bullet.width, bullet.heigth, null);
            }
            else {
                bullets.remove(bullet);
            }
        }

        for (int i = 0; i < enemys.size(); i++) {
            System.out.println(enemys);
            EnemyPlane ep = enemys.get(i);
            if (ep.y < 580) {
                bi.drawImage(ep.image, ep.x, ep.y += ep.speed, ep.width, ep.heigth, null);
            }
            else {
                enemys.remove(ep);
            }
        }
        if (!isFlag) {
            bi.drawImage(new ImageIcon("E:\\java_test_code\\IDEA javaSECode" +
                    "\\javaSECode\\java_basic_projects\\Thunder fighter\\img\\img_1.png").getImage(), 0, 0, 400, 600,  null);

        }
        g.drawImage(image, 0, 0, null);
    }

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        Player player = new Player(frame);
        frame.addKeyListener(player);
    }
}
