package com.guoll.modules.sysmanage.bean;

import com.guoll.modules.framework.base.BaseModel;

public class SysDicItem extends BaseModel{

	private Integer id;//逐渐
	private String name;//字典类型名
	private String code;//字典类型编码
	private Integer dic_type_id;
	private Integer code_build_type;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getDic_type_id() {
		return dic_type_id;
	}
	public void setDic_type_id(Integer dic_type_id) {
		this.dic_type_id = dic_type_id;
	}
	public Integer getCode_build_type() {
		return code_build_type;
	}
	public void setCode_build_type(Integer code_build_type) {
		this.code_build_type = code_build_type;
	}
	
}