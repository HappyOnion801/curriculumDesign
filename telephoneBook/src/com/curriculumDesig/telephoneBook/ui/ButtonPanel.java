package com.curriculumDesig.telephoneBook.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-14
 * @ Github: HappyOnion801
 */
public class ButtonPanel extends JPanel {
    private int width;
    private int height;

    private String settingText = "";
    private String allText = "";
    private String groupText = "";
    private String addText = "";

    private JButton setting;
    private JButton all;
    private JButton group;
    private JButton add;

    private Font font;

    public ButtonPanel(int width, int height) {
        this.width = width;
        this.height = height;

        super.setPreferredSize(new Dimension(this.width, this.height));
        super.setLayout(null);

        this.init();

        super.add(this.setting);
        super.add(this.all);
        super.add(this.group);
        super.add(this.add);

    }

    private void init() {

        this.font = new Font(
                "宋体",
                Font.PLAIN,
                10
        );

        this.setting = new JButton(settingText);
        this.all = new JButton(allText);
        this.group = new JButton(groupText);
        this.add = new JButton(allText);

        this.setting.setBounds(
                (int) (this.width * 0.07),
                (int) (this.height * 0.15),
                (int) (this.width * 0.2),
                (int) (this.height * 0.7)
        );
        this.all.setBounds(
                (int) (this.width * 0.34),
                (int) (this.height * 0.15),
                (int) (this.width * 0.16),
                (int) (this.height * 0.7)
        );
        this.group.setBounds(
                (int) (this.width * 0.48),
                (int) (this.height * 0.15),
                (int) (this.width * 0.16),
                (int) (this.height * 0.7)
        );
        this.add.setBounds(
                (int) (this.width * 0.70),
                (int) (this.height * 0.15),
                (int) (this.width * 0.2),
                (int) (this.height * 0.7)
        );

        this.setting.setFont(this.font);
        this.setting.setFocusPainted(false);
        this.all.setFont(this.font);
        this.all.setFocusPainted(false);
        this.group.setFont(this.font);
        this.group.setFocusPainted(false);
        this.add.setFont(this.font);
        this.add.setFocusPainted(false);

    }


    public void setFirstListener(ActionListener actionListener) {
        this.setting.addActionListener(actionListener);
    }

    public void setSecondListener(ActionListener actionListener) {
        this.all.addActionListener(actionListener);
    }

    public void setThirdListener(ActionListener actionListener) {
        this.group.addActionListener(actionListener);
    }

    public void setLastListener(ActionListener actionListener) {
        this.add.addActionListener(actionListener);
    }

    public void setText(String set, String all, String group, String add) {
        this.settingText = set;
        this.allText = all;
        this.groupText = group;
        this.addText = add;

        this.setting.setText(this.settingText);
        this.all.setText(this.allText);
        this.group.setText(this.groupText);
        this.add.setText(this.addText);
    }
}
