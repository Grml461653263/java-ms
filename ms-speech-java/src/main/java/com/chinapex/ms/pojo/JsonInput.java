package com.chinapex.ms.pojo;

/**
 * @author leiyuhua
 * json 接收语音转写的参数ben
 */
public class JsonInput {


    //ID
    private String Id;
    //客户
    private String account_c;
    //呼叫结果
    private String Callresult_c;
    //呼叫时间
    private String Calltime_c;
    //语音链接
    private String voiceLink_c;
    //callID
    private String callId_c;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAccount_c() {
        return account_c;
    }

    public void setAccount_c(String account_c) {
        this.account_c = account_c;
    }

    public String getCallresult_c() {
        return Callresult_c;
    }

    public void setCallresult_c(String callresult_c) {
        Callresult_c = callresult_c;
    }

    public String getCalltime_c() {
        return Calltime_c;
    }

    public void setCalltime_c(String calltime_c) {
        Calltime_c = calltime_c;
    }

    public String getVoiceLink_c() {
        return voiceLink_c;
    }

    public void setVoiceLink_c(String voiceLink_c) {
        this.voiceLink_c = voiceLink_c;
    }

    public String getCallId_c() {
        return callId_c;
    }

    public void setCallId_c(String callId_c) {
        this.callId_c = callId_c;
    }

    @Override
    public String toString() {
        return "JsonInput{" +
                "Id='" + Id + '\'' +
                ", account_c='" + account_c + '\'' +
                ", Callresult_c='" + Callresult_c + '\'' +
                ", Calltime_c='" + Calltime_c + '\'' +
                ", voiceLink_c='" + voiceLink_c + '\'' +
                ", callId_c='" + callId_c + '\'' +
                '}';
    }
}
