package com.guoll.modules.resultRecord.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoll.modules.framework.base.BaseController;
import com.guoll.modules.resultRecord.bean.ResultRecord;
import com.guoll.modules.resultRecord.service.ResultRecordService;
import com.guoll.modules.useCase.bean.UseCase;
import com.guoll.modules.useCase.service.UseCaseService;

import util.RoamProjectException;

/**
 * 执行记录
 * 
 * @author lukas 414024003@qq.com
 * @Date 2017年3月20日 15:50:29
 * @version 1.0
 */
@Controller
@RequestMapping("/resultRecord")
public class ResultRecordController extends BaseController {

	@Autowired(required = false)
	ResultRecordService resultRecordService;
	@Autowired(required = false)
	UseCaseService useCaseService;

	/**
	 * 查询某个用例累计执行轨迹的入口
	 * 2017/07/22对此方法功能进行调整，改后该方法用于查询某个用例的累计执行轨迹的入口，查询出来的结果不再区分用例执行和项目执行
	 * 目前调用：项目下用例下：执行轨迹
	 * 
	 * 
	 * @return
	 */
	@RequestMapping("/listInit")
	public String listInit(HttpServletRequest request, Integer resultType, Integer proID, Integer uCID,
			Integer isPass) {
		ResultRecord resultRecord = new ResultRecord();
		resultType = -1;
		proID = -1;
		isPass = -1;
		resultRecord.setResultType(resultType);
		resultRecord.setProID(proID);
		resultRecord.setuCID(uCID);
		resultRecord.setIsPass(isPass);
		request.setAttribute("resultRecord", resultRecord);
		return "resultRecord/list";
	}

	/**
	 * 一般项目管理入口
	 * 
	 * @return
	 */
	@RequestMapping("/resultlist")
	public String listInit2(HttpServletRequest request, Integer resultType, Integer proID, Integer uCID,
			Integer isPass) {
		ResultRecord resultRecord = new ResultRecord();
		if (resultType == null || resultType == 0) {
			resultType = -1;
		}
		if (proID == null || proID == 0) {
			proID = -1;
		}
		if (uCID == null || uCID == 0) {
			uCID = -1;
		}
		resultRecord.setResultType(resultType);
		resultRecord.setProID(proID);
		resultRecord.setuCID(uCID);
		resultRecord.setIsPass(isPass);
		request.setAttribute("resultRecord", resultRecord);
		return "resultRecord/resultlist";
	}

	/**
	 * 一般项目管理入口
	 * 
	 * @return
	 */
	@RequestMapping("/listInit3")
	public String listInit3(HttpServletRequest request, Integer resultType, Integer proID, Integer uCID,
			Integer isPass) {
		ResultRecord resultRecord = new ResultRecord();
		if (resultType == null || resultType == 0) {
			resultType = -1;
		}
		if (proID == null || proID == 0) {
			proID = -1;
		}
		if (uCID == null || uCID == 0) {
			uCID = -1;
		}
		resultRecord.setResultType(resultType);
		resultRecord.setProID(proID);
		resultRecord.setuCID(uCID);
		resultRecord.setIsPass(isPass);
		request.setAttribute("resultRecord", resultRecord);
		return "resultRecord/show";
	}

	/**
	 * 查询执行记录
	 * 
	 * @return
	 */
	@RequestMapping("/listPage")
	@ResponseBody
	public Map<String, Object> listPage(ResultRecord resultRecord) {
		Map<String, Object> pageData = new HashMap<String, Object>();
		resultRecord.setPages();// 设置分页信息
		pageData.put("total", resultRecordService.queryResultRecordSum(resultRecord));
		pageData.put("rows", resultRecordService.queryResultRecordList(resultRecord));
		resultRecord.setIsPass(0);
		pageData.put("resultRecord", resultRecordService.queryNumOfIsPass(resultRecord));
		resultRecord.setIsPass(1);
		pageData.put("failureRate", resultRecordService.queryNumOfIsPass(resultRecord));
		resultRecord.setIsPass(-1);
		return pageData;
	}

