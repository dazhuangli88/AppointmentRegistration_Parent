package com.fugui.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 富贵
 * @Date 2024/1/22 9:52
 * @Version 1.0
 */

public class TestWrite {

    public static void main(String[]args){

        //构建数据list集合
        List<UserData> list = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            UserData data = new UserData();
            data.setUid(i);
            data.setUsername("lucy"+i);
            list.add(data);
        }

        //设置excel文件路径和文件名称
        String fileName = "D:\\excel\\01.xls";
        //调用方法实现写操作
        EasyExcel.write(fileName,UserData.class).sheet("用户信息")
                .doWrite(list);

    }
    }
