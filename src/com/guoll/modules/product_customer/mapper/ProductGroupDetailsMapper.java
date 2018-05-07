 package com.guoll.modules.product_customer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guoll.modules.product_customer.bean.ProductGroupDetails;

public interface ProductGroupDetailsMapper {
	
	public int addProductGroupDetails(List<ProductGroupDetails> list);
	
	public List<ProductGroupDetails> queryProductGroupDetails(ProductGroupDetails pgd);

	public int deleteProductGroupDetailsByGroupid(@Param("group_id") Integer group_id);
}
