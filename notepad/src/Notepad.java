
import java.awt.*;
import java.awt.GraphicsEnvironment;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.event.*;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-22
 * @ Github: HappyOnion801
 */
public class Notepad extends JFrame {

    public static void main(String[] args) {
        new Notepad();
    }

    private JMenu fileMenu;// 文件菜单的引用
    private JMenuItem menuOpen;
    private JMenuItem menuSave;
    private JMenuItem menuSaveAs;
    private JMenuItem menuClose;

    private JMenu editMenu;// 编辑菜单的引用
    private JMenuItem menuCut;
    private JMenuItem menuCopy;
    private JMenuItem menuPaste;

    private JMenu formatMenu;// 格式菜单的引用
    private JMenuItem formatMenu_Font;

    private JMenu aboutMenu;// 关于菜单的引用
    private JMenuItem menuAbout;

    private JMenuBar menuBar;// 存放菜单的工具条

    private JTextArea textArea;// 文本编辑区
    private JLabel stateBar;// 状态栏
    private JFileChooser fileChooser;// 文件选择器
    private JScrollPane panel;// 滚动面板
    private Container contentPane;// 内容面板
    private JPopupMenu popUpMenu;// 弹出菜单

    public Notepad() {
        super("记事本");
        setSize(1150, 600);

        setUpUIComponent();
        setUpEvenListener();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setUpUIComponent() {

        menuBar = new JMenuBar();

        fileMenu = new JMenu("文件");
        menuOpen = new JMenuItem("打开");
        menuSave = new JMenuItem("保存");
        menuSaveAs = new JMenuItem("另存为");
        menuClose = new JMenuItem("关闭");

        editMenu = new JMenu("编辑");
        menuCut = new JMenuItem("剪切");
        menuCopy = new JMenuItem("复制");
        menuPaste = new JMenuItem("粘贴");

        formatMenu = new JMenu("格式");
        formatMenu_Font = new JMenuItem("字体");

        aboutMenu = new JMenu("关于");
        menuAbout = new JMenuItem("关于JNotePad");

        fileMenu.add(menuOpen);
        fileMenu.add(menuSave);
        fileMenu.add(menuSaveAs);
        fileMenu.add(menuClose);

        editMenu.add(menuCut);
        editMenu.add(menuCopy);
        editMenu.add(menuPaste);

        formatMenu.add(formatMenu_Font);

        aboutMenu.add(menuAbout);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(aboutMenu);

        menuOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        menuClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        menuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        menuCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        menuPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));

        super.setJMenuBar(menuBar);

