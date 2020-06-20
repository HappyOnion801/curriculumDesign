package com.curriculumDesign.telephoneBook.listener.button;

import com.curriculumDesign.telephoneBook.listener.frame.MainFrameListener;
import com.curriculumDesign.telephoneBook.model.Friend;
import com.curriculumDesign.telephoneBook.service.Service;
import com.curriculumDesign.telephoneBook.ui.frame.MainFrame;
import com.curriculumDesign.telephoneBook.ui.panel.SearchPanel;

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
            List<Friend> res = this.service.searchTelephone(str);
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
