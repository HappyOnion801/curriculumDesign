package com.curriculumDesign.telephoneBook.service;

import com.curriculumDesign.telephoneBook.model.Friend;
import com.curriculumDesign.telephoneBook.model.Inputable;
import com.curriculumDesign.telephoneBook.model.User;
import com.curriculumDesign.telephoneBook.service.baseService.DataService;
import com.curriculumDesign.telephoneBook.service.baseService.NetworkService;
import com.curriculumDesign.telephoneBook.service.baseService.SetService;

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

    private NetworkService networkService = NetworkService.getInstance();
    private DataService dataService = DataService.getInstance();
    private SetService setService = SetService.getInstance();

    private ResourceBundle rb = setService.getResourceBundle();
    private String nowPage;
    private Friend nowFriend;
    private boolean NetWork = true;

    public Friend getNowFriend() {
        return nowFriend;
    }

    public void setNowFriend(Friend nowFriend) {
        this.nowFriend = nowFriend;
    }

    public String getNowPage() {
        return nowPage;
    }

    public void setNowPage(String nowPage) {
        this.nowPage = nowPage;
    }

    private Service() {
    }

    public static Service getInstance() {
        return instance;
    }

    public String getText(String text) {
        if (this.rb == null) return text;
        return rb.getString(text);
    }

    public String getIconPath() {
        return this.setService.getIconPath();
    }

    public int login(User user) {
        return this.networkService.login(user);
    }

    public void logout() {
        if (this.NetWork) this.networkService.logout();
    }

    public boolean addFriend(Friend friend) {
        boolean res = true;
        if (this.NetWork) res = (networkService.insert(friend) == 1);
        if (res) res = this.dataService.addFriend(friend);
        return res;
    }

    public boolean delete(Friend friend) {
        boolean res = true;
        if (this.NetWork) res = (networkService.delete(friend) == 1);
        if (res) res = this.dataService.deleteFriend(friend);
        return res;
    }

    public boolean update(Friend friend) {
        boolean res = true;
        if (this.NetWork) res = (networkService.update(friend) == 1);
        if (res) res = this.dataService.update(friend);
        return res;
    }

    public LinkedList<Friend> getAllFriend() {
        return this.dataService.getAllFriend();
    }

    public boolean downloadData() {
        LinkedList<Friend> data = this.networkService.getFriends();
        if (data == null) return false;
        this.dataService.setData(data);
        return true;
    }

    public void NotNetwork() {
        this.NetWork = false;
    }

    public HashMap<String, LinkedList<Friend>> getFriendByGroup() {
        HashMap<String, LinkedList<Friend>> res = new HashMap<>();
        LinkedList<Friend> allFriend = this.dataService.getAllFriend();

        for (Friend friend : allFriend) {
            String group = friend.getGroup();
            if (!res.containsKey(group)) {
                res.put(group, new LinkedList<>());
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

    public List<Friend> searchTelephone(String num) {
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

    private int createId() {
        LinkedList<Friend> all = this.dataService.getAllFriend();
        boolean[] id = new boolean[100000];
        for (Friend friend : all) {
            id[friend.getId()] = true;
        }
        for (int i = 0; i < id.length; i++) {
            if (!id[i]) return i;
        }
        return -1;
    }

    public Friend newFriend(HashMap<String, LinkedList<String>> attribute) {
        if (attribute.get("telephone").size() == 0) {
            attribute.get("telephone").add("");
        }
        if (attribute.get("email").size() == 0) {
            attribute.get("email").add("");
        }
        if (attribute.get("other").size() == 0) {
            attribute.get("other").add("");
        }
        if (attribute.get("group").size() == 0) {
            attribute.get("group").add(this.getText("noGroup"));
        }
        return new Friend(
                attribute.get("name"),
                attribute.get("telephone"),
                attribute.get("email"),
                attribute.get("other"),
                attribute.get("group")
        );
    }


    public Friend createFriend(HashMap<String, LinkedList<String>> attribute) {
        int id = this.createId();
        Friend friend = this.newFriend(attribute);
        friend.setId(id);
        System.out.println(friend);
        return friend;
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
        LinkedList<String> res = new LinkedList<>(Arrays.asList(this.setService.getLanguages()));
        res.remove(this.setService.getLanguage());
        res.addFirst(this.setService.getLanguage());
        return res;
    }

    public boolean setLanguage(String string) {
        if (string == null) return false;
        String[] languages = this.setService.getLanguages();
        for (int i = 0; i < languages.length; i++) {
            if (string.equals(languages[i])) {
                return this.setService.setLanguage(i);
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
        File file = new File(this.setService.getDerivedPath());
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(file));
            for (Friend friend : friends) {
                HashMap<String, LinkedList<String>> attribute = this.friendToMap(friend);
                String[] keys = new String[]{"name", "telephone", "email", "other", "group"};
                for (String key : keys) {
                    this.print(this.getText(key), attribute.get(key), pw);
                }
                pw.println("==========================");
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
