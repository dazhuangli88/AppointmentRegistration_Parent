package com.fugui.AppointmentRegistration.cmn.controller;

import com.alibaba.excel.EasyExcel;
import com.fugui.AppointmentRegistration.common.result.Result;
import com.fugui.AppointmentRegistration.model.cmn.Dict;
import com.fugui.AppointmentRegistration.vo.cmn.DictEeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fugui.AppointmentRegistration.cmn.service.DictService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author 富贵
 * @Date 2024/1/20 19:45
 * @Version 1.0
 */
@Api(tags = "数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
public class DictController {
    @Autowired

    private DictService dictService;
    //导入数据字典
    @ApiOperation(value = "导入数据字典")
    public  Result importDict(MultipartFile file) {
        dictService.importDictData(file);
        return Result.ok();
    }
//        try {
//            EasyExcel.read(file.getInputStream(), DictEeVo.class,new DictListener(baseMapper)).sheet().doRead();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //导出数据字典
    @ApiOperation(value = "导出数据字典")
    @GetMapping("exportData")
    public void exportDict(HttpServletResponse response){
        dictService.exportDictData(response);

    }

    //根据dictCode获取下级节点(dictCode(省)->id->市)
    @ApiOperation(value = "根据dictCode获取下级节点")
    @GetMapping("findByDictCode/{dictCode}")
    public Result findByDict(@PathVariable String dictCode){
        List<Dict> list = dictService.findByDictCode(dictCode);
        return Result.ok(list);
    }

    //根据数据id查询子数据列表
    @ApiOperation(value ="根据数据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id ){
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);

    }

    //根据dictcode和value查询
    @ApiOperation(value ="根据dictcode和value查询")
    @GetMapping("getName/{dictCode}/{value}")
    public String getName(@PathVariable String dictCode,
                          @PathVariable String value){
        String dictName = dictService.getDictName(dictCode,value);
        return dictName;
    }

    //根据value查询
    @ApiOperation(value ="根据value查询")
    @GetMapping("getName/{value}")
    public String getName(@PathVariable String value){
        String dictName = dictService.getDictName("",value);
        return dictName;
    }

}
