package com.fugui.AppointmentRegistration.cmn.service.impl;


import com.alibaba.excel.EasyExcel;
//import com.alibaba.spring.util.BeanUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fugui.AppointmentRegistration.cmn.listener.DictListener;
import com.fugui.AppointmentRegistration.model.cmn.Dict;
import com.fugui.AppointmentRegistration.cmn.mapper.DictMapper;
import com.fugui.AppointmentRegistration.vo.cmn.DictEeVo;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fugui.AppointmentRegistration.cmn.service.DictService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 富贵
 * @Date 2024/1/20 19:03
 * @Version 1.0
 */

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Autowired
    private DictMapper dictMapper;
    @Override
    //根据数据id查询子数据列表
    public List<Dict> findChildData(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<Dict> dictList = baseMapper.selectList(wrapper);
        // 向list集合每个dict对象中设置hashChildren
        for(Dict dict:dictList){
            Long dictId = dict.getId();
            boolean isChild = this.isChildren(dictId);
            dict.setHasChildren(isChild);
        }
        return dictList;
    }
    //判断id下面是否有子节点
    private boolean isChildren(Long id){
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        //0>0, 1>0
        return count>0;

    }

    @Override

    public void exportDictData(HttpServletResponse response) {
        try {
            //设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = "dict";
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xls");
            //查数据库

            List<Dict> dictList = dictMapper.selectList(null);
            //Dict-->DictEevo
            List<DictEeVo> dictVoList = new ArrayList<>(dictList.size());
            for(Dict dict : dictList) {
                DictEeVo dictVo = new DictEeVo();
                //dictEevo.setId(dict.getId);
                BeanUtils.copyProperties(dict,dictVo);
                dictVoList.add(dictVo);
            }
            //调用方法进行写操作
            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("数据字典").doWrite(dictVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //导入数据字典
    @Override
    public void importDictData(MultipartFile file){
        try {
            EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //根据dictcode和value查询
    @Override
    public String getDictName(String dictCode, String value) {
        if(StringUtils.isEmpty(dictCode)){
        //如果dictCode为空，直接根据value查询
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("value",value);
        Dict dict = baseMapper.selectOne(wrapper);
        return dict.getName();
    }else{//如果dictCode不为空，根据dictCode和value查询
        //根据dictCode查询dict对象，得到dict的id值
        Dict codeDict = this.getDictByDictCode(dictCode);
        Long parent_id = codeDict.getId();
        //根据parent_id和value进行查询
        Dict finalDict = baseMapper.selectOne(new QueryWrapper<Dict>()
                .eq("parent_id", parent_id)
                .eq("value",value));
        return finalDict.getName();
        }


    }
    //根据dictCode获取下级节点(dictCode(省)->id->市辖区)
    @Override
    public List<Dict> findByDictCode(String dictCode) {
        //根据dictcode获取对应（根据129行代码调用getDictByDictCode方法）
        Dict dict = this.getDictByDictCode(dictCode);
        //根据Id获取子节点(根据38行代码调用findChildData方法)
        List<Dict> chliData = this.findChildData(dict.getId());
        return chliData;
    }

    private Dict getDictByDictCode(String dictCode){
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_code",dictCode);
        Dict codeDict = baseMapper.selectOne(wrapper);
        return codeDict;

    }


}
