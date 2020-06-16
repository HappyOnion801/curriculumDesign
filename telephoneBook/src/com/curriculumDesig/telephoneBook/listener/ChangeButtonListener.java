package com.curriculumDesig.telephoneBook.listener;

import com.curriculumDesig.telephoneBook.model.Friend;
import com.curriculumDesig.telephoneBook.service.Service;
import com.curriculumDesig.telephoneBook.ui.ButtonItem;
import com.curriculumDesig.telephoneBook.ui.InputItem;
import com.curriculumDesig.telephoneBook.ui.LabelItem;
import com.curriculumDesig.telephoneBook.ui.MainFrame;

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
        boolean[] addable = new boolean[]{false, true, true, false, false};

        for (int i = 0; i < keys.length; i++) {
            this.mainFrame.getShowPanel().addItem(
                    new InputItem(
                            itemWidth,
                            this.service.getText(keys[i]),
                            keys[i],
                            addable[i],
                            attribute.get(keys[i])
                    )
            );
        }


        ButtonItem button = new ButtonItem(
                this.mainFrame.getWidth() - 30,
                this.service.getText("change")
        );
        ChangeFriendListener changeFriendListener = new ChangeFriendListener(this.mainFrame, this.friend);
        button.setButtonListener(changeFriendListener);
        this.mainFrame.getShowPanel().addItem(button);

        this.mainFrame.repaint();
        this.mainFrame.setVisible(true);
    }
}
