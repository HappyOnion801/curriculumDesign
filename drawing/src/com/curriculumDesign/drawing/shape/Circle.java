package com.curriculumDesign.drawing.shape;

import java.awt.*;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-18
 * @ Github: HappyOnion801
 */
public class Circle implements Shape {
    private int x, y;
    private Color circleColor;
    private int radius;

    public Circle() {
        super();
    }

    public Circle(int x, int y, int radius, Color circleColor) {
        super();
        this.circleColor = circleColor;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(circleColor);
        g2d.drawArc(x, y, radius, radius, 0, 360);
    }

}