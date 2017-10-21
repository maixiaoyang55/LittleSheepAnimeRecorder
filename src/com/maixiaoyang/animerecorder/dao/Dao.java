package com.maixiaoyang.animerecorder.dao;

import com.maixiaoyang.animerecorder.conndb.ConnDB;
import com.maixiaoyang.animerecorder.dao.model.TbAnimationInfo;
import com.maixiaoyang.animerecorder.ui.ContentPanel;
import com.maixiaoyang.animerecorder.ui.EntryPanel;
import com.maixiaoyang.animerecorder.ui.WeekButtonPanel;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * 小肥羊追番神器数据访问对象
 * @author maixiaoyang
 */
public class Dao {

    private static EntryPanel entryPanel;
    private static List<TbAnimationInfo> animationInfoList;
    private final static int WATCHED = 8;
    public static String[] tableName = new String[]{"watching_monday","watching_tuesday","watching_wednesday",
            "watching_thursday","watching_friday","watching_saturday",
            "watching_sunday","watching_other","watched"};

    /**
     * 查询对象表格数据库并建立对应面板番剧条目
     * @param tableName 查询数据库表格的名称
     * */
    public static void dataBaseQuery(String tableName) {
        List<TbAnimationInfo> animationInfoList = Dao.getAnimationInfos(tableName);
        for (int i = 0; i < animationInfoList.size(); i++) {
            TbAnimationInfo animationInfo = animationInfoList.get(i);
            entryPanel = new EntryPanel(animationInfo);
        }
    }

    /**
     * 查询对象表格数据库并建立对应面板番剧条目
     * @param tableName 查询数据库表格的名称
     * */
    public static List<TbAnimationInfo> getAnimationList(String tableName) {
        List<TbAnimationInfo> animationInfoList = Dao.getAnimationInfos(tableName);
        return animationInfoList;
    }

    /**
     * 读取指定周目番剧信息
     * @param tableName 查询数据库表格的名称
     * */
    public static List getAnimationInfos(String tableName) {
        List list = findForList("select * from " + tableName);
        List<TbAnimationInfo> animationInfoList = new ArrayList<>();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            TbAnimationInfo animationInfo = new TbAnimationInfo();
            List info = (List) iterator.next();
            animationInfo.setId((String) info.get(0));
            animationInfo.setName((String) info.get(1));
            animationInfo.setAmount((String) info.get(2));
            animationInfo.setDate((String) info.get(3));
            animationInfoList.add(animationInfo);
        }
        return animationInfoList;
    }

    public static List findForList(String sql) {
        List<List> list = new ArrayList<>();
        ResultSet resultSet = ConnDB.executeQuery(sql);
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int colCount = metaData.getColumnCount();
            while (resultSet.next()) {
                List<String> row = new ArrayList<>();
                //注意这里i是从1开始而不是0！
                for (int i = 1; i <= colCount; i++) {
                    String str = resultSet.getString(i);
                    if (str != null && !str.isEmpty()) {
                        str = str.trim();
                    }
                    row.add(str);
                }
                list.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void loadAnimation() {
        animationInfoList = getAnimationList(tableName[dayOfWeek()]);
        ContentPanel.getContentPanel().addEntry(animationInfoList);
        ContentPanel.setAddAnimeButton();
        ConnDB.close();
    }

    public static void loadAnimation(int weekNum) {
        animationInfoList = getAnimationList(tableName[weekNum]);
        WeekButtonPanel.weekDaySelected = weekNum;
        ContentPanel.getContentPanel().addEntry(animationInfoList);
        ContentPanel.setAddAnimeButton();
        ContentPanel.getContentPanel().revalidate(); //添加控件后调用revalidate()或repaint()重绘面板
        ConnDB.close();
    }

    public static void loadAnimationForWatched() {
        animationInfoList = getAnimationList(tableName[WATCHED]);
        ContentPanel.getContentPanel().addWatchedEntry(animationInfoList);
        ContentPanel.getContentPanel().revalidate(); //添加控件后调用revalidate()或repaint()重绘面板;
        ConnDB.close();
    }

    public static void addAnimationForWatched(String animeName, String number, String date) {
        String sql = "insert into " + tableName[WATCHED] + "(name,number,date) values('" + animeName + "','" + number +"','" + date + "')";
        ConnDB.executeUpdate(sql);
        ConnDB.close();
    }

    public static void addAnimationInfo(int animationSzie, String animeName, String date) {
        int id = animationSzie + 1;
        String sql = "insert into " + tableName[WeekButtonPanel.weekDaySelected] + "(id,name,number,date) values(" + id + ",'" + animeName + "','0','" + date + "')";
        ConnDB.executeUpdate(sql);
    }

    public static void updateAnimationInfo(String columName, String animationName, int id) {
        String sql = "update " + tableName[WeekButtonPanel.weekDaySelected] + " set " + columName + "=\"" + animationName + "\" where id=" + id;
        ConnDB.execute(sql);
    }

    public static void deleteAnimationInfo(int id) {
        String sql = "delete from " + tableName[WeekButtonPanel.weekDaySelected] + " where id=" + id;
        ConnDB.executeUpdate(sql);
        ConnDB.close();
    }

    /**
     *返回当前星期，Calendar.getInstance().get(Calendar.DAY_OF_WEEK)返回值对应时间为周日是1，周一是2，周二是3...以此类推
     *这里为了方便做了转换，使时间对应转换为周一是0，周二是1，周三是2...
     * */
    public static int dayOfWeek() {
        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 1) {
            return 7;
        } else {
            return  Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 2;
        }
    }

}
