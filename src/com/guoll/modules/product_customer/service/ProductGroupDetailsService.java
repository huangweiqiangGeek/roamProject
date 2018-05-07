package com.guoll.modules.product_customer.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.guoll.modules.product_customer.bean.ProductGroupDetails;
import com.guoll.modules.product_customer.mapper.ProductGroupDetailsMapper;
@Repository("productGroupDetailsService")
public class ProductGroupDetailsService {
	@Autowired
	ProductGroupDetailsMapper mapper;

	public int addProductGroupDetails(List<ProductGroupDetails> list){
		return mapper.addProductGroupDetails(list);
	}
	
	public List<ProductGroupDetails> queryProductGroupDetails(ProductGroupDetails pgd){
		return mapper.queryProductGroupDetails(pgd);
	}
	
	public int deleteProductGroupDetailsByGroupid(Integer group_id){
		return mapper.deleteProductGroupDetailsByGroupid(group_id);
	}
}
