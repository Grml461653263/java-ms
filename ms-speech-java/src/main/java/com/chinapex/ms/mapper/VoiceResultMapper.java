package com.chinapex.ms.mapper;

import com.chinapex.ms.pojo.VoiceResult;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;

public interface VoiceResultMapper extends Mapper<VoiceResult> {

    @Insert("insert into voice_result (ID,URL,TEXT,JSON,DATE) values (#{id},#{url},#{text},#{json},#{date})")
    int insertVoiceResult(String id, String url, String text, String json, Date date);
}
