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

import com.guoll.modules.framework.Constants;
import com.guoll.modules.framework.util.SessionUtils;
import com.guoll.modules.sysmanage.bean.SysPost;
import com.guoll.modules.sysmanage.bean.SysRole;
import com.guoll.modules.sysmanage.bean.SysUser;
import com.guoll.modules.sysmanage.service.SysPostService;
import com.guoll.modules.sysmanage.service.SysRoleService;
import com.guoll.modules.sysmanage.service.SysUserService;

@Controller
@RequestMapping("/sysPost")
public class SysPostController {

	@Autowired(required=false)
	SysPostService sysPostService;
	
	@Autowired(required=false)
	SysUserService sysUserService;
	
	@Autowired(required=false)
	SysRoleService sysRoleService;
	
	/**
	 * 岗位管理入口
	 * @return
	 */
	@RequestMapping("sysPostManage")
	public String sysPostManage(HttpServletRequest request){
		request.setAttribute("root_org_id", Constants.root_org_id);
		return "sysmanage/sysPostManage";
	}
	
	/**
	 * 查询岗位列表
	 * @return
	 */
	@RequestMapping("/querySysPostList")
	@ResponseBody
	public Map<String,Object> querySysPostList(SysPost sysPost){
		Map<String,Object> pageData = new HashMap<String,Object>();
		pageData.put("total", sysPostService.querySysPostSum(sysPost));
		pageData.put("rows", sysPostService.querySysPostList(sysPost));
		return pageData;
	}
	
	/**
	 * 新增/修改岗位
	 * @return
	 */
	@RequestMapping("/addOrUpdateSysPost")
	public String addOrUpdateSysPost(HttpServletRequest request){
		String id =request.getParameter("id");
		if(StringUtils.isNotEmpty(id)){
			request.setAttribute("sysPost", sysPostService.querySysPostById(Integer.parseInt(id)));
		}else{
			request.setAttribute("org_id",request.getParameter("org_id"));
		}
		return "sysmanage/addOrUpdateSysPost";
	}
	
	/**
	 * 新增/修改岗位
	 * @return
	 */
	@RequestMapping("/deleteSysPost")
	@ResponseBody
	public void deleteSysPost(SysPost sysPost,HttpServletRequest request){
		sysPostService.deleteSysPost(sysPost);
	}
	
	/**
	 * 保存新增/修改岗位
	 * @return
	 */
	@RequestMapping("/saveSysPost")
	public String saveSysPost(SysPost sysPost,HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		sysPost.setUpdate_user_id(sysUser.getId());
		sysPost.setUpdate_user_name(sysUser.getCn_name());
		sysPost.setUpdate_time(new Date());

		List<SysPost> list = sysPostService.queryPostToOrgId(sysPost);
		if(sysPost.getId()==null){
			if(list.size()>0){
				request.setAttribute("re", "re");
			}else{
				sysPostService.saveSysPost(sysPost);
				request.setAttribute("close", "close");
			}
		}else{

			switch(list.size()){
			    case 1:
			    	SysPost post = list.get(0);
			    	if(!post.getId().equals(sysPost.getId())){
			    		request.setAttribute("re", "re");
			    		break;
			    	}
			    	
				case 0:
					sysPostService.saveSysPost(sysPost);
					request.setAttribute("close", "close");
			}
		}
		return "sysmanage/addOrUpdateSysPost";
	}
	/*********************************岗位人员管理部分***************************************************/
	/**
	 * 岗位人员管理
	 * @return
	 */
	@RequestMapping("/postUserManage")
	public String postUserManage(HttpServletRequest request){
		request.setAttribute("post_id", request.getParameter("id"));
		return "sysmanage/sysPostUserManage";
	}
	/**
	 * 岗位已选人员
	 * @return
	 */
	@RequestMapping("/sysPostUserInclude")
	public String sysPostUserInclude(HttpServletRequest request){
		request.setAttribute("post_id", request.getParameter("post_id"));
		return "sysmanage/sysPostUserInclude";
	}
	
	/**
	 * 岗位已选人员
	 * @return
	 */
	@RequestMapping("/querySysPostUserIncludeList")
	@ResponseBody
	public Map<String,Object> querySysPostUserIncludeList(SysUser sysUser){
		Map<String,Object> pageData = new HashMap<String,Object>();
		pageData.put("total", sysUserService.querySysPostUserIncludeSum(sysUser));
		pageData.put("rows", sysUserService.querySysPostUserIncludeList(sysUser));
		return pageData;
	}
	/**
	 * 岗位已选人员
	 * @return
	 */
	@RequestMapping("/sysPostUserUnclude")
	public String sysPostUserUnclude(HttpServletRequest request){
		request.setAttribute("post_id", request.getParameter("post_id"));
		return "sysmanage/sysPostUserUnclude";
	}
	/**
	 * 岗位未选人员
	 * @return
	 */
	@RequestMapping("/querySysPostUserUncludeList")
	@ResponseBody
	public Map<String,Object> querySysPostUserUncludeList(SysUser sysUser){
		Map<String,Object> pageData = new HashMap<String,Object>();
		pageData.put("total", sysUserService.querySysPostUserUncludeSum(sysUser));
		pageData.put("rows", sysUserService.querySysPostUserUncludeList(sysUser));
		return pageData;
	}
	
