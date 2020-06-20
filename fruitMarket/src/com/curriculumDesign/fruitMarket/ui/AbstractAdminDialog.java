package com.curriculumDesign.fruitMarket.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-17
 * @ Github: HappyOnion801
 */
public abstract class AbstractAdminDialog extends JFrame {
    // 表格组件和标签组件
    private JLabel titleLabel = new JLabel("水果列表");
    protected JTable table = new JTable();
    private JScrollPane tablePane = new JScrollPane();
    private JLabel numberLabel = new JLabel("水果编号");
    private JLabel nameLabel = new JLabel("水果名称");
    private JLabel priceLabel = new JLabel("水果单价");
    private JLabel unitLabel = new JLabel("计价单位");
    // 添加文本框和按钮
    protected JTextField addNumberText = new JTextField(6);
    protected JTextField addNameText = new JTextField(6);
    protected JTextField addPriceText = new JTextField(6);
    protected JTextField addUnitText = new JTextField(6);
    private JButton addBtn = new JButton("添加水果");

    protected JTextField updateNumberText = new JTextField(6);
    protected JTextField updateNameText = new JTextField(6);
    protected JTextField updatePriceText = new JTextField(6);
    protected JTextField updateUnitText = new JTextField(6);
    private JButton updateBtn = new JButton("编辑水果");

    protected JTextField delNumberText = new JTextField(6);
    private JButton delBtn = new JButton("删除水果");

    protected JTextField findNameText = new JTextField(6);
    private JButton findBtn = new JButton("查询水果");

    public AbstractAdminDialog() {
        init();// 初始化窗体
        addComponents();// 添加组件
    }

    private void init() {
        setTitle("水果超市管理");
        setSize(600, 450);
        setLocation(300, 200);
        setResizable(false);// 不允许改变大小
        addAllList();// 载入表格数据
    }

    // 添加组件
    private void addComponents() {
        setLayout(null);
        // 装入表格部分的组件
        titleLabel.setBounds(265, 20, 70, 25);
        add(titleLabel);// 加到窗体
        table.getTableHeader().setReorderingAllowed(false);// 不允许拖动列表
        table.getTableHeader().setResizingAllowed(false);// 不允许改变列大小
        table.setEnabled(false);// 不允许编辑
        tablePane.setBounds(50, 50, 500, 200);// 表格面板
        tablePane.setViewportView(table);// 把table装入面板
        add(tablePane);
        // 编辑部分的组件
        numberLabel.setBounds(50, 250, 70, 25);
        nameLabel.setBounds(150, 250, 70, 25);
        priceLabel.setBounds(250, 250, 70, 25);
        unitLabel.setBounds(350, 250, 70, 25);
        add(numberLabel);
        add(nameLabel);
        add(priceLabel);
        add(unitLabel);

        // 添加水果组件
        addNumberText.setBounds(50, 280, 80, 25);
        addNameText.setBounds(150, 280, 80, 25);
        addPriceText.setBounds(250, 280, 80, 25);
        addUnitText.setBounds(350, 280, 80, 25);
        addBtn.setBounds(460, 280, 90, 25);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFruit();// 添加水果
                addAllList();// 刷新水果列表
            }
        });
        add(addNumberText);
        add(addNameText);
        add(addPriceText);
        add(addUnitText);
        add(addBtn);
        // 编辑水果组件
        updateNumberText.setBounds(50, 310, 80, 25);
        updateNameText.setBounds(150, 310, 80, 25);
        updatePriceText.setBounds(250, 310, 80, 25);
        updateUnitText.setBounds(350, 310, 80, 25);
        updateBtn.setBounds(460, 310, 90, 25);
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFruit();// 抽象
                addAllList();// 加载
            }
        });
        add(updateNumberText);
        add(updateNameText);
        add(updatePriceText);
        add(updateUnitText);
        add(updateBtn);
        // 删除水果组件
        delNumberText.setBounds(50, 340, 80, 25);
        delBtn.setBounds(460, 340, 90, 25);
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delFruit();// 抽象
                addAllList();// 加载
            }
        });
        add(delBtn);
        add(delNumberText);
        // 查询水果组件
        findNameText.setBounds(50, 370, 80, 25);
        findBtn.setBounds(460, 370, 90, 25);
        findBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findFruit();// 抽象
                addAllList();// 加载
            }
        });
        add(findBtn);
        add(findNameText);
    }

    protected abstract void delFruit();// 抽象方法。在controller里面实现

    protected abstract void updateFruit();// 抽象方法。在controller里面实现

    protected abstract void addFruit();// 抽象方法。在controller里面实现

    protected abstract void addAllList();// 抽象方法，在controller里面实现

    protected abstract void findFruit();// 抽象方法。在controller里面实现

}
