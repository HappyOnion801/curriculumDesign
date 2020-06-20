package com.curriculumDesign.telephoneBook.model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-13
 * @ Github: HappyOnion801
 */
public class Friend implements Serializable {
    private static final long serialVersionUID = 2935986423648346564L;

    private int id;
    private String name;
    private String telephone;
    private String email;
    private String other;
    private String group;

    public Friend(int id, String name, String number, String email, String other, String group) {
        this.id = id;
        this.name = name;
        this.telephone = number;
        this.email = email;
        this.other = other;
        this.group = group;
    }

    public Friend(LinkedList<String> name, LinkedList<String> telephone, LinkedList<String> email, LinkedList<String> other, LinkedList<String> group) {
        this(
                -1,
                name.getFirst(),
                telephone.getFirst(),
                email.getFirst(),
                other.getFirst(),
                group.getFirst()
        );
    }

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", other='" + other + '\'' +
                ", group='" + group + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }


    public LinkedList<String> getNumber() {
        LinkedList<String> res = new LinkedList<>();
        res.add(this.telephone);
        return res;
    }

    public LinkedList<String> getEmail() {
        LinkedList<String> res = new LinkedList<>();
        res.add(this.email);
        return res;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOther() {
        return other;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (object instanceof Friend) {
            Friend friend = (Friend) object;
            return this.id == friend.getId();
        }
        return false;
    }
}
