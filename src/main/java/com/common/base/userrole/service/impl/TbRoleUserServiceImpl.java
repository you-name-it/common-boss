package com.common.base.userrole.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.base.userrole.entity.TbRoleUser;
import com.common.base.userrole.mapper.TbRoleUserMapper;
import com.common.base.userrole.service.ITbRoleUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色映射表  服务实现类
 * </p>
 *
 * @author xj
 * @since 2016-12-26
 */
@Service
public class TbRoleUserServiceImpl extends ServiceImpl<TbRoleUserMapper, TbRoleUser> implements ITbRoleUserService {
	
}
