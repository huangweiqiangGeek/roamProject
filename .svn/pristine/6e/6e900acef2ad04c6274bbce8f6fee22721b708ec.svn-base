package com.guoll.modules.sysmanage.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.guoll.modules.sysmanage.bean.SysRole;

/**
 * SysRole Mapper
 * @author 
 *
 */
public interface SysRoleMapper {

	public Integer querySysRoleSum(SysRole sysRole);
	//public List<SysRole> querySysRoleList(SysRole sysRole,RowBounds rowBounds);
	public List<SysRole> querySysRoleList(SysRole sysRole);
	public SysRole querySysRoleById(Integer id);
	public void updateSysRole(SysRole sysRole);
	public void addSysRole(SysRole sysRole);
	public void deleteLinkSysRoleMenu(SysRole sysRole);
	public void deleteLinkSysRolePost(SysRole sysRole);
	public void deleteSysRole(SysRole sysRole);
	public Integer querySysPostRoleIncludeSum(SysRole sysRole);
	public List<SysRole> querySysPostRoleIncludeList(SysRole sysRole);
	public Integer querySysPostRoleUncludeSum(SysRole sysRole);
	public List<SysRole> querySysPostRoleUncludeList(SysRole sysRole);
	public List<SysRole> queryLinkRoleMenu(SysRole sysRole);
	public void addLinkRoleMenu(SysRole sysRole);
	public void deleteLinkRoleMenu(SysRole sysRole);
	public SysRole queryMenuParentIdAndRoleLinkCount(SysRole sysRole);
}
