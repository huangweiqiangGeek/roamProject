package com.guoll.modules.sysmanage.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.guoll.modules.sysmanage.bean.SysDic;
import com.guoll.modules.sysmanage.bean.SysDicItem;
import com.guoll.modules.sysmanage.bean.SysDicType;

public interface SysDicMapper{
	
	/**
	 * 查询字典类型数量
	 * @return
	 */
	public Integer querySysDicTypeSum(SysDicType sysDicType);
	
	/**
	 * 查询字典类型列表
	 * @return
	 */
	public List<SysDicType> querySysDicTypeList(SysDicType sysDicType,RowBounds RowBounds);
	
	/**
	 * 查询字典类型
	 * @return
	 */
	public SysDicType querySysDicTypeById(Integer id);
	
	/**
	 * 新增字典
	 * @return
	 */
	public void addSysDicType(SysDicType sysDicType);
	
	/**
	 * 修改字典
	 * @return
	 */
	public void updateSysDicType(SysDicType sysDicType);
	
	/**
	 * 删除字典
	 * @return
	 */
	public void deleteSysDicType(SysDicType sysDicType);
	
	/***********字典明细************/
	
	/**
	 * 查询明细数量
	 * @return
	 */
	public Integer querySysDicItemSum(SysDicItem sysDicItem);
	/**
	 * 查询明细列表
	 * @return
	 */
	public List<SysDicItem> querySysDicItemList(SysDicItem sysDicItem);
	
	/**
	 * 查询明细一条记录
	 * @return
	 */
	public SysDicItem querySysDicItemById(Integer id);
	
	/**
	 * 新增字典明细
	 * @return
	 */
	public void addSysDicItem(SysDicItem sysDicItem);
	
	/**
	 * 修改字典明细
	 * @return
	 */
	public void updateSysDicItem(SysDicItem sysDicItem);
	
	/**
	 * 删除字典明细
	 * @return
	 */
	public void deleteSysDicItem(SysDicItem sysDicItem);	
	/**
	 * 
	 * 查询下拉列表数据
	 *
	 * @param dict_type
	 * @return 描述 
	 *
	 */
	public List<SysDic> queryDic(Integer dict_type);
	
}