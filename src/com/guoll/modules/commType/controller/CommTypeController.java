package com.guoll.modules.commType.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoll.modules.commType.bean.CommType;
import com.guoll.modules.commType.service.CommTypeService;

/**
 * 通用状态\类型管理
 * 
 * @author lukas 414024003@qq.com
 * @Date 2017年3月15日 14:09:47
 * @version 1.0
 */
@Controller
@RequestMapping("/commType")
public class CommTypeController {

	@Autowired(required = false)
	CommTypeService commTypeService;

	/**
	 * 通用状态\类型入口
	 * 
	 * @return
	 */
	@RequestMapping("/listInit")
	public String listInit(HttpServletRequest request) {
		return "commType/list";
	}

	/**
	 * 查询通用状态\类型列表
	 * 
	 * @return
	 */
	@RequestMapping("/listPage")
	@ResponseBody
	public Map<String, Object> listPage(CommType c) {
		Map<String, Object> pageData = new HashMap<String, Object>();
		c.setPages();// 设置分页信息
		pageData.put("total", commTypeService.queryCommTypeSum(c));
		pageData.put("rows", commTypeService.queryCommTypeList(c));
		return pageData;
	}

	/**
	 * 修改通用状态\类型初始化
	 * 
	 * @return
	 */
	@RequestMapping("/updateInit")
	public String updateInit(Integer id, HttpServletRequest request) {
		if (id != null) {
			CommType c = commTypeService.querySysCommTypeById(id);
			request.setAttribute("commType", c);
			return "commType/edit";
		}
		return "commType/list";
	}

	/**
	 * 新增通用状态\类型初始化
	 * 
	 * @return
	 */
	@RequestMapping("/addInit")
	public String addInit(Integer org_id, HttpServletRequest request) {
		return "commType/edit";
	}

	/**
	 * 删除通用状态\类型
	 * 
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(CommType c, HttpServletRequest request) {
		commTypeService.deleteCommType(c);
	}

	/**
	 * 保存通用状态\类型
	 * 
	 * @return
	 */
	@RequestMapping("/save")
	public String save(CommType c, HttpServletRequest request) {
		commTypeService.saveCommType(c);
		request.setAttribute("close", "close");
		return "commType/edit";
	}

}