package com.fugui.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.util.List;

/**
 * @Author 富贵
 * @Date 2024/1/22 10:29
 * @Version 1.0
 */
public class TestRead {

    public static void main(String[] args) {

        //读取文件路径
        String filName = "D:\\excel\\01.xls";
        //调用方法实现读取操作
        ExcelListener excelListener = new ExcelListener();
        EasyExcel.read(filName,UserData.class,excelListener).sheet().doRead();
//        EasyExcel.read(filName,UserData.class,excelListener).sheet().doReadSync();
//        for (UserData userData : excelListener.getDataList()) {
//            System.out.println(userData);
//        }
    }
}




