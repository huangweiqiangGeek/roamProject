package com.guoll.modules.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoll.modules.product_customer.service.ProductGroupDetailsService;
import com.guoll.modules.project.bean.Project;
import com.guoll.modules.project.mapper.ProjectMapper;
import com.guoll.modules.statistics.bean.ProjectExecuteTrace;
import com.guoll.modules.statistics.mapper.ProjectExecuteTraceMapper;
import com.guoll.modules.statistics.service.ProjectExecuteTraceService;
import com.guoll.modules.sysmanage.bean.SysUser;
import com.guoll.modules.useCase.bean.UseCase;
import com.guoll.modules.useCase.controller.UseCaseExecutorController;
import com.guoll.modules.useCase.controller.UseCaseExecutorController.Operations;
import com.guoll.modules.useCase.mapper.UseCaseMapper;
import com.guoll.modules.useCase.service.TaskProductGroupService;

import util.DateUtil;
import util.log.LogUtils;


/**
 * 项目
 * @author lukas 414024003@qq.com
 * @version 1.0
 */
@Repository("projectService")
public class ProjectService{

	@Autowired
    private ProjectMapper mapper;
	@Autowired
	ProjectExecuteTraceMapper projectExecuteTraceService;
	@Autowired
	private UseCaseMapper useCaseMapper;
	@Autowired(required = false)
	private TaskProductGroupService taskProductGroupService;
	@Autowired(required = false)
	private ProductGroupDetailsService productGroupDetailsService;
	/**
	 *  查询项目总数量
	 * @return
	 */
	public Integer queryProjectSum(Project c){
		return mapper.queryProjectSum(c);
	}
	
