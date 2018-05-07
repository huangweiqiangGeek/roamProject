package com.guoll.modules.framework.base;

import java.io.Serializable;
import java.util.Date;

import com.guoll.modules.framework.util.DateUtil;

public class BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer update_user_id;//最近修改人ID
	private String update_user_name;//最近修改人名称(冗余字段)
	private Date update_time;//最近修改时间
	private String update_time_show;//最近维护时间页面显示
	private Integer delete_flag;//删除表示
	private Integer page = 1;
	private Integer rows = 10;
	private String stime;
	private String etime;
	private Integer create_user_id;//创建者ID
	private Date create_time;//创建时间
	private String create_time_show;//创建时间
	private String create_user_name;//创建者
	private Integer parentId;//父类ID
	private Integer appStatusId;//审批状态
	private String appStatus;//审批状态
    private String approval_remark;//审批意见
    private int start = 0;
    private int end = 10;
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
		if(update_time!=null){
			setUpdate_time_show(DateUtil.getFormatFullDate(update_time));
		}
	}
	public String getUpdate_time_show() {
		return update_time_show;
	}
	public void setUpdate_time_show(String update_time_show) {
		this.update_time_show = update_time_show;
	}
	public Integer getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}
	public Integer getPage() {
		setPages();
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		setPages();
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public Integer getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(Integer create_user_id) {
		this.create_user_id = create_user_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
		if(create_time!=null){
			setCreate_time_show(DateUtil.getFormatFullDate(create_time));
		}
	}
	public String getCreate_user_name() {
		return create_user_name;
	}
	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}
	public String getCreate_time_show() {
		return create_time_show;
	}
	public void setCreate_time_show(String create_time_show) {
		this.create_time_show = create_time_show;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getAppStatusId() {
		return appStatusId;
	}
	public void setAppStatusId(Integer appStatusId) {
		this.appStatusId = appStatusId;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getApproval_remark() {
		return approval_remark;
	}
	public void setApproval_remark(String approval_remark) {
		this.approval_remark = approval_remark;
	}
    public void setPages(){
    	start = page * rows -rows;
    	end = page * rows;
    }
	public int getStart() {
		setPages();
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		setPages();
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}

}