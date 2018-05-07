package com.guoll.modules.useCase.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.guoll.modules.billtemplate.bean.BillTemplate;
import com.guoll.modules.billtemplate.service.BillTemplateService;
import com.guoll.modules.commConfig.bean.CommConfig;
import com.guoll.modules.commConfig.service.CommConfigService;
import com.guoll.modules.commType.bean.CommType;
import com.guoll.modules.commType.service.CommTypeService;
import com.guoll.modules.framework.base.BaseController;
import com.guoll.modules.framework.util.SessionUtils;
import com.guoll.modules.product_customer.bean.ProductBusinessType;
import com.guoll.modules.product_customer.bean.ProductGroup;
import com.guoll.modules.product_customer.bean.ProductGroupDetails;
import com.guoll.modules.product_customer.service.ProductBusinessTypeService;
import com.guoll.modules.product_customer.service.ProductGroupDetailsService;
import com.guoll.modules.product_customer.service.ProductGroupService;
import com.guoll.modules.project.bean.Project;
import com.guoll.modules.project.service.ProjectService;
import com.guoll.modules.resultRecord.service.ResultRecordService;
import com.guoll.modules.resultTemplate.bean.ResultTemplate;
import com.guoll.modules.resultTemplate.service.ResultTemplateService;
import com.guoll.modules.sysmanage.bean.SysUser;
import com.guoll.modules.useCase.bean.TaskProductGroup;
import com.guoll.modules.useCase.bean.UseCase;
import com.guoll.modules.useCase.controller.UseCaseExecutorController.Operations;
import com.guoll.modules.useCase.service.TaskProductGroupService;
import com.guoll.modules.useCase.service.UseCaseService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.Convert;
import util.DateUtil;
import util.RemoteShellExecutor;
import util.RoamProjectException;
import util.fields.BillTemplateAttribute;
import util.fields.DetailTmpFiled;
import util.fields.ResourceFiledForParseJson;

/**
 * 项目用例管理
 * 
 * @author lukas 414024003@qq.com
 * @Date 2017年3月17日 11:36:12
 * @version 1.0
 */
@Controller
@RequestMapping("/useCase")
public class UseCaseController extends BaseController {

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
	@Autowired(required = false)
	private TaskProductGroupService taskProductGroupService;
	@Autowired(required = false)
	private ProductGroupDetailsService productGroupDetailsService;
	
	@Autowired(required = false)
	private ProductBusinessTypeService productBusinessTypeService;
	
	@Autowired(required = false)
	private ProductGroupService productGroupService;
	
	@Autowired(required = false)
	private  TaskProductGroupService  taskGroupService;
	
	@RequestMapping("/initUseCasePage")
	public String initUseCasePage(HttpServletRequest request) {
		return "useCaseManage/createUseCase";
	}
	
	

	/**
	 * 项目用例入口
	 * 
	 * @return
	 */
	@RequestMapping("/listInit")
	public String listInit(Integer proID, Integer isPass, HttpServletRequest request) {
		Project p = new Project();
		p = queryProjectById(proID);
		request.setAttribute("proID", proID);
		request.setAttribute("proName", p.getProName());
		request.setAttribute("proNumber", p.getProNumber());
		if (isPass != null) {
			request.setAttribute("isPass", isPass);
		}
		setListInit(request);// 初始下拉框
		return "useCase/list";// 指定初始化JSP页面
	}

	/**
	 * 项目用例入口
	 * 
	 * @return
	 */
	@RequestMapping("/listInit2")
	public String listInit2(Integer proID, HttpServletRequest request) {
		Project p = new Project();
		p = queryProjectById(proID);
		request.setAttribute("proID", proID);
		request.setAttribute("proName", p.getProName());
		request.setAttribute("proNumber", p.getProNumber());
		setListInit(request);// 初始下拉框
		return "useCase/listAll";// 指定初始化JSP页面
	}

