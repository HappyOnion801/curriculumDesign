package com.curriculumDesign.telephoneBook;

import com.curriculumDesign.telephoneBook.ui.MainFrame;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-17
 * @ Github: HappyOnion801
 */
public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.curriculumDesign.telephoneBook.setting.Setting");
            Class.forName("com.curriculumDesign.telephoneBook.dataBaseService.DataBaseService");
            Class.forName("com.curriculumDesign.telephoneBook.service.Service");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        new MainFrame();
    }
}
