package com.fugui.AppointmentRegistration.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
//import com.alibaba.spring.util.BeanUtils;
import com.fugui.AppointmentRegistration.cmn.mapper.DictMapper;
import com.fugui.AppointmentRegistration.model.cmn.Dict;
import com.fugui.AppointmentRegistration.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;

/**
 * @Author 富贵
 * @Date 2024/1/22 17:43
 * @Version 1.0
 */
public class DictListener extends AnalysisEventListener<DictEeVo> {
    private DictMapper dictMapper;

    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }


    //按行读取
    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        //调用方法添加数据库
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
