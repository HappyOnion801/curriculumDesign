package com.curriculumDesign.drawing.ui;

import com.curriculumDesign.drawing.shape.*;
import com.curriculumDesign.drawing.shape.Rectangle;
import com.curriculumDesign.drawing.shape.Shape;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Enumeration;
import java.util.Stack;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-18
 * @ Github: HappyOnion801
 */
public class SketchpadPanel extends Canvas implements MouseListener, MouseMotionListener {

    private static final long serialVersionUID = -5229161042153132522L;

    private int beginX = 0, beginY = 0, currentX = 0, currentY = 0;
    private boolean isMousePressing = false;

    private final Stack<Shape> currentShapes = new Stack<>();
    private final Stack<Shape> deletedShapes = new Stack<>();

    private ShapeTypes type;
    private Color color;

    public SketchpadPanel() {
        addMouseListener(this); // 向当前类（事件源）添加一个鼠标事件监听器只有将监听器添加到事件源上，
        // 当事件被事件源抛出后，才可能会有监听器处理此事件。
        addMouseMotionListener(this); // 同上
    }

    public void undo() {
        if (currentShapes.size() > 0) {
            Shape shape = currentShapes.pop();
            deletedShapes.push(shape);
            repaint();
        }
    }

    public void redo() {
        if (deletedShapes.size() > 0) {
            Shape shape = deletedShapes.pop();
            currentShapes.push(shape);
            repaint();
        }
    }

    public void setShapeType(ShapeTypes type) {
        this.type = type;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void mousePressed(MouseEvent e) {
        beginX = e.getX();
        beginY = e.getY();
        isMousePressing = true;
    }

    public void mouseDragged(MouseEvent e) {
        currentX = e.getX();
        currentY = e.getY();
        this.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        currentX = e.getX();
        currentY = e.getY();
        isMousePressing = false;

        switch (type) {
                        case LINE:
                            Line line = new Line(beginX, beginY, currentX, currentY, color);
                            currentShapes.push(line);
                            break;

                        case CIRCLE:
                            int radius = (int) Math
                                    .sqrt((beginX - currentX) * (beginX - currentX) + (beginY - currentY) * (beginY - currentY));
                            Circle circle = new Circle(beginX - radius / 2, beginY - radius / 2, radius, color);
                            currentShapes.push(circle);
                            break;

                        case RECTANGLE:
                            Rectangle rectangle;
                            if (currentX < beginX) {
                                if (currentY < beginY) {
                                    // 左上方
                                    rectangle = new Rectangle(currentX, currentY, beginX - currentX, beginY - currentY, color);
                                } else { // 左下方
                                    rectangle = new Rectangle(currentX, beginY, beginX - currentX, currentY - beginY, color);
                                }
                } else {
                    if (currentY < beginY) {
                        // 右上方
                        rectangle = new Rectangle(beginX, currentY, currentX - beginX, beginY - currentY, color);
                    } else {
                        // 右下方
                        rectangle = new Rectangle(beginX, beginY, currentX - beginX, currentY - beginY, color);
                    }
                }
                currentShapes.push(rectangle);
                break;
        }
        repaint();
    }

    public void paint(Graphics g) {

        Dimension size = getSize();
        int width = size.width;
        int height = size.height;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        Shape shape;
        Enumeration<Shape> e = currentShapes.elements();

        while (e.hasMoreElements()) {
            shape = e.nextElement();
            shape.paint(g);
        }

        if (isMousePressing) {
            g.setColor(color);
            switch (type) {
                case LINE:
                    g.drawLine(beginX, beginY, currentX, currentY);
                    break;
                case RECTANGLE:
                    if (currentX < beginX) {
                        if (currentY < beginY) {
                            // 左上方
                            g.drawRect(currentX, currentY, beginX - currentX, beginY - currentY);
                        } else { // 左下方
                            g.drawRect(currentX, beginY, beginX - currentX, currentY - beginY);
                        }
                    } else {
                        if (currentY < beginY) {
                            // 右上方
                            g.drawRect(beginX, currentY, currentX - beginX, beginY - currentY);
                        } else {
                            // 右下方
                            g.drawRect(beginX, beginY, currentX - beginX, currentY - beginY);
                        }
                    }
                    break;
                case CIRCLE:
                    // 半径
                    int radius = (int) Math
                            .sqrt((beginX - currentX) * (beginX - currentX) + (beginY - currentY) * (beginY - currentY));
                    g.drawArc(beginX - radius / 2, beginY - radius / 2, radius, radius, 0, 360);
                    break;
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    @Override

    public void mouseEntered(MouseEvent e) {
    }

    @Override

    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}