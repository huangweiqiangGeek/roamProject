package com.guoll.modules.sysmanage.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.guoll.modules.sysmanage.bean.SysPost;
import com.guoll.modules.sysmanage.bean.SysUser;

/**
 * SysUser Mapper
 * @author 
 *
 */
public interface SysUserMapper {

	/**
	 * 检查登录
	 * @param email
	 * @param pwd
	 * @return
	 */
	public SysUser queryLogin(SysUser sysUser);
	public Integer querySysUserSum(SysUser sysUser);
	//public List<SysUser> querySysUserList_s(SysUser sysUser,RowBounds rowBounds);
	public List<SysUser> querySysUserList(SysUser sysUser);
	public SysUser querySysUserById(Integer id);
	public void updateSysUser(SysUser sysUser);
	public void addSysUser(SysUser sysUser);
	
	public void deleteLinkSysUserPost(SysUser sysUser);
	public void deleteSysUser(SysUser sysUser);
	
	public Integer querySysPostUserIncludeSum(SysUser sysUser);
	public Integer querySysPostUserUncludeSum(SysUser sysUser);
	public List<SysUser> querySysPostUserIncludeList(SysUser sysUser);
	public List<SysUser> querySysPostUserUncludeList(SysUser sysUser);
	public void updatePassWord(SysUser sysUser);
	public List<SysUser> queryUserToOrgId(SysUser sysUser);
	//生成自增长主键id,在t_auto_increment表中配置相关的主键信息
	public  Integer  getOverviewid(Integer id);
}
