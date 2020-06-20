package com.curriculumDesign.telephoneBook.listener.button;

import com.curriculumDesign.telephoneBook.listener.frame.MainFrameListener;
import com.curriculumDesign.telephoneBook.model.Friend;
import com.curriculumDesign.telephoneBook.service.Service;
import com.curriculumDesign.telephoneBook.model.Inputable;
import com.curriculumDesign.telephoneBook.ui.frame.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-15
 * @ Github: HappyOnion801
 */
public class CreateFriendListener implements ActionListener {
    private Service service = Service.getInstance();
    private MainFrame mainFrame;

    public CreateFriendListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public boolean createFriend() {
        LinkedList<Inputable> inputs = this.mainFrame.getShowPanel().getAllInputItem();

        HashMap<String, LinkedList<String>> attribute = this.service.inputsToMap(inputs);

        if (attribute.get("name").size() == 0) {
            JOptionPane.showMessageDialog(
                    this.mainFrame, this.service.getText("pleaseInputName"),
                    this.service.getText("error"),
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        Friend friend = this.service.createFriend(attribute);
        if (service.addFriend(friend)) {
            JOptionPane.showMessageDialog(this.mainFrame, this.service.getText("createSuccess"));
            return true;
        } else {
            JOptionPane.showMessageDialog(this.mainFrame, this.service.getText("createFail"));
            return false;
        }
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (this.createFriend()) {
            new MainFrameListener(this.mainFrame).addFriendItem();
        }
    }
}
