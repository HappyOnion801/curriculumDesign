package com.curriculumDesig.telephoneBook.listener;

import com.curriculumDesig.telephoneBook.dataBaseService.DataBaseService;
import com.curriculumDesig.telephoneBook.service.Service;
import com.curriculumDesig.telephoneBook.ui.MainFrame;

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
