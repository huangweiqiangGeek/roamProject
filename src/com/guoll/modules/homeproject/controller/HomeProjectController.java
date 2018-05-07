package com.guoll.modules.homeproject.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoll.modules.framework.base.BaseController;
import com.guoll.modules.homeproject.bean.HomeProject;
import com.guoll.modules.homeproject.service.HomeProjectService;

/**
 * 项目控制类
 * @author htnm
 *
 */
@Controller
@RequestMapping("/homeProject")
public class HomeProjectController extends BaseController {
	
	@Autowired(required=false)
	HomeProjectService homeProjectService;
	
	/**
	 * 跳转到新增页面
	 */
	@RequestMapping("addInit")
	public String addInit(HttpServletRequest request) {
		return "homeProject/edit";
	}
	
	/**
	 * 修改初始化
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("editInit")
	public String editInit(HttpServletRequest request,HomeProject homeProject,HttpServletResponse response)  {
		HomeProject homeProject2 = homeProjectService.queryById(homeProject);
		response.setCharacterEncoding("UTF-8");
		
		request.setAttribute("homeProject", homeProject2);
		Object attribute = request.getAttribute("homeProject");
		return "homeProject/edit";
	}
	/**
	 * 跳转到列表
	 */
	@RequestMapping("listInit")
	public String listInit(HttpServletRequest request) {
		return "homeProject/list";
	}
	
	/**
	 * 分页查询列表
	 */
	@RequestMapping("listPage")
	@ResponseBody
	public Map<String, Object> listPage(HttpServletRequest request,HomeProject c) {
		Map<String, Object> pageData = new HashMap<String, Object>();
		String post_name = getSessionUser(request).getPost_name();
		if(post_name.equals("系统管理员")){
			
		}else {
			c.setPostName(post_name);
		}
		c.setPages();// 设置分页信息
		Integer total = homeProjectService.queryCount(c);
		pageData.put("total", total );
		List<HomeProject> homeProjectList = homeProjectService.queryHomeProjectList(c);
		pageData.put("rows", homeProjectList);
		return pageData;
	}
	
	
	
	/**
	 * 保存新增/修改
	 * @param request
	 * @param homeProject
	 */
	@RequestMapping("saveHomeProject")
	//@ResponseBody
	public String saveHomeProject(HttpServletRequest request,HomeProject homeProject){
		try {
			homeProjectService.save(homeProject,getSessionUser(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("close", "close");
		return "homeProject/edit";
	}
	/**
	 * 根据id删除
	 * @param request
	 * @param homeProject
	 */
	@RequestMapping("deleteHomeProject")
	@ResponseBody
	public void deleteHomeProjectById(HttpServletRequest request,HomeProject homeProject){
		homeProjectService.deleteById(homeProject);
	}
}
