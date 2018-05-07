package com.guoll.modules.sysmanage.bean;

import java.util.Date;

import com.guoll.modules.framework.base.BaseModel;

public class SysOrg extends BaseModel{

	private Integer id;//主键
	private String name;//名称
	private String code;//编码
	private String org_type;//组织类型
	private String business_desc;//业务描述
	private String address;//地址
	private String zone_code;//行政区划
	private String telephone;//电话
	private String zipcode;//邮编
	private String email;//电子邮件
	private String leader_name;//领导
	private String leader_mobile;//领导电话
	private String contacter_mobile;//联系人电话
	private String contacter_name;//联系人名称
	private String remark;//备注
	private Integer parent_id;//父ID
	private Integer order_no;//排序号
	private Integer update_user_id;//修改人
	private String update_user_name;//修改人名称
	private Date update_time;//修改时间
	private Integer pId;//父ID
	
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
	public String getOrg_type() {
		return org_type;
	}
	public void setOrg_type(String org_type) {
		this.org_type = org_type;
	}
	public String getBusiness_desc() {
		return business_desc;
	}
	public void setBusiness_desc(String business_desc) {
		this.business_desc = business_desc;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZone_code() {
		return zone_code;
	}
	public void setZone_code(String zone_code) {
		this.zone_code = zone_code;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLeader_name() {
		return leader_name;
	}
	public void setLeader_name(String leader_name) {
		this.leader_name = leader_name;
	}
	public String getLeader_mobile() {
		return leader_mobile;
	}
	public void setLeader_mobile(String leader_mobile) {
		this.leader_mobile = leader_mobile;
	}
	public String getContacter_mobile() {
		return contacter_mobile;
	}
	public void setContacter_mobile(String contacter_mobile) {
		this.contacter_mobile = contacter_mobile;
	}
	public String getContacter_name() {
		return contacter_name;
	}
	public void setContacter_name(String contacter_name) {
		this.contacter_name = contacter_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Integer getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}
	public Integer getUpdate_user_id() {
		return update_user_id;
	}
	public void setUpdate_user_id(Integer update_user_id) {
		this.update_user_id = update_user_id;
	}
	public String getUpdate_user_name() {
		return update_user_name;
	}
	public void setUpdate_user_name(String update_user_name) {
		this.update_user_name = update_user_name;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
}