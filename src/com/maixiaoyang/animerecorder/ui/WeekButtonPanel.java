package com.maixiaoyang.animerecorder.ui;

import com.maixiaoyang.animerecorder.dao.Dao;

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

    private final static String COMMAND_MONDAY_BUTTON = "mondayButton";
    private final static String COMMAND_TUESDAY_BUTTON = "tuesdayButton";
    private final static String COMMAND_WEDNESDAY_BUTTON = "wednesdayButton";
    private final static String COMMAND_THURSDAY_BUTTON = "thursdayButton";
    private final static String COMMAND_FRIDAY_BUTTON = "fridayButton";
    private final static String COMMAND_SATURDAY_BUTTON = "saturdayButton";
    private final static String COMMAND_SUNDAY_BUTTON = "sundayButton";
    private final static String COMMAND_OHTER_BUTTON = "otherButton";
    private final static String COMMAND_WATCHED_BUTTON = "watchedButton";

    public static int weekDaySelected;

    public WeekButtonPanel() {
        //设置面板透明度为不透明
        setOpaque(false);
        setLayout(new GridLayout(1, 8));
        setSize(100, 100);
        setBounds(310, 50, 780, 60);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        ContentPanel.clear();
        if (COMMAND_MONDAY_BUTTON.equals(e.getActionCommand())) {
            Dao.loadAnimation(MONDAY);
        } else if (COMMAND_TUESDAY_BUTTON.equals(e.getActionCommand())) {
            Dao.loadAnimation(TUESDAY);
        } else if (COMMAND_WEDNESDAY_BUTTON.equals(e.getActionCommand())) {
            Dao.loadAnimation(WEDNESDAY);
        } else if (COMMAND_THURSDAY_BUTTON.equals(e.getActionCommand())) {
            Dao.loadAnimation(THURSDAY);
        } else if (COMMAND_FRIDAY_BUTTON.equals(e.getActionCommand())) {
            Dao.loadAnimation(FRIDAY);
        } else if (COMMAND_SATURDAY_BUTTON.equals(e.getActionCommand())) {
            Dao.loadAnimation(SATURDAY);
        } else if (COMMAND_SUNDAY_BUTTON.equals(e.getActionCommand())) {
            Dao.loadAnimation(SUNDAY);
        } else if (COMMAND_OHTER_BUTTON.equals(e.getActionCommand())) {
            Dao.loadAnimation(OTHER);
        }else if (COMMAND_WATCHED_BUTTON.equals(e.getActionCommand())) {
            Dao.loadAnimationForWatched();
        }
    }
}

