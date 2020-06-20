package com.curriculumDesign.telephoneBook.listener.button;

import com.curriculumDesign.telephoneBook.model.User;
import com.curriculumDesign.telephoneBook.service.Service;
import com.curriculumDesign.telephoneBook.ui.frame.LoginFrame;
import com.curriculumDesign.telephoneBook.ui.frame.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-20
 * @ Github: HappyOnion801
 */
public class LoginButtonListener implements ActionListener {
    private Service service = Service.getInstance();
    private LoginFrame loginFrame;

    public LoginButtonListener(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    private String getAttribute(String id, HashMap<String, LinkedList<String>> map) {
        try {
            LinkedList<String> list = map.get(id);
            if (list.size() == 0) return null;
            return list.getFirst();
        } catch (Exception e) {
            return null;
        }
    }

    private void in() {
        this.service.NotNetwork();
        this.loginFrame.dispose();
        new MainFrame();
    }

    private void login(String id, String password) {
        User user = new User(id, password);
        int res = this.service.login(user);
        if (res == 1) {
            boolean download = this.service.downloadData();
            if (download) {
                this.loginFrame.dispose();
                new MainFrame();
            }else{
                JOptionPane.showMessageDialog(this.loginFrame, this.service.getText("downloadError"));
            }

        } else if (res == 0) {
            JOptionPane.showMessageDialog(this.loginFrame, this.service.getText("idOrPasswordError"));
        } else if (res == -1) {
            JOptionPane.showMessageDialog(this.loginFrame, this.service.getText("connectionException"));
        } else {
            JOptionPane.showMessageDialog(this.loginFrame, this.service.getText("Error"));
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        HashMap<String, LinkedList<String>> inputs = this.service.inputsToMap(this.loginFrame.getShowPanel().getAllInputItem());
        String id = this.getAttribute("id", inputs);
        String password = this.getAttribute("password", inputs);
        if (id == null && password == null) {
            this.in();
        } else if (id == null) {
            JOptionPane.showMessageDialog(this.loginFrame, this.service.getText("pleaseInputId"));
        } else if (password == null) {
            JOptionPane.showMessageDialog(this.loginFrame, this.service.getText("pleaseInputPassword"));
        } else {
            this.login(id, password);
        }
    }
}
