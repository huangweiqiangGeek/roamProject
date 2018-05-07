package com.guoll.modules.resultTemplate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guoll.modules.billtemplate.bean.BillTemplate;
import com.guoll.modules.resultTemplate.bean.ResultTemplate;
import com.guoll.modules.resultTemplate.mapper.ResultTemplateMapper;

/**
 * 预设结果对比模版
 * @author lukas 414024003@qq.com
 * @version 1.0
 */
@Repository("resultTemplateService")
public class ResultTemplateService{

	@Autowired
    private ResultTemplateMapper mapper;
	
	/**
	 *  查询预设结果对比模版总数量
	 * @return
	 */
	public Integer queryResultTemplateSum(ResultTemplate c){
		return mapper.queryResultTemplateSum(c);
	}
	
	/**
	 * 查询预设结果对比模版列表
	 * @return
	 */
	public List<ResultTemplate> queryResultTemplateList(ResultTemplate c) {
		return mapper.queryResultTemplateList(c);
	}
	
	/**
	 * 查询所有预设结果对比模版列表
	 * @return
	 */
	public List<ResultTemplate> queryResultTemplateListAll(ResultTemplate c) {
		return mapper.queryResultTemplateListAll(c);
	}
	
	/**
	 * 通过标识查询预设结果对比模版记录
	 * @return
	 */
	public ResultTemplate querySysResultTemplateById(Integer id){
		return mapper.querySysResultTemplateById(id);
	}
	
	/**
	 * 保存人员
	 * @return
	 */
	public void saveResultTemplate(ResultTemplate c) {
		if(c.getId()!=null){
			mapper.updateResultTemplate(c);
		}else{
			mapper.addResultTemplate(c);
		}
	}
	
	/**
	 * 通过单个标识删除
	 * @return
	 */
	public void deleteResultTemplate(ResultTemplate c) {
		mapper.deleteResultTemplate(c);
	}
	
	/**
	 * 通过标识批量删除
	 * @return
	 */
	public void deleteResultTemplateAll(String [] item) {
		mapper.deleteResultTemplateAll(item);
	}
	
	//通过标识查看相应的模板
	public  ResultTemplate  queryResultTemplete(ResultTemplate rt){
		return  mapper.queryResultTemplete(rt);
	}
	
	
}
