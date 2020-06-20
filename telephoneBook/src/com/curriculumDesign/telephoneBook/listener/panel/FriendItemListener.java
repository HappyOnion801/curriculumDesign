package com.curriculumDesign.telephoneBook.listener.panel;

import com.curriculumDesign.telephoneBook.listener.button.ChangeButtonListener;
import com.curriculumDesign.telephoneBook.listener.frame.MainFrameListener;
import com.curriculumDesign.telephoneBook.service.Service;
import com.curriculumDesign.telephoneBook.ui.frame.MainFrame;
import com.curriculumDesign.telephoneBook.ui.panel.ButtonItem;
import com.curriculumDesign.telephoneBook.ui.panel.CardItem;
import com.curriculumDesign.telephoneBook.ui.panel.FriendItem;
import com.curriculumDesign.telephoneBook.ui.panel.LabelItem;

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
            this.mainFrame.getShowPanel().deleteAll();
            if (this.service.delete(this.friendItem.getFriend())) {
                if ("group".equals(this.service.getNowPage()))
                    new MainFrameListener(this.mainFrame).addFriendGroup();
                else if ("all".equals(this.service.getNowPage()))
                    new MainFrameListener(this.mainFrame).addFriendItem();
                mainFrame.repaint();
                mainFrame.setVisible(true);
                JOptionPane.showMessageDialog(this.mainFrame, this.service.getText("deleteSuccess"));
            } else {
                JOptionPane.showMessageDialog(this.mainFrame, this.service.getText("deleteFail"));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            this.service.setNowPage("information");
            this.service.setNowFriend(this.friendItem.getFriend());
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
