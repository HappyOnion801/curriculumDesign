package com.curriculumDesign.telephoneBook.ui.frame;

import com.curriculumDesign.telephoneBook.listener.frame.LoginFrameListener;
import com.curriculumDesign.telephoneBook.ui.panel.ShowPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-20
 * @ Github: HappyOnion801
 */
public class LoginFrame extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 270;

    private ShowPanel showPanel;

    public LoginFrame() {
        super.setSize(WIDTH, HEIGHT);
        super.setResizable(false);

        this.showPanel = new ShowPanel(WIDTH);
        super.setLayout(new FlowLayout());
        super.add(this.showPanel);
        super.setLocation(500,300);

        super.addWindowListener(new LoginFrameListener(this));
        super.setVisible(true);
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public ShowPanel getShowPanel(){
        return this.showPanel;
    }

}
