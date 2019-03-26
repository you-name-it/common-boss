package com.common.service.loans.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.service.loans.entity.PersonalLoans;
import com.common.service.loans.mapper.PersonalLoansMapper;
import com.common.service.loans.service.PersonalLoansService;
import org.springframework.stereotype.Service;

/**
 * 〈个人贷款服务〉
 *
 * @author HuangQiuRong
 * @create 2019/3/19
 */
@Service
public class PersonalLoansServiceImpl extends ServiceImpl<PersonalLoansMapper,PersonalLoans> implements PersonalLoansService {
}
