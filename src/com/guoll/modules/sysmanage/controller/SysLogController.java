package com.guoll.modules.sysmanage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoll.modules.sysmanage.bean.SysLog;
import com.guoll.modules.sysmanage.service.SysLogService;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

	@Autowired(required=false)
	SysLogService sysLogService;
	
	/**
	 * 日志管理入口
	 * @return
	 */
	@RequestMapping("sysLogManage")
	public String sysLogManage(){
		return "sysmanage/sysLogManage";
	}
	
	/**
	 * 查询操作日志
	 * @return
	 */
	@RequestMapping("/querySysLogList")
	@ResponseBody
	public Map<String,Object> queryLogList(SysLog sysLog){
		Map<String,Object> pageData = new HashMap<String,Object>();
		pageData.put("total", sysLogService.querySysLogSum(sysLog));
		pageData.put("rows", sysLogService.querySysLogList(sysLog));
		return pageData;
	}
}