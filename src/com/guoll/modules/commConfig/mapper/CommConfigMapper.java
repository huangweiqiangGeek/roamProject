package com.guoll.modules.commConfig.mapper;

import java.util.List;

import com.guoll.modules.commConfig.bean.CommConfig;

/**
 * 通用配置接口
 * @author lukas
 *
 */
public interface CommConfigMapper {
	/**
	 * 查询通用配置总数量
	 * @return
	 */
	public Integer queryConfigSum(CommConfig c);
	
	/**
	 * 分页查询通用配置列表
	 * @return
	 */
	public List<CommConfig> queryConfigList(CommConfig c);
	/**
	 * 查询通用配置所有列表
	 * @return
	 */
	public List<CommConfig> queryAllConfigList(CommConfig c);
	
	/**
	 * 通过标识查询通用配置记录
	 * @return
	 */
	public CommConfig querySysConfigById(Integer id);
	
	/**
	 * 通过省份查询通用配置记录 
	 * @return
	 */
	public CommConfig querySysConfigByProvince(String provinceName);
	
	/**
	 * 新增通用配置
	 * @return
	 */
	public void addConfig(CommConfig c);
	
	/**
	 * 修改通用配置
	 * @return
	 */
	public void updateConfig(CommConfig c);
	
	/**
	 * 删除通用配置
	 * @return
	 */
	public void deleteConfig(CommConfig c);
	
	/**
	 * 通过标识批量删除
	 * @param item
	 */
	public void deleteConfigAll(String [] item);

	/**
	 * 根据配置查询系统配置
	 * @param c
	 * @return
	 */
	public List<CommConfig> querySysConfigList(CommConfig c);
}