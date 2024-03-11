package com.fugui.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author 富贵
 * @Date 2024/1/22 10:15
 * @Version 1.0
 */
@Data
public class ExcelListener extends AnalysisEventListener<UserData> {

//    private final List<UserData> dataList = new ArrayList<>();

    //一行一行读取excel内容，从第二行开始读取
    @Override
    public void invoke(UserData userData, AnalysisContext analysisContext) {
//        dataList.add(userData);
        System.out.println(userData);
    }
    //读取表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }

   //读取之后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
