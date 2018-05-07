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
import com.guoll.modules.sysmanage.bean.SysRole;
import com.guoll.modules.sysmanage.bean.SysUser;
import com.guoll.modules.sysmanage.service.SysRoleService;

@Controller
@RequestMapping("/sysRole")
public class SysRoleController {

	@Autowired(required=false)
	SysRoleService sysRoleService;
	
	/**
	 * 角色管理入口
	 * @return
	 */
	@RequestMapping("sysRoleManage")
	public String sysRoleManage(){
		return "sysmanage/sysRoleManage";
	}
	
	/**
	 * 查询角色列表
	 * @return
	 */
	@RequestMapping("/querySysRoleList")
	@ResponseBody
	public Map<String,Object> querySysRoleList(SysRole sysRole){
		Map<String,Object> pageData = new HashMap<String,Object>();
		
		sysRole.setPages();//设置分页信息
		
		pageData.put("total", sysRoleService.querySysRoleSum(sysRole));
		pageData.put("rows", sysRoleService.querySysRoleList(sysRole));
		return pageData;
	}
	
	/**
	 * 新增/修改角色
	 * @return
	 */
	@RequestMapping("/addOrUpdateSysRole")
	public String addOrUpdateSysRole(Integer id,HttpServletRequest request){
		if(id!=null){
			SysRole sysRole = sysRoleService.querySysRoleById(id);
			request.setAttribute("sysRole", sysRole);
		}
		return "sysmanage/addOrUpdateSysRole";
	}
	
	/**
	 * 新增/修改角色
	 * @return
	 */
	@RequestMapping("/deleteSysRole")
	@ResponseBody
	public void deleteSysRole(SysRole sysRole,HttpServletRequest request){
		sysRoleService.deleteSysRole(sysRole);
	}
	
	/**
	 * 保存新增/修改角色
	 * @return
	 */
	@RequestMapping("/saveSysRole")
	public String saveSysRole(SysRole sysRole,HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		sysRole.setUpdate_user_id(sysUser.getId());
		sysRole.setUpdate_user_name(sysUser.getCn_name());
		sysRole.setUpdate_time(new Date());
		sysRoleService.saveSysRole(sysRole);
		request.setAttribute("close", "close");
		return "sysmanage/addOrUpdateSysRole";
	}
	
	/**
	 * 角色菜单管理入口
	 * @return
	 */
	@RequestMapping("/sysRoleMenuCheck")
	public String sysRoleMenuCheck(HttpServletRequest request){
		
		String role_id = request.getParameter("id");
		if(StringUtils.isNotEmpty(role_id)){
			SysRole sysRole = new SysRole();
			sysRole.setId(Integer.parseInt(role_id));
			request.setAttribute("menu_ids", sysRoleService.queryMenuIdsByRoleId(sysRole));
		}
		request.setAttribute("role_id",role_id );
		return "sysmanage/sysRoleMenuCheck";
	}
	
	/**
	 * 角色菜单管理入口
	 * @return
	 */
	@RequestMapping("/saveSysRoleMenu")
	@ResponseBody
	public void saveSysRoleMenu(SysRole sysRole){
		sysRoleService.saveRoleMenu(sysRole);
	}
	
	/**
	 * 人员角色列表
	 * @return
	 */
	@RequestMapping("/querySysUserRoleList")
	@ResponseBody
	public List<SysRole> querySysUserRoleList(SysRole sysRole){
		return sysRoleService.querySysRoleList(sysRole);
	}
}