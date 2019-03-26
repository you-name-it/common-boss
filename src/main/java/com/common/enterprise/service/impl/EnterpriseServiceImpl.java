package com.common.enterprise.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.base.user.entity.User;
import com.common.base.user.service.UserService;
import com.common.enterprise.entity.Company;
import com.common.enterprise.mapper.EnterpriseMapper;
import com.common.enterprise.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈企业服务实现类〉
 *
 * @author HuangQiuRong
 * @create 2019/1/11
 */
@Service
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper,Company> implements EnterpriseService {

    @Autowired
    private UserService userService;

    @Autowired
    private EnterpriseService enterpriseService;


    @Override
    @Transactional
    public Boolean insertAndUpdateUser(Company company, User sessionUser) {
        try{
            boolean result = this.enterpriseService.insert(company);

            if(result){
                User user = new User();
                user.setID(sessionUser.getID());
                user.setCompanyStatus("2");
                user.setCompanyID(company.getID().toString());
                boolean b = this.userService.updateById(user);
                if(b){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return false;
    }
}