	/**
	 * 岗位添加未选人员
	 * @return
	 */
	@RequestMapping("/addUserToPost")
	@ResponseBody
	public void addUserToPost(SysPost sysPost){
		if(StringUtils.isNotEmpty(sysPost.getEntity_ids())){
			String[] user_id_array = sysPost.getEntity_ids().split(",");
			for(String user_id : user_id_array){
				sysPost.setUser_id(Integer.parseInt(user_id));
				sysPostService.addUserToPost(sysPost);
			}
		}
	}
	
	/**
	 * 岗位删除已选人员
	 * @return
	 */
	@RequestMapping("/deleteUserFromPost")
	@ResponseBody
	public void deleteUserFromPost(SysPost sysPost){
		if(StringUtils.isNotEmpty(sysPost.getEntity_ids())){
			String[] user_id_array = sysPost.getEntity_ids().split(",");
			for(String user_id : user_id_array){
				sysPost.setUser_id(Integer.parseInt(user_id));
				sysPostService.deleteUserFromPost(sysPost);
			}
		}
	}
	
	/*********************************岗位角色管理部分***************************************************/
	/**
	 * 岗位角色管理
	 * @return
	 */
	@RequestMapping("/postRoleManage")
	public String postRoleManage(HttpServletRequest request){
		request.setAttribute("post_id", request.getParameter("id"));
		return "sysmanage/sysPostRoleManage";
	}
	/**
	 * 岗位已选角色
	 * @return
	 */
	@RequestMapping("/sysPostRoleInclude")
	public String sysPostRoleInclude(HttpServletRequest request){
		request.setAttribute("post_id", request.getParameter("post_id"));
		return "sysmanage/sysPostRoleInclude";
	}
	/**
	 * 岗位已选角色
	 * @return
	 */
	@RequestMapping("/querySysPostRoleIncludeList")
	@ResponseBody
	public Map<String,Object> querySysPostRoleIncludeList(SysRole sysRole){
		Map<String,Object> pageData = new HashMap<String,Object>();
		pageData.put("total", sysRoleService.querySysPostRoleIncludeSum(sysRole));
		pageData.put("rows", sysRoleService.querySysPostRoleIncludeList(sysRole));
		return pageData;
	}
	/**
	 * 岗位已选角色
	 * @return
	 */
	@RequestMapping("/sysPostRoleUnclude")
	public String sysPostRoleUnclude(HttpServletRequest request){
		request.setAttribute("post_id", request.getParameter("post_id"));
		return "sysmanage/sysPostRoleUnclude";
	}
	/**
	 * 岗位未选角色
	 * @return
	 */
	@RequestMapping("/querySysPostRoleUncludeList")
	@ResponseBody
	public Map<String,Object> querySysPostRoleUncludeList(SysRole sysRole){
		Map<String,Object> pageData = new HashMap<String,Object>();
		pageData.put("total", sysRoleService.querySysPostRoleUncludeSum(sysRole));
		pageData.put("rows", sysRoleService.querySysPostRoleUncludeList(sysRole));
		return pageData;
	}
	/**
	 * 岗位添加未选角色
	 * @return
	 */
	@RequestMapping("/addRoleToPost")
	@ResponseBody
	public void addRoleToPost(SysPost sysPost){
		if(StringUtils.isNotEmpty(sysPost.getEntity_ids())){
			String[] role_id_array = sysPost.getEntity_ids().split(",");
			for(String role_id : role_id_array){
				sysPost.setRole_id(Integer.parseInt(role_id));
				sysPostService.addRoleToPost(sysPost);
			}
		}
	}
	/**
	 * 岗位删除已选角色
	 * @return
	 */
	@RequestMapping("/deleteRoleFromPost")
	@ResponseBody
	public void deleteRoleFromPost(SysPost sysPost){
		if(StringUtils.isNotEmpty(sysPost.getEntity_ids())){
			String[] role_id_array = sysPost.getEntity_ids().split(",");
			for(String role_id : role_id_array){
				sysPost.setRole_id(Integer.parseInt(role_id));
				sysPostService.deleteRoleFromPost(sysPost);
			}
		}
	}
	/**
	 * 查询人员岗位列表
	 * @return
	 */
	@RequestMapping("/querySysUserPostList")
	@ResponseBody
	public List<SysPost> querySysUserPostList(SysPost sysPost){
		return sysPostService.queryUserSysPostList(sysPost);
	}
}