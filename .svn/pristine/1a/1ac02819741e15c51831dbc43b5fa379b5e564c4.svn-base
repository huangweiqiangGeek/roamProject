package com.guoll.modules.homeproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guoll.modules.homeproject.bean.HomeProject;
import com.guoll.modules.homeproject.mapper.HomeProjectMapper;
import com.guoll.modules.sysmanage.bean.SysUser;

/**
 * 项目业务类
 * @author htnm
 *
 */
@Repository("homeProjectService")
public class HomeProjectService {
	
	@Autowired
	HomeProjectMapper mapper;
	
	/**
	 * 保存/更改
	 * @param homeProject
	 */
	public void save(HomeProject homeProject,SysUser sysUser){
		homeProject.setProvinceSpell(sysUser.getSysProvince().getProvinceSpell());
		homeProject.setPostName(sysUser.getPost_name());
		if (homeProject.getId() == null) {
			mapper.save(homeProject);
		}else {
			mapper.update(homeProject);
		}
	}

	public Integer queryCount(HomeProject c) {
		Integer count = mapper.queryCount(c);
		return count;
	}

	public List<HomeProject> queryHomeProjectList(HomeProject c) {
		List<HomeProject> queryList = mapper.queryList(c);
		return queryList;
	}

	public void deleteById(HomeProject homeProject) {
		mapper.deleteById(homeProject);
	}

	public HomeProject queryById(HomeProject homeProject) {
		List<HomeProject> queryList = mapper.queryList(homeProject);
		return queryList.get(0);
	}
}
