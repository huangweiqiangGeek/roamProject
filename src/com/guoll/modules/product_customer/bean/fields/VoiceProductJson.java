package com.guoll.modules.product_customer.bean.fields;

import java.io.Serializable;

public class VoiceProductJson implements Serializable {

	private String productName;// 产品名
	private String productId;//产品id
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	@Override
	public String toString() {
		return "GroupProductJson [productName=" + productName + ", productId=" + productId + "]";
	}

}
