package com.guoll.modules.commConfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.guoll.modules.commConfig.bean.CommConfig;
import com.guoll.modules.commConfig.mapper.CommConfigMapper;


/**
 * 通用配置
 * @author lukas 414024003@qq.com
 * @version 1.0
 */
@Repository("commConfigService")
public class CommConfigService{

	@Autowired
    private CommConfigMapper mapper;
	
	/**
	 *  查询通用配置总数量
	 * @return
	 */
	public Integer queryConfigSum(CommConfig c){
		return mapper.queryConfigSum(c);
	}
	
	/**
	 * 分页查询通用配置列表
	 * @return
	 */
	public List<CommConfig> queryConfigList(CommConfig c) {
		return mapper.queryConfigList(c);
	}
	/**
	 * 查询通用配置所有列表
	 * @return
	 */
	public List<CommConfig> queryAllConfigList(CommConfig c) {
		return mapper.queryAllConfigList(c);
	}
	
	/**
	 * 通过标识查询通用配置记录
	 * @return
	 */
	public CommConfig querySysConfigById(CommConfig c){
		return mapper.querySysConfigById(c);
	}
	
	/**
	 * 通过省份查询通用配置记录 
	 * @return
	 */
	public CommConfig querySysConfigByProvince(String provinceName){
		return mapper.querySysConfigByProvince(provinceName);
	}
	
	/**
	 * 保存人员
	 * @return
	 */
	public void saveCommConfig(CommConfig c) {
		if(c.getId()!=null){
			mapper.updateConfig(c);
		}else{
			mapper.addConfig(c);
		}
	}
	
	/**
	 * 通过单个标识删除
	 * @return
	 */
	public void deleteCommConfig(CommConfig c) {
		mapper.deleteConfig(c);
	}
	
	/**
	 * 通过标识批量删除
	 * @return
	 */
	public void deleteCommConfigAll(String [] item) {
		mapper.deleteConfigAll(item);
	}

	/**
	 * 根据配置查询系统配置
	 * @param c
	 * @return
	 */
	public List<CommConfig> querySysConfigList(CommConfig c) {
		// TODO Auto-generated method stub
		return mapper.querySysConfigList( c);
	}
	
	
}
