package com.maixiaoyang.animerecorder.UI;

import com.maixiaoyang.animerecorder.DAO.Dao;
import com.maixiaoyang.animerecorder.DAO.model.TbAnimationInfo;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 小肥羊追番神器番剧条目面板
 * @author maixiaoyang
 */
public class EntryPanel extends JPanel implements ActionListener, DocumentListener {

    private static int WIDTH = 750;
    private static int HEIGHT = 40;

    private JTextField animeID;
    private JTextField animeName;
    private MyButton subButton;
    private JTextField animeAmount;
    private JButton plusButton;
    private JTextField watchedDate;
    private int id;

    public EntryPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(0, 0, 0, 0));
        setBounds(10, 10, WIDTH, HEIGHT);
        Font f = new Font("楷体", Font.BOLD, 19);

        animeID = new JTextField(5);
        animeID.setHorizontalAlignment(JTextField.CENTER);
        animeID.setEditable(false);
        animeID.setFont(f);
        add(animeID);

        animeName = new JTextField(23);
        animeName.setHorizontalAlignment(JTextField.CENTER);
        animeName.getDocument().addDocumentListener(this);
        animeName.setFont(f);
        add(animeName);

        animeAmount = new JTextField("0", 3);
        animeAmount.setHorizontalAlignment(JTextField.CENTER);
        animeAmount.setFont(f);
        animeAmount.setEditable(false);
        add(animeAmount);

        watchedDate = new JTextField(10);
        watchedDate.setHorizontalAlignment(JTextField.CENTER);
        watchedDate.setFont(f);
        watchedDate.setEditable(false);
        add(watchedDate);

        subButton = new MyButton("-", false);
        subButton.setForeground(Color.BLACK);
        subButton.setFont(f);
        subButton.setActionCommand("subButton");
        subButton.addActionListener(this);
        add(subButton);

        plusButton = new MyButton("+", false);
        plusButton.setForeground(Color.BLACK);
        plusButton.setFont(f);
        plusButton.setActionCommand("plusButton");
        plusButton.addActionListener(this);
        add(plusButton);
    }

    public EntryPanel(int x, int y) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        //setBackground(new Color(0, 0, 0, 0));
        setBackground(Color.RED);
        setBounds(x, y, WIDTH, HEIGHT);
        Font f = new Font("楷体", Font.BOLD, 19);

        animeID = new JTextField(5);
        animeID.setHorizontalAlignment(JTextField.CENTER);
        animeID.setEditable(false);
        animeID.setFont(f);
        add(animeID);

        animeName = new JTextField(23);
        animeName.setHorizontalAlignment(JTextField.CENTER);
        animeName.getDocument().addDocumentListener(this);
        animeName.setFont(f);
        add(animeName);

        animeAmount = new JTextField("0", 3);
        animeAmount.setHorizontalAlignment(JTextField.CENTER);
        animeAmount.setFont(f);
        animeAmount.setEditable(false);
        add(animeAmount);

        watchedDate = new JTextField(10);
        watchedDate.setHorizontalAlignment(JTextField.CENTER);
        watchedDate.setFont(f);
        watchedDate.setEditable(false);
        add(watchedDate);

        subButton = new MyButton("-", false);
        subButton.setForeground(Color.BLACK);
        subButton.setFont(f);
        subButton.setActionCommand("subButton");
        subButton.addActionListener(this);
        add(subButton);

        plusButton = new MyButton("+", false);
        plusButton.setForeground(Color.BLACK);
        plusButton.setFont(f);
        plusButton.setActionCommand("plusButton");
        plusButton.addActionListener(this);
        add(plusButton);
    }

    public EntryPanel(int id, String date, int x, int y) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        //setBackground(new Color(0, 0, 0, 0));
        setBackground(Color.RED);
        setBounds(x, y, WIDTH, HEIGHT);
        Font f = new Font("楷体", Font.BOLD, 19);

        this.id = id;

        animeID = new JTextField(String.valueOf(id), 5);
        animeID.setHorizontalAlignment(JTextField.CENTER);
        animeID.setEditable(false);
        animeID.setFont(f);
        add(animeID);

        animeName = new JTextField(23);
        animeName.setHorizontalAlignment(JTextField.CENTER);
        animeName.getDocument().addDocumentListener(this);
        animeName.setFont(f);
        add(animeName);

        animeAmount = new JTextField("0", 3);
        animeAmount.setHorizontalAlignment(JTextField.CENTER);
        animeAmount.setFont(f);
        animeAmount.setEditable(false);
        add(animeAmount);

        watchedDate = new JTextField(date, 10);
        watchedDate.setHorizontalAlignment(JTextField.CENTER);
        watchedDate.setFont(f);
        watchedDate.setEditable(false);
        add(watchedDate);

        subButton = new MyButton("-", false);
        subButton.setForeground(Color.BLACK);
        subButton.setFont(f);
        subButton.setActionCommand("subButton");
        subButton.addActionListener(this);
        add(subButton);

        plusButton = new MyButton("+", false);
        plusButton.setForeground(Color.BLACK);
        plusButton.setFont(f);
        plusButton.setActionCommand("plusButton");
        plusButton.addActionListener(this);
        add(plusButton);
    }

    public EntryPanel(int id, int x, int y) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(0, 0, 0, 0));
        //setBackground(Color.RED);
        setBounds(x, y, WIDTH, HEIGHT);
        Font f = new Font("楷体", Font.BOLD, 19);

        this.id = id;

        animeID = new JTextField(String.valueOf(id), 5);
        animeID.setHorizontalAlignment(JTextField.CENTER);
        animeID.setEditable(false);
        animeID.setFont(f);
        add(animeID);

        animeName = new JTextField(23);
        animeName.setHorizontalAlignment(JTextField.CENTER);
        animeName.getDocument().addDocumentListener(this);
        animeName.setFont(f);
        add(animeName);

        animeAmount = new JTextField("0", 3);
        animeAmount.setHorizontalAlignment(JTextField.CENTER);
        animeAmount.setFont(f);
        animeAmount.setEditable(false);
        add(animeAmount);

        watchedDate = new JTextField(10);
        watchedDate.setHorizontalAlignment(JTextField.CENTER);
        watchedDate.setFont(f);
        watchedDate.setEditable(false);
        add(watchedDate);

        subButton = new MyButton("-", false);
        subButton.setForeground(Color.BLACK);
        subButton.setFont(f);
        subButton.setActionCommand("subButton");
        subButton.addActionListener(this);
        add(subButton);

        plusButton = new MyButton("+", false);
        plusButton.setForeground(Color.BLACK);
        plusButton.setFont(f);
        plusButton.setActionCommand("plusButton");
        plusButton.addActionListener(this);
        add(plusButton);
    }

    public EntryPanel(TbAnimationInfo animationInfo) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(0, 0, 0, 0));
        //setBackground(Color.RED);

        id = Integer.parseInt(animationInfo.getId());
        String name = animationInfo.getName();
        String amount = animationInfo.getAmount();
        String date = animationInfo.getDate();

        setBounds(10, (id - 1) * 50 + 10, WIDTH, HEIGHT);
        Font f = new Font("楷体", Font.BOLD, 19);

        animeID = new JTextField(String.valueOf(id), 5);
        animeID.setHorizontalAlignment(JTextField.CENTER);
        animeID.setEditable(false);
        animeID.setFont(f);
        add(animeID);

        animeName = new JTextField(name, 23);
        animeName.setHorizontalAlignment(JTextField.CENTER);
        animeName.setEditable(true);
        animeName.getDocument().addDocumentListener(this);
        animeName.setFont(f);
        add(animeName);

        animeAmount = new JTextField(amount, 3);
        animeAmount.setHorizontalAlignment(JTextField.CENTER);
        animeAmount.setFont(f);
        animeAmount.setEditable(false);
        add(animeAmount);

        watchedDate = new JTextField(date, 10);
        watchedDate.setHorizontalAlignment(JTextField.CENTER);
        watchedDate.setFont(f);
        watchedDate.setEditable(false);
        add(watchedDate);

        subButton = new MyButton("-", false);
        subButton.setForeground(Color.BLACK);
        subButton.setFont(f);
        subButton.setActionCommand("subButton");
        subButton.addActionListener(this);
        add(subButton);

        plusButton = new MyButton("+", false);
        plusButton.setForeground(Color.BLACK);
        plusButton.setFont(f);
        plusButton.setActionCommand("plusButton");
        plusButton.addActionListener(this);
        add(plusButton);
    }

    public EntryPanel(TbAnimationInfo animationInfo, boolean b) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(0, 0, 0, 0));

        id = Integer.parseInt(animationInfo.getId());
        String name = animationInfo.getName();
        String amount = animationInfo.getAmount();
        String date = animationInfo.getDate();

        setBounds(10, (id - 1) * 50 + 10, WIDTH, HEIGHT);
        Font f = new Font("楷体", Font.BOLD, 19);

        animeID = new JTextField(String.valueOf(id), 5);
        animeID.setHorizontalAlignment(JTextField.CENTER);
        animeID.setEditable(false);
        animeID.setFont(f);
        add(animeID);

        animeName = new JTextField(name, 23);
        animeName.setHorizontalAlignment(JTextField.CENTER);
        animeName.setEditable(false);
        animeName.setFont(f);
        add(animeName);

        animeAmount = new JTextField(amount, 3);
        animeAmount.setHorizontalAlignment(JTextField.CENTER);
        animeAmount.setFont(f);
        animeAmount.setEditable(false);
        add(animeAmount);

        watchedDate = new JTextField(date, 10);
        watchedDate.setHorizontalAlignment(JTextField.CENTER);
        watchedDate.setFont(f);
        watchedDate.setEditable(false);
        add(watchedDate);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if ("subButton".equals(e.getActionCommand())) {
            animeAmount.setText(String.valueOf(Integer.parseInt(animeAmount.getText()) - 1));
            Dao.updateAnimationInfo("number", animeAmount.getText(), id);
        } else if ("plusButton".equals(e.getActionCommand())) {
            animeAmount.setText(String.valueOf(Integer.parseInt(animeAmount.getText()) + 1));
            Dao.updateAnimationInfo("number", animeAmount.getText(), id);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        //TODO 输入事件
        String animationName = animeName.getText();
        Dao.updateAnimationInfo("name", animationName, id);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        //TODO 删除事件
        String animationName = animeName.getText();
        Dao.updateAnimationInfo("name", animationName, id);
    }

}
