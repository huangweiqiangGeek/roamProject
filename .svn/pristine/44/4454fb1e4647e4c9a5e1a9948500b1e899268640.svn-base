package com.guoll.modules.statistics.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.guoll.modules.resultRecord.bean.ResultRecord;
import com.guoll.modules.resultRecord.mapper.ResultRecordMapper;
import com.guoll.modules.statistics.bean.ProjectExecuteTrace;
import com.guoll.modules.statistics.mapper.ProjectExecuteTraceMapper;

/**
 * 项目执行记录服务层
 * 
 * @author wangbj
 *
 */
@Repository("projectExecuteTraceService")
public class ProjectExecuteTraceService {

	@Autowired
	ProjectExecuteTraceMapper projectExecuteTraceMapper;
	@Autowired
	ResultRecordMapper resultRecordMapper;

	/**
	 * 新增项目执行记录
	 */
	public void addProjectExecuteTrace(ProjectExecuteTrace projectExecuteTrace) {
		// 将集合数据转换为json字符串
		projectExecuteTrace.setAllCaseListJson(JSON.toJSONString(projectExecuteTrace.getAllCaseList()));
		projectExecuteTrace.setDisableCaseListJson(JSON.toJSONString(projectExecuteTrace.getDisableCaseList()));
		projectExecuteTrace.setFailedCaseListJson(JSON.toJSONString(projectExecuteTrace.getFailedCaseList()));
		projectExecuteTrace.setPassCaseListJson(JSON.toJSONString(projectExecuteTrace.getPassCaseList()));
		// 设置基本数据
		projectExecuteTrace.setProExecuteDate(new Date());
		projectExecuteTraceMapper.addProjectExecuteTrace(projectExecuteTrace);
	}

	/**
	 * 查询项目执行记录
	 */
	public List<ProjectExecuteTrace> queryProjectExecuteTraceByExampleWithPaging(
			ProjectExecuteTrace projectExecuteTrace) {
		List<ProjectExecuteTrace> list = projectExecuteTraceMapper
				.queryProjectExecuteTraceByExampleWithPaging(projectExecuteTrace);
		if (list.size() > 0)
			for (ProjectExecuteTrace projectExecuteTrace2 : list) {
				// 将json字符串转换为集合数据
				projectExecuteTrace2
						.setAllCaseList(JSON.parseArray(projectExecuteTrace2.getAllCaseListJson(), Integer.class));
				projectExecuteTrace2.setDisableCaseList(
						JSON.parseArray(projectExecuteTrace2.getDisableCaseListJson(), Integer.class));
				projectExecuteTrace2.setFailedCaseList(
						JSON.parseArray(projectExecuteTrace2.getFailedCaseListJson(), Integer.class));
				projectExecuteTrace2
						.setPassCaseList(JSON.parseArray(projectExecuteTrace2.getPassCaseListJson(), Integer.class));
			}
		return list;
	}

	/**
	 * 查询项目的最大执行批次，如果没有，返回0
	 * 
	 * @param proID
	 * @return
	 */
	public int queryLastExecuteBatch(Integer proID) {
		Integer batch = projectExecuteTraceMapper.queryLastExecuteBatch(proID);
		return batch == null ? 0 : batch;
	}

	/**
	 * 删除执行批次记录
	 */

	/**
	 * 查询某个项目的批次总数
	 * 
	 * @param projectExecuteTrace
	 * @return
	 */
	public Integer queryProjectExecuteTraceCountByExampleWithPaging(ProjectExecuteTrace projectExecuteTrace) {
		Integer batch = projectExecuteTraceMapper.queryLastExecuteBatch(projectExecuteTrace.getProID());
		return batch == null ? 0 : batch;
	}

	/**
	 * 查询某个项目的某个批次的用例执行记录执行记录 处理逻辑：先查询执行轨迹获取用例id集合和执行批次，再查询执行记录
	 * 最终返回分页数据
	 * @param id
	 * @param page
	 * @param rows
	 * @return
	 */
	public Map<String, Object> findRecordOfUseCaseExecuteOfOneBatchOfProject(Integer id, Integer page, Integer rows) {
			//获取当前项目执行批次
			ProjectExecuteTrace projectExecuteTrace = projectExecuteTraceMapper.queryProjectExecuteTraceById(id);
			//获取用例id集合
			projectExecuteTrace.setAllCaseList(JSON.parseArray(projectExecuteTrace.getAllCaseListJson(), Integer.class));
			//创建map集合封装查询参数
			Map<String, Object> map = new HashMap<>();
			map.put("ids", projectExecuteTrace.getAllCaseList());//用例id
			//创建记录封装分页等数据，并且保存到map集合
			ResultRecord resultRecord = new ResultRecord();
			if (page != null) {
				resultRecord.setPage(page);
			}
			if (rows != null) {
				resultRecord.setRows(rows);
			}
			resultRecord.setPages();//此方法会计算开始结束条目
			resultRecord.setProExecuteBatch(projectExecuteTrace.getProExecuteBatch());//设置批次
			resultRecord.setResultType(1);//设置类型
			map.put("resultRecord", resultRecord);
			List<ResultRecord> resultRecords = resultRecordMapper.queryResultRecordListWithBatchOfProjec(map);
			Integer total = resultRecordMapper.queryResultRecordCountWithBatchOfProjec(map);
			Map<String, Object> pageData = new HashMap<String, Object>();
			pageData.put("total",total);
			pageData.put("rows",resultRecords);
			return pageData;
	}

	/**
	 * 删除项目执行轨迹
	 */
	public void delete(Integer id) {
		projectExecuteTraceMapper.delete(id);
	}
}
