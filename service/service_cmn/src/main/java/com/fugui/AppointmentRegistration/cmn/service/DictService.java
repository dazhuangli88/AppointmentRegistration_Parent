package com.fugui.AppointmentRegistration.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fugui.AppointmentRegistration.model.cmn.Dict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author 富贵
 * @Date 2024/1/9 20:34
 * @Version 1.0
 */
@Service
public interface DictService extends IService<Dict> {

    List<Dict> findChildData(Long id);

    //导出数据字典接口
    void exportDictData(HttpServletResponse response);
    //导入数据字典
    void importDictData(MultipartFile file);
}
