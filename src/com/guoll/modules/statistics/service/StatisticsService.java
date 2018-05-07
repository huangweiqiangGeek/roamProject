package com.guoll.modules.statistics.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.Set;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guoll.modules.homeproject.bean.HomeProject;
import com.guoll.modules.homeproject.mapper.HomeProjectMapper;
import com.guoll.modules.homeproject.service.HomeProjectService;
import com.guoll.modules.product_customer.bean.Product;
import com.guoll.modules.product_customer.mapper.ProductMapper;
import com.guoll.modules.product_customer.service.ProductService;
import com.guoll.modules.project.bean.Project;
import com.guoll.modules.project.mapper.ProjectMapper;
import com.guoll.modules.statistics.mapper.StatisticsMapper;
import com.guoll.modules.sysmanage.bean.SysPost;
import com.guoll.modules.sysmanage.bean.SysProvince;
import com.guoll.modules.sysmanage.bean.SysUser;
import com.guoll.modules.sysmanage.mapper.SysPostMapper;
import com.guoll.modules.sysmanage.mapper.SysProvinceMapper;
import com.guoll.modules.sysmanage.service.SysPostService;
import com.guoll.modules.sysmanage.service.SysProvinceService;
import com.guoll.modules.useCase.bean.UseCase;
import com.guoll.modules.useCase.mapper.UseCaseMapper;

import util.DateUtil;


/**
 * 统计服务层
 * @author htnm
 *
 */
@Repository("statisticsService")
public class StatisticsService {
	
	@Autowired
    private SysProvinceMapper sysProvinceMapper;
	@Autowired
	private SysPostMapper sysPostMapper;
	@Autowired
	private HomeProjectMapper homeProjectMapper;
	@Autowired
	private ProjectMapper projectMapper ;
	@Autowired
	private UseCaseMapper useCaseMapper ;
	@Autowired
	StatisticsMapper statisticsMapper;
	@Autowired
	ProductMapper productMapper;
	@Autowired
	HomeProjectService homeProjectService;
	@Autowired
	ProductService productService;
	
	/**
	 * 精确到岗位的统计
	 * 根据传递过来的用户信息查询用例执行统计数据
	 * 如果传递过来的是省用户，只查询本省
	 * 如果是超级用户，查询所以省
	 * @param user
	 * @return
	 */
	public List<Map<String, String>> getCountOfProvience(SysUser user) {
		 List<Map<String, String>> countOfProvience = statisticsMapper.getCountOfProvience(user);
		 for (Map<String, String> map : countOfProvience) {
			Long pass_count = (Long)(Object)map.get("pass_count");//Long.parseLong(map.get("pass_count").toString());
			Long total_count = (Long)(Object)map.get("total_count");//Long.parseLong(map.get("total_count").toString());
			float rate = 0;
			if (total_count != 0) {
				rate = ((float) pass_count )/ ((float) total_count) * 100;
			}
			String result = ""+rate;
			try {
					result = result.substring(0, result.indexOf(".")+3);
			} catch (Exception e) {
			}
			map.put("percent", result + "%");
		}
		return countOfProvience;
	}
	
	/**
	 * 精确到岗位的统计
	 * 根据传递过来的用户信息查询用例执行统计数据,并写到excel文件中返回文件全名
	 * 如果传递过来的是省用户，只查询本省
	 * 如果是超级用户，查询所以省
	 * @param user
	 * @param path
	 * @return
	 */
	public String getFileOfCountOfProvience(SysUser user, String path) throws Exception {
		//查询列表
		List<Map<String, String>> countOfProvience = statisticsMapper.getCountOfProvience(user);
		for (Map<String, String> map : countOfProvience) {
			Long pass_count = (Long)(Object)map.get("pass_count");//Long.parseLong(map.get("pass_count").toString());
			Long total_count = (Long)(Object)map.get("total_count");//Long.parseLong(map.get("total_count").toString());
			float rate = 0;
			if (total_count != 0) {
				rate = ((float) pass_count )/ ((float) total_count) * 100;
			}
			String result = ""+rate;
			try {
					result = result.substring(0, result.indexOf(".")+3);
			} catch (Exception e) {
			}
			map.put("percent", result + "%");
		}
		
		//生成文件
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row titleRow = sheet.createRow(0);
		Cell cell0 = titleRow.createCell(0);
		cell0.setCellValue("省份");
		Cell cell1 = titleRow.createCell(1);
		cell1.setCellValue("总用例数量");
		Cell cell2 = titleRow.createCell(2);
		cell2.setCellValue("测试通过用例数量");
		Cell cell3 = titleRow.createCell(3);
		cell3.setCellValue("未测试用例数量");
		Cell cell4 = titleRow.createCell(4);
		cell4.setCellValue("测试未完成用例数量");
		Cell cell5 = titleRow.createCell(5);
		cell5.setCellValue("测试不通过用例数量");
		Cell cell6 = titleRow.createCell(6);
		cell6.setCellValue("测试通过用例占比");

		int i = 1;
		for (Map<String, String> map : countOfProvience) {
			Row row = sheet.createRow(i++);
			Cell cell_0 = row.createCell(0);
			cell_0.setCellValue(map.get("proProvince"));
			Cell cell_1 = row.createCell(1);
			cell_1.setCellValue((Long)(Object)map.get("total_count"));
			Cell cell_2 = row.createCell(2);
			cell_2.setCellValue((Long)(Object)map.get("pass_count"));
			Cell cell_3 = row.createCell(3);
			cell_3.setCellValue((Long)(Object)map.get("nodo_count"));
			Cell cell_4 = row.createCell(4);
			cell_4.setCellValue((Long)(Object)map.get("diable_count"));
			Cell cell_5 = row.createCell(5);
			cell_5.setCellValue((Long)(Object)map.get("failed_count"));
			Cell cell_6 = row.createCell(6);
			cell_6.setCellValue(map.get("percent"));
		}
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = path+"用例执行统计-"+DateUtil.getSdfTimes()+".xls";
		FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
		//写出文件
		workbook.write(fileOutputStream);
		//关闭流
		fileOutputStream.close();
		return fileName;
	}

	
	
	
	//省管理员统计任务分页
	
	/**
	 * 精确到任务(省份)
	 * 根据省份列表返回省分详细统计,精确到任务
	 */
	public List<Map<String, String>> getProvinceStatistic2Project(List<String> provinceSpells){
		List<Map<String, String>> projects = new ArrayList<>();//任务s ,LIST<Map>集合结构
		//1遍历每一个省
		for (int i = 0 ;i < provinceSpells.size();i ++){
			String provinceSpell = provinceSpells.get(i);
			//2获取省份数据
			SysProvince sysProvince = sysProvinceMapper.findSysProvinceBySpell(provinceSpell);
			//3获取该省所有的岗位列表
			SysPost sysPost0 = new SysPost();
			sysPost0.setProvinceSpell(provinceSpell);
			sysPost0.setOrg_id(2);
			List<SysPost> sysposts = sysPostMapper.querySysPostByProvinceSpell(sysPost0);
			//4遍历每一个岗位
			for(int j = 0;j < sysposts.size();j ++) {
				SysPost sysPost = sysposts.get(j);
				//5查询该岗位所对应的项目列表
				//获取省份
					Project project0 = new Project();
				project0.setProProvince(sysPost.getName());
					List<Project> projectListAll = projectMapper.queryProjectListAll(project0);
					//9遍历每一个任务
					for (Project project : projectListAll) {
						//获取任务数据
						Map<String, String> projetDataMap = new HashMap<>();
						projetDataMap.put("projectId", project.getId()+"");//任务id
						projetDataMap.put("projectName", project.getProName());//任务名
						UseCase useCase0= new UseCase();
						useCase0.setProID(project.getId());
						//任务下用例总数
						int projectUseCaseCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例总数
						projetDataMap.put("projectUseCaseCount", projectUseCaseCount+"");
						//任务下通过用例数
						useCase0.setIsPass(18);
						int projectUseCasePassCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例通过数
						projetDataMap.put("projectUseCasePassCount", projectUseCasePassCount+"");
						//任务下未执行用例数
						useCase0.setIsPass(17);
						int projectUseCaseNodoCount = useCaseMapper.queryUseCaseSum(useCase0);//任务未测试用例数
						projetDataMap.put("projectUseCaseNodoCount", projectUseCaseNodoCount+"");
						//任务下未完成用例数
						useCase0.setIsPass(21);
						int projectUseCaseDisableCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试未完成数
						projetDataMap.put("projectUseCaseDisableCount", projectUseCaseDisableCount+"");
						//任务下不通过用例数
						useCase0.setIsPass(19);
						int projectUseCaseFailedCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试不通过数
						projetDataMap.put("projectUseCaseFailedCount", projectUseCaseFailedCount+"");
						//通过率
						String projectUseCasePassRate = turn2Rate(projectUseCaseCount, projectUseCasePassCount);
						projetDataMap.put("projectUseCasePassRate", projectUseCasePassRate);
						projetDataMap.put("provinceSpell", provinceSpell);
						projetDataMap.put("provinceName", sysProvince.getProvinceName());
					/*	projetDataMap.put("homeProjectId", ""+homeProject.getId());
						projetDataMap.put("homeProjectName", homeProject.getName());*/
						projects.add(projetDataMap);
					}
			
			}
		}
		return projects;
	}
	
