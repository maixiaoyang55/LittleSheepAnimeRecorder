package com.maixiaoyang.animerecorder.ui;

import com.maixiaoyang.animerecorder.dao.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

/**
 * 小肥羊追番神器添加项目提示框
 * @author maixiaoyang
 */
public class MyDialog extends JDialog implements ActionListener {

    private JTextField animeName;

    private final static String COMMAND_COMFIRM_ADD_BUTTON = "comfirmAddButton";
    private final static String COMMAND_COMFIRM_DELETE_BUTTON = "comfirmDeleteButton";
    private final static String COMMAND_COMFIRM_COMPLETE_BUTTON = "comfirmCompleteButton";
    private final static String COMMAND_CANCEL_BUTTON = "cancelButton";
    private final static int MAX_ANIMATIONSIZE = 10;
    public final static int TYPE_ONE   = 1;
    public final static int TYPE_TWO   = 2;
    public final static int TYPE_THREE = 3;

    public MyDialog(JFrame frame, int type) {
        super(frame);
        setLayout(null);
        setBounds(740, 400, 380, 150);

        if (type == TYPE_ONE) {
            JLabel labelAnimeName = new JLabel("动漫名称：");
            labelAnimeName.setFont(EntryPanel.ANIMENAME_FONT);
            labelAnimeName.setBounds(10, 10, 150, 30);
            add(labelAnimeName);

            animeName = new JTextField(23);
            animeName.setHorizontalAlignment(JTextField.CENTER);
            animeName.setFont(EntryPanel.ANIMENAME_FONT);
            animeName.setBounds(120, 10, 220, 30);
            add(animeName);

            addConfirmButton(COMMAND_COMFIRM_ADD_BUTTON);
            addCancelButton();
        } else if (type == TYPE_TWO) {
            addTipLabel("确定要删除吗？");
            addConfirmButton(COMMAND_COMFIRM_DELETE_BUTTON);
            addCancelButton();
        } else if (type == TYPE_THREE) {
            addTipLabel("确定看完了吗？");
            addConfirmButton(COMMAND_COMFIRM_COMPLETE_BUTTON);
            addCancelButton();
        }
    }

    private void addTipLabel(String text) {
        JLabel labelAnimeName = new JLabel(text);
        labelAnimeName.setFont(EntryPanel.ANIMENAME_FONT);
        labelAnimeName.setBounds(80, 10, 200, 30);
        add(labelAnimeName);
    }

    private void addConfirmButton(String actionCommand) {
        MyButton comfirmButton = new MyButton("确认");
        comfirmButton.setBounds(90, 70, 100, 30);
        comfirmButton.setFont(EntryPanel.GENERAL_FONT);
        comfirmButton.setForeground(Color.BLACK);
        comfirmButton.setActionCommand(actionCommand);
        comfirmButton.addActionListener(this);
        add(comfirmButton);
    }

    private void addCancelButton() {
        MyButton cancelButton = new MyButton("取消");
        cancelButton.setBounds(200, 70, 100, 30);
        cancelButton.setFont(EntryPanel.GENERAL_FONT);
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setActionCommand("cancelButton");
        cancelButton.addActionListener(this);
        add(cancelButton);
    }

    private void deleteEntry() {
        int animationInfoSize = ContentPanel.getAnimationInfoSize();
        int id = EntryPanel.getId(EntryPanel.instance);
        Dao.deleteAnimationInfo(id);
        ContentPanel.clear();
        animationInfoSize--;
        for (int i = id; i <= animationInfoSize; i++) {
            Dao.updateAnimationInfo("id", String.valueOf(i), i + 1);
        }

        Dao.loadAnimation(WeekButtonPanel.weekDaySelected);

        ContentPanel.setAnimationInfoSize(animationInfoSize);
        ContentPanel.getContentPanel().revalidate(); //使组件失效，并重新计算布局绘制组件
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (COMMAND_COMFIRM_ADD_BUTTON.equals(e.getActionCommand())) {

            int animationInfoSize = ContentPanel.getAnimationInfoSize();
            String animationName = animeName.getText();

            ContentPanel.getAddAnimeButton().setBounds(5, (animationInfoSize + 1) * 50 + 10, 100, 40);

            Date date = new Date();
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
            ContentPanel.getContentPanel().add(new EntryPanel(animationInfoSize + 1, animationName, dateFormat.format(date), 10, animationInfoSize * 50 + 10));
            Dao.addAnimationInfo(animationInfoSize, animationName, dateFormat.format(date));

            if (animationInfoSize > MAX_ANIMATIONSIZE) {
                ContentPanel.getContentPanel().setPreferredSize(new Dimension(775, 610 + (animationInfoSize - 10) * 50));
            }

            animationInfoSize++;
            ContentPanel.setAnimationInfoSize(animationInfoSize);
            ContentPanel.getContentPanel().revalidate(); //使组件失效，并重新计算布局绘制组件

            dispose();
        } else if (COMMAND_COMFIRM_DELETE_BUTTON.equals(e.getActionCommand())) {
            deleteEntry();
            dispose();
        } else if (COMMAND_COMFIRM_COMPLETE_BUTTON.equals(e.getActionCommand())) {
            deleteEntry();
            Dao.addAnimationForWatched(EntryPanel.getAnimeName(EntryPanel.instance), EntryPanel.getAnimeAmount(EntryPanel.instance), EntryPanel.getWatchedDate(EntryPanel.instance));
            dispose();
        } else if (COMMAND_CANCEL_BUTTON.equals(e.getActionCommand())) {
            dispose();
        }
    }

}
