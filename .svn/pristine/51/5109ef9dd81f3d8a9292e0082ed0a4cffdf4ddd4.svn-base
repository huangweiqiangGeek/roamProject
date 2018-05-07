package com.guoll.modules.statistics.bean;

import java.util.Date;
import java.util.List;

import com.guoll.modules.framework.base.BaseModel;

/**
 * 项目执行记录记录数据封装类
 * @author htnm
 *
 */
public class ProjectExecuteTrace extends BaseModel {
	private Integer id;//数据库id
	private Integer proID;//项目id
	private String proName;//项目名称
	private Integer proExecuteBatch;//该项目执行的批次，是该记录记录时该项目第几次执行
	private Date proExecuteDate;//本次执行时间
	
	private Integer allCaseCount;//本次执行时的项目的用例总数
	private List<Integer> allCaseList;//本次执行时项目的用例id集合
	private String allCaseListJson;//本次执行时项目的用例id集合Json字符串
	
	private Integer passCaseCount;//本次执行时的项目中通过的用例的总数
	private List<Integer> passCaseList;//本次执行时项目中通过的用例id集合
	private String passCaseListJson;//本次执行时项目中通过用例id集合json字符串
	
	private Integer failedCaseCount;//本次执行时失敗的用例的數目
	private List<Integer> failedCaseList;//本次执行失败的用例id集合
	private String failedCaseListJson;//本次执行失败的用例id集合json字符串
	
	private Integer disableCaseCount;//本次执行时用例因各种原因报异常致未完成的数量
	private List<Integer> disableCaseList;//本次执行时用例因各种原因报异常致使为完成的用例id集合
	private String disableCaseListJson;//本次执行时用例因各种原因报异常致使为完成的用例id集合json字符串
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public Integer getProID() {
		return proID;
	}
	public void setProID(Integer proID) {
		this.proID = proID;
	}
	public Integer getProExecuteBatch() {
		return proExecuteBatch;
	}
	public void setProExecuteBatch(Integer proExecuteBatch) {
		this.proExecuteBatch = proExecuteBatch;
	}
	public Date getProExecuteDate() {
		return proExecuteDate;
	}
	public void setProExecuteDate(Date proExecuteDate) {
		this.proExecuteDate = proExecuteDate;
	}
	public Integer getAllCaseCount() {
		return allCaseCount;
	}
	public void setAllCaseCount(Integer allCaseCount) {
		this.allCaseCount = allCaseCount;
	}
	public List<Integer> getAllCaseList() {
		return allCaseList;
	}
	public void setAllCaseList(List<Integer> allCaseList) {
		this.allCaseList = allCaseList;
	}
	public String getAllCaseListJson() {
		return allCaseListJson;
	}
	public void setAllCaseListJson(String allCaseListJson) {
		this.allCaseListJson = allCaseListJson;
	}
	public Integer getPassCaseCount() {
		return passCaseCount;
	}
	public void setPassCaseCount(Integer passCaseCount) {
		this.passCaseCount = passCaseCount;
	}
	public List<Integer> getPassCaseList() {
		return passCaseList;
	}
	public void setPassCaseList(List<Integer> passCaseList) {
		this.passCaseList = passCaseList;
	}
	public String getPassCaseListJson() {
		return passCaseListJson;
	}
	public void setPassCaseListJson(String passCaseListJson) {
		this.passCaseListJson = passCaseListJson;
	}
	public Integer getFailedCaseCount() {
		return failedCaseCount;
	}
	public void setFailedCaseCount(Integer failedCaseCount) {
		this.failedCaseCount = failedCaseCount;
	}
	public List<Integer> getFailedCaseList() {
		return failedCaseList;
	}
	public void setFailedCaseList(List<Integer> failedCaseList) {
		this.failedCaseList = failedCaseList;
	}
	public String getFailedCaseListJson() {
		return failedCaseListJson;
	}
	public void setFailedCaseListJson(String failedCaseListJson) {
		this.failedCaseListJson = failedCaseListJson;
	}
	public Integer getDisableCaseCount() {
		return disableCaseCount;
	}
	public void setDisableCaseCount(Integer disableCaseCount) {
		this.disableCaseCount = disableCaseCount;
	}
	public List<Integer> getDisableCaseList() {
		return disableCaseList;
	}
	public void setDisableCaseList(List<Integer> disableCaseList) {
		this.disableCaseList = disableCaseList;
	}
	public String getDisableCaseListJson() {
		return disableCaseListJson;
	}
	public void setDisableCaseListJson(String disableCaseListJson) {
		this.disableCaseListJson = disableCaseListJson;
	}
	@Override
	public String toString() {
		return "ProjectExecuteTrace [id=" + id + ", proID=" + proID + ", proExecuteBatch=" + proExecuteBatch
				+ ", proExecuteDate=" + proExecuteDate + ", allCaseCount=" + allCaseCount + ", allCaseList="
				+ allCaseList + ", passCaseCount=" + passCaseCount + ", passCaseList=" + passCaseList
				+ ", failedCaseCount=" + failedCaseCount + ", failedCaseList=" + failedCaseList + ", disableCaseCount="
				+ disableCaseCount + ", disableCaseList=" + disableCaseList + "]";
	}
}
