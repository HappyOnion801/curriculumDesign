package com.curriculumDesign.drawing.ui;

import com.curriculumDesign.drawing.shape.ShapeTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-18
 * @ Github: HappyOnion801
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = -7080053971741609904L; // 序列化，用于版本控制
    private final JPanel commandPanel = new JPanel();
    private final JPanel colorPanel = new JPanel();
    private final JPanel mainPanel = new JPanel();

    private final JButton redButton = new JButton("Red");
    private final JButton blueButton = new JButton("Blue");
    private final JButton greenButton = new JButton("Green");

    private final JButton lineButton = new JButton("直线");
    private final JButton circleButton = new JButton("圆");
    private final JButton rectangleButton = new JButton("矩形");

    private final JButton undoButton = new JButton("撤销");
    private final JButton redoButton = new JButton("恢复撤销");
    private final JButton exitButton = new JButton("退出");

    SketchpadPanel sketchPanel = new SketchpadPanel();

    private void initFrame() {

        commandPanel.setLayout(new FlowLayout());
        commandPanel.add(lineButton);
        commandPanel.add(circleButton);
        commandPanel.add(rectangleButton);
        commandPanel.add(undoButton);
        commandPanel.add(redoButton);
        commandPanel.add(exitButton);

        colorPanel.setLayout(new FlowLayout());
        colorPanel.add(redButton);
        colorPanel.add(blueButton);
        colorPanel.add(greenButton);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(commandPanel, BorderLayout.NORTH);
        mainPanel.add(colorPanel, BorderLayout.CENTER);

        getContentPane().add("South", mainPanel);
        getContentPane().add("Center", sketchPanel);

        // 初始化设置，颜色和命令
        lineButton.setForeground(Color.RED);
        sketchPanel.setColor(Color.BLACK);
        redButton.setForeground(Color.RED);
        sketchPanel.setShapeType(ShapeTypes.LINE);// 类型为直线
    }

    /* 命令按钮监听 */
    private void initListener() {

        redButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                redAction(e);
            }
        });

        blueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                blueAction(e);
            }
        });

        greenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                greenAction(e);
            }
        });
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                undoAction(e);
            }
        });

        redoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                redoAction(e);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitAction(e);
            }
        });

        lineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lineAction(e);
            }
        });

        circleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                circleAction(e);
            }
        });

        rectangleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rectangleAction(e);
            }
        });
    }
    // 构造方法初始化，界面

    public MainFrame() {
        initFrame();
        initListener();
        this.setSize(500, 600);// 窗口大小
        setLocationByPlatform(true);// 再次打开时，显示窗口原来关闭的位置
        setResizable(true);// 是否可调整大小
    }

    /* 处理事件 */
    private void undoAction(ActionEvent e) {
        sketchPanel.undo();
    }

    private void redoAction(ActionEvent e) {
        sketchPanel.redo();
    }

    private void exitAction(ActionEvent e) {
        System.exit(0);
    }

    private void lineAction(ActionEvent e) {
        lineButton.setForeground(Color.RED);
        circleButton.setForeground(Color.BLACK);
        rectangleButton.setForeground(Color.BLACK);
        sketchPanel.setShapeType(ShapeTypes.LINE);
    }

    private void circleAction(ActionEvent e) {
        circleButton.setForeground(Color.RED);
        lineButton.setForeground(Color.BLACK);
        rectangleButton.setForeground(Color.BLACK);
        sketchPanel.setShapeType(ShapeTypes.CIRCLE);
    }

    private void rectangleAction(ActionEvent e) {
        rectangleButton.setForeground(Color.RED);
        lineButton.setForeground(Color.BLACK);
        circleButton.setForeground(Color.BLACK);
        sketchPanel.setShapeType(ShapeTypes.RECTANGLE);
    }

    private void redAction(ActionEvent e) {
        redButton.setForeground(Color.RED);
        blueButton.setForeground(Color.BLACK);
        greenButton.setForeground(Color.BLACK);
        sketchPanel.setColor(Color.RED);
    }

    private void blueAction(ActionEvent e) {
        blueButton.setForeground(Color.RED);
        redButton.setForeground(Color.BLACK);
        greenButton.setForeground(Color.BLACK);
        sketchPanel.setColor(Color.BLUE);
    }

    private void greenAction(ActionEvent e) {
        greenButton.setForeground(Color.RED);
        blueButton.setForeground(Color.BLACK);
        redButton.setForeground(Color.BLACK);
        sketchPanel.setColor(Color.GREEN);
    }

}