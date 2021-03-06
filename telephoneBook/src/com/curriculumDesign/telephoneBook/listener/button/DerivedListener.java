package com.curriculumDesign.telephoneBook.listener.button;

import com.curriculumDesign.telephoneBook.service.Service;
import com.curriculumDesign.telephoneBook.ui.frame.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-16
 * @ Github: HappyOnion801
 */
public class DerivedListener implements ActionListener {
    private Service service = Service.getInstance();
    private MainFrame mainFrame;

    public DerivedListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        boolean res = this.service.derived();
        String message = this.service.getText("derivedError");
        if (res) message = this.service.getText("derivesSuccessful");
        JOptionPane.showMessageDialog(this.mainFrame, message);
    }
}
