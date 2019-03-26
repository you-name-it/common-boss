package com.common.service.KolAppointmentRecord.web;

import com.common.base.index.web.BaseController;
import com.common.base.user.entity.User;
import com.common.service.KolAppointmentRecord.entity.KolAppointmentRecord;
import com.common.service.KolAppointmentRecord.service.KolAppointmentRecordService;
import com.common.utils.AliMessageContant;
import com.common.utils.StringUtil;
import com.common.utils.sendMessage;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 〈kol预约记录〉
 *
 * @author HuangQiuRong
 * @create 2019/3/19
 */
@Controller
@RequestMapping("/kolAppointment")
public class KolAppointmentRecordController extends BaseController {

    @Autowired
    private KolAppointmentRecordService kolAppointmentRecordService;
    
    @PostMapping("save")
    @ResponseBody
    public Object save(HttpServletRequest request, KolAppointmentRecord kolAppointmentRecord){
        try {
            //获取当前登陆的用户
            User userEntity = (User) SecurityUtils.getSubject().getPrincipal();

            kolAppointmentRecord.setCreateTime(new Date());
            kolAppointmentRecord.setUpdateTime(new Date());
            kolAppointmentRecord.setStatus("1");
            kolAppointmentRecord.setRecordID(StringUtil.uuid());

            if (this.kolAppointmentRecordService.insert(kolAppointmentRecord)) {


                sendMessage.sendNotice(userEntity.getPhone(),userEntity.getUserName(), AliMessageContant.Notice);
                sendMessage.sendNotice("15913118205","亚太基金总裁陈少坤你好", AliMessageContant.NoticeServiceManage);

                return success();
            } else {
                return fail("系统错误!", "500");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return fail("系统错误!", "500");
        }
    }

}
