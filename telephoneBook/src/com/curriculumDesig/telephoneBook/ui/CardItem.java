package com.curriculumDesig.telephoneBook.ui;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-15
 * @ Github: HappyOnion801
 */
public class CardItem extends Item {
    private String title;
    private LinkedList<String> content;

    private JLabel titleLabel;

    private Font titleFont;
    private Font contentFont;
    private Color backgroundColor;


    public CardItem(int width, String title, LinkedList<String> content) {
        super.width = width;
        super.height = 0;

        this.title = title;
        this.content = content;

        init();

        super.add(this.titleLabel);
        addContent();

        super.setBackground(this.backgroundColor);

        super.setPreferredSize(
                new Dimension(this.width, this.height)
        );
    }

    public CardItem(int width,String title,String content){
        this(width,title,new LinkedList<>());
        this.content.add(content);
        this.addContent();
        super.setPreferredSize(
                new Dimension(this.width, this.height)
        );
    }

    private  void init() {
        this.backgroundColor = new Color(255, 255, 255);

        this.titleLabel = new JLabel(this.title);
        this.titleFont = new Font("宋体", Font.PLAIN, 15);
        this.titleLabel.setFont(this.titleFont);
        this.titleLabel.setPreferredSize(
                new Dimension((int) (this.width * 0.9), 25)
        );

        this.contentFont = new Font("宋体",Font.PLAIN,13);

        super.height = 33;
    }

    private void addContent(){
        for(String str : this.content){
            JLabel jLabel = new JLabel(str);
            jLabel.setPreferredSize(
                    new Dimension((int)(this.width*0.8),13)
            );
            jLabel.setFont(this.contentFont);
            super.add(jLabel);
            super.height += 19;
        }
    }

    @Override
    public int getHeight() {
        return super.height;
    }
}
