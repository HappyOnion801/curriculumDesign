package com.curriculumDesign.telephoneBook.service.baseService;

import com.curriculumDesign.telephoneBook.model.Setter;

import java.io.*;
import java.util.ResourceBundle;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-13
 * @ Github: HappyOnion801
 */
public class SetService {

    private static final String setPath = "D:" + File.separator + "curriculumDesign" + File.separator + "Telephone" + File.separator + "setPath.set";
    private static final String dataPath = "D:" + File.separator + "curriculumDesign" + File.separator + "Telephone" + File.separator + "friend.data";
    private static final String derivedPath = "D:" + File.separator + "curriculumDesign" + File.separator + "Telephone" + File.separator + "allFriend.txt";
    private static final String iconPath = "D:" + File.separator + "curriculumDesign" + File.separator + "Telephone" + File.separator + "icon.png";

    private static SetService instance;

    private String[] languages = {"zh_CN", "en_US"};
    private ResourceBundle resourceBundle;
    private Setter setter;

    private SetService() {
        File file = new File(setPath);
        if (file.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                this.setter = (Setter) ois.readObject();
                ois.close();
            } catch (Exception e) {
                System.out.println("读取设置时发生错误！");
                this.setter = new Setter();
                e.printStackTrace();
            }
        } else {
            file.getParentFile().mkdirs();
            this.setter = new Setter();
        }
        this.resourceBundle = ResourceBundle.getBundle(languages[setter.getLanguage()]);
    }

    public static SetService getInstance() {
        if (instance == null) instance = new SetService();
        return instance;
    }

    public String getDataPath() {
        return dataPath;
    }

    public String getDerivedPath() {
        return derivedPath;
    }

    public boolean save() {
        File set = new File(setPath);
        if (!set.getParentFile().exists()) set.getParentFile().mkdirs();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(set));
            oos.writeObject(setter);
            oos.close();
            return true;
        } catch (Exception e) {
            System.out.println("保存设置时发生错误！");
            e.printStackTrace();
            return false;
        }
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public String[] getLanguages() {
        return languages;
    }

    public String getIconPath() {
        return iconPath;
    }

    public String getLanguage() {
        return languages[this.setter.getLanguage()];
    }

    public boolean setLanguage(int foot) {
        if (foot > languages.length) return false;
        else setter.setLanguage(foot);
        this.save();
        return true;
    }
}
