package com.common.service.KOLCenter.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.common.service.KOLCenter.entity.GlobalKOL;

/**
 * @author HuangQiuRong
 * @create 2019/3/19
 */

public interface GlobalKOLService extends IService<GlobalKOL> {
    Page<GlobalKOL> selectKolPage(Page<GlobalKOL> objectPage, String state);
}
