package com.chinapex.ms.server;

import com.alibaba.fastjson.JSONObject;
import com.chinapex.ms.mapper.VoiceResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VoiceResult {

    @Autowired
    private VoiceResultMapper vr;

    /**
     *根据CallID 返回通话信息
     * @param json
     * @return
     */
    public Map<String,Object> getVoiceResultByID(JSONObject json){
        Map<String,Object> map = new HashMap<>();

        try {
            String params = json.getString("id");
            com.chinapex.ms.pojo.VoiceResult voiceResult = vr.selectByPrimaryKey(params);
            map.put("code",200);
            map.put("msg",voiceResult);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","服务器错误");
        }
        return map;
    }


}
