package com.guoll.modules.commType.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guoll.modules.commType.bean.CommType;
import com.guoll.modules.commType.mapper.CommTypeMapper;


/**
 * 通用类型
 * @author lukas 414024003@qq.com
 * @version 1.0
 */
@Repository("commTypeService")
public class CommTypeService{

	@Autowired
    private CommTypeMapper mapper;
	
	/**
	 *  查询通用类型总数量
	 * @return
	 */
	public Integer queryCommTypeSum(CommType c){
		return mapper.queryCommTypeSum(c);
	}
	
	/**
	 * 查询通用类型列表
	 * @return
	 */
	public List<CommType> queryCommTypeList(CommType c) {
		return mapper.queryCommTypeList(c);
	}
	
	/**
	 * 查询通用类型所有列表查询条件为类型集合
	 * @return
	 */
	public List<CommType> queryCommTypeListByIds(String [] item) {
		return mapper.queryCommTypeListByIds(item);
	}
	
	/**
	 * 查询所有通用类型列表
	 * @return
	 */
	public List<CommType> queryCommTypeListAll(CommType c) {
		return mapper.queryCommTypeListAll(c);
	}
	
	/**
	 * 通过标识查询通用类型记录
	 * @return
	 */
	public CommType querySysCommTypeById(Integer id){
		return mapper.querySysCommTypeById(id);
	}
	
	/**
	 * 保存人员
	 * @return
	 */
	public void saveCommType(CommType c) {
		if(c.getId()!=null){
			mapper.updateCommType(c);
		}else{
			mapper.addCommType(c);
		}
	}
	
	/**
	 * 通过单个标识删除
	 * @return
	 */
	public void deleteCommType(CommType c) {
		mapper.deleteCommType(c);
	}
	
	/**
	 * 通过标识批量删除
	 * @return
	 */
	public void deleteCommTypeAll(String [] item) {
		mapper.deleteCommTypeAll(item);
	}
	
	
}