	/**
	 * 按项目执行用例 （注意：按项目执行的用例永远都只能有一条，即使多次按项目执行尽是覆盖上次的执行记录结果）
	 * 
	 * @param proID
	 * @param request
	 * @return
	 */
	public Map<String, Object> projectExecuteCase(Integer proID, HttpServletRequest request,SysUser sysUser,LogUtils logUtils) {
		Map<String, Object> pageData = new HashMap<String, Object>();
		Operations operation = Operations.projectExecute;
		String fileName = DateUtil.getSdfTimes3();
		String realPath = request.getServletContext().getRealPath("/");
		try {
			long start = System.currentTimeMillis();
			// 准备执行工具
			UseCaseExecutorController useCaseExecutor = new UseCaseExecutorController(sysUser, operation, logUtils, fileName, proID);

			// 准备执行
			logUtils.writeLog(realPath, sysUser, "项目执行,请求执行开始,检测请求信息", operation.name(), fileName);
			if (proID == null || proID <= 0) {
				pageData.put("type", -1);
				pageData.put("msg", "请选择需要执行的项目！");
				logUtils.writeLog(realPath, sysUser, "项目执行,请求执行开始,检测请求信息,项目执行,请求检测,请求中没有指定项目id,执行停止", operation.name(),
						fileName);
				return pageData;
			}
			// 0验证当前项目是否在执行
			Object executingProject = request.getServletContext().getAttribute("executingProject");
			List<Integer> proIds = null;
			if (executingProject == null) {
				proIds = new ArrayList<Integer>();
			} else {
				proIds = (List<Integer>) executingProject;
			}
			if (proIds.contains(proID)) {
				pageData.put("type", -1);
				pageData.put("msg", "本项目有使用者正在执行,请稍侯！");
				return pageData;
			} else {
				proIds.add(proID);
				request.setAttribute("executingProject", proID);
			}

			Project project = mapper.queryProjectById(proID);

			System.gc();
			// 2获取项目下的用例集合
			List<UseCase> listOfUseCase = new ArrayList<UseCase>();
			UseCase c = new UseCase();
			c.setProID(proID);// 设定查询用例查询条件为项目标识
			listOfUseCase = useCaseMapper.queryUseCaseListAll(c);// 通过项目标识查询下属所有用例
			// 3遍历用例集合，获取每一个用例，进行执行
			// 先定义通过率统计变量
			logUtils.writeLog(realPath, sysUser, "项目执行,开始执行项目" + project + ";当前项目共有" + listOfUseCase.size() + "个用例",
					operation.name(), fileName);

			//创建项目执行记录并初始化数据
			ProjectExecuteTrace  projectExecuteTrace = new ProjectExecuteTrace();
			projectExecuteTrace.setProID(project.getId());
			projectExecuteTrace.setProName(project.getProName());
			projectExecuteTrace.setProExecuteBatch(projectExecuteTraceService.queryLastExecuteBatch(proID) + 1);
			projectExecuteTrace.setAllCaseList(new ArrayList<Integer>());
			projectExecuteTrace.setDisableCaseList(new ArrayList<Integer>());
			projectExecuteTrace.setFailedCaseList(new ArrayList<Integer>());
			projectExecuteTrace.setPassCaseList(new ArrayList<Integer>());
			int allCaseCount = 0; //统计总用例数
			int disableCaseCount = 0; //统计未完成数
			int failedCaseCount = 0; //统计失败数
			int passCaseCount = 0; //统计通过数
			Integer projectExecuteTraceBatch = projectExecuteTraceService.queryLastExecuteBatch(proID);
			for (UseCase useCase : listOfUseCase) {
				try {
					int ispass = useCase.getIsPass();//接收执行结果的数据
					try {
						ispass = useCaseExecutor.execute(useCase,projectExecuteTraceBatch == null || projectExecuteTraceBatch <= 0 ? 1 : projectExecuteTraceBatch + 1,taskProductGroupService,productGroupDetailsService);
					} catch (Exception e) {
					}finally{
						//处理执行结果，用于保存项目执行轨迹
						allCaseCount++;
						projectExecuteTrace.getAllCaseList().add(useCase.getId());
						if (ispass == 18) {
							passCaseCount ++;
							projectExecuteTrace.getPassCaseList().add(useCase.getId());
						} else if (ispass == 19) {
							failedCaseCount ++;
							projectExecuteTrace.getFailedCaseList().add(useCase.getId());
						} else {
							disableCaseCount ++;
							projectExecuteTrace.getDisableCaseList().add(useCase.getId());
						}
					}
					
					//处理用于实时显示于页面的项目执行状态
					Object attribute = request.getServletContext().getAttribute("projectExecuteCaseStatus" + proID);// 获取当前执行状态集合
					if (attribute == null) {
						HashMap<String, Object> map2 = new HashMap<String, Object>();
						map2.put("now", 1);
						map2.put("sum", listOfUseCase.size());
						map2.put("stop", false);
						request.getServletContext().setAttribute("projectExecuteCaseStatus" + proID, map2);
					} else {
						Map<String, Object> projectExecuteCaseStatus = (Map<String, Object>) attribute;
						projectExecuteCaseStatus.put("now",
								Integer.parseInt(projectExecuteCaseStatus.get("now").toString()) + 1);
						if (Integer.parseInt(projectExecuteCaseStatus.get("now").toString()) == Integer
								.parseInt(projectExecuteCaseStatus.get("sum").toString())) {
							projectExecuteCaseStatus.put("stop", true);
							request.getServletContext().setAttribute("projectExecuteCaseStatus" + proID,
									projectExecuteCaseStatus);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					
					//处理用于实时显示于页面的项目执行状态
					Object attribute = request.getServletContext().getAttribute("projectExecuteCaseStatus" + proID);// 获取当前执行状态集合
					if (attribute == null) {
						HashMap<String, Object> map2 = new HashMap<String, Object>();
						map2.put("now", 1);
						map2.put("sum", listOfUseCase.size());
						map2.put("stop", false);
						request.getServletContext().setAttribute("projectExecuteCaseStatus" + proID, map2);
					} else {
						Map<String, Object> projectExecuteCaseStatus = (Map<String, Object>) attribute;
						projectExecuteCaseStatus.put("now",
								Integer.parseInt(projectExecuteCaseStatus.get("now").toString()) + 1);
						if (Integer.parseInt(projectExecuteCaseStatus.get("now").toString()) == Integer
								.parseInt(projectExecuteCaseStatus.get("sum").toString())) {
							projectExecuteCaseStatus.put("stop", true);
							request.getServletContext().setAttribute("projectExecuteCaseStatus" + proID,
									projectExecuteCaseStatus);
						}
					}
				}
			}
			//保存项目执行记录
			projectExecuteTrace.setAllCaseCount(allCaseCount);
			projectExecuteTrace.setPassCaseCount(passCaseCount);
			projectExecuteTrace.setDisableCaseCount(disableCaseCount);
			projectExecuteTrace.setFailedCaseCount(failedCaseCount);
			projectExecuteTraceService.addProjectExecuteTrace(projectExecuteTrace);
			
			
			//统计页面需要的通过率数据
			UseCase useCase = new UseCase();
			useCase.setProID(proID);
			List<UseCase> listAll = useCaseMapper.queryUseCaseListAll(useCase);
			Integer successRate = 0;
			Integer failureRate = 0;
			Integer cannot = 0;
			for (UseCase useCase2 : listAll) {
				if (useCase2.getIsPass() != null & useCase2.getIsPass() == 18) {
					successRate++;
				} else if (useCase2.getIsPass() != null & useCase2.getIsPass() == 19) {
					failureRate++;
				} else if (useCase2.getIsPass() != null & useCase2.getIsPass() == 21) {
					cannot++;
				}
			}
			
			//计算通过率统计各种用例数量以返回给页面
			float rate = ((float) successRate )/ ((float) (successRate + failureRate+cannot)) * 100;
			String result = ""+rate;
			try {
				result = result.substring(0, result.indexOf(".")+3);
			} catch (Exception e) {
			}
			project.setSuccessRate(result + "%");
			System.out.println(project.getSuccessRate());
			project.setHadExe("是");
			saveProject(project);
			pageData.put("type", "1");
			pageData.put("msg", "执行结束，执行通过" + successRate + "个，执行不通过" + failureRate + "个，未完成执行" + cannot
					+ "个，对于未完成执行的用例，请查看用例数据设置是否正确");
			pageData.put("successRate", successRate);// 执行通过的个数
			pageData.put("failureRate", failureRate);// 执行不通过的个数
			pageData.put("cannotRate", cannot);// 执行不通过的个数
			pageData.put("successUrl", "resultRecord/listInit?proID=" + proID + "&resultType=1&isPass=0");// 通过率查看地址
			pageData.put("failureUrl", "resultRecord/listInit?proID=" + proID + "&resultType=1&isPass=1");// 失败率查看地址
			pageData.put("resultUrl", "resultRecord/listInit3?proID=" + proID + "&resultType=1");// 查看地址
			pageData.put("resultUrl", "resultRecord/listInit3?proID=" + proID + "&resultType=1");// 查看地址
			pageData.put("cannotUrl", "useCase/listPage?proID=" + proID + "&isPass=21");// 未完成用例查看地址
			
			long end = System.currentTimeMillis();
			System.out.println("执行结束，执行通过" + successRate + "个，执行不通过" + failureRate + "个，未完成执行" + cannot
					+ "个，对于未完成执行的用例，请查看用例数据设置是否正确");
			logUtils.writeLog(realPath, sysUser, "执行结束，执行通过" + successRate + "个，执行不通过" + failureRate + "个，未完成执行"
					+ cannot + "个，对于未完成执行的用例，请查看用例数据设置是否正确", operation.name(), fileName);
			return pageData;
		} catch (Exception e) {
			try {
				//处理用于实时显示于页面的项目执行状态
				Object executingProject = request.getServletContext().getAttribute("executingProject");
				List<Integer> proIds = null;
				proIds = (List<Integer>) executingProject;
				if (proIds != null) {
					proIds.remove(proID);
				}
				request.setAttribute("executingProject", proID);

				logUtils.writeLog(realPath, sysUser, "项目执行,执行失败,捕获异常,异常信息:" + e.getMessage(), operation.name(),
						fileName);
				logUtils.writeLog(realPath, sysUser, "项目执行,执行失败,捕获异常,异常栈:" + e.getStackTrace(), operation.name(),
						fileName);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			pageData.put("type", "1");
			pageData.put("msg", "系统繁忙,请联系管理员");
		}
		return pageData;
	}
	
	
	/**
	 * 查询项目列表
	 * @return
	 */
	public List<Project> queryProjectList(Project c) {
		return mapper.queryProjectList(c);
	}
	
	/**
	 * 查询所有项目列表
	 * @return
	 */
	public List<Project> queryProjectListAll(Project c) {
		return mapper.queryProjectListAll(c);
	}
	
	/**
	 * 通过标识查询项目记录
	 * @return
	 */
	public Project queryProjectById(Integer id){
		return mapper.queryProjectById(id);
	}
	
	/**
	 * 通过标识查询当前项目归属省份中的回归项目结果记录
	 * @return
	 */
	public Project queryProjectByLastId(Map<String, Object> map){
		return mapper.queryProjectByLastId(map);
	}
	
	/**
	 * 保存人员
	 * @return
	 */
	public void saveProject(Project c) {
		if(c.getId()!=null){
			mapper.updateProject(c);
		}else{
			mapper.addProject(c);
		}
	}
	
	/**
	 * 通过标识修改项目状态
	 * @return
	 */
	public void updateProjectByStatus(Project c){
		mapper.updateProjectByStatus(c);
	}
	
	/**
	 * 通过标识修改项目用例总数
	 * @return
	 */
	public void updateProjectByCount(Project c){
		mapper.updateProjectByCount(c);
	}
	
	/**
	 * 通过单个标识删除
	 * @return
	 */
	public void deleteProject(Project c) {
		mapper.deleteProject(c);
	}
	
	/**
	 * 通过标识批量删除
	 * @return
	 */
	public void deleteProjectAll(String [] item) {
		mapper.deleteProjectAll(item);
	}

	/**
	 * 通过项目名查询项目
	 * @param p
	 */
	public List<Project> queryProjectByNames(Project p) {
		return mapper.queryProjectByNames(p);
	}
	
	public List<Project> queryTaskNameListByProvince(Project p){
		return mapper.queryTaskNameListByProvince(p);
	}
	
	
/*
 *查询当前普通操作人员的任务
 */
	public   List<Project>  queryPuTongProject(SysUser p){
		return  mapper.queryPuTongProject(p);
	};
	
}

