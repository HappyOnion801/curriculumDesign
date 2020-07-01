package JMouse;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-22
 * @ Github: HappyOnion801
 */
public class MouseClient extends Frame {
    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 700;
    public static int mouseCount;// 击中了几只老鼠
    public static int count;// 击打次数
    public static int escape;// 逃跑数
    public static int gameTime;// 游戏持续时间
    List<Mouse> mouses = new ArrayList<Mouse>();// 所有老鼠的集合
    public int[] tab = new int[10];// 标记坑里是不是有老鼠
    Random rd = new Random();// 随机变量
    public int initMouseCount = 2;// 初始游戏屏幕显示两只老鼠
    Image offScreenImage = null;// 初始画布

    public void launchFrame() {
        this.setLocation(200, 10);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setVisible(true);
        this.setTitle("打地鼠");
        this.setBackground(Color.ORANGE);
        this.setResizable(false);// 窗口大小不可改变
        this.addKeyListener(new KeyMonitor());// 键盘监听器
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);// closed
            }
        });
        new Thread(new GameTime()).start();// 启动线程
        new Thread(new PaintThread()).start();// 启动线程 绘图
    }

    public class GameTime implements Runnable {

        @Override
        public void run() {
            while (true) {
                gameTime++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class PaintThread implements Runnable {

        @Override
        public void run() {
            // TODO 自动生成的方法存根
            while (true) {
                repaint();// 重绘组件、awt、update、paint
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
            }
        }
    }

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOff = offScreenImage.getGraphics();
        Color c = gOff.getColor();
        gOff.setColor(Color.ORANGE);
        gOff.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOff.setColor(c);
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.gray);
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                g.fillOval(GAME_WIDTH / 4 * i - 60, GAME_HEIGHT / 4 * j - 25, 120, 50);
            }
        }
        g.setColor(c);
        g.drawString("击打次数：" + count, 50, 80);
        g.drawString("击中次数：" + mouseCount, 50, 100);
        g.drawString("逃跑次数：" + escape, 50, 120);
        g.drawString("游戏时长：" + gameTime, 50, 140);
        if (mouseCount >= 80) {
            initMouseCount = 8;
        } else if (mouseCount >= 50) {
            initMouseCount = 6;
        } else if (mouseCount >= 20) {
            initMouseCount = 4;
        }
        while (mouses.size() < initMouseCount) {// 当前有的老鼠数量，小于应该出现的数量时，随机选坑
            int temp = rd.nextInt(9);// [0,9)
            while (tab[temp] == 1) {
                temp++;
                if (temp == 9) {
                    temp = 0;
                }
            }
            tab[temp] = 1;// 等于则表示坑里有老鼠
            mouses.add(new Mouse(GAME_WIDTH / 4 * (temp % 3 + 1) - 70, GAME_HEIGHT / 4 * (temp / 3 + 1) - 100, temp,
                    this));// 老鼠的定义不对，不完整
        }
        for (int i = 0; i < mouses.size(); i++) {
            Mouse m = mouses.get(i);
            m.draw(g);
        }
    }

    public static void main(String[] args) {
        MouseClient km = new MouseClient();
        km.launchFrame();
    }

    private class KeyMonitor extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            // TODO 自动生成的方法存根
            System.out.println(e.getKeyCode());
            count++;
            for (int i = 0; i < mouses.size(); i++) {
                if (mouses.get(i).KeyRel(e))
                    break;
            }
        }
    }
}