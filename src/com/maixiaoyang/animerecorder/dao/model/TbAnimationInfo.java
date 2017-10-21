package com.maixiaoyang.animerecorder.dao.model;

import java.io.Serializable;

/**
 * 小肥羊追番神器动漫数据模型
 * @author maixiaoyang
 */
public class TbAnimationInfo implements Serializable {

    /** 番剧编号 */
    private String id;
    /** 番剧名字 */
    private String name;
    /** 观看集数 */
    private String amount;
    /** 观看日期 */
    private String date;

    /** 缺省构造函数 */
    public TbAnimationInfo() {}

    public TbAnimationInfo(String id) {  //最小构造函数（主键）
        this.id = id;
    }

    /** 完整构造函数 */
    public TbAnimationInfo(String id, String name, String amount, String date) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
