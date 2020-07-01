package com.curriculumDesign.calculator.main;
/**
 * @ Author: MaCode
 * @ Date: 2020-06-30
 * @ Github: HappyOnion801
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener {

    private static final long serialVersionUID = -169068472193786457L;//Java的序列化机制

    int i;
    //定义数字和运算符按钮数组
    private final String[] str = {"7", "8", "9", "÷", "4", "5", "6", "×", "1",
            "2", "3", "-", ".", "0", "=", "+"};
    boolean isFirstDigit = true;// 设置布尔型变量，用于标记是否是第一次按下键盘
    double number = 0.0;
    String operator = "=";

    JButton[] buttons = new JButton[str.length];//按钮数组
    JButton reset = new JButton("CE"); //清零按钮
    JTextField display = new JTextField("0"); //文本框，初始显示0

    //无参数构造函数
    public Main() {
        super("计算器");//调用父类的构造方法

        //添加面板1，网格布局，依次将按钮添加到面板1上
        JPanel panel1 = new JPanel(new GridLayout(4, 4));
        for (i = 0; i < str.length; i++) {
            buttons[i] = new JButton(str[i]);
            panel1.add(buttons[i]);
        }

        //添加面板2，边界布局，将文本框和清零按钮添加到面板2上
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add("Center", display);
        panel2.add("East", reset);

        //获取内容面板
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add("North", panel2);
        getContentPane().add("Center", panel1);

        //3.注册监听器以监听事件源产生的事件
        //为4*4面板的按钮添加事件监听
        for (i = 0; i < str.length; i++)
            buttons[i].addActionListener(this);
        reset.addActionListener(this);//为清零按钮添加事件监听
        display.addActionListener(this);//为文本框按钮添加事件监听
        addWindowListener(new WindowCloser());//关闭窗口
        setSize(400, 400);//设定窗体大小
        //setBounds (400 , 400, 400, 400);//绝对布局
        setVisible(true);//设置组件可见，显示窗体
        //pack();
    }

    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();//获得组件，按钮或文本框
        String label = e.getActionCommand();//返回与此动作相关的命令字符串
        if (target == reset)
            handleReset();
            //后面有handleaReset方法的定义
        else if ("0123456789.".indexOf(label) >= 0) {//当输入的是“”中的一个时，响应输入数字事件
            handleNumber(label); //后面有handleNumber方法的定义
        } else
            handleOperator(label); //后面有handleOperator方法的定义
    }


    // handleReset()方法:归零响应.
    public void handleReset() {
        display.setText("0");
        isFirstDigit = true;
        operator = "=";
    }

    // handleNumber()方法: 获取操作数
    public void handleNumber(String key) {
        if (isFirstDigit)
            display.setText(".".equals(key) ? "0." : key);
        else if ((key.equals(".")) && (display.getText().indexOf(".") < 0))
            display.setText(display.getText() + ".");
        else if (!key.equals("."))
            display.setText(display.getText() + key);
        isFirstDigit = false;
    }

    //运算符处理操作
    public void handleOperator(String key) {
        if (operator.equals("+")) {
            number += Double.valueOf(display.getText());//把获取到的字符串转换为double类型
            display.setText(String.valueOf(number));
        } else if (operator.equals("-")) {
            number -= Double.valueOf(display.getText());
            display.setText(String.valueOf(number));
        } else if (operator.equals("×")) {
            number *= Double.valueOf(display.getText());
            display.setText(String.valueOf(number));
        } else if (operator.equals("÷")) {
            if (Double.parseDouble(display.getText()) == 0) {
                display.setText("error除数不能为0");
            } else {
                number /= Double.valueOf(display.getText());
                display.setText(String.valueOf(number));
            }
        } else if (operator.equals("=")) {
            number = Double.valueOf(display.getText());
            display.setText(String.valueOf(number));
        }
        operator = key;
        isFirstDigit = true;
    }

    //关闭窗体
    private class WindowCloser extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Main();//匿名对象
    }
}
