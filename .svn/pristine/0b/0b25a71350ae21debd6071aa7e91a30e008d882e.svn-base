package com.guoll.modules.sysmanage.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guoll.modules.framework.page.RowBounsUtil;
import com.guoll.modules.framework.util.DateUtil;
import com.guoll.modules.sysmanage.bean.SysDic;
import com.guoll.modules.sysmanage.bean.SysDicItem;
import com.guoll.modules.sysmanage.bean.SysDicType;
import com.guoll.modules.sysmanage.mapper.SysDicMapper;

@Repository("sysDicService")
public class SysDicService{

	@Autowired
    private SysDicMapper mapper;
	
	public Integer querySysDicTypeSum(SysDicType sysDicType) {
		return mapper.querySysDicTypeSum(sysDicType);
	}
	public List<SysDicType> querySysDicTypeList(SysDicType sysDicType) {
		
		return mapper.querySysDicTypeList(sysDicType,
				RowBounsUtil.getRowBounds(sysDicType.getPage(), sysDicType.getRows()));
	}
	/**
	 * 查询字典类型列表
	 * @return
	 */
	public SysDicType querySysDicTypeById(Integer id){
		return mapper.querySysDicTypeById(id);
	}
	/**
	 * 保存字典类型
	 * @return
	 */
	public void saveSysDicType(SysDicType sysDicType) {
		
		if(sysDicType.getId()!=null){
			mapper.updateSysDicType(sysDicType);
		}else{
			if(StringUtils.isEmpty(sysDicType.getCode())){
				sysDicType.setCode(DateUtil.getCurrentFullDateStr()+"_"+Math.round(Math.random()*10));
			}
			mapper.addSysDicType(sysDicType);
		}
	}
	/**
	 * 删除字典类型
	 * @return
	 */
	public void deleteSysDicType(SysDicType sysDicType) {
		mapper.deleteSysDicType(sysDicType);
	}
	
	/**
	 * 查询字典明细数量
	 * @return
	 */
	public Integer querySysDicItemSum(SysDicItem sysDicItem){
		return mapper.querySysDicItemSum(sysDicItem);
	}
	/**
	 * 查询字典明细列表
	 * @return
	 */
	public List<SysDicItem> querySysDicItemList(SysDicItem sysDicItem){
		return mapper.querySysDicItemList(sysDicItem);
	}
	
	/**
	 * 查询字典明细
	 * @return
	 */
	public SysDicItem querySysDicItemById(Integer id){
		return mapper.querySysDicItemById(id);
	}
	
	/**
	 * 保存字典明细
	 * @return
	 */
	public void saveSysDicItem(SysDicItem sysDicItem) {
		
		if(sysDicItem.getId()!=null){
			mapper.updateSysDicItem(sysDicItem);
		}else{
			if(StringUtils.isEmpty(sysDicItem.getCode())){
				sysDicItem.setCode(DateUtil.getCurrentFullDateStr()+"_"+Math.round(Math.random()*10));
			}
			mapper.addSysDicItem(sysDicItem);
		}
	}
	
	/**
	 * 删除字典明细
	 * @return
	 */
	public void deleteSysDicItem(SysDicItem sysDicItem) {
		mapper.deleteSysDicItem(sysDicItem);
	}	
	/**
	 * 
	 * 查询下拉列表数据
	 *
	 * @param dict_type
	 * @return 描述 
	 *
	 */
	public List<SysDic> queryDic(Integer dict_type){
	    return mapper.queryDic(dict_type);
	}

}
