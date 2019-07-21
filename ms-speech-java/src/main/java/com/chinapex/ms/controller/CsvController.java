package com.chinapex.ms.controller;

import com.chinapex.ms.csv.ThreadMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author leiyuhua
 * 2019.1.4
 * 请求处理cSv 文件入口
 */
@Controller
@RequestMapping("csv")
public class CsvController {

    @Autowired
    private ThreadMain threadMain;

    @GetMapping("init")
    @ResponseBody
    public Map<String, Object> uploadCsv() {
        Map<String, Object> map = threadMain.InitCsv();
        return map;
    }


}
