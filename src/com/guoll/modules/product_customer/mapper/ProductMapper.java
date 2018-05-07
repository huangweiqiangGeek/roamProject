package com.guoll.modules.product_customer.mapper;

import java.util.List;

import com.guoll.modules.product_customer.bean.Product;

/**
 * 
 * @author htnm
 *
 */
public interface ProductMapper {

	void addNewProduct(Product product);//新增

	void updateProduct(Product product);//修改
	
	void deleteProductById(Product product);//根据id删除
	
	List<Product> selectProducts(Product product);//根据模型查询

	Integer queryCountOfProduct(Product product);//根据模型查询数量
}
