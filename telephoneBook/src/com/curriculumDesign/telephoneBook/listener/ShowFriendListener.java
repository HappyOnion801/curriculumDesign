package com.curriculumDesign.telephoneBook.listener;

import com.curriculumDesign.telephoneBook.model.Friend;
import com.curriculumDesign.telephoneBook.service.Service;
import com.curriculumDesign.telephoneBook.ui.ShowFriendFrame;

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

        sb.append("\n    ").append(service.getText("name")).append(" : \n");
        sb.append("        ").append(friend.getName()).append("\n\n");


        sb.append("    ").append(this.service.getText("telephone")).append(" : \n");
        LinkedList<String> number = this.showFriendFrame.getFriend().getNumber();
        for (String s : number) sb.append("        ").append(s).append("\n");
        sb.append("\n");

        LinkedList<String> email = this.showFriendFrame.getFriend().getEmail();
        if (email.size() > 0) {
            sb.append("    ").append(this.service.getText("email")).append(" : \n");
            for (String s : email) sb.append("        ").append(s).append("\n");
            sb.append("\n");
        }

        if ("".equals(this.showFriendFrame.getFriend().getGroup())) {
            sb.append("    ").append(this.service.getText("group")).append(" : \n");
            sb.append("        ").append(this.showFriendFrame.getFriend().getGroup()).append("\n\n");
        }

        if ("".equals(this.showFriendFrame.getFriend().getOther())) {
            sb.append("    ").append(this.service.getText("other")).append(" : \n");
            sb.append("        ").append(this.showFriendFrame.getFriend().getOther());
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