	// @RequestMapping("/listInit3")
	// public String listInit3(Integer proID, HttpServletRequest request) {
	//
	// Project p = new Project();
	// p = queryProjectById(proID);
	// request.setAttribute("proID", proID);
	// request.setAttribute("proName", p.getProName());
	// request.setAttribute("proNumber", p.getProNumber());
	// //setListInit(request);// 初始下拉框
	// return "useCase/edit";// 指定初始化JSP页面
	// }
	/**
	 * 查询项目用例列表
	 * 
	 * @return
	 */
	@RequestMapping("/listPage")
	@ResponseBody
	public Map<String, Object> listPage(UseCase c) {
		try {
			if (c.getProductId() != null) {
				c.setProductId(c.getProductId().trim());
			}
			Map<String, Object> pageData = new HashMap<String, Object>();
			c.setPages();// 设置分页信息
			Integer queryUseCaseSum = useCaseService.queryUseCaseSum(c);
			pageData.put("total", queryUseCaseSum);// 获取查询总记录条数
			List<UseCase> queryUseCaseList = useCaseService.queryUseCaseList(c);
			pageData.put("rows", queryUseCaseList);// 获取查询的集合
			return pageData;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询项目用例列表
	 * 
	 * @return
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public UseCase getById(Integer id, HttpServletRequest request) {
		try {
			UseCase useCase = useCaseService.queryUseCaseById(id);
			useCase.setExceptionCause(useCase.getExceptionCauseByte() != null
					? new String(useCase.getExceptionCauseByte(), "GBK") : null);
			useCase.setExceptionMessage(useCase.getExceptionMessageByte() != null
					? new String(useCase.getExceptionMessageByte(), "GBK") : null);

			// 处理每一步错误日志显示数据
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			if (useCase.getExceptionStep() == -1) {
				for (int i = 1; i < 16; i++) {
					HashMap<String, Object> map2 = new HashMap<String, Object>();
					map2.put("step", i);
					map2.put("success", 1);
					map2.put("exceptionCause", "");
					map2.put("exceptionMessage", "");
					map2.put("exceptionTrace", "");
					list.add(map2);
				}
			} else {
				for (int i = 1; i < 16; i++) {// 错误前
					HashMap<String, Object> map2 = new HashMap<String, Object>();
					if (i < useCase.getExceptionStep()) {
						map2.put("step", i);
						map2.put("success", 1);
						map2.put("exceptionCause", "");
						map2.put("exceptionMessage", "");
						map2.put("exceptionTrace", "");
					} else if (i == useCase.getExceptionStep()) {// 错误
						map2.put("step", i);
						map2.put("success", 2);
						map2.put("exceptionCause", useCase.getExceptionCause());
						map2.put("exceptionMessage", useCase.getExceptionMessage());
						map2.put("exceptionTrace", useCase.getExceptionTrace());
					}
					if (i > useCase.getExceptionStep()) {// 错误后
						map2.put("step", i);
						map2.put("success", 3);
						map2.put("exceptionCause", "");
						map2.put("exceptionMessage", "");
						map2.put("exceptionTrace", "");
					}
					list.add(map2);
				}
			}
			useCase.setExceptionList(list);
			return useCase;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询项目用例列表
	 * 
	 * @return
	 */
	@RequestMapping("/test")
	@ResponseBody
	public Object test() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("step", 1);
		map.put("success", true);
		map.put("exceptionCause", "exceptionCause");
		map.put("exceptionMessage", "exceptionMessage");
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("step", 2);
		map2.put("success", true);
		map2.put("exceptionCause", "exceptionCause");
		map2.put("exceptionMessage", "exceptionMessage");
		list.add(map);
		list.add(map2);
		return list;
	}

	/**
	 * 跳转到错误日志页面
	 */
	@RequestMapping("toFailurelog")
	public String toFailurelog(String id, HttpServletRequest request) {
		request.setAttribute("id", id);
		return "useCase/failureLog";
	}

	/**
	 * 修改项目用例初始化
	 * 
	 * @return
	 */
	@RequestMapping("/updateInit")
	public String updateBillTemplate(Integer id, HttpServletRequest request) {
		try {
			setListInit(request);
			if (id != null) {
				UseCase u = useCaseService.queryUseCaseById(id);
				u.setTicketJson(new String(u.getuCTicket(), "GBK").replace('“', '"').replace('"', '＃'));
				if (u.getuCAccumulate() != null) {
					u.setAccumulateJson(new String(u.getuCAccumulate(), "GBK").replace('“', '"').replace('"', '＃'));
				} else {
					/*
					 * u.setAccumulateJson(
					 * "{\"ExpAmount\":[{\"fileName\":\"总累积量\",\"val\":\"0\"},{\"fileName\":\"金额\",\"val\":\"0\"},{\"fileName\":\"\",\"val\":\"0\"}]}"
					 * );
					 */
				}
				u.setResourceJson(new String(u.getuCResource(), "GBK").replace('“', '"').replace('"', '＃'));
				u.setExpectJson(new String(u.getuCExpect(), "GBK").replace('“', '"').replace('"', '＃'));
				if (u.getuCExpAmount() != null) {
					u.setExpAmountJson(new String(u.getuCExpAmount(), "GBK").replace('“', '"').replace('"', '＃'));
				} else {
					u.setExpAmountJson("");
				}
				u.setExpDetailJson(new String(u.getuCExpDetail(), "GBK").replace('“', '"').replace('"', '＃'));
				request.setAttribute("useCase", u);
				return "useCase/edit";
			}
			return "useCase/list";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 新增项目用例初始化
	 * 
	 * @return
	 */
	@RequestMapping("/addInit")
	public String addInit(Integer proID, HttpServletRequest request) {
		setListInit(request);
		UseCase u = new UseCase();
		u.setProID(proID);
		request.setAttribute("useCase", u);
		return "useCase/edit";
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
		ids = Convert.withCha(ids, ",");
		useCaseService.deleteUseCaseAll(ids.split(","));
	}

	/**
	 * 保存新增/修改项目用例
	 * 
	 * @return
	 */
	@RequestMapping("/save")
	public ModelAndView save(UseCase u, HttpServletRequest request) {
		Integer id = u.getId();
		Map map1=new HashMap();
		SysUser sysUser = getSessionUser(request);
		Integer group_id=u.getGroup_id();
		String billType=u.getBillType();
		String  ucResource=null;
		String  productId=u.getProductId();
		//产品群Id不为空时
		try {
////				  if(!"".equals(group_id)&&group_id !=null){
////						ProductGroupDetails pgd=new ProductGroupDetails();
//////						TaskProductGroup	tpg=new TaskProductGroup();
////						//
//////						tpg=taskProductGroupService.queryProductGroupById(Integer.parseInt(group_id));
////						pgd.setBusiness_type(billType);
//////						tpg.getGroup_id();
////						pgd.setGroup_id(Integer.parseInt(group_id));
////						List<ProductGroupDetails> list=productGroupDetailsService.queryProductGroupDetails(pgd);
////						//查出产品群详细信息
////						if(list.size()>0){
////							String productsJson=new String(list.get(0).getProducts_json(),"GBK");
////							JSONObject json1=JSONObject.fromObject(productsJson);
////							JSONArray parr = (JSONArray) json1.getJSONArray("table"+billType);
////							// 取到产品的集合
////						JSONObject js=new  JSONObject();
////				       String resourceJson=u.getResourceJson();
////						/*String uCStauts=request.getParameter("mstr").trim();
////						String []  proName=uCStauts.split("/");*/
////				       if(!"".equals(resourceJson)&& resourceJson !=null){
////				    		js=JSONObject.fromObject(resourceJson);
////				    		/*	UseCase  us=useCaseService.queryUseCaseById(id);
////							String  cr=new String(us.getuCResource(),"utf-8");*/
////								JSONArray arr = (JSONArray) js.get("pro");
////								JSONArray arrt=new  JSONArray();
////								List listp=new ArrayList();
////								List lista=new ArrayList();
////								Map mapA=new HashMap();
////							  Map mapP=new HashMap();
////								//已选择的产品Id;集合
////								for(int i=0;i<arr.size();i++){
////									JSONObject  arj=(JSONObject) arr.get(i);
////									lista.add(arj.get("proName").toString().trim());
////									mapA.put(arj.get("proName").toString().trim(),arj);
////								}
////								//取所有的产品群集合
////								for(int i=0;i<parr.size();i++){
////									JSONObject  prj=(JSONObject) parr.get(i);
////									listp.add(prj.get("productId").toString().trim());
////									mapP.put(prj.get("productId").toString().trim(), prj);
////								}
////								
////								for(int i=0;i<listp.size();i++){
////								    JSONObject art=new  JSONObject();
////									JSONObject  prj=new  JSONObject();
////								   JSONObject  arj=new  JSONObject();
////								   
////									boolean flag=lista.contains(listp.get(i));
////									if(flag){
////										arj=(JSONObject) mapA.get(listp.get(i));
////										art.put("proName", arj.get("proName"));
////										art.put("proAway", "1");
////										art.put("status",arj.get("status"));
//										art.put("type", arj.get("type"));
//										art.put("val", arj.get("val"));
//									}else{
//										prj=(JSONObject) mapP.get(listp.get(i));
//										art.put("proName", prj.get("productId"));
//										art.put("proAway", "1");
//										art.put("status","0");
//										art.put("type", "0");
//										art.put("val", "0");
//									}
//										arrt.add(art);	
//										
//									}
//								//把得到的数组放到json1中
//								js.put("pro",arrt);
//								/*		JSONArray arr1= new  JSONArray();
//								js.put("pro", arr1);*/
////								if(arr.size()>0){
////									JSONObject  jb=(JSONObject) arr.get(0);
////									String productId =jb.getString("proName");
////									u.setProductId(productId);
////								}
//								ucResource=js.toString();
//								}else{
//									ucResource= "{\"proAway\":1,\"pro\":[]}";
//							       }
//						}
//					}
				// 取到产品的集合
			JSONObject js=new  JSONObject();
	       String resourceJson=u.getResourceJson();
			/*String uCStauts=request.getParameter("mstr").trim();
			String []  proName=uCStauts.split("/");*/
	       if(!"".equals(resourceJson)&& resourceJson !=null){
	    		js=JSONObject.fromObject(resourceJson);
	    		/*	UseCase  us=useCaseService.queryUseCaseById(id);
				String  cr=new String(us.getuCResource(),"utf-8");*/
					JSONArray arr = (JSONArray) js.get("pro");
			/*		JSONArray arr1= new  JSONArray();
					js.put("pro", arr1);*/
					if(arr.size()>0){
						JSONObject  jb=(JSONObject) arr.get(0);
						String productId1 =jb.getString("proName");
						u.setProductId(productId1);
					}
					  
	       }else{
	    	   resourceJson= "{\"proAway\":1,\"pro\":[]}";
	       }
		    u.setuCResource(resourceJson.getBytes("GBK"));
//			// 取到产品的集合
//		JSONObject js=new  JSONObject();
//       String resourceJson=u.getResourceJson();
//		/*String uCStauts=request.getParameter("mstr").trim();
//		String []  proName=uCStauts.split("/");*/
//       if(!"".equals(resourceJson)&& resourceJson !=null){
//    		js=JSONObject.fromObject(resourceJson);
//    		/*	UseCase  us=useCaseService.queryUseCaseById(id);
//			String  cr=new String(us.getuCResource(),"utf-8");*/
//				JSONArray arr = (JSONArray) js.get("pro");
//		/*		JSONArray arr1= new  JSONArray();
//				js.put("pro", arr1);*/
//				if(arr.size()>0){
//					JSONObject  jb=(JSONObject) arr.get(0);
//					String productId =jb.getString("proName");
//					u.setProductId(productId);
//				}
//				  
//       }else{
//    	   resourceJson= "{\"proAway\":1,\"pro\":[]}";
//       }
    
//		String subSequenceResourceJson = resourceJson.substring(resourceJson.indexOf("["),
//				resourceJson.indexOf("]") + 1);
//		subSequenceResourceJson = subSequenceResourceJson.replace("＃", "\"");
       
			u.setuCTicket(u.getTicketJson().getBytes("GBK"));
			if (Convert.notEmpty(u.getAccumulateJson())) {
				u.setuCAccumulate(u.getAccumulateJson().getBytes("GBK"));
			}
			
			u.setuCExpect(u.getExpectJson().getBytes("GBK"));
			if (Convert.notEmpty(u.getAccumulateJson())) {
				u.setuCExpAmount(u.getExpAmountJson().getBytes("GBK"));
			}
			u.setuCExpDetail(u.getExpDetailJson().getBytes("GBK"));
            u.setAbbreviation(sysUser.getSysProvince().getAbbreviation());
			useCaseService.saveUseCase(u);
			// return "0";
			return new ModelAndView("redirect:/useCase/listInit?proID=" + u.getProID());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			return null;
	}
	
	
	/*
	 * 
	 *创建用例
	 */

//	@RequestMapping("/createListsave")
//	public ModelAndView createListsave(UseCase u, HttpServletRequest request) {
//		Integer id = u.getId();
//		String  useCase_id=request.getParameter("useCase_id");  
//		if(!"".equals(useCase_id)&&useCase_id!=null){
//			u.setId(Integer.parseInt(useCase_id));
//		}
//		Map map1=new HashMap();
//		try {
//			// 取到产品的集合
//		JSONObject js=new  JSONObject();
//       String resourceJson=u.getResourceJson();
//		/*String uCStauts=request.getParameter("mstr").trim();
//		String []  proName=uCStauts.split("/");*/
//       if(!"".equals(resourceJson)&& resourceJson !=null){
//    		js=JSONObject.fromObject(resourceJson);
//    		/*	UseCase  us=useCaseService.queryUseCaseById(id);
//			String  cr=new String(us.getuCResource(),"utf-8");*/
//				JSONArray arr = (JSONArray) js.get("pro");
//		/*		JSONArray arr1= new  JSONArray();
//				js.put("pro", arr1);*/
//				if(arr.size()>0){
//					JSONObject  jb=(JSONObject) arr.get(0);
//					String productId =jb.getString("proName");
//					u.setProductId(productId);
//				}
//				  
//       }else{
//    	   resourceJson= "{\"proAway\":1,\"pro\":[]}";
//       }
//       u.setuCResource(resourceJson.getBytes("GBK"));
////		String subSequenceResourceJson = resourceJson.substring(resourceJson.indexOf("["),
////				resourceJson.indexOf("]") + 1);
////		subSequenceResourceJson = subSequenceResourceJson.replace("＃", "\"");
//		
//			u.setuCTicket(u.getTicketJson().getBytes("GBK"));
//			if (Convert.notEmpty(u.getAccumulateJson())) {
//				u.setuCAccumulate(u.getAccumulateJson().getBytes("GBK"));
//			}
//			
//			u.setuCExpect(u.getExpectJson().getBytes("GBK"));
//			if (Convert.notEmpty(u.getAccumulateJson())) {
//				u.setuCExpAmount(u.getExpAmountJson().getBytes("GBK"));
//			}
//			u.setuCExpDetail(u.getExpDetailJson().getBytes("GBK"));
//
//			useCaseService.saveUseCase(u);
//			// return "0";
//			return new ModelAndView("redirect:/useCase/listInit?proID=" + u.getProID());
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null;
//	}
	//
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
			for (@SuppressWarnings("rawtypes")
			Iterator iterator = list.iterator(); iterator.hasNext();) {
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
	@RequestMapping(value = "/billInit")
	public String billInit(String uCUserID, String scriptUrl, HttpServletRequest request) {
		CommConfig commConfig = new CommConfig();
		commConfig = commConfigService.querySysConfigByProvince(getProvinceName(request));
		request.setAttribute("commConfig", commConfig);
		request.setAttribute("uCUserID", uCUserID);
		request.setAttribute("scriptUrl", scriptUrl);
		return "useCase/setBillTime";
	}

	/**
	 * 获取当前产品标识的订购时间
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBillTime")
	@ResponseBody
	@SuppressWarnings("unused")
	public Object getBillTime(HttpServletRequest request) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String uCUserID = request.getParameter("uCUserID");// 当前用例的用户号
		String proId = request.getParameter("proId");// 用户输入产品标识
		Integer sceneType = Convert.strToInt(request.getParameter("sceneType"), -1);// 产品套餐查询场景类型
																					// 1.套餐内
																					// 2.跨套餐
		String ipAddress = request.getParameter("ipAddress");// shell脚本服务器地址
		String hostName = request.getParameter("hostName");// shell脚本服务器用户名
		String hostPassWord = request.getParameter("hostPassWord");// shell脚本服务器用户名的密码
		String field1 = request.getParameter("field1");// 脚本服务器路径
		String scriptUrl = request.getParameter("scriptUrl");// 脚本名称
		// TODO 通过shell脚本工厂，调用shell脚本，并返回查询的数据
		// RemoteShellExecutor remoteShellExecutor = new
		// RemoteShellExecutor(ipAddress, hostName, hostPassWord);
		// String execString = remoteShellExecutor.exec(field1 + File.separator
		// + scriptUrl);//执行脚本返回的字符串格式数据
		// TODO 注意
		// ：快餐类型产品业务场景又分为套餐内和夸套餐，套内查询时间格式组成为套餐开始时间+N分钟（不能超过套餐截止时间），跨套餐查询时间格式组成为套餐截止时间-N分钟（不能超过套餐开始时间）

		map.put("sTime", DateUtil.getDay());
		return map;
	}

	/**
	 * 获取当前话单用户的产品包
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getBillProduct")
	@ResponseBody
	public Object getBillProduct(HttpServletRequest request) {
		try {
			SysUser sysUser = getSessionUser(request);
			Map<String, String> map = new HashMap<String, String>();
			CommConfig commConfig = new CommConfig();
			commConfig = commConfigService.querySysConfigByProvince(sysUser.getPost_name());
			String uCUserID = request.getParameter("uCUserID");// 当前用例的用户号
			String ipAddress = commConfig.getIpAddress();// shell脚本服务器地址
			String hostName = commConfig.getHostName();// shell脚本服务器用户名
			String hostPassWord = commConfig.getHostPassWord();// shell脚本服务器用户名的密码
			String field1 = commConfig.getField1();// 脚本服务器路径
			String scriptUrl = commConfig.getField2();// 查询脚本名称

			// TODO 通过shell脚本工厂，调用shell脚本，并返回查询的数据
			RemoteShellExecutor remoteShellExecutor = new RemoteShellExecutor(ipAddress, hostName, hostPassWord);
			String execString = remoteShellExecutor.exec(field1 + File.separator + scriptUrl, request,
					getSessionUser(request), logUtils);// 执行脚本返回的字符串格式数据

			map.put("productList", "0");
			map.put("type", "0");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取项目基本信息
	 * 
	 * @param proID
	 * @return
	 */
	public Project queryProjectById(Integer proID) {
		return projectService.queryProjectById(proID);
	}

	/**
	 * 批量执行当前用例
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/copyCases")
	@ResponseBody
	public Map<String, Object> copyCases(HttpServletRequest request, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		SysUser sysUser =getSessionUser(request);
		String task_group_id = request.getParameter("taskgroupId");
		String[] tids = task_group_id.split(",");
		for (String antids : tids) {
			String[] ids = id.split(",");
			for (String anId : ids) {
				try {
					UseCase queryUseCaseById = useCaseService.queryUseCaseById(Integer.parseInt(anId));
					String uCName = queryUseCaseById.getuCName() + "(副本)";
					queryUseCaseById.setId(null);
					queryUseCaseById.setExceptionCause(null);
					queryUseCaseById.setExceptionCauseByte(null);
					queryUseCaseById.setEditTime(null);
					queryUseCaseById.setExceptionList(null);
					queryUseCaseById.setExceptionMessage(null);
					queryUseCaseById.setExceptionMessageByte(null);
					queryUseCaseById.setExceptionStep(-1);
					queryUseCaseById.setExceptionTrace(null);
					queryUseCaseById.setIsPass(17);
					queryUseCaseById.setCreate_time(new Date());
					queryUseCaseById.setExecuteNum(null);
					queryUseCaseById.setCopy_mark("1");
					queryUseCaseById.setTask_group_id(Integer.parseInt(antids));
					queryUseCaseById.setuCName(uCName);
					queryUseCaseById.setAbbreviation(sysUser.getSysProvince().getAbbreviation());
					useCaseService.saveUseCase(queryUseCaseById);
					map.put("msg", "复制成功");
				} catch (Exception e) {
					map.put("msg", "复制失败");
					e.printStackTrace();
				}
			}
		}
		return map;
	}

	@RequestMapping(value = "/copyMsg")
	@ResponseBody
	public Map<String, Object> copyMsg(HttpServletRequest request, String id) {
		Map<String, Object> map = new HashMap<>();
		String task_group_id = request.getParameter("taskgroupId");
		/* String Id = request.getParameter("id"); */

		// String[] ids = id.split(",");//当前台传多个id的时候 Pan:已在copyCases中切割id
		// for(String anid:ids){
		if (task_group_id == null) {
			map.put("result", "没有选择复制地址");
			return map;
		} else {
			try {
				map = copyCases(request, id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// }
		return map;
	}

	/*
	 * @RequestMapping(value = "/copyMsg")
	 * 
	 * @ResponseBody public Map<String,Object> copyMsg2(HttpServletRequest
	 * request, String id){ id = request.getParameter("id");//获取前台传的用例id String
	 * groupId = request.getParameter("groupId"); //获取groupid，用于确定文件复制地址
	 * Map<String, Object> map = new HashMap<>(); if(id == null){
	 * map.put("result", "请选择复制的文件"); return map; }else{ try {
	 * map=copyCases(request,id); //得到上传文件 } catch (Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * 
	 * }
	 * 
	 * 
	 * return map; }
	 */
	/**
	 * 批量执行当前用例
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/executeCases")
	@ResponseBody
	public Map<String, Object> executeCases(HttpServletRequest request, String id) throws Exception {
		// 1准备执行工具
		Map<String, Object> map = new HashMap<String, Object>();
		Operations operation = Operations.usecaseExecute;
		SysUser sysUser = getSessionUser(request);
		String fileName = DateUtil.getSdfTimes3();
		UseCaseExecutorController useCaseExecutor = new UseCaseExecutorController(sysUser, operation, logUtils,
				fileName, null);
		logUtils.writeLog(useCaseExecutor.realPath, sysUser, "用例执行,请求执行开始,检测请求信息", operation.name(), fileName);
		if (Convert.isEmpty(id)) {
			map.put("type", "-1");
			map.put("msg", "网络繁忙！请稍后再试！");
			logUtils.writeLog(useCaseExecutor.realPath, sysUser, "用例执行,请求执行开始,检测请求信息,请求中没有指定用例id", operation.name(),
					fileName);
			return map;
		}
		// 2 拆分获取id列表
		String[] split = id.split(",");
		ArrayList<Integer> ids = new ArrayList<Integer>();
		// 3 遍历每一个id进行执行
		Integer successRate = 0;
		Integer failureRate = 0;
		Integer cannot = 0;
		for (String anId : split) {
			try {
				if (!anId.trim().equals("")) {
					UseCase queryUseCaseById = useCaseService.queryUseCaseById(Integer.parseInt(anId));
					try {
						if (queryUseCaseById == null) {
							continue;
						}
						queryUseCaseById.setAbbreviation(sysUser.getSysProvince().getAbbreviation());
						useCaseExecutor.execute(queryUseCaseById, taskProductGroupService, productGroupDetailsService);
						/*************************************/
						// Thread.sleep (2000);
						// int uCItemNumber = queryUseCaseById.getExecuteNum();
						// queryUseCaseById.setExecuteNum(uCItemNumber+1);
						// boolean resultFlg[] = { true };//
						// 用以统计是否通过，使用数组因为是对象，所以可以做到传参是是传址，该数组将只有一个元素
						// int a=(int) (Math.random()*10);
						// if(a>=7){
						// resultFlg[0]=false;
						// }
						// byte[] resultDetail =
						// queryUseCaseById.getuCExpDetail();
						// byte[]
						// resultResource=queryUseCaseById.getuCResource();
						// byte[]
						// resultTotal=queryUseCaseById.getuCAccumulate();
						// ResultRecord record = new ResultRecord();
						//
						// record.setResultType(1);// 结果类型 1.项目执行结果
						// 2.用例执行,执行用例结果
						// record.setProID(queryUseCaseById.getProID());// 项目标识
						// record.setuCID(queryUseCaseById.getId());
						// record.setuCItemNumber(uCItemNumber+1);
						// record.setProExecuteBatch(-1);
						// record.setResultDetail(resultDetail);
						// record.setResultResource(resultResource);
						// record.setResultTotal(resultTotal);
						// if (resultFlg[0]) {
						// queryUseCaseById.setIsPass(18);// isPass（是否通过 17.待执行
						// 18.是 19.否）
						// record.setIsPass(0);// isPass 是否通过 0.是 1.否
						// } else {
						// queryUseCaseById.setIsPass(19);
						// record.setIsPass(1);
						// }
						// record.setProductId(queryUseCaseById.getProductId());
						// record.setuCUserID(queryUseCaseById.getuCUserID());
						// record.setResultType(1);// 结果类型 1.项目执行结果 2.用例执行结果
						// resultRecordService.saveResultRecord(record,
						// queryUseCaseById);// 保存执行结果

						/**************************************/
					} catch (Exception e) {
					}

					UseCase queryUseCaseById2 = useCaseService.queryUseCaseById(Integer.parseInt(anId));
					if (queryUseCaseById2.getIsPass() != null & queryUseCaseById2.getIsPass() == 18) {
						successRate++;
					} else if (queryUseCaseById2.getIsPass() != null & queryUseCaseById2.getIsPass() == 19) {
						failureRate++;
					} else if (queryUseCaseById2.getIsPass() != null & queryUseCaseById2.getIsPass() == 21) {
						cannot++;
					}
				} else {
					continue;
				}
			} catch (Exception e) {
			}
		}
		if (failureRate > 0 || cannot > 0) {
			map.put("status", 2);// 批量执行执行通过的用例个数
		} else {
			map.put("status", 1);// 批量执行执行通过的用例个数
		}
		map.put("successRate", successRate);// 批量执行执行通过的用例个数
		map.put("failureRate", failureRate);// 批量执行执行未通过的用例个数
		map.put("cannot", cannot);// 批量执行执行未完成的用例个数
		return map;
	}

	/**
	 * 执行当前用例
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/executeCase")
	@ResponseBody
	public Object executeCase(HttpServletRequest request) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		try {
			long start = System.currentTimeMillis();

			// 1准备执行工具
			Operations operation = Operations.usecaseExecute;
			SysUser sysUser = getSessionUser(request);
			String fileName = DateUtil.getSdfTimes3();
			UseCaseExecutorController useCaseExecutor = new UseCaseExecutorController(sysUser, operation, logUtils,
					fileName, null);

			logUtils.writeLog(useCaseExecutor.realPath, sysUser, "用例执行,请求执行开始,检测请求信息", operation.name(), fileName);
			// 2查询数据库获取当前用例信息
			String ids = Convert.withCha(request.getParameter("id"), ",");// 获取需要执行的用例标识
			if (Convert.isEmpty(ids)) {
				map.put("type", "-1");
				map.put("msg", "网络繁忙！请稍后再试！");
				logUtils.writeLog(useCaseExecutor.realPath, sysUser, "用例执行,请求执行开始,检测请求信息,请求中没有指定用例id", operation.name(),
						fileName);
				return map;
			}
			List<UseCase> cases = new ArrayList<UseCase>();// 当前所有用例的集合
			cases = useCaseService.queryUseCaseListByIds(ids.split(","));// 查询当前用例
			if (cases == null || cases.size() <= 0) {
				map.put("type", "-1");
				map.put("msg", "网络繁忙！请稍后再试！");
				logUtils.writeLog(useCaseExecutor.realPath, sysUser, "用例执行,请求执行开始,检测请求信息,指定id的用例不存在", operation.name(),
						fileName);
				return map;
			}

			// 3执行用例
			UseCase useCase = cases.get(0);
			useCase.setAbbreviation(sysUser.getSysProvince().getAbbreviation());
			logUtils.writeLog(useCaseExecutor.realPath, sysUser, "用例执行,请求执行开始,检测请求信息,开始执行用例" + useCase.toString2(),
					operation.name(), fileName);
			useCaseExecutor.execute(useCase, taskProductGroupService, productGroupDetailsService);

			/**********************************/
			//
			// Thread.sleep (2000);
			// int uCItemNumber = useCase.getExecuteNum();
			// useCase.setExecuteNum(uCItemNumber+1);
			// boolean resultFlg[] = { true };//
			// 用以统计是否通过，使用数组因为是对象，所以可以做到传参是是传址，该数组将只有一个元素
			// int a=(int) (Math.random()*10);
			// if(a>=7){
			// resultFlg[0]=false;
			// }
			// byte[] resultDetail = useCase.getuCExpDetail();
			// byte[] resultResource=useCase.getuCResource();
			// byte[] resultTotal=useCase.getuCAccumulate();
			// ResultRecord record = new ResultRecord();
			//
			// record.setResultType(1);// 结果类型 1.项目执行结果 2.用例执行,执行用例结果
			// record.setProID(useCase.getProID());// 项目标识
			// record.setuCID(useCase.getId());
			// record.setuCItemNumber(uCItemNumber+1);
			// record.setProExecuteBatch(-1);
			// record.setResultDetail(resultDetail);
			// record.setResultResource(resultResource);
			// record.setResultTotal(resultTotal);
			// if (resultFlg[0]) {
			// useCase.setIsPass(18);// isPass（是否通过 17.待执行 18.是 19.否）
			// record.setIsPass(0);// isPass 是否通过 0.是 1.否
			// } else {
			// useCase.setIsPass(19);
			// record.setIsPass(1);
			// }
			// record.setProductId(useCase.getProductId());
			// record.setuCUserID(useCase.getuCUserID());
			// record.setResultType(1);// 结果类型 1.项目执行结果 2.用例执行结果
			// resultRecordService.saveResultRecord(record, useCase);// 保存执行结果
			//

			/*******************************/
			// 将执行结果返回给客户端
			cases = useCaseService.queryUseCaseListByIds(ids.split(","));
			useCase = cases.get(0);
			if (useCase.getIsPass() == 18) {
				map.put("type", "1");
				map.put("msg", "成功");// 对比通过时显示信息
			} else if (useCase.getIsPass() == 19) {
				map.put("type", "1");
				map.put("msg", "失败,执行完成,执行结果与预设结果不一致");// 对比不通过时显示信息
			} else {
				map.put("type", "1");
				map.put("msg", "失败,执行未完成,请检查用例与系统配置");// 执行未完成时显示信息
			}

			long end = System.currentTimeMillis();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			// 将执行结果返回给客户端
			map.put("type", "1");
			map.put("msg", "执行失败，请检查数据设置");
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 合并免费资源数据，把备份到字符串集合的免费资源数据设置到免费资源对象中，以备使用
	 */
	public List<ResourceFiledForParseJson> margeResource(List<ResourceFiledForParseJson> list,
			Map<String, Map<String, String>> resourceTemp) {
		for (ResourceFiledForParseJson resourceFiledForParseJson : list) {
			Map<String, String> map = resourceTemp.get(resourceFiledForParseJson.getProName());
			if (map == null) {
				continue;
			}
			if (resourceFiledForParseJson.getType().equals("2")) {
				String amt = map.get("amt");
				if (amt != null && !amt.trim().equals("") && amt.contains("_")) {
					String[] split = amt.split("_");
					resourceFiledForParseJson.setVal(split[0]);
					resourceFiledForParseJson.setTxtProCarrVal(split[1]);
				}
			} else {
				resourceFiledForParseJson.setVal(map.get("amt"));
			}
		}
		return list;
	}

	/**
	 * 用例批量回归
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/regressionAll")
	@ResponseBody
	public Object regressionAll(HttpServletRequest request) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		SysUser user = getSessionUser(request);
		// 1.获取当前用例信息
		String ids = Convert.withCha(request.getParameter("id"), ",");// 获取需要执行的用例标识
		if (Convert.isEmpty(ids)) {
			map.put("type", "-1");
			map.put("msg", "请选择需要回归的用例！");
			return map;
		}
		List<UseCase> cases = new ArrayList<UseCase>();// 当前所有用例的集合
		cases = useCaseService.queryUseCaseListByIds(ids.split(","));// 查询当前用例
		if (cases == null || cases.size() <= 0) {
			map.put("type", "-1");
			map.put("msg", "请选择需要回归的用例！");
			return map;
		}
		// 2.获取当前角色的回归项目标识
		Project project = new Project();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("proType", 2);
		m.put("proProvinceID", getProvinceName(request));
		project = projectService.queryProjectByLastId(m);
		if (project == null) {
			map.put("type", "-1");
			map.put("msg", "当前省份还没有回归项目，请联系管理员添加！");
			return map;
		}
		Integer proID = -1;
		if (project.getId() == null || project.getId() <= 0) {
			map.put("type", "-1");
			map.put("msg", "当前省份还没有回归项目，请联系管理员添加！");
			return map;
		}
		proID = project.getId();
		for (UseCase useCase : cases) {
			if (useCase == null) {
				continue;
			}
			useCase.setId(null);
			useCase.setProID(proID);
			useCase.setIsPass(17);
			useCase.setExecuteNum(0);
			useCase.setuCStauts(15);
			useCase.setAbbreviation(user.getSysProvince().getAbbreviation());
			useCaseService.saveUseCase(useCase);
		}

		map.put("type", "1");
		map.put("msg", "成功");
		return map;
	}

	/**
	 * 获取用例执行记录的详单结果
	 * 
	 * @param useCase
	 * @return
	 * @throws Exception
	 */
	public String resultDetail(UseCase useCase, Map<String, Map<String, String>> map, boolean[] resultFlg,
			HttpServletRequest request) throws Exception {
		String resultDetail = new String(useCase.getuCExpDetail(), "utf-8");// 获取用的免费资源预期
		JSONObject expDetailJson = JSONObject.fromObject(resultDetail);
		JSONArray expDetailJsonArray = expDetailJson.getJSONArray("expDetailJson");
		logUtils.writeLog(request, getSessionUser(request), "用例执行,比较详单预期与计算结果,预期:" + expDetailJson);
		JSONArray arrayExpDetailJson = new JSONArray();
		for (int i = 0; i < expDetailJsonArray.size(); i++) {
			JSONObject j = expDetailJsonArray.getJSONObject(i);
			String fieldNames = j.getString("fieldNames");// 需要查询的字段，以及字段的预设结果值("fieldNames":"Mob_fee@570^Toll_fee@0^dis_id@0^duration@1")
			Map<String, String> map2 = map.get(fieldNames);// 获取对应字段的结果值集合
			logUtils.writeLog(request, getSessionUser(request),
					"用例执行,比较详单预期与计算结果,详单预期:" + expDetailJson + ";计算结果" + map2 + "比较前resultFlg=" + resultFlg[0]);
			String[] arrayFied = fieldNames.split("\\^");// proId@1^proName@2
			String newArrayFied = "";
			for (int o = 0; o < arrayFied.length; o++) {
				String fielsValue = arrayFied[o];
				String[] f = fielsValue.split("@");// proId@1
				String k = f[0].toString();// 查询的字段
				String v = f[1].toString();// 查询的字段预期的设定的结果值
				/*
				 * String resultV = ""; if (k.contains(",")) { String[] split =
				 * k.split(","); for (int i2 = 0; i2 < split.length; i++) { if
				 * (i2 != 0) { resultV += ","; } resultV += map2.get(split[i]);
				 * if (!(v.trim()).equals(map2.get(split[i]).trim())) {
				 * resultFlg[0] = false; } } } else { resultV = map2.get(k);//
				 * 字段计算结果 if (!(v.trim()).equals(resultV == null ? null :
				 * resultV.trim())) {// 对比 resultFlg[0] = false; } }
				 */
				String resultV = map2.get(k);// 执行后的实际值
				if (!compare(v, resultV)) {
					resultFlg[0] = false;
				}

				newArrayFied += k + "@" + v + "|" + resultV + "^";// proId@1|1^
			}
			newArrayFied = Convert.withCha(newArrayFied, "^");
			j.put("fieldNames", newArrayFied);
			arrayExpDetailJson.add(j);
			logUtils.writeLog(request, getSessionUser(request), "用例执行,比较详单预期与计算结果,比较后resultFlg=" + resultFlg[0]);
		}
		expDetailJson.put("expDetailJson", arrayExpDetailJson);

		return expDetailJson.toString();
	}

	/**
	 * 获取当前用例执行记录的免费资源结果
	 * 
	 * @param useCase
	 * @return
	 * @throws Exception
	 */
	public String resultResource(UseCase useCase, Map<String, Map<String, String>> resourceResultToMap,
			boolean[] resultFlg, HttpServletRequest request) throws Exception {
		String resultResource = new String(useCase.getuCExpect(), "GBK");// 免费资源结果预期json字符串
		logUtils.writeLog(request, getSessionUser(request), "用例执行,比较免费资源预期与计算结果,免费资源预期:" + resultResource);
		JSONObject jsonObject = JSONObject.fromObject(resultResource);// 免费资源预期结果json对象
		JSONArray jsonArray = jsonObject.getJSONArray("uCExpect");// 免费资源预期结果中的详细集合
		JSONArray array = new JSONArray();
		for (int i = 0; i < jsonArray.size(); i++) {// 遍历免费资源预期的每一个产品
			JSONObject proOfExp = jsonArray.getJSONObject(i);// 获取免费资源预期的每一个产品
			String type = (String) proOfExp.get("type");// 获取预期结果的产品类型：0 本月套餐
														// 1上月结转 2 本月和上月结转都有
			String proId = (String) proOfExp.get("name");// 获取预期产品的产品id
			Map<String, String> proOfResult = resourceResultToMap.get(proId);// 获取结果集中的相应产品id的产品
			logUtils.writeLog(request, getSessionUser(request), "用例执行,比较免费资源预期与计算结果,比较产品:" + proId + ",预期" + proOfExp
					+ ";计算结果:" + proOfResult + "比较前resultFlg=" + resultFlg[0]);
			if (proOfResult == null) {// 结果中没有该产品
				throw new RoamProjectException(useCase.toString2() + ",预期免费资源有产品:" + proId + ",而结果中没有该产品");
			} else {
				String amt = proOfResult.get("amt");// 获取产品执行结果的值，可能是本月_上月，本月，上月
				amt = amt.trim();
				if (type.equals("0")) {// 本月套餐
					String val = (String) proOfExp.get("val");// 获取预期值
					if (val == null) {
						if (amt != null) {
							resultFlg[0] = false;
						}
					} else if (val.trim().equals("")) {
						if (amt.trim().equals("")) {
							resultFlg[0] = false;
						}
					} else {
						if (!compare(val, amt)) {
							resultFlg[0] = false;
						}
					}
					proOfExp.put("realityVal", amt);
				} else if (type.equals("1")) {// 上月结转
					String val = (String) proOfExp.get("val");// 获取预期值
					if (val == null) {
						if (amt != null) {
							resultFlg[0] = false;
						}
					} else if (val.trim().equals("")) {
						if (amt.trim().equals("")) {
							resultFlg[0] = false;
						}
					} else {
						if (!compare(val, amt)) {
							resultFlg[0] = false;
						}
					}
					proOfExp.put("realityVal", amt);
				} else if (type.equals("2")) {// 本月套餐和上月结转都有
					if (!amt.contains("_")) {
						throw new RoamProjectException(
								useCase.toString2() + ",预期免费资源有产品:" + proId + ",该产品预期既有本月产品值,又有上月结转值,而结果中仅有一个值,无法进行比较");
					} else {
						String[] split = amt.split("_");
						String val = (String) proOfExp.get("val");// 获取本月预期值
						if (val == null) {
							if (split[0] != null) {
								resultFlg[0] = false;
							}
						} else if (val.trim().equals("")) {
							if (split[0].trim().equals("")) {
								resultFlg[0] = false;
							}
						} else {
							if (!compare(val, split[0])) {
								resultFlg[0] = false;
							}
						}
						proOfExp.put("realityVal", split[1]);
						String carryVal = proOfExp.getString("carryVal");// 获取结转预期值
						if (carryVal == null) {
							if (split[1] != null) {
								resultFlg[0] = false;
							}
						} else if (carryVal.trim().equals("")) {
							if (split[1].trim().equals("")) {
								resultFlg[0] = false;
							}
						} else {
							if (!compare(carryVal, split[1])) {
								resultFlg[0] = false;
							}
						}
						proOfExp.put("realityTextProCarrVal", split[1]);
					}
				}
				array.add(proOfExp);
			}
			logUtils.writeLog(request, getSessionUser(request),
					"用例执行,比较免费资源预期与计算结果,比较产品:" + proId + ",比较后resultFlg=" + resultFlg[0]);
		}
		jsonObject.put("uCExpect", array);
		return jsonObject.toString();
	}

	/**
	 * 获取当前用例执行记录的累积量结果
	 * 
	 * @param useCase
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String resultTotal(UseCase useCase) throws UnsupportedEncodingException {
		if (useCase.getuCExpAmount() == null || useCase.getuCExpAmount().length <= 0) {
			return "";
		}
		String resultTotal = new String(useCase.getuCExpAmount(), "GBK");// 免费资源结果
		if (Convert.isEmpty(resultTotal)) {
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

	private boolean compare(String s1, String s2) {
		if ((s1 == null && s2 != null) || (s2 == null && s1 != null)) {
			return false;
		}
		s1 = s1.trim();
		s2 = s2.trim();
		try {
			if (Integer.parseInt(s1) == Integer.parseInt(s2)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			try {
				if (Double.parseDouble(s1) == Double.parseDouble(s2)) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e2) {
				try {
					if (s1.equals(s2)) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e3) {
					return false;
				}
			}
		}
	}

	/**
	 * 上传语音用例
	 */
	@RequestMapping("/uploadYuyin")
	@ResponseBody
	public Map<String, String> uploadYuyin(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request) {

		// 获得原始文件名
		String fileName = file.getOriginalFilename();
		// 新文件名,DateUtil为自定义的返回各种格式时间的工具类
		String newFileName = DateUtil.getSdfTimes3() + "_" + fileName;

		// 获得项目的路径
		String realPath = request.getSession().getServletContext().getRealPath("/");// 返回的是:E:\tomcat7\webapps\roamProject\
		String realPath2 = realPath.substring(0, realPath.indexOf("webapps") - 1);// 返回的是:E:\tomcat7
		String path = realPath2 + File.separator + "Excels" + File.separator + DateUtil.getDay() + File.separator
				+ getSessionUser(request).getUser_code() + File.separator; // 设定文件保存的目录

		// 保存文件
		File f = new File(path);
		if (!f.exists())
			f.mkdirs();
		if (!file.isEmpty()) {
			try {
				FileOutputStream fos = new FileOutputStream(path + newFileName);
				InputStream in = file.getInputStream();
				int b = 0;
				while ((b = in.read()) != -1) {
					fos.write(b);
				}
				fos.close();
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 调用解析Excel并保存到数据库的方法
		Map<String, String> map = importIntoDb(path + newFileName, request);
		return map;

	}

	@Autowired(required = false)
	ResultTemplateService resultTemplateService;
	@Autowired(required = false)
	BillTemplateService billTemplateService;

	private Map<String, String> importIntoDb(String fileName, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		SysUser user = getSessionUser(request);
		int success = 0;
		String errnos = "";
		try {
			// 第一步，读取文件内容创建所有的项目
			// 第1步,读取文件
			  Workbook workbook = null;
		        try {
		        	workbook = new XSSFWorkbook(fileName);
		        } catch (Exception ex) {
		        	workbook = new HSSFWorkbook(new FileInputStream(fileName));
		        }
//			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileName));
			String realPath = request.getServletContext().getRealPath("/");
			String operation = "importBillTemplate";
			SysUser sysUser = getSessionUser(request);
			String fileName2 = DateUtil.getSdfTimes3();
			logUtils.writeLog(realPath, sysUser, "第1步,接收Excel文件保存到本地,本地文件:" + fileName, operation, fileName2);

			// 第2步,读取第一个sheet
			Sheet sheetAt0 = workbook.getSheetAt(0);
			// 第3步,获取行范围
			int lastRowNum = sheetAt0.getLastRowNum();
			// 第4步,遍历用例行获取项目创建数据到集合并且key没有重复
			Map<String, String> projectMap = new HashMap<String, String>();
			for (int i = 5; i <= lastRowNum; i++) {
				Row row = sheetAt0.getRow(i);
				Cell  cell=row.getCell(1);
				String  kk=getStringValue(cell); 
				String pname = getStringValue(row.getCell(1)).trim();
				String pdesc = getStringValue(row.getCell(3)).trim();
				if("".equals(pname)||pname==null){
			      break;
				}
				projectMap.put(pname, pdesc);
				
			}
			// 第5步,遍历集合创建项目
			for (String pname : projectMap.keySet()) {
				Project p = new Project();
				p.setProName(pname);
				p.setProExplain(pname);
				p.setProRemark(pname);
			//	p.setProNumber(pname);
				p.setProStatus(13);
				p.setProProvince(user.getPost_name());
				p.setProType(1);
				p.setHadExe("否");
				p.setSuccessRate("0.0%");
				p.setUseCaseCount(0);
				// 验证是否已有项目
				List<Project> queryProjectByNames = projectService.queryProjectByNames(p);
				//没有这个项目就增加1条数据
				if (queryProjectByNames.size() == 0) {
					//主动获取proNumber
					Integer  proNumber=useCaseService.getOverviewid(12);
					p.setProNumber(Integer.toString(proNumber));
					projectService.saveProject(p); 
				}
			}

			// 第二步,读取表格中特殊的标记内容
			Row row5 = sheetAt0.getRow(4);
			String name_ucUserNo = getStringValue(row5.getCell(15));// 话单中如果该字段为xxxx时,值为空
			String name_timeLong = getStringValue(row5.getCell(16));// 话单中如果该字段为xxxx时,值为空
			String name_upVolum =  getStringValue(row5.getCell(17));//话单中如果该字段为xxxx时,值为空
			String name_downVolum = getStringValue(row5.getCell(18));//话单中如果该字段为xxxx时,值为空
			String name_colum=getStringValue(row5.getCell(19));//话单中如果该字段为xxxx时,值为空
			
			
			
			//第181列是否有预备字段
			if(!"".equals(name_colum)&&name_colum !=null){
				
			}
			String name_timeStart = getStringValue(row5.getCell(26));// 话单中开始时间英文名
			Row row4 = sheetAt0.getRow(3);
			short lastCellNum = row5.getLastCellNum();// 最右列号
			Map<String, Integer> billMarks = new HashMap<String, Integer>();// 话单自选填写列标记集合
			Map<String, Integer> detialMarks = new HashMap<String, Integer>();// 详单预期自选填写列标记集合
			// 自第24列往后,为工具使用自自选填写各模板字段的值,需要将其中的英文字段名与类型和所在列号记录下来
			// 此处进行此操作,过程中进行分类放到集合中
			for (int i = 28; i <= lastCellNum; i++) {
				String type = getStringValue(row4.getCell(i));
				String name = getStringValue(row5.getCell(i));
				if (type != null && type.equals("zx-a")) {
					//话单字段
					if("xxxx".equals(name)){
						//如果字段是xxxx那么不插入
					}else{
						billMarks.put(name, i);
					}
					
				}
				if (type != null && type.equals("zx-b")) {
					//详单字段
					if("xxxx".equals(name)){
						//如果字段是xxxx那么不插入
					}else{
						detialMarks.put(name, i);
					}
				}
			}
			Set<String> keySet_detialMarks = detialMarks.keySet();// 详单预期自选填写列标记集合
			Set<String> keySet_billMarks = billMarks.keySet();// 话单自选填写列标记集合

			
			
			// 第三步,读取每一行,创建用例
			for (int i = 5; i <= lastRowNum; i++) {
				try {
					// 第1步,查询出本用例所对应的项目,用例模板，详单模板
					Row row = sheetAt0.getRow(i);
					String pname = getStringValue(row.getCell(1));
                   if("".equals(pname)||pname==null){
                	   map.put("status","0");// 是否结束,'0'导入成功,"1"代表失败
               		map.put("number", success + "");// 导入成功的数目
               		map.put("url", "http://localhost:8080/roamProject/project/listInit");
               		return  map;
                   }
					// 查询项目，要求省内唯一
					Project p = new Project();
					p.setProProvince(user.getPost_name());
					p.setProName(pname);
					List<Project> queryProjectByNames = projectService.queryProjectByNames(p);
					//插入项目时已经验证过是唯一的才插入进去的,不用验证唯一性
//					if (queryProjectByNames.size() != 1) {
//						for (int j = 0; j < queryProjectByNames.size(); j++) {
//							Project project = queryProjectByNames.get(j);
//							System.out.println(project.getProProvince() + "-" + project.getProName());
//						}
//						throw new RoamProjectException(
//								"根据产品名称查询到的项目不唯一,所需要的项目名称" + pname + ",现获取到的项目数目为" + queryProjectByNames.size());
//					}
					
					Project project = queryProjectByNames.get(0);

					// 查询话单模板
					BillTemplate billTemplateByName = null;
				String templateType = getStringValue(row.getCell(4)).trim();   //取话单类型即业务类型
					String billName = getStringValue(row.getCell(5)).trim();    //话单类型即话单模板名称
					String  rtemplateName=getStringValue(row.getCell(38)).trim(); 
					 String  cj2= getStringValue(row.getCell(6));
					 String  cj3= getStringValue(row.getCell(7));
					 String  cj5= getStringValue(row.getCell(8));
					 String  cj6=getStringValue(row.getCell(9));
					 String  cj7=getStringValue(row.getCell(10));
					try {
						BillTemplate billTemplate = new BillTemplate();
						billTemplate.setTemplateName(billName);
						billTemplate.setProvinceName(getSessionUser(request).getPost_name());
						List<BillTemplate> queryBillTemplateList = billTemplateService
								.queryBillTemplateListByName(billTemplate);
						//话单模板为唯一,不唯一说明错误直接返回
						if (queryBillTemplateList.size() != 1) {
						/*	for (int j = 0; j < queryBillTemplateList.size(); j++) {
								BillTemplate billTemplate2 = queryBillTemplateList.get(j);
								System.out.println(
										billTemplate2.getProvinceName() + "-" + billTemplate2.getTemplateName());
							}*/
							
							map.put("status", "2");
							return  map;
						}
						if("".equals(billName)||billName==null){
							map.put("status","0");// 是否结束,'0'导入成功,"1"代表失败
							map.put("number", success + "");// 导入成功的数目
							map.put("url", "http://localhost:8080/roamProject/project/listInit");
							return map;
						}
						billTemplateByName = queryBillTemplateList.get(0);
					} catch (Exception e) {
						throw e;
					}

					
					// 获取详单模板，要求省内唯一
					List<ProductBusinessType>  productTypeList= productBusinessTypeService.queryProductBusinessTypeSelect();
		
					ResultTemplate rTemplate = new ResultTemplate();
				
					for(ProductBusinessType  productType :  productTypeList){
						String  TYPE_NAME=productType.getType_name();
						String  TYPE_ID=productType.getType_id();
						//匹配模板类型
						if(templateType.equals(TYPE_NAME)){
							rTemplate.setTemplateKind(Integer.parseInt(TYPE_ID));
						}
					}
				
				/*	if(templateType.equals("语音")){
						rTemplate.setTemplateKind(1);
					}
				
					if(templateType.equals("GPRS")){
						rTemplate.setTemplateKind(2);
					}
					
					if(templateType.equals("短信/彩信")){
						rTemplate.setTemplateKind(3);
					}
				
					if(templateType.equals("Wlan")){
						rTemplate.setTemplateKind(4);
					}
				 
					if(templateType.equals("漫游")){
						rTemplate.setTemplateKind(5);
					}	
					
					if(templateType.equals("流量漫游")){
						rTemplate.setTemplateKind(6);
					}	*/
					
					rTemplate.setProvinceName(sysUser.getPost_name());
					rTemplate.setTemplateName(rtemplateName);
					List<ResultTemplate> resultTemplateListAll = resultTemplateService
							.queryResultTemplateListAll(rTemplate);
					if (resultTemplateListAll.size() != 1) {
						map.put("status", "3");
						return   map;
						
					}
					ResultTemplate resultTemplate = resultTemplateListAll.get(0);

					// 第2步,创建用例设置基本数据
					UseCase useCase = new UseCase();
					String ucname = "";
						ucname = cj2 + "-" + cj3 + "-" + cj5 + "-" + cj6+"_"+cj7;
					//是否有免费资源0.没有,1.有
						int haveSource =0;
						String haveSource1=getStringValue(row.getCell(25));
						if(!"".equals(haveSource1)&& haveSource1 !=null){
							haveSource = Integer.parseInt(getStringValue(row.getCell(25)));
							if(haveSource==0){
								if(getStringValue(row.getCell(11))!=null &&!"".equals(getStringValue(row.getCell(11)))){
									map.put("status", "4");
									map.put("rowNumber", Integer.toString(i));
									map.put("number", success + "");// 导入成功的数目
									map.put("url", "http://localhost:8080/roamProject/project/listInit");
									return  map;
								}
							}
						}
					//先默认task_group_id 为 0;
					int task_group_id=0;
					useCase.setProID(project.getId());// 项目id
					useCase.setuCUserID(getStringValue(row.getCell(15)));// 电话号码测试用
					useCase.setuCName(ucname);// 用例名
					//主动生成用例编号
					Integer ucNumber=useCaseService.getOverviewid(10);
					
					useCase.setuCNumber(sysUser.getSysProvince().getAbbreviation()+"_"+ucNumber);// 用例编号
					useCase.setuCScene(ucname);// 用例场景
					useCase.setIsPass(17);// 执行状态
					useCase.setExecuteNum(0);// 执行次数
					useCase.setuCStauts(15);// 用例状态
					useCase.setBillName(billTemplateByName.getTemplateName());// 话单模板名字
					useCase.setResultName(resultTemplate.getTemplateName());// 箱单模板名字
					useCase.setExceptionStep(-1);// 错误部位
					useCase.setResultType(resultTemplate.getTemplateType());// 详单类型
					useCase.setHaveSource(haveSource);
					useCase.setProductId(getStringValue(row.getCell(11)));
					useCase.setBillType(billTemplateByName.getTemplateType() + "");
					useCase.setTask_group_id(task_group_id);
					// 第3步，构造话单
					// 3.1解析话单模板 到map中
					String tmpAttr = new String(billTemplateByName.getTemplateAttribute(), "GBK");
					tmpAttr = tmpAttr.substring(tmpAttr.indexOf("[") + 1, tmpAttr.lastIndexOf("]"));
					tmpAttr = tmpAttr.replaceAll("“", "\"");
					tmpAttr = tmpAttr.replaceAll("”", "\"");
					tmpAttr = "[" + tmpAttr + "]";
					List<BillTemplateAttribute> parseArray = JSON.parseArray(tmpAttr, BillTemplateAttribute.class);
					Map<String, BillTemplateAttribute> map_BillTemplateAttribute = new HashMap<String, BillTemplateAttribute>();
					for (BillTemplateAttribute billTemplateAttribute : parseArray) {
						map_BillTemplateAttribute.put(billTemplateAttribute.getFieldName(), billTemplateAttribute);
					}
					// 3.2修改特别字段的值
					
					// 话单开始时间
					if(!"xxxx".equals(name_timeStart)){
					BillTemplateAttribute billTemplateAttribute_name_timeStart = map_BillTemplateAttribute
							.get(name_timeStart);
					if("".equals(billTemplateAttribute_name_timeStart) || billTemplateAttribute_name_timeStart==null){
						map.put("status", "5");
						return  map;
					}
					billTemplateAttribute_name_timeStart.setDefault(getStringValue(row.getCell(26)));
					
					map_BillTemplateAttribute.put(name_timeStart, billTemplateAttribute_name_timeStart);//赋值之后放进map去
					}
					
					// 用户电话号码
					if(!"xxxx".equals(name_ucUserNo)){
					BillTemplateAttribute billTemplateAttribute_name_ucUserNo = map_BillTemplateAttribute
							.get(name_ucUserNo);
					if("".equals(billTemplateAttribute_name_ucUserNo) || billTemplateAttribute_name_ucUserNo==null){
						map.put("status", "6");
						return  map;
					}
					billTemplateAttribute_name_ucUserNo.setDefault(getStringValue(row.getCell(15)));
					
					map_BillTemplateAttribute.put(name_ucUserNo, billTemplateAttribute_name_ucUserNo);//赋值之后放进map去
					}
					
					// 通话时长
					if(!"xxxx".equals(name_timeLong)){
					BillTemplateAttribute billTemplateAttribute_name_timeLong = map_BillTemplateAttribute
							.get(name_timeLong);
					if("".equals(billTemplateAttribute_name_timeLong) || billTemplateAttribute_name_timeLong==null){
						map.put("status", "7");
						return  map;
					}
					billTemplateAttribute_name_timeLong.setDefault(getStringValue(row.getCell(16)));
					
					map_BillTemplateAttribute.put(name_timeLong, billTemplateAttribute_name_timeLong);//赋值之后放进map去
					}
					
			
					//其他需要展示的字段   (*)
					//上行流量
					if(!"xxxx".equals(name_upVolum)){
					BillTemplateAttribute billTemplateAttribute_name_upVolum= map_BillTemplateAttribute
							.get(name_upVolum);
					if("".equals(billTemplateAttribute_name_upVolum) || billTemplateAttribute_name_upVolum==null){
						map.put("status", "8");
						return  map;
					}
					billTemplateAttribute_name_upVolum.setDefault(getStringValue(row.getCell(17)));
					map_BillTemplateAttribute.put(name_upVolum, billTemplateAttribute_name_upVolum);//赋值之后放进map去
					}
					
			       // 下行流量
					if(!"xxxx".equals(name_downVolum)){
					BillTemplateAttribute billTemplateAttribute_name_downVolum= map_BillTemplateAttribute
							.get(name_downVolum);
					if("".equals(billTemplateAttribute_name_downVolum) || billTemplateAttribute_name_downVolum==null){
						map.put("status", "9");
						return  map;
					}
					billTemplateAttribute_name_downVolum.setDefault(getStringValue(row.getCell(18)));
					map_BillTemplateAttribute.put(name_downVolum, billTemplateAttribute_name_downVolum);//赋值之后放进map去
					}
			   //预留字段 (要显示的)
					//第181列是否有预备字段  1.如果excel中是否有该字段 ,没有该字段就不用插入值
					if(!"xxxx".equals(name_colum)){
						BillTemplateAttribute billTemplateAttribute_name_colum= map_BillTemplateAttribute
								.get(name_colum);
						if("".equals(billTemplateAttribute_name_colum) || billTemplateAttribute_name_colum==null){
							map.put("status", "10");
							return  map;
						}
						billTemplateAttribute_name_colum.setDefault(getStringValue(row.getCell(19)));
						map_BillTemplateAttribute.put(name_colum, billTemplateAttribute_name_colum);//赋值之后放进map去
					}
					
					
					// 自选字段
					
					for (String key : keySet_billMarks) {
						Integer integer = billMarks.get(key);
						//当字段名为"xxxx"时不插入进去
						if("xxxx".equals(key)){
							
						}else{
							BillTemplateAttribute billTemplateAttribute = map_BillTemplateAttribute.get(key);
							if("".equals(billTemplateAttribute) || billTemplateAttribute==null){
								map.put("status", "11");
								map.put("columName", key);
								return  map;
							}
							String stringValue = getStringValue(row.getCell(integer));
							if (stringValue != null && !stringValue.trim().equals("")) {
								billTemplateAttribute.setDefault(stringValue);
								map_BillTemplateAttribute.put(key, billTemplateAttribute);//赋值之后放进map去
						                                       }
							
						}
					}
					// 3.2转换成字符串保存到用例
					String stepOne = "{\"stepOnes\":[";
					int map_BillTemplateAttribute_keySet_size = map_BillTemplateAttribute.keySet().size();
					int count = 0;
					for (String key : map_BillTemplateAttribute.keySet()) {
						BillTemplateAttribute billTemplateAttribute = map_BillTemplateAttribute.get(key);
						stepOne = stepOne + billTemplateAttribute;
						count++;
						if (count != map_BillTemplateAttribute_keySet_size) {
							stepOne = stepOne + ",";
						}
					}
					stepOne = stepOne + "]}";
					useCase.setuCTicket(stepOne.getBytes("GBK"));

					// 第4步,设置免费资源,目前只做支持一种免费资源的,只有本月套餐的产品
					
		//确定是否有两种免费资源,第二产品Id是否有值
					String proJson=null;
					String proExpJson=null;
					if(!"".equals(getStringValue(row.getCell(13))) && getStringValue(row.getCell(13)) !=null){
						//有第二种免费资源
					 proJson= "{\"proAway\":\"1\",\"pro\":[{\"proAway\":\"1\",\"proName\":\""
								+ getStringValue(row.getCell(11)) // 产品id
								+ "\",\"type\":\""+getStringValue(row.getCell(12)) +"\",\"val\":\"" + (haveSource == 1 ? getStringValue(row.getCell(20)) : 0) // 产品量
								+ "\"},"
								+"{\"proAway\":\"1\",\"proName\":\""
								+ getStringValue(row.getCell(13)) // 产品id2
								+ "\",\"type\":\""+getStringValue(row.getCell(14)) +"\",\"val\":\"" + (haveSource == 1 ? getStringValue(row.getCell(21)) : 0) // 产品量
								+"\"}]}";
						
						
						
						// 第5步,设置免费资源预期，目前支持两种免费资源的，资源本月套餐的产品的预期   (这个有问题呢)
					 proExpJson = "{\"uCExpect\":[{\"name\":\"" + getStringValue(row.getCell(11)) // 产品id1
								+ "\",\"type\":\""+getStringValue(row.getCell(12)) +"\",\"initVal\":\""
								+ (haveSource == 1 ? getStringValue(row.getCell(20)) : 0) // 产品量
								+ "\",\"val\":\"" + (haveSource == 1 ? getStringValue(row.getCell(22)) : 0) // 产品预期量1
								+ "\"},"
								+"{\"name\":\""+ getStringValue(row.getCell(13)) // 产品id2
								+ "\",\"type\":\""+getStringValue(row.getCell(14)) +"\",\"initVal\":\""
								+ (haveSource == 1 ? getStringValue(row.getCell(21)) : 0) // 产品量2
								+ "\",\"val\":\"" + (haveSource == 1 ? getStringValue(row.getCell(23)) : 0) // 产品预期量2
								+ "\"}]}";
						
				
						
					}else{
						//只有一种免费资源
						 proJson = "{\"proAway\":\"1\",\"pro\":[{\"proAway\":\"1\",\"proName\":\""
								+ getStringValue(row.getCell(11)) // 产品id
								+ "\",\"type\":\""+getStringValue(row.getCell(12)) +"\",\"val\":\"" + (haveSource == 1 ? getStringValue(row.getCell(20)) : 0) // 产品量
								+ "\"}]}";
						
						
						
						// 第5步,设置免费资源预期，目前只支持一种免费资源的，资源本月套餐的产品的预期   (这个有问题呢)
					   proExpJson = "{\"uCExpect\":[{\"name\":\"" + getStringValue(row.getCell(11)) // 产品id
								+ "\",\"type\":\""+getStringValue(row.getCell(12)) +"\",\"initVal\":\""
								+ (haveSource == 1 ? getStringValue(row.getCell(20)) : 0) // 产品量
								+ "\",\"val\":\"" + (haveSource == 1 ? getStringValue(row.getCell(22)) : 0) // 产品预期量
								+ "\"}]}";
						
					}
					useCase.setuCResource(proJson.getBytes("GBK"));
					useCase.setuCExpect(proExpJson.getBytes("GBK"));
					
					//保存完页面数据后,使用例关联到产品群
					//获取产品群名字
                    ProductGroup  pg=new ProductGroup();
                    //放入当前用户所在省份
                    pg.setProvinceSpell(user.getProvinceSpell());
                    //放入产品群名称
                    pg.setName(getStringValue(row.getCell(2)));
                 
                    
                    //取产品群JSON
                   List<ProductGroup>  listP= productGroupService.queryProductGroupListByProvince(pg);
                   if(listP.size()!=1){
                	   map.put("status","12");
                	   map.put("rowName", Integer.toString(i));
                	   return  map;
                   }
                   pg=listP.get(0);
                   String groupProductJson = new String(pg.getGroupProductJsonBytes(),"utf-8");
       			     JSONObject json=new  JSONObject();
       			  json=JSONObject.fromObject(groupProductJson);
       			  JSONArray parr = (JSONArray) json.get("groupProductJson");
       			     
       			  //取存
       			 JSONObject js=new  JSONObject();
            String  Resource=new String(useCase.getuCResource(),"GBK");
            js=JSONObject.fromObject(Resource);
			JSONArray arr = (JSONArray) js.get("pro");
			JSONArray arrt=new  JSONArray();
			List listp=new ArrayList();
			List lista=new ArrayList();
			Map mapA=new HashMap();
		  Map mapP=new HashMap();
			//已选择的产品Id;集合
			for(int k=0;k<arr.size();k++){
				JSONObject  arj=(JSONObject) arr.get(k);
				lista.add(arj.get("proName").toString().trim());
				mapA.put(arj.get("proName").toString().trim(),arj);
			}
			//取所有的产品群集合
			for(int j=0;j<parr.size();j++){
				JSONObject  prj=(JSONObject) parr.get(j);
				listp.add(prj.get("productId").toString().trim());
				mapP.put(prj.get("productId").toString().trim(), prj);
			}
			
			for(int t=0;t<listp.size();t++){
			    JSONObject art=new  JSONObject();
				JSONObject  prj=new  JSONObject();
			   JSONObject  arj=new  JSONObject();
				boolean flag=lista.contains(listp.get(t));
				if(flag){
					arj=(JSONObject) mapA.get(listp.get(t));
					art.put("proName", arj.get("proName"));
					art.put("proAway", "1");
					art.put("status","1");
					art.put("type", arj.get("type"));
					art.put("val", arj.get("val"));
				}else{
					prj=(JSONObject) mapP.get(listp.get(t));
					art.put("proName", prj.get("productId"));
					art.put("proAway", "1");
					art.put("status","0");
					art.put("type", "0");
					art.put("val", "0");
				}
					arrt.add(art);	
			}
			js.put("pro",arrt);
			useCase.setuCResource(js.toString().getBytes("GBK"));		
//					
//				
		/*			
					// 第5步,设置免费资源预期，目前只支持一种免费资源的，资源本月套餐的产品的预期   (这个有问题呢)
					String proExpJson = "{\"uCExpect\":[{\"name\":\"" + getStringValue(row.getCell(11)) // 产品id
							+ "\",\"type\":\""+getStringValue(row.getCell(12)) +"\",\"initVal\":\""
							+ (haveSource == 1 ? getStringValue(row.getCell(20)) : 0) // 产品量
							+ "\",\"val\":\"" + (haveSource == 1 ? getStringValue(row.getCell(22)) : 0) // 产品预期量
							+ "\"}]}";
					
				*/
				
					// 第6步,设置详单预期
					// 6.1 解析详单模板
					String detailTemp = new String(resultTemplate.getTemplateAttribute(), "GBK");
					detailTemp = detailTemp.substring(detailTemp.indexOf("[") + 1, detailTemp.indexOf("]"));
					detailTemp = detailTemp.replaceAll("“", "\"");
					detailTemp = detailTemp.replaceAll("”", "\"");
					detailTemp = "[" + detailTemp + "]";
					List<DetailTmpFiled> parseArray2 = JSON.parseArray(detailTemp, DetailTmpFiled.class);
					DetailTmpFiled detailTmpFiled = parseArray2.get(0);
					String[] fieldName = detailTmpFiled.getFieldName().split(",");
					String[] explain = detailTmpFiled.getExplain().split(",");
					String strForDetailExp = "";
					int size = fieldName.length;
					for (int j = 0; j < size; j++) {
						String thisFieldName = fieldName[j];
						if(detialMarks.get(thisFieldName)==null || "".equals(detialMarks.get(thisFieldName))){
							map.put("status", "13");
							map.put("detialName",thisFieldName);
							return map;
						}
						String val = getStringValue(row.getCell(detialMarks.get(thisFieldName)));
						strForDetailExp = strForDetailExp + thisFieldName + "@" + val;
						if (j != size - 1) {
							strForDetailExp = strForDetailExp + "^";
						}
					}
					String detailExp = "{\"expDetailJson\":[{\"tablename\":\"" + detailTmpFiled.getTableName()
							+ "\",\"scripturl\":\"" + detailTmpFiled.getScriptUrl() + "\",\"defaults\":\""
							+ detailTmpFiled.getDefault() + "\",\"explain\":\"" + detailTmpFiled.getExplain()
							+ "\",\"fieldNames\":\"" + strForDetailExp+"^" // 拼接如下格式的字符串：duration@通话时长^localfee@长途费^roamfee@漫游非^tollfee@长话费"
							+ "\"}]}";
					useCase.setuCExpDetail(detailExp.getBytes("GBK"));
					useCase.setAbbreviation(sysUser.getSysProvince().getAbbreviation());
				//	<!--  查询是否有这个用例-->
				UseCase useCaselist= useCaseService.queryUseCaseHave(useCase);
					if(!"".equals(useCaselist)&&useCaselist!=null){
						useCase.setId(useCaselist.getId());
						useCaseService.updateUseCase(useCase);
					}else{
						useCaseService.saveUseCase(useCase);
					}
					//取刚生成的usecase的ID
					UseCase  uCase=new  UseCase();
				UseCase  uc= useCaseService.queryUseCase(useCase);
				uCase.setId(uc.getId());
			
                   TaskProductGroup tpg=new TaskProductGroup();
                   TaskProductGroup tpg2=new TaskProductGroup();
               
                   //取用例的proId
                   tpg2.setTask_id(project.getId());
                   tpg2.setGroup_id(pg.getId());
                   tpg=taskGroupService.queryProductGroup(tpg2);
                   if(!"".equals(tpg)&& tpg !=null){
                	   //当前产品存在时不创建新的产品群
                	   uCase.setTask_group_id(tpg.getId());
                   }else{
                       //当产品不存在时创建新的产品群
                       //主动生成 TaskProductGroup 的 id
                	   TaskProductGroup tpg1=new TaskProductGroup();
                       int taskProductGroup_id= useCaseService.getOverviewid(13);
                       tpg1.setId(taskProductGroup_id);
                       tpg1.setTask_id(project.getId());
                       tpg1.setGroup_id(pg.getId());
                       tpg1.setProvinceSpell(pg.getProvinceSpell());
                       tpg1.setPhone_number_str(pg.getPhone_number_str());
                       tpg1.setProductsMark(pg.getProductsMark());
                       taskGroupService.addProductGroupToTask(tpg1);
//                       //取到 taskProductsGroup的  id 并且关联给useCase 表中的task_group_id
//                       TaskProductGroup tpg1= taskGroupService.queryProductGroup(tpg);
                       uCase.setTask_group_id(taskProductGroup_id);
                    //   tpg1.getTask_id();
                       //更新useCase表
                   }
//                   project.getId();
//                   pg.getId();
//                   pg.getProductsMark();
//                   pg.getPhone_number_str();
//                   pg.getProvinceSpell();
                   useCaseService.updateUseCaseById(uCase);
					success++;
		
		} catch (Exception e3) {
			// TODO: handle exception
			e3.printStackTrace();
		}
			}
		} catch(Exception e){
			e.printStackTrace();
			}

		map.put("status","0");// 是否结束,'0'导入成功,"1"代表失败
		map.put("number", success + "");// 导入成功的数目
		map.put("url", "http://localhost:8080/roamProject/project/listInit");
		return map;
			
	}

/*	private String getStringValue(Cell cell) {
		// TODO Auto-generated method stub
		return null;
	}*/



	private String getStringValue(Cell cell) {
		if (cell != null)
			cell.setCellType(Cell.CELL_TYPE_STRING);
		String value;
		try {
			value = cell.getStringCellValue() + "";
			return (value.trim());
		} catch (Exception e) {
		}
		try {
			value = cell.getDateCellValue() + "";
			return (value.trim());
		} catch (Exception e) {
		}
		try {
			value = cell.getNumericCellValue() + "";
			return (value.trim());
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * 同步模板与用例
	 * 
	 * @return
	 */
	@RequestMapping("/updateUseCaseTemplate")
	@ResponseBody
	public Map<String, Object> updateUseCaseTemplate(Integer id) {
		Map<String, Object> map = new HashMap<>();
		try {
			useCaseService.updateUseCaseTemplate(id);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		return map;
	}
	
	
	/*
	 * 1.新需求的操作
	 * 当点击选择模板时,绑定详单模板
	 */
	
	@RequestMapping("/rativeResultTemplete")
	@ResponseBody
	//bill_template 表中的  templateType
	public Map<String, Object> updateUseCaseTemplate1(Integer  templateType,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		ResultTemplate  rt=new ResultTemplate();
		try {
			if(templateType>0){
				//获取当前用户的信息
				SysUser sysUser = (SysUser) SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
				rt.setTemplateKind(templateType);
				String provinceName=sysUser.getPost_name();
				rt.setProvinceName(provinceName);
				ResultTemplate  rt1=resultTemplateService.queryResultTemplete(rt);
				String templateAttribute= new String(rt1.getTemplateAttribute(), "GBK");
			  map.put("templateAttribute", templateAttribute);
				map.put("success", true);
			}
			
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		return map;
	}

	private void queryResultTemplete(ResultTemplate rt) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		int a = (int) (Math.random() * 10);
		System.out.println(a);
	}
}