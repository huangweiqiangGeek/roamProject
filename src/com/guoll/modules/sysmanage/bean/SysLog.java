package com.guoll.modules.sysmanage.bean;

import com.guoll.modules.framework.base.BaseModel;

public class SysLog extends BaseModel{

	private Integer id;
	private Integer user_id;
	private String user_name;
	private String module;
	private String operate;
	private String operate_result;
	private String operate_time;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getOperate_result() {
		return operate_result;
	}
	public void setOperate_result(String operate_result) {
		this.operate_result = operate_result;
	}
	public String getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(String operate_time) {
		this.operate_time = operate_time;
	}
}
