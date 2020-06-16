package com.curriculumDesig.telephoneBook.listener;

import com.curriculumDesig.telephoneBook.ui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-14
 * @ Github: HappyOnion801
 */
public class GroupListener implements ActionListener {
    private MainFrame mainFrame;
    public GroupListener(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.mainFrame.getSearch().clear();
        new MainFrameListener(this.mainFrame).addFriendGroup();
    }
}
