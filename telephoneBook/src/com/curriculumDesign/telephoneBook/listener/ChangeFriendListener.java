package com.curriculumDesign.telephoneBook.listener;

import com.curriculumDesign.telephoneBook.model.Friend;
import com.curriculumDesign.telephoneBook.service.Service;
import com.curriculumDesign.telephoneBook.ui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-16
 * @ Github: HappyOnion801
 */
public class ChangeFriendListener implements ActionListener {
    private Service service = Service.getInstance();
    private MainFrame mainFrame;
    private Friend friend;

    public ChangeFriendListener(MainFrame mainFrame, Friend friend) {
        this.mainFrame = mainFrame;
        this.friend = friend;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        CreateFriendListener createFriendListener = new CreateFriendListener(this.mainFrame);
        boolean succ = createFriendListener.createFriend();
        if(succ){
            service.delete(this.friend);
            new MainFrameListener(this.mainFrame).addFriendItem();

            this.mainFrame.repaint();
            this.mainFrame.setVisible(true);
        }
    }
}
