package com.guoll.modules.statistics.mapper;

import java.util.List;

import com.guoll.modules.statistics.bean.ProjectExecuteTrace;
/**
 * 
 * @author htnm
 *
 */
public interface ProjectExecuteTraceMapper {

	/**
	 * 新增项目执行记录
	 */
	void addProjectExecuteTrace(ProjectExecuteTrace projectExecuteTrace);

	/**
	 * 查询项目执行记录
	 */
	List<ProjectExecuteTrace> queryProjectExecuteTraceByExampleWithPaging(ProjectExecuteTrace projectExecuteTrace);

	/**
	 * 查询项目的最大执行批次
	 * @param proID
	 * @return
	 */
	Integer queryLastExecuteBatch(Integer proID);

	/**
	 * 根据id查询项目执行轨迹
	 * @param id
	 * @return
	 */
	ProjectExecuteTrace queryProjectExecuteTraceById(Integer id);

	/**
	 * 删除项目执行轨迹
	 */
	void delete(Integer id);
}
