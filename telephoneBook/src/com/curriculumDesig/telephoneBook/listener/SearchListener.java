package com.curriculumDesig.telephoneBook.listener;

import com.curriculumDesig.telephoneBook.model.Friend;
import com.curriculumDesig.telephoneBook.service.Service;
import com.curriculumDesig.telephoneBook.ui.MainFrame;
import com.curriculumDesig.telephoneBook.ui.SearchPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-13
 * @ Github: HappyOnion801
 */
public class SearchListener implements ActionListener {
    private Service service = Service.getInstance();
    private SearchPanel searchPanel;
    private MainFrame mainFrame;

    private void search(String str) {
        if ("".equals(str) || str == null)  return;

        if (str.matches("[0-9]+$")) {
            List<Friend> res = this.service.searchNumber(str);
            new MainFrameListener(this.mainFrame).addSearchResult(res);
        } else {
            List<Friend> res = this.service.searchName(str);
            new MainFrameListener(this.mainFrame).addSearchResult(res);
        }

    }

    public SearchListener(MainFrame mainFrame, SearchPanel searchPanel) {
        this.mainFrame = mainFrame;
        this.searchPanel = searchPanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.search(this.searchPanel.getInputText().trim());
    }
}
