package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.guoll.modules.product_customer.bean.ProductBusinessType;
import com.guoll.modules.product_customer.service.ProductBusinessTypeService;
import com.guoll.modules.sysmanage.bean.SysUser;
import com.guoll.modules.useCase.bean.UseCase;

import util.fields.DetailExpectFieldForParseJson;
import util.fields.ResorceExpectFieldForPaseJson;
import util.fields.ResourceFiledForParseJson;
import util.log.LogUtils;

/**
 * 相关执行shell工具类
 * 
 * @author wangb
 *
 */
public class ExecutorOfShell {

	public ExecutorOfShell(String ip, String usr, String pasword) {
		executor = new RemoteShellExecutor(ip, usr, pasword);
	}

	private RemoteShellExecutor executor;

	/**
	 * 单个产品的免费资源发送执行
	 * 
	 * @param ResourceFiledForParseJson
	 *            filedForParseJson
	 * @param String
	 *            useCaseMark 用例标记,用户的手机号
	 * @param shellPath
	 *            脚本路径
	 * @param shellName
	 *            脚本名称
	 * @return
	 * @throws Exception
	 */
	public String sendAndExecuteResource(ResourceFiledForParseJson filedForParseJson, String useCaseMark,
			String shellPath, String shellName, HttpServletRequest request, SysUser sysUser, LogUtils logUtils)
			throws Exception {
		// 拼接执行命令
		String cmds = null;
		if (filedForParseJson.getTxtProCarrVal() == null || filedForParseJson.getTxtProCarrVal().equals("")) {
			cmds = shellPath + "/" + shellName + " " + useCaseMark + " " + filedForParseJson.getProName() + " "
					//+ filedForParseJson.getType() + " " 
					+ filedForParseJson.getVal();
		} else {
			// "source " +
			cmds = shellPath + "/" + shellName + " " + useCaseMark + " " + filedForParseJson.getProName() + " "
					//+ filedForParseJson.getType() + " " 
					+ filedForParseJson.getVal() + " "
					+ filedForParseJson.getTxtProCarrVal();
		}
		return executor.exec(cmds, request, sysUser, logUtils);
	}

	/**
	 * 单个产品的免费资源发送执行
	 * 
	 * @param filedForParseJson
	 *            免费资源解析包装对象
	 * @param uCUserID
	 *            电话号码
	 * @param shellPath
	 *            脚本路径
	 * @param shellName
	 *            脚本名
	 * @param sysUser
	 *            使用者
	 * @param logUtils
	 *            日志工具
	 * @param realPath
	 *            项目绝对路径
	 * @param operation
	 *            操作
	 * @param fileName
	 *            日志文件名
	 * @return
	 * @throws Exception
	 */
	public String sendAndExecuteResource(ResourceFiledForParseJson filedForParseJson, String uCUserID, String shellPath,
			String shellName, SysUser sysUser, LogUtils logUtils, String realPath, String operation, String fileName)
			throws Exception {
		// 拼接执行命令
		String cmds = null;
		if (filedForParseJson.getTxtProCarrVal() == null || filedForParseJson.getTxtProCarrVal().equals("")) {
			cmds = shellPath + "/" + shellName + " " + uCUserID + " " + filedForParseJson.getProName() 
					+ " " + filedForParseJson.getVal()
					+ " "	+ filedForParseJson.getType();
		} else {
			// "source " +
			cmds = shellPath + "/" + shellName + " " + uCUserID + " " + filedForParseJson.getProName() 
					+" "+ filedForParseJson.getVal() 
					+" "+ filedForParseJson.getTxtProCarrVal()
					+" "+ filedForParseJson.getType() ;
		}
		return executor.exec(cmds, sysUser, logUtils, realPath, operation, fileName);
	}

	/**
	 * 原始话单生成标准话单
	 * 
	 * @param fileName
	 *            原始话单文件名(java代码生成的)
	 * @param shellPath
	 *            脚本路径
	 * @param shellName
	 *            脚本名称
	 * @return
	 * @throws Exception
	 */
	public String toStandardBill(String fileName, String shellPath, String shellName, HttpServletRequest request,
			SysUser sysUser, LogUtils logUtils) throws Exception {
		String cmds = shellPath + "/" + shellName + " " + fileName;
		return executor.exec(cmds, request, sysUser, logUtils);
	}

