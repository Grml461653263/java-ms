package com.chinapex.ms.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 分词pojo
 *
 * @author leiyuhua
 * 2018/12/28
 */
public class WordsResult {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //句子置信度，范围为[0,1]
    private String wc;
    //词相对于本句子的起始帧，其中一帧是10ms
    private String wordBg;
    //词相对于本句子的终止帧，其中一帧是10ms
    private String wordEd;
    //分词内容
    private String wordsName;
    //词属性，p表示标点，s表示顺滑词，n表示普通词，g表示分段
    private String wp;

    //分词对象的id
    private int rid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getWc() {
        return wc;
    }

    public void setWc(String wc) {
        this.wc = wc;
    }

    public String getWordBg() {
        return wordBg;
    }

    public void setWordBg(String wordBg) {
        this.wordBg = wordBg;
    }

    public String getWordEd() {
        return wordEd;
    }

    public void setWordEd(String wordEd) {
        this.wordEd = wordEd;
    }

    public String getWordsName() {
        return wordsName;
    }

    public void setWordsName(String wordsName) {
        this.wordsName = wordsName;
    }

    public String getWp() {
        return wp;
    }

    public void setWp(String wp) {
        this.wp = wp;
    }
}
