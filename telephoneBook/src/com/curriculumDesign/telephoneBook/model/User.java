package com.curriculumDesign.telephoneBook.model;

import java.io.Serializable;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-18
 * @ Github: HappyOnion801
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1759919772199066161L;
    private String id;
    private String passWord;

    public User(String id, String passWord) {
        this.id = id;
        this.passWord = passWord;
    }

    public String getId() {
        return this.id;
    }

    public String getPassWord() {
        return this.passWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
