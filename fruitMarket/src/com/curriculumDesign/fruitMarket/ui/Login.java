package com.curriculumDesign.fruitMarket.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-17
 * @ Github: HappyOnion801
 */
public abstract class Login extends AbstractMainFrame {
    protected void Login() {
        super.setLayout(null);
        JPanel input = new JPanel();
        String str[] = {"977046910", "126376237", "378712838"};
        JComboBox userName = new JComboBox(str);
        userName.setBounds(135, 20, 135, 25);
        JPasswordField password = new JPasswordField();
        password.setBounds(135, 65, 130, 25);
        JButton log = new JButton("登录");
        log.setBounds(170, 120, 60, 25);
        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = (String) userName.getSelectedItem();
                String pass = new String(password.getPassword());
                if ("123456".equals(pass)) {
                    showAdminDialog();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "您输入的账户或密码不正确，请重新输入");
                }
            }
        });
        super.add(userName);
        super.add(password);
        super.add(log);
        super.setSize(400, 200);
        setVisible(false);
        setVisible(true);
    }

    protected abstract void showAdminDialog();// 抽象方法在controller中实现
}

