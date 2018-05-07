package com.guoll.modules.product_customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoll.modules.commType.bean.CommType;
import com.guoll.modules.commType.service.CommTypeService;
import com.guoll.modules.framework.base.BaseController;
import com.guoll.modules.product_customer.bean.Product;
import com.guoll.modules.product_customer.service.ProductService;
import com.guoll.modules.sysmanage.bean.SysUser;

import sun.security.krb5.internal.PAData;

/**
 * 
 * @author htnm
 *
 */
@Controller
@RequestMapping("/productController")
public class ProductController extends BaseController{
	@Autowired(required = false)
	private ProductService productService;
	@Autowired(required = false)
	private CommTypeService commTypeservice;
	/**
	 * 跳转到产品列表页面
	 */
	@RequestMapping("/toProductList")
	public String toProductList(){
		return "product/list";
	}
	
	/**
	 * 获取产品列表数据
	 */
	@RequestMapping("/getProductList")
	@ResponseBody
	public Map<String, Object> getProductList(HttpServletRequest request, Product product) {
		Map<String, Object> pageData = new HashMap<>();
		if (product.getProvinceSpell() == null || product.getProvinceSpell().trim().equals("")) {
			SysUser user = getSessionUser(request);
			if (user.getOrg_id() != 4) {//不是超级管理员
				product.setProvinceSpell(user.getProvinceSpell());
			}
		}
		List<Product> list = productService.queryProductByModel(product);
		Integer count = productService.getCountOfProduct(product);
		pageData.put("total", count);
		pageData.put("rows", list);
		return pageData;
	}
	
	/**
	 * 跳转到新增页面
	 */
	@RequestMapping("/toAddNew")
	public String toAddNew(HttpServletRequest request){
		CommType c = new CommType();
		c.setAffiliationType(9);
		List<CommType> queryCommTypeList = commTypeservice.queryCommTypeList(c);
		request.setAttribute("chargeTypes", queryCommTypeList);
		return "product/edit";
	}
	
	/**
	 * 保存新增或修改
	 */
	@RequestMapping("save")
	public String save(Product product,HttpServletRequest request) {
		try {
			productService.save(product, getSessionUser(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("close", "close");
		return "homeProject/edit";
	}
	
	/**
	 * 修改初始化修改
	 */
	@RequestMapping("toUpdate")
	public String toUpdate(Integer id,HttpServletRequest request){
		Product product = productService.queryProductById(id);
		request.setAttribute("product", product);
		
		CommType c = new CommType();
		c.setAffiliationType(9);
		List<CommType> queryCommTypeList = commTypeservice.queryCommTypeList(c);
		request.setAttribute("chargeTypes", queryCommTypeList);
		return "product/edit";
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("deleteProductById")
	public void deleteProductById(Integer id,HttpServletRequest request){
		productService.deleteProductById(id);
	}
	
	
}
