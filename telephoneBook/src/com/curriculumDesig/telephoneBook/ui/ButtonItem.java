package com.curriculumDesig.telephoneBook.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-15
 * @ Github: HappyOnion801
 */
public class ButtonItem extends Item {

    private JButton button;

    private Font textFont;

    private String text;

    public ButtonItem(int width, String text) {
        super.width = width;
        super.height = 35;
        this.text = text;

        this.init();

        super.add(this.button);
        super.setPreferredSize(new Dimension(this.width,this.height));
    }

    public void init(){
        this.textFont = new Font("宋体", Font.PLAIN,13);
        this.button = new JButton(this.text);
        this.button.setFont(this.textFont);
        this.button.setPreferredSize(new Dimension(100,25));
        this.button.setFocusPainted(false);
    }

    public void setButtonListener(ActionListener actionListener){
        this.button.addActionListener(actionListener);
    }

    @Override
    public int getHeight() {
        return super.height;
    }
}
