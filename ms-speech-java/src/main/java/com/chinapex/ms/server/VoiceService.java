package com.chinapex.ms.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinapex.ms.mapper.ResultMapper;
import com.chinapex.ms.mapper.VoiceResultMapper;
import com.chinapex.ms.mapper.WordsResultMapper;
import com.chinapex.ms.pojo.JsonInput;
import com.chinapex.ms.pojo.Result;
import com.chinapex.ms.pojo.Results;
import com.chinapex.ms.pojo.VoiceResult;
import com.chinapex.ms.thread.CachecThreadPool;
import com.chinapex.ms.thread.SecondLevedThread;
import com.chinapex.ms.thread.ThreadPoolUtils;
import com.chinapex.ms.utils.DownloadFileUtils;
import com.chinapex.ms.utils.XFSpeechUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * 语音转化Server类
 *
 * @author leiyuhau
 * 2019.1.2
 */
@Service
public class VoiceService {

    @Autowired
    private ResultMapper resultMapper;

    @Autowired
    private VoiceResultMapper voiceResultMapper;

    @Autowired
    private WordsResultMapper wordsResultMapper;

    Logger log = LoggerFactory.getLogger(JsonInput.class);
    /**
     * 单条通话信息 处理
     *
     * @param json 请求示例如下
     *             {
     *             "Id":"a0g6F00000f5RsK",
     *             "account_c":"0016F00002UGA9mQAH",
     *             "Callresult_c":"呼叫成功",
     *             "Calltime_c":"2018-9-15 下午3:00",
     *             "voiceLink_c":"http://103.38.234.11:1080/xa003/2018/06/18/170745.mp3",
     *             "callId_c":"6082D075-8CA8-47BA-B46F-F4B84D0A316F"
     *             <p>
     *             }
     * @return map
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> inputVoiceSource(JSONObject json) {
        Map<String, Object> map = new HashMap<>();
        JsonInput input = json.toJavaObject(JsonInput.class);
        map = this.listVoiceResult(input, map);
        return map;

    }

    /**
     * 多条语料信息处理
     *
     * @param list 请求参数实体集合
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> listVoiceSource(List<JsonInput> list) {
        Map<String, Object> map = new HashMap<>();
        //

        try {
            ExecutorService executor = ThreadPoolUtils.getExecutor();

            for (int i = 0; i < list.size(); i++) {
                executor.execute(new SecondLevedThread(list.get(i)));
            }
            map.put("msg", "操作成功");
            map.put("code", 200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "服务器错误");
            map.put("code", 300);
        }
        return map;
    }


    Map<String, Object> listVoiceResult(JsonInput input, Map<String, Object> map) {
        Results rs = new Results();

        if (map.size() > 0) {
            map.clear();
        }
        StringBuffer buffer = new StringBuffer();
        try {
            if (map.size() > 0) {
                map.clear();
            }
            String voiceLink_c = input.getVoiceLink_c();
            //下载语料资源
            DownloadFileUtils df = new DownloadFileUtils();
            String path = df.DownloadFile(voiceLink_c);
            File file = new File(path);
            if(file.exists()){

                //语义转化
                String recognize = XFSpeechUtils.VoiceRecognize(path);
                List<Result> results = JSON.parseArray(recognize, Result.class);
                results.forEach(result -> {
                    buffer.append(result.getOnebest());
                    result.setVid(input.getId());
                });
                rs.setListResult(results);

                VoiceResult voiceResult = new VoiceResult(input.getId(), input.getVoiceLink_c(), buffer.toString(), recognize, new Date());
                //新增 解析 结果
                int i = voiceResultMapper.insertSelective(voiceResult);

                //新增 单句内容
                rs.getListResult().forEach(list -> {
                    resultMapper.insertSelective(list);
                    //新增分词  内容

                /*int lid = list.getId();
                if(list.getWordsResultList()!=null) {
                    list.getWordsResultList().forEach(li -> {
                        li.setRid(list.getId());

                        //新增分词信息
                         wordsResultMapper.insertSelective(li);
                    });
                }*/
                });
                log.info("语料----"+input.getVoiceLink_c()+"已识别");
            }else {
                log.info("语料时长过短，不予处理");
            }
            map.put("msg", "转义成功，数据已入库");
            map.put("Id", input.getId());

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "300");
            map.put("msg", "服务器错误");
        }

        return map;
    }
}
