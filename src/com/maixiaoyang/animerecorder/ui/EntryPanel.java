package com.maixiaoyang.animerecorder.ui;

import com.maixiaoyang.animerecorder.dao.Dao;
import com.maixiaoyang.animerecorder.dao.model.TbAnimationInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

/**
 * 小肥羊追番神器番剧条目面板
 * @author maixiaoyang
 */
public class EntryPanel extends JPanel implements ActionListener {

    private static int WIDTH = 750;
    private static int HEIGHT = 40;

    private JTextField animeID;
    private JTextField animeName;
    private MyButton subButton;
    private JTextField animeAmount;
    private MyButton plusButton;
    private JTextField watchedDate;
    private JLabel deleteIcon;
    private JLabel completeIcon;
    public final static Color BUTTON_FOREGROUND = Color.WHITE;
    public final static Font ANIMENAME_FONT = new Font("楷体", Font.BOLD, 22);
    public final static Font GENERAL_FONT = new Font("楷体", Font.BOLD, 19);
    private int id;

    private final static String COMMAND_SUB_BUTTON = "subButton";
    private final static String COMMAND_PLUS_BUTTON = "plusButton";

    private int mouseX, mouseY;
    private int panelX, panelY;

    public static EntryPanel instance;

    public EntryPanel(int id, String name, String date, int x, int y) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(0, 0, 0, 0));
        setBounds(x, y, WIDTH, HEIGHT);

        this.id = id;

        addAnimeIDText(String.valueOf(id));
        addAnimeNameText(name);
        addAnimeAmountText("0");
        addWatchedDateText(date);
        addSubButton();
        addPlusButton();
        addDeleteIcon();
    }

    public EntryPanel(TbAnimationInfo animationInfo) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(0, 0, 0, 0));

        //addLisntener();

        id = Integer.parseInt(animationInfo.getId());
        String name = animationInfo.getName();
        String amount = animationInfo.getAmount();
        String date = animationInfo.getDate();

        setBounds(10, (id - 1) * 50 + 10, WIDTH, HEIGHT);

        addAnimeIDText(String.valueOf(id));
        addAnimeNameText(name);
        addAnimeAmountText(amount);
        addWatchedDateText(date);
        addSubButton();
        addPlusButton();
        addDeleteIcon();
        addCompleteIcon();
    }

    public EntryPanel(TbAnimationInfo animationInfo, boolean b) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(0, 0, 0, 0));

        id = Integer.parseInt(animationInfo.getId());
        String name = animationInfo.getName();
        String amount = animationInfo.getAmount();
        String date = animationInfo.getDate();

        setBounds(10, (id - 1) * 50 + 10, WIDTH, HEIGHT);

        addAnimeIDText(String.valueOf(id));
        addAnimeNameText(name);
        addAnimeAmountText(amount);
        addWatchedDateText(date);
    }

    public static int getId(EntryPanel instance) {
        return instance.getId();
    }

    private int getId() {
        return id;
    }

    private String getAnimeName() {
        return animeName.getText();
    }

    public static String getAnimeName(EntryPanel instance) {
        return instance.getAnimeName();
    }

    private String getAnimeAmount() {
        return animeAmount.getText();
    }

    public static String getAnimeAmount(EntryPanel instance) {
        return instance.getAnimeAmount();
    }

    private String getWatchedDate() {
        return watchedDate.getText();
    }

    public static String getWatchedDate(EntryPanel instance) {
        return instance.getWatchedDate();
    }

    private EntryPanel getInstance() {
        return this;
    }

    private void addAnimeIDText(String id) {
        animeID = new JTextField(String.valueOf(id), 5);
        animeID.setHorizontalAlignment(JTextField.CENTER);
        animeID.setEditable(false);
        animeID.setFont(GENERAL_FONT);
        add(animeID);
    }

    private void addAnimeNameText(String name) {
        animeName = new JTextField(name, 23);
        animeName.setHorizontalAlignment(JTextField.CENTER);
        animeName.setEditable(false);
        animeName.setFont(GENERAL_FONT);
        add(animeName);
    }

    private void addAnimeAmountText(String amount) {
        animeAmount = new JTextField(amount, 3);
        animeAmount.setHorizontalAlignment(JTextField.CENTER);
        animeAmount.setFont(GENERAL_FONT);
        animeAmount.setEditable(false);
        add(animeAmount);
    }

    private void addWatchedDateText(String date) {
        watchedDate = new JTextField(date, 10);
        watchedDate.setHorizontalAlignment(JTextField.CENTER);
        watchedDate.setFont(GENERAL_FONT);
        watchedDate.setEditable(false);
        add(watchedDate);
    }

    private void addSubButton() {
        subButton = new MyButton("-", false);
        subButton.setForeground(BUTTON_FOREGROUND);
        subButton.setFont(GENERAL_FONT);
        subButton.setActionCommand("subButton");
        subButton.addActionListener(this);
        add(subButton);
    }

    private void addPlusButton() {
        plusButton = new MyButton("+", false);
        plusButton.setForeground(BUTTON_FOREGROUND);
        plusButton.setFont(GENERAL_FONT);
        plusButton.setActionCommand("plusButton");
        plusButton.addActionListener(this);
        add(plusButton);
    }

    private void addDeleteIcon() {
        deleteIcon = new JLabel();
        URL imgUrl = MainUI.class.getResource("images/delete_button.png");
        ImageIcon icon = new ImageIcon(imgUrl);
        deleteIcon.setIcon(icon);
        deleteIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                instance = getInstance();
                new MyDialog(MainUI.getFrame(), MyDialog.TYPE_TWO).setVisible(true);
            }
        });
        add(deleteIcon);
    }

    private void addCompleteIcon() {
        completeIcon = new JLabel();
        URL imgUrl = MainUI.class.getResource("images/complete_button.png");
        ImageIcon icon = new ImageIcon(imgUrl);
        completeIcon.setIcon(icon);
        completeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                instance = getInstance();
                new MyDialog(MainUI.getFrame(), MyDialog.TYPE_THREE).setVisible(true);
            }
        });
        add(completeIcon);
    }

    private void addLisntener() {
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(panelX + (e.getXOnScreen() - mouseX), panelY + (e.getYOnScreen() - mouseY));
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getXOnScreen();
                mouseY = e.getYOnScreen();
                panelX = getX();
                panelY = getY();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (COMMAND_SUB_BUTTON.equals(e.getActionCommand())) {
            animeAmount.setText(String.valueOf(Integer.parseInt(animeAmount.getText()) - 1));
            Dao.updateAnimationInfo("number", animeAmount.getText(), id);
        } else if (COMMAND_PLUS_BUTTON.equals(e.getActionCommand())) {
            animeAmount.setText(String.valueOf(Integer.parseInt(animeAmount.getText()) + 1));
            Dao.updateAnimationInfo("number", animeAmount.getText(), id);
        }
    }

}
