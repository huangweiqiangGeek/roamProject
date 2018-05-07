package com.guoll.modules.sysmanage.mapper;

import java.util.List;

import com.guoll.modules.sysmanage.bean.SysMenu;


public interface SysMenuMapper{
	
	/**
	 * 根据父ID查询子菜单列表
	 * @return
	 */
	public List<SysMenu> queryByParentId(Integer parent_id);

	/**
	 * 查询用户权限内菜单
	 * @return
	 */
	public List<SysMenu> queryByUserLimitsMenu(SysMenu sysMenu);
	
	/**
	 * 新增菜单
	 * @return
	 */
	public void addSysMenu(SysMenu sysMenu);
	
	/**
	 * 修改菜单
	 * @return
	 */
	public void updateSysMenu(SysMenu sysMenu);
	
	/**
	 * 移动菜单
	 * @return
	 */
	public void dragSysMenu(SysMenu sysMenu);
	
	/**
	 * 修改菜单名称
	 * @return
	 */
	public void renameSysMenu(SysMenu sysMenu);
	
	/**
	 * 删除菜单
	 * @return
	 */
	public void deleteSysMenu(SysMenu sysMenu);
}