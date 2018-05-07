package com.guoll.modules.sysmanage.mapper;

import java.util.List;

import com.guoll.modules.sysmanage.bean.SysProvince;

public interface SysProvinceMapper {
	
	/**
	 * 根据拼写查询省份数据
	 * @param provinceSpell
	 * @return
	 */
	SysProvince findSysProvinceBySpell(String provinceSpell);

	/**
	 * 查询所有的省份列表
	 * @return
	 */
	List<SysProvince> queryAllSysprovinceList();

}
