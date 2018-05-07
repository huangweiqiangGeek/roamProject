package com.guoll.modules.useCase.bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.guoll.modules.framework.base.BaseModel;

public class UseCase extends BaseModel {
	/**
	 * 本用例是否包含免费资源
	 */
	private Integer haveSource;
	public Integer getHaveSource() {
		return haveSource;
	}


	public void setHaveSource(Integer haveSource) {
		this.haveSource = haveSource;
	}
    
	private  Integer group_id;//产品群ID
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public String getAbbreviation() {
		return abbreviation;
	}


	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	private String copy_mark;//复制标记  0 是非复制  1是复制未编辑  2 是已编辑
	private Integer task_group_id;//任务产品组ID
	private String produt_group_name;//产品群的名称
	private  String abbreviation;  //用例编号前缀
	public String getProdut_group_name() {
		return produt_group_name;
	}

	public void setProdut_group_name(String produt_group_name) {
		this.produt_group_name = produt_group_name;
	}

	public String getCopy_mark() {
		return copy_mark;
	}

	public void setCopy_mark(String copy_mark) {
		this.copy_mark = copy_mark;
	}

	public Integer getTask_group_id() {
		return task_group_id;
	}

	public void setTask_group_id(Integer task_group_id) {
		this.task_group_id = task_group_id;
	}

	/**
	 * 主键自增
	 */
	private Integer id;

	/**
	 * 用例归属项目标识
	 */
	private Integer proID;

	/**
	 * 话单归属用户标识
	 */
	private String uCUserID;

	/**
	 * 用例名称
	 */
	private String uCName;

	/**
	 * 用例编号，用户自主录入，推荐规则为：项目编号+场景
	 */
	private String uCNumber;

	/**
	 * 用例使用场景
	 */
	private String uCScene;

	/**
	 * 话单内容
	 */

	@JsonIgnore
	private byte[] uCTicket;

	/**
	 * 初始化累积量
	 */
	@JsonIgnore
	private byte[] uCAccumulate;

	/**
	 * 初始化免费资源
	 */
	@JsonIgnore
	private byte[] uCResource;

	/**
	 * 免费资源预期结果
	 */
	@JsonIgnore
	private byte[] uCExpect;

	/**
	 * 预期累积量
	 */
	@JsonIgnore
	private byte[] uCExpAmount;

	/**
	 * 详单预期结果
	 */
	@JsonIgnore
	private byte[] uCExpDetail;

	/**
	 * 是否通过 1.待执行 2.是 3.否
	 */
	private Integer isPass;

	/**
	 * 执行总次数
	 */
	private Integer executeNum;

	/**
	 * 用例状态,来源通用类型表
	 */
	private Integer uCStauts;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 编辑时间
	 */
	private Date editTime;

	/**
	 * 用例备注
	 */
	private String uCRemark;

	/**
	 * 选择的话单模版名称
	 */
	private String billName;

	/**
	 * 话单类型
	 */
	private int templateType;
	/**
	 * 话单类型
	 */
	private String billType;

	/**
	 * 标准话单名称
	 */
	private String standardBill;
	/**
	 * 预设免费资源中的产品id,如果有多个,为第一个
	 * 
	 * @return
	 */
	private String productId;

	/**
	 * 选择的预设结果模版名称
	 */
	private String resultName;

	private String ticketJson;

	private String accumulateJson;

	private String resourceJson;

	private String expectJson;

	private String expAmountJson;

	private String expDetailJson;

	private String statusName;

	private String passName;

	private Integer proType;
	
	private Integer resultType;
	
	private String billStartTime;
	
	public Integer getResultType() {
		return resultType;
	}

	public void setResultType(Integer resultType) {
		this.resultType = resultType;
	}

	/**
	 * 出错步数，如果等于-1表示没有出错，若为其他数字表示执行该用例时出错的步骤
	 */
	private int exceptionStep = -1;

