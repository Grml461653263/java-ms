package com.chinapex.ms.csv;

import com.chinapex.ms.pojo.JsonInput;
import com.chinapex.ms.thread.SecondLevedThread;
import com.chinapex.ms.thread.ThreadPoolUtils;
import com.chinapex.ms.utils.CSVUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author leiyuhua
 * 2018.12.30
 */
@Component
public class ThreadMain {

    /**
     * 处理MS语料CSV文件的入口
     * @return
     */
    public Map<String, Object> InitCsv() {

        Map<String, Object> map = new HashMap<>();
        try {
            String parth = "C:\\Users\\Administrator\\Desktop\\MS\\custom_sample(1).csv";
            List<JsonInput> list = CSVUtils.RedCSV(parth);


            ExecutorService executor = ThreadPoolUtils.getExecutor();


            List<JsonInput> inputs = list.subList(820, 825);

            int middle = (int) inputs.size() / 2;

            int size = inputs.size() - middle;

            List<JsonInput> subList = inputs.subList(0, middle);
            for (int i = 0; i < middle; i++) {
                executor.execute(new SecondLevedThread(subList.get(i)));
            }
            List<JsonInput> inputList = inputs.subList(middle, inputs.size());
            for (int i = 0; i < size; i++) {
                executor.execute(new SecondLevedThread(inputList.get(i)));
            }

            map.put("code", "200");
            map.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "300");
            map.put("msg", "error");
        }

        return map;
    }
}
