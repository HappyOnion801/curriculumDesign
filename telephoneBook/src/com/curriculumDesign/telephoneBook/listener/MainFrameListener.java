package com.curriculumDesign.telephoneBook.listener;

import com.curriculumDesign.telephoneBook.model.Friend;
import com.curriculumDesign.telephoneBook.service.Service;
import com.curriculumDesign.telephoneBook.ui.FriendItem;
import com.curriculumDesign.telephoneBook.ui.LabelItem;
import com.curriculumDesign.telephoneBook.ui.MainFrame;

import javax.imageio.ImageIO;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-14
 * @ Github: HappyOnion801
 */
public class MainFrameListener implements WindowListener {
    private Service service = Service.getInstance();
    private MainFrame mainFrame;

    public MainFrameListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    private void addLabel(String text) {
        this.mainFrame.getShowPanel().addItem(
                new LabelItem(
                        this.mainFrame.getWidth(),
                        30,
                        text
                )
        );
    }

    private void addItem(Friend friend) {
        FriendItem friendItem = new FriendItem(
                friend,
                (int) (this.mainFrame.getWidth() * 0.8),
                new String[]{this.service.getText("telephone"), this.service.getText("email")}
        );
        friendItem.addMouseListener(new FriendItemListener(this.mainFrame, friendItem));
        this.mainFrame.getShowPanel().addItem(friendItem);
    }

    private void addItems(List<Friend> friends) {
        for (Friend friend : friends) {
            this.addItem(friend);
        }
    }

    public void addSearchResult(List<Friend> res) {
        this.mainFrame.getShowPanel().deleteAll();

        if (res.size() == 0) {
            this.addLabel(this.service.getText("notFind"));
        } else {
            this.addItems(res);
        }
        this.mainFrame.repaint();
        this.mainFrame.setVisible(true);
    }

    public void addFriendItem() {
        this.mainFrame.getShowPanel().deleteAll();

        LinkedList<Friend> allFriend = this.service.getAllFriend();
        if (allFriend.size() == 0) {
            this.addLabel(this.service.getText("noFriends"));
        } else {
            this.addLabel(this.service.getText("all"));
            for (Friend friend : allFriend) {
                addItem(friend);
            }
        }

        this.mainFrame.displaySearchPanel();
        this.mainFrame.repaint();
        this.mainFrame.setVisible(true);
    }

    public void addFriendGroup() {
        this.mainFrame.getShowPanel().deleteAll();

        HashMap<String, LinkedList<Friend>> all = this.service.getFriendByGroup();
        if (all.keySet().size() == 0) {
            this.addLabel(this.service.getText("noFriends"));
            return;
        }

        Set<String> groups = all.keySet();
        for (String group : groups) {
            this.addLabel(group);

            LinkedList<Friend> friends = all.get(group);
            for (Friend friend : friends) {
                this.addItem(friend);
            }
        }

        this.mainFrame.displaySearchPanel();
        this.mainFrame.repaint();
        this.mainFrame.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {
        this.mainFrame.setTitle(this.service.getText("telephoneBook"));
        this.mainFrame.getSearch().setText(this.service.getText("search"));
        BufferedImage icon;
        try {
            icon = ImageIO.read(new FileInputStream(this.service.getIconPath()));
            this.mainFrame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addFriendItem();

        this.mainFrame.getButton().setText(
                this.service.getText("setting"),
                this.service.getText("allFriend"),
                this.service.getText("group"),
                this.service.getText("add")
        );
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        this.mainFrame.dispose();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {
    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
