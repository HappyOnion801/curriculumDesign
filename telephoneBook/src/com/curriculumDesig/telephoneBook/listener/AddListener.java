package com.curriculumDesig.telephoneBook.listener;

import com.curriculumDesig.telephoneBook.service.Service;
import com.curriculumDesig.telephoneBook.ui.ButtonItem;
import com.curriculumDesig.telephoneBook.ui.InputItem;
import com.curriculumDesig.telephoneBook.ui.LabelItem;
import com.curriculumDesig.telephoneBook.ui.MainFrame;

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
        boolean[] addable = new boolean[]{false, true, true, false, false};

        for (int i = 0; i < keys.length; i++) {
            this.mainFrame.getShowPanel().addItem(
                    new InputItem(
                            itemWidth,
                            this.service.getText(keys[i]),
                            keys[i],
                            addable[i]
                    )
            );
        }

        ButtonItem button = new ButtonItem(
                itemWidth,
                this.service.getText("add")
        );
        button.setButtonListener(new CreateFriendListener(this.mainFrame));

        this.mainFrame.getShowPanel().addItem(button);
        this.mainFrame.repaint();
        this.mainFrame.setVisible(true);
    }
}