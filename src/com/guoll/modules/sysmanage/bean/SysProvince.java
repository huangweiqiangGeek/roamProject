package com.guoll.modules.sysmanage.bean;

import com.guoll.modules.framework.base.BaseModel;

public class SysProvince extends BaseModel {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String provinceSpell;
	private String provinceName;
    private String abbreviation;
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvinceSpell() {
		return provinceSpell;
	}

	public void setProvinceSpell(String provinceSpell) {
		this.provinceSpell = provinceSpell;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Override
	public String toString() {
		return "SysProvince [id=" + id + ", provinceSpell=" + provinceSpell + ", provinceName=" + provinceName
				+ ", abbreviation=" + abbreviation + "]";
	}

}
