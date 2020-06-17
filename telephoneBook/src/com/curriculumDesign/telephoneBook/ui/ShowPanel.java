package com.curriculumDesign.telephoneBook.ui;

import com.curriculumDesign.telephoneBook.model.Friend;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-13
 * @ Github: HappyOnion801
 */
public class ShowPanel extends JScrollPane {
    private int width;
    private int height = 5;
    private JPanel content;
    private LinkedList<Item> items;

    public ShowPanel(int width) {
        super(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.width = width - 30;
        this.content = new JPanel();
        this.items = new LinkedList<>();
        super.setViewportView(this.content);
        super.setBorder(null);
    }

    public void addItem(Item item) {
        this.height += item.getHeight() + 5;
        this.content.setPreferredSize(new Dimension(this.width, this.height));
        this.items.add(item);
        this.content.add(item);
    }

    public void delete(Friend friend) {
        int index = this.items.indexOf(friend);
        if (index >= 0) {
            Item fi = this.items.get(index);
            this.height -= (fi.getHeight() + 5);
            this.content.remove(fi);
            this.items.remove(index);
            this.content.setPreferredSize(
                    new Dimension(this.width, this.height)
            );
        }
    }

    public void reLoad() {
        this.height = 5;
        for (Item item : this.items) {
            this.height += item.getHeight() + 5;
        }
        this.content.setPreferredSize(new Dimension(this.width, this.height));
    }

    public void deleteAll() {
        for (Item item : this.items) {
            this.content.remove(item);
        }

        this.items.clear();

        this.height = 5;
        this.content.setPreferredSize(
                new Dimension(this.width, this.height)
        );
    }

    public LinkedList<Inputable> getAllInputItem(){
        LinkedList<Inputable> res = new LinkedList<>();
        for(Item item : this.items){
            if(item instanceof Inputable) res.add((Inputable) item);
        }
        return res;
    }
}
