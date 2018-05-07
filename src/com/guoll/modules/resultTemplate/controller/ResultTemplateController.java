package com.guoll.modules.resultTemplate.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.guoll.modules.commType.bean.CommType;
import com.guoll.modules.commType.service.CommTypeService;
import com.guoll.modules.framework.base.BaseController;
import com.guoll.modules.framework.util.SessionUtils;
import com.guoll.modules.product_customer.bean.ProductBusinessType;
import com.guoll.modules.product_customer.service.ProductBusinessTypeService;
import com.guoll.modules.resultTemplate.bean.ResultTemplate;
import com.guoll.modules.resultTemplate.service.ResultTemplateService;
import com.guoll.modules.sysmanage.bean.SysUser;

/**
 * 预设结果模版管理
 * @author lukas 414024003@qq.com
 * @Date  2017年3月17日 11:36:12
 * @version 1.0
 */
@Controller
@RequestMapping("/resultTemplate")
public class ResultTemplateController extends BaseController {

	@Autowired(required=false)
	ResultTemplateService resultTemplateService;
	@Autowired(required=false)
	CommTypeService commTypeService;
	@Autowired(required=false)
	public   ProductBusinessTypeService  productBusinessTypeService;
	/**
	 * 预设结果模版入口
	 * @return
	 */
	@RequestMapping("/listInit")
	public String listInit(HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);//获取当前用户实体
		setListInit(request);
		request.setAttribute("provinceName", sysUser.getPost_name());//获取当前用户登录所属省份标识
		return "resultTemplate/list";//指定初始化JSP页面
	}
	
	
	
	/**
	 * 查询预设结果模版列表
	 * @return
	 */
	@RequestMapping("/listPage")
	@ResponseBody
	public Map<String,Object> listPage(ResultTemplate c,HttpServletRequest request){
		c.setProvinceName(getProvinceName(request));
		Map<String,Object> pageData = new HashMap<String,Object>();
		c.setPages();//设置分页信息
		pageData.put("total", resultTemplateService.queryResultTemplateSum(c));//获取查询总记录条数
		pageData.put("rows", resultTemplateService.queryResultTemplateList(c));//获取查询的集合
		return pageData;
	}
	
	@RequestMapping("/resultListInit")
	public String resultListInit(HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);//获取当前用户实体
		setListInit(request);
		request.setAttribute("provinceName", sysUser.getPost_name());//获取当前用户登录所属省份标识
		return "useCase/result_list";//指定初始化JSP页面
	}
	
	@RequestMapping("/resultListPage")
	@ResponseBody
	public Map<String,Object> resultListPage(ResultTemplate c,HttpServletRequest request) throws UnsupportedEncodingException{
		c.setProvinceName(getProvinceName(request));
		Map<String,Object> pageData = new HashMap<String,Object>();
		c.setPages();//设置分页信息
		List<ResultTemplate> list =new ArrayList<ResultTemplate>();
		List<ResultTemplate> tempList =new ArrayList<ResultTemplate>();
		tempList = resultTemplateService.queryResultTemplateList(c);
		for (ResultTemplate t : tempList) {
			String attrJson =new String(t.getTemplateAttribute(),"GBK").replace('“', '#').replace("/", "|");
			t.setAttrJson(attrJson);
			list.add(t);
		}
		pageData.put("total", resultTemplateService.queryResultTemplateSum(c));//获取查询总记录条数
		pageData.put("rows", list);//获取查询的集合
		return pageData;
	}
	
	/**
	 * 修改预设结果模版初始化
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/updateInit")
	public String updateBillTemplate(Integer id,HttpServletRequest request) throws UnsupportedEncodingException{
		setListInit(request);
		if(id!=null){
			ResultTemplate b = resultTemplateService.querySysResultTemplateById(id);
			request.setAttribute("resultTemplate", b);
			String s =new String(b.getTemplateAttribute(),"GBK").replace('“', '"');
			request.setAttribute("templateAttributes",s);
			return "resultTemplate/edit";
		}
		return "resultTemplate/list";
	}
	/**
	 * 新增预设结果模版初始化
	 * @return
	 */
	@RequestMapping("/addInit")
	public String addInit(Integer org_id,HttpServletRequest request){
		setListInit(request);
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);//获取当前用户实体
		ResultTemplate resultTemplate = new ResultTemplate();
		resultTemplate.setProvinceName(sysUser.getPost_name());
		request.setAttribute("resultTemplate", resultTemplate);
		return "resultTemplate/edit";
	}
	/**
	 * 删除预设结果模版
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(ResultTemplate b,HttpServletRequest request){
		resultTemplateService.deleteResultTemplate(b);
	}
	
	/**
	 * 保存新增/修改预设结果模版
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/save")
	public String save(ResultTemplate b,HttpServletRequest request) throws UnsupportedEncodingException{
       String templateAttributes  = request.getParameter("templateAttributes");
       b.setTemplateAttribute(templateAttributes.getBytes("GBK"));
       resultTemplateService.saveResultTemplate(b);
		return "resultTemplate/list";
	}
	

	/**
	 * 设定下拉框初始值和业务类型的下拉选
	 * @param request
	 */
	public void setListInit(HttpServletRequest request){
		List<CommType> list = new ArrayList<CommType>();//创建集合
		List<CommType> satusList = new ArrayList<CommType>();//创建预设结果模版状态集合
		List<CommType> typeList = new ArrayList<CommType>();//创建预设结果模版类型集合
		String [] item ={"2","8"};//归属类型: 1.话单模版状态、2.预设结果模版状态、3.项目状态、4.用例状态、5.执行结果状态 6.选择方式状态 7.用例执行状态 8.预设结果模版类型
		list = commTypeService.queryCommTypeListByIds(item);
		if(list!=null && list.size()>0){
			//遍历当前集合并筛选对应的对象加载到satusList以及typeList中
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				CommType commType = (CommType) iterator.next();
				if(commType==null){
					continue;
				}
				//判断当前对象是否为状态对象如果是则添加到satusList集合中，如果不是那么则添加到typeList集合中
				if(commType.getAffiliationType()==2){
					satusList.add(commType);
				}else{
					typeList.add(commType);
				}
			}
		}
		
		List<ProductBusinessType>  productTypeList= productBusinessTypeService.queryProductBusinessTypeSelect();
		request.setAttribute("productTypeList", productTypeList);
		request.setAttribute("satusList", satusList);
		request.setAttribute("typeList", typeList);
	}
	
}