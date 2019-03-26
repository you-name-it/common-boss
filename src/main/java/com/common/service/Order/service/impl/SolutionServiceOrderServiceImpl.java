package com.common.service.Order.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.service.Order.entity.BuyVipOrder;
import com.common.service.Order.mapper.SolutionServiceOrderMapper;
import com.common.service.Order.service.SolutionServiceOrderService;
import org.springframework.stereotype.Service;

/**
 * 〈个人贷款服务〉
 *
 * @author HuangQiuRong
 * @create 2019/3/19
 */
@Service
public class SolutionServiceOrderServiceImpl extends ServiceImpl<SolutionServiceOrderMapper,BuyVipOrder> implements SolutionServiceOrderService {
}
