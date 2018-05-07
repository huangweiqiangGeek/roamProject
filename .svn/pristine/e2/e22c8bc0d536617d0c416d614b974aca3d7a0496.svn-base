package com.guoll.modules.sysmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guoll.modules.framework.page.RowBounsUtil;
import com.guoll.modules.sysmanage.bean.SysLog;
import com.guoll.modules.sysmanage.mapper.SysLogMapper;

@Repository("sysLogService")
public class SysLogService{

	@Autowired
    private SysLogMapper mapper;
	
	public Integer querySysLogSum(SysLog sysLog){
		return mapper.querySysLogSum(sysLog);
	}
	public List<SysLog> querySysLogList(SysLog sysLog){
		return mapper.querySysLogList(sysLog,RowBounsUtil.getRowBounds(sysLog.getPage(), sysLog.getRows()));
	}
}
