package com.curriculumDesign.telephoneBook.ui;

import com.curriculumDesign.telephoneBook.listener.ShowFriendListener;
import com.curriculumDesign.telephoneBook.model.Friend;

import javax.swing.*;
import java.awt.*;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-14
 * @ Github: HappyOnion801
 */
public class ShowFriendFrame extends JFrame {
    private Friend friend;
    private JTextArea content;

    private Font contentFont;

    public ShowFriendFrame(Friend friend) {
        this.friend = friend;

        this.content = new JTextArea();
        content.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(
                this.content,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        this.init();

        super.setSize(new Dimension(250,400));
        super.setResizable(false);
        super.setContentPane(jScrollPane);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.addWindowListener(new ShowFriendListener(this));
        super.setVisible(true);
    }

    private void init(){
        this.contentFont = new Font("宋体",Font.PLAIN,15);

        this.content.setFont(this.contentFont);
    }

    public void setContent(String content){
        this.content.setText(content);
    }

    public Friend getFriend(){
        return this.friend;
    }
}
