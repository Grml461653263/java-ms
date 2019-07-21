package com.chinapex.ms.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * voice 结果类
 *
 * @author leiyuhua
 * 2018.12.29
 */
public class VoiceResult {


    @Id
    @Column(name = "ID")
    private String ID;
    //语料资源地址
    @Column(name = "URL")
    private String URL;
    //语料处理结果（文本）
    @Column(name = "TEXT")
    private String TEXT;
    //语料结果（JSON）
    @Column(name = "JSON")
    private String JSON;
    //结果录入时间
    @Column(name = "INSERTDATE")
    private Date UPDATE;
    //备注
    @Column(name = "REMARKS")
    private String REMARKS;

    public  VoiceResult(){

    }
    public VoiceResult(String ID, String URL, String TEXT, String JSON, Date UPDATE) {
        this.ID = ID;
        this.URL = URL;
        this.TEXT = TEXT;
        this.JSON = JSON;
        this.UPDATE = UPDATE;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getTEXT() {
        return TEXT;
    }

    public void setTEXT(String TEXT) {
        this.TEXT = TEXT;
    }

    public String getJSON() {
        return JSON;
    }

    public void setJSON(String JSON) {
        this.JSON = JSON;
    }

    public Date getUPDATE() {
        return UPDATE;
    }

    public void setUPDATE(Date UPDATE) {
        this.UPDATE = UPDATE;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }
}
