package com.common.service.KOLCenter.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.service.KOLCenter.entity.GlobalKOL;
import com.common.service.KOLCenter.mapper.GlobalKOLMapper;
import com.common.service.KOLCenter.service.GlobalKOLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈全球KOL投放业务〉
 *
 * @author HuangQiuRong
 * @create 2019/3/19
 */
@Service
public class GlobalKOLServiceImpl extends ServiceImpl<GlobalKOLMapper,GlobalKOL> implements GlobalKOLService {

    @Autowired
    private GlobalKOLMapper globalKOLMapper;

    @Override
    public Page<GlobalKOL> selectKolPage(Page<GlobalKOL> page, String state) {

        page.setRecords(this.globalKOLMapper.selectKolPage(page,state));
        return page;
    }
}
