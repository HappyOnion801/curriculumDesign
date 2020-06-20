package com.curriculumDesign.drawing.shape;

import java.awt.*;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-18
 * @ Github: HappyOnion801
 */
public class Line implements Shape {
    private int x1, y1;
    private int x2, y2;
    private Color lineColor;

    public Line(int x1, int y1, int x2, int y2, Color lineColor) {
        super();
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.lineColor = lineColor;
    }

    public Line() {
        super();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2d.setColor(lineColor);
        g2d.drawLine(x1, y1, x2, y2);
    }
}