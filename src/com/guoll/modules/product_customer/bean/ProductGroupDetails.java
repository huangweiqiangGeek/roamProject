package com.guoll.modules.product_customer.bean;

import java.io.Serializable;

public class ProductGroupDetails implements Serializable{

	private Integer id;
	private Integer group_id;//产品组ID
	private String business_type;//业务类型
	private String business_type_name;//业务类型名
	private byte[] products_json;//子产品JSON
	private String products_json_str;
	
	
	
	public String getBusiness_type_name() {
		return business_type_name;
	}
	public void setBusiness_type_name(String business_type_name) {
		this.business_type_name = business_type_name;
	}
	public String getProducts_json_str() {
		return products_json_str;
	}
	public void setProducts_json_str(String products_json_str) {
		this.products_json_str = products_json_str;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}
	public String getBusiness_type() {
		return business_type;
	}
	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}
	public byte[] getProducts_json() {
		return products_json;
	}
	public void setProducts_json(byte[] products_json) {
		this.products_json = products_json;
	}
	
	
}
