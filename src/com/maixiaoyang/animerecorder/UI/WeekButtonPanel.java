package com.maixiaoyang.animerecorder.UI;

import com.maixiaoyang.animerecorder.DAO.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 小肥羊追番神器星期选择按钮面板
 * @author maixiaoyang
 */
public class WeekButtonPanel extends JPanel implements ActionListener {

    private static MyButton mondayButton;
    private static MyButton tuesdayButton;
    private static MyButton wednesdayButton;
    private static MyButton thursdayButton;
    private static MyButton fridayButton;
    private static MyButton saturdayButton;
    private static MyButton sundayButton;
    private static MyButton otherButton;
    private static MyButton watchedButton;
    private final static int MONDAY    = 0;
    private final static int TUESDAY   = 1;
    private final static int WEDNESDAY = 2;
    private final static int THURSDAY  = 3;
    private final static int FRIDAY    = 4;
    private final static int SATURDAY  = 5;
    private final static int SUNDAY    = 6;
    private final static int OTHER     = 7;
    public static int weekDaySelected;

    public WeekButtonPanel() {
        setOpaque(false); //设置面板透明度为不透明
        //setLayout(new FlowLayout(0)); //设置布局管理器为流布局管理器
        setLayout(new GridLayout(1, 8));
        setSize(100, 100);
        setBounds(310, 30, 780, 60);

        Font f = new Font("宋体", Font.BOLD, 20);

        mondayButton = new MyButton("周一");
        mondayButton.setFont(f);
        mondayButton.setActionCommand("mondayButton");
        mondayButton.addActionListener(this);
        add(mondayButton);

        tuesdayButton = new MyButton("周二");
        tuesdayButton.setFont(f);
        tuesdayButton.setActionCommand("tuesdayButton");
        tuesdayButton.addActionListener(this);
        add(tuesdayButton);

        wednesdayButton = new MyButton("周三");
        wednesdayButton.setFont(f);
        wednesdayButton.setActionCommand("wednesdayButton");
        wednesdayButton.addActionListener(this);
        add(wednesdayButton);

        thursdayButton = new MyButton("周四");
        thursdayButton.setFont(f);
        thursdayButton.setActionCommand("thursdayButton");
        thursdayButton.addActionListener(this);
        add(thursdayButton);

        fridayButton = new MyButton("周五");
        fridayButton.setFont(f);
        fridayButton.setActionCommand("fridayButton");
        fridayButton.addActionListener(this);
        add(fridayButton);

        saturdayButton = new MyButton("周六");
        saturdayButton.setFont(f);
        saturdayButton.setActionCommand("saturdayButton");
        saturdayButton.addActionListener(this);
        add(saturdayButton);

        sundayButton = new MyButton("周日");
        sundayButton.setFont(f);
        sundayButton.setActionCommand("sundayButton");
        sundayButton.addActionListener(this);
        add(sundayButton);

        otherButton = new MyButton("其他");
        otherButton.setFont(f);
        otherButton.setActionCommand("otherButton");
        otherButton.addActionListener(this);
        add(otherButton);

        watchedButton = new MyButton("已看完");
        watchedButton.setFont(new Font("宋体", Font.BOLD, 15));
        watchedButton.setActionCommand("watchedButton");
        watchedButton.addActionListener(this);
        add(watchedButton);
    }

    public static void getFocus(int weekDay) {
        switch (weekDay) {
            case MONDAY: mondayButton.requestFocus(); weekDaySelected = MONDAY; break;
            case TUESDAY: tuesdayButton.requestFocus(); weekDaySelected = TUESDAY; break;
            case WEDNESDAY: wednesdayButton.requestFocus(); weekDaySelected = WEDNESDAY; break;
            case THURSDAY: thursdayButton.requestFocus(); weekDaySelected = THURSDAY; break;
            case FRIDAY: fridayButton.requestFocus(); weekDaySelected = FRIDAY; break;
            case SATURDAY: saturdayButton.requestFocus(); weekDaySelected = SATURDAY; break;
            case SUNDAY: sundayButton.requestFocus(); weekDaySelected = SUNDAY; break;
            default:break;
        }
    }

    public void actionPerformed(ActionEvent e) {
        ContentPanel.clear();
        if (e.getActionCommand().equals("mondayButton")) {
            Dao.loadAnimation(MONDAY);
        } else if (e.getActionCommand().equals("tuesdayButton")) {
            Dao.loadAnimation(TUESDAY);
        } else if (e.getActionCommand().equals("wednesdayButton")) {
            Dao.loadAnimation(WEDNESDAY);
        } else if (e.getActionCommand().equals("thursdayButton")) {
            Dao.loadAnimation(THURSDAY);
        } else if (e.getActionCommand().equals("fridayButton")) {
            Dao.loadAnimation(FRIDAY);
        } else if (e.getActionCommand().equals("saturdayButton")) {
            Dao.loadAnimation(SATURDAY);
        } else if (e.getActionCommand().equals("sundayButton")) {
            Dao.loadAnimation(SUNDAY);
        } else if (e.getActionCommand().equals("otherButton")) {
            Dao.loadAnimation(OTHER);
        }else if (e.getActionCommand().equals("watchedButton")) {
            Dao.loadAnimationForWatched();
        }
    }
}

