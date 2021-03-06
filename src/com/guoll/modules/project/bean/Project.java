package com.guoll.modules.project.bean;

import java.sql.Timestamp;

import com.guoll.modules.framework.base.BaseModel;

/**
 * 任务类
 * @author Administrator
 * 2017.8.18版后此级目录改名为任务，新添以目录项目（HomeProject）来管理任务
 */
public class Project extends BaseModel {
    private Integer id;
   
    private Integer pId;
    
	private Integer proType;
    
    private String proName;

    private String proNumber;

    private String proExplain;

    private Integer proStatus;

    private String proProvince;

    private String proProvinceID;

    private Integer useCaseCount;

    private Timestamp createTime;
    
    private Timestamp editTime;

    private String proRemark;
    
    private String statusName;
    
    private String hadExe;
    
    private String successRate;
    
    private Integer proExecuteBatch;//该项目累计执行的批次
    
    private Integer homeProjectId;//项目id，次字段为项目该为任务，添加一个新的项目分级（HomeProject）后添加
    
	public Integer getHomeProjectId() {
		return homeProjectId;
	}

	public void setHomeProjectId(Integer homeProjectId) {
		this.homeProjectId = homeProjectId;
	}
   
	 public Integer getpId() {
			return pId;
		}

		public void setpId(Integer pId) {
			this.pId = pId;
		}
	public Integer getProExecuteBatch() {
		return proExecuteBatch;
	}

	public void setProExecuteBatch(Integer proExecuteBatch) {
		this.proExecuteBatch = proExecuteBatch;
	}

	public String getHadExe() {
		return hadExe;
	}

	public void setHadExe(String hadExe) {
		this.hadExe = hadExe;
	}

	public String getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(String successRate) {
		this.successRate = successRate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProType() {
		return proType;
	}

	public void setProType(Integer proType) {
		this.proType = proType;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProNumber() {
		return proNumber;
	}

	public void setProNumber(String proNumber) {
		this.proNumber = proNumber;
	}


	public Integer getProStatus() {
		return proStatus;
	}

	public void setProStatus(Integer proStatus) {
		this.proStatus = proStatus;
	}

	public String getProProvince() {
		return proProvince;
	}

	public void setProProvince(String proProvince) {
		this.proProvince = proProvince;
	}


	public String getProProvinceID() {
		return proProvinceID;
	}

	public void setProProvinceID(String proProvinceID) {
		this.proProvinceID = proProvinceID;
	}

	public Integer getUseCaseCount() {
		return useCaseCount;
	}

	public void setUseCaseCount(Integer useCaseCount) {
		this.useCaseCount = useCaseCount;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getEditTime() {
		return editTime;
	}

	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}

	public String getProRemark() {
		return proRemark;
	}

	public void setProRemark(String proRemark) {
		this.proRemark = proRemark;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	

	public String getProExplain() {
		return proExplain;
	}

	public void setProExplain(String proExplain) {
		this.proExplain = proExplain;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", pId=" + pId + ", proType=" + proType + ", proName=" + proName + ", proNumber="
				+ proNumber + ", proExplain=" + proExplain + ", proStatus=" + proStatus + ", proProvince=" + proProvince
				+ ", proProvinceID=" + proProvinceID + ", useCaseCount=" + useCaseCount + ", createTime=" + createTime
				+ ", editTime=" + editTime + ", proRemark=" + proRemark + ", statusName=" + statusName + ", hadExe="
				+ hadExe + ", successRate=" + successRate + ", proExecuteBatch=" + proExecuteBatch + ", homeProjectId="
				+ homeProjectId + "]";
	}
    
   
    
}