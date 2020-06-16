package com.curriculumDesig.telephoneBook.listener;

import com.curriculumDesig.telephoneBook.model.Friend;
import com.curriculumDesig.telephoneBook.service.Service;
import com.curriculumDesig.telephoneBook.ui.Inputable;
import com.curriculumDesig.telephoneBook.ui.MainFrame;
import com.sun.tools.javac.Main;
import jdk.dynalink.linker.LinkerServices;

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
    private MainFrame mainFrame;
    private Service service = Service.getInstance();

    public CreateFriendListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public boolean createFriend(){
        LinkedList<Inputable> inputs = this.mainFrame.getShowPanel().getAllInputItem();

        HashMap<String,LinkedList<String>> attribute = this.service.inputsToMap(inputs);

        if (attribute.get("name").size() == 0) {
            JOptionPane.showMessageDialog(
                    this.mainFrame, this.service.getText("pleaseInputName"),
                    this.service.getText("error"),
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        if (attribute.get("telephone").size() == 0) {
            attribute.get("telephone").add("");
        }
        if (attribute.get("email").size() == 0) {
            attribute.get("email").add("");
        }
        if (attribute.get("other").size() == 0) {
            attribute.get("other").add("");
        }
        if (attribute.get("group").size() == 0) {
            attribute.get("group").add(this.service.getText("noGroup"));
        }

        Friend friend = new Friend(
                attribute.get("name"),
                attribute.get("telephone"),
                attribute.get("email"),
                attribute.get("other"),
                attribute.get("group")
        );
        service.addFriend(friend);
        return true;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        this.createFriend();
        new MainFrameListener(this.mainFrame).addFriendItem();
    }
}
