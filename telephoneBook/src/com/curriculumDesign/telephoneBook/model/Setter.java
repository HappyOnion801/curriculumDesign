package com.curriculumDesign.telephoneBook.model;

import java.io.Serializable;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-16
 * @ Github: HappyOnion801
 */
public class Setter implements Serializable {
    private static final long serialVersionUID = 3082921891473463289L;
    private int language = 0;
    public int getLanguage() {
        return language;
    }
    public void setLanguage(int language) {
        this.language = language;
    }
}
