package com.maixiaoyang.animerecorder.dao;

import com.maixiaoyang.animerecorder.conndb.ConnDataBase;
import com.maixiaoyang.animerecorder.dao.model.TbAnimationInfo;
import com.maixiaoyang.animerecorder.ui.ContentPanel;
import com.maixiaoyang.animerecorder.ui.EntryPanel;
import com.maixiaoyang.animerecorder.ui.WeekButtonPanel;

import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        ResultSet resultSet = ConnDataBase.executeQuery(sql);
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
        ConnDataBase.close();
    }

    public static void loadAnimation(int weekNum) {
        animationInfoList = getAnimationList(tableName[weekNum]);
        WeekButtonPanel.weekDaySelected = weekNum;
        ContentPanel.getContentPanel().addEntry(animationInfoList);
        ContentPanel.setAddAnimeButton();
        ContentPanel.getContentPanel().revalidate(); //添加控件后调用revalidate()或repaint()重绘面板
        ConnDataBase.close();
    }

    public static void loadAnimationForWatched() {
        animationInfoList = getAnimationList(tableName[WATCHED]);
        ContentPanel.getContentPanel().addWatchedEntry(animationInfoList);
        ContentPanel.getContentPanel().revalidate(); //添加控件后调用revalidate()或repaint()重绘面板;
        ConnDataBase.close();
    }

    public static void addAnimationForWatched(String animeName, String number, String date) {
        String sql = "insert into " + tableName[WATCHED] + "(name,number,date) values('" + animeName + "','" + number +"','" + date + "')";
        ConnDataBase.executeUpdate(sql);
        ConnDataBase.close();
    }

    public static void addAnimationInfo(int animationSzie, String animeName, String date) {
        int id = animationSzie + 1;
        String sql = "insert into " + tableName[WeekButtonPanel.weekDaySelected] + "(id,name,number,date) values(" + id + ",'" + animeName + "','0','" + date + "')";
        ConnDataBase.executeUpdate(sql);
    }

    public static void updateAnimationInfo(String columName, String animationName, int id) {
        String sql = "update " + tableName[WeekButtonPanel.weekDaySelected] + " set " + columName + "=\"" + animationName + "\" where id=" + id;
        ConnDataBase.execute(sql);
    }

    public static void deleteAnimationInfo(int id) {
        String sql = "delete from " + tableName[WeekButtonPanel.weekDaySelected] + " where id=" + id;
        ConnDataBase.executeUpdate(sql);
        ConnDataBase.close();
    }

    /**
     *返回当前星期，Calendar.getInstance().get(Calendar.DAY_OF_WEEK)返回值对应时间为周日是1，周一是2，周二是3...以此类推
     *这里为了方便做了转换，使时间对应转换为周一是0，周二是1，周三是2...
     * */
    public static int dayOfWeek() {
        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 1) {
            return 6;
        } else {
            return  Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 2;
        }
    }

    public static String backup() throws SQLException {
        //备份文件中的所有sql
        LinkedList<String> sqls = new LinkedList<>();
        //涉及的相关表名数组
        ArrayList<Tables> tableList = new ArrayList<>();
        for (int i = 0; i < tableName.length; i++) {
            ResultSet rs = ConnDataBase.executeQuery("desc " + tableName[i]);
            ArrayList<Columns> columns = new ArrayList<>();
            while (rs.next()) {
                Columns c = new Columns();
                c.setName(rs.getString("Field"));
                c.setType(rs.getString("Type"));
                String isnull = rs.getString("Null");
                if ("YES".equals(isnull)) {
                    c.setNull(true);
                }
                String key = rs.getString("key");
                if ("PRI".equals(key)) {
                    c.setKey(true);
                    String increment = rs.getString("Extra");
                    if ("auto_increment".equals(increment)) {
                        c.setIncrement(true);
                    }
                }
                columns.add(c);
            }
            Tables table = new Tables(tableName[i], columns);
            tableList.add(table);
            rs.close();
        }

        for (int i = 0; i < tableList.size(); i++) {
            Tables table = tableList.get(i);
            String dropsql = "DROP TABLE IF EXISTS "  + table.getName() + " ;";
            sqls.add(dropsql);
            StringBuilder createsql = new StringBuilder();
            createsql.append("CREATE TABLE " + table.getName() + "( ");
            ArrayList<Columns> columns = table.getColumns();
            for (int k = 0; k < columns.size(); k++) {
                Columns c = columns.get(k);
                createsql.append(c.getName() + " " + c.getType());
                if (!c.isNull()) {
                    createsql.append(" not null ");
                }
                if (c.isKey()) {
                    createsql.append(" primary key ");
                    if (c.isIncrement()) {
                        createsql.append(" AUTO_INCREMENT ");
                    }
                }
                if (k < columns.size() - 1) {
                    createsql.append(",");
                } else {
                    createsql.append(");");
                }
            }
            sqls.add(createsql.toString());
            ResultSet rs = ConnDataBase.executeQuery("select * from " + table.getName());
            while (rs.next()) {
                StringBuilder insertsql = new StringBuilder();
                insertsql.append("INSERT INTO " + table.getName() + " VALUES(");
                for (int j = 0; j < columns.size(); j++) {
                    Columns c = columns.get(j);
                    String type = c.getType();
                    if (type.startsWith("varchar") || type.startsWith("char") || type.startsWith("datetime")) {
                        insertsql.append("'" + rs.getString(c.getName()) + "'");
                    } else {
                        insertsql.append(rs.getString(c.getName()));
                    }
                    if (j < columns.size() - 1) {
                        insertsql.append(",");
                    } else {
                        insertsql.append(");");
                    }
                }
                sqls.add(insertsql.toString());
            }
            rs.close();
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String backupTime = sdf.format(date);
        String filePath = "backup" + File.separator + "\\" + backupTime + ".sql";

        File sqlFile = new File(filePath);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter rw = null;
        try {
            fos = new FileOutputStream(sqlFile);
            osw = new OutputStreamWriter(fos);
            rw = new BufferedWriter(osw);
            for (String tmp : sqls) {
                rw.write(tmp);
                rw.newLine();
                rw.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 倒序依次关闭所有IO流
            if (rw != null) {
                try {
                    rw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        ConnDataBase.executeUpdate("delete from backup where id=1");
        ConnDataBase.close();

        String sql = "insert into backup(id,filePath) values('1', '" + filePath + "')";
        ConnDataBase.executeUpdate(sql);
        ConnDataBase.close();

        return  filePath;
    }

    public static void restore() {
        String filePath = new String();

        ResultSet resultSet = ConnDataBase.executeQuery("select filePath from backup where id=1");
        try {
            while (resultSet.next()) {
                filePath = resultSet.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        File sqlFile = new File(filePath);
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(sqlFile);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String readStr;
            while ((readStr = br.readLine()) != null) {
                if (!"".equals(readStr.trim())) {
                    int count = ConnDataBase.executeUpdate(readStr);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 倒序依次关闭所有IO流
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void updateDateBase() {

    }

}
