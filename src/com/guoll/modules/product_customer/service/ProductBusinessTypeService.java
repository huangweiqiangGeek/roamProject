package com.guoll.modules.product_customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoll.modules.product_customer.bean.ProductBusinessType;
import com.guoll.modules.product_customer.mapper.ProductBusinessTypeMapper;

@Service
public class ProductBusinessTypeService {

	@Autowired
	ProductBusinessTypeMapper productBusinessTypeMapper;
	
	public List<ProductBusinessType> queryProductBusinessTypeSelect(){
		return productBusinessTypeMapper.queryProductBusinessTypeSelect();
	}
	public int addProductBusinessType(ProductBusinessType pbt){
		return productBusinessTypeMapper.addProductBusinessType(pbt);
	}
	
	public ProductBusinessType queryProductBusinessByNameOrId(ProductBusinessType pbt){
		return productBusinessTypeMapper.queryProductBusinessByNameOrId(pbt);
	}
}
