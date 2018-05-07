package com.guoll.modules.product_customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guoll.modules.framework.base.BaseController;
import com.guoll.modules.product_customer.bean.Customer;
import com.guoll.modules.product_customer.service.CustomerService;
import com.guoll.modules.sysmanage.bean.SysUser;
/**
 * 
 * @author htnm
 *
 */
@Controller
@RequestMapping("customerController")
public class CustomerController extends BaseController{
	
	@Autowired(required=false)
	private CustomerService customerServicer;
	/**
	 * 跳转到列表页面
	 */
	@RequestMapping("toCustomerList")
	public String toCustomerList(HttpServletRequest request) {
		SysUser user = getSessionUser(request);
		request.setAttribute("provinceSpell", user.getProvinceSpell());//省份拼写
		return "customer/list";
	}
	
	/**
	 * 获取用户列表数据
	 */
	@RequestMapping("getCustomerList")
	public Map<String, Object>  getCustomerList(HttpServletRequest request,Customer customer){
		try {
			if (customer.getProvinceSpell() == null) {//验证是否有省份信息，若无，设定为登录者的省份信息
				SysUser sessionUser = getSessionUser(request);
				customer.setProvinceSpell(sessionUser.getProvinceSpell());
				customer.setPostName(sessionUser.getPost_name());
			}
			List<Customer> list = customerServicer.queryCustomersByModel(customer);//查询列表
			Integer count = customerServicer.getCountOfCustomer(customer);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("total", count);// 获取查询总记录条数
			data.put("rows", list);// 获取查询的集合
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据id删除用户
	 * @param customer
	 * @return 
	 */
	@RequestMapping("deleteById")
	public Map<String, Object> deleteById(Customer customer){
		Map<String, Object> pageData = new HashMap<>();
		if (customer.getId() == null) {
			pageData.put("success", false);
			pageData.put("msg", "id 不能为空");
		}else{
			customerServicer.deleteCustomerById(customer);
			pageData.put("success", true);
		}
		return pageData;
	}
	
	/**
	 * 跳转到编辑页面
	 */
	@RequestMapping()
	public String toEdit(HttpServletRequest request,Customer customer) {
		if (customer.getId() == null) {//新增
			
		}else{
			Customer customer2 = customerServicer.queryCustomerById(customer);
			request.setAttribute("customer", customer2);
		}
		return "customer/edit";
		
	}
}
