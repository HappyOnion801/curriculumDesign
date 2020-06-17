package com.curriculumDesign.telephoneBook.listener;

import com.curriculumDesign.telephoneBook.service.Service;
import com.curriculumDesign.telephoneBook.ui.*;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-14
 * @ Github: HappyOnion801
 */
public class FriendItemListener implements MouseListener {
    private FriendItem friendItem;
    private MainFrame mainFrame;
    private Service service = Service.getInstance();

    public FriendItemListener(MainFrame mainFrame, FriendItem friendItem) {
        this.mainFrame = mainFrame;
        this.friendItem = friendItem;
    }

    public void showFriend() {
        this.mainFrame.getShowPanel().deleteAll();
        this.mainFrame.hidSearchPanel();
        this.mainFrame.getShowPanel().addItem(
                new LabelItem(
                        this.mainFrame.getWidth(),
                        30,
                        this.service.getText("information")
                )
        );

        HashMap<String, LinkedList<String>> attribute = this.service.friendToMap(this.friendItem.getFriend());
        int itemWidth = this.mainFrame.getWidth() - 30;
        String[] keys = new String[]{"name", "telephone", "email", "other", "group"};

        for (String key : keys) {
            this.mainFrame.getShowPanel().addItem(
                    new CardItem(
                            itemWidth,
                            this.service.getText(key) + " :",
                            attribute.get(key)
                    )
            );
        }

        ButtonItem button = new ButtonItem(this.mainFrame.getWidth() - 30, this.service.getText("change"));
        button.setButtonListener(new ChangeButtonListener(this.mainFrame, this.friendItem.getFriend()));

        this.mainFrame.getShowPanel().addItem(button);

        this.mainFrame.repaint();
        this.mainFrame.setVisible(true);
    }

    public void deleteFriend() {
        int n = JOptionPane.showConfirmDialog(
                this.friendItem,
                this.service.getText("deletePrompt") + "?",
                this.service.getText("delete"),
                JOptionPane.YES_NO_OPTION
        );
        if (n == JOptionPane.YES_OPTION) {
            this.mainFrame.getShowPanel().delete(this.friendItem.getFriend());
            this.service.delete(this.friendItem.getFriend());
            mainFrame.repaint();
            mainFrame.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            this.showFriend();
        } else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
            this.deleteFriend();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }
}
