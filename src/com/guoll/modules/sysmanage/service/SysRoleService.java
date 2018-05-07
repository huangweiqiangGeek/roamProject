package com.guoll.modules.sysmanage.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guoll.modules.framework.page.RowBounsUtil;
import com.guoll.modules.sysmanage.bean.SysRole;
import com.guoll.modules.sysmanage.mapper.SysRoleMapper;

@Repository("sysRoleService")
public class SysRoleService{

	@Autowired
    private SysRoleMapper mapper;
	
	/**
	 * 查询角色数量
	 * @return
	 */
	public Integer querySysRoleSum(SysRole sysRole){
		return mapper.querySysRoleSum(sysRole);
	}
	/**
	 * 查询角色列表
	 * @return
	 */
	public List<SysRole> querySysRoleList(SysRole sysRole) {
		//return mapper.querySysRoleList(sysRole,RowBounsUtil.getRowBounds(sysRole.getPage(), sysRole.getRows()));
		return mapper.querySysRoleList(sysRole);
	}
	/**
	 * 查询一条角色
	 * @return
	 */
	public SysRole querySysRoleById(Integer id){
		return mapper.querySysRoleById(id);
	}
	/**
	 * 保存角色
	 * @return
	 */
	public void saveSysRole(SysRole sysRole) {
		
		if(sysRole.getId()!=null){
			mapper.updateSysRole(sysRole);
		}else{
			mapper.addSysRole(sysRole);
		}
	}
	/**
	 * 删除角色
	 * @return
	 */
	public void deleteSysRole(SysRole sysRole) {
		mapper.deleteLinkSysRoleMenu(sysRole);
		mapper.deleteLinkSysRolePost(sysRole);
		mapper.deleteSysRole(sysRole);
	}
	/**
	 * 查询岗位已选角色数量
	 * @return
	 */
	public Integer querySysPostRoleIncludeSum(SysRole sysRole){
		return mapper.querySysPostRoleIncludeSum(sysRole);
	}
	/**
	 * 查询岗位已选角色
	 * @return
	 */
	public List<SysRole> querySysPostRoleIncludeList(SysRole sysRole){
		//return mapper.querySysPostRoleIncludeList(sysRole,RowBounsUtil.getRowBounds(sysRole.getPage(), sysRole.getRows()));
		return mapper.querySysPostRoleIncludeList(sysRole);
	}
	/**
	 * 查询岗位未选角色数量
	 * @return
	 */
	public Integer querySysPostRoleUncludeSum(SysRole sysRole){
		return mapper.querySysPostRoleUncludeSum(sysRole);
	}
	/**
	 * 查询岗位未选角色
	 * @return
	 */
	public List<SysRole> querySysPostRoleUncludeList(SysRole sysRole){
		return mapper.querySysPostRoleUncludeList(sysRole);
	}
	/**
	 * 角色菜单保存
	 * @return
	 */
	public void saveRoleMenu(SysRole sysRole){
		mapper.deleteLinkRoleMenu(sysRole);
		if(StringUtils.isNotEmpty(sysRole.getMenu_ids())){
			String[] menu_ids = sysRole.getMenu_ids().split(",");
			for(String menu_id : menu_ids){
				sysRole.setMenu_id(Integer.parseInt(menu_id));
				mapper.addLinkRoleMenu(sysRole);
				//判断parent是否已添加
				SysRole sysRole_ = mapper.queryMenuParentIdAndRoleLinkCount(sysRole);
				if(sysRole_.getParent_link_role_count()==0){
					sysRole_.setId(sysRole.getId());
					mapper.addLinkRoleMenu(sysRole_);
				}
			}
		}
	}
	public StringBuffer queryMenuIdsByRoleId(SysRole sysRole){
		StringBuffer menu_ids = new StringBuffer("");
		List<SysRole> sysRoleList = mapper.queryLinkRoleMenu(sysRole);
		for(SysRole sysRole_ : sysRoleList){
			menu_ids.append(sysRole_.getMenu_id()).append(",");
		}
		return menu_ids;
	}
}
