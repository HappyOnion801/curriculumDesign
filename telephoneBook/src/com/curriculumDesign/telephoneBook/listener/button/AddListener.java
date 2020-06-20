package com.curriculumDesign.telephoneBook.listener.button;

import com.curriculumDesign.telephoneBook.service.Service;
import com.curriculumDesign.telephoneBook.ui.panel.ButtonItem;
import com.curriculumDesign.telephoneBook.ui.panel.InputItem;
import com.curriculumDesign.telephoneBook.ui.panel.LabelItem;
import com.curriculumDesign.telephoneBook.ui.frame.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-14
 * @ Github: HappyOnion801
 */
public class AddListener implements ActionListener {
    private MainFrame mainFrame;
    private Service service = Service.getInstance();

    public AddListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.mainFrame.getSearch().clear();
        this.mainFrame.getShowPanel().deleteAll();
        this.mainFrame.hidSearchPanel();

        this.mainFrame.getShowPanel().addItem(
                new LabelItem(
                        this.mainFrame.getWidth(),
                        30,
                        this.service.getText("addFriend")
                )
        );

        int itemWidth = this.mainFrame.getWidth() - 30;

        String[] keys = new String[]{"name", "telephone", "email", "other", "group"};

        for (String key : keys) {
            this.mainFrame.getShowPanel().addItem(
                    new InputItem(
                            itemWidth,
                            this.service.getText(key),
                            key,
                            false
                    )
            );
        }

        ButtonItem button = new ButtonItem(
                itemWidth,
                this.service.getText("create")
        );
        button.setButtonListener(new CreateFriendListener(this.mainFrame));

        this.mainFrame.getShowPanel().addItem(button);
        this.mainFrame.repaint();
        this.mainFrame.setVisible(true);
    }
}