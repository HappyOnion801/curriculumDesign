package com.curriculumDesign.fruitMarket.service;

import com.curriculumDesign.fruitMarket.dateBase.DataBase;
import com.curriculumDesign.fruitMarket.model.Fruit;

import java.io.*;
import java.util.ArrayList;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-17
 * @ Github: HappyOnion801
 */
public class DataService {

    private static String path = "D:\\curriculumDesign\\FruitMarket\\fruit.data";
    private static File file = new File(path);

    static {
        try {
            file = new File(path);
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                ArrayList<Fruit> data = (ArrayList<Fruit>) ois.readObject();
                DataBase.data = data;
                ois.close();
            } else {
                System.out.println("文件不存在，读档失败！");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(DataBase.data);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addFruit(Fruit newFruit) {
        Fruit f = find(newFruit.getNumber());
        if (f == null) {
            DataBase.data.add(newFruit);
            this.save();
            return true;
        } else {
            return false;
        }
    }

    public boolean delFruit(String number) {
        Fruit f = find(number);
        if (f != null) {
            DataBase.data.remove(f);
            this.save();
            return true;
        } else
            return false;
    }

    public boolean updateFruit(Fruit newFruit) {
        Fruit f = find(newFruit.getNumber());
        if (f != null) {
            f.setName(newFruit.getName());
            f.setPrice(newFruit.getPrice());
            f.setUnit(newFruit.getUnit());
            this.save();
            return true;
        } else
            return false;

    }

    public Fruit find(String number) {
        for (int i = 0; i < DataBase.data.size(); i++) {
            if (DataBase.data.get(i).getNumber().equals(number)) {
                return DataBase.data.get(i);
            }
        }
        return null;
    }

    public Fruit find2(String name) {
        for (int i = 0; i < DataBase.data.size(); i++) {
            if (DataBase.data.get(i).getName().equals(name)) {
                return DataBase.data.get(i);
            }

        }
        return null;
    }

    public boolean findFruit(String name) {
        Fruit f = find2(name);
        if (f != null) {
            System.out.println("编号：" + f.getNumber() + "," + "水果名称为：" + f.getName() + "," + "价格：" + f.getPrice() + "元,"
                    + "每" + f.getUnit());
            return true;
        } else
            return false;
    }

    public ArrayList<Fruit> getAll() {
        return DataBase.data;
    }
}
