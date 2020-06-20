package com.curriculumDesign.telephoneBook.listener.button;

import com.curriculumDesign.telephoneBook.Main;
import com.curriculumDesign.telephoneBook.listener.frame.MainFrameListener;
import com.curriculumDesign.telephoneBook.model.Friend;
import com.curriculumDesign.telephoneBook.service.Service;
import com.curriculumDesign.telephoneBook.ui.frame.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-16
 * @ Github: HappyOnion801
 */
public class SaveChangeListener implements ActionListener {
    private Service service = Service.getInstance();
    private MainFrame mainFrame;
    private Friend friend;

    public SaveChangeListener(MainFrame mainFrame, Friend friend) {
        this.mainFrame = mainFrame;
        this.friend = friend;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        HashMap<String, LinkedList<String>> attribute = this.service.inputsToMap(this.mainFrame.getShowPanel().getAllInputItem());
        Friend friend = this.service.newFriend(attribute);
        friend.setId(this.service.getNowFriend().getId());
        if (this.service.update(friend)) {
            JOptionPane.showMessageDialog(this.mainFrame, this.service.getText("changeInformationSuccess"));
        } else {
            JOptionPane.showMessageDialog(this.mainFrame, this.service.getText("changeInformationFail"));
        }
        new MainFrameListener(this.mainFrame).addFriendItem();
    }
}
