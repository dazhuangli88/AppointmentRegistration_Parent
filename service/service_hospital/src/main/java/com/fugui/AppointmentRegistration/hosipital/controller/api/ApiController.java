package com.fugui.AppointmentRegistration.hosipital.controller.api;

import com.fugui.AppointmentRegistration.common.result.Result;
import com.fugui.AppointmentRegistration.common.result.ResultCodeEnum;
import com.fugui.AppointmentRegistration.common.result.YyghException;
import com.fugui.AppointmentRegistration.hosipital.service.DepartmentService;
import com.fugui.AppointmentRegistration.hosipital.service.HospitalSetService;
import com.fugui.AppointmentRegistration.hosipital.service.HospitalService;
import com.fugui.AppointmentRegistration.hosipital.service.ScheduleService;
import com.fugui.AppointmentRegistration.model.hosp.Department;
import com.fugui.AppointmentRegistration.model.hosp.Hospital;
import com.fugui.AppointmentRegistration.model.hosp.Schedule;
import com.fugui.AppointmentRegistration.vo.hosp.DepartmentQueryVo;
import com.fugui.AppointmentRegistration.vo.hosp.ScheduleQueryVo;
import com.fugui.AppoointmentRegistration.common.helper.HttpRequestHelper;
import com.fugui.AppoointmentRegistration.common.util.MD5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author 富贵
 * @Date 2024/2/29 16:21
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/hospital")
public class ApiController {

    private HospitalService hospitalService;
    @Autowired
    public void setHospitalService(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }


    private HospitalSetService hospitalSetService;
    @Autowired
    public void setHospitalSetService(HospitalSetService hospitalSetService) {
        this.hospitalSetService = hospitalSetService;
    }

    @Autowired
    private DepartmentService  departmentService;

    @Autowired
    private  ScheduleService   scheduleService;

    //删除排班接口
    @PostMapping("schedule/remove")
    public Result remove(HttpServletRequest request){
        //获取传递过来排班信息
        Map<String,String[]> requestMap = request.getParameterMap();
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //医院编号和排班编号
        String hoscode = (String)paramMap.get("hoscode");
        String hosScheduleId = (String)paramMap.get("hosScheduleId");

        //TODO签名校验

        scheduleService.remove(hoscode,hosScheduleId);
        return Result.ok();
    }

    //查询排班接口
    @PostMapping("schedule/list")
    public Result findSchedule(HttpServletRequest request){
        //获取传递过来排班信息
        Map<String,String[]> requestMap = request.getParameterMap();
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //医院编号
        String hoscode = (String)paramMap.get("hoscode");
        //科室编号
        String depcode = (String)paramMap.get("depcode");

        //当前页 和每页记录数
        int page = StringUtils.isEmpty( paramMap.get("page")) ? 1 : Integer.parseInt((String)paramMap.get("page"));
        int limit = StringUtils.isEmpty( paramMap.get("limit")) ? 1 : Integer.parseInt((String)paramMap.get("limit"));
        //TODO签名校验

       ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);
        scheduleQueryVo.setDepcode(depcode);
        //调用service方法
        Page<Schedule> pageModel = scheduleService.findPageSchedule(page,limit,scheduleQueryVo);
        return Result.ok(pageModel);
    }

    //上传排班接口
    @PostMapping("saveSchedule")
    public Result saveSchedule(HttpServletRequest request){
        //获取传递过来排班信息
        Map<String,String[]> requestMap = request.getParameterMap();
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //TDOD 签名校验
        scheduleService.save(paramMap);
        return Result.ok();
    }

    //上传科室接口
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request){
        //获取传递过来的科室信息
        Map<String,String[]> requestMap = request.getParameterMap();
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //获取医院编号
        String hoscode = (String)paramMap.get("hoscode");

        //1获取医院系统传递过来的签名，签名进行MD5加密
        String hospSign = (String)paramMap.get("sign");

        //2.根据传递过来的医院编码，查询数据库，查询签名
        String signKey = hospitalSetService.getSignKey(hoscode);

        //3.把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //4.判断签名是否一致
        if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        //调用service方法实现根据医院编号查询
       departmentService.save(paramMap);
        return Result.ok();
    }

    //删除科室接口
    @PostMapping("department/remove")
    public Result removeDepartment(HttpServletRequest request){
        //获取传递过来的科室信息
        Map<String,String[]> requestMap = request.getParameterMap();
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //医院编号 和 科室编号
        String hoscode = (String)paramMap.get("hoscode");
        String depcode = (String)paramMap.get("depcode");

        //TODO签名校验
        departmentService.remove(hoscode,depcode);
        return Result.ok();
    }

    //查询科室接口
    @PostMapping("department/list")
    public Result findDepartment(HttpServletRequest request){
        //获取传递过来的科室信息
        Map<String,String[]> requestMap = request.getParameterMap();
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //医院编号
        String hoscode = (String)paramMap.get("hoscode");
        //当前页 和每页记录数
        int page = StringUtils.isEmpty( paramMap.get("page")) ? 1 : Integer.parseInt((String)paramMap.get("page"));
        int limit = StringUtils.isEmpty( paramMap.get("limit")) ? 1 : Integer.parseInt((String)paramMap.get("limit"));
        //TDOD签名校验

        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);
        //调用service方法
        Page<Department> pageModel = departmentService.findPageDepartment(page,limit,departmentQueryVo);
        return Result.ok(pageModel);
    }


    //查询医院
    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request){
        //获取传递过来的医院信息
        Map<String,String[]> requestMap = request.getParameterMap();
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //获取医院编号
        String hoscode = (String)paramMap.get("hoscode");

        //1获取医院系统传递过来的签名，签名进行MD5加密
        String hospSign = (String)paramMap.get("sign");

        //2.根据传递过来的医院编码，查询数据库，查询签名
        String signKey = hospitalSetService.getSignKey(hoscode);

        //3.把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);


        //4.判断签名是否一致
      if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
    }
        //调用service方法实现根据医院编号查询
        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);
    }


    //上传医院接口
    @PostMapping("saveHospital")
    public Result saveHospital(HttpServletRequest request){
        //获取传递过来的医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //1获取医院系统传递过来的签名，签名进行MD5加密
        String hospSign = (String)paramMap.get("sign");

        //2.根据传递过来的医院编码，查询数据库，查询签名
        String hoscode = (String)paramMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);

        //3.把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

       //4.判断签名是否一致
        if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        //调用service的方法
        hospitalService.save(paramMap);
        return Result.ok();

    }


}
