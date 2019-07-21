package com.chinapex.ms.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinapex.ms.server.*;

import java.util.Map;

/**
 * 语音识别的总入口
 *
 * @author leiyuhau
 * 2018.12.28
 */
@Controller
@RequestMapping("voice")
public class VoiceController {

    @Autowired
    private VoiceService voiceService;

    @Autowired
    private  VoiceResult voiceResult;

    /**
     *  根据通话参数 转写 语音内容
     * @param json
     * @return
     */
    @PostMapping("upload")
    @ResponseBody
    public Map<String, Object> inputVoiceSource(@RequestBody JSONObject json) {

        return voiceService.inputVoiceSource(json);
    }

    /**
     * 根据CallID 返回通话信息
     * @param json
     * @return
     */
    @PostMapping("get")
    @ResponseBody
    public Map<String,Object> getVoiceResult(@RequestBody JSONObject json){
       return voiceResult.getVoiceResultByID(json);
    }

}
