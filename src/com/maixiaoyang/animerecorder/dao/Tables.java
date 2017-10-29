package com.maixiaoyang.animerecorder.dao;

import java.util.ArrayList;

/**
 * 小肥羊追番神器数据库表对象
 * @author maixiaoyang
 */
public class Tables {

    /** 表名*/
    private String name;
    /** 表中列集合*/
    private ArrayList<Columns> columns = new ArrayList<Columns>();

    public Tables() {
        super();
    }

    public Tables(String name, ArrayList<Columns> columns) {
        super();
        this.name = name;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Columns> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<Columns> columns) {
        this.columns = columns;
    }

}
