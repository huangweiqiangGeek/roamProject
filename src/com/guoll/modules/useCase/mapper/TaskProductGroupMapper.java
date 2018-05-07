package com.guoll.modules.useCase.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guoll.modules.useCase.bean.TaskProductGroup;

public interface TaskProductGroupMapper {

	public int addProductGroupToTask(TaskProductGroup tpg);
	public List<TaskProductGroup> queryProductGroupListByTask(TaskProductGroup tpg);
	public TaskProductGroup queryProductGroupById(@Param("id") Integer id);
	public void deleteProductGroupToTask(TaskProductGroup tpg);
	public void delectProductnode(TaskProductGroup tpg);
	public  TaskProductGroup  queryProductGroup(TaskProductGroup tpg);
}
