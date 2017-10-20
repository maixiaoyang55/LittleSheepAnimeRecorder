package com.maixiaoyang.animerecorder.UI;

import com.maixiaoyang.animerecorder.DAO.Dao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;

import static com.maixiaoyang.animerecorder.DAO.Dao.loadAnimation;

/**
 * 小肥羊追番神器主要视图框架
 * @author maixiaoyang
 */
public class MainUI extends JFrame {

    private static final int WIDTH = 1100;
    private static final int HEIGHT = 750;

    private Image img;
    private int mouseX, mouseY;
    private int frameX, frameY;
    private int screenWidth, screenHeight;
    private ContentPanel contentPanel;
    private WeekButtonPanel weekButtonPanel;

    private void getScreenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
    }

    public MainUI() {
        Container c = getContentPane();
        setResizable(false); //禁止用户自由改变窗口大小
        setLayout(null); //设置布局为空，此时任意组件、面板可以随意大小、位置放置。

        /*************************设置初始位置为屏幕正中心*************************/
        getScreenSize();
        setLocation(screenWidth / 2 - WIDTH / 2, screenHeight / 2 - HEIGHT / 2);
        /***********************************************************************/

        setUndecorated(true); //禁止此Jframe的装饰，即将边框和标题栏隐藏
        setSize(WIDTH, HEIGHT); //设置窗口的大小
        setBackground(new Color(0, 0, 0, 100)); //设置窗口的背景
        addMouseMotionListener(new MouseMotionAdapter() { //设置鼠标运动监听器
            @Override
            public void mouseDragged(MouseEvent e) { //鼠标拖拽方法
                setLocation(frameX + (e.getXOnScreen() - mouseX), frameY + (e.getYOnScreen() - mouseY)); //设置窗口的当前位置
            }
        });
        addMouseListener(new MouseAdapter() { //设置鼠标监听器
            @Override
            public void mousePressed(MouseEvent e) { //鼠标按下方法
                mouseX = e.getXOnScreen(); //获取鼠标在屏幕上的x坐标
                mouseY = e.getYOnScreen(); //获取鼠标在屏幕上的y坐标
                frameX = getX(); //获取窗口的x坐标
                frameY = getY(); //获取窗口的y坐标

            }
        });

        weekButtonPanel = new WeekButtonPanel();
        c.add(weekButtonPanel);

        weekButtonPanel.setFocusable(true);
        weekButtonPanel.requestFocus();

        contentPanel = new ContentPanel();
        c.add(contentPanel.getPanel());
    }

    public static void main(String[] args) {
        MainUI mainUI = new MainUI();
        mainUI.init();
        mainUI.setVisible(true);
        WeekButtonPanel.getFocus(Dao.dayOfWeek());
    }

    public void init() {
        addCloseButton();
        addMiniButton();
        loadAnimation();
    }

    public void addMiniButton(){
        JButton miniButton = new JButton();
        miniButton.setBounds(1000, 13, 30, 30);
        miniButton.setBorderPainted(false);
        miniButton.setFocusPainted(false);
        miniButton.setContentAreaFilled(false);
        URL imaUrl = MainUI.class.getResource("images/mini_button.png");
        ImageIcon icon = new ImageIcon(imaUrl);
        miniButton.addActionListener(e -> setExtendedState(JFrame.ICONIFIED));
        miniButton.setIcon(icon);
        add(miniButton);
    }

    public void addCloseButton() {
        JButton closeButton = new JButton();
        closeButton.setBounds(1050, 13, 30, 30);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        URL imaUrl = MainUI.class.getResource("images/close_button.png");
        ImageIcon icon = new ImageIcon(imaUrl);
        closeButton.addActionListener(e -> System.exit(0));
        closeButton.setIcon(icon);
        add(closeButton);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        URL imgUrl = MainUI.class.getResource("images/bg01.png");
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); //获取图片路径
        g2.drawImage(img, 0, 0, this); //绘制图片

        g2.setColor(Color.LIGHT_GRAY); //设置画笔颜色
        g2.setStroke(new BasicStroke(5.0f)); //设置画笔属性（宽度）
        g2.drawRoundRect(300, 5, 795, 740, 10, 10); //绘制圆角矩阵

        g2.dispose();
    }

}
