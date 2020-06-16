package com.curriculumDesig.telephoneBook.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-13
 * @ Github: HappyOnion801
 */
public class SearchPanel extends JPanel {
    private JTextField input;
    private JButton search;

    private String buttonText = "";

    private int width;
    private int height;

    private Font font;

    public SearchPanel(int width, int height) {
        this.width = width - 30;
        this.height = height;

        this.input = new JTextField();
        this.search = new JButton(this.buttonText);

        this.font = new Font("宋体", Font.PLAIN, (int) (this.width * 0.035));

        this.init();

        super.setPreferredSize(new Dimension(this.width, this.height));
        super.add(input);
        super.add(search);
    }

    private void init() {

        Dimension inputDimension = new Dimension(
                (int) (this.width * 0.55),
                (int) (this.height * 0.7)
        );
        this.input.setPreferredSize(inputDimension);
        this.input.setFont(font);

        Dimension searchDimension = new Dimension(
                (int) (this.width * 0.2),
                (int) (this.height * 0.7)
        );
        this.search.setPreferredSize(searchDimension);
        this.search.setFont(font);
        this.search.setFocusPainted(false);

    }

    public String getInputText() {
        return this.input.getText();
    }

    public void setActionListener(ActionListener actionListener) {
        this.search.addActionListener(actionListener);
    }

    public void setText(String text) {
        this.buttonText = text;
        this.search.setText(text);
    }

    public void clear(){
        this.input.setText("");
    }
}
