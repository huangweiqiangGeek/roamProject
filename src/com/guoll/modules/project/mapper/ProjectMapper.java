package com.guoll.modules.project.mapper;

import java.util.List;
import java.util.Map;

import com.guoll.modules.project.bean.Project;
import com.guoll.modules.sysmanage.bean.SysUser;



/**
 * 项目接口
 * @author lukas 414024003@qq.com
 * @version 1.0
 */
public interface ProjectMapper {

	/**
	 * 查询项目总数量
	 * @return
	 */
	public Integer queryProjectSum(Project c);
	
	/**
	 * 查询项目列表
	 * @return
	 */
	public List<Project> queryProjectList(Project c);
	
	/**
	 * 查询项目所有列表
	 * @return
	 */
	public List<Project> queryProjectListAll(Project c);
	
	
	/**
	 * 通过标识查询项目记录
	 * @return
	 */
	public Project queryProjectById(Integer id);
	
	/**
	 * <!-- 通过标识查询当前项目归属省份中的回归项目结果记录 -->
	 * @return
	 */
	public Project queryProjectByLastId(Map<String, Object> map);
	
	/**
	 * 新增项目
	 * @return
	 */
	public void addProject(Project c);
	
	/**
	 * 修改项目
	 * @return
	 */
	public void updateProject(Project c);
	
	/**
	 * 通过标识修改项目状态
	 * @return
	 */
	public void updateProjectByStatus(Project c);
	
	/**
	 * 通过标识修改项目用例总数
	 * @return
	 */
	public void updateProjectByCount(Project c);
	
	/**
	 * 删除项目
	 * @return
	 */
	public void deleteProject(Project c);
	
	/**
	 * 通过标识批量删除
	 * @param item
	 */
	public void deleteProjectAll(String [] item);

	/**
	 * 通过项目名查询项目
	 * @param p
	 */
	public List<Project>  queryProjectByNames(Project p);
	
	/**
	 * 初始化任务下拉选
	 * @param p
	 * @return
	 */
	public List<Project> queryTaskNameListByProvince(Project p);
	
	
/*
 *查询当前普通操作人员的任务
 */
	
	public   List<Project>  queryPuTongProject(SysUser p);
	
}
