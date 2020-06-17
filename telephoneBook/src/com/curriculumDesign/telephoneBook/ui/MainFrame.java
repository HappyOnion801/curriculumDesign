package com.curriculumDesign.telephoneBook.ui;

import com.curriculumDesign.telephoneBook.listener.*;

import javax.swing.*;
import java.awt.*;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-13
 * @ Github: HappyOnion801
 */
public class MainFrame extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;

    private SearchPanel search;
    private ShowPanel content;
    private ButtonPanel button;

    public MainFrame() {
        super.setLayout(new BorderLayout());
        super.setSize(new Dimension(WIDTH, HEIGHT));
        super.setResizable(false);

        this.init();

        super.add(search, BorderLayout.NORTH);
        super.add(content, BorderLayout.CENTER);
        super.add(this.button, BorderLayout.SOUTH);

        super.addWindowListener(new MainFrameListener(this));
        super.setVisible(true);
    }

    private void init() {
        this.search = new SearchPanel(WIDTH, 40);
        this.content = new ShowPanel(WIDTH);
        this.button = new ButtonPanel(WIDTH, 40);

        this.search.setActionListener(new SearchListener(this, this.search));
        this.button.setFirstListener(new SetListener(this));
        this.button.setSecondListener(new AllListener(this));
        this.button.setThirdListener(new GroupListener(this));
        this.button.setLastListener(new AddListener(this));
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    public ShowPanel getShowPanel() {
        return this.content;
    }

    public SearchPanel getSearch() {
        return this.search;
    }

    public ButtonPanel getButton() {
        return this.button;
    }

    public void hidSearchPanel(){
        super.remove(this.search);
    }

    public void displaySearchPanel(){
        super.add(this.search,BorderLayout.NORTH);
    }
}