package com.curriculumDesign.drawing.shape;

import java.awt.*;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-18
 * @ Github: HappyOnion801
 */
public class Rectangle implements Shape {
    private int x, y;
    private int width, height;
    private Color rectangleColor;

    public Rectangle() {
        super();
    }

    public Rectangle(int x, int y, int width, int height, Color rectangleColor) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rectangleColor = rectangleColor;
    }

    public void paint(Graphics g) {
        g.setColor(rectangleColor);
        g.drawRect(x, y, width, height);
    }

}