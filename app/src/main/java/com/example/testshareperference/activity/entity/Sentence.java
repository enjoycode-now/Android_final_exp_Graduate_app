/**
 * Copyright 2021 bejson.com
 */
package com.example.testshareperference.activity.entity;
import java.util.List;

/**
 * Auto-generated: 2021-05-20 21:35:33
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Sentence {

    private int code;
    private String msg;
    private List<SentenceData> data;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setData(List<SentenceData> data) {
        this.data = data;
    }
    public List<SentenceData> getData() {
        return data;
    }

}