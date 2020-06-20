package com.curriculumDesign.telephoneBook.service.baseService;

import com.curriculumDesign.telephoneBook.model.Friend;

import java.io.*;
import java.util.LinkedList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-13
 * @ Github: HappyOnion801
 */
public class DataService {
    private static SetService setService = SetService.getInstance();
    private static DataService instance = new DataService();
    private static LinkedList<Friend> data;
    private static File file;

    private DataService() {
        data = new LinkedList<>();
        file = new File(setService.getDataPath());
        if (file.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                Object object = ois.readObject();
                if (object instanceof LinkedList) {
                    data = (LinkedList<Friend>) object;
                }
                ois.close();
            } catch (Exception e) {
                System.out.println("读档时发生错误！");
                e.printStackTrace();
            }
        }
    }

    public static DataService getInstance() {
        return instance;
    }

    public void setData(LinkedList<Friend> d){
        data = d;
    }

    public boolean addFriend(Friend friend) {
        boolean res = data.add(friend);
        if (res) res = this.save();
        return res;
    }

    public boolean update(Friend friend) {
        boolean res = data.remove(friend);
        if(res) res = data.add(friend);
        if(res) res = this.save();
        return res;
    }


    public boolean deleteFriend(Friend friend) {
        boolean res = data.remove(friend);
        if (res) res = this.save();
        return res;
    }

    public LinkedList<Friend> getAllFriend() {
        return data;
    }

    public boolean save() {
        try {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
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
