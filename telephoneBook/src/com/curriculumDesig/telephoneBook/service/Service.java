package com.curriculumDesig.telephoneBook.service;

import com.curriculumDesig.telephoneBook.dataBaseService.DataBaseService;
import com.curriculumDesig.telephoneBook.model.Friend;
import com.curriculumDesig.telephoneBook.setting.Setting;
import com.curriculumDesig.telephoneBook.ui.InputItem;
import com.curriculumDesig.telephoneBook.ui.Inputable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-14
 * @ Github: HappyOnion801
 */
public class Service {
    private static Service instance = new Service();

    private DataBaseService dataService = DataBaseService.getInstance();
    private Setting setting = Setting.getInstance();
    private ResourceBundle rb = setting.getResourceBundle();

    private Service() {
    }

    public static Service getInstance() {
        return instance;
    }

    public String getText(String text) {
        if (this.rb == null) return text;
        return rb.getString(text);
    }

    public void addFriend(Friend friend) {
        this.dataService.addFriend(friend);
    }

    public void delete(Friend friend) {
        System.out.println(this.dataService.deleteFriend(friend));
    }

    public LinkedList<Friend> getAllFriend() {
        return this.dataService.getAllFriend();
    }

    public HashMap<String, LinkedList<Friend>> getFriendByGroup() {
        HashMap<String, LinkedList<Friend>> res = new HashMap<>();
        LinkedList<Friend> allFriend = this.dataService.getAllFriend();

        Iterator<Friend> it = allFriend.iterator();
        while (it.hasNext()) {
            Friend friend = it.next();
            String group = friend.getGroup();
            if (!res.containsKey(group)) {
                res.put(group, new LinkedList<Friend>());
            }
            res.get(group).add(friend);
        }

        return res;
    }

    public List<Friend> searchName(String name) {
        List<Friend> res = new LinkedList<>();
        for (Friend friend : this.dataService.getAllFriend()) {
            if (friend.getName().contains(name)) {
                res.add(friend);
            }
        }
        return res;
    }

    public List<Friend> searchNumber(String num) {
        List<Friend> res = new LinkedList<>();
        for (Friend friend : this.dataService.getAllFriend()) {
            for (String number : friend.getNumber()) {
                if (number.contains(num)) {
                    res.add(friend);
                    break;
                }
            }
        }
        return res;
    }

    public HashMap<String, LinkedList<String>> friendToMap(Friend friend) {
        HashMap<String, LinkedList<String>> res = new HashMap<>();

        LinkedList<String> name = new LinkedList<>();
        LinkedList<String> other = new LinkedList<>();
        LinkedList<String> group = new LinkedList<>();

        name.add(friend.getName());
        other.add(friend.getOther());
        group.add(friend.getGroup());

        res.put("name", name);
        res.put("telephone", friend.getNumber());
        res.put("email", friend.getEmail());
        res.put("other", other);
        res.put("group", group);

        return res;
    }

    public HashMap<String, LinkedList<String>> inputsToMap(LinkedList<Inputable> inputs) {
        HashMap<String, LinkedList<String>> attribute = new HashMap<>();
        for (Inputable inputable : inputs) {
            LinkedList<String> content = new LinkedList<>();
            for (String str : inputable.getInputContent()) {
                String s = str.trim();
                if (!"".equals(s)) {
                    content.add(s);
                }
            }
            attribute.put(inputable.getId(), content);
        }
        return attribute;
    }

    public LinkedList<String> getAllLanguage() {
        LinkedList<String> res = new LinkedList<>();
        for (String str : this.setting.getLanguages()) {
            res.add(str);
        }
        res.remove(this.setting.getLanguage());
        res.addFirst(this.setting.getLanguage());
        return res;
    }

    public boolean setLanguage(String string) {
        if (string == null) return false;
        String[] languages = this.setting.getLanguages();
        for (int i = 0; i < languages.length; i++) {
            if (string.equals(languages[i])) {
                return this.setting.setLanguage(i);
            }
        }
        return false;
    }

    private void print(String title, LinkedList<String> content, PrintWriter pw) {
        pw.println(title + " :");
        for (String str : content) {
            pw.println("\t" + str);
        }
    }

    public boolean derived() {
        LinkedList<Friend> friends = this.dataService.getAllFriend();
        File file = new File(this.setting.getDerivedPath());
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(file));
            for (Friend friend : friends) {
                HashMap<String, LinkedList<String>> attribute = this.friendToMap(friend);
                String[] keys = new String[]{"name","telephone","email","other","group"};
                for(String key : keys){
                    this.print(this.getText(key), attribute.get(key), pw);
                }
                pw.println();
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
