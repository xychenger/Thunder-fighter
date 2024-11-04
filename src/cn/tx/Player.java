package cn.tx;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * ClassName: Player
 * Package: cn.tx
 * Description:
 *
 * @Author 夏叶城二
 * @Create 2024/11/4 18:50
 * @Version 1.0
 */
public class Player extends KeyAdapter {

    //直接传入frame，就可以通过frame拿到heroplane，和bullets数组
    GameFrame frame;

    public Player(GameFrame frame) {
        this.frame = frame;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int KeyCode = e.getKeyCode();
        switch (KeyCode) {
            case 38:
                this.frame.heroPlane.up = true;
                break;
            case 40:
                this.frame.heroPlane.down = true;
                break;
            case 37:
                this.frame.heroPlane.left = true;
                break;
            case 39:
                this.frame.heroPlane.right = true;
                break;
            case 66:
                addBullet();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int KeyCode = e.getKeyCode();
        switch (KeyCode) {
            case 38:
                this.frame.heroPlane.up = false;
                break;
            case 40:
                this.frame.heroPlane.down = false;
                break;
            case 37:
                this.frame.heroPlane.left = false;
                break;
            case 39:
                this.frame.heroPlane.right = false;
                break;

        }
    }

    public void addBullet() {
        frame.bullets.add(new Bullet(frame.heroPlane.x + 10, frame.heroPlane.y - 20));
    }
}
