package com.guoll.modules.useCase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.guoll.modules.useCase.bean.TaskProductGroup;
import com.guoll.modules.useCase.mapper.TaskProductGroupMapper;

@Service
public class TaskProductGroupService {

	@Autowired
	private TaskProductGroupMapper mapper;
	
	public int addProductGroupToTask(TaskProductGroup tpg){
		return mapper.addProductGroupToTask(tpg);
	}
	
	public List<TaskProductGroup> queryProductGroupListByTask(TaskProductGroup tpg){
		return mapper.queryProductGroupListByTask(tpg);
	}
	
	public TaskProductGroup queryProductGroupById(Integer id){
		return mapper.queryProductGroupById(id);
	}
	
	public void deleteProductGroupToTask(TaskProductGroup tpg){
		mapper.deleteProductGroupToTask(tpg);
	}
	//查询单独的TaskProductGroup
	public TaskProductGroup queryProductGroup(TaskProductGroup tpg){
		return mapper.queryProductGroup(tpg);
	}
	
}
