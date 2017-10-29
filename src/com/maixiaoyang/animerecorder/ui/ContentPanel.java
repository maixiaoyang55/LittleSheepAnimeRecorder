package com.maixiaoyang.animerecorder.ui;

import com.maixiaoyang.animerecorder.dao.model.TbAnimationInfo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 小肥羊追番神器内容面板
 * @author maixiaoyang
 */
public class ContentPanel extends JPanel {

    private static JScrollPane scrollPane  = null;
    private static MyButton addAnimeButton;
    private static ContentPanel instance;
    private static int animationInfoSize = 0;
    private final static int MAX_ENTRY_NUM = 12;


    public ContentPanel() {
        instance = this;
        setLayout(null);
        setOpaque(false);
        /*
        * 当ContentPanel添加组件至超出范围时，使scrollPane有效，必须使用setPreferredSize()，而是不是setSize()，并且
        * 内容面板大于滚动面板时就会出现滚动条（当scrollPane设置成需要时出现滚动条）。
        * 因此，当添加组件超出范围时，需要更新ContentPanel的大小来适应滚动面板。
        * 这里ContentPanel最多添加12个EntryPanel就会超出范围
        * */
        //初始height:610 + 50
        setPreferredSize(new Dimension(775,610));

        scrollPane = new JScrollPane(this);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBounds(310, 120, 775, 615);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public static void setAddAnimeButton() {
        addAnimeButton = new MyButton("+", false);
        addAnimeButton.setForeground(Color.WHITE);
        addAnimeButton.setBounds(10, animationInfoSize * 50 + 10, 100, 40);
        addAnimeButton.setFont(new Font("楷体", Font.BOLD, 25));
        addAnimeButton.addActionListener(e -> new MyDialog(MainFrame.getFrame(), MyDialog.TYPE_ONE).setVisible(true));
        ContentPanel.instance.add(addAnimeButton);
    }

    public static MyButton getAddAnimeButton() {
        return addAnimeButton;
    }

    public static int getAnimationInfoSize() {
        return animationInfoSize;
    }

    public static void setAnimationInfoSize(int animationInfoSize) {
        ContentPanel.animationInfoSize = animationInfoSize;
    }

    /**
     * 添加番剧条目
     * @param animationInfoList 番剧条目信息列表
     * */
    public void addEntry(List<TbAnimationInfo> animationInfoList) {
        TbAnimationInfo animationInfo = new TbAnimationInfo();
        animationInfoSize = animationInfoList.size();
        for (int i = 0; i < animationInfoList.size(); i++) {
            animationInfo = animationInfoList.get(i);
            EntryPanel entryPanel = new EntryPanel(animationInfo);
            add(entryPanel);
        }
        try {
            if (animationInfo != null && Integer.parseInt(animationInfo.getId()) > MAX_ENTRY_NUM) {
                setPreferredSize(new Dimension(775, 610 + (Integer.parseInt(animationInfo.getId()) - 12) * 50));
            } else {
                setPreferredSize(new Dimension(775,610));
            }
        } catch (Exception e) {

        }
    }

    public void addWatchedEntry(List<TbAnimationInfo> animationInfoList) {
        TbAnimationInfo animationInfo = new TbAnimationInfo();
        for (int i = 0; i < animationInfoList.size(); i++) {
            animationInfo = animationInfoList.get(i);
            EntryPanel entryPanel = new EntryPanel(animationInfo, true);
            add(entryPanel);
        }
        try {
            if (animationInfo != null && Integer.parseInt(animationInfo.getId()) > MAX_ENTRY_NUM) {
                setPreferredSize(new Dimension(775, 610 + (Integer.parseInt(animationInfo.getId()) - 12) * 50));
            } else {
                setPreferredSize(new Dimension(775,610));
            }
        } catch (Exception e) {

        }
    }

    public static JScrollPane getPanel() {
        return scrollPane ;
    }

    public static ContentPanel getContentPanel() {
        return ContentPanel.instance;
    }

    public static void clear() {
        ContentPanel.instance.removeAll();
        ContentPanel.instance.repaint();
    }

}
