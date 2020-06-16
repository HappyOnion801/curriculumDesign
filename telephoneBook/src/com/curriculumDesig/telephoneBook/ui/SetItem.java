package com.curriculumDesig.telephoneBook.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-16
 * @ Github: HappyOnion801
 */
public class SetItem extends Item implements Inputable {
    private String title;
    private String id;
    private LinkedList<String> content;

    private JLabel titleLabel;
    private JComboBox<String> chooseBox;

    private Font font;
    private Color BackgroundColor;

    public SetItem(int width, String title, String id, LinkedList<String> content) {
        super.width = width;
        super.height = 30;

        this.title = title;
        this.id = id;
        this.content = content;

        this.init();
        super.setBackground(this.BackgroundColor);
        super.setPreferredSize(
                new Dimension(
                        this.width,
                        this.height
                )
        );
        super.setLayout(null);

        super.add(this.titleLabel);
        this.titleLabel.setBounds(
                (int) (this.width * 0.05),
                (int) (this.height * 0.2),
                (int) (this.width * 0.2),
                (int) (this.height * 0.7)
        );
        super.add(this.chooseBox);
        this.chooseBox.setBounds(
                (int) (this.width * 0.3),
                (int) (this.height * 0.18),
                (int) (this.width * 0.5),
                (int) (this.height * 0.7)
        );
    }

    public void init() {
        this.font = new Font("宋体", Font.PLAIN, 15);
        this.BackgroundColor = new Color(255, 255, 255);

        this.titleLabel = new JLabel(this.title);
        this.titleLabel.setFont(this.font);

        this.chooseBox = new JComboBox<>();
        for (String str : this.content) {
            this.chooseBox.addItem(str);
        }
        this.chooseBox.setFont(this.font);
        this.chooseBox.setBackground(this.BackgroundColor);
    }

    @Override
    public LinkedList<String> getInputContent() {
        LinkedList<String> res = new LinkedList<>();
        int selectItem = this.chooseBox.getSelectedIndex();
        res.add(this.content.get(selectItem));
        return res;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getHeight() {
        return super.height;
    }
}
