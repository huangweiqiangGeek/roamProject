package com.guoll.modules.resultRecord.mapper;

import java.util.List;
import java.util.Map;

import com.guoll.modules.resultRecord.bean.ResultRecord;

/**
 * 用例执行结果接口
 * @author lukas 414024003@qq.com
 * @version 1.0
 */
public interface ResultRecordMapper {
	/**
	 * 统计通过/不通过的数量
	 * @param c
	 * @return
	 */
	public Integer queryNumOfIsPass(ResultRecord c);
	/**
	 * 查询用例执行结果总数量
	 * @return
	 */
	public Integer queryResultRecordSum(ResultRecord c);
	
	/**
	 * 查询用例执行结果列表
	 * @return
	 */
	public List<ResultRecord> queryResultRecordList(ResultRecord c);
	
	/**
	 * 查询用例执行结果所有列表
	 * @return
	 */
	public List<ResultRecord> queryResultRecordListAll(ResultRecord c);
	
	/**
	 * 通过标识查询用例执行结果记录
	 * @return
	 */
	public ResultRecord queryResultRecordById(Integer id);
	
	/**
	 * 通过标识查询用例最后一次执行结果记录
	 * @return
	 */
	public ResultRecord queryResultRecordByLastId(Map<String, Object> map);
	
	/**
	 * 新增用例执行结果
	 * @return
	 */
	public void addResultRecord(ResultRecord c);
	
	/**
	 * 修改用例执行结果
	 * @return
	 */
	public void updateResultRecord(ResultRecord c);
	
	/**
	 * 通过标识修改用例执行结果是否执行通过
	 * @return
	 */
	public void updateResultRecordByPass(ResultRecord c);
	
	/**
	 * 删除用例执行结果
	 * @return
	 */
	public void deleteResultRecord(ResultRecord c);
	
	/**
	 * 通过标识批量删除
	 * @param item
	 */
	public void deleteResultRecordAll(String [] item);
	
	/**
	 * 根据项目执行批次查询用例执行结果
	 * @param map
	 * @return
	 */
	public List<ResultRecord> queryResultRecordListWithBatchOfProjec(Map<String, Object> map);
	/**
	 * 根据项目执行批次查询用例执行结果的数目
	 * @param map
	 * @return
	 */
	public Integer queryResultRecordCountWithBatchOfProjec(Map<String, Object> map);
	
	
//	<!-- 查询当前该用例最新的执行结果 -->
	public   ResultRecord  queryResultOfUseCase(Integer  uCID);

}
