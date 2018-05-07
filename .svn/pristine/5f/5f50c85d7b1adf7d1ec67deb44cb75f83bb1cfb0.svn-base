package com.guoll.modules.billtemplate.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import util.DateUtil;
import util.fields.BillTemplateAttribute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guoll.modules.billtemplate.bean.BillTemplate;
import com.guoll.modules.billtemplate.mapper.BillTemplateMapper;
import com.guoll.modules.useCase.bean.UseCase;
import com.guoll.modules.useCase.mapper.UseCaseMapper;
import com.guoll.modules.useCase.service.UseCaseService;

/**
 * 话单模版
 * @author lukas 414024003@qq.com
 * @version 1.0
 */
@Repository("billTemplateService")
public class BillTemplateService{

	@Autowired
    private BillTemplateMapper mapper;
	@Autowired
    private UseCaseMapper useCaseMapper;
	@Autowired
	private UseCaseService useCaseService;
	
	/**
	 *  查询话单模版总数量
	 * @return
	 */
	public Integer queryBillTemplateSum(BillTemplate billTemplate){
		return mapper.queryBillTemplateSum(billTemplate);
	}
	
	/**
	 * 查询话单模版列表
	 * @return
	 */
	public List<BillTemplate> queryBillTemplateList(BillTemplate billTemplate) {
		return mapper.queryBillTemplateList(billTemplate);
	}
	
	/**
	 * 通过标识查询话单模版记录
	 * @return
	 */
	public BillTemplate querySysBillTemplateById(Integer id){
		return mapper.querySysBillTemplateById(id);
	}
	
	/**
	 * 保存人员
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public void saveBillTemplate(BillTemplate billTemplate) throws UnsupportedEncodingException {
		if(billTemplate.getId()!=null){
			billTemplate.setEditTime(DateUtil.getTimestamp());
			//处理修改记录
			//1）解析旧的
			BillTemplate oldTemp = mapper.querySysBillTemplateById(billTemplate.getId());
			String oldAttrJson = new String(oldTemp.getTemplateAttribute(), "GBK").replace('“', '"').replace("/", "|");
			String oldAttrJsonList = oldAttrJson.substring(oldAttrJson.indexOf("["), oldAttrJson.lastIndexOf("]")+1);
			System.out.println(oldAttrJsonList);
			List<BillTemplateAttribute> oldAttrList = JSON.parseArray(oldAttrJsonList, BillTemplateAttribute.class);
			//2）解析新的
			String newAttrJson = new String(billTemplate.getTemplateAttribute(), "GBK").replace('“', '"').replace("/", "|");
			String newAttrJsonList = newAttrJson.substring(newAttrJson.indexOf("["), newAttrJson.lastIndexOf("]")+1);
			List<BillTemplateAttribute> newAttrList = JSON.parseArray(newAttrJsonList, BillTemplateAttribute.class);
			//3)比较
			List<BillTemplateAttribute> changed = new ArrayList<>();
			for (BillTemplateAttribute billTemplateAttribute : newAttrList) {
				if (!oldAttrList.contains(billTemplateAttribute)) {
					changed.add(billTemplateAttribute);
				}
			}
			//4）保存
			List<HashMap> mapList=new  ArrayList<HashMap>();
			if (oldTemp.getUpdateTrace() == null ) {
			}else {
				mapList = JSONObject.parseArray(new String(oldTemp.getUpdateTrace(),"utf-8"),HashMap.class);
			}
			HashMap<String, String> thisUpdate = new HashMap<String,String>();
			thisUpdate.put("batch", mapList.size()+1+"");
			thisUpdate.put("value", JSON.toJSONString(changed));
			mapList.add(thisUpdate);
			billTemplate.setUpdateTrace(JSON.toJSONString(mapList).getBytes());
			mapper.updateBillTemplate(billTemplate);
		}else{
			billTemplate.setEditTime(DateUtil.getTimestamp());
	if(!"".equals(billTemplate.getTemplateName())&&billTemplate.getTemplateName()!=null){
		List<BillTemplate>  list1=mapper.queryBillTemplateListByName(billTemplate);
		//当已经有这个模板时,不添加
		  if(list1.size()>0){
			  billTemplate.setId(list1.get(0).getId());
			  mapper.updateBillTemplate(billTemplate);
		 }else{
			 mapper.addBillTemplate(billTemplate);
		 }
			}
		}
	}
	
	/**
	 * 通过单个标识删除
	 * @return
	 */
	public void deleteBillTemplate(BillTemplate billTemplate) {
		mapper.deleteBillTemplate(billTemplate);
	}
	
	/**
	 * 通过标识批量删除
	 * @return
	 */
	public void deleteBillTemplateAll(String [] item) {
		mapper.deleteBillTemplateAll(item);
	}

	public List<BillTemplate> queryBillTemplateListByName(BillTemplate billTemplate) {
		// TODO Auto-generated method stub
		return mapper.queryBillTemplateListByName( billTemplate);
	}

	/**
	 * 根据模板同步用例和模板
	 * @param id
	 * @throws Exception
	 */
	public void updateUseCaseTemplate(Integer id) throws Exception {
		UseCase usecase = new UseCase();
		usecase.setBillId(id);
		BillTemplate template = mapper.querySysBillTemplateById(id);
		List<UseCase> list = useCaseMapper.queryUseCaseListAll(usecase);
		for (UseCase useCase2 : list) {
			useCaseService.mergerBillTemplateAttribute(useCase2, template);
		}
	}
	
	
	
}
