package com.chinapex.ms.utils;

import com.chinapex.ms.pojo.JsonInput;
import com.csvreader.CsvReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leiyuhau
 * 2018.12.20
 * 读取CSV 文件 工具类
 */
public class CSVUtils {

    public static List<JsonInput> RedCSV(String filePath) {

        List<JsonInput> list = new ArrayList<>();

        try {
            if (list.size() > 0) {
                list.clear();
            }
            CsvReader csv = new CsvReader(filePath, ',', Charset.forName("GBK"));
            csv.readHeaders();//跳过表头信息
            List<JsonInput> inputs = StagedCare(csv, list);
            return inputs;
        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;

    }

    static List<JsonInput> StagedCare(CsvReader csv, List<JsonInput> list) {

        try {
            while (csv.readRecord()) {
                String str = csv.get(2);
                if (str.equals("呼叫成功")) {
                    JsonInput jsonInput = new JsonInput();
                    jsonInput.setId(csv.get(0));
                    jsonInput.setAccount_c(csv.get(1));
                    jsonInput.setCallresult_c(csv.get(2));
                    jsonInput.setCalltime_c(csv.get(3));
                    jsonInput.setVoiceLink_c(csv.get(4));
                    jsonInput.setCallId_c(csv.get(5));
                    list.add(jsonInput);
                }
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
