package com.curriculumDesign.telephoneBook.listener.button;

import com.curriculumDesign.telephoneBook.listener.frame.MainFrameListener;
import com.curriculumDesign.telephoneBook.service.Service;
import com.curriculumDesign.telephoneBook.ui.frame.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-14
 * @ Github: HappyOnion801
 */
public class AllListener implements ActionListener {
    private Service service = Service.getInstance();
    private MainFrame mainFrame;
    public AllListener(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.service.setNowPage("all");
        this.mainFrame.getSearch().clear();
        new MainFrameListener(this.mainFrame).addFriendItem();
    }
}
