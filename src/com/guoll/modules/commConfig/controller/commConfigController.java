package com.guoll.modules.commConfig.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoll.modules.commConfig.bean.CommConfig;
import com.guoll.modules.commConfig.service.CommConfigService;
import com.guoll.modules.framework.base.BaseController;
import com.guoll.modules.framework.base.BaseModel;
import com.guoll.modules.sysmanage.bean.SysUser;

import util.CommConfigUtils;
import util.CommConfigUtils.CommConfigKeys;
import util.ExecutorOfShell;
import util.FtpUtils;
import util.NetStatusTest;
import util.RoamProjectException;
import util.TxtFileUtil;
import util.fields.ResorceExpectFieldForPaseJson;
import util.fields.ResourceFiledForParseJson;

/**
 * shell脚本配置管理
 * 
 * @author lukas 414024003@qq.com
 * @Date 2017年3月15日 14:09:47
 * @version 1.0
 */
@Controller
@RequestMapping("/commConfig")
public class commConfigController extends BaseController {

	@Autowired(required = false)
	CommConfigService commConfigService;

	/**
	 * 测试环境
	 */
	@RequestMapping("/testCfg")
	@ResponseBody
	@SuppressWarnings("unused")
	public Map<String, Object> testCfg(HttpServletRequest request, String uCUserID, String proName, String type,
			String val, String txtProCarrVal) {
		ResourceFiledForParseJson resourceFiledForParseJson = new ResourceFiledForParseJson();
		resourceFiledForParseJson.setProName(proName);
		resourceFiledForParseJson.setTxtProCarrVal(txtProCarrVal);
		resourceFiledForParseJson.setType(type);
		resourceFiledForParseJson.setVal(val);
		ResorceExpectFieldForPaseJson resorceExpectFieldForPaseJson = new ResorceExpectFieldForPaseJson();
		resorceExpectFieldForPaseJson.setName(proName);
		resorceExpectFieldForPaseJson.setType(type);

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 1准备基础配置
			SysUser sysUser = getSessionUser(request);
			CommConfig ftp_ftptype = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(request,
					CommConfigKeys.ftp_ftptype, commConfigService);
			CommConfig shell_setResourceOfUserInput = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(
					request, CommConfigKeys.shell_setResourceOfUserInput, commConfigService);
			ExecutorOfShell executor_setResourceOfUserInput = new ExecutorOfShell(
					shell_setResourceOfUserInput.getIpAddress(), shell_setResourceOfUserInput.getHostName(),
					shell_setResourceOfUserInput.getHostPassWord());
			CommConfig shell_getResourceResult = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(
					request, CommConfigKeys.shell_getResourceResult, commConfigService);
			ExecutorOfShell executor_getResourceResult = new ExecutorOfShell(shell_getResourceResult.getIpAddress(),
					shell_getResourceResult.getHostName(), shell_getResourceResult.getHostPassWord());
			CommConfig txt_getResourceResult = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(request,
					CommConfigKeys.txt_getResourceResult, commConfigService);

			// 2设置免费资源
			executor_setResourceOfUserInput.sendAndExecuteResource(resourceFiledForParseJson, uCUserID,
					shell_setResourceOfUserInput.getField1(), shell_setResourceOfUserInput.getField2(), request,
					sysUser, logUtils);

			// 3获取免费资源
			executor_getResourceResult.getResourceResult(resorceExpectFieldForPaseJson, uCUserID,
					shell_getResourceResult.getField1(), shell_getResourceResult.getField2(), request, sysUser,
					logUtils);

			// 4下载免费资源结果文件
			long idforfill = System.currentTimeMillis();
			Thread.sleep(500);
			FtpUtils.download("txts" + File.separator + idforfill + "ResourceResult.txt",
					txt_getResourceResult.getField1() + "/" + sysUser.getUser_code() + "_"
							+ txt_getResourceResult.getField2(),
					txt_getResourceResult.getIpAddress(), 21, txt_getResourceResult.getHostName(),
					txt_getResourceResult.getHostPassWord(), ftp_ftptype.getField1());

			// 5解析免费资源结果
			TxtFileUtil txtFileUtil = new TxtFileUtil();
			Map<String, Map<String, String>> resourceResultToMap = txtFileUtil
					.txtResourceResultToMap("txts" + File.separator + idforfill + "ResourceResult.txt");

			// 6比较结果
			// 获取结果
			Map<String, String> map2 = resourceResultToMap.get(proName);
			if (map2 == null) {
				map.put("success", false);
				map.put("failReasion", "设置免费资源后从返回的结果中没有获取到同id的产品数据");
				map.put("proName", "");
				map.put("val", "");
				map.put("txtProCarrVal", "");
				return map;
			}
			String amt = map2.get("value_residue");
			if (type.equals("2")) {
				String[] split = amt.split("_");// 既有结转又有本月时以_分割
				if (split[0].trim().equals(val.trim()) && split[1].trim().equals(txtProCarrVal.trim())) {
					map.put("success", true);
				} else {
					map.put("success", false);
					map.put("failReasion", "设置免费资源后从返回的结果中与设置的值不匹配");
				}
				map.put("proName", proName);
				map.put("val", split[0]);
				map.put("txtProCarrVal", split[1]);
				return map;
			} else {
				if (amt.trim().equals(val.trim())) {
					map.put("success", true);
				} else {
					map.put("success", false);
					map.put("failReasion", "设置免费资源后从返回的结果中与设置的值不匹配");
				}
				map.put("proName", proName);
				map.put("val", amt);
				map.put("txtProCarrVal", "");
				return map;
			}

		} catch (Exception e) {
			map.put("success", false);
			map.put("failReasion", "测试异常");
			map.put("proName", "");
			map.put("val", "");
			map.put("txtProCarrVal", "");
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 获取当前电话号码的免费资源
	 */
	@RequestMapping("/testCfg2")
	@ResponseBody
	@SuppressWarnings("unused")
	public Map<String, Object> testCfg2(HttpServletRequest request, String uCUserID, String proName, String type) {
		ResourceFiledForParseJson resourceFiledForParseJson = new ResourceFiledForParseJson();
		resourceFiledForParseJson.setProName(proName);
		resourceFiledForParseJson.setType(type);
		ResorceExpectFieldForPaseJson resorceExpectFieldForPaseJson = new ResorceExpectFieldForPaseJson();
		resorceExpectFieldForPaseJson.setName(proName);
		resorceExpectFieldForPaseJson.setType(type);

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 1准备基础配置
			SysUser sysUser = getSessionUser(request);
			CommConfig ftp_ftptype = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(request,
					CommConfigKeys.ftp_ftptype, commConfigService);
			CommConfig shell_setResourceOfUserInput = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(
					request, CommConfigKeys.shell_setResourceOfUserInput, commConfigService);
			ExecutorOfShell executor_setResourceOfUserInput = new ExecutorOfShell(
					shell_setResourceOfUserInput.getIpAddress(), shell_setResourceOfUserInput.getHostName(),
					shell_setResourceOfUserInput.getHostPassWord());
			CommConfig shell_getResourceResult = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(
					request, CommConfigKeys.shell_getResourceResult, commConfigService);
			ExecutorOfShell executor_getResourceResult = new ExecutorOfShell(shell_getResourceResult.getIpAddress(),
					shell_getResourceResult.getHostName(), shell_getResourceResult.getHostPassWord());
			CommConfig txt_getResourceResult = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(request,
					CommConfigKeys.txt_getResourceResult, commConfigService);

			// 3获取免费资源
			executor_getResourceResult.getResourceResult(resorceExpectFieldForPaseJson, uCUserID,
					shell_getResourceResult.getField1(), shell_getResourceResult.getField2(), request, sysUser,
					logUtils);

			// 4下载免费资源结果文件
			long idforfill = System.currentTimeMillis();
			Thread.sleep(1500);
			FtpUtils.download("txts" + File.separator + idforfill + "ResourceResult.txt",
					txt_getResourceResult.getField1() + "/" + sysUser.getUser_code() + "_"
							+ txt_getResourceResult.getField2(),
					txt_getResourceResult.getIpAddress(), 21, txt_getResourceResult.getHostName(),
					txt_getResourceResult.getHostPassWord(), ftp_ftptype.getField1());

			// 5解析免费资源结果
			TxtFileUtil txtFileUtil = new TxtFileUtil();
			Map<String, Map<String, String>> resourceResultToMap = txtFileUtil
					.txtResourceResultToMap("txts" + File.separator + idforfill + "ResourceResult.txt");
			Map<String, String> map2 = resourceResultToMap.get(proName);
			if (map2 == null) {
				// map.put("", value)
				throw new RoamProjectException("当前手机号没有该产品");
			}
			String amt = map2.get("value_residue");
			if (amt.contains("_")) {
				String[] split = amt.split("_");// 既有结转又有本月时以_分割
				map.put("proName", proName);
				map.put("val", split[0]);
				map.put("txtProCarrVal", split[1]);
			} else {
				map.put("proName", proName);
				map.put("val", amt);
				map.put("txtProCarrVal", "");
			}
			return map;
		} catch (Exception e) {
			map.put("success", false);
			map.put("failReasion", "测试异常");
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 测试网络连接
	 */
	@RequestMapping("/testNet")
	@ResponseBody
	public Map<String, Object> testNet(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		try {
			Map<String, CommConfig> provinceConfig = CommConfigUtils.getProvinceConfig(request, commConfigService);
			Set<String> keySet = provinceConfig.keySet();
			String ipSets = "以下ip存在问题：";
			boolean success = true;
			for (String key : keySet) {
				CommConfig commConfig = provinceConfig.get(key);
				if (commConfig.getField3().equals("ftp_ftptype")) {// 某些不需要测试的配置直接跳过
					continue;
				}
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("ipAddress", commConfig.getIpAddress());// 测试ip
				map2.put("describe", commConfig.getField4());// 测试ip的作用描述
				map2.put("status", 0);// 本ip的状态，0测试中，1畅通 ,2不通
				listmap.add(map2);
				request.getServletContext().setAttribute(getSessionUser(request).getPost_name() + "ipTest", listmap);
				if (NetStatusTest.isConnect(commConfig.getIpAddress()) == false) {
					map2.put("status", 2);// 本ip的状态，0测试中，1畅通 ,2不通
					ipSets = ipSets + commConfig.getField4() + "(" + commConfig.getIpAddress() + ")" + ";";
					success = false;
				} else {
					map2.put("status", 1);// 本ip的状态，0测试中，1畅通 ,2不通
				}
				request.getServletContext().setAttribute(getSessionUser(request).getPost_name() + "ipTest", listmap);
			}
			if (success) {
				ipSets = "网络畅通";
			}
			map.put("ipSets", ipSets);// 没有通过的ip
			map.put("success", success);// 是否全部测试通过,true / false

		} catch (Exception e) {
			map.put("success", false);
			map.put("ipSets", "系统故障");// 报错
			e.printStackTrace();
		}
		request.getServletContext().removeAttribute(getSessionUser(request).getPost_name() + "ipTest");
		return map;
	}

	/**
	 * 测试网络连接
	 */
	@RequestMapping("/getIpTestStu")
	@ResponseBody
	public List<Map<String, Object>> getIpTestStu(HttpServletRequest request) {
		Object attribute = null;
		int count = 0;
		while (attribute == null && count < 30) {
			attribute = request.getAttribute(getSessionUser(request).getPost_name() + "ipTest");
		}
		if (attribute == null) {
			return null;
		} else {
			return (List<Map<String, Object>>) attribute;
		}
	}

	/**
	 * 测试网络连接跳转
	 */
	@RequestMapping("/toLinkTest")
	public String toLinkTest(HttpServletRequest request) {
		return "commConfig/linkTest";
	}

	/**
	 * 测试某个ip网络连接
	 */
	@RequestMapping("/testNet2")
	@ResponseBody
	public Map<String, Object> testNet2(String ip) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (NetStatusTest.isConnect(ip) == false) {
				map.put("success", false);
				map.put("ipSets", "以下ip连接<font style='color:red'>存在问题</font>:" + ip);// 不通
			} else {
				map.put("success", true);
				map.put("ipSets", "以下ip连接<font style='color:red'>畅通</font>:" + ip);// 通
			}
		} catch (Exception e) {
			map.put("success", false);
			map.put("ipSets", "系统故障");// 报错
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 测试脚本弹窗
	 */
	@RequestMapping("/gainResour")
	public String gainResour() {
		return "commConfig/gainResour";
	}

	/**
	 * 测试脚本弹窗
	 */
	@RequestMapping("/testCftInit")
	public String testCftInit() {
		return "commConfig/setResour";
	}

	/**
	 * shell脚本配置入口
	 * 
	 * @return
	 */
	@RequestMapping("/listInit")
	public String listInit(HttpServletRequest request) {
		provinceIDInit(request);
		return "commConfig/list";
	}

	/**
	 * 查询shell脚本配置列表
	 * 
	 * @return
	 */
	@RequestMapping("/listPage")
	@ResponseBody
	public Map<String, Object> listPage(CommConfig c, HttpServletRequest request) {
		c.setProvinceName(getProvinceName(request));
		Map<String, Object> pageData = new HashMap<String, Object>();
		c.setPages();// 设置分页信息
		c.setProvinceName(getSessionUser(request).getPost_name());
		pageData.put("total", commConfigService.queryConfigSum(c));
		pageData.put("rows", commConfigService.queryConfigList(c));
		return pageData;
	}

	/**
	 * 修改shell脚本配置初始化
	 * 
	 * @return
	 */
	@RequestMapping("/updateInit")
	public String updateInit(Integer id, HttpServletRequest request) {
		CommConfig c = commConfigService.querySysConfigById(id);
		request.setAttribute("commConfig", c);
		/*
		 * if (id != null) { if (c.getField5().equals("0")) {
		 * 
		 * }else if (c.getField5().equals("1")) {
		 * 
		 * }else if (c.getField5().equals("2")){
		 * 
		 * } return "commConfig/edit"; }
		 */
		return "commConfig/amend";
	}

	/**
	 * 新增shell脚本配置初始化
	 * 
	 * @return
	 */
	@RequestMapping("/addInit")
	public String addInit(Integer org_id, HttpServletRequest request) {
		SysUser sysUser = getSessionUser(request);
		CommConfig c = new CommConfig();
		c.setProvinceName(sysUser.getPost_name());
		request.setAttribute("commConfig", c);
		return "commConfig/edit";
	}

	/**
	 * 删除shell脚本配置
	 * 
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(CommConfig c, HttpServletRequest request) {
		commConfigService.deleteCommConfig(c);
		;
	}

	/**
	 * 保存shell脚本配置
	 * 
	 * @return
	 */
	@RequestMapping("/save")
	public String save(CommConfig c, HttpServletRequest request) {
		c.setProvinceName(getProvinceName(request));
		commConfigService.saveCommConfig(c);
		request.setAttribute("close", "close");
		// 如果是系统配置,更新应用域的存储
		if (c.getField5().trim().equals("0") || c.getField5().trim().equals("1") || c.getField5().trim().equals("2")
				|| c.getField5().trim().equals("3")) {
			CommConfigUtils.updateProvinceConfig(request, c, commConfigService);
		}
		return "commConfig/amend";
	}

	/**
	 * 保存shell脚本配置
	 * 
	 * @return
	 */
	@RequestMapping("/saveSysConfig")
	@ResponseBody
	public String saveSysConfig(@RequestBody CommConfig CommConfig, HttpServletRequest request) {
		try {
			CommConfig.setProvinceName(getProvinceName(request));
			SysUser sysUser = getSessionUser(request);
			CommConfig.setProvinceName(sysUser.getPost_name());
			List<CommConfig> list = commConfigService.querySysConfigList(CommConfig);
			if (list.size() > 0 && list.get(0) != null) {
				CommConfig.setId(list.get(0).getId());
			}
			CommConfig.setField1(CommConfig.getField1());
			CommConfig.setField2(CommConfig.getField2());
			CommConfig.setField3(CommConfig.getField3());
			CommConfig.setField4(CommConfig.getField4());
			CommConfig.setField5(CommConfig.getField5());
			commConfigService.saveCommConfig(CommConfig);
			request.setAttribute("close", "false");
			// request.setAttribute("commConfig", c);//回显修改 TODO
			String type = CommConfig.getField5();
			// 如果是系统配置,更新应用域的存储
			if (CommConfig.getField5().trim().equals("0") || CommConfig.getField5().trim().equals("1") || CommConfig.getField5().trim().equals("2")
					|| CommConfig.getField5().trim().equals("3")) {
				CommConfigUtils.updateProvinceConfig(request, CommConfig, commConfigService);
			}
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}

	/**
	 * 跳转到添加脚本环境配置
	 * 
	 * @return
	 */
	@RequestMapping("/addShellInit")
	public String addShellInit() {
		return "commConfig/edit";
	}

	/**
	 * 跳转到添加路径环境配置
	 * 
	 * @return
	 */
	@RequestMapping("/addPathInit")
	public String addPathInit() {
		return "commConfig/editOne";
	}

	/**
	 * 跳转到添加文件环境配置
	 * 
	 * @return
	 */
	@RequestMapping("/addFileInit")
	public String addFileInit() {
		return "commConfig/editTwo";
	}

	/**
	 * 跳转到其他配置
	 * 
	 * @return
	 */
	@RequestMapping("/addOthers")
	public String addOthers() {
		return "commConfig/editThree";
	}

}