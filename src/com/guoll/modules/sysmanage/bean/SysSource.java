package com.guoll.modules.sysmanage.bean;

import com.guoll.modules.framework.base.BaseModel;

public class SysSource extends BaseModel{

	private Integer id;//主键
	private String code;//资源编码,命名方式：bt_menuName_order既bt_加上菜单名称加上_加上编号
	private String name;//资源名称
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}