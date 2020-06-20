package com.curriculumDesign.telephoneBook.ui.panel;

import com.curriculumDesign.telephoneBook.model.Inputable;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-20
 * @ Github: HappyOnion801
 */
public class PasswordItem extends Item implements Inputable {
    private String title;
    private String id;

    private Font titleFont;
    private Color backgroundColor;

    private JLabel titleLabel;
    private JPasswordField password;


    public PasswordItem(int width, String title, String id) {
        super.width = width;
        super.height = 65;
        this.title = title;
        this.id = id;

        this.init();

        super.add(this.titleLabel);
        super.add(this.password);
        super.setPreferredSize(new Dimension(this.width, this.height));
        super.setBackground(this.backgroundColor);
    }

    public void init() {
        this.backgroundColor = new Color(255, 255, 255);
        this.titleFont = new Font("宋体", Font.PLAIN, 15);

        this.titleLabel = new JLabel(this.title + " :");
        this.titleLabel.setFont(this.titleFont);
        this.titleLabel.setPreferredSize(new Dimension((int) (this.width * 0.9), 20));

        this.password = new JPasswordField();
        this.password.setPreferredSize(
                new Dimension((int) (this.width * 0.8), 25)
        );
    }

    @Override
    public int getHeight() {
        return super.height;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public LinkedList<String> getInputContent() {
        LinkedList<String> res = new LinkedList<>();
        res.add(new String(this.password.getPassword()));
        return res;
    }
}
