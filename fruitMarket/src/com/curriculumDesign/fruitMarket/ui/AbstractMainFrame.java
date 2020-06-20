package com.curriculumDesign.fruitMarket.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-17
 * @ Github: HappyOnion801
 */
public abstract class AbstractMainFrame extends JFrame {//只管长什么样，具体业务是继承自类实现

    public AbstractMainFrame() {
        init();//初始化窗体
        addComponents();//添加组件
    }

    //窗体初始化
    private void init() {
        setTitle("水果超市欢迎您");
        setSize(600, 350);
        setLocation(600, 100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected void addComponents() {
        JLabel titleLabel = new JLabel(
                new ImageIcon("D:\\curriculumDesign\\FruitMarket\\FruitStore.jpg")
        );
        add(titleLabel, BorderLayout.NORTH);
        JPanel login = new JPanel();
        JButton btn = new JButton("进入管理系统");
        login.add(btn);
        add(login);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(titleLabel);
                remove(login);
                Login();
            }
        });
    }

    protected abstract void Login();
    //protected abstract void showAdminDialog() ;//抽象方法，子类controller中实现
}
