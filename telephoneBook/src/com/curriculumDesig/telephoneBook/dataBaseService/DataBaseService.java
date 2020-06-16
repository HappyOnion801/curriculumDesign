package com.curriculumDesig.telephoneBook.dataBaseService;

import com.curriculumDesig.telephoneBook.model.Friend;
import com.curriculumDesig.telephoneBook.setting.Setting;

import java.io.*;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-13
 * @ Github: HappyOnion801
 */
public class DataBaseService {
    private static Setting setting = Setting.getInstance();
    private static DataBaseService instance = new DataBaseService();
    private static LinkedList<Friend> data;
    private static File file;

    private DataBaseService() {
        data = new LinkedList<>();
        file = new File(setting.getDataPath());
        if (file.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                data = (LinkedList<Friend>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                System.out.println("读档时发生错误！");
                e.printStackTrace();
            }
        }
    }

    public static DataBaseService getInstance() {
        return instance;
    }

    public boolean addFriend(Friend friend) {
        try {
            this.data.add(friend);
            this.save();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteFriend(Friend friend) {
        boolean res = data.remove(friend);
        if (res) this.save();
        return res;
    }

    public LinkedList<Friend> getAllFriend() {
        return (LinkedList<Friend>) data.clone();
    }

    public int size() {
        return data.size();
    }

    public boolean save() {
        try {
            File parent = file.getParentFile();
            if (!parent.exists()) parent.mkdirs();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(data);
            oos.close();
        } catch (Exception e) {
            System.out.println("存档时发生异常！");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
