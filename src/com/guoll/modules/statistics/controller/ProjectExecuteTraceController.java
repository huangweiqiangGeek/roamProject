package com.guoll.modules.statistics.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoll.modules.project.bean.Project;
import com.guoll.modules.resultRecord.bean.ResultRecord;
import com.guoll.modules.statistics.bean.ProjectExecuteTrace;
import com.guoll.modules.statistics.service.ProjectExecuteTraceService;
import com.guoll.modules.useCase.bean.UseCase;


/**
 * 项目执行记录控制层
 * @author htnm
 *
 */
@Controller
@RequestMapping("/projectExecuteTraceController")
public class ProjectExecuteTraceController {
	
	@Autowired(required=false)
	ProjectExecuteTraceService projectExecuteTraceService;
	
	/**
	 * 跳转到项目执行轨迹页面，用于展示本批次用例执行通过情况
	 * @param request
	 * @param response
	 * @param proID
	 * @return
	 */
	@RequestMapping("/toProjectExecuteTracePage")
	public String toProjectExecuteTracePage(HttpServletRequest request,HttpServletResponse response,Integer proID){
		ProjectExecuteTrace projectExecuteTrace = new ProjectExecuteTrace();
		projectExecuteTrace.setProID(proID);
		request.setAttribute("projectExecuteTrace", projectExecuteTrace);
		return "statistics/projectExecuteTrace";
	}
	
	/**
	 * 查询某个项目的执行轨迹，用于展示本批次用例执行通过情况
	 * @param projectExecuteTrace
	 * @return
	 */
	@RequestMapping("/findExecuteTraceOfProject")
	@ResponseBody
	public Map<String, Object> findExecuteTraceOfProject(ProjectExecuteTrace projectExecuteTrace) {
		Map<String, Object> pageData = new HashMap<String, Object>();
		projectExecuteTrace.setPages();// 设置分页信息
		pageData.put("total", projectExecuteTraceService.queryProjectExecuteTraceCountByExampleWithPaging(projectExecuteTrace));
		pageData.put("rows",projectExecuteTraceService.queryProjectExecuteTraceByExampleWithPaging(projectExecuteTrace));
		return pageData;
	}
	
	/**
	 * 跳转到项目执行轨迹对应用例执行结果页面
	 * @param request
	 * @param response
	 * @param proID
	 * @return
	 */
	@RequestMapping("/toRecordOfUseCaseExecuteWithBatch")
	public String toRecordOfUseCaseExecuteWithBatch(HttpServletRequest request,HttpServletResponse response,Integer id,Integer rows,Integer page){
		ProjectExecuteTrace projectExecuteTrace = new ProjectExecuteTrace();
		projectExecuteTrace.setId(id);
		request.setAttribute("projectExecuteTrace", projectExecuteTrace);
		return "statistics/resultRecordListWithBatch";
	}
	
	/**
	 * 查询某个项目的某个批次的用例执行记录执行记录
	 * @return
	 */
	@RequestMapping("/findRecordOfUseCaseExecuteOfOneBatchOfProject")
	@ResponseBody
	public Map<String, Object>  findRecordOfUseCaseExecuteOfOneBatchOfProject(Integer id,Integer rows,Integer page) {
		Map<String, Object> map=null;
		try {
			map = projectExecuteTraceService.findRecordOfUseCaseExecuteOfOneBatchOfProject(id,page,rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 删除项目执行轨迹
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(Integer id, HttpServletRequest request) {
		projectExecuteTraceService.delete(id);
	}
}
