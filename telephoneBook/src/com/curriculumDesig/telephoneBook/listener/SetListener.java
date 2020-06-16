package com.curriculumDesig.telephoneBook.listener;

import com.curriculumDesig.telephoneBook.service.Service;
import com.curriculumDesig.telephoneBook.ui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-14
 * @ Github: HappyOnion801
 */
public class SetListener implements ActionListener {
    private MainFrame mainFrame;
    private Service service = Service.getInstance();

    public SetListener(MainFrame frame) {
        this.mainFrame = frame;
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
                        this.service.getText("setting")
                )
        );

        this.mainFrame.getShowPanel().addItem(
                new SetItem(
                        this.mainFrame.getWidth() - 30,
                        this.service.getText("language"),
                        "language",
                        this.service.getAllLanguage()
                )
        );

        ButtonItem derived = new ButtonItem(this.mainFrame.getWidth() - 30, this.service.getText("derived"));
        derived.setButtonListener(new DerivedListener(this.mainFrame));
        ButtonItem save = new ButtonItem(this.mainFrame.getWidth() - 30, this.service.getText("save"));
        save.setButtonListener(new SaveSettingListener(this.mainFrame));

        this.mainFrame.getShowPanel().addItem(derived);
        this.mainFrame.getShowPanel().addItem(save);

        LinkedList<String> about = new LinkedList<>();
        about.add(this.service.getText("authorMaCode"));
        about.add(this.service.getText("githubHappyOnion801"));
        about.add(this.service.getText("aboutOpenSourceSoftwareIsProhibitedFromSelling"));
        about.add(this.service.getText("versionV1.0"));
        this.mainFrame.getShowPanel().addItem(
                new CardItem(
                        this.mainFrame.getWidth() - 30,
                        this.service.getText("about") + " :",
                        about
                )
        );

        this.mainFrame.repaint();
        this.mainFrame.setVisible(true);

    }
}
