package com.curriculumDesign.telephoneBook.listener.frame;

import com.curriculumDesign.telephoneBook.listener.button.LoginButtonListener;
import com.curriculumDesign.telephoneBook.service.Service;
import com.curriculumDesign.telephoneBook.ui.frame.LoginFrame;
import com.curriculumDesign.telephoneBook.ui.panel.ButtonItem;
import com.curriculumDesign.telephoneBook.ui.panel.InputItem;
import com.curriculumDesign.telephoneBook.ui.panel.LabelItem;
import com.curriculumDesign.telephoneBook.ui.panel.PasswordItem;

import javax.imageio.ImageIO;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-20
 * @ Github: HappyOnion801
 */
public class LoginFrameListener implements WindowListener {
    private Service service = Service.getInstance();
    private LoginFrame loginFrame;

    public LoginFrameListener(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {
        this.loginFrame.getShowPanel().addItem(
                new LabelItem(400, 30, this.service.getText("userLogin"))
        );
        this.loginFrame.getShowPanel().addItem(
                new InputItem(this.loginFrame.getWidth() - 150, this.service.getText("id"), "id", false)
        );
        this.loginFrame.getShowPanel().addItem(
                new PasswordItem(this.loginFrame.getWidth() - 150, this.service.getText("password"), "password")
        );
        ButtonItem login = new ButtonItem(this.loginFrame.getWidth(), this.service.getText("login"));
        login.setButtonListener(new LoginButtonListener(this.loginFrame));


        this.loginFrame.getShowPanel().addItem(login);
        this.loginFrame.setTitle(this.service.getText("login"));
        try {
            BufferedImage icon = ImageIO.read(new FileInputStream(this.service.getIconPath()));
            this.loginFrame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.loginFrame.setVisible(true);
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        this.loginFrame.dispose();
        System.exit(0);
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
