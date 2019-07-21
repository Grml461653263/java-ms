package com.chinapex.ms.utils;


import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

/**
 * @author leiyuhua
 * 2018.12.22
 * 下载语料工具类
 */
public class DownloadFileUtils {

    public String DownloadFile(String filepath) {
        try {
            //音频下载地址
            String str = this.rePath();
            File file = new File(str);
            file.createNewFile();

            URL httpurl = new URL(filepath);
            //下载音频
            FileUtils.copyURLToFile(httpurl, file);

           /* Encoder encoder = new Encoder();
            MultimediaInfo info = encoder.getInfo(file);
            long duration = info.getDuration();

           long time =  duration/1000;*/

           //时长不够删除音频
           /* if(time<30){
                file.delete();
                return null;
            }*/
            return str;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String rePath() throws FileNotFoundException {

        File path = new File(ResourceUtils.getURL("classpath:").getPath());

        String map3path = path.getAbsolutePath() + "/static/mp3/download/";
        String  paths = "1,2,3,4,5,6,7,8,9,0,A,B,C,D,E,F";
        String[] split = paths.split(",");
        for (String s : split) {
            map3path += split[(int)(Math.random()*16)]+"\\";
        }
        File upload = new File(map3path);
        if (!upload.exists()) {
            upload.mkdirs();
        }
        String str = upload + "\\" + UUID.randomUUID().toString().replaceAll("-", "") + ".mp3";
        return str;
    }

}
