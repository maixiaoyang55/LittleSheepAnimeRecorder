package com.maixiaoyang.animerecorder.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * 小肥羊追番神器自定义按钮样式
 * @author maixiaoyang
 */
public class MyButton extends JButton {

    private boolean hover;

    public MyButton(String text) {
        super(text);
        //禁止绘制边框
        setBorderPainted(false);
        //禁止绘制焦点方框
        setFocusPainted(false);
        //设置透明
        setContentAreaFilled(false);

        setForeground(Color.WHITE);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                hover = false;
                repaint();
            }
        });
    }

    public MyButton(String text, boolean b) {
        super(text);
        if (b == false) {
            //禁止绘制边框
            setBorderPainted(false);
            //禁止绘制焦点方框
            setFocusPainted(false);
            //设置透明
            setContentAreaFilled(false);

            setForeground(Color.WHITE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        int h = getHeight();
        int w = getWidth();
        float tran = 1f;
        if (!hover) {
            tran = 0.6f;
        }
        //使用防锯齿改善显示质量
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //用来控制渐变的类
        GradientPaint p;
        //判断按钮是否被按下
        if (getModel().isPressed()) {
            p = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1, new Color(100, 100, 100));
        } else {
            p = new GradientPaint(0, 1, Color.blue, 0, h - 3, Color.PINK);
        }
        //实现文字的透明变化
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, tran));
        //获取当前剪贴区域
        Shape clip = g2.getClip();
        if (hover) {
            //Float 类定义一个所有圆角都使用 float 坐标指定的矩形
            RoundRectangle2D.Float r2d2 = new RoundRectangle2D.Float(0, 0, w, h, h, h);
            //将当前 Clip 与指定 Shape 的内部区域相交，并将 Clip 设置为所得的交集
            g2.clip(r2d2);
            GradientPaint gp2 = new GradientPaint(0, 0, Color.ORANGE, 0, h, Color.pink, true);
            g2.setPaint(gp2);
            //用当前颜色填充指定的矩形
            g2.fillRect(0, 0, w, h);
        }
        g2.setClip(clip);
        g2.setPaint(p);
        g2.drawRoundRect(0, 0, w - 1, h - 1, h, h);
        super.paintComponent(g);
        g2.dispose();
    }

}
