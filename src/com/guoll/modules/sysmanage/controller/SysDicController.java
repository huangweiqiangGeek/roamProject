package com.guoll.modules.sysmanage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoll.modules.framework.util.SessionUtils;
import com.guoll.modules.sysmanage.bean.SysDic;
import com.guoll.modules.sysmanage.bean.SysDicItem;
import com.guoll.modules.sysmanage.bean.SysDicType;
import com.guoll.modules.sysmanage.bean.SysUser;
import com.guoll.modules.sysmanage.service.SysDicService;

@Controller
@RequestMapping("/sysDic")
public class SysDicController {

	@Autowired(required=false)
	SysDicService sysDicService;
	
	/**
	 * 字典管理入口
	 * @return
	 */
	@RequestMapping("sysDicManage")
	public String sysDicManage(){
		return "sysmanage/sysDicTypeManage";
	}
	
	/**
	 * 查询字典类型列表
	 * @return
	 */
	@RequestMapping("/querySysDicTypeList")
	@ResponseBody
	public Map<String,Object> querySysDicTypeList(SysDicType sysDicType){
		Map<String,Object> pageData = new HashMap<String,Object>();
		pageData.put("total", sysDicService.querySysDicTypeSum(sysDicType));
		pageData.put("rows", sysDicService.querySysDicTypeList(sysDicType));
		return pageData;
	}
	
	/**
	 * 新增/修改字典类型
	 * @return
	 */
	@RequestMapping("/addOrUpdateSysDicType")
	public String addOrUpdateSysDicType(Integer id,HttpServletRequest request){
		if(id!=null){
			SysDicType sysDicType = sysDicService.querySysDicTypeById(id);
			request.setAttribute("sysDicType", sysDicType);
		}
		return "sysmanage/addOrUpdateSysDicType";
	}
	
	/**
	 * 删除字典类型
	 * @return
	 */
	@RequestMapping("/deleteSysDicType")
	@ResponseBody
	public void deleteSysDicType(SysDicType sysDicType,HttpServletRequest request){
		sysDicService.deleteSysDicType(sysDicType);
	}
	
	/**
	 * 保存新增/修改字典类型
	 * @return
	 */
	@RequestMapping("/saveSysDicType")
	public String saveSysDicType(SysDicType sysDicType,HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		sysDicType.setUpdate_user_id(sysUser.getId());
		sysDicType.setUpdate_user_name(sysUser.getCn_name());
		sysDicType.setUpdate_time(new Date());
		sysDicService.saveSysDicType(sysDicType);
		request.setAttribute("close", "close");
		return "sysmanage/addOrUpdateSysDicType";
	}
	
	/**
	 * 字典明细
	 * @return
	 */
	@RequestMapping("/sysDicItemManage")
	public String sysDicItemManage(SysDicType sysDicType,HttpServletRequest request){
		request.setAttribute("dic_type_id", sysDicType.getId());
		request.setAttribute("code_build_type", sysDicType.getCode_build_type());
		return "sysmanage/sysDicItemManage";
	}
	
	/**
	 * 查询字典明细列表
	 * @return
	 */
	@RequestMapping("/querySysDicItemList")
	@ResponseBody
	public Map<String,Object> querySysDicItemList(SysDicItem sysDicItem){
		Map<String,Object> pageData = new HashMap<String,Object>();
		pageData.put("total", sysDicService.querySysDicItemSum(sysDicItem));
		pageData.put("rows", sysDicService.querySysDicItemList(sysDicItem));
		return pageData;
	}
	
	/**
	 * 新增/修改字典类型
	 * @return
	 */
	@RequestMapping("/addOrUpdateSysDicItem")
	public String addOrUpdateSysDicItem(HttpServletRequest request){
		String id = request.getParameter("id");
		SysDicItem sysDicItem = null;
		if(StringUtils.isNotEmpty(id)){
			sysDicItem = sysDicService.querySysDicItemById(Integer.parseInt(id));
		}else{
			sysDicItem = new SysDicItem();
			sysDicItem.setDic_type_id(Integer.parseInt(request.getParameter("dic_type_id")));
		}
		sysDicItem.setCode_build_type(Integer.parseInt(request.getParameter("code_build_type")));
		request.setAttribute("sysDicItem", sysDicItem);
		return "sysmanage/addOrUpdateSysDicItem";
	}
	
	/**
	 * 保存新增/修改字典明细
	 * @return
	 */
	@RequestMapping("/saveSysDicItem")
	public String saveSysDicItem(SysDicItem sysDicItem,HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		sysDicItem.setUpdate_user_id(sysUser.getId());
		sysDicItem.setUpdate_user_name(sysUser.getCn_name());
		sysDicItem.setUpdate_time(new Date());
		sysDicService.saveSysDicItem(sysDicItem);
		request.setAttribute("close", "close");
		return "sysmanage/addOrUpdateSysDicItem";
	}
	/**
	 * 删除字典明细
	 * @return
	 */
	@RequestMapping("/deleteSysDicItem")
	@ResponseBody
	public void deleteSysDicType(SysDicItem sysDicItem,HttpServletRequest request){
		sysDicService.deleteSysDicItem(sysDicItem);
	}
	/**
	 * 
	 * 查询下来列表
	 *
	 * @param dict_type
	 * @return 描述 
	 *
	 */
	@RequestMapping("/queryDic")
	@ResponseBody
	public List<SysDic> queryDic(Integer dict_type,HttpServletRequest request){
	    System.out.println(dict_type);
	    return sysDicService.queryDic(dict_type);
	}

}