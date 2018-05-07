package com.guoll.modules.resultRecord.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guoll.modules.project.bean.Project;
import com.guoll.modules.project.mapper.ProjectMapper;
import com.guoll.modules.resultRecord.bean.ResultRecord;
import com.guoll.modules.resultRecord.mapper.ResultRecordMapper;
import com.guoll.modules.useCase.bean.UseCase;
import com.guoll.modules.useCase.mapper.UseCaseMapper;
import com.guoll.modules.useCase.service.UseCaseService;

/**
 * 用例执行结果
 * @author lukas 414024003@qq.com
 * @version 1.0
 */
@Repository("resultRecordService")
public class ResultRecordService{

	@Autowired
    private ResultRecordMapper mapper;
	@Autowired
	private UseCaseMapper useCaseMapper;
	
	/**
	 *  查询用例执行结果总数量
	 * @return
	 */
	public Integer queryResultRecordSum(ResultRecord c){
		return mapper.queryResultRecordSum(c);
	}
	
	/**
	 * 查询用例执行结果列表
	 * @return
	 */
	public List<ResultRecord> queryResultRecordList(ResultRecord c) {
		return mapper.queryResultRecordList(c);
	}
	
	/**
	 * 查询所有用例执行结果列表
	 * @return
	 */
	public List<ResultRecord> queryResultRecordListAll(ResultRecord c) {
		return mapper.queryResultRecordListAll(c);
	}
	
	/**
	 * 通过标识查询用例执行结果记录
	 * @return
	 */
	public ResultRecord queryResultRecordById(Integer id){
		return mapper.queryResultRecordById(id);
	}
	
	/**
	 *  通过标识查询用例最后一次执行结果记录
	 * @return
	 */
	public ResultRecord queryResultRecordByLastId(Map<String, Object> map){
		return mapper.queryResultRecordByLastId(map);
	}
	
	
	@Autowired(required = false)
	ProjectMapper projectMapper;
	
	
	
	/**
	 * 保存人员
	 * @return
	 */
	public void saveResultRecord(ResultRecord c,UseCase useCase) {
		if (c.getId()!=null) {//结果有id,更新
			mapper.updateResultRecord(c);
			useCaseMapper.updateUseCaseByPass(useCase);
		}else {//结果没有id,需要添加
			useCaseMapper.updateUseCaseByPass(useCase);
			mapper.addResultRecord(c);
		}
		
		
		
		
		UseCase useCase2 = new UseCase();
		useCase2.setProID(useCase.getProID());
		List<UseCase> listAll = useCaseMapper.queryUseCaseListAll(useCase2);
		Integer successRate = 0;
		Integer failureRate = 0;
		Integer cannot = 0;
		for (UseCase useCase22 : listAll) {
			if (useCase22.getIsPass() != null & useCase22.getIsPass() == 18) {
				successRate++;
			} else if (useCase22.getIsPass() != null & useCase22.getIsPass() == 19) {
				failureRate++;
			} else {
				cannot++;
			}
		}
		
		float rate = ((float) successRate )/ ((float) (successRate + failureRate+cannot)) * 100;
		String result = ""+rate;
		try {
			result = result.substring(0, result.indexOf(".")+3);
		} catch (Exception e) {
		}
		
		Project project = projectMapper.queryProjectById(useCase.getProID());
		project.setSuccessRate(result + "%");
		
		projectMapper.updateProject(project);
		
		
		
	}
	
	public void saveResultRecord(ResultRecord c) {
		if(c.getId()!=null){
			mapper.updateResultRecord(c);
		}else{
			mapper.addResultRecord(c);
		}
	}
	
	/**
	 * 通过标识修改用例执行结果是否执行通过
	 * @return
	 */
	public void updateResultRecordByPass(ResultRecord c){
		mapper.updateResultRecordByPass(c);
	}
	
	/**
	 * 通过单个标识删除
	 * @return
	 */
	public void deleteResultRecord(ResultRecord c) {
		mapper.deleteResultRecord(c);
	}
	
	/**
	 * 通过标识批量删除
	 * @return
	 */
	public void deleteResultRecordAll(String [] item) {
		mapper.deleteResultRecordAll(item);
	}

	/**
	 * 统计通过/不通过的数量
	 * @param resultRecord
	 * @return
	 */
	public Integer queryNumOfIsPass(ResultRecord resultRecord) {
		// TODO Auto-generated method stub
		return mapper.queryNumOfIsPass(resultRecord);
	}
	
	
}
