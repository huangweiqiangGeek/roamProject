package com.guoll.modules.homeproject.mapper;

import java.util.List;

import com.guoll.modules.homeproject.bean.HomeProject;

/**
 * 
 * @author htnm
 *
 */
public interface HomeProjectMapper {

	void save(HomeProject homeProject);

	void update(HomeProject homeProject);
	
	List<HomeProject> queryList(HomeProject homeProject);
	
	Integer queryCount(HomeProject homeProject);

	void deleteById(HomeProject homeProject);
	
}
