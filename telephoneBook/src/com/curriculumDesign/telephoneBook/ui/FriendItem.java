package com.curriculumDesign.telephoneBook.ui;

import com.curriculumDesign.telephoneBook.model.Friend;

import javax.swing.*;
import java.awt.*;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-13
 * @ Github: HappyOnion801
 */
public class FriendItem extends Item {
    private Friend friend;

    private String telephone;
    private String email;

    private Color backgroundColor = new Color(255, 255, 255);
    private Color contentColor = new Color(70, 70, 70);
    private Color nameColor = new Color(0, 0, 0);

    private Font nameFont = new Font("宋体", Font.PLAIN, 20);
    private Font contentFont = new Font("宋体", Font.PLAIN, 13);


    public FriendItem(Friend friend, int width, String[] text) {
        this.friend = friend;
        super.width = width;
        super.height = 50;
        this.telephone = text[0];
        this.email = text[1];
        super.setBackground(this.backgroundColor);
        super.setPreferredSize(new Dimension(super.width, super.height));
        super.setLayout(null);

        this.init();
    }

    private void init() {

        JLabel name = new JLabel(this.friend.getName());
        name.setBounds(30, 0, 100, 50);
        name.setForeground(this.nameColor);
        name.setFont(this.nameFont);
        super.add(name);

        String contentNumber = this.telephone;
        String contentEmail = this.email;
        try {
            contentNumber += " : " + this.friend.getNumber().getFirst();
            contentEmail += " : " + this.friend.getEmail().getFirst();
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel number = new JLabel(contentNumber);
        number.setBounds(130, 5, this.width - 130, 25);
        number.setForeground(this.contentColor);
        number.setFont(this.contentFont);
        super.add(number);

        JLabel email = new JLabel(contentEmail);
        email.setBounds(130, 23, this.width - 130, 25);
        email.setForeground(this.contentColor);
        email.setFont(this.contentFont);
        super.add(email);
    }

    @Override
    public int getHeight() {
        return super.height;
    }

    public Friend getFriend() {
        return this.friend;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o instanceof FriendItem) {
            FriendItem fi = (FriendItem) o;
            return this.friend.equals(fi.getFriend());
        }
        if (o instanceof Friend) {
            return this.friend.equals(o);
        }
        return false;
    }
}
