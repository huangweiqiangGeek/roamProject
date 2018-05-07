package com.guoll.modules.product_customer.bean;

import java.util.List;
import java.util.TreeSet;

import com.guoll.modules.framework.base.BaseModel;

/**
 * 
 * @author htnm
 *
 */
public class ProductGroup extends BaseModel {
	private Integer id;
	private  Integer groupId;//产品组的名称
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	private String name;//组合名，预计自动化时会自动使用产品进行拼接
	private String productsMark;//产品组合中产品的id拼接字符串：id1@id2@id3...
	private String provinceSpell;//省份
	private String postName;//测试岗位
	private TreeSet<String> productIdSet;
	private Integer customerCount;//组合用户数量
	private List<Product> productList;//产品列表
	//private Integer productType;//组的产品类型
	private String phone_number_str;
	
	
	private String groupProductJson;//产品组产品json,字段（productName 产品名，productId产品id）
	private byte[] groupProductJsonBytes;
	
	private String groupNoJson;//电话号码json,字段（phoneNo 号码）
	private byte[] groupNoJsonBytes;
	
//	private String voiceProductJson;// 语音产品json,字段（productName 产品名，productId产品id）
//	private byte[] voiceProductJsonBytes;
//	
//	private String gprsProductJson;//流量产品json,字段（productName 产品名，productId产品id）
//	private byte[] gprsProductJsonByte;
	
	private String business_type;
	

	public String getBusiness_type() {
		return business_type;
	}

	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}

	public String getPhone_number_str() {
		return phone_number_str;
	}

	public void setPhone_number_str(String phone_number_str) {
		this.phone_number_str = phone_number_str;
	}

	public String getGroupProductJson() {
		return groupProductJson;
	}

	public void setGroupProductJson(String groupProductJson) {
		this.groupProductJson = groupProductJson;
	}

	public byte[] getGroupProductJsonBytes() {
		return groupProductJsonBytes;
	}

	public void setGroupProductJsonBytes(byte[] groupProductJsonBytes) {
		this.groupProductJsonBytes = groupProductJsonBytes;
	}

	public String getGroupNoJson() {
		return groupNoJson;
	}

	public void setGroupNoJson(String groupNoJson) {
		this.groupNoJson = groupNoJson;
	}

	public byte[] getGroupNoJsonBytes() {
		return groupNoJsonBytes;
	}

	public void setGroupNoJsonBytes(byte[] groupNoJsonBytes) {
		this.groupNoJsonBytes = groupNoJsonBytes;
	}

//	public String getVoiceProductJson() {
//		return voiceProductJson;
//	}
//
//	public void setVoiceProductJson(String voiceProductJson) {
//		this.voiceProductJson = voiceProductJson;
//	}
//
//	public byte[] getVoiceProductJsonBytes() {
//		return voiceProductJsonBytes;
//	}
//
//	public void setVoiceProductJsonBytes(byte[] voiceProductJsonBytes) {
//		this.voiceProductJsonBytes = voiceProductJsonBytes;
//	}
//
//	public String getGprsProductJson() {
//		return gprsProductJson;
//	}
//
//	public void setGprsProductJson(String gprsProductJson) {
//		this.gprsProductJson = gprsProductJson;
//	}
//
//	public byte[] getGprsProductJsonByte() {
//		return gprsProductJsonByte;
//	}
//
//	public void setGprsProductJsonByte(byte[] gprsProductJsonByte) {
//		this.gprsProductJsonByte = gprsProductJsonByte;
//	}

	/*public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}*/

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Integer getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(Integer customerCount) {
		this.customerCount = customerCount;
	}

	/**
	 * 添加产品id
	 * @param productId
	 */
	public void addProductId(String productId) {
		if(productsMark == null || productsMark.trim().equals("")){
			productsMark = productId;
		}else {
			productsMark = productsMark + "@" + productId;
		}
		if (productIdSet.contains(productId)) {
			return;
		} else {
			productIdSet.add(productId);
		}
	}

	/**
	 * 移除产品id
	 * @param productId
	 */
	public void removeProductId(String productId) {
		productIdSet.remove(productId);
	}

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

	public String getProductsMark() {
		
		return productsMark;
//		if (productIdSet == null) {
//			productIdSet = new TreeSet<String>();
//		}
//		if (productList != null) {
//			for(int i = 0;i < productList.size(); i ++) {
//				addProductId(productList.get(i).getProductId());
//			}
//		}
//		if (productsMark != null && !productsMark.trim().equals("")) {
//			String mark = "";
//			String[] split = productsMark.split("@");
//			for (String oneId : split) {
//				addProductId(oneId);
//			}
//			for(int i = 0 ;i < productIdSet.size();i ++){
//				String first = productIdSet.pollFirst();
//				if (i != 0) {
//					mark = mark + "@" + first;
//				}else {
//					mark = first;
//				}
//			}
//			return mark;
//		}else{
//			if (productIdSet != null && productIdSet.size() > 0) {
//				String mark = "";
//				for(int i = 0 ;i < productIdSet.size();i ++){
//					String first = productIdSet.pollFirst();
//					if (i != 0) {
//						mark = mark + "@" + first;
//					}else {
//						mark = first;
//					}
//				}
//				return mark;
//			}else{
//				return productsMark;
//			}
//		}
	}

	public void setProductsMark(String productsMark) {
		this.productsMark = productsMark;
	}

	public String getProvinceSpell() {
		return provinceSpell;
	}

	public void setProvinceSpell(String provinceSpell) {
		this.provinceSpell = provinceSpell;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public TreeSet<String> getProductIdSet() {
		return productIdSet;
	}

	public void setProductIdSet(TreeSet<String> productIdSet) {
		this.productIdSet = productIdSet;
	}
	
}
