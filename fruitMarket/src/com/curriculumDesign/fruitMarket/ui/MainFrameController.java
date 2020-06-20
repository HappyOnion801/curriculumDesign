package com.curriculumDesign.fruitMarket.ui;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-17
 * @ Github: HappyOnion801
 */
public class MainFrameController extends Login{
    //实现功能
    @Override
    protected void showAdminDialog() {
        System.out.println("进入系统");
        new AdminDialogController().setVisible(true);
    }
}