package com.curriculumDesign.fruitMarket.ui;

import com.curriculumDesign.fruitMarket.model.Fruit;
import com.curriculumDesign.fruitMarket.service.AdminService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-17
 * @ Github: HappyOnion801
 */
public class AdminDialogController extends AbstractAdminDialog {
    public AdminDialogController() {
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
            @Override
            public void windowClosed(WindowEvent e) {
            }
            @Override
            public void windowIconified(WindowEvent e) {
            }
            @Override
            public void windowDeiconified(WindowEvent e) {
            }
            @Override
            public void windowActivated(WindowEvent e) {
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    protected void delFruit() {
        // 删除水果
        String number = delNumberText.getText();
        AdminService service = new AdminService();
        if (service.delFruit(number)) {
            addAllList();// 刷新表格
        } else {
            JOptionPane.showMessageDialog(this, "没有这个编号的水果，删除失败");
        }
    }

    @Override
    protected void updateFruit() {
        // 更新水果
        String number = updateNumberText.getText();
        String name = updateNameText.getText();
        String price = updatePriceText.getText();
        String unit = updateUnitText.getText();
        AdminService service = new AdminService();
        if (service.updateFruit(number, name, price, unit)) {
            addAllList();
        } else {
            JOptionPane.showMessageDialog(this, "没有这个编号的水果，更新失败");
        }
    }

    @Override
    protected void addFruit() {
        // 添加水果
        String number = addNumberText.getText();
        String name = addNameText.getText();
        String price = addPriceText.getText();
        String unit = addUnitText.getText();
        AdminService service = new AdminService();
        if (service.addFruit(number, name, price, unit)) {
            addAllList();
        } else {
            JOptionPane.showMessageDialog(this, "编号不能重复");
        }
    }

    protected void findFruit() {
        // 查询水果
        String name = findNameText.getText();
        AdminService service = new AdminService();
        if (service.findFruit(name)) {
            addAllList();// 刷新表格
        } else {
            JOptionPane.showMessageDialog(this, "对不起，本商店无此类水果");
        }
    }

    @Override
    protected void addAllList() {
        AdminService service = new AdminService();
        ArrayList<Fruit> data = service.getAll();
        String[] thead = { "水果编号", "水果名称", "水果单价（元）", "计价单位" };
        String[][] tbody = new String[data.size()][4];
        for (int i = 0; i < tbody.length; i++) {
            Fruit f = data.get(i);
            tbody[i][0] = f.getNumber();
            tbody[i][1] = f.getName();
            tbody[i][2] = Double.toString(f.getPrice());
            tbody[i][3] = f.getUnit();
        }
        TableModel model = new DefaultTableModel(tbody, thead);
        table.setModel(model);
    }
}