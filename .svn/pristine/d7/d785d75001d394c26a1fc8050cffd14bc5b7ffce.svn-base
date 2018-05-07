package com.guoll.modules.sysmanage.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoll.modules.framework.Constants;
import com.guoll.modules.framework.util.SessionUtils;
import com.guoll.modules.sysmanage.bean.SysOrg;
import com.guoll.modules.sysmanage.bean.SysUser;
import com.guoll.modules.sysmanage.service.SysOrgService;

@Controller
@RequestMapping("/sysOrg")
public class SysOrgController {

	@Autowired(required=false)
	SysOrgService sysOrgService;
	
	/**
	 * 组织管理入口
	 * @return
	 */
	@RequestMapping("/sysOrgManage")
	public String sysOrgManage(){
		return "sysmanage/sysOrgManage";
	}
	
	/**
	 * 查询组织机构列表
	 * @return
	 */
	@RequestMapping("/querySysOrgList")
	@ResponseBody
	public List<SysOrg> querySysOrgList(){
		
		 List<SysOrg> list = sysOrgService.queryBeanList(Constants.root_org_id);
		 System.out.println(list);
		 return list;
	}
	
	/**
	 * 添加组织
	 * @return
	 */
	@RequestMapping("/addSysOrg")
	@ResponseBody
	public void addSysOrg(SysOrg sysOrg,HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		sysOrg.setUpdate_user_id(sysUser.getId());
		sysOrg.setUpdate_user_name(sysUser.getCn_name());
		sysOrg.setUpdate_time(new Date());
		sysOrgService.addSysOrg(sysOrg);
	}
	
	/**
	 * 修改组织信息
	 * @return
	 */
	@RequestMapping("/updateSysOrg")
	@ResponseBody
	public void updateSysOrg(SysOrg sysOrg,HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		sysOrg.setUpdate_user_id(sysUser.getId());
		sysOrg.setUpdate_user_name(sysUser.getCn_name());
		sysOrg.setUpdate_time(new Date());
		sysOrgService.updateSysOrg(sysOrg);
	}
	
	/**
	 * 重命名组织
	 * @return
	 */
	@RequestMapping("/renameSysOrg")
	@ResponseBody
	public void renameSysOrg(SysOrg sysOrg,HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		sysOrg.setUpdate_user_id(sysUser.getId());
		sysOrg.setUpdate_user_name(sysUser.getCn_name());
		sysOrg.setUpdate_time(new Date());
		sysOrgService.updateSysOrgName(sysOrg);
	}
	
	/**
	 * 删除组织
	 * @return
	 */
	@RequestMapping("/deleteSysOrg")
	@ResponseBody
	public void deleteSysOrg(SysOrg sysOrg,HttpServletRequest request){
		sysOrgService.deleteSysOrg(sysOrg);
	}
	
	/**
	 * 删除组织
	 * @return
	 */
	@RequestMapping("/dragSysOrg")
	@ResponseBody
	public void dragSysOrg(SysOrg sysOrg,HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		sysOrg.setUpdate_user_id(sysUser.getId());
		sysOrg.setUpdate_user_name(sysUser.getCn_name());
		sysOrg.setUpdate_time(new Date());
		sysOrgService.dragSysOrg(sysOrg);
	}
}