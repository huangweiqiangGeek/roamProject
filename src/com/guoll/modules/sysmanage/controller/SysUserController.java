package com.guoll.modules.sysmanage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoll.modules.framework.Constants;
import com.guoll.modules.framework.password.DigestUtils;
import com.guoll.modules.framework.util.SessionUtils;
import com.guoll.modules.sysmanage.bean.SysPost;
import com.guoll.modules.sysmanage.bean.SysUser;
import com.guoll.modules.sysmanage.service.SysUserService;

@Controller
@RequestMapping("/sysUser")
public class SysUserController{

	@Autowired(required=false)
	SysUserService sysUserService;
	
	/**
	 * 人员管理入口
	 * @return
	 */
	@RequestMapping("sysUserManage")
	public String sysUserManage(HttpServletRequest request){
		request.setAttribute("root_org_id", Constants.root_org_id);
		return "sysmanage/sysUserManage";
	}
	
	/**
	 * 查询人员类型列表
	 * @return
	 */
	@RequestMapping("/querySysUserList")
	@ResponseBody
	public Map<String,Object> querySysUserList(SysUser sysUser){
		Map<String,Object> pageData = new HashMap<String,Object>();
		
		sysUser.setPages();//设置分页信息
		
		pageData.put("total", sysUserService.querySysUserSum(sysUser));
		pageData.put("rows", sysUserService.querySysUserList(sysUser));
		return pageData;
	}
	
	/**
	 * 修改人员类型
	 * @return
	 */
	@RequestMapping("/updateSysUser")
	public String updateSysUser(Integer id,HttpServletRequest request){
		if(id!=null){
			SysUser sysUser = sysUserService.querySysUserById(id);
			request.setAttribute("sysUser", sysUser);
			request.setAttribute("org_id", sysUser.getOrg_id());
		}
		
		return "sysmanage/addOrUpdateSysUser";
	}
	/**
	 * 新增人员类型
	 * @return
	 */
	@RequestMapping("/addSysUser")
	public String addSysUser(Integer org_id,HttpServletRequest request){
		request.setAttribute("org_id", org_id);
		SysUser sysUser = new SysUser();
		sysUser.setUser_pass(Constants.START_PASSWORD);
		request.setAttribute("sysUser", sysUser);
		return "sysmanage/addOrUpdateSysUser";
	}
	/**
	 * 删除人员
	 * @return
	 */
	@RequestMapping("/deleteSysUser")
	@ResponseBody
	public void deleteSysUser(SysUser sysUser,HttpServletRequest request){
		sysUserService.deleteSysUser(sysUser);
	}
	
	/**
	 * 保存新增/修改人员类型
	 * @return
	 */
	@RequestMapping("/saveSysUser")
	public String saveSysUser(SysUser sysUser,HttpServletRequest request){
		try {
			SysUser sysUser_ = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
			sysUser.setUpdate_user_id(sysUser_.getId());
			sysUser.setUpdate_user_name(sysUser_.getCn_name());
			sysUser.setUpdate_time(new Date());
			if(sysUser.getUser_pass()==null){
				sysUser.setUser_pass(Constants.START_PASSWORD);
			}
			if(sysUser.getUser_pass().length()!=32){
				sysUser.setUser_pass(DigestUtils.encodeMD5Hex(sysUser.getUser_pass().getBytes()));
			}
			List<SysUser> list = sysUserService.queryUserToOrgId(sysUser);
			if(sysUser.getId()==null){
				if(list.size()>0){
					request.setAttribute("re", "re");
				}else{
					sysUserService.saveSysUser(sysUser);
					request.setAttribute("close", "close");
				}
			}else{

				switch(list.size()){
				    case 1:
				    	SysUser user = list.get(0);
				    	request.setAttribute("re", "re");
				    	if(!user.getId().equals(sysUser.getId()))break;
					case 0:
						sysUserService.saveSysUser(sysUser);
						request.setAttribute("close", "close");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sysmanage/addOrUpdateSysUser";
	}
	/**
	 * 人员岗位列表
	 * @return
	 */
	@RequestMapping("/sysUserPostsPage")
	public String sysUserPostsPage(HttpServletRequest request){
		request.setAttribute("user_id", request.getParameter("user_id"));
		return "sysmanage/sysUserPosts";
	}
	/**
	 * 人员角色列表
	 * @return
	 */
	@RequestMapping("/sysUserRolesPage")
	public String sysUserRolesPage(HttpServletRequest request){
		request.setAttribute("user_id", request.getParameter("user_id"));
		return "sysmanage/sysUserRoles";
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping("/updatePassWord")
	@ResponseBody
	public Integer updatePassWord(SysUser sysUser,HttpServletRequest request){
		SysUser sysUser_ = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		sysUser.setId(sysUser_.getId());
		String pass = sysUser_.getUser_pass();
		String passWord = sysUser.getUser_pass();
		if(pass.length()!=32){
			pass = DigestUtils.encodeMD5Hex(sysUser_.getUser_pass().getBytes());
		}
		if(passWord.length()!=32){
			passWord = DigestUtils.encodeMD5Hex(sysUser.getUser_pass().getBytes());
			
		}
		if(sysUser.getNew_user_pass().length()!=32){
			sysUser.setNew_user_pass(DigestUtils.encodeMD5Hex(sysUser.getNew_user_pass().getBytes()));
		}
		if(pass.equals(passWord)){
			sysUserService.updatePassWord(sysUser);
		}else{
			return 1;
		}
		return 2;
	}

}