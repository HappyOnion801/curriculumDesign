package com.curriculumDesig.telephoneBook.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-15
 * @ Github: HappyOnion801
 */
public class InputItem extends Item implements Inputable {
    private String title;
    private String id;
    private boolean addAble;

    private Font titleFont;
    private Color backgroundColor;

    private JPanel myself;
    private JLabel titleLabel;
    private LinkedList<JTextField> inputs;
    private JButton button;


    public InputItem(int width, String title, String id, boolean addAble) {
        super.width = width;
        super.height = 35;
        this.title = title;
        this.id = id;
        this.addAble = addAble;

        this.init();

        super.add(this.titleLabel);

        this.addInput();

        if (addAble) {
            this.button = new JButton("+");
            button.setPreferredSize(
                    new Dimension(
                            (int) (this.width * 0.25),
                            15)
            );
            this.button.setBorder(null);
            this.button.setBackground(this.backgroundColor);
            this.button.setFocusPainted(false);
            this.height += 15;
            super.add(this.button);
            this.button.addActionListener(actionEvent -> {
                        InputItem.this.addInput();
                        MainFrame mainFrame = (MainFrame) InputItem.this.getRootFrame(myself);
                        mainFrame.getShowPanel().reLoad();
                        mainFrame.repaint();
                        mainFrame.setVisible(true);
                    }
            );
        }
        super.setPreferredSize(new Dimension(this.width, this.height));
        super.setBackground(this.backgroundColor);
    }

    public void addInput() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension((int) (this.width * 0.8), 25));
        this.inputs.add(textField);
        this.height += 30;
        super.add(textField, this.inputs.size());
        super.setPreferredSize(new Dimension(this.width, this.height));
    }

    public void init() {
        this.backgroundColor = new Color(255, 255, 255);
        this.titleFont = new Font("宋体", Font.PLAIN, 15);
        this.inputs = new LinkedList<>();

        this.titleLabel = new JLabel(this.title + " :");
        this.titleLabel.setFont(this.titleFont);
        this.titleLabel.setPreferredSize(new Dimension((int) (this.width * 0.9), 20));
        this.myself = this;
    }

    public InputItem(int width, String title, String id, boolean addAble, LinkedList<String> content) {
        this(width, title, id, addAble);
        for (int i = 1; i < content.size(); i++) {
            this.addInput();
        }
        int i = 0;
        for (String str : content) {
            this.inputs.get(i++).setText(str);
        }
    }

    public InputItem(int width, String title, String id, boolean addAble, String content) {
        this(width, title, id, addAble);
        this.inputs.getFirst().setText(content);
    }

    private JFrame getRootFrame(Component com) {
        Component parent = com.getParent();
        if (parent instanceof JFrame) return (JFrame) parent;
        else return getRootFrame(parent);

    }

    @Override
    public int getHeight() {
        return super.height;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public LinkedList<String> getInputContent() {
        LinkedList<String> res = new LinkedList<>();
        for (JTextField textField : this.inputs) {
            if (!"".equals(textField.getText())) res.add(textField.getText());
        }
        return res;
    }
}
