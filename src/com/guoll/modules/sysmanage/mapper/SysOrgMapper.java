package com.guoll.modules.sysmanage.mapper;

import java.util.List;
import com.guoll.modules.sysmanage.bean.SysOrg;

public interface SysOrgMapper{
	
	/**
	 * 根据ID获得组织
	 * @return
	 */
	public List<SysOrg> queryByParentId(Integer parent_id);
		
	/**
	 * 新增组织
	 * @return
	 */
	public void addSysOrg(SysOrg sysOrg);
	
	/**
	 * 修改组织
	 * @return
	 */
	public void updateSysOrg(SysOrg sysOrg);
	
	/**
	 * 移动菜单
	 * @return
	 */
	public void dragSysOrg(SysOrg sysOrg);
	
	/**
	 * 修改菜单名称
	 * @return
	 */
	public void renameSysOrg(SysOrg sysOrg);
	
	/**
	 * 删除菜单
	 * @return
	 */
	public void deleteSysOrg(SysOrg sysOrg);
	public void deleteLinkSysOrgPostRole(SysOrg sysOrg);
	public void deleteLinkSysOrgPostUser(SysOrg sysOrg);
	public void deleteSysOrgPost(SysOrg sysOrg);
	public void deleteSysOrgUser(SysOrg sysOrg);
}
