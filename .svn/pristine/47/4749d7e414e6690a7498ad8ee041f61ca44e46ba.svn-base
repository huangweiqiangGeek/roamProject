package com.guoll.modules.billtemplate.bean;

import java.sql.Timestamp;
import java.util.Arrays;

import com.guoll.modules.framework.base.BaseModel;

/**
 * 话单模版类
 * @author lukas 414024003@qq.com
 * @version 1.0
 */
public class BillTemplate extends BaseModel{
	/**
	 * 模板类型 1 语音,2 GPRS,3 短信,4 WLAN
	 */
    private int templateType;
     
    private String templateTypeName;

	/**
	 * 模版标识
	 */
	private Integer id;
	
	private  Integer pid;
	

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	/**
	 * 话单模版名称
	 */
	private String templateName;
	
	/**
	 * 省份标识
	 */
	private int provinceID;
	
	/**
	 * 省份名称
	 */
	private String provinceName;
	
	/**
	 * 模版状态
	 */
	private int templateStatus;
	
	/**
	 * 模版组成内容
	 */
	private byte [] templateAttribute;
	
	private String attrJson;
	
	/**
	 * 模版备注
	 */
	private String templateRemark;
	
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	
	/**
	 * 编辑时间
	 */
	private Timestamp editTime;
	
	private String commTypeName;

	
	private byte[] updateTrace;//修改轨迹
	
	public byte[] getUpdateTrace() {
		return updateTrace;
	}

	public void setUpdateTrace(byte[] updateTrace) {
		this.updateTrace = updateTrace;
	}

	public String getCommTypeName() {
		return commTypeName;
	}

	public void setCommTypeName(String commTypeName) {
		this.commTypeName = commTypeName;
	}

	/**
	 * 获取标识
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置标识
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取模版名称
	 * @return
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * 设置模版名称
	 * @param templateName
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * 获取省份标识
	 * @return
	 */
	public int getProvinceID() {
		return provinceID;
	}

	/**
	 * 设置省份标识
	 * @param provinceID
	 */
	public void setProvinceID(int provinceID) {
		this.provinceID = provinceID;
	}

	/**
	 * 获取省份名称
	 * @return
	 */
	public String getProvinceName() {
		return provinceName;
	}

	/**
	 * 设置省份名称
	 * @param provinceName
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	/**
	 * 获取模版状态
	 * @return
	 */
	public int getTemplateStatus() {
		return templateStatus;
	}

	/**
	 * 设置模版状态
	 * @param templateStatus
	 */
	public void setTemplateStatus(int templateStatus) {
		this.templateStatus = templateStatus;
	}

	/**
	 * 获取模版组成内容
	 * @return
	 */
	public byte [] getTemplateAttribute() {
		return templateAttribute;
	}

	/**
	 * 设置模版组成内容
	 * @param templateAttribute
	 */
	public void setTemplateAttribute(byte [] templateAttribute) {
		this.templateAttribute = templateAttribute;
	}

	
	public String getAttrJson() {
		return attrJson;
	}

	public void setAttrJson(String attrJson) {
		this.attrJson = attrJson;
	}

	/**
	 * 获取模版备注
	 * @return
	 */
	public String getTemplateRemark() {
		return templateRemark;
	}

	/**
	 * 设置模版备注
	 * @param templateRemark
	 */
	public void setTemplateRemark(String templateRemark) {
		this.templateRemark = templateRemark;
	}

	/**
	 * 获取创建时间
	 * @return
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * @param createTime
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取编辑时间
	 * @return
	 */
	public Timestamp getEditTime() {
		return editTime;
	}

	/**
	 * 设置编辑时间
	 * @param editTime
	 */
	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}

	public int getTemplateType() {
		return templateType;
	}
	
	public void setTemplateType(int templateType) {
		if(templateType==1){
			setTemplateTypeName("语音");
		}
		if(templateType==2){
			setTemplateTypeName("GPRS");
		}
		if(templateType==3){
			setTemplateTypeName("短信");
		}
		if(templateType==4){
			setTemplateTypeName("WLAN");
		}
		this.templateType = templateType;
	}
	
	public String getTemplateTypeName() {
		return templateTypeName;
	}
	
	public void setTemplateTypeName(String templateTypeName) {
		this.templateTypeName = templateTypeName;
	}

	@Override
	public String toString() {
		return "BillTemplate [templateType=" + templateType + ", templateTypeName=" + templateTypeName + ", id=" + id
				+ ", pid=" + pid + ", templateName=" + templateName + ", provinceID=" + provinceID + ", provinceName="
				+ provinceName + ", templateStatus=" + templateStatus + ", templateAttribute="
				+ Arrays.toString(templateAttribute) + ", attrJson=" + attrJson + ", templateRemark=" + templateRemark
				+ ", createTime=" + createTime + ", editTime=" + editTime + ", commTypeName=" + commTypeName
				+ ", updateTrace=" + Arrays.toString(updateTrace) + "]";
	}
}