	/**
	 * 精确到任务
	 * 根据省份列表返回省分详细统计,精确到任务
	 * 返回数据结构
	 * provinceList｛//省份s ,LIST<Map>集合结构
				｛//省份a数据，键值对结构
					provincSpell//省份拼音	
					provinceName//省份名
					provinceUseCaseCount//省用例总数
					provinceUseCasePassCount//省用例通过数
					provinceUseCaseNodoCount//省未测试用例数
					provinceUseCaseDisableCount//省测试未完成数
					provinceUseCaseFailedCount//省测试不通过数
					provinceUseCasePassRate//省通过率
					homeProjs｛//项目s ,LIST<Map>集合结构
						｛//项目b数据，键值对结构
							homeProjId//项目id
							homeProjName//项目名
							homeProjUseCaseCount//项目用例总数
							homeProjUseCasePassCount//项目用例通过数
							homeProjUseCaseNodoCount//项目未测试用例数
							homeProjUseCaseDisableCount//项目测试未完成数
							homeProjUseCaseFailedCount//项目测试不通过数
							homeProjUseCasePassRate//项目通过率
							projects｛//项目s ,LIST<Map>集合结构
								｛//任务c数据,键值对结构
									projectId//任务id
									projectName//任务名
									projectUseCaseCount//任务用例总数
									projectUseCasePassCount//任务用例通过数
									projectUseCaseNodoCount//任务未测试用例数
									projectUseCaseDisableCount//任务测试未完成数
									projectUseCaseFailedCount//任务测试不通过数
									projectUseCasePassRate//任务通过率
								｝
							｝
						｝
					｝
				｝
			｝
	 */
	
	
	//admin超级管理员统计使用
	//以省为维度,统计所有省所有的任务的执行率结果,等你等
	public List<Map<String, Object>> getProvinceStatistic2ProjectList(List<String> provinceSpells){
		List<Map<String, Object>> mapList = new ArrayList<>();
		//1遍历每一个省
		for (int i = 0 ;i < provinceSpells.size();i ++){
			String provinceSpell = provinceSpells.get(i);
			//2获取省份数据
			Map<String, Object> mapOfProvince = new HashMap<>();
			int provinceUseCaseCount = 0;//省用例总数
			int provinceUseCasePassCount = 0;//省用例通过数
			int provinceUseCaseNodoCount = 0;//省未测试用例数
			int provinceUseCaseDisableCount = 0;//省测试未完成数
			int provinceUseCaseFailedCount = 0;//省测试不通过数
			SysProvince sysProvince = sysProvinceMapper.findSysProvinceBySpell(provinceSpell);
			//3获取该省所有的岗位列表
			SysPost sysPost0 = new SysPost();
			sysPost0.setProvinceSpell(provinceSpell);
			sysPost0.setOrg_id(2);
			List<SysPost> sysposts = sysPostMapper.querySysPostByProvinceSpell(sysPost0);
			List<Map<String, Object>> homeProjs = new ArrayList<>();
			//4遍历每一个岗位
			for(int j = 0;j < sysposts.size();j ++) {
				SysPost sysPost = sysposts.get(j);
				//5查询该岗位所对应的项目列表
				HomeProject homeProject0 = new HomeProject();
				homeProject0.setPostName(sysPost.getName());
				List<HomeProject> homProjects = homeProjectMapper.queryList(homeProject0);
				//6遍历每一个项目,获取项目数据
				for(int k = 0 ;k < homProjects.size(); k ++){
					//7获取项目数据
					HomeProject homeProject = homProjects.get(k);
					Map<String, Object> mapOfHomeProject = new HashMap<>();
					int homeProjUseCaseCount = 0;//项目用例总数
					int homeProjUseCasePassCount = 0;//项目用例通过数
					int homeProjUseCaseNodoCount = 0;//项目未测试用例数
					int homeProjUseCaseDisableCount = 0;//项目测试未完成数
					int homeProjUseCaseFailedCount = 0;//项目测试不通过数
					List<Map<String, String>> projects = new ArrayList<>();//任务s ,LIST<Map>集合结构
					//8查询项目下的任务列表
					Project project0 = new Project();
					project0.setHomeProjectId(homeProject.getId());
					List<Project> projectListAll = projectMapper.queryProjectListAll(project0);
					//9遍历每一个任务
					for (Project project : projectListAll) {
						//获取任务数据
						Map<String, String> projetDataMap = new HashMap<>();
						projetDataMap.put("projectId", project.getId()+"");//任务id
						projetDataMap.put("projectName", project.getProName());//任务名
						UseCase useCase0= new UseCase();
						useCase0.setProID(project.getId());
						//任务下用例总数
						int projectUseCaseCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例总数
						projetDataMap.put("projectUseCaseCount", projectUseCaseCount+"");
						homeProjUseCaseCount = homeProjUseCaseCount + projectUseCaseCount;
						provinceUseCaseCount = provinceUseCaseCount + projectUseCaseCount;
						//任务下通过用例数(已通过)
						useCase0.setIsPass(18);
						int projectUseCasePassCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例通过数
						projetDataMap.put("projectUseCasePassCount", projectUseCasePassCount+"");
						homeProjUseCasePassCount = homeProjUseCasePassCount + projectUseCasePassCount;
						provinceUseCasePassCount = provinceUseCasePassCount + projectUseCasePassCount;
						//任务下未执行用例数(未测试)
						useCase0.setIsPass(17);
						int projectUseCaseNodoCount = useCaseMapper.queryUseCaseSum(useCase0);//任务未测试用例数
						projetDataMap.put("projectUseCaseNodoCount", projectUseCaseNodoCount+"");
						homeProjUseCaseNodoCount = homeProjUseCaseNodoCount + projectUseCaseNodoCount;
						provinceUseCaseNodoCount = provinceUseCaseNodoCount + projectUseCaseNodoCount;
						//任务下未完成用例数(执行未完成)
						useCase0.setIsPass(21);
						int projectUseCaseDisableCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试未完成数
						projetDataMap.put("projectUseCaseDisableCount", projectUseCaseDisableCount+"");
						homeProjUseCaseDisableCount = homeProjUseCaseDisableCount + projectUseCaseDisableCount;
						provinceUseCaseDisableCount = provinceUseCaseDisableCount + projectUseCaseDisableCount;
						//任务下不通过用例数(执行不通过)
						useCase0.setIsPass(19);
						int projectUseCaseFailedCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试不通过数
						projetDataMap.put("projectUseCaseFailedCount", projectUseCaseFailedCount+"");
						homeProjUseCaseFailedCount = homeProjUseCaseFailedCount + projectUseCaseFailedCount;
						provinceUseCaseFailedCount = provinceUseCaseFailedCount + projectUseCaseFailedCount;
						//通过率(执行通过率)
						String projectUseCasePassRate = turn2Rate(projectUseCaseCount, projectUseCasePassCount);
						projetDataMap.put("projectUseCasePassRate", projectUseCasePassRate);
						projects.add(projetDataMap);
					}
					mapOfHomeProject.put("homeProjId", homeProject.getId());
					mapOfHomeProject.put("homeProjName", homeProject.getName());
					mapOfHomeProject.put("homeProjUseCaseCount", homeProjUseCaseCount);
					mapOfHomeProject.put("homeProjUseCasePassCount", homeProjUseCasePassCount);
					mapOfHomeProject.put("homeProjUseCaseNodoCount", homeProjUseCaseNodoCount);
					mapOfHomeProject.put("homeProjUseCaseDisableCount", homeProjUseCaseDisableCount);
					mapOfHomeProject.put("homeProjUseCaseFailedCount", homeProjUseCaseFailedCount);
					mapOfHomeProject.put("homeProjUseCasePassRate", turn2Rate(homeProjUseCaseCount, homeProjUseCasePassCount));
					mapOfHomeProject.put("projects", projects);
					homeProjs.add(mapOfHomeProject);
				}
			}
			mapOfProvince.put("provincSpell", sysProvince.getProvinceSpell());
			mapOfProvince.put("provinceName", sysProvince.getProvinceName());
			mapOfProvince.put("provinceUseCaseCount", provinceUseCaseCount);
			mapOfProvince.put("provinceUseCasePassCount", provinceUseCasePassCount);
			mapOfProvince.put("provinceUseCaseNodoCount", provinceUseCaseNodoCount);
			mapOfProvince.put("provinceUseCaseDisableCount", provinceUseCaseDisableCount);
			mapOfProvince.put("provinceUseCaseFailedCount", provinceUseCaseFailedCount);
			mapOfProvince.put("provinceUseCasePassRate", turn2Rate(provinceUseCaseCount, provinceUseCasePassCount));
			mapOfProvince.put("homeProjs", homeProjs);
			mapList.add(mapOfProvince);
		}
		return mapList;
	}
	
	
	public List<Map<String, Object>> getProvinceStatistic2ProjectForExcel(List<String> provinceSpells){
		List<Map<String, Object>> mapList = new ArrayList<>();
		//1遍历每一个省
		for (int i = 0 ;i < provinceSpells.size();i ++){
			String provinceSpell = provinceSpells.get(i);
			//2获取省份数据
			Map<String, Object> mapOfProvince = new HashMap<>();
			int provinceUseCaseCount = 0;//省用例总数
			int provinceUseCasePassCount = 0;//省用例通过数
			int provinceUseCaseNodoCount = 0;//省未测试用例数
			int provinceUseCaseDisableCount = 0;//省测试未完成数
			int provinceUseCaseFailedCount = 0;//省测试不通过数
			SysProvince sysProvince = sysProvinceMapper.findSysProvinceBySpell(provinceSpell);
			//3获取该省所有的岗位列表
			SysPost sysPost0 = new SysPost();
			sysPost0.setProvinceSpell(provinceSpell);
			sysPost0.setOrg_id(2);
			List<SysPost> sysposts = sysPostMapper.querySysPostByProvinceSpell(sysPost0);
			List<Map<String, Object>> homeProjs = new ArrayList<>();
			//4遍历每一个岗位
			for(int j = 0;j < sysposts.size();j ++) {
				SysPost sysPost = sysposts.get(j);
				//5查询该岗位所对应的项目列表
				HomeProject homeProject0 = new HomeProject();
				homeProject0.setPostName(sysPost.getName());
				List<HomeProject> homProjects = homeProjectMapper.queryList(homeProject0);
				//6遍历每一个项目,获取项目数据
				for(int k = 0 ;k < homProjects.size(); k ++){
					//7获取项目数据
					HomeProject homeProject = homProjects.get(k);
					Map<String, Object> mapOfHomeProject = new HashMap<>();
					int homeProjUseCaseCount = 0;//项目用例总数
					int homeProjUseCasePassCount = 0;//项目用例通过数
					int homeProjUseCaseNodoCount = 0;//项目未测试用例数
					int homeProjUseCaseDisableCount = 0;//项目测试未完成数
					int homeProjUseCaseFailedCount = 0;//项目测试不通过数
					List<Map<String, String>> projects = new ArrayList<>();//任务s ,LIST<Map>集合结构
					//8查询项目下的任务列表
					Project project0 = new Project();
					project0.setHomeProjectId(homeProject.getId());
					List<Project> projectListAll = projectMapper.queryProjectListAll(project0);
					//9遍历每一个任务
					for (Project project : projectListAll) {
						//获取任务数据
						Map<String, String> projetDataMap = new HashMap<>();
						projetDataMap.put("projectId", project.getId()+"");//任务id
						projetDataMap.put("projectName", project.getProName());//任务名
						UseCase useCase0= new UseCase();
						useCase0.setProID(project.getId());
						//任务下用例总数
						int projectUseCaseCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例总数
						projetDataMap.put("projectUseCaseCount", projectUseCaseCount+"");
						homeProjUseCaseCount = homeProjUseCaseCount + projectUseCaseCount;
						provinceUseCaseCount = provinceUseCaseCount + projectUseCaseCount;
						//任务下通过用例数(已通过)
						useCase0.setIsPass(18);
						int projectUseCasePassCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例通过数
						projetDataMap.put("projectUseCasePassCount", projectUseCasePassCount+"");
						homeProjUseCasePassCount = homeProjUseCasePassCount + projectUseCasePassCount;
						provinceUseCasePassCount = provinceUseCasePassCount + projectUseCasePassCount;
						//任务下未执行用例数(未测试)
						useCase0.setIsPass(17);
						int projectUseCaseNodoCount = useCaseMapper.queryUseCaseSum(useCase0);//任务未测试用例数
						projetDataMap.put("projectUseCaseNodoCount", projectUseCaseNodoCount+"");
						homeProjUseCaseNodoCount = homeProjUseCaseNodoCount + projectUseCaseNodoCount;
						provinceUseCaseNodoCount = provinceUseCaseNodoCount + projectUseCaseNodoCount;
						//任务下未完成用例数(执行未完成)
						useCase0.setIsPass(21);
						int projectUseCaseDisableCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试未完成数
						projetDataMap.put("projectUseCaseDisableCount", projectUseCaseDisableCount+"");
						homeProjUseCaseDisableCount = homeProjUseCaseDisableCount + projectUseCaseDisableCount;
						provinceUseCaseDisableCount = provinceUseCaseDisableCount + projectUseCaseDisableCount;
						//任务下不通过用例数(执行不通过)
						useCase0.setIsPass(19);
						int projectUseCaseFailedCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试不通过数
						projetDataMap.put("projectUseCaseFailedCount", projectUseCaseFailedCount+"");
						homeProjUseCaseFailedCount = homeProjUseCaseFailedCount + projectUseCaseFailedCount;
						provinceUseCaseFailedCount = provinceUseCaseFailedCount + projectUseCaseFailedCount;
						//通过率(执行通过率)
						String projectUseCasePassRate = turn2Rate(projectUseCaseCount, projectUseCasePassCount);
						projetDataMap.put("projectUseCasePassRate", projectUseCasePassRate);
						projects.add(projetDataMap);
					}
					mapOfHomeProject.put("homeProjId", homeProject.getId());
					mapOfHomeProject.put("homeProjName", homeProject.getName());
					mapOfHomeProject.put("homeProjUseCaseCount", homeProjUseCaseCount);
					mapOfHomeProject.put("homeProjUseCasePassCount", homeProjUseCasePassCount);
					mapOfHomeProject.put("homeProjUseCaseNodoCount", homeProjUseCaseNodoCount);
					mapOfHomeProject.put("homeProjUseCaseDisableCount", homeProjUseCaseDisableCount);
					mapOfHomeProject.put("homeProjUseCaseFailedCount", homeProjUseCaseFailedCount);
					mapOfHomeProject.put("homeProjUseCasePassRate", turn2Rate(homeProjUseCaseCount, homeProjUseCasePassCount));
					mapOfHomeProject.put("projects", projects);
					homeProjs.add(mapOfHomeProject);
				}
			}
			mapOfProvince.put("provincSpell", sysProvince.getProvinceSpell());
			mapOfProvince.put("provinceName", sysProvince.getProvinceName());
			mapOfProvince.put("provinceUseCaseCount", provinceUseCaseCount);
			mapOfProvince.put("provinceUseCasePassCount", provinceUseCasePassCount);
			mapOfProvince.put("provinceUseCaseNodoCount", provinceUseCaseNodoCount);
			mapOfProvince.put("provinceUseCaseDisableCount", provinceUseCaseDisableCount);
			mapOfProvince.put("provinceUseCaseFailedCount", provinceUseCaseFailedCount);
			mapOfProvince.put("provinceUseCasePassRate", turn2Rate(provinceUseCaseCount, provinceUseCasePassCount));
			mapOfProvince.put("homeProjs", homeProjs);
			mapList.add(mapOfProvince);
		}
		return mapList;
	}
	 
	
	
	//普通操作人员统计分页
	/*
	 * 查询普通操作人员旗下的任务
	 * 
	 */
	public List<Map<String, String>> getPuTongStatisticProject(SysUser  sysUser){
		List<Map<String, String>> projects = new ArrayList<>();//任务s ,LIST<Map>集合结构
		//获取普通操作员任务数据
		List<Project>  projectListAll=projectMapper.queryPuTongProject(sysUser);
		for (Project project : projectListAll) {
			//获取任务数据
			Map<String, String> projetDataMap = new HashMap<>();
			projetDataMap.put("projectId", project.getId()+"");//任务id
			projetDataMap.put("projectName", project.getProName());//任务名
			UseCase useCase0= new UseCase();
			useCase0.setProID(project.getId());
			//任务下用例总数
			int projectUseCaseCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例总数
			projetDataMap.put("projectUseCaseCount", projectUseCaseCount+"");
			//任务下通过用例数
			useCase0.setIsPass(18);
			int projectUseCasePassCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例通过数
			projetDataMap.put("projectUseCasePassCount", projectUseCasePassCount+"");
			//任务下未执行用例数
			useCase0.setIsPass(17);
			int projectUseCaseNodoCount = useCaseMapper.queryUseCaseSum(useCase0);//任务未测试用例数
			projetDataMap.put("projectUseCaseNodoCount", projectUseCaseNodoCount+"");
			//任务下未完成用例数
			useCase0.setIsPass(21);
			int projectUseCaseDisableCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试未完成数
			projetDataMap.put("projectUseCaseDisableCount", projectUseCaseDisableCount+"");
			//任务下不通过用例数
			useCase0.setIsPass(19);
			int projectUseCaseFailedCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试不通过数
			projetDataMap.put("projectUseCaseFailedCount", projectUseCaseFailedCount+"");
			//通过率
			String projectUseCasePassRate = turn2Rate(projectUseCaseCount, projectUseCasePassCount);
			projetDataMap.put("projectUseCasePassRate", projectUseCasePassRate);
			projetDataMap.put("provinceName", project.getProProvince());
			projects.add(projetDataMap);
		}
		
		return  projects ;
	}
	
	
	//普通操作人员的饼状图
	/*
	 * 查询普通操作人员旗下的任务总的统计
	 * 
	 */
	public Map<String, String>	getPuTongStatisticProjectEchart(SysUser  sysUser){
		Map  Putong=new HashMap();
		Map  PutongProvinceName=new  HashMap();
		List<Map<String, String>> projects = new ArrayList<>();//任务s ,LIST<Map>集合结构
		//获取普通操作员任务数据
		List<Project>  projectListAll=projectMapper.queryPuTongProject(sysUser);
		int PuTongUseCaseCount = 0;//普通操作人员用例总例通过数
		int PuTongUseCasePassCount = 0;//项目用例通过数
		int PuTongUseCaseNodoCount = 0;//普通操作人员未测试用例数
		int PuTongUseCaseDisableCount = 0;//普通操作人员测试未完成数
		int PuTongUseCaseFailedCount = 0;//普通操作人员测试不通过数
		for (Project project : projectListAll) {
			//获取任务数据
			Map<String, String> projetDataMap = new HashMap<>();
			projetDataMap.put("projectId", project.getId()+"");//任务id
			projetDataMap.put("projectName", project.getProName());//任务名
			UseCase useCase0= new UseCase();
			useCase0.setProID(project.getId());
			//任务下用例总数
			int projectUseCaseCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例总数
			projetDataMap.put("projectUseCaseCount", projectUseCaseCount+"");
			PuTongUseCaseCount=PuTongUseCaseCount+projectUseCaseCount;
			//任务下通过用例数
			useCase0.setIsPass(18);
			int projectUseCasePassCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例通过数
			projetDataMap.put("projectUseCasePassCount", projectUseCasePassCount+"");
			PuTongUseCasePassCount=PuTongUseCasePassCount+projectUseCasePassCount;
			//任务下未执行用例数
			useCase0.setIsPass(17);
			int projectUseCaseNodoCount = useCaseMapper.queryUseCaseSum(useCase0);//任务未测试用例数
			projetDataMap.put("projectUseCaseNodoCount", projectUseCaseNodoCount+"");
			PuTongUseCaseNodoCount=PuTongUseCaseNodoCount+projectUseCaseNodoCount;
			//任务下未完成用例数
			useCase0.setIsPass(21);
			int projectUseCaseDisableCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试未完成数
			projetDataMap.put("projectUseCaseDisableCount", projectUseCaseDisableCount+"");
			PuTongUseCaseDisableCount=PuTongUseCaseDisableCount+projectUseCaseDisableCount;
			//任务下不通过用例数
			useCase0.setIsPass(19);
			int projectUseCaseFailedCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试不通过数
			projetDataMap.put("projectUseCaseFailedCount", projectUseCaseFailedCount+"");
			PuTongUseCaseFailedCount=PuTongUseCaseFailedCount+projectUseCaseFailedCount;
			//通过率
			String projectUseCasePassRate = turn2Rate(projectUseCaseCount, projectUseCasePassCount);
			projetDataMap.put("projectUseCasePassRate", projectUseCasePassRate);
			projetDataMap.put("provinceName", project.getProProvince());
			PutongProvinceName.put("provinceName",project.getProProvince());
			projects.add(projetDataMap);
		}
		Putong.put("ProvinceName",PutongProvinceName.get("provinceName"));
		Putong.put("PuTongName",sysUser.getCn_name() );
		Putong.put("PuTongUseCaseCount", PuTongUseCaseCount);
		Putong.put("PuTongUseCasePassCount", PuTongUseCasePassCount);
		Putong.put("PuTongUseCaseNodoCount", PuTongUseCaseNodoCount);
		Putong.put("PuTongUseCaseDisableCount", PuTongUseCaseDisableCount);
		Putong.put("PuTongUseCaseFailedCount", PuTongUseCaseFailedCount);
		Putong.put("provinceUseCasePassRate", turn2Rate(PuTongUseCaseCount, PuTongUseCasePassCount));
		return  Putong ;
	}
	
	
	
	//  以项目为维度,统计任务的通过率,未通过等等
	/**
	 * 精确到项目
	 * 根据省份列表返回省分详细统计,精确到项目
	 */
	public List<Map<String, Object>> getProvinceStatistic2HomeProject(List<String> provinceSpells){
		List<Map<String, Object>> homeProjs = new ArrayList<>();
		//1遍历每一个省
		for (int i = 0 ;i < provinceSpells.size();i ++){
			String provinceSpell = provinceSpells.get(i);
			//2获取省份数据
			SysProvince sysProvince = sysProvinceMapper.findSysProvinceBySpell(provinceSpell);
			//3获取该省所有的岗位列表
			SysPost sysPost0 = new SysPost();
			sysPost0.setProvinceSpell(provinceSpell);
			sysPost0.setOrg_id(2);
			List<SysPost> sysposts = sysPostMapper.querySysPostByProvinceSpell(sysPost0);
			//4遍历每一个岗位
			for(int j = 0;j < sysposts.size();j ++) {
				SysPost sysPost = sysposts.get(j);
				//5查询该岗位所对应的项目列表
				HomeProject homeProject0 = new HomeProject();
				homeProject0.setPostName(sysPost.getName());
				List<HomeProject> homProjects = homeProjectMapper.queryList(homeProject0);
				//6遍历每一个项目,获取项目数据
				for(int k = 0 ;k < homProjects.size(); k ++){
					//7获取项目数据
					HomeProject homeProject = homProjects.get(k);
					Map<String, Object> mapOfHomeProject = new HashMap<>();
					int homeProjUseCaseCount = 0;//项目用例总数
					int homeProjUseCasePassCount = 0;//项目用例通过数
					int homeProjUseCaseNodoCount = 0;//项目未测试用例数
					int homeProjUseCaseDisableCount = 0;//项目测试未完成数
					int homeProjUseCaseFailedCount = 0;//项目测试不通过数
					List<Map<String, String>> projects = new ArrayList<>();//任务s ,LIST<Map>集合结构
					//8查询项目下的任务列表
					Project project0 = new Project();
					project0.setHomeProjectId(homeProject.getId());
					List<Project> projectListAll = projectMapper.queryProjectListAll(project0);
					//9遍历每一个任务
					for (Project project : projectListAll) {
						//获取任务数据
						UseCase useCase0= new UseCase();
						useCase0.setProID(project.getId());
						//任务下用例总数
						int projectUseCaseCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例总数
						homeProjUseCaseCount = homeProjUseCaseCount + projectUseCaseCount;
						//任务下通过用例数
						useCase0.setIsPass(18);
						int projectUseCasePassCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例通过数
						homeProjUseCasePassCount = homeProjUseCasePassCount + projectUseCasePassCount;
						//任务下未执行用例数
						useCase0.setIsPass(17);
						int projectUseCaseNodoCount = useCaseMapper.queryUseCaseSum(useCase0);//任务未测试用例数
						homeProjUseCaseNodoCount = homeProjUseCaseNodoCount + projectUseCaseNodoCount;
						//任务下未完成用例数
						useCase0.setIsPass(21);
						int projectUseCaseDisableCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试未完成数
						homeProjUseCaseDisableCount = homeProjUseCaseDisableCount + projectUseCaseDisableCount;
						//任务下不通过用例数
						useCase0.setIsPass(19);
						int projectUseCaseFailedCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试不通过数
						homeProjUseCaseFailedCount = homeProjUseCaseFailedCount + projectUseCaseFailedCount;
					}
					mapOfHomeProject.put("provinceName", sysProvince.getProvinceName());
					mapOfHomeProject.put("provinceSpell", provinceSpell);
					mapOfHomeProject.put("homeProjectId", homeProject.getId());
					mapOfHomeProject.put("homeProjectName", homeProject.getName());
					mapOfHomeProject.put("homeProjectUseCaseCount", homeProjUseCaseCount);
					mapOfHomeProject.put("homeProjectUseCasePassCount", homeProjUseCasePassCount);
					mapOfHomeProject.put("homeProjectUseCaseNodoCount", homeProjUseCaseNodoCount);
					mapOfHomeProject.put("homeProjectUseCaseDisableCount", homeProjUseCaseDisableCount);
					mapOfHomeProject.put("homeProjectUseCaseFailedCount", homeProjUseCaseFailedCount);
					mapOfHomeProject.put("homeProjectUseCasePassRate", turn2Rate(homeProjUseCaseCount, homeProjUseCasePassCount));
					mapOfHomeProject.put("projects", projects);
					homeProjs.add(mapOfHomeProject);
				}
			}
		}
		return homeProjs;
	}
	
	
	/**
	 * 精确到项目
	 * 根据省份列表返回省分详细统计,精确到项目
	 * 返回数据结构
	 * provinceList｛//省份s ,LIST<Map>集合结构
				｛//省份a数据，键值对结构
					provincSpell//省份拼音	
					provinceName//省份名
					provinceUseCaseCount//省用例总数
					provinceUseCasePassCount//省用例通过数
					provinceUseCaseNodoCount//省未测试用例数
					provinceUseCaseDisableCount//省测试未完成数
					provinceUseCaseFailedCount//省测试不通过数
					provinceUseCasePassRate//省通过率
					homeProjs｛//项目s ,LIST<Map>集合结构
						｛//项目b数据，键值对结构
							homeProjId//项目id
							homeProjName//项目名
							homeProjUseCaseCount//项目用例总数
							homeProjUseCasePassCount//项目用例通过数
							homeProjUseCaseNodoCount//项目未测试用例数
							homeProjUseCaseDisableCount//项目测试未完成数
							homeProjUseCaseFailedCount//项目测试不通过数
							homeProjUseCasePassRate//项目通过率
						｝
					｝
				｝
			｝
	 */
	
	//统计以省为维度,统计一个省的任务通过率,未通过等等
	public List<Map<String, Object>> getProvinceStatisticProjectCircle(List<String> provinceSpells){
		List<Map<String, Object>> mapList = new ArrayList<>();
		//1遍历每一个省
		for (int i = 0 ;i < provinceSpells.size();i ++){
			String provinceSpell = provinceSpells.get(i);
			//2获取省份数据
			Map<String, Object> mapOfProvince = new HashMap<>();
			int provinceUseCaseCount = 0;//省用例总数
			int provinceUseCasePassCount = 0;//省用例通过数
			int provinceUseCaseNodoCount = 0;//省未测试用例数
			int provinceUseCaseDisableCount = 0;//省测试未完成数
			int provinceUseCaseFailedCount = 0;//省测试不通过数
			SysProvince sysProvince = sysProvinceMapper.findSysProvinceBySpell(provinceSpell);
			//3获取该省所有的岗位列表
			SysPost sysPost0 = new SysPost();
			sysPost0.setProvinceSpell(provinceSpell);
			sysPost0.setOrg_id(2);
			List<SysPost> sysposts = sysPostMapper.querySysPostByProvinceSpell(sysPost0);
			List<Map<String, Object>> homeProjs = new ArrayList<>();
			//4遍历每一个岗位
			for(int j = 0;j < sysposts.size();j ++) {
				SysPost sysPost = sysposts.get(j);
				//5查询该岗位所对应的项目列表
	             	Project  project0=new Project();
	             	project0.setProProvince(sysPost.getName());
					List<Project> projectListAll = projectMapper.queryProjectListAll(project0);
					//9遍历每一个任务
					for (Project project : projectListAll) {
						//获取任务数据
						UseCase useCase0= new UseCase();
						useCase0.setProID(project.getId());
						//任务下用例总数
						int projectUseCaseCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例总数
					
						provinceUseCaseCount = provinceUseCaseCount + projectUseCaseCount;
						//任务下通过用例数
						useCase0.setIsPass(18);
						int projectUseCasePassCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例通过数
					
						provinceUseCasePassCount = provinceUseCasePassCount + projectUseCasePassCount;
						//任务下未执行用例数
						useCase0.setIsPass(17);
						int projectUseCaseNodoCount = useCaseMapper.queryUseCaseSum(useCase0);//任务未测试用例数
					
						provinceUseCaseNodoCount = provinceUseCaseNodoCount + projectUseCaseNodoCount;
						//任务下未完成用例数
						useCase0.setIsPass(21);
						int projectUseCaseDisableCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试未完成数
					
						provinceUseCaseDisableCount =	provinceUseCaseDisableCount + projectUseCaseDisableCount;
						//任务下不通过用例数
						useCase0.setIsPass(19);
						int projectUseCaseFailedCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试不通过数
					
						provinceUseCaseFailedCount = provinceUseCaseFailedCount + projectUseCaseFailedCount;
					}
		
			}
			mapOfProvince.put("provincSpell", sysProvince.getProvinceSpell());
			mapOfProvince.put("provinceName", sysProvince.getProvinceName());
			mapOfProvince.put("provinceUseCaseCount", provinceUseCaseCount);
			mapOfProvince.put("provinceUseCasePassCount", provinceUseCasePassCount);
			mapOfProvince.put("provinceUseCaseNodoCount", provinceUseCaseNodoCount);
			mapOfProvince.put("provinceUseCaseDisableCount", provinceUseCaseDisableCount);
			mapOfProvince.put("provinceUseCaseFailedCount", provinceUseCaseFailedCount);
			mapOfProvince.put("provinceUseCasePassRate", turn2Rate(provinceUseCaseCount, provinceUseCasePassCount));
			mapOfProvince.put("homeProjs", homeProjs);
			mapList.add(mapOfProvince);
		}
		return mapList;
	}
	
	
	
	//统计以省为维度,统计一个省的任务通过率,未通过等等
	public List<Map<String, Object>> getProvinceStatistic2HomeProjectForExcel(List<String> provinceSpells){
		List<Map<String, Object>> mapList = new ArrayList<>();
		//1遍历每一个省
		for (int i = 0 ;i < provinceSpells.size();i ++){
			String provinceSpell = provinceSpells.get(i);
			//2获取省份数据
			Map<String, Object> mapOfProvince = new HashMap<>();
			int provinceUseCaseCount = 0;//省用例总数
			int provinceUseCasePassCount = 0;//省用例通过数
			int provinceUseCaseNodoCount = 0;//省未测试用例数
			int provinceUseCaseDisableCount = 0;//省测试未完成数
			int provinceUseCaseFailedCount = 0;//省测试不通过数
			SysProvince sysProvince = sysProvinceMapper.findSysProvinceBySpell(provinceSpell);
			//3获取该省所有的岗位列表
			SysPost sysPost0 = new SysPost();
			sysPost0.setProvinceSpell(provinceSpell);
			sysPost0.setOrg_id(2);
			List<SysPost> sysposts = sysPostMapper.querySysPostByProvinceSpell(sysPost0);
			List<Map<String, Object>> homeProjs = new ArrayList<>();
			//4遍历每一个岗位
			for(int j = 0;j < sysposts.size();j ++) {
				SysPost sysPost = sysposts.get(j);
				//5查询该岗位所对应的项目列表
				HomeProject homeProject0 = new HomeProject();
				homeProject0.setPostName(sysPost.getName());
				List<HomeProject> homProjects = homeProjectMapper.queryList(homeProject0);
				//6遍历每一个项目,获取项目数据
				for(int k = 0 ;k < homProjects.size(); k ++){
					//7获取项目数据
					HomeProject homeProject = homProjects.get(k);
					Map<String, Object> mapOfHomeProject = new HashMap<>();
					int homeProjUseCaseCount = 0;//项目用例总数
					int homeProjUseCasePassCount = 0;//项目用例通过数
					int homeProjUseCaseNodoCount = 0;//项目未测试用例数
					int homeProjUseCaseDisableCount = 0;//项目测试未完成数
					int homeProjUseCaseFailedCount = 0;//项目测试不通过数
					List<Map<String, String>> projects = new ArrayList<>();//任务s ,LIST<Map>集合结构
					//8查询项目下的任务列表
					Project project0 = new Project();
					project0.setHomeProjectId(homeProject.getId());
					List<Project> projectListAll = projectMapper.queryProjectListAll(project0);
					//9遍历每一个任务
					for (Project project : projectListAll) {
						//获取任务数据
						UseCase useCase0= new UseCase();
						useCase0.setProID(project.getId());
						//任务下用例总数
						int projectUseCaseCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例总数
						homeProjUseCaseCount = homeProjUseCaseCount + projectUseCaseCount;
						provinceUseCaseCount = provinceUseCaseCount + projectUseCaseCount;
						//任务下通过用例数
						useCase0.setIsPass(18);
						int projectUseCasePassCount = useCaseMapper.queryUseCaseSum(useCase0);//任务用例通过数
						homeProjUseCasePassCount = homeProjUseCasePassCount + projectUseCasePassCount;
						provinceUseCasePassCount = provinceUseCasePassCount + projectUseCasePassCount;
						//任务下未执行用例数
						useCase0.setIsPass(17);
						int projectUseCaseNodoCount = useCaseMapper.queryUseCaseSum(useCase0);//任务未测试用例数
						homeProjUseCaseNodoCount = homeProjUseCaseNodoCount + projectUseCaseNodoCount;
						provinceUseCaseNodoCount = provinceUseCaseNodoCount + projectUseCaseNodoCount;
						//任务下未完成用例数
						useCase0.setIsPass(21);
						int projectUseCaseDisableCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试未完成数
						homeProjUseCaseDisableCount = homeProjUseCaseDisableCount + projectUseCaseDisableCount;
						provinceUseCaseDisableCount = provinceUseCaseDisableCount + projectUseCaseDisableCount;
						//任务下不通过用例数
						useCase0.setIsPass(19);
						int projectUseCaseFailedCount = useCaseMapper.queryUseCaseSum(useCase0);//任务测试不通过数
						homeProjUseCaseFailedCount = homeProjUseCaseFailedCount + projectUseCaseFailedCount;
						provinceUseCaseFailedCount = provinceUseCaseFailedCount + projectUseCaseFailedCount;
					}
					mapOfHomeProject.put("homeProjId", homeProject.getId());
					mapOfHomeProject.put("homeProjName", homeProject.getName());
					mapOfHomeProject.put("homeProjUseCaseCount", homeProjUseCaseCount);
					mapOfHomeProject.put("homeProjUseCasePassCount", homeProjUseCasePassCount);
					mapOfHomeProject.put("homeProjUseCaseNodoCount", homeProjUseCaseNodoCount);
					mapOfHomeProject.put("homeProjUseCaseDisableCount", homeProjUseCaseDisableCount);
					mapOfHomeProject.put("homeProjUseCaseFailedCount", homeProjUseCaseFailedCount);
					mapOfHomeProject.put("homeProjUseCasePassRate", turn2Rate(homeProjUseCaseCount, homeProjUseCasePassCount));
					mapOfHomeProject.put("projects", projects);
					homeProjs.add(mapOfHomeProject);
				}
			}
			mapOfProvince.put("provincSpell", sysProvince.getProvinceSpell());
			mapOfProvince.put("provinceName", sysProvince.getProvinceName());
			mapOfProvince.put("provinceUseCaseCount", provinceUseCaseCount);
			mapOfProvince.put("provinceUseCasePassCount", provinceUseCasePassCount);
			mapOfProvince.put("provinceUseCaseNodoCount", provinceUseCaseNodoCount);
			mapOfProvince.put("provinceUseCaseDisableCount", provinceUseCaseDisableCount);
			mapOfProvince.put("provinceUseCaseFailedCount", provinceUseCaseFailedCount);
			mapOfProvince.put("provinceUseCasePassRate", turn2Rate(provinceUseCaseCount, provinceUseCasePassCount));
			mapOfProvince.put("homeProjs", homeProjs);
			mapList.add(mapOfProvince);
		}
		return mapList;
	}
	
	/**
	 * 根据任务进行统计，细化到产品
	 * @param projectId
	 * @return
	 */
	public List<Map<String, Object>> getStatisticProject2Product(Integer projectId) {
		Project project = projectMapper.queryProjectById(projectId);
		HomeProject homeProject = new HomeProject();
		homeProject.setId(project.getHomeProjectId());
		homeProject = homeProjectService.queryById(homeProject);
		SysProvince province = sysProvinceMapper.findSysProvinceBySpell(homeProject.getProvinceSpell());
		UseCase c = new UseCase();
		c.setProID(projectId);
		//1获取任务下所有的用例
		List<UseCase> listAll = useCaseMapper.queryUseCaseListAll(c);
		List<Map<String, Object>> products = new ArrayList<>();
		//2根据产品id对用例进行分类
		Map<String, List<UseCase>> useCaseGroupsByProduct = new HashMap<>();
		for (UseCase useCase : listAll) {
			List<UseCase> list = useCaseGroupsByProduct.get(useCase.getProductId());
			if (list == null) {
				list = new ArrayList<>();
			}
			list.add(useCase);
			useCaseGroupsByProduct.put(useCase.getProductId(), list);
		}
		//3遍历每一种产品的用例统计数据
		for (String key : useCaseGroupsByProduct.keySet()) {
			//统计用例
			List<UseCase> list = useCaseGroupsByProduct.get(key);
			int productUseCaseCount = 0;
			int productUseCasePassCount = 0;
			int productUseCaseNodoCount = 0;
			int productUseCaseDisableCount = 0;
			int productUseCaseFailedCount = 0;
			for (UseCase useCase : list) {
				productUseCaseCount ++;
				if (useCase.getIsPass() == 18) {
					productUseCasePassCount ++;
				}else if (useCase.getIsPass() == 19) {
					productUseCaseFailedCount ++;
				}else if (useCase.getIsPass() == 17) {
					productUseCaseNodoCount ++;
				}else {
					productUseCaseDisableCount ++;
				}
			}
			//查询出产品的完整信息
			Product product = new Product();
			product.setProvinceSpell(homeProject.getProvinceSpell());
			product.setProductId(key);
			List<Product> list2 = productService.queryProductByModel(product);
			Product product2 = list2.get(0);
			Map<String, Object> productDatas = new HashMap<>();
			productDatas.put("provinceSpell", product2.getProvinceSpell());
			productDatas.put("provinceName", province.getProvinceName());
			productDatas.put("homeProjectId", homeProject.getId());
			productDatas.put("homeProjectName", homeProject.getName());
			productDatas.put("projectName", project.getProName());
			productDatas.put("projectId", projectId);
			productDatas.put("productName", product2.getProductName());
			productDatas.put("productId", product2.getProductId());
			productDatas.put("productUseCaseCount", productUseCaseCount);
			productDatas.put("productUseCasePassCount", productUseCasePassCount);
			productDatas.put("productUseCaseNodoCount", productUseCaseNodoCount);
			productDatas.put("productUseCaseDisableCount", productUseCaseDisableCount);
			productDatas.put("productUseCaseFailedCount", productUseCaseFailedCount);
			productDatas.put("productUseCaseFailedPassRate", turn2Rate(productUseCaseCount, productUseCasePassCount));
			products.add(productDatas);
		}
		return products;
	}
	
	
	
	private String turn2Rate(int total_count,int pass_count){
		float rate = 0;
		if (total_count != 0) {
			rate = ((float) pass_count )/ ((float) total_count) * 100;
		}
		String result = ""+rate;
		try {
				result = result.substring(0, result.indexOf(".")+3);
		} catch (Exception e) {
		}
		return result + "%";
	}
	
	
	
	/**
	 * 精确到任务，获取省用例执行结果统计文件
	 */
	@SuppressWarnings({ "unused", "resource" })
	public String getProvinceStatistic2Project(List<String> provinceSpells, String path) throws Exception {
		//1查询数据
		List<Map<String, Object>> countOfProvience = getProvinceStatistic2ProjectForExcel(provinceSpells);
		//2创建工作空间
		Workbook workbook = new HSSFWorkbook();
		//3编写项目统计sheet
		Sheet rw_sheet = workbook.createSheet("任务统计");
		//4编写任务统计sheet
		//4.1编写首行
		int rowNo = 0;
		Row rw_row0 = rw_sheet.createRow(rowNo++);
		Cell rw_row0_sf = rw_row0.createCell(0);
		rw_row0_sf.setCellValue("省份");
		Cell rw_row0_xm = rw_row0.createCell(1);
		rw_row0_xm.setCellValue("项目");
		Cell rw_row0_rw = rw_row0.createCell(2);
		rw_row0_rw.setCellValue("任务");
		Cell rw_row0_zyls = rw_row0.createCell(3);
		rw_row0_zyls.setCellValue("总用例数");
		Cell rw_row0_tgs_ = rw_row0.createCell(4);
		rw_row0_tgs_.setCellValue("通过数");
		Cell rw_row0_wcss = rw_row0.createCell(5);
		rw_row0_wcss.setCellValue("未测试数");
		Cell rw_row0_btgs = rw_row0.createCell(6);
		rw_row0_btgs.setCellValue("不通过数");
		Cell rw_row0_wwcs = rw_row0.createCell(7);
		rw_row0_wwcs.setCellValue("未完成数");
		Cell rw_row0_tgl = rw_row0.createCell(8);
		rw_row0_tgl.setCellValue("通过率");
		//4.2遍历countOfProvience.获取每一省份数据
		for(int i = 0;i < countOfProvience.size();i ++ ) {
			Map<String, Object> provinceData = countOfProvience.get(i);
			String provincSpell = (String)provinceData.get("provincSpell");
			String provinceName = (String)provinceData.get("provinceName");
			String provinceUseCaseCount = ""+provinceData.get("provinceUseCaseCount");
			String provinceUseCasePassCount = ""+provinceData.get("provinceUseCasePassCount");
			String provinceUseCaseNodoCount = ""+provinceData.get("provinceUseCaseNodoCount");
			String provinceUseCaseDisableCount = ""+provinceData.get("provinceUseCaseDisableCount");
			String provinceUseCaseFailedCount = ""+provinceData.get("provinceUseCaseFailedCount");
			String provinceUseCasePassRate = (String)provinceData.get("provinceUseCasePassRate");
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> homeProjs = (List<Map<String, Object>>) provinceData.get("homeProjs"); //项目数据列表
			//4.3遍历homeProjs，获取每一个项目数据
			for (Map<String, Object> homeProjdata : homeProjs) {
				String homeProjId = "" + homeProjdata.get("homeProjId");
				String homeProjName = (String) homeProjdata.get("homeProjName");
				String homeProjUseCaseCount = ""+ homeProjdata.get("homeProjUseCaseCount");
				String homeProjUseCasePassCount = ""+ homeProjdata.get("homeProjUseCasePassCount");
				String homeProjUseCaseNodoCount = ""+ homeProjdata.get("homeProjUseCaseNodoCount");
				String homeProjUseCaseDisableCount = ""+ homeProjdata.get("homeProjUseCaseDisableCount");
				String homeProjUseCaseFailedCount = ""+ homeProjdata.get("homeProjUseCaseFailedCount");
				String homeProjUseCasePassRate = (String) homeProjdata.get("homeProjUseCasePassRate");
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> projects = (List<Map<String, Object>>) homeProjdata.get("projects");//任务数据列表
				//4.4遍历projects，获取每一个任务的数据
				for (Map<String, Object> projData : projects) {
					String projectId = "" + projData.get("projectId");
					String projectName = (String) projData.get("projectName");
					String projectUseCaseCount = "" + projData.get("projectUseCaseCount");
					String projectUseCasePassCount = ""+ projData.get("projectUseCasePassCount");
					String projectUseCaseNodoCount = ""+ projData.get("projectUseCaseNodoCount");
					String projectUseCaseDisableCount = ""+ projData.get("projectUseCaseDisableCount");
					String projectUseCaseFailedCount = ""+projData.get("projectUseCaseFailedCount");
					String projectUseCasePassRate = (String) projData.get("projectUseCasePassRate");
					//4.5将任务数据写到表格
					Row rw_row = rw_sheet.createRow(rowNo++);
					Cell rw_row_sf = rw_row.createCell(0);//省份
					rw_row_sf.setCellValue(provinceName);
					Cell rw_row_xm = rw_row.createCell(1);//项目
					rw_row_xm.setCellValue(homeProjName);
					Cell rw_row_rw = rw_row.createCell(2);//任务
					rw_row_rw.setCellValue(projectName);
					Cell rw_row_zyls = rw_row.createCell(3);//总用例数
					rw_row_zyls.setCellValue(projectUseCaseCount);
					Cell rw_row_tgs_ = rw_row.createCell(4);//"通过数"
					rw_row_tgs_.setCellValue(projectUseCasePassCount);
					Cell rw_row_wcss = rw_row.createCell(5);//"未测试数"
					rw_row_wcss.setCellValue(projectUseCaseNodoCount);
					Cell rw_row_btgs = rw_row.createCell(6);//"不通过数"
					rw_row_btgs.setCellValue(projectUseCaseFailedCount);
					Cell rw_row_wwcs = rw_row.createCell(7);//"未完成数"
					rw_row_wwcs.setCellValue(projectUseCaseDisableCount);
					Cell rw_row_tgl = rw_row.createCell(8);//"通过率"
					rw_row_tgl.setCellValue(projectUseCasePassRate);
				}
			}
		}
		//5写成文件并返回文件地址
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = path+"用例执行统计-"+DateUtil.getSdfTimes()+".xls";
		FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
		//写出文件
		workbook.write(fileOutputStream);
		//关闭流
		fileOutputStream.close();
		return fileName;
	}
	
	/**
	 * 精确到项目，获取省用例执行结果统计文件
	 */
	@SuppressWarnings({ "unused", "resource" })
	public String getProvinceStatistic2HomeProject(List<String> provinceSpells, String path) throws Exception {
		//1查询数据
		List<Map<String, Object>> countOfProvience = getProvinceStatistic2HomeProjectForExcel(provinceSpells);
		//2创建工作空间
		Workbook workbook = new HSSFWorkbook();
		//3编写项目统计sheet
		Sheet rw_sheet = workbook.createSheet("任务统计");
		//4编写任务统计sheet
		//4.1编写首行
		int rowNo = 0;
		Row rw_row0 = rw_sheet.createRow(rowNo++);
		Cell rw_row0_sf = rw_row0.createCell(0);
		rw_row0_sf.setCellValue("省份");
		Cell rw_row0_xm = rw_row0.createCell(1);
		rw_row0_xm.setCellValue("项目");
		Cell rw_row0_zyls = rw_row0.createCell(2);
		rw_row0_zyls.setCellValue("总用例数");
		Cell rw_row0_tgs_ = rw_row0.createCell(3);
		rw_row0_tgs_.setCellValue("通过数");
		Cell rw_row0_wcss = rw_row0.createCell(4);
		rw_row0_wcss.setCellValue("未测试数");
		Cell rw_row0_btgs = rw_row0.createCell(5);
		rw_row0_btgs.setCellValue("不通过数");
		Cell rw_row0_wwcs = rw_row0.createCell(6);
		rw_row0_wwcs.setCellValue("未完成数");
		Cell rw_row0_tgl = rw_row0.createCell(7);
		rw_row0_tgl.setCellValue("通过率");
		//4.2遍历countOfProvience.获取每一省份数据
		for(int i = 0;i < countOfProvience.size();i ++ ) {
			Map<String, Object> provinceData = countOfProvience.get(i);
			String provincSpell = (String)provinceData.get("provincSpell");
			String provinceName = (String)provinceData.get("provinceName");
			String provinceUseCaseCount = ""+provinceData.get("provinceUseCaseCount");
			String provinceUseCasePassCount = ""+provinceData.get("provinceUseCasePassCount");
			String provinceUseCaseNodoCount = ""+provinceData.get("provinceUseCaseNodoCount");
			String provinceUseCaseDisableCount = ""+provinceData.get("provinceUseCaseDisableCount");
			String provinceUseCaseFailedCount = ""+provinceData.get("provinceUseCaseFailedCount");
			String provinceUseCasePassRate = (String)provinceData.get("provinceUseCasePassRate");
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> homeProjs = (List<Map<String, Object>>) provinceData.get("homeProjs"); //项目数据列表
			//4.3遍历homeProjs，获取每一个项目数据
			for (Map<String, Object> homeProjdata : homeProjs) {
				String homeProjId = "" + homeProjdata.get("homeProjId");
				String homeProjName = (String) homeProjdata.get("homeProjName");
				String homeProjUseCaseCount = ""+ homeProjdata.get("homeProjUseCaseCount");
				String homeProjUseCasePassCount = ""+ homeProjdata.get("homeProjUseCasePassCount");
				String homeProjUseCaseNodoCount = ""+ homeProjdata.get("homeProjUseCaseNodoCount");
				String homeProjUseCaseDisableCount = ""+ homeProjdata.get("homeProjUseCaseDisableCount");
				String homeProjUseCaseFailedCount = ""+ homeProjdata.get("homeProjUseCaseFailedCount");
				String homeProjUseCasePassRate = (String) homeProjdata.get("homeProjUseCasePassRate");
				// 4.4将项目数据写到表格
				Row rw_row = rw_sheet.createRow(rowNo++);
				Cell rw_row_sf = rw_row.createCell(0);// 省份
				rw_row_sf.setCellValue(provinceName);
				Cell rw_row_xm = rw_row.createCell(1);// 项目
				rw_row_xm.setCellValue(homeProjName);
				Cell rw_row_zyls = rw_row.createCell(2);// 总用例数
				rw_row_zyls.setCellValue(homeProjUseCaseCount);
				Cell rw_row_tgs_ = rw_row.createCell(3);// "通过数"
				rw_row_tgs_.setCellValue(homeProjUseCasePassCount);
				Cell rw_row_wcss = rw_row.createCell(4);// "未测试数"
				rw_row_wcss.setCellValue(homeProjUseCaseNodoCount);
				Cell rw_row_btgs = rw_row.createCell(5);// "不通过数"
				rw_row_btgs.setCellValue(homeProjUseCaseFailedCount);
				Cell rw_row_wwcs = rw_row.createCell(6);// "未完成数"
				rw_row_wwcs.setCellValue(homeProjUseCaseDisableCount);
				Cell rw_row_tgl = rw_row.createCell(7);// "通过率"
				rw_row_tgl.setCellValue(homeProjUseCasePassRate);
			}
		}
		//5写成文件并返回文件地址
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = path+"用例执行统计-"+DateUtil.getSdfTimes()+".xls";
		FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
		//写出文件
		workbook.write(fileOutputStream);
		//关闭流
		fileOutputStream.close();
		return fileName;
	}
	
	/**
	 * 根据任务进行统计，细化到产品，获取统计文件
	 * @param projectId
	 * @return
	 * @throws Exception 
	 */
	public String getStatisticProject2Product(Integer projectId,String path) throws Exception {
		//1查询数据
		List<Map<String, Object>> StatisticProject2Product = getStatisticProject2Product(projectId);
		//2创建工作空间
		Workbook workbook = new HSSFWorkbook();
		//3编写项目统计sheet
		Sheet cp_sheet = workbook.createSheet("产品统计");
		//4编写任务统计sheet
		//4.1编写首行
		int rowNo = 0;
		Row cp_row0 = cp_sheet.createRow(rowNo++);
		Cell cp_row0_sf = cp_row0.createCell(0);
		cp_row0_sf.setCellValue("省份");
		Cell cp_row0_xm = cp_row0.createCell(1);
		cp_row0_xm.setCellValue("项目");
		Cell cp_row0_rw = cp_row0.createCell(2);
		cp_row0_rw.setCellValue("任务");
		Cell cp_row0_cp = cp_row0.createCell(3);
		cp_row0_cp.setCellValue("产品");
		Cell cp_row0_zyls = cp_row0.createCell(4);
		cp_row0_zyls.setCellValue("总用例数");
		Cell cp_row0_tgs = cp_row0.createCell(5);
		cp_row0_tgs.setCellValue("通过数");
		Cell cp_row0_wcss = cp_row0.createCell(6);
		cp_row0_wcss.setCellValue("未测试数");
		Cell cp_row0_btgs = cp_row0.createCell(7);
		cp_row0_btgs.setCellValue("不通过数");
		Cell cp_row0_wwcs = cp_row0.createCell(8);
		cp_row0_wwcs.setCellValue("未完成数");
		Cell cp_row0_tgl = cp_row0.createCell(9);
		cp_row0_tgl.setCellValue("通过率");
		//4.2遍历countOfProvience.获取每一产品数据
		for(int i = 0;i < StatisticProject2Product.size();i ++ ) {
			Map<String, Object> productData = StatisticProject2Product.get(i);
			String provinceName = (String)productData.get("provinceName");
			String homeProjectName = (String)productData.get("homeProjectName");
			String projectName = (String)productData.get("projectName");
			String productName = (String)productData.get("productName");
			String productUseCaseCount = ""+productData.get("productUseCaseCount");
			String productUseCasePassCount = ""+productData.get("productUseCasePassCount");
			String productUseCaseNodoCount = ""+productData.get("productUseCaseNodoCount");
			String productUseCaseDisableCount = ""+productData.get("productUseCaseDisableCount");
			String productUseCaseFailedCount = ""+productData.get("productUseCaseFailedCount");
			String productUseCasePassRate = (String)productData.get("productUseCasePassRate");

			// 4.4将项目数据写到表格
			Row cp_row = cp_sheet.createRow(rowNo++);
			Cell cp_row_sf = cp_row.createCell(0);// 省份
			cp_row_sf.setCellValue(provinceName);
			Cell cp_row_xm = cp_row.createCell(1);// 项目
			cp_row_xm.setCellValue(homeProjectName);
			Cell cp_row_rw = cp_row.createCell(2);// 任务
			cp_row_rw.setCellValue(projectName);
			Cell cp_row_cp = cp_row.createCell(3);// 产品
			cp_row_cp.setCellValue(productName);
			Cell cp_row_zyls = cp_row.createCell(4);// 总用例数
			cp_row_zyls.setCellValue(productUseCaseCount);
			Cell cp_row_tgs = cp_row.createCell(5);// "通过数"
			cp_row_tgs.setCellValue(productUseCasePassCount);
			Cell cp_row_wcss = cp_row.createCell(6);// "未测试数"
			cp_row_wcss.setCellValue(productUseCaseNodoCount);
			Cell cp_row_btgs = cp_row.createCell(7);// "不通过数"
			cp_row_btgs.setCellValue(productUseCaseFailedCount);
			Cell cp_row_wwcs = cp_row.createCell(8);// "未完成数"
			cp_row_wwcs.setCellValue(productUseCaseDisableCount);
			Cell cp_row_tgl = cp_row.createCell(9);// "通过率"
			cp_row_tgl.setCellValue(productUseCasePassRate);

		}
		// 5写成文件并返回文件地址
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = path+"用例执行统计-"+DateUtil.getSdfTimes()+".xls";
		FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
		//写出文件
		workbook.write(fileOutputStream);
		//关闭流
		fileOutputStream.close();
		return fileName;
	}
}