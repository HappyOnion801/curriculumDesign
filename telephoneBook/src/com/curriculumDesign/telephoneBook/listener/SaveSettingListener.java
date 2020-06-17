package com.curriculumDesign.telephoneBook.listener;

import com.curriculumDesign.telephoneBook.service.Service;
import com.curriculumDesign.telephoneBook.ui.MainFrame;

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
public class SaveSettingListener implements ActionListener {
    private Service service = Service.getInstance();
    private MainFrame mainFrame;

    public SaveSettingListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        HashMap<String, LinkedList<String>> input = this.service.inputsToMap(this.mainFrame.getShowPanel().getAllInputItem());
        boolean res = this.service.setLanguage(input.get("language").getFirst());
        String message = this.service.getText("saveError");
        if(res) message = this.service.getText("saveSuccessful");
        JOptionPane.showMessageDialog(this.mainFrame,message);
    }
}