	/**
	 * 异常描述信息
	 */
	private String exceptionMessage;
	private byte[] exceptionMessageByte;
	/**
	 * 异常原因
	 */
	private String exceptionCause;
	private byte[] exceptionCauseByte;
	/**
	 * 异常路径
	 */
	private String exceptionTrace;
	/**
	 * 异常步骤封装
	 */
	private List<Map<String, Object>> exceptionList;

	/**
	 * 所用的话单模板id
	 * @return
	 */
	//private Integer billTemplateId;
	/**
	 * 所用的话单模板的id
	 * @return
	 */
	private Integer billId;

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public List<Map<String, Object>> getExceptionList() {
		return exceptionList;
	}

	public void setExceptionList(List<Map<String, Object>> exceptionList) {
		this.exceptionList = exceptionList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProID() {
		return proID;
	}

	public void setProID(Integer proID) {
		this.proID = proID;
	}

	public String getBillStartTime() {
		return billStartTime;
	}

	public void setBillStartTime(String billStartTime) {
		this.billStartTime = billStartTime;
	}

	public String getuCUserID() {
		return uCUserID;
	}

	public void setuCUserID(String uCUserID) {
		this.uCUserID = uCUserID;
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

	public String getuCScene() {
		return uCScene;
	}

	public void setuCScene(String uCScene) {
		this.uCScene = uCScene;
	}

	public byte[] getuCTicket() {
		return uCTicket;
	}

	public void setuCTicket(byte[] uCTicket) {
		this.uCTicket = uCTicket;
	}

	public byte[] getuCAccumulate() {
		return uCAccumulate;
	}

	public void setuCAccumulate(byte[] uCAccumulate) {
		this.uCAccumulate = uCAccumulate;
	}

	public byte[] getuCResource() {
		return uCResource;
	}

	public void setuCResource(byte[] uCResource) {
		this.uCResource = uCResource;
	}

	public byte[] getuCExpect() {
		return uCExpect;
	}

	public void setuCExpect(byte[] uCExpect) {
		this.uCExpect = uCExpect;
	}

	public byte[] getuCExpAmount() {
		return uCExpAmount;
	}

	public void setuCExpAmount(byte[] uCExpAmount) {
		this.uCExpAmount = uCExpAmount;
	}

	public byte[] getuCExpDetail() {
		return uCExpDetail;
	}

	public void setuCExpDetail(byte[] uCExpDetail) {
		this.uCExpDetail = uCExpDetail;
	}

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}

	public Integer getExecuteNum() {
		return executeNum;
	}

	public void setExecuteNum(Integer executeNum) {
		this.executeNum = executeNum;
	}

	public Integer getuCStauts() {
		return uCStauts;
	}

	public void setuCStauts(Integer uCStauts) {
		this.uCStauts = uCStauts;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getuCRemark() {
		return uCRemark;
	}

	public void setuCRemark(String uCRemark) {
		this.uCRemark = uCRemark;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public int getTemplateType() {
		return templateType;
	}

	public void setTemplateType(int templateType) {
		this.templateType = templateType;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getStandardBill() {
		return standardBill;
	}

	public void setStandardBill(String standardBill) {
		this.standardBill = standardBill;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

	public String getTicketJson() {
		return ticketJson;
	}

	public void setTicketJson(String ticketJson) {
		this.ticketJson = ticketJson;
	}

	public String getAccumulateJson() {
		return accumulateJson;
	}

	public void setAccumulateJson(String accumulateJson) {
		this.accumulateJson = accumulateJson;
	}

	public String getResourceJson() {
		return resourceJson;
	}

	public void setResourceJson(String resourceJson) {
		this.resourceJson = resourceJson;
	}

	public String getExpectJson() {
		return expectJson;
	}

	public void setExpectJson(String expectJson) {
		this.expectJson = expectJson;
	}

	public String getExpAmountJson() {
		return expAmountJson;
	}

	public void setExpAmountJson(String expAmountJson) {
		this.expAmountJson = expAmountJson;
	}

	public String getExpDetailJson() {
		return expDetailJson;
	}

	public void setExpDetailJson(String expDetailJson) {
		this.expDetailJson = expDetailJson;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getPassName() {
		return passName;
	}

	public void setPassName(String passName) {
		this.passName = passName;
	}

	public Integer getProType() {
		return proType;
	}

	public void setProType(Integer proType) {
		this.proType = proType;
	}

	public int getExceptionStep() {
		return exceptionStep;
	}

	public void setExceptionStep(int exceptionStep) {
		this.exceptionStep = exceptionStep;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public byte[] getExceptionMessageByte() {
		return exceptionMessageByte;
	}

	public void setExceptionMessageByte(byte[] exceptionMessageByte) {
		this.exceptionMessageByte = exceptionMessageByte;
	}

	public String getExceptionCause() {
		return exceptionCause;
	}

	public void setExceptionCause(String exceptionCause) {
		this.exceptionCause = exceptionCause;
	}

	public byte[] getExceptionCauseByte() {
		return exceptionCauseByte;
	}

	public void setExceptionCauseByte(byte[] exceptionCauseByte) {
		this.exceptionCauseByte = exceptionCauseByte;
	}

	public String getExceptionTrace() {
		return exceptionTrace;
	}

	public void setExceptionTrace(String exceptionTrace) {
		this.exceptionTrace = exceptionTrace;
	}

	@Override
	public String toString() {
		return "UseCase [haveSource=" + haveSource + ", group_id=" + group_id + ", copy_mark=" + copy_mark
				+ ", task_group_id=" + task_group_id + ", produt_group_name=" + produt_group_name + ", abbreviation="
				+ abbreviation + ", id=" + id + ", proID=" + proID + ", uCUserID=" + uCUserID + ", uCName=" + uCName
				+ ", uCNumber=" + uCNumber + ", uCScene=" + uCScene + ", uCTicket=" + Arrays.toString(uCTicket)
				+ ", uCAccumulate=" + Arrays.toString(uCAccumulate) + ", uCResource=" + Arrays.toString(uCResource)
				+ ", uCExpect=" + Arrays.toString(uCExpect) + ", uCExpAmount=" + Arrays.toString(uCExpAmount)
				+ ", uCExpDetail=" + Arrays.toString(uCExpDetail) + ", isPass=" + isPass + ", executeNum=" + executeNum
				+ ", uCStauts=" + uCStauts + ", createTime=" + createTime + ", editTime=" + editTime + ", uCRemark="
				+ uCRemark + ", billName=" + billName + ", templateType=" + templateType + ", billType=" + billType
				+ ", standardBill=" + standardBill + ", productId=" + productId + ", resultName=" + resultName
				+ ", ticketJson=" + ticketJson + ", accumulateJson=" + accumulateJson + ", resourceJson=" + resourceJson
				+ ", expectJson=" + expectJson + ", expAmountJson=" + expAmountJson + ", expDetailJson=" + expDetailJson
				+ ", statusName=" + statusName + ", passName=" + passName + ", proType=" + proType + ", resultType="
				+ resultType + ", billStartTime=" + billStartTime + ", exceptionStep=" + exceptionStep
				+ ", exceptionMessage=" + exceptionMessage + ", exceptionMessageByte="
				+ Arrays.toString(exceptionMessageByte) + ", exceptionCause=" + exceptionCause + ", exceptionCauseByte="
				+ Arrays.toString(exceptionCauseByte) + ", exceptionTrace=" + exceptionTrace + ", exceptionList="
				+ exceptionList + ", billId=" + billId + "]";
	}

	public String toString2() {
		return "UseCase [id=" + id + ", proID=" + proID + ", uCUserID=" + uCUserID + ", uCName=" + uCName
				+ ", uCNumber=" + uCNumber + ", uCScene=" + uCScene + "]";
	}

}