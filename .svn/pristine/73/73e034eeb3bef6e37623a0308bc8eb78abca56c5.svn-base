package com.guoll.modules.sysmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guoll.modules.framework.page.RowBounsUtil;
import com.guoll.modules.sysmanage.bean.SysPost;
import com.guoll.modules.sysmanage.bean.SysUser;
import com.guoll.modules.sysmanage.mapper.SysUserMapper;

@Repository("sysUserService")
public class SysUserService{

	@Autowired
    private SysUserMapper mapper;
	
	/**
	 * 查询人员总数
	 * @return
	 */
	public Integer querySysUserSum(SysUser sysUser){
		return mapper.querySysUserSum(sysUser);
	}
	
	/**
	 * 查询人员列表
	 * @return
	 */
	public List<SysUser> querySysUserList(SysUser sysUser) {
		//return mapper.querySysUserList_s(sysUser,RowBounsUtil.getRowBounds(sysUser.getPage(), sysUser.getRows()));
		return mapper.querySysUserList(sysUser);
	}
	
	/**
	 * 查询一条人员
	 * @return
	 */
	public SysUser querySysUserById(Integer id){
		return mapper.querySysUserById(id);
	}
	
	/**
	 * 保存人员
	 * @return
	 */
	public void saveSysUser(SysUser sysUser) {
		
		if(sysUser.getId()!=null){
			mapper.updateSysUser(sysUser);
		}else{
			mapper.addSysUser(sysUser);
		}
	}
	
	/**
	 * 删除
	 * @return
	 */
	public void deleteSysUser(SysUser sysUser) {
		mapper.deleteLinkSysUserPost(sysUser);
		mapper.deleteSysUser(sysUser);
	}
	
	/**
	 * 查询岗位已选人员数量
	 * @return
	 */
	public Integer querySysPostUserIncludeSum(SysUser sysUser){
		return mapper.querySysPostUserIncludeSum(sysUser);
	}
	
	/**
	 * 查询岗位已选人员
	 * @return
	 */
	public List<SysUser> querySysPostUserIncludeList(SysUser sysUser){
		return mapper.querySysPostUserIncludeList(sysUser);
	}
	
	/**
	 * 查询岗位未选人员数量
	 * @return
	 */
	public Integer querySysPostUserUncludeSum(SysUser sysUser){
		return mapper.querySysPostUserUncludeSum(sysUser);
	}
	
	/**
	 * 查询岗位未选人员
	 * @return
	 */
	public List<SysUser> querySysPostUserUncludeList(SysUser sysUser){
		return mapper.querySysPostUserUncludeList(sysUser);
	}
	
	/**
	 * 修改密码
	 * @param sysUser
	 */
	public void updatePassWord(SysUser sysUser){
		mapper.updatePassWord(sysUser);
	}
	
	/**
	 * 查询是否有重复人名
	 * @param sysPost
	 * @return
	 */
	public List<SysUser> queryUserToOrgId(SysUser sysUser){
		return mapper.queryUserToOrgId(sysUser);
	}
	
	//生成自增长主键id,在t_auto_increment表中配置相关的主键信息
	public  Integer  getOverviewid(Integer id){
		return  mapper.getOverviewid(id);
	};
}
