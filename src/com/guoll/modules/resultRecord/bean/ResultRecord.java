package com.guoll.modules.resultRecord.bean;

import java.util.Arrays;
import java.util.Date;

import com.guoll.modules.framework.base.BaseModel;

public class ResultRecord extends BaseModel {
	private Integer id;

	/**
	 * 结果类型 1.项目执行结果 2.用例执行结果
	 */
	private Integer resultType;

	/**
	 * 项目标识
	 */
	private Integer proID;

	/**
	 * 用例标识
	 */
	private Integer uCID;

	/**
	 * 是该用例的累计第几次执行
	 */
	private Integer uCItemNumber;

	/**
	 * 免费资源结果
	 */
	private byte[] resultResource;

	/**
	 * 累积量结果
	 */
	private byte[] resultTotal;

	/**
	 * 详单结果
	 */
	private byte[] resultDetail;

	/**
	 * 是否通过 0.是 1.否
	 */
	private Integer isPass;

	/**
	 * 执行时间
	 */
	private Date executeTime;

	/**
	 * 
	 */
	private String uCName;

	private String uCNumber;

	private String proName;

	private String proNumber;
	
	/**
	 * 用户标志
	 */
	private String uCUserID;
	/**
	 * 预期免费资源产品id,如有多个,为第一个的
	 */
	private String productId;
	private Integer proExecuteBatch;//如果本次是项目执行，对应的项目执行的批次
	
	public Integer getProExecuteBatch() {
		return proExecuteBatch;
	}

	public void setProExecuteBatch(Integer proExecuteBatch) {
		this.proExecuteBatch = proExecuteBatch;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getResultType() {
		return resultType;
	}

	public void setResultType(Integer resultType) {
		this.resultType = resultType;
	}

	public Integer getProID() {
		return proID;
	}

	public void setProID(Integer proID) {
		this.proID = proID;
	}

	public Integer getuCID() {
		return uCID;
	}

	public void setuCID(Integer uCID) {
		this.uCID = uCID;
	}

	public Integer getuCItemNumber() {
		return uCItemNumber;
	}

	public void setuCItemNumber(Integer uCItemNumber) {
		this.uCItemNumber = uCItemNumber;
	}

	public byte[] getResultResource() {
		return resultResource;
	}

	public void setResultResource(byte[] resultResource) {
		this.resultResource = resultResource;
	}

	public String getuCUserID() {
		return uCUserID;
	}

	public void setuCUserID(String uCUserID) {
		this.uCUserID = uCUserID;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public byte[] getResultTotal() {
		return resultTotal;
	}

	public void setResultTotal(byte[] resultTotal) {
		this.resultTotal = resultTotal;
	}

	public byte[] getResultDetail() {
		return resultDetail;
	}

	public void setResultDetail(byte[] resultDetail) {
		this.resultDetail = resultDetail;
	}

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public String getuCName() {
		return uCName;
	}

	public void setuCName(String uCName) {
		this.uCName = uCName;
	}

	public String getuCNumber() {
		return uCNumber;
	}

	public void setuCNumber(String uCNumber) {
		this.uCNumber = uCNumber;
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

	@Override
	public String toString() {
		return "ResultRecord [id=" + id + ", resultType=" + resultType + ", proID=" + proID + ", uCID=" + uCID
				+ ", uCItemNumber=" + uCItemNumber + ", resultResource=" + Arrays.toString(resultResource)
				+ ", resultTotal=" + Arrays.toString(resultTotal) + ", resultDetail=" + Arrays.toString(resultDetail)
				+ ", isPass=" + isPass + ", executeTime=" + executeTime + ", uCName=" + uCName + ", uCNumber="
				+ uCNumber + ", proName=" + proName + ", proNumber=" + proNumber + "]";
	}

	public String toString2() {
		return "ResultRecord [id=" + id + ", resultType=" + resultType + ", proID=" + proID + ", uCID=" + uCID
				+ ", uCItemNumber=" + uCItemNumber + ", resultResource=" + ", resultTotal=" + ", resultDetail="
				+ ", isPass=" + isPass + ", executeTime=" + executeTime + ", uCName=" + uCName + ", uCNumber="
				+ uCNumber + ", proName=" + proName + ", proNumber=" + proNumber + "]";
	}

}