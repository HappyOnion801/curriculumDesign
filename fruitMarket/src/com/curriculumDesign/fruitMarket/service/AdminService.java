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
public class AdminService {



    // 水果列表
    public ArrayList<Fruit> getAll() {
        DataService dao = new DataService();
        return dao.getAll();
    }

    // 增加一个水果
    public boolean addFruit(String number, String name, String price, String unit) {
        Fruit newFruit = new Fruit(number, name, Double.parseDouble(price), unit);
        DataService dao = new DataService();
        return dao.addFruit(newFruit);
    }

    // 删除一个水果
    public boolean delFruit(String number) {
        DataService dao = new DataService();
        return dao.delFruit(number);

    }

    // 修改水果信息
    public boolean updateFruit(String number, String name, String price, String unit) {
        DataService dao = new DataService();
        return dao.updateFruit(new Fruit(number, name, Double.parseDouble(price), unit));
    }

    // 查询一个水果信息
    public boolean findFruit(String name) {
        DataService dao = new DataService();
        return dao.findFruit(name);
    }
}
