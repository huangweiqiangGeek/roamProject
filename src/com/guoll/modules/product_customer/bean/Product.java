package com.guoll.modules.product_customer.bean;

import com.guoll.modules.framework.base.BaseModel;

/**
 * @author htnm
 */
public class Product extends BaseModel{
	private Integer id;//数据库id
	private Integer productType;//产品大类型，0父产品   1语音，2流量
	private String productId;//省侧产品id
	private String productName;//产品名
	private String productDescribe;//产品描述
	private String provinceSpell;
	private String resourcesQuantity;
	private Integer chargeType;//套餐类型
	private Double charge1;
	private Double charge2;
	private Double charge3;
	private Double charge4;
	private String tScope;
	private String pScope;
	private String postName;
	private Integer isChild	;//1 不区分,2子产品,3父产品,如果是2必须填写父产品id
	private	String parentsId;//父产品id;如果isChild为子产品，此字段需要填写
	
	public String getParentsId() {
		return parentsId;
	}
	public void setParentsId(String parentsId) {
		this.parentsId = parentsId;
	}
	public Integer getIsChild() {
		return isChild;
	}
	public void setIsChild(Integer isChild) {
		this.isChild = isChild;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductType() {
		return productType;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescribe() {
		return productDescribe;
	}
	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}
	public String getProvinceSpell() {
		return provinceSpell;
	}
	public void setProvinceSpell(String provinceSpell) {
		this.provinceSpell = provinceSpell;
	}
	public String getResourcesQuantity() {
		return resourcesQuantity;
	}
	public void setResourcesQuantity(String resourcesQuantity) {
		this.resourcesQuantity = resourcesQuantity;
	}
	public Integer getChargeType() {
		return chargeType;
	}
	public void setChargeType(Integer chargeType) {
		this.chargeType = chargeType;
	}
	public Double getCharge1() {
		return charge1;
	}
	public void setCharge1(Double charge1) {
		this.charge1 = charge1;
	}
	public Double getCharge2() {
		return charge2;
	}
	public void setCharge2(Double charge2) {
		this.charge2 = charge2;
	}
	public Double getCharge3() {
		return charge3;
	}
	public void setCharge3(Double charge3) {
		this.charge3 = charge3;
	}
	public Double getCharge4() {
		return charge4;
	}
	public void setCharge4(Double charge4) {
		this.charge4 = charge4;
	}
	public String gettScope() {
		return tScope;
	}
	public void settScope(String tScope) {
		this.tScope = tScope;
	}
	public String getpScope() {
		return pScope;
	}
	public void setpScope(String pScope) {
		this.pScope = pScope;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productType=" + productType + ", productId=" + productId + ", productName="
				+ productName + ", productDescribe=" + productDescribe + ", provinceSpell=" + provinceSpell
				+ ", resourcesQuantity=" + resourcesQuantity + ", chargeType=" + chargeType + ", charge1=" + charge1
				+ ", charge2=" + charge2 + ", charge3=" + charge3 + ", charge4=" + charge4 + ", tScope=" + tScope
				+ ", pScope=" + pScope + ", postName=" + postName + ", isChild=" + isChild + ", parentsId=" + parentsId
				+ "]";
	}
	
}
