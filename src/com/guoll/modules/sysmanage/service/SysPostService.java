package com.guoll.modules.sysmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guoll.modules.framework.page.RowBounsUtil;
import com.guoll.modules.sysmanage.bean.SysPost;
import com.guoll.modules.sysmanage.mapper.SysPostMapper;

@Repository("sysPostService")
public class SysPostService{

	@Autowired
    private SysPostMapper mapper;
	
	/**
	 * 查询岗位数量
	 * @return
	 */
	public Integer querySysPostSum(SysPost sysPost) {
		return mapper.querySysPostSum(sysPost);
	}
	/**
	 * 查询岗位列表
	 * @return
	 */
	public List<SysPost> querySysPostList(SysPost sysPost) {
		return mapper.querySysPostList(sysPost,RowBounsUtil.getRowBounds(sysPost.getPage(), sysPost.getRows()));
	}
	
	/**
	 * 查询一条岗位
	 * @return
	 */
	public SysPost querySysPostById(Integer id){
		return mapper.querySysPostById(id);
	}
	/**
	 * 保存岗位
	 * @return
	 */
	public void saveSysPost(SysPost sysPost) {
		
		if(sysPost.getId()!=null){
			mapper.updateSysPost(sysPost);
		}else{
			mapper.addSysPost(sysPost);
		}
	}
	/**
	 * 删除岗位
	 * @return
	 */
	public void deleteSysPost(SysPost sysPost) {
		mapper.deleteLinkSysPostUser(sysPost);
		mapper.deleteLinkSysPostRole(sysPost);
		mapper.deleteSysPost(sysPost);
	}
	/**
	 * 查询用户岗位列表
	 * @return
	 */
	public List<SysPost> queryUserSysPostList(SysPost sysPost) {
		return mapper.queryUserSysPostList(sysPost);
	}
	/**
	 * 岗位添加人员
	 * @return
	 */
	public void addUserToPost(SysPost sysPost){
		mapper.addUserToPost(sysPost);
	}
	/**
	 * 岗位移除人员
	 * @return
	 */
	public void deleteUserFromPost(SysPost sysPost){
		mapper.deleteUserFromPost(sysPost);
	}
	/**
	 * 岗位添加角色
	 * @return
	 */
	public void addRoleToPost(SysPost sysPost){
		mapper.addRoleToPost(sysPost);
	}
	/**
	 * 岗位移除角色
	 * @return
	 */
	public void deleteRoleFromPost(SysPost sysPost){
		mapper.deleteRoleFromPost(sysPost);
	}
	
	/**
	 * 查询是否有重复岗位
	 * @param sysPost
	 * @return
	 */
	public List<SysPost> queryPostToOrgId(SysPost sysPost){
		return mapper.queryPostToOrgId(sysPost);
	}
}
