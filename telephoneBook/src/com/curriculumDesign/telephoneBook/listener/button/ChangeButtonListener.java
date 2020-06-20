package com.curriculumDesign.telephoneBook.listener.button;

import com.curriculumDesign.telephoneBook.model.Friend;
import com.curriculumDesign.telephoneBook.service.Service;
import com.curriculumDesign.telephoneBook.ui.panel.ButtonItem;
import com.curriculumDesign.telephoneBook.ui.panel.InputItem;
import com.curriculumDesign.telephoneBook.ui.panel.LabelItem;
import com.curriculumDesign.telephoneBook.ui.frame.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-16
 * @ Github: HappyOnion801
 */
public class ChangeButtonListener implements ActionListener {
    private Service service = Service.getInstance();
    private MainFrame mainFrame;
    private Friend friend;

    public ChangeButtonListener(MainFrame mainFrame, Friend friend) {
        this.mainFrame = mainFrame;
        this.friend = friend;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.mainFrame.getShowPanel().deleteAll();
        this.mainFrame.hidSearchPanel();
        this.service.setNowPage("changeInformation");
        this.mainFrame.getShowPanel().addItem(
                new LabelItem(
                        this.mainFrame.getWidth(),
                        30,
                        this.service.getText("changeInformation")
                )
        );

        int itemWidth = this.mainFrame.getWidth() - 30;
        HashMap<String, LinkedList<String>> attribute = this.service.friendToMap(this.friend);
        String[] keys = new String[]{"name", "telephone", "email", "other", "group"};

        for (String key : keys) {
            this.mainFrame.getShowPanel().addItem(
                    new InputItem(
                            itemWidth,
                            this.service.getText(key),
                            key,
                            false,
                            attribute.get(key)
                    )
            );
        }

        ButtonItem button = new ButtonItem(
                this.mainFrame.getWidth() - 30,
                this.service.getText("saveChange")
        );
        SaveChangeListener saveChangeListener = new SaveChangeListener(this.mainFrame, this.friend);
        button.setButtonListener(saveChangeListener);
        this.mainFrame.getShowPanel().addItem(button);

        this.mainFrame.repaint();
        this.mainFrame.setVisible(true);
    }
}
