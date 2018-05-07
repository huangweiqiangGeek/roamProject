package com.guoll.modules.useCase.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.guoll.modules.commConfig.bean.CommConfig;
import com.guoll.modules.commConfig.service.CommConfigService;
import com.guoll.modules.commType.bean.CommType;
import com.guoll.modules.commType.service.CommTypeService;
import com.guoll.modules.framework.base.BaseController;
import com.guoll.modules.project.bean.Project;
import com.guoll.modules.project.service.ProjectService;
import com.guoll.modules.resultRecord.bean.ResultRecord;
import com.guoll.modules.resultRecord.service.ResultRecordService;
import com.guoll.modules.sysmanage.bean.SysUser;
import com.guoll.modules.useCase.bean.UseCase;
import com.guoll.modules.useCase.service.UseCaseService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.Convert;
import util.DateUtil;

/**
 * 回归项目用例管理
 * 
 * @author lukas 414024003@qq.com
 * @Date 2017年3月17日 11:36:12
 * @version 1.0
 */
@Controller
@RequestMapping("/regressionUseCase")
public class RegressionUseCaseController extends BaseController {

	@Autowired(required = false)
	UseCaseService useCaseService;
	@Autowired(required = false)
	CommTypeService commTypeService;
	@Autowired(required = false)
	ProjectService projectService;
	@Autowired(required = false)
	CommConfigService commConfigService;
	@Autowired(required = false)
	ResultRecordService resultRecordService;
	/**
	 * 项目用例入口
	 * 
	 * @return
	 */
	@RequestMapping("/listInit")
	public String listInit(Integer proID,HttpServletRequest request) {
		   Project project = new Project();
		    Map<String, Object> m = new HashMap<String, Object>();
		    m.put("proType", 2);
		    m.put("proProvince", getProvinceName(request));
		    project = projectService.queryProjectByLastId(m);
		    proID= project.getId();
		    request.setAttribute("proID", proID);
		    setListInit(request);
		return "regressionUseCase/list";// 指定初始化JSP页面
	}

	/**
	 * 查询项目用例列表
	 * 
	 * @return
	 */
	@RequestMapping("/listPage")
	@ResponseBody
	public Map<String, Object> listPage(UseCase c) {
		Map<String, Object> pageData = new HashMap<String, Object>();
		c.setPages();// 设置分页信息
		pageData.put("total", useCaseService.queryUseCaseSum(c));// 获取查询总记录条数
		pageData.put("rows", useCaseService.queryUseCaseList(c));// 获取查询的集合
		return pageData;
	}

	/**
	 * 修改项目用例初始化
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/updateInit")
	public String updateBillTemplate(Integer id, HttpServletRequest request) throws Exception {
		setListInit(request);
		if (id != null) {
			UseCase u = useCaseService.queryUseCaseById(id);
			u.setTicketJson(new String(u.getuCTicket()).replace('“', '"').replace('"', '＃'));
			u.setAccumulateJson(new String(u.getuCAccumulate()).replace('“', '"').replace('"', '＃'));
			u.setResourceJson(new String(u.getuCResource()).replace('“', '"').replace('"', '＃'));
			u.setExpectJson(new String(u.getuCExpect()).replace('“', '"').replace('"', '＃'));
			u.setExpAmountJson(new String(u.getuCExpAmount()).replace('“', '"').replace('"', '＃'));
			u.setExpDetailJson(new String(u.getuCExpDetail()).replace('“', '"').replace('"', '＃'));
			request.setAttribute("useCase", u);
			return "regressionUseCase/edit";
		}
		return "regressionUseCase/list";
	}

	/**
	 * 新增项目用例初始化
	 * 
	 * @return
	 */
	@RequestMapping("/addInit")
	public String addInit(Integer proID, HttpServletRequest request) {
		setListInit(request);
		SysUser sysUser = getSessionUser(request);
		UseCase u = new UseCase();
		u.setProID(proID);
		request.setAttribute("useCase", u);
		return "regressionUseCase/edit";
	}

