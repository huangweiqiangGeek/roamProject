package com.guoll.modules.sysmanage.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.guoll.modules.sysmanage.bean.SysPost;

/**
 * SysPost Mapper
 * @author 
 *
 */
public interface SysPostMapper {

	public Integer querySysPostSum(SysPost sysPost);
	public List<SysPost> querySysPostList(SysPost sysPost,RowBounds rowBounds);
	public SysPost querySysPostById(Integer id);
	public void updateSysPost(SysPost sysPost);
	public void addSysPost(SysPost sysPost);
	public void deleteLinkSysPostUser(SysPost sysPost);
	public void deleteLinkSysPostRole(SysPost sysPost);
	public void deleteSysPost(SysPost sysPost);
	public void addUserToPost(SysPost sysPost);
	public void deleteUserFromPost(SysPost sysPost);
	public void addRoleToPost(SysPost sysPost);
	public void deleteRoleFromPost(SysPost sysPost);
	public List<SysPost> queryUserSysPostList(SysPost sysPost);
	public List<SysPost> queryUserSysPostList(SysPost sysPost,RowBounds rowBounds);
	public List<SysPost> queryPostToOrgId(SysPost sysPost);
	public List<SysPost> querySysPostByProvinceSpell(SysPost sysPost);
}
