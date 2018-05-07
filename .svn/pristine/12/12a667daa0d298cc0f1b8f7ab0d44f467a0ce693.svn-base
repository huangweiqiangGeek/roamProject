package com.guoll.modules.sysmanage.bean;

import com.guoll.modules.framework.base.BaseModel;

public class SysMenu extends BaseModel{

	private Integer id;//主键
	private String name;//菜单名称
	private Integer menu_type;//菜单类型0:中间菜单1:叶子菜单
	private Integer parent_id;//父节点id
	private Integer pId;
	private Integer order_no;//排序
	private String href;//链接地址
	private Integer state;
	private Integer user_id;
	private Integer user_first_post_id;
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
	public Integer getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(Integer menu_type) {
		this.menu_type = menu_type;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		if(parent_id==null){
			parent_id=0;
		}
		this.parent_id = parent_id;
		this.pId = parent_id;
	}
	
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public Integer getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getUser_first_post_id() {
		return user_first_post_id;
	}
	public void setUser_first_post_id(Integer user_first_post_id) {
		this.user_first_post_id = user_first_post_id;
	}
}