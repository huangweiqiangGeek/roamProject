package com.guoll.modules.sysmanage.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoll.modules.framework.Constants;
import com.guoll.modules.framework.util.SessionUtils;
import com.guoll.modules.sysmanage.bean.SysMenu;
import com.guoll.modules.sysmanage.bean.SysUser;
import com.guoll.modules.sysmanage.service.SysMenuService;

@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {

	@Autowired(required=false)
	SysMenuService sysMenuService;
	
	/**
	 * 菜单管理入口
	 * @return
	 */
	@RequestMapping("/sysMenuManage")
	public String sysMenuManage(){
		return "sysmanage/sysMenuManage";
	}
	
	/**
	 * 菜单管理入口
	 * @return
	 */
	@RequestMapping("/querySysMenuList")
	@ResponseBody
	public List<SysMenu> querySysMenuList(){
		try {
			return sysMenuService.queryBeanList(Constants.root_menu_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 保存菜单 前台修改 新增 修改名称 移动 等统一后台处理
	 * @return
	 */
	@RequestMapping("/updateSysMenu")
	@ResponseBody
	public void updateSysMenu(SysMenu sysMenu,HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		sysMenu.setUpdate_user_id(sysUser.getId());
		sysMenu.setUpdate_user_name(sysUser.getCn_name());
		sysMenu.setUpdate_time(new Date());
		sysMenuService.updateSysMenu(sysMenu);
	}
	
	/**
	 * 删除菜单
	 * @return
	 */
	@RequestMapping("/deleteSysMenu")
	@ResponseBody
	public void deleteSysMenu(SysMenu sysMenu,HttpServletRequest request){
		sysMenuService.deleteSysMenu(sysMenu);
	}
	
	/**
	 * 删除菜单
	 * @return
	 */
	@RequestMapping("/dragSysMenu")
	@ResponseBody
	public void dragSysMenu(SysMenu sysMenu,HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		sysMenu.setUpdate_user_id(sysUser.getId());
		sysMenu.setUpdate_user_name(sysUser.getCn_name());
		sysMenu.setUpdate_time(new Date());
		sysMenuService.dragSysMenu(sysMenu);
	}
	
	/**
	 * 删除菜单
	 * @return
	 */
	@RequestMapping("/addSysMenu")
	@ResponseBody
	public void addSysMenu(SysMenu sysMenu,HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		sysMenu.setUpdate_user_id(sysUser.getId());
		sysMenu.setUpdate_user_name(sysUser.getCn_name());
		sysMenu.setUpdate_time(new Date());
		sysMenuService.addSysMenu(sysMenu);
	}
	/**
	 * 删除菜单
	 * @return
	 */
	@RequestMapping("/renameSysMenu")
	@ResponseBody
	public void renameSysMenu(SysMenu sysMenu,HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		sysMenu.setUpdate_user_id(sysUser.getId());
		sysMenu.setUpdate_user_name(sysUser.getCn_name());
		sysMenu.setUpdate_time(new Date());
		sysMenuService.renameSysMenu(sysMenu);
	}
	
	/**
	 * 菜单管理入口
	 * @return
	 */
	@RequestMapping("/queryByUserLimitsMenu")
	@ResponseBody
	public List<SysMenu> queryByUserLimitsMenu(HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		SysMenu sysMenu = new SysMenu();
		sysMenu.setUser_id(sysUser.getId());
		sysMenu.setParent_id(Constants.root_menu_id);
		
		try {
			return sysMenuService.queryBeanListByRole(sysMenu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
