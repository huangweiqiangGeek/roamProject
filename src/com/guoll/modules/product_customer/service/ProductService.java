package com.guoll.modules.product_customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoll.modules.product_customer.bean.Product;
import com.guoll.modules.product_customer.mapper.ProductMapper;
import com.guoll.modules.sysmanage.bean.SysUser;

/**
 * 
 * @author htnm
 *
 */
@Service
public class ProductService {
	@Autowired
	private ProductMapper productMapper;
	
	/**
	 * 保存产品
	 * @param product
	 * @param user
	 */
	public void save(Product product,SysUser user){
		if (product.getId() == null) {//新增
			product.setPostName(user.getPost_name());
			product.setProvinceSpell(user.getSysProvince().getProvinceSpell());
			productMapper.addNewProduct(product);
		}else {//修改
			productMapper.updateProduct(product);
		}
	}
	/**
	 * 通过id删除
	 * @param product
	 */
	public void deleteProductById(Product product){
		productMapper.deleteProductById(product);
	}
	/**
	 * 通过id删除
	 * @param product
	 */
	public void deleteProductById(Integer id){
		Product product = new Product();
		product.setId(id);
		productMapper.deleteProductById(product);
	}
	/**
	 * 通过id查询
	 * @param product
	 */
	public Product queryProductById(Integer id){
		Product product = new Product();
		product.setId(id);
		List<Product> selectProducts = productMapper.selectProducts(product);
		if (selectProducts.size() > 0) {
			return selectProducts.get(0);
		}else {
			return null;
		}
	}
	/**
	 * 通过id查询
	 * @param product
	 */
	public Product queryProductById(Product product){
		List<Product> selectProducts = productMapper.selectProducts(product);
		if (selectProducts.size() > 0) {
			return selectProducts.get(0);
		}else {
			return null;
		}
	}
	/**
	 * 通过模型查询列表
	 * @param product
	 */
	public List<Product> queryProductByModel(Product product){
		List<Product> selectProducts = productMapper.selectProducts(product);
		return selectProducts;
	}
	
	/**
	 * 通过模型查询数量
	 * @param product
	 * @return
	 */
	public Integer getCountOfProduct(Product product) {
		Integer count = productMapper.queryCountOfProduct(product);
		return count;
	}
}
