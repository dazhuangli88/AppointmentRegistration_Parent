package com.fugui.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author 富贵
 * @Date 2024/1/22 9:49
 * @Version 1.0
 */
@Data
public class UserData {

    @ExcelProperty(value = "用户编号",index = 0)
    private int uid;

    @ExcelProperty("用户名称")
    private String username;

}
