package com.curriculumDesig.telephoneBook.listener;

import com.curriculumDesig.telephoneBook.model.Friend;
import com.curriculumDesig.telephoneBook.service.Service;
import com.curriculumDesig.telephoneBook.ui.ShowFriendFrame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-14
 * @ Github: HappyOnion801
 */
public class ShowFriendListener implements WindowListener {
    private ShowFriendFrame showFriendFrame;
    private Service service = Service.getInstance();

    public ShowFriendListener(ShowFriendFrame showFriendFrame) {
        this.showFriendFrame = showFriendFrame;
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {
        StringBuilder sb = new StringBuilder();
        Friend friend = this.showFriendFrame.getFriend();

        sb.append("\n    " + service.getText("name") + " : \n");
        sb.append("        " + friend.getName() + "\n\n");


        sb.append("    " + this.service.getText("telephone") + " : \n");
        LinkedList<String> number = this.showFriendFrame.getFriend().getNumber();
        Iterator<String> it = number.iterator();
        while (it.hasNext()) sb.append("        " + it.next() + "\n");
        sb.append("\n");

        LinkedList<String> email = this.showFriendFrame.getFriend().getEmail();
        if (email.size() > 0) {
            sb.append("    " + this.service.getText("email") + " : \n");
            it = email.iterator();
            while (it.hasNext()) sb.append("        " + it.next() + "\n");
            sb.append("\n");
        }

        if (this.showFriendFrame.getFriend().getGroup() != "") {
            sb.append("    " + this.service.getText("group") + " : \n");
            sb.append("        " + this.showFriendFrame.getFriend().getGroup() + "\n\n");
        }

        if (this.showFriendFrame.getFriend().getOther() != "") {
            sb.append("    " + this.service.getText("other") + " : \n");
            sb.append("        " + this.showFriendFrame.getFriend().getOther());
        }

        this.showFriendFrame.setContent(sb.toString());
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
