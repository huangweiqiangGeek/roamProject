package com.guoll.modules.sysmanage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guoll.modules.sysmanage.bean.SysUser;
import com.guoll.modules.sysmanage.mapper.SysUserMapper;

@Repository("passagewayService")
public class PassagewayService{

	@Autowired
    private SysUserMapper userMapper;
	
	public SysUser queryLogin(SysUser sysUser) {
		SysUser sysUser_query = userMapper.queryLogin(sysUser);
		return sysUser_query;
	}
}