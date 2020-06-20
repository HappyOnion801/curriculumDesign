package com.curriculumDesign.fruitMarket.model;

import java.io.Serializable;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-17
 * @ Github: HappyOnion801
 */
public class Fruit implements Serializable {
    private static final long serialVersionUID = 2302811115087866986L;
    private String number;
    private String name;
    private double price;
    private String unit;

    public Fruit(String number, String name, double price, String unit) {
        super();
        this.number = number;
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Fruit [number=" + number + ", name=" + name + ", price=" + price + ", unit=" + unit + "]";
    }

}