	/**
	 * 查询统计通过/不通过的数量 请求路径:resultRecord/getRateOfSuccess 请求参数: id 项目id;
	 * 
	 * @return
	 */
	@RequestMapping("/getRateOfSuccess")
	@ResponseBody
	public Map<String, Object> getRateOfSuccess(Integer proID) {
		if (proID == null) {
			throw new RoamProjectException("项目id为null");
		}
		UseCase useCase = new UseCase();
		useCase.setProID(proID);
		List<UseCase> listAll = useCaseService.queryUseCaseListAll(useCase);
		Integer successRate = 0;
		Integer failureRate = 0;
		Integer cannotRate = 0;
		for (UseCase useCase2 : listAll) {
			if (useCase2.getIsPass() != null & useCase2.getIsPass() == 18) {
				successRate++;
			} else if (useCase2.getIsPass() != null & useCase2.getIsPass() == 19) {
				failureRate++;
			} else if (useCase2.getIsPass() != null & useCase2.getIsPass() == 21) {
				cannotRate++;
			}
		}
		System.out.println(successRate + " " + failureRate + " " + cannotRate);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("failureRate", failureRate);// 保存不通过的结果的数目
		map.put("resultRecord", successRate);// 保存通过的结果的数目
		map.put("cannotRate", cannotRate);// 保存通过的结果的数目
		map.put("successUrl", "resultRecord/resultlist?proID=" + proID + "&resultType=1&isPass=0");// 通过率查看地址
		map.put("failureUrl", "resultRecord/resultlist?proID=" + proID + "&resultType=1&isPass=1");// 失败率查看地址
		map.put("cannotUrl", "useCase/listInit?proID=" + proID + "&isPass=21");// 未完成用例查看地址
		return map;// 返回
	}

	/**
	 * 删除项目管理
	 * 
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(ResultRecord resultRecord, HttpServletRequest request) {
		resultRecordService.deleteResultRecord(resultRecord);
	}

	/**
	 * 查看用例最近执行结果的详细内容
	 * 目前调用的地方有：
	 * 项目下用例下：执行结果
	 * @throws Exception 
	 */
	@RequestMapping("/lastRecord")
	public String lastRecord(Integer id, HttpServletRequest request) throws Exception {
		ResultRecord resultRecord = new ResultRecord();
		resultRecord.setuCID(id);
		List<ResultRecord> recordList = resultRecordService.queryResultRecordList(resultRecord);
		ResultRecord resultRecord2 = recordList.get(0);
		String resultResource = new String(resultRecord2.getResultResource(), "GBK");
		String resultTotal = "";
		if (resultRecord2.getResultTotal() != null && resultRecord2.getResultTotal().length > 0) {
			resultTotal = new String(resultRecord2.getResultTotal(), "GBK");
		}
		String resultDetail = new String(resultRecord2.getResultDetail(), "GBK");
		request.setAttribute("resultResource", resultResource.replace('“', '"').replace('"', '＃'));
		request.setAttribute("resultTotal", resultTotal.replace('“', '"').replace('"', '＃'));
		request.setAttribute("resultDetail", resultDetail.replace('“', '"').replace('"', '＃'));
		request.setAttribute("uCUserID", resultRecord2.getuCUserID());
		request.setAttribute("productId", resultRecord2.getProductId());
		UseCase useCase = useCaseService.queryUseCaseById(resultRecord2.getuCID());
		request.setAttribute("billType", useCase.getBillType());
		return "resultRecord/edit";
	}

	/**
	 * 查看具体的用例执行结果
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/recordInit")
	public String recordInit(Integer id, HttpServletRequest request) throws Exception {
		if (id != null) {
			ResultRecord resultRecord = resultRecordService.queryResultRecordById(id);
			String resultResource = new String(resultRecord.getResultResource(), "GBK");
			String resultTotal = "";
			if (resultRecord.getResultTotal() != null && resultRecord.getResultTotal().length > 0) {
				resultTotal = new String(resultRecord.getResultTotal(), "GBK");
			}
			String resultDetail = new String(resultRecord.getResultDetail(), "GBK");
			request.setAttribute("resultResource", resultResource.replace('“', '"').replace('"', '＃'));
			request.setAttribute("resultTotal", resultTotal.replace('“', '"').replace('"', '＃'));
			request.setAttribute("resultDetail", resultDetail.replace('“', '"').replace('"', '＃'));
			request.setAttribute("uCUserID", resultRecord.getuCUserID());
			request.setAttribute("productId", resultRecord.getProductId());
			UseCase useCase = useCaseService.queryUseCaseById(resultRecord.getuCID());
			request.setAttribute("billType", useCase.getBillType());
			return "resultRecord/edit";
		}
		return "resultRecord/list";
	}

}