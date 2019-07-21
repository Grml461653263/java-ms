package com.chinapex.ms.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

/**
 * 语料识别 结果的pojo类
 *
 * @author leiyuhua
 * 2018/12/28
 */
public class Result {

    @Id
    @Column(name = "id")
    private int id;
    //开始时间
    private String bg;
    //结束时间
    private String ed;
    //句子内容
    private String onebest;
    //句子标识，相同si表示同一句话，从0开始
    private String si;
    //说话人编号
    private String speaker;

    //语料ID
    private String vid;

    //分词结果集
    private List<WordsResult> wordsResultList;


    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public String getOnebest() {
        return onebest;
    }

    public void setOnebest(String onebest) {
        this.onebest = onebest;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public List<WordsResult> getWordsResultList() {
        return wordsResultList;
    }

    public void setWordsResultList(List<WordsResult> wordsResultList) {
        this.wordsResultList = wordsResultList;
    }
}