        // 文字编辑区域
        textArea = new JTextArea();
        textArea.setFont(new Font("宋体", Font.PLAIN, 16));
        textArea.setLineWrap(true);// 自动换行
        panel = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);// 需要时垂直滚动条出现，水平，从不出现；ALWAYS,总是出现

        contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);

        // 状态栏
        stateBar = new JLabel("未修改");
        stateBar.setHorizontalAlignment(SwingConstants.LEFT);
        stateBar.setBorder(BorderFactory.createEtchedBorder());
        contentPane.add(stateBar, BorderLayout.SOUTH);

        popUpMenu = editMenu.getPopupMenu();
        fileChooser = new JFileChooser();
    }

    private void setUpEvenListener() {

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeFile();
            }
        });
        menuOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        menuSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        menuSaveAs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveAsFile();
            }
        });
        menuClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeFile();
            }
        });
        menuCut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cut();
            }
        });
        menuCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Copy();
            }
        });
        menuPaste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paste();
            }
        });
        formatMenu_Font.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                font();
            }
        });
        menuAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 显示对话框
                JOptionPane.showOptionDialog(null,
                        "程序名称:\n    JNotePad \n" + "程序设计:\n      \n" + "简介:\n    一个简单的文字编辑器\n" + "    可作为验收Java的实现对象\n"
                                + "    欢迎网友下载研究交流\n\n" + " /",
                        "关于JNotePad", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            }
        });
        // 编辑区键盘事件
        textArea.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                processTextArea();
            }
        });
        // 编辑区鼠标事件
        textArea.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3)
                    popUpMenu.show(editMenu, e.getX(), e.getY());
            }

            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    popUpMenu.setVisible(false);
            }
        });
    }

    private void closeFile() {
        // TODO Auto-generated method stub
        // 是否已保存文件
        if (isCurrentFileSaved()) {
            // 释放窗口资源，而后关闭程序
            dispose();
        } else {
            int option = JOptionPane.showConfirmDialog(null, "文件已修改，是否保存？", "保存文件？", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE, null);
            switch (option) {
                case JOptionPane.YES_OPTION:
                    saveFile();
                    break;
                case JOptionPane.NO_OPTION:
                    dispose();
            }
        }
    }

    private boolean isCurrentFileSaved() {
        if (stateBar.getText().equals("已修改")) {
            return false;
        } else
            return true;
    }

    private void openFile() {
        // TODO Auto-generated method stub
        int option = fileChooser.showDialog(null, null);
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedReader buf = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                this.setTitle(fileChooser.getSelectedFile().toString());
                textArea.setText("");
                stateBar.setText("未修改");
                String lineSeparator = System.getProperty("line.separator");
                String text;
                while ((text = buf.readLine()) != null) {
                    textArea.append(text);
                    textArea.append(lineSeparator);
                }
                buf.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, e.toString(), "开启文件失败", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile() {
        // TODO Auto-generated method stub
        // 从标题栏取得文件名称
        File file = new File(getTitle());// 创建文件对象，目录；文件存在
        // 若指定的文件不存在
        if (!file.exists()) {
            // 执行另存为
            saveAsFile();
        } else {
            try {
                // 开启指定的文件
                BufferedWriter buf = new BufferedWriter(new FileWriter(file));
                // 将文字编辑区的文字写入文件
                buf.write(textArea.getText());
                buf.close();
                // 设定状态栏为未修改
                stateBar.setText("未修改");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.toString(), "写入文件失败", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveAsFile() {
        // TODO Auto-generated method stub
        // 显示文件对话框
        int option = fileChooser.showSaveDialog(null);
        // 如果确认选取文件
        if (option == JFileChooser.APPROVE_OPTION) {
            // 取得选择的文件
            File file = fileChooser.getSelectedFile();
            // 在标题栏上设定文件名称
            setTitle(file.toString());
            try {
                // 建立文件
                file.createNewFile();
                // 进行文件保存
                saveFile();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.toString(), "无法建立新文件", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cut() {
        // TODO Auto-generated method stub
        textArea.cut();
        stateBar.setText("已修改");
        popUpMenu.setVisible(false);
    }

    private void Copy() {
        // TODO Auto-generated method stub
        textArea.copy();
        popUpMenu.setVisible(false);
    }

    private void paste() {
        // TODO Auto-generated method stub
        textArea.paste();
        stateBar.setText("已修改");
        popUpMenu.setVisible(false);
    }

    private void font() {
        // TODO Auto-generated method stub
        final JDialog fontDialog = new JDialog(this, "字体设置", false);
        Container con = fontDialog.getContentPane();
        con.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel fontLabel = new JLabel("字体(F)：");
        fontLabel.setPreferredSize(new Dimension(100, 20));// 构造一个Dimension，并将其初始化为指定宽度和高度
        con.add(fontLabel);
        JLabel styleLabel = new JLabel("字形(Y)：");
        styleLabel.setPreferredSize(new Dimension(100, 20));
        con.add(styleLabel);
        JLabel sizeLabel = new JLabel("大小(S)：");
        sizeLabel.setPreferredSize(new Dimension(100, 20));
        con.add(sizeLabel);
        final JLabel sample = new JLabel("张选仲的记事本-ZXZ's Notepad");
        // sample.setHorizontalAlignment(SwingConstants.CENTER);
        final JTextField fontText = new JTextField(9);
        fontText.setPreferredSize(new Dimension(200, 20));
        final JTextField styleText = new JTextField(8);
        styleText.setPreferredSize(new Dimension(200, 20));
        final int style[] = {Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD + Font.ITALIC};
        final JTextField sizeText = new JTextField(5);
        sizeText.setPreferredSize(new Dimension(200, 20));
        JButton okButton = new JButton("确定");
        JButton cancel = new JButton("取消");
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fontDialog.dispose();
            }
        });
        Font currentFont = textArea.getFont(); // 获得当前文本内容的字体样式
        fontText.setText(currentFont.getFontName());
        fontText.selectAll();
        // styleText.setText(currentFont.getStyle());
        // styleText.selectAll();
        if (currentFont.getStyle() == Font.PLAIN)
            styleText.setText("常规");
        else if (currentFont.getStyle() == Font.BOLD)
            styleText.setText("粗体");
        else if (currentFont.getStyle() == Font.ITALIC)
            styleText.setText("斜体");
        else if (currentFont.getStyle() == (Font.BOLD + Font.ITALIC))
            styleText.setText("粗斜体");
        styleText.selectAll();
        String str = String.valueOf(currentFont.getSize());
        sizeText.setText(str);
     //   sizeText.selectAll();
        final JList fontList, styleList, sizeList; // 列表组件
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final String fontName[] = ge.getAvailableFontFamilyNames(); // 获得字体名字
        fontList = new JList(fontName);
        fontList.setFixedCellWidth(86); // 列表中每项的宽度
        fontList.setFixedCellHeight(20);
        fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 单选
        final String fontStyle[] = {"常规", "粗体", "斜体", "粗斜体"};
        styleList = new JList(fontStyle);
        styleList.setFixedCellWidth(86);
        styleList.setFixedCellHeight(20);
        styleList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        if (currentFont.getStyle() == Font.PLAIN)
            styleList.setSelectedIndex(0);
        else if (currentFont.getStyle() == Font.BOLD)
            styleList.setSelectedIndex(1);
        else if (currentFont.getStyle() == Font.ITALIC)
            styleList.setSelectedIndex(2);
        else if (currentFont.getStyle() == (Font.BOLD + Font.ITALIC))
            styleList.setSelectedIndex(3);
        final String fontSize[] = {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72"};
        sizeList = new JList(fontSize);
        sizeList.setFixedCellWidth(43);
        sizeList.setFixedCellHeight(20);
        sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fontList.addListSelectionListener(new ListSelectionListener() // 给字体列表添加监听器
        {
            public void valueChanged(ListSelectionEvent event) {
                fontText.setText(fontName[fontList.getSelectedIndex()]); // 将列表中选中的字体名称显示在文本框fontText中
                fontText.selectAll();
                Font sampleFont1 = new Font(fontText.getText(), style[styleList.getSelectedIndex()],
                        Integer.parseInt(sizeText.getText()));
                sample.setFont(sampleFont1);
            }
        });
        styleList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int s = style[styleList.getSelectedIndex()]; // styleList.getSelectedIndex(),获得选择项的索引号，对应style数组的下标。
                styleText.setText(fontStyle[s]);
                styleText.selectAll();
                Font sampleFont2 = new Font(fontText.getText(), style[styleList.getSelectedIndex()],
                        Integer.parseInt(sizeText.getText()));
                sample.setFont(sampleFont2);
            }
        });
        sizeList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                sizeText.setText(fontSize[sizeList.getSelectedIndex()]);
                // sizeText.requestFocus();
                sizeText.selectAll();
                Font sampleFont3 = new Font(fontText.getText(), style[styleList.getSelectedIndex()],
                        Integer.parseInt(sizeText.getText()));
                sample.setFont(sampleFont3);
            }
        });
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Font okFont = new Font(fontText.getText(), style[styleList.getSelectedIndex()],
                        Integer.parseInt(sizeText.getText()));
                textArea.setFont(okFont);
                fontDialog.dispose();
            }
        });
        JPanel samplePanel = new JPanel(); // 定义面板
        samplePanel.setBorder(BorderFactory.createTitledBorder("示例")); // 设置面板边框
        // samplePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        samplePanel.add(sample);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        panel2.add(fontText);
        panel2.add(styleText);
        panel2.add(sizeText);
        panel2.add(okButton);
        panel3.add(new JScrollPane(fontList));// JList不支持直接滚动，所以要让JList作为JScrollPane的视口视图
        panel3.add(new JScrollPane(styleList));
        panel3.add(new JScrollPane(sizeList));
        panel3.add(cancel);
        con.add(panel1);
        con.add(panel2);
        con.add(panel3);
        con.add(samplePanel);
        fontDialog.setSize(350, 340);
        fontDialog.setLocation(200, 200);
        fontDialog.setResizable(false);
        fontDialog.setVisible(true);

    }

    protected void processTextArea() {
        // TODO Auto-generated method stub
        stateBar.setText("已修改");
    }

}
