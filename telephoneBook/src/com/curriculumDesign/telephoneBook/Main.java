package com.curriculumDesign.telephoneBook;

import com.curriculumDesign.telephoneBook.ui.frame.LoginFrame;
import com.curriculumDesign.telephoneBook.ui.frame.MainFrame;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-17
 * @ Github: HappyOnion801
 */
public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.curriculumDesign.telephoneBook.service.baseService.SetService");
            Class.forName("com.curriculumDesign.telephoneBook.service.baseService.DataService");
            Class.forName("com.curriculumDesign.telephoneBook.service.Service");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        new LoginFrame();
    }
}
