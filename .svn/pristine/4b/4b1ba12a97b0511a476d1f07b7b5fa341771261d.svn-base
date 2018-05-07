package com.guoll.modules.billtemplate.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.guoll.modules.billtemplate.bean.BillTemplate;

/**
 * 话单模版接口
 * @author lukas 414024003@qq.com
 * @version 1.0
 */
public interface BillTemplateMapper {
   
	/**
	 * 查询话单模版总数量
	 * @return
	 */
	public Integer queryBillTemplateSum(BillTemplate billTemplate);
	
	/**
	 * 查询话单模版列表
	 * @return
	 */
	public List<BillTemplate> queryBillTemplateList(BillTemplate billTemplate);
	
	/**
	 * 通过标识查询话单模版记录
	 * @return
	 */
	public BillTemplate querySysBillTemplateById(Integer id);
	
	/**
	 * 新增话单模版
	 * @return
	 */
	public void addBillTemplate(BillTemplate billTemplate);
	
	/**
	 * 修改话单模版
	 * @return
	 */
	public void updateBillTemplate(BillTemplate billTemplate);
	
	/**
	 * 删除话单模版
	 * @return
	 */
	public void deleteBillTemplate(BillTemplate billTemplate);
	
	/**
	 * 通过标识批量删除
	 * @param item
	 */
	public void deleteBillTemplateAll(String [] item);

	public List<BillTemplate> queryBillTemplateListByName(BillTemplate billTemplate);
	
}
