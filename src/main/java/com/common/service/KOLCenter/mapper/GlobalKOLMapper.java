package com.common.service.KOLCenter.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.service.KOLCenter.entity.GlobalKOL;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HuangQiuRong
 * @create 2019/3/19
 */

public interface GlobalKOLMapper extends BaseMapper<GlobalKOL> {
    List<GlobalKOL> selectKolPage(Page<GlobalKOL> page, @Param("state") String state);
}
