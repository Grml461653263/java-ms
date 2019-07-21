package com.chinapex.ms.thread;

import com.alibaba.fastjson.JSON;
import com.chinapex.ms.mapper.ResultMapper;
import com.chinapex.ms.mapper.VoiceResultMapper;
import com.chinapex.ms.pojo.JsonInput;
import com.chinapex.ms.pojo.Result;
import com.chinapex.ms.pojo.Results;
import com.chinapex.ms.pojo.VoiceResult;
import com.chinapex.ms.utils.DownloadFileUtils;
import com.chinapex.ms.utils.SpringBeanUtils;
import com.chinapex.ms.utils.XFSpeechUtils;
import org.apache.commons.lang3.StringUtils;


import java.io.File;
import java.util.Date;
import java.util.List;

public class SecondLevedThread implements Runnable {

    private JsonInput jsonInput;
    private ResultMapper resultMapper;
    private VoiceResultMapper voiceResultMapper;

    public SecondLevedThread(JsonInput jsonInput) {
        this.jsonInput = jsonInput;
    }

    @Override
    public void run() {

        resultMapper = SpringBeanUtils.getBean(ResultMapper.class);
        voiceResultMapper = SpringBeanUtils.getBean(VoiceResultMapper.class);

        Results result = new Results();
        StringBuffer buffer = new StringBuffer();
        String link_c = jsonInput.getVoiceLink_c();
        if (StringUtils.isNotEmpty(link_c)) {
            //下载 语料资源
            DownloadFileUtils downloadFileUtils = new DownloadFileUtils();
            String file = downloadFileUtils.DownloadFile(link_c);

            //语音内容转换
            String recognize = XFSpeechUtils.VoiceRecognize(file);

            File delete = new File(file);
            if (delete.exists()) {
                delete.delete();
            }

            if(StringUtils.isNotEmpty(recognize)) {
                //转换内容转化未实体对象
                List<Result> results = JSON.parseArray(recognize, Result.class);

                for (Result res : results) {
                    buffer.append(res.getOnebest());
                    res.setVid(jsonInput.getId());
                }

                result.setListResult(results);

                //新增入库
                VoiceResult voiceResult = new VoiceResult(jsonInput.getId(), jsonInput.getVoiceLink_c(), buffer.toString(), recognize, new Date());
                //新增 解析 结果
                voiceResultMapper.insertSelective(voiceResult);

                List<Result> listResult = result.getListResult();

                for (Result res : listResult) {

                    resultMapper.insertSelective(res);
                }
            }
        }
    }
}
