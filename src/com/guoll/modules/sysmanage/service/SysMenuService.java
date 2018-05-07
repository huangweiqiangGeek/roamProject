package com.guoll.modules.sysmanage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guoll.modules.sysmanage.bean.SysMenu;
import com.guoll.modules.sysmanage.bean.SysOrg;
import com.guoll.modules.sysmanage.mapper.SysMenuMapper;

@Repository("sysMenuService")
public class SysMenuService{

	@Autowired
    private SysMenuMapper mapper;
	
	
	public List<SysMenu> queryBeanList(Integer parent_id) {

		List<SysMenu> returnList = new ArrayList<SysMenu>();

		queryByParentId(returnList, parent_id);

		return returnList;
	}

	public void queryByParentId(List<SysMenu> returnList, Integer parent_id) {

		List<SysMenu> list = mapper.queryByParentId(parent_id);

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				SysMenu menu = list.get(i);
				returnList.add(menu);
				queryByParentId(returnList, menu.getId());
			}
		}

	}
	
	
	
	public List<SysMenu> queryByParentId(Integer parent_id) {
		// TODO Auto-generated method stub
		return mapper.queryByParentId(parent_id);
	}
	
	
	public List<SysMenu> queryBeanListByRole(SysMenu sysMenu) {

		List<SysMenu> returnList = new ArrayList<SysMenu>();

		queryByParentId2(returnList, sysMenu.getParent_id(),sysMenu.getUser_id());
		
		return returnList;
	}

	public void queryByParentId2(List<SysMenu> returnList, int pid,int userId) {
		SysMenu sysMenu = new SysMenu();
		sysMenu.setParent_id(pid);
		sysMenu.setUser_id(userId);
		List<SysMenu> list = mapper.queryByUserLimitsMenu(sysMenu);

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				sysMenu = list.get(i);
				returnList.add(sysMenu);
				queryByParentId2(returnList, sysMenu.getId(),userId);
			}
		}

	}
	
	
	
	
	//主菜单（权限）
	public List<SysMenu> queryByUserLimitsMenu(SysMenu sysMenu) {
		// TODO Auto-generated method stub
		return mapper.queryByUserLimitsMenu(sysMenu);
	}
	
	public void updateSysMenu(SysMenu sysMenu) {
		// TODO Auto-generated method stub
		mapper.updateSysMenu(sysMenu);
	}
	
	public void addSysMenu(SysMenu sysMenu) {
		// TODO Auto-generated method stub
			mapper.addSysMenu(sysMenu);
	}
	
	public void dragSysMenu(SysMenu sysMenu) {
		// TODO Auto-generated method stub
			mapper.dragSysMenu(sysMenu);
	}
	
	public void renameSysMenu(SysMenu sysMenu) {
		// TODO Auto-generated method stub
			mapper.renameSysMenu(sysMenu);
	}
	
	public void deleteSysMenu(SysMenu sysMenu) {
		// TODO Auto-generated method stub
		mapper.deleteSysMenu(sysMenu);
	}
}