	/**
	 * 原始话单生成标准话单
	 * 
	 * @param billFileName
	 *            原始话单文件名
	 * @param shellPath
	 *            脚本路径
	 * @param shellName
	 *            脚本名称
	 * @param sysUser
	 *            用户
	 * @param logUtils
	 *            日志工具
	 * @param realPath
	 *            项目绝对路径
	 * @param operation
	 *            操作
	 * @param fileName
	 *            日志文件名称
	 * @return
	 * @throws Exception
	 */
	public String toStandardBill(String billFileName, String shellPath, String shellName, SysUser sysUser,
			LogUtils logUtils, String realPath, String operation, String fileName,String billType) throws Exception {
		String cmds = shellPath + "/" + shellName + " " + sysUser.getUser_code() + " " + billFileName;
		return executor.exec(cmds, sysUser, logUtils, realPath, operation, fileName);
	}

	/**
	 * 执行标准话单的拷贝
	 * 
	 * @param fileName
	 *            标准话单
	 * @param shellPath
	 *            脚本路径
	 * @param shellName
	 *            脚本名称
	 * @return
	 * @throws Exception
	 */
	public String standardBillMove(String fileName, String shellPath, String shellName, HttpServletRequest request,
			SysUser sysUser, LogUtils logUtils) throws Exception {
		String cmds = shellPath + "/" + shellName + " " + fileName;/// home/zftest/zftest/script/movecdr.sh
																	/// negw.01.1493865322.1122334455.0000000000.201705.20170504.CG21
		return executor.exec(cmds, request, sysUser, logUtils);
	}

	/**
	 * 执行标准话单的拷贝
	 * 
	 * @param standardBill
	 *            标准话单
	 * @param shellPath
	 *            脚本路径
	 * @param shellName脚本名称
	 * @param sysUser
	 *            用户
	 * @param logUtils
	 *            日志工具
	 * @param realPath
	 *            项目绝对路径
	 * @param operation
	 *            操作
	 * @param fileName
	 *            日志文件名
	 * @return
	 * @throws Exception
	 */
	public String standardBillMove(String standardBill, String shellPath, String shellName, SysUser sysUser,
			LogUtils logUtils, String realPath, String operation, String fileName) throws Exception {
		String cmds = shellPath + "/" + shellName + " " + standardBill;/// home/zftest/zftest/script/movecdr.sh
		/// negw.01.1493865322.1122334455.0000000000.201705.20170504.CG21
		return executor.exec(cmds, sysUser, logUtils, realPath, operation, fileName);
	}

	/**
	 * 获取免费资源结果
	 * 
	 * @param useCase
	 * @param shellPath
	 * @param shellName
	 * @throws Exception
	 */
	public void getResourceResult(UseCase useCase, String shellPath, String shellName, HttpServletRequest request,
			SysUser sysUser, LogUtils logUtils) throws Exception {
		String resourceExp = new String(useCase.getuCExpect(), "GBK");
		resourceExp = resourceExp.substring(resourceExp.indexOf("["), resourceExp.indexOf("]") + 1);
		List<ResorceExpectFieldForPaseJson> list = JSON.parseArray(resourceExp, ResorceExpectFieldForPaseJson.class);
		for (ResorceExpectFieldForPaseJson resorceExpectFieldForPaseJson : list) {
			String cmds = shellPath + "/" + shellName + " " + useCase.getuCUserID() + " "
					+ resorceExpectFieldForPaseJson.getName() //+ " " + resorceExpectFieldForPaseJson.getType()
					;
			executor.exec(cmds, request, sysUser, logUtils);
		}
	}

