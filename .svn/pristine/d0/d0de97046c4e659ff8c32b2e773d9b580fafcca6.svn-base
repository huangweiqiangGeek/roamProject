package com.guoll.modules.sysmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoll.modules.sysmanage.bean.SysProvince;
import com.guoll.modules.sysmanage.mapper.SysProvinceMapper;

@Repository("SysProvinceService")
public class SysProvinceService {
	@Autowired
    private SysProvinceMapper mapper;
	
	/**
	 * 根据拼写查询省份数据
	 * @param provinceSpell
	 * @return
	 */
	public SysProvince findSysProvinceBySpell(String provinceSpell){
		SysProvince Sysprovince = mapper.findSysProvinceBySpell(provinceSpell);
		return Sysprovince;
	}

	/**
	 * 查询所有的省份列表
	 * @return
	 */
	public List<SysProvince> queryAllSysprovinceList() {
		return mapper.queryAllSysprovinceList();
	}
}
