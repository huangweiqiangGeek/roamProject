package com.guoll.modules.sysmanage.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoll.modules.sysmanage.bean.SysProvince;
import com.guoll.modules.sysmanage.service.SysProvinceService;

@Controller
@RequestMapping("/sysProvince")
public class SysProvinceController{
	
	@Autowired(required=false)
	SysProvinceService sysProvinceService;
	
	@RequestMapping("/queryAllSysprovinceList")
	@ResponseBody
	public List<SysProvince> queryAllSysprovinceList(){
		return sysProvinceService.queryAllSysprovinceList();
	}
}