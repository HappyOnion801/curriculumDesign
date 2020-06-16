package com.curriculumDesig.telephoneBook.model;

import com.curriculumDesig.telephoneBook.ui.FriendItem;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-13
 * @ Github: HappyOnion801
 */
public class Friend implements Serializable {
    private String name;
    private LinkedList<String> number;
    private LinkedList<String> email;
    private String other;
    private String group;
    private String id;

    private Friend() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 128; i++) {
            sb.append(random.nextInt(10));
        }
        this.id = sb.toString();
    }

    public Friend(String name, String number, String email, String other, String group) {
        this();
        this.name = name;
        this.number = new LinkedList<String>();
        this.email = new LinkedList<String>();
        this.other = other;
        this.group = group;
        this.email.add(email);
        this.number.add(number);
    }

    public Friend(LinkedList<String> name,LinkedList<String> telephone,LinkedList<String> email,LinkedList<String> other,LinkedList<String> group){
        this();
        this.name = name.getFirst();
        this.number = telephone;
        this.email = email;
        this.other = other.getFirst();
        this.group = group.getFirst();
    }

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", other='" + other + '\'' +
                ", group='" + group + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<String> getNumber() {
        return number;
    }

    public void setNumber(LinkedList<String> number) {
        this.number = number;
    }

    public LinkedList<String> getEmail() {
        return email;
    }

    public void setEmail(LinkedList<String> email) {
        this.email = email;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getId(){
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if(o instanceof Friend){
            Friend f = (Friend) o;
            return this.id.equals(f.getId());
        }else if(o instanceof FriendItem){
            FriendItem fi = (FriendItem) o;
            return fi.equals(this);
        }
        return false;
    }
}
