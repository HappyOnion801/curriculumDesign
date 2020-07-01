package JMouse;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-22
 * @ Github: HappyOnion801
 */
public class Mouse {
    private MouseClient mc;
    private int x, y;
    private int tab1;
    Random rd = new Random();
    private int data;
    private boolean live = true;
    private int mouseTime = 10;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] images = null;
    private static Map<Image, String> imgKV = new HashMap<Image, String>();// 存放图片和对应的号码
    // list.get(i)得到的是对应位置的元素,map.get(key)得到的是value
    static {
        images = new Image[] { tk.getImage(Mouse.class.getClassLoader().getResource("imags/A.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/B.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/C.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/D.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/E.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/F.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/G.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/H.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/I.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/J.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/K.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/L.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/M.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/N.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/O.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/P.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/Q.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/R.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/S.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/T.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/U.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/V.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/W.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/X.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/Y.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/Z.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/0.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/1.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/2.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/3.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/4.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/5.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/6.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/7.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/8.png")),
                tk.getImage(Mouse.class.getClassLoader().getResource("imags/9.png"))
        };// 反射机制A.class得到A的一个class对象,属性名or方法名
        imgKV.put(images[0], "A");
        imgKV.put(images[1], "B");
        imgKV.put(images[2], "C");
        imgKV.put(images[3], "D");
        imgKV.put(images[4], "E");
        imgKV.put(images[5], "F");
        imgKV.put(images[6], "G");
        imgKV.put(images[7], "H");
        imgKV.put(images[8], "I");
        imgKV.put(images[9], "J");
        imgKV.put(images[10], "K");
        imgKV.put(images[11], "L");
        imgKV.put(images[12], "M");
        imgKV.put(images[13], "N");
        imgKV.put(images[14], "O");
        imgKV.put(images[15], "P");
        imgKV.put(images[16], "Q");
        imgKV.put(images[17], "R");
        imgKV.put(images[18], "S");
        imgKV.put(images[19], "T");
        imgKV.put(images[20], "U");
        imgKV.put(images[21], "V");
        imgKV.put(images[22], "W");
        imgKV.put(images[23], "X");
        imgKV.put(images[24], "Y");
        imgKV.put(images[25], "Z");
        imgKV.put(images[26], "0");
        imgKV.put(images[27], "1");
        imgKV.put(images[28], "2");
        imgKV.put(images[29], "3");
        imgKV.put(images[30], "4");
        imgKV.put(images[31], "5");
        imgKV.put(images[32], "6");
        imgKV.put(images[33], "7");
        imgKV.put(images[34], "8");
        imgKV.put(images[35], "9");
    }

    public Mouse(int x, int y, int tab1, MouseClient mc) {
        super();
        this.mc = mc;
        this.x = x;
        this.y = y;
        this.tab1 = tab1;
        data = rd.nextInt(36);
        new Thread(new MouseTime()).start();
    }

    private class MouseTime implements Runnable {

        @Override
        public void run() {
            while (true) {
                mouseTime--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void draw(Graphics g) {
        if (!live) {
            mc.mouses.remove(this);
            mc.tab[tab1] = 0;
            return;
        }
        g.drawImage(images[data], x, y, null);
        Color c = g.getColor();
        g.setColor(Color.PINK);
        g.setFont(new Font(null, 1, 40));
        g.drawString("" + mouseTime, x + 50, y + 10);
        if (mouseTime <= 0) {
            this.live = false;
            mc.escape++;
        }
    }

    public boolean KeyRel(KeyEvent e) {
        int key = imgKV.get(images[data]).charAt(0);// String.charAt(0)拿到字符串第一个字母的char值
        if (e.getKeyCode() == key) {
            System.out.println(key);
            mc.mouseCount++;
            this.live = false;
            return true;
        }
        return false;
    }
}