	/**
	 * 删除项目用例
	 * 
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(UseCase b, HttpServletRequest request) {
		useCaseService.deleteUseCase(b);
	}
	
	/**
	 * 批量删除项目用例
	 * 
	 * @return
	 */
	@RequestMapping("/deleteAll")
	@ResponseBody
	public void deleteAll(String ids, HttpServletRequest request) {
		ids = Convert.withCha(ids,",");
		useCaseService.deleteUseCaseAll(ids.split(","));
	}


	/**
	 * 保存新增/修改项目用例
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public ModelAndView  save(UseCase u, HttpServletRequest request) throws Exception {
		SysUser sysUser = getSessionUser(request);

		u.setuCTicket(u.getTicketJson().getBytes());
		if(Convert.notEmpty(u.getAccumulateJson())){
			u.setuCAccumulate(u.getAccumulateJson().getBytes());
		}
		u.setuCResource(u.getResourceJson().getBytes());
		u.setuCExpect(u.getExpectJson().getBytes());
		if(Convert.notEmpty(u.getAccumulateJson())){
			u.setuCExpAmount(u.getExpAmountJson().getBytes());
		}
		u.setuCExpDetail(u.getExpDetailJson().getBytes());
		u.setAbbreviation(sysUser.getSysProvince().getAbbreviation());
		useCaseService.saveUseCase(u);
		return new ModelAndView("redirect:/regressionUseCase/listInit?proID="+u.getProID()); 
	}

	/**
	 * 设定下拉框初始值
	 * 
	 * @param request
	 */
	public void setListInit(HttpServletRequest request) {
		List<CommType> list = new ArrayList<CommType>();// 创建集合
		List<CommType> satusList = new ArrayList<CommType>();// 创建项目用例状态集合
		List<CommType> typeList = new ArrayList<CommType>();// 创建项目用例类型集合
		String[] item = { "4", "7" };// 归属类型:
										// 1.话单模版状态、2.项目用例状态、3.项目状态、4.用例状态、5.执行结果状态
										// 6.选择方式状态 7.用例执行状态 8.项目用例类型
		list = commTypeService.queryCommTypeListByIds(item);
		if (list != null && list.size() > 0) {
			// 遍历当前集合并筛选对应的对象加载到satusList以及typeList中
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				CommType commType = (CommType) iterator.next();
				if (commType == null) {
					continue;
				}
				// 判断当前对象是否为状态对象如果是则添加到satusList集合中，如果不是那么则添加到typeList集合中
				if (commType.getAffiliationType() == 4) {
					satusList.add(commType);
				} else {
					typeList.add(commType);
				}
			}
		}
		request.setAttribute("satusList", satusList);
		request.setAttribute("typeList", typeList);
	}
	
	
	/**
	 * 话单时间查询初始化
	 * 
	 * @return
	 */
	@RequestMapping(value="/billInit")
	public String billInit(String uCUserID,String scriptUrl,HttpServletRequest request) {
		CommConfig commConfig = new CommConfig();
		commConfig=commConfigService.querySysConfigByProvince(getProvinceName(request));
		request.setAttribute("commConfig", commConfig);
		request.setAttribute("uCUserID", uCUserID);
		request.setAttribute("scriptUrl", scriptUrl);
		return "regressionUseCase/setBillTime";
	}
	
	/**
	 * 获取当前产品标识的订购时间
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getBillTime")
	@ResponseBody
	public Object getBillTime(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		String uCUserID =request.getParameter("uCUserID");//当前用例的用户号
		String proId = request.getParameter("proId");//用户输入产品标识
	    Integer sceneType = Convert.strToInt(request.getParameter("sceneType"), -1);//产品套餐查询场景类型  1.套餐内 2.跨套餐
	    String ipAddress =request.getParameter("ipAddress");//shell脚本服务器地址
	    String hostName=request.getParameter("hostName");//shell脚本服务器用户名
	    String hostPassWord = request.getParameter("hostPassWord");//shell脚本服务器用户名的密码
	    String field1 = request.getParameter("field1");//脚本服务器路径
	    String scriptUrl = request.getParameter("scriptUrl");//脚本名称
	     //TODO 通过shell脚本工厂，调用shell脚本，并返回查询的数据
	    // TODO 注意 ：快餐类型产品业务场景又分为套餐内和夸套餐，套内查询时间格式组成为套餐开始时间+N分钟（不能超过套餐截止时间），跨套餐查询时间格式组成为套餐截止时间-N分钟（不能超过套餐开始时间）
	    map.put("sTime", DateUtil.getDay());
		return map;
	}
	
	
	/**
	 * 获取当话单用户的产品包
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getBillProduct")
	@ResponseBody
	public Object getBillProduct(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		CommConfig commConfig = new CommConfig();
		commConfig=commConfigService.querySysConfigByProvince(getProvinceName(request));
		String uCUserID =request.getParameter("uCUserID");//当前用例的用户号
	    String ipAddress =commConfig.getIpAddress();//shell脚本服务器地址
	    String hostName=commConfig.getHostName();//shell脚本服务器用户名
	    String hostPassWord = commConfig.getHostPassWord();//shell脚本服务器用户名的密码
	    String field1 = commConfig.getField1();//脚本服务器路径
	    String scriptUrl = commConfig.getField2();//查询脚本名称
	     //TODO 通过shell脚本工厂，调用shell脚本，并返回查询的数据
	    map.put("productList", "0");
	    map.put("type", "0");
		return map;
	}
	
	
	/**
	 * 获取项目基本信息
	 * @param proID
	 * @return
	 */
	public Project queryProjectById(Integer proID){
		return  projectService.queryProjectById(proID);
	}
	
	
	/**
	 * 按回归项目执行
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/executeProjectCase")
	@ResponseBody
	public Object executeProjectCase(Integer proID,HttpServletRequest request){
		Map<String,Object> pageData = new HashMap<String,Object>();
		if(proID==null || proID<=0){
			pageData.put("type", -1);
			pageData.put("msg", "请选择需要执行的项目！");
			return pageData;
		}
		//获取脚本服务器信息
		CommConfig commConfig = new CommConfig();
		commConfig=commConfigService.querySysConfigByProvince(getProvinceName(request));
	    String ipAddress =commConfig.getIpAddress();//shell脚本服务器地址
	    String hostName=commConfig.getHostName();//shell脚本服务器用户名
	    String hostPassWord = commConfig.getHostPassWord();//shell脚本服务器用户名的密码
	    String field1 = commConfig.getField1();//脚本服务器路径
	    String scriptUrl = commConfig.getField2();//查询脚本名称	
	  //获取脚本服务器信息
		List<UseCase> list = new ArrayList<UseCase>();
		UseCase c = new UseCase();
		c.setProID(proID);//设定查询用例查询条件为项目标识
		list = useCaseService.queryUseCaseListAll(c);//通过项目标识查询下属所有用例
		Integer resultType=1;//设定用例执行结果的类型 1.项目执行结果 2.用例执行结果
		for (UseCase useCase : list) {
			if(useCase==null){
				continue;
			}
	    	// 获取当前用例resultDetail详单结果预设内容，内容为多个预设结果模版，每个模版都有对应的shell脚本，通过shell脚本查询出对比的结果
		    String resultDetail = resultDetail(useCase);//当前用例执行后的详单结果（JSON字符窜）
			// 获取当前用例resultResource（免费资源结果）并通过预设结果模版查询对应的对比结果值
		    String resultResource =resultResource(useCase);//当用例执行后的免费资源结果
			// 获取当前用例resultTotal（累积量结果）,并通过预设结果模版查询对应的对比结果值。需要注意该处不是必要，可能会出现为空
			String  resultTotal = resultTotal(useCase);
			/**
			 * 需要验证当前用例是否已经存在项目执行结果，如果存在则进行覆盖，如果不存在则进行新增
			 */
			 // 1.查询当前用例执行记录
			ResultRecord record = new ResultRecord();//创建用例执行结果记录对象
			Map<String, Object> map  =new HashMap<String, Object>();//创建查询用例执行结果记录条件记录
			map.put("resultType", resultType);//设定用例执行结果类型
			map.put("proID", proID);//设定执行结果用例所属项目
			map.put("uCID", useCase.getId());//设定执行结果用例所属标识
			record=resultRecordService.queryResultRecordByLastId(map);
			if(record==null){
				//该用例没有进行过按项目执行，需要新增记录
				record = new ResultRecord();
		    	record.setResultType(1);//结果类型 1.项目执行结果 2.用例执行结果
		    	record.setProID(proID);//项目标识
		    	record.setuCID(useCase.getId());
		    	record.setuCItemNumber(1);//因为是按项目执行因此都是1次
		    	record.setIsPass(0);//isPass 是否通过 0.是 1.否 目前默认通过
		    	record.setResultDetail(resultDetail.getBytes());
		    	record.setResultResource(resultResource.getBytes());
		    	record.setResultTotal(resultTotal.getBytes());
				resultRecordService.saveResultRecord(record);
			}else{
				//该用例进行过按项目执行，只需覆盖上一次数据
				record.setIsPass(0);//isPass 是否通过 0.是 1.否 目前默认通过
		    	record.setResultDetail(resultDetail.getBytes());
		    	record.setResultResource(resultResource.getBytes());
		    	record.setResultTotal(resultTotal.getBytes());
				resultRecordService.saveResultRecord(record);
				
			}
		}
		pageData.put("type", "1");
		pageData.put("msg", "成功");
		pageData.put("successRate", "100%");//通过率
		pageData.put("successUrl", "resultRecord/listInit?proID="+proID+"&resultType=1&isPass=0");//通过率查看地址
		pageData.put("failureRate", "0%");//失败率以及查看地址
		pageData.put("failureUrl", "resultRecord/listInit?proID="+proID+"&resultType=1&isPass=1");//失败率查看地址
		return pageData;
	}
	
	/**
	 * 执行当前用例
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/executeCase")
	@ResponseBody
	public Object executeCase(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		//获取脚本服务器信息
		CommConfig commConfig = new CommConfig();
		commConfig=commConfigService.querySysConfigByProvince(getProvinceName(request));
	    String ipAddress =commConfig.getIpAddress();//shell脚本服务器地址
	    String hostName=commConfig.getHostName();//shell脚本服务器用户名
	    String hostPassWord = commConfig.getHostPassWord();//shell脚本服务器用户名的密码
	    String field1 = commConfig.getField1();//脚本服务器路径
	    String scriptUrl = commConfig.getField2();//查询脚本名称
	   //获取脚本服务器信息
//	     //TODO 通过shell脚本工厂，调用shell脚本，并返回查询的数据
		// 1.获取当前用例信息
	    String ids = Convert.withCha(request.getParameter("id"),",");//获取需要执行的用例标识
	    if(Convert.isEmpty(ids)){
	    	 map.put("type", "-1");
		 	 map.put("msg", "网络繁忙！请稍后再试！");
		 	 return map;
	    }
	    List<UseCase> cases = new ArrayList<UseCase>();//当前所有用例的集合
	    cases = useCaseService.queryUseCaseListByIds(ids.split(","));//查询当前用例
	    if(cases==null || cases.size()<=0){
	    	 map.put("type", "-1");
		 	 map.put("msg", "网络繁忙！请稍后再试！");
		 	 return map;
	    }
	    for (UseCase useCase : cases) {
	    	if(useCase==null){
	    		continue;
			}
	    	ResultRecord record = new ResultRecord();
	    	// 2.获取当前用例resultDetail详单结果预设内容，内容为多个预设结果模版，每个模版都有对应的shell脚本，通过shell脚本查询出对比的结果
		    String resultDetail = resultDetail(useCase);//当前用例执行后的详单结果（JSON字符窜）
			// 3.获取当前用例resultResource（免费资源结果）并通过预设结果模版查询对应的对比结果值
		    String resultResource =resultResource(useCase);//当用例执行后的免费资源结果
			// 4.获取当前用例resultTotal（累积量结果）,并通过预设结果模版查询对应的对比结果值。需要注意该处不是必要，可能会出现为空
			String  resultTotal = resultTotal(useCase);
			// 5.校验当前用例执行的次数
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("resultType", 2);
			m.put("proID", useCase.getProID());
			m.put("uCID", useCase.getId());
			int uCItemNumber=1;//默认执行项次为1
			record = resultRecordService.queryResultRecordByLastId(m);
			if(record!=null){
				uCItemNumber = record.getuCItemNumber()+1;
			}
			// 6.写入执行结果记录表并回写当前用例的执行结果包含executeNum（执行总次数）、
			record = new ResultRecord();
	    	record.setResultType(2);//结果类型 1.项目执行结果 2.用例执行结果
	    	record.setProID(useCase.getProID());//项目标识
	    	record.setuCID(useCase.getId());
	    	record.setuCItemNumber(uCItemNumber);
	    	record.setIsPass(0);//isPass 是否通过 0.是 1.否
	    	record.setResultDetail(resultDetail.getBytes());
	    	record.setResultResource(resultResource.getBytes());
	    	record.setResultTotal(resultTotal.getBytes());
			useCase.setIsPass(18);//isPass（是否通过 17.待执行 18.是 19.否）
			useCase.setExecuteNum(uCItemNumber);
			resultRecordService.saveResultRecord(record, useCase);
		}
	    // 7.将执行结果返回给客户端
	    map.put("type", "1");
	    map.put("msg", "成功");
		return map;
	}
	
	
	
	
	/**
	 * 获取用例执行记录的详单结果
	 * @param useCase
	 * @return
	 */
	public String resultDetail(UseCase useCase){
		String  resultDetail = new String(useCase.getuCExpDetail());
		JSONObject expDetailJson=JSONObject.fromObject(resultDetail);
		JSONArray expDetailJsonArray = expDetailJson.getJSONArray("expDetailJson");
		JSONArray arrayExpDetailJson = new JSONArray();
		for (int i = 0; i < expDetailJsonArray.size(); i++) {
			JSONObject j = expDetailJsonArray.getJSONObject(i);
			String tablename =j.getString("tablename");//需要查询的表名("tablename":"t_pro")
			String fieldNames = j.getString("fieldNames");//需要查询的字段，以及字段的预设结果值("fieldNames":"proId@1^proName@2")
			String scripturl = j.getString("scripturl");//shell脚本名称（shell脚本的全路径需要在方法的CommConfig获取）
			String defaults = j.getString("defaults");//默认值
			String [] arrayFied = fieldNames.split("\\^");//proId@1^proName@2
			String newArrayFied="";
			for (int o = 0; o < arrayFied.length; o++) {
				String fielsValue = arrayFied[o];
				String [] f = fielsValue.split("@");//proId@1
				String k = f[0].toString();//查询的字段
				String v = f[1].toString();//查询的字段预期的设定的结果值
				//执行shell脚本并返回该查询的实际值  TODO
				String resultV="1";//查询返回的实际值
				newArrayFied += k+"@"+v+"|"+resultV+"^";//proId@1|1^
			}
			newArrayFied = Convert.withCha(newArrayFied, "^");
			j.put("fieldNames", newArrayFied);
			arrayExpDetailJson.add(j);
		}
		expDetailJson.put("expDetailJson", arrayExpDetailJson);
		
		return expDetailJson.toString();
	}
	
	/**
	 * 获取当前用例执行记录的免费资源结果
	 * @param useCase
	 * @return
	 */
	public String resultResource(UseCase useCase){
		String resultResource = new String(useCase.getuCExpect());//免费资源结果
		JSONObject jsonObject = JSONObject.fromObject(resultResource);
		JSONArray jsonArray = jsonObject.getJSONArray("uCExpect");
		JSONArray array = new JSONArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject json_to_string = jsonArray.getJSONObject(i);
			json_to_string.put("realityVal", 1);
			array.add(json_to_string);
		}
		jsonObject.put("uCExpect", array);
		return jsonObject.toString();
	}
	
	
	/**
	 * 获取当前用例执行记录的累积量结果
	 * @param useCase
	 * @return
	 */
	public String resultTotal(UseCase useCase){
		if(useCase.getuCExpAmount()==null || useCase.getuCExpAmount().length<=0){
			return "";
		}
		String resultTotal = new String(useCase.getuCExpAmount());//免费资源结果
		if(Convert.isEmpty(resultTotal)){
			return "";
		}
		JSONObject jsonObject = JSONObject.fromObject(resultTotal);
		JSONArray jsonArray = jsonObject.getJSONArray("uCExpAmount");
		JSONArray array = new JSONArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject json_to_string = jsonArray.getJSONObject(i);
			json_to_string.put("realityVal", 1);
			array.add(json_to_string);
		}
		jsonObject.put("uCExpAmount", array);
		return jsonObject.toString();
	}
}