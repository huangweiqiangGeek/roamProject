package com.guoll.modules.useCase.mapper;

import java.util.List;

import com.guoll.modules.useCase.bean.UseCase;




/**
 * 用例接口
 * @author lukas 414024003@qq.com
 * @version 1.0
 */
public interface UseCaseMapper {

	/**
	 * 查询用例总数量
	 * @return
	 */
	public Integer queryUseCaseSum(UseCase c);
	
	/**
	 * 查询用例列表
	 * @return
	 */
	public List<UseCase> queryUseCaseList(UseCase c);
	
	/**
	 * 查询用例列表
	 * @return
	 */
	public List<UseCase> queryUseCaseList1(UseCase c);
	
	/*
	 * 
	 * 更新用例或保存用例
	 */
	
	public void updateUseCaseByRemark(UseCase c);
	/**
	 * 查询用例所有列表
	 * @return
	 */
	public List<UseCase> queryUseCaseListAll(UseCase c);
	
	
	/**
	 * 通过标识集合查询用例记录
	 * @return
	 */
	public List<UseCase> queryUseCaseListByIds(String [] item);
	
	/**
	 * 通过标识查询用例记录
	 * @return
	 */
	public UseCase queryUseCaseById(Integer id);
	
	/**
	 * 新增用例
	 * @return
	 */
	public int addUseCase(UseCase c);
	
	/**
	 * 修改用例
	 * @return
	 */
	public void updateUseCase(UseCase c);
	
	/**
	 * 通过标识修改用例状态
	 * @return
	 */
	public void updateUseCaseByStatus(UseCase c);
	
	/**
	 * 通过标识修改用例执行次数
	 * @return
	 */
	public void updateUseCaseByCount(UseCase c);
	
	/**
	 * 通过标识修改用例是否执行通过
	 * @return
	 */
	public void updateUseCaseByPass(UseCase c);
	
	/**
	 * 删除用例
	 * @return
	 */
	public void deleteUseCase(UseCase c);
	
	/**
	 * 通过标识批量删除
	 * @param item
	 */
	public void deleteUseCaseAll(String [] item);
	
	/*
	 * 通过id给task_group_id 赋值关联
	 */
   public  void  updateUseCaseById(UseCase  c);
   
/*
 * 查询刚刚生成usecase的id
 */
   
   public  UseCase  queryUseCase(UseCase  c);
   
   /*
    * 查询任务下产品的用例
    * 
    */
    public  List<UseCase>  projectOfUsecase(Integer  projectId);
    
    /*
     * 主动生成ID
     */
    public  Integer  getOverviewid(Integer id);
   
    /*
     * 查询是否存在同样的用例
     */
    
    public   UseCase  queryUseCaseHave(UseCase c);
    
    /*
     * 查询选中任务的所有用例
     * 
     */
    
    public  List<UseCase>  queryUseCaseOfTemplates(String [] item);
}
