package com.curriculumDesign.telephoneBook.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-14
 * @ Github: HappyOnion801
 */
public class LabelItem extends Item {

    private String text;
    private Font textFont;
    private JLabel content;

    public LabelItem(int width, int height, String text) {
        this.text = text;
        super.height = height;
        super.width = width;

        super.setPreferredSize(new Dimension(width, height));

        init();

        super.add(content);
    }

    private void init() {
        this.textFont = new Font("宋体", Font.PLAIN, 17);
        this.content = new JLabel(this.text);
        this.content.setFont(this.textFont);
    }

    @Override
    public int getHeight() {
        return super.height;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o instanceof LabelItem) {
            LabelItem labelItem = (LabelItem) o;
            return this.text.equals(labelItem.text);
        }
        return false;
    }
}
