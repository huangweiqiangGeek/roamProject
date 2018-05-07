package com.guoll.modules.product_customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoll.modules.product_customer.bean.Customer;
import com.guoll.modules.product_customer.bean.Product;
import com.guoll.modules.product_customer.bean.ProductGroup;
import com.guoll.modules.product_customer.mapper.CustomerMapper;
import com.guoll.modules.product_customer.mapper.ProductGroupMapper;
import com.guoll.modules.sysmanage.bean.SysUser;

/**
 * 
 * @author htnm
 *
 */
@Service
public class CustomerService {
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private ProductGroupMapper productGroupMapper;
	/**
	 * 保存新增或修改
	 * @param customer
	 * @param sysUser
	 */
	public void save(Customer customer,SysUser sysUser){
		if (customer.getId() == null) {
			customer.setProvinceSpell(sysUser.getProvinceSpell());
			customer.setPostName(sysUser.getPost_name());
			ProductGroup pg = new ProductGroup();
			pg.setProductsMark(customer.getProductsMark());
			customer.setProductsMark(pg.getProductsMark());
			customerMapper.addNewCustomer(customer);
		}else {
			ProductGroup pg = new ProductGroup();
			pg.setProductsMark(customer.getProductsMark());
			customer.setProductsMark(pg.getProductsMark());
			customerMapper.addNewCustomer(customer);
		}
	}
	
	/**
	 * 根据id删除
	 * @param customer
	 */
	public void deleteCustomerById(Customer customer) {
		customerMapper.deleteCustomerById(customer);
	}
	/**
	 * 根据id删除
	 */
	public void deleteCustomerById(Integer customerId) {
		Customer customer = new Customer();
		customer.setId(customerId);
		customerMapper.deleteCustomerById(customer);
	}
	
	/**
	 * 根据id查询
	 */
	public Customer queryCustomerById(Integer customerId){
		Customer c = new Customer();
		c.setId(customerId);
		List<Customer> list = customerMapper.queryCustomerListByModel(c);
		if (list.size() > 0) {
			Customer customer = list.get(0);
			addProductForCustomer(customer);
			return customer;
		}else {
			return null;
		}
	}
	/**
	 * 根据id查询
	 */
	public Customer queryCustomerById(Customer c){
		List<Customer> list = customerMapper.queryCustomerListByModel(c);
		if (list.size() > 0) {
			Customer customer = list.get(0);
			addProductForCustomer(customer);
			return customer;
		}else {
			return null;
		}
	}
	/**
	 * 根据模型查询
	 */
	public List<Customer> queryCustomersByModel(Customer c){
		List<Customer> list = customerMapper.queryCustomerListByModel(c);
		for (Customer customer : list) {
			addProductForCustomer(customer);
		}
		return list;
	}

	/**
	 * 根据模型查询
	 */
	public Integer getCountOfCustomer(Customer customer) {
		Integer count = customerMapper.queryCountOfCustomer(customer);
		return count;
	}
	
	
	private void addProductForCustomer(Customer customer) {
		ProductGroup pg = new ProductGroup();
		pg.setProvinceSpell(customer.getProvinceSpell());
		pg.setProductsMark(customer.getProductsMark());
		List<ProductGroup> list = productGroupMapper.queryProductGroupByList(pg);
		ProductGroup group = list.get(0);
		List<Product> l = productGroupMapper.queryProductListOfGroupByGroupId(group.getId());
		customer.setProducts(l);
	}
}