	/**
	 * 获取免费资源结果
	 * 
	 * @param resorceExpectFieldForPaseJson
	 * @param uCUserID
	 * @param shellPath
	 * @param shellName
	 * @param request
	 * @param sysUser
	 * @param logUtils
	 * @throws Exception
	 */
	public void getResourceResult(ResorceExpectFieldForPaseJson resorceExpectFieldForPaseJson, String uCUserID,
			String shellPath, String shellName, HttpServletRequest request, SysUser sysUser, LogUtils logUtils)
			throws Exception {
		String cmds = shellPath + "/" + shellName + " " + sysUser.getUser_code() + " " + uCUserID + " "
				+ resorceExpectFieldForPaseJson.getName() //+ " " + resorceExpectFieldForPaseJson.getType()
				;
		executor.exec(cmds, request, sysUser, logUtils);
	}

	/**
	 * 获取免费资源结果
	 * 
	 * @param useCase
	 *            用例
	 * @param shellPath
	 *            脚本路径
	 * @param shellName
	 *            脚本名称
	 * @param sysUser
	 *            用户
	 * @param logUtils
	 *            日志工具
	 * @param realPath
	 *            项目绝对路径
	 * @param operation
	 *            操作
	 * @param fileName
	 *            日志文件名
	 * @throws Exception
	 */
	public void getResourceResult(UseCase useCase, String shellPath, String shellName, SysUser sysUser,
			LogUtils logUtils, String realPath, String operation, String fileName) throws Exception {
		String resourceExp = new String(useCase.getuCExpect(), "GBK");
		resourceExp = resourceExp.substring(resourceExp.indexOf("["), resourceExp.indexOf("]") + 1);
		List<ResorceExpectFieldForPaseJson> list = JSON.parseArray(resourceExp, ResorceExpectFieldForPaseJson.class);
		for (ResorceExpectFieldForPaseJson resorceExpectFieldForPaseJson : list) {
			String cmds = shellPath + "/" + shellName + " " + sysUser.getUser_code() + " " + useCase.getuCUserID() + " "
					+ resorceExpectFieldForPaseJson.getName()+ " " + resorceExpectFieldForPaseJson.getType()
					;
			executor.exec(cmds, sysUser, logUtils, realPath, operation, fileName);
		}
	}

