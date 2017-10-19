package com.maixiaoyang.animerecorder.ConnDB;

import java.sql.*;

/**
 * 小肥羊追番神器数据库连接
 * @author maixiaoyang
 */
public class ConnDB {

    private static String dbClassName = "com.mysql.jdbc.Driver"; //数据库驱动
    private static String dbUrl = "jdbc:mysql://localhost:3306/anime_record?useUnicode=true&characterEncoding=GBK"; //访问MySQL数据库的路径
    private static String dbUser = "root"; //访问MySQL数据库的用户名
    private static String dbPwd = ""; //访问MySQL数据库的密码
    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet resultSet = null;

    /**
     * 功能：连接数据库
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(dbClassName).newInstance(); //加载数据库驱动，注册到驱动管理器
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPwd); //创建数据库连接
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
	 * 功能：执行查询语句
	 * @param sql：用于指定查询语句
	 */
    public static ResultSet executeQuery(String sql) {
        try {
            connection = getConnection();
            /*
            *ResultSet的Type属性TYPE_FORWARD_ONLY,   TYPE_SCROLL_INSENSITIVE,or TYPE_SCROLL_SENSITIVE解释：
            *1.TYPE_FORWORD_ONLY,只可向前滚动；
            *2.TYPE_SCROLL_INSENSITIVE,双向滚动，但不及时更新，就是如果数据库里的数据修改过，并不在ResultSet中反应出来。
            *3.TYPE_SCROLL_SENSITIVE，双向滚动，并及时跟踪数据库的更新,以便更改ResultSet中的数据
            *
            *CONCUR_READ_ONLY ：
            *就是类似只读 属性，不可仪更改的啊！不能用结果集更新数据。
            *CONCUR_UPDATABLE ：
            *ResultSet对象可以执行数据库的新增、修改、和移除。
            */
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
	 * 功能:执行更新操作
	 * @param sql：用于指定更新语句
	 */
    public static int executeUpdate(String sql) {
        int result = 0;
        try {
            connection = getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return result;
    }

    /***
     *更新对象表格数据
     */
    public static void execute(String sql) {
        try {
            connection = getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
    }

    /**
	 * 功能:关闭数据库的连接
	 */
    public static void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