	/**
	 * 调用shell脚本获取详单结果 遍历详单预期 思路：把详单预期解析出来，根据详单预期查询获取详单文件，最后逐行读取文件，以键=值分解到map
	 * 
	 * @throws Exception
	 */
	public Map<String, Map<String, String>> getDetailResult(UseCase useCase, String ftpFile, String ftpFileUrl,
			int ftpFilePort, String ftpFileUsername, String ftpFilePassword, String shellPath,
			HttpServletRequest request, SysUser sysUser, LogUtils logUtils, String ftps) throws Exception {
		try {
			Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();// 新建集合，解析结果数据结构
			String detailExp = new String(useCase.getuCExpDetail(), "GBK");// 获取预期结果json
			detailExp = detailExp.substring(detailExp.indexOf("["), detailExp.indexOf("]") + 1);// 截取有用部分
			List<DetailExpectFieldForParseJson> list = JSON.parseArray(detailExp, DetailExpectFieldForParseJson.class);// 解析预期结果json
			for (DetailExpectFieldForParseJson detailExpectFieldForParseJson : list) {// 遍历预期结果，获取其中的字段
				Map<String, String> map2 = new HashMap<String, String>();
				String fieldNames = detailExpectFieldForParseJson.getFieldNames();// 获取变量组
				String cmds = shellPath + "/" + detailExpectFieldForParseJson.getScripturl() + " "
						+ useCase.getStandardBill();
				executor.exec(cmds, request, sysUser, logUtils);// 获取详单计算结果到详单结果文档中
				String localFile = "txts" + File.separator + useCase.getId() + System.currentTimeMillis()
						+ "DetailResult.txt";
				Thread.sleep(1000);
				FtpUtils.download(localFile, ftpFile, ftpFileUrl, ftpFilePort, ftpFileUsername, ftpFilePassword, ftps);// 下载详单结果到本地

				File file = new File(localFile);

				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String temp = null;
				while ((temp = br.readLine()) != null) {// 读取解析变量值
					if (temp.trim() == "") {
						continue;
					}
					if (!temp.contains(":")) {
						throw new RoamProjectException("详单结果格式不正确,标准格式:每一行:key:value");
					}
					String[] split2 = temp.split(":");
					map2.put(split2[0].trim(), split2[1].trim());
				}
				br.close();
				map.put(detailExpectFieldForParseJson.getFieldNames(), map2);
				file.delete();
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public Map<String, Map<String, String>> getDetailResult(UseCase useCase, String ftpFile, String ftpFileUrl,
			int ftpFilePort, String ftpFileUsername, String ftpFilePassword, String shellPath, SysUser sysUser,
			LogUtils logUtils, String ftps, String realPath, String operation, String fileName) throws Exception {
		try {
			Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();// 新建集合，解析结果数据结构
			if (useCase.getResultType() != null && useCase.getResultType() == 7) {
				String detailExp = new String(useCase.getuCExpDetail(), "utf-8");// 获取预期结果json
				detailExp = detailExp.substring(detailExp.indexOf("["), detailExp.indexOf("]") + 1);// 截取有用部分
				List<DetailExpectFieldForParseJson> list = JSON.parseArray(detailExp,
						DetailExpectFieldForParseJson.class);// 解析预期结果json
				for (DetailExpectFieldForParseJson detailExpectFieldForParseJson : list) {// 遍历预期结果，获取其中的字段
					Map<String, String> map2 = new HashMap<String, String>();
					String fieldNames = detailExpectFieldForParseJson.getFieldNames();// 获取变量组
					String cmds = shellPath + "/" + detailExpectFieldForParseJson.getScripturl() + " "
							+ sysUser.getUser_code() + " " + useCase.getStandardBill();
					long starttime = System.currentTimeMillis();
					executor.exec(cmds, sysUser, logUtils, realPath, operation, fileName);// 获取详单计算结果到详单结果文档中
					String localFile = "txts" + File.separator + useCase.getId() + System.currentTimeMillis()
							+ "DetailResult.txt";
					long endtime = System.currentTimeMillis();
					long time = endtime - starttime;
					logUtils.writeLog(realPath, sysUser, "时间统计，第8步,获取详单执行结果,调用脚本，历时:" + time, operation, fileName);

					Thread.sleep(0);

					long starttime2 = System.currentTimeMillis();
					FtpUtils.download(localFile, ftpFile, ftpFileUrl, ftpFilePort, ftpFileUsername, ftpFilePassword,
							ftps);// 下载详单结果到本地
					long endtime2 = System.currentTimeMillis();
					long time2 = endtime2 - starttime2;
					logUtils.writeLog(realPath, sysUser, "时间统计，第8步,获取详单执行结果,下载文件，历时:" + time2, operation, fileName);

					File file = new File(localFile);

					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
					String temp = null;
					while ((temp = br.readLine()) != null) {// 读取解析变量值
						if (temp.trim() == "") {
							continue;
						}
						if (!temp.contains("=")) {
							throw new RoamProjectException("详单结果格式不正确,标准格式:每一行:key=value");
						}
						String[] split2 = temp.split("=");
						if (split2.length>1) {
							map2.put(split2[0].trim(),split2[1] == null ? "" :  split2[1].trim());
						}else{
							map2.put(split2[0].trim(),"");
						}
					}
					br.close();
					map.put(detailExpectFieldForParseJson.getFieldNames(), map2);
					file.delete();
				}
			}else if (useCase.getResultType() != null && useCase.getResultType() == 8) {

				String detailExp = new String(useCase.getuCExpDetail(), "GBK");// 获取预期结果json
				detailExp = detailExp.substring(detailExp.indexOf("["), detailExp.indexOf("]") + 1);// 截取有用部分
				List<DetailExpectFieldForParseJson> list = JSON.parseArray(detailExp,
						DetailExpectFieldForParseJson.class);// 解析预期结果json
				for (DetailExpectFieldForParseJson detailExpectFieldForParseJson : list) {// 遍历预期结果，获取其中的字段
					Map<String, String> map2 = new HashMap<String, String>();
					String fieldNames = detailExpectFieldForParseJson.getFieldNames();// 获取变量组
					String cmds = shellPath + "/" + detailExpectFieldForParseJson.getScripturl() + " "
							+ sysUser.getUser_code()+ " " + useCase.getuCUserID() + " " + useCase.getBillStartTime();

					long starttime = System.currentTimeMillis();
					executor.exec(cmds, sysUser, logUtils, realPath, operation, fileName);// 获取详单计算结果到详单结果文档中
					String localFile = "txts" + File.separator + useCase.getId() + System.currentTimeMillis()
							+ "DetailResult.txt";
					long endtime = System.currentTimeMillis();
					long time = endtime - starttime;
					logUtils.writeLog(realPath, sysUser, "时间统计，第8步,获取详单执行结果,调用脚本，历时:" + time, operation, fileName);

					Thread.sleep(0);

					long starttime2 = System.currentTimeMillis();
					FtpUtils.download(localFile, ftpFile, ftpFileUrl, ftpFilePort, ftpFileUsername, ftpFilePassword,
							ftps);// 下载详单结果到本地
					long endtime2 = System.currentTimeMillis();
					long time2 = endtime2 - starttime2;
					logUtils.writeLog(realPath, sysUser, "时间统计，第8步,获取详单执行结果,下载文件，历时:" + time2, operation, fileName);

					File file = new File(localFile);

					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
					String temp = null;
					while ((temp = br.readLine()) != null) {// 读取解析变量值
						if (temp.trim() == "") {
							continue;
						}
						if (!temp.contains("=")) {
							throw new RoamProjectException("详单结果格式不正确,标准格式:每一行:key=value");
						}
						String[] split2 = temp.split("=");
						if (split2.length>1) {
							map2.put(split2[0].trim(),split2[1] == null ? "" :  split2[1].trim());
						}else{
							map2.put(split2[0].trim(),"");
						}
					}
					br.close();
					map.put(detailExpectFieldForParseJson.getFieldNames(), map2);
					file.delete();
				}
			
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public List<Object> getAutoProductGroup(String phoneNumber,String businessType,String shellPath,String shellName,
			SysUser sysUser,LogUtils logUtils,HttpServletRequest request, String ftpFile,String ftpFileUrl,
			int ftpFilePort,String ftpFileUsername,String ftpFilePassword,String ftps,
			ProductBusinessTypeService productBusinessTypeService) throws Exception{
		
		Map<String,Object> map=new HashMap<String,Object>();
		List<Object> GroupList=new ArrayList<Object>(2);
		try {
			Gson gson = new Gson();
			//手机号信息
			List<Map<String,String>> phoneMapList = new ArrayList<>();
			Map<String,String> phoneMap = new HashMap<>();
			phoneMap.put("phoneNumber", phoneNumber);
			phoneMapList.add(phoneMap);
			//产品信息
	        List<Map<String,String>> groupList = new ArrayList<>();
	        List<ProductBusinessType> pBusinessType = productBusinessTypeService.queryProductBusinessTypeSelect();
	        //业务类型关系
	        Map<String,String> bType = new HashMap<>();
	        for (ProductBusinessType productBusinessType : pBusinessType) {
	        	bType.put(productBusinessType.getType_id(), productBusinessType.getType_name());
			}
	        //放置业务类型和对应的产品信息
	        Map<String,List<Map<String,String>>> typeMap = new HashMap<>();
	        
			String cmds = shellPath + "/" + shellName + " " + phoneNumber +" " + businessType;
			String localFile = "txts" + File.separator + System.currentTimeMillis()
			+ "getAutoProductGroup.txt";
			executor.exec(cmds, request, sysUser, logUtils);
			Thread.sleep(1000);
			FtpUtils.download(localFile, ftpFile, ftpFileUrl, ftpFilePort, ftpFileUsername, ftpFilePassword, ftps);
			File file = new File(localFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String temp = null;
			while ((temp = br.readLine()) != null) {// 读取解析变量值
				if (temp.trim() == "") {
					continue;
				}
				String[] split = temp.split(":");
				if (split[0].contains("Main")){//判断是否为主产品
	            	Map<String,String> m = new HashMap<>();
	                String[] groups = split[1].split(";");//主产品信息  48元4G飞享套餐，prodid.1008611
	                String[] group = groups[0].split(",");//主产品详情 group[0]:48元4G飞享套餐 ;group[1]:prodid.1008611
	                m.put("productName", group[0]);//设置主产品名称
	                m.put("productId", group[1]);//设置主产品ID
	                groupList.add(m);
	            }else{ //否则为产品或子产品
	            	//获取产品和业务类型
	            	String[] typeId = split[0].split("_");//detials_1、product_1 ；
            		//都要添加到业务类型中
	            	String[] groups = split[1].split(";"); //产品或子产品信息 28元4G语音套餐，prodid.1008612；。。。
	            	if(typeMap.containsKey(typeId[1])){//如果存在当前业务类型则需要添加信息kq
	            		//获取原来的List继续添加数据
	            		List<Map<String, String>> groupDetailsList = typeMap.get(typeId[1]);
	            		for(String group:groups){	//group 28元4G语音套餐，prodid.1008612
	            			String[] g = group.split(",");  //g[0]:28元4G语音套餐;g[1]prodid.1008612
	            			Map<String,String> m = new HashMap<>();
		            		m.put("productName", g[0]);//设置子产品名称
			                m.put("productId", g[1]);//设置子产品ID
			                groupDetailsList.add(m);	
	            		}
	            	}else{//如果不存在当前业务类型
	            		//新增List
	            		List<Map<String,String>> groupDetailsList = new ArrayList<>();
	            		for(String group:groups){		//group 28元4G语音套餐，prodid.1008612
	            			String[] g = group.split(",");  //g[0]:28元4G语音套餐;g[1]prodid.1008612
	            			Map<String,String> m = new HashMap<>();
	            			m.put("productName", g[0]);//设置子产品名称
	            			m.put("productId", g[1]);//设置子产品ID
	            			groupDetailsList.add(m);
	            		}
	            		//添加当前业务类型的数据
	            		typeMap.put(typeId[1], groupDetailsList);
	            	}
            		
	            	//如果不为子产品 则需要额外显示到产品信息中 
	                if(split[0].contains("product")){
	                	for(String group:groups){		//group 28元4G语音套餐，prodid.1008612
		            		String[] g = group.split(",");  //g[0]:28元4G语音套餐;g[1]prodid.1008612
		            		Map<String,String> m = new HashMap<>();
		            		m.put("productName", g[0]);//设置产品名称
			                m.put("productId", g[1]);//设置产品ID
			                groupList.add(m);
		            	}
	                }
	            }
				temp = br.readLine();
				
			}
			br.close();
			file.delete();
			
			//添加要返回的数据 groupProductJson、groupNoJson、business_type
			//获取productJson数据
			Map<String,List<Map<String,String>>> groupMap = new HashMap<>();
			groupMap.put("groupProductJson", groupList);
			Map<String,List<Map<String,String>>> noJsonMap = new HashMap<>();
			noJsonMap.put("groupNoJson", phoneMapList);
			//返回前端需要接收的数据
			map.put("business_type",businessType);
			map.put("groupProductJson",gson.toJson(groupMap));
			map.put("groupNoJson", gson.toJson(noJsonMap));
			
			 
			//业务类型数据
			List<Map<String,Object>> l=new ArrayList<Map<String,Object>>();
			for (String typeId : typeMap.keySet()) {
				Map<String,Object> map2=new HashMap<String,Object>();
				map2.put("business_type", typeId);
				map2.put("business_type_name", bType.get(typeId));
				Map<String, List<Map<String, String>>> m = new HashMap<>();
				m.put("table"+typeId, typeMap.get(typeId));
				map2.put("products_json",gson.toJson(m));
				l.add(map2);
			}
//			
			GroupList.add(map);
			GroupList.add(l);
			return GroupList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		
		
	}
}
