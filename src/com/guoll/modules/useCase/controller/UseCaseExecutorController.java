package com.guoll.modules.useCase.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guoll.modules.commConfig.bean.CommConfig;
import com.guoll.modules.commConfig.service.CommConfigService;
import com.guoll.modules.product_customer.bean.ProductGroupDetails;
import com.guoll.modules.product_customer.service.ProductGroupDetailsService;
import com.guoll.modules.resultRecord.bean.ResultRecord;
import com.guoll.modules.resultRecord.service.ResultRecordService;
import com.guoll.modules.sysmanage.bean.SysUser;
import com.guoll.modules.useCase.bean.TaskProductGroup;
import com.guoll.modules.useCase.bean.UseCase;
import com.guoll.modules.useCase.service.TaskProductGroupService;
import com.guoll.modules.useCase.service.UseCaseService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.CommConfigUtils;
import util.CommConfigUtils.CommConfigKeys;
import util.Convert;
import util.ExecutorOfShell;
import util.ExecutorOfShell2;
import util.FtpUtils;
import util.RoamProjectException;
import util.TxtFileUtil;
import util.fields.ResourceFiledForParseJson;
import util.log.LogUtils;
@Controller
@RequestMapping("/useCaseExecutorController")
public class UseCaseExecutorController {
	public final static String version = "2017-07-31-01";
	public static CommConfigService commConfigService;
	public static UseCaseService useCaseService;
	public static ServletContext servletContext;
	public static String realPath;
	public static ResultRecordService resultRecordService;
	
	
	/**
	 * 创建用例执行工具 SysUser sysUser : 执行用例的用户
	 * 
	 * @param sysUser
	 *            使用者
	 * @param operation
	 *            操作
	 * @param logUtils
	 *            日志工作
	 * @param fileName
	 *            日志文件名
	 * @param proID
	 *            当为项目执行时,需要传入此值,否则为null即可
	 */
	public UseCaseExecutorController(SysUser sysUser, Operations operation, LogUtils logUtils, String fileName, Integer proID) {
		this.sysUser = sysUser;
		this.operation = operation.toString();
		this.logUtils = logUtils;
		this.fileName = fileName;
		this.proID = proID;
		if (commConfigService == null) {
			throw new RoamProjectException("创建用例执行工具异常");
		}
		if (useCaseService == null) {
			throw new RoamProjectException("创建用例执行工具异常");
		}
		if (servletContext == null) {
			throw new RoamProjectException("创建用例执行工具异常");
		}
		if (realPath == null || realPath.trim().equals("")) {
			throw new RoamProjectException("创建用例执行工具异常");
		}
		if (sysUser == null) {
			throw new RoamProjectException("创建用例执行工具异常");
		}
		if (logUtils == null) {
			throw new RoamProjectException("创建用例执行工具异常");
		}
		if (fileName == null || fileName.trim().equals("")) {
			throw new RoamProjectException("创建用例执行工具异常");
		}
		if (resultRecordService == null) {
			throw new RoamProjectException("创建用例执行工具异常");
		}
		prepareCommConfig();
	}

	/**
	 * 校验静态
	 */
	public static void testStatic(CommConfigService commConfigService2, UseCaseService useCaseService2,
			ServletContext servletContext2, ResultRecordService resultRecordService2) {
		if (commConfigService == null) {
			commConfigService = commConfigService2;
		}
		if (useCaseService == null) {
			useCaseService = useCaseService2;
		}
		if (servletContext == null) {
			servletContext = servletContext2;
		}
		if (resultRecordService == null) {
			resultRecordService = resultRecordService2;
		}
		if (realPath == null || realPath.trim().equals("")) {
			realPath = servletContext.getRealPath("/");
		}

	}

	/**
	 * 操作类型枚举，根据记录是否覆盖分两类
	 */
	public static enum Operations {
		/**
		 * 记录覆盖的操作
		 */
		projectExecute,

		/**
		 * 记录不覆盖的操作
		 */
		usecaseExecute

		/**
		 * 批量执行
		 */

	}

	/**
	 * 执行某个指定的用例
	 * 
	 * @return UseCase 该用例执行后
	 * @throws Exception
	 */
	
	public int execute(UseCase useCase,TaskProductGroupService taskProductGroupService,ProductGroupDetailsService productGroupDetailsService) throws Exception {
		return  execute( useCase, -1,taskProductGroupService,productGroupDetailsService);
	}
	@SuppressWarnings("finally")
	public int execute(UseCase useCase,int proExecuteBatch,TaskProductGroupService taskProductGroupService,ProductGroupDetailsService productGroupDetailsService) throws Exception {
		try {
			// 第1步,验证执行环境
			// 定义一个变量,目的在于满足以下要求:调用脚本的时的异常可以记录,但是不终止执行
			try {
				long start = System.currentTimeMillis();
				logUtils.writeLog(realPath, sysUser, "第1步，验证执行环境,当前工具版本:" + version, operation, fileName);
				// 检验配置参数是否存在null值
				testConfigIsNull(ftp_ftptype, "ftp 配置");
				testConfigIsNull(txt_getResourceResult, "获取免费资源计算结果的文件");
				testConfigIsNull(txt_cdr_filename, "生成标准话单后,存放标准话单文件名的文件");
				testConfigIsNull(shell_getResourceResult, "执行后,获取免费资源执行结果时调用的脚本");
				testConfigIsNull(shell_toStandardBill, "构造话单,生成标准话单时调用的脚本");
				testConfigIsNull(shell_moveBill, "执行用例,搬移标准话单时调用的脚本");
				testConfigIsNull(shell_queryResourceByShell, "初始化免费资源,获取免费资源时调用的脚本");
				testConfigIsNull(shell_setResourceOfUserInput, "初始化免费资源,设置免费资源时调用的脚本");
				testConfigIsNull(path_ShellOfDetialResult, "详单脚本路径");
				testConfigIsNull(path_uploadTxtBillForShell, "上传话单路径");
				
				//特殊判断校验
				// || txt_getDetialResultOfWlan == null
				// || txt_getDetialResultOfMsg == null
				// || txt_getDetialResultOfGprs == null
				// || txt_getDetialResultOfGsmr == null
				if (useCase.getBillType().equals("1") || useCase.getBillType().equals("01")) {
					if (txt_getDetialResultOfGsmr == null) {
						//	useCase.setIsPass(21);
						//	useCaseService.saveUseCase(useCase);
							throw new RoamProjectException("第1步，验证执行环境,存在配置错误,有配置获取不到:获取语音详单的文件没有配置");
					}
				}

				// 检验ftp工具是否存在参数
				if (ftp_ftptype.getField1() == null || ftp_ftptype.getField1().trim().equals("")) {
					// ftp配置错误
					logUtils.writeLog(realPath, sysUser, "第1步，验证执行环境,检查当前ftp配置,配置错误", operation, fileName);
					throw new RoamProjectException("第1步，验证执行环境,检查当前ftp配置,配置错误");
				}
				// 检验ftp工具是否存在参数 日志记录
				logUtils.writeLog(realPath, sysUser, "第1步，验证执行环境，当前ftp配置：" + ftp_ftptype.getField1(), operation,
						fileName);
				long stop = System.currentTimeMillis();
				logUtils.writeLog(realPath, sysUser, "第1步，验证执行环境历时(毫秒):" + (stop - start), operation, fileName);
			} catch (Exception e) {
				wrongMangger(e, 1, useCase);
				logUtils.writeLog(realPath, sysUser, "第1步，验证执行环境,执行报错,错误描述信息:" + e.getMessage(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第1步，验证执行环境,执行报错,错误原因:" + e.getCause(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第1步，验证执行环境,执行报错,错误路径:" + getStringTrace(e.getStackTrace()),
						operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第1步，验证执行环境,执行报错,错误LocalizedMessage:" + e.getLocalizedMessage(),
						operation, fileName);
				useCaseService.saveUseCase(useCase);
				throw e;
			}

			// 第2步,备份免费资源
			TxtFileUtil txtFileUtil = new TxtFileUtil();
			Map<String, Map<String, String>> resourceTemp = null;

			//TODO 这里要把这个产品组下用例不需要的子产品置0
			Integer task_group_id=useCase.getTask_group_id();
			String business_type=useCase.getBillType();
			TaskProductGroup tpg=taskProductGroupService.queryProductGroupById(task_group_id);
			
			Integer group_id=tpg.getGroup_id();
			ProductGroupDetails pgd=new ProductGroupDetails();
			pgd.setGroup_id(group_id);
			pgd.setBusiness_type(business_type);
			List<ProductGroupDetails> pgdlist=productGroupDetailsService.queryProductGroupDetails(pgd);
			JSONArray jsonary=new JSONArray();
			if(pgdlist.size()>0){
				String products_json=new String(pgdlist.get(0).getProducts_json(),"utf-8");
				products_json = products_json.substring(products_json.indexOf("["), products_json.indexOf("]") + 1);// 截取免费资源集合部分
				 jsonary = JSONArray.fromObject(products_json);
			}
			
			
			
			
			// 第3步,设置免费资源
			if(useCase.getHaveSource()==1){
			try {
				long start = System.currentTimeMillis();

				// 解析用例中的免费资源字符串到map集合
				String sourceString = new String(useCase.getuCResource(), "utf-8");// 获取免费资源
				sourceString = sourceString.substring(sourceString.indexOf("["), sourceString.indexOf("]") + 1);// 截取免费资源集合部分
				List<ResourceFiledForParseJson> list = com.alibaba.fastjson.JSONArray.parseArray(sourceString,
						ResourceFiledForParseJson.class);// 解析为免费资源集合

				//把产品组其他同类产品也添加到list并设置值为0
				if(jsonary.size()>0){
					  for(int i=0;i<jsonary.size();i++){
						  boolean flag=true;
						  JSONObject job = jsonary.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
						  String productId=job.getString("productId");
						  for(ResourceFiledForParseJson rf:list){
							String proName=rf.getProName();
							if(productId.equals(proName)){
								flag=false;
							}
						  }
						  //把用例中没有用到的子产品设置为0
						  if(flag){
							  ResourceFiledForParseJson rfpj=new ResourceFiledForParseJson();
							  rfpj.setProName(productId);
							    rfpj.setVal("0");
						  }
					   
					  }
					}
				
				// 解析用例中的免费资源字符串到map集合,日志记录免费资源数据
				logUtils.writeLog(realPath, sysUser, "第3步,设置免费资源,免费资源解析后的数据:" + list, operation, fileName);

				// 调用脚本设置免费资源,日志记录脚本配置数据
				logUtils.writeLog(realPath, sysUser,
						"第3步,设置免费资源,调用脚本设置免费资源,脚本配置:" + shell_setResourceOfUserInput.toString(), operation, fileName);

				// 逐一产品进行调用脚本设置
				for (ResourceFiledForParseJson source : list) {
					try {
						long starttime = System.currentTimeMillis();
						executor_setResourceOfUserInput.sendAndExecuteResource(source, useCase.getuCUserID(),
								shell_setResourceOfUserInput.getField1(), shell_setResourceOfUserInput.getField2(),
								sysUser, logUtils, realPath, operation, fileName);
						long endtime = System.currentTimeMillis();
						long time = endtime - starttime;
						logUtils.writeLog(realPath, sysUser, "时间统计，第3步,设置免费资源,调用脚本，历时:" + time+"type"+source.getType(), operation, fileName);

					} catch (Exception e) {
						wrongMangger(e, 2, useCase);
						throw e;
					}
				}

				long stop = System.currentTimeMillis();
				logUtils.writeLog(realPath, sysUser, "第3步,设置免费资源完成,历时(毫秒):" + (stop - start), operation, fileName);
			} catch (Exception e) {
				wrongMangger(e, 3, useCase);
				logUtils.writeLog(realPath, sysUser, "第3步,设置免费资源,执行报错,错误描述信息:" + e.getMessage(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第3步,设置免费资源,执行报错,错误原因:" + e.getCause(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第3步,设置免费资源,执行报错,错误路径:" + getStringTrace(e.getStackTrace()),
						operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第3步,设置免费资源,执行报错,错误LocalizedMessage:" + e.getLocalizedMessage(),
						operation, fileName);
				useCaseService.saveUseCase(useCase);
				throw e;
			}
			}

			// 第4步,设置累积量
			try {

			} catch (Exception e) {
				wrongMangger(e, 4, useCase);
				logUtils.writeLog(realPath, sysUser, "第4步,设置累积量,执行报错,错误描述信息:" + e.getMessage(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第4步,设置累积量,执行报错,错误原因:" + e.getCause(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第4步,设置累积量,执行报错,错误路径:" + getStringTrace(e.getStackTrace()),
						operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第4步,设置累积量,执行报错,错误LocalizedMessage:" + e.getLocalizedMessage(),
						operation, fileName);
				useCaseService.saveUseCase(useCase);
				throw e;
			}

			// 第5步,上传本地话单
			try {
				long start = System.currentTimeMillis();
				// 上传本地话单,日志记录上传路径配置
				logUtils.writeLog(realPath, sysUser, "第5步,上传本地话单,上传路径配置：" + path_uploadTxtBillForShell.toString(),
						operation, fileName);
				// 先生成本地临时话单文件
				txtFileUtil.makeTxtFileFromBill(useCase);

				// 进行上传本地话单文件
				long starttime = System.currentTimeMillis();
				FtpUtils.upload("txts" + File.separator + useCase.getId() + ".txt",
						path_uploadTxtBillForShell.getField1() + "/" + useCase.getId() + ".txt",
						path_uploadTxtBillForShell.getIpAddress(), Integer.parseInt(ftp_ftptype.getField2()),
						path_uploadTxtBillForShell.getHostName(), path_uploadTxtBillForShell.getHostPassWord(),
						ftp_ftptype.getField1());
				long endtime = System.currentTimeMillis();
				long time = endtime - starttime;
				logUtils.writeLog(realPath, sysUser, "时间统计，第5步,上传本地话单,上传文件，历时:" + time, operation, fileName);

				// 清除临时本地话单文件
				File file = new File("txts" + File.separator + useCase.getId() + ".txt");
				if (file.exists()) {
					file.delete();
				}
				long stop = System.currentTimeMillis();

				logUtils.writeLog(realPath, sysUser, "第5步,上传本地话单完成,历时(毫秒)：" + (stop - start), operation, fileName);
			} catch (Exception e) {
				wrongMangger(e, 5, useCase);
				logUtils.writeLog(realPath, sysUser, "第5步,上传本地话单,执行报错,错误描述信息:" + e.getMessage(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第5步,上传本地话单,执行报错,错误原因:" + e.getCause(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第5步,上传本地话单,执行报错,错误路径:" + getStringTrace(e.getStackTrace()),
						operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第5步,上传本地话单,执行报错,错误LocalizedMessage:" + e.getLocalizedMessage(),
						operation, fileName);
				useCaseService.saveUseCase(useCase);
				throw e;
			}
			
			Thread.sleep(0);
			
			// 第6步,调用脚本生成标准话单
			String standardBill = useCase.getStandardBill();
			try {
				// 调用脚本生成标准话单,日志记录脚本配置
				logUtils.writeLog(realPath, sysUser, "第6步,调用脚本生成标准话单,脚本配置:" + shell_toStandardBill.toString(),
						operation, fileName);

				// 调用脚本生成标准话单
				try {
					long starttime = System.currentTimeMillis();
					executor_toStandardBill.toStandardBill(
							path_uploadTxtBillForShell.getField1() + "/" + useCase.getId() + ".txt",
							shell_toStandardBill.getField1(), shell_toStandardBill.getField2(), sysUser, logUtils,
							realPath, operation, fileName,useCase.getBillType());
					long endtime = System.currentTimeMillis();
					long time = endtime - starttime;
					logUtils.writeLog(realPath, sysUser, "时间统计，第6步,调用脚本生成标准话单,调用脚本，历时:" + time, operation, fileName);
				} catch (Exception e) {
					wrongMangger(e, 2, useCase);
					throw e;
				}

				// 下载存有标准话单文件名的txt文档,日志记录
				logUtils.writeLog(realPath, sysUser,
						"第6步,调用脚本生成标准话单,下载存有标准话单文件名的txt文档,文档配置" + txt_cdr_filename.toString(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第6步,调用脚本生成标准话单,下载存有标准话单文件名的txt文档,文档名:工作人员用户名_filed2,即"
						+ sysUser.getUser_code() + "_" + txt_cdr_filename.getField2(), operation, fileName);

				// 下载存有标准话单文件名的txt文件
				Thread.sleep(0);
				long starttime = System.currentTimeMillis();
				FtpUtils.download("txts" + File.separator + useCase.getId() + "cdr_filename.txt",
						txt_cdr_filename.getField1() + "/" + sysUser.getUser_code() + "_"
								+ txt_cdr_filename.getField2(),
						txt_cdr_filename.getIpAddress(), Integer.parseInt(ftp_ftptype.getField2()),
						txt_cdr_filename.getHostName(), txt_cdr_filename.getHostPassWord(), ftp_ftptype.getField1());
				long endtime = System.currentTimeMillis();
				long time = endtime - starttime;
				logUtils.writeLog(realPath, sysUser, "时间统计，第6步,调用脚本生成标准话单,下载文件名文件，历时:" + time, operation, fileName);
				// 读取标准话单文件名
				standardBill = txtFileUtil
						.getStandardFillName("txts" + File.separator + useCase.getId() + "cdr_filename.txt");
				logUtils.writeLog(realPath, sysUser, "记录标准话单文件名的文件的数据:" + standardBill, operation, fileName);
				if (standardBill.contains(";")) {
					String[] split = standardBill.split(";");
					logUtils.writeLog(realPath, sysUser, "进入含有‘;’的处理" , operation, fileName);
					useCase.setStandardBill(split[0]);
					if (split.length>1){
						logUtils.writeLog(realPath, sysUser, "进入含有‘;’的处理，成功分离文件名和开始时间，文件名"+ split[0] + ",开始时间：" +  split[1], operation, fileName);
						useCase.setBillStartTime(split[1]);
					}else {
						standardBill = standardBill.trim();
						String substring = standardBill.substring(standardBill.length()-14);
						useCase.setBillStartTime(substring);
						logUtils.writeLog(realPath, sysUser, "进入含有‘;’的处理，但没有成功凭借分离文件名和开始时间，后14位时间作为开始时间,后14位:"+substring, operation, fileName);
					}
				} else {
					useCase.setStandardBill(standardBill);
					logUtils.writeLog(realPath, sysUser, "进入不含有‘;’的处理" , operation, fileName);
				}
				useCaseService.saveUseCase(useCase);
			} catch (Exception e) {
				wrongMangger(e, 6, useCase);
				logUtils.writeLog(realPath, sysUser, "第6步,调用脚本生成标准话单,执行报错,错误描述信息:" + e.getMessage(), operation,
						fileName);
				logUtils.writeLog(realPath, sysUser, "第6步,调用脚本生成标准话单,执行报错,错误原因:" + e.getCause(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第6步,调用脚本生成标准话单,执行报错,错误路径:" + getStringTrace(e.getStackTrace()),
						operation, fileName);
				logUtils.writeLog(realPath, sysUser,
						"第6步,调用脚本生成标准话单,执行报错,错误LocalizedMessage:" + e.getLocalizedMessage(), operation, fileName);
				useCaseService.saveUseCase(useCase);
				throw e;
			}

			// 第7步,搬移标准话单开始批单
			try {
				long start = System.currentTimeMillis();

				// 搬移标准话单开始批单,日志记录脚本配置
				logUtils.writeLog(realPath, sysUser, "第7步,搬移标准话单开始批单,调用搬移脚本,脚本配置" + shell_moveBill.toString(),
						operation, fileName);

				// 调用脚本进行搬移
				try {
					long starttime = System.currentTimeMillis();
					executor_moveBill.standardBillMove(useCase.getStandardBill(), shell_moveBill.getField1(),
							shell_moveBill.getField2(), sysUser, logUtils, realPath, operation, fileName);
					long endtime = System.currentTimeMillis();
					long time = endtime - starttime;
					logUtils.writeLog(realPath, sysUser, "时间统计，第7步,搬移标准话单开始批单,调用脚本，历时:" + time, operation, fileName);
				} catch (Exception e) {
					wrongMangger(e, 2, useCase);
					throw e;
				}

				long stop = System.currentTimeMillis();
				logUtils.writeLog(realPath, sysUser, "第7步,搬移标准话单开始批单,调用搬移脚本完成,历时(毫秒)" + (stop - start), operation,
						fileName);
			} catch (Exception e) {
				wrongMangger(e, 7, useCase);
				logUtils.writeLog(realPath, sysUser, "第7步,搬移标准话单开始批单,执行报错,错误描述信息:" + e.getMessage(), operation,
						fileName);
				logUtils.writeLog(realPath, sysUser, "第7步,搬移标准话单开始批单,执行报错,错误原因:" + e.getCause(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第7步,搬移标准话单开始批单,执行报错,错误路径:" + getStringTrace(e.getStackTrace()),
						operation, fileName);
				logUtils.writeLog(realPath, sysUser,
						"第7步,搬移标准话单开始批单,执行报错,错误LocalizedMessage:" + e.getLocalizedMessage(), operation, fileName);
				useCaseService.saveUseCase(useCase);
				throw e;
			}

			// 第8步,获取详单执行结果
			Map<String, Map<String, String>> detailResult = null;
			//Thread.sleep(150000);
			try {
				long start = System.currentTimeMillis();
				// 判断话单类型
				CommConfig txt_getDetialResult = null;
				if (useCase.getBillType().trim().equals("1")) {
					txt_getDetialResult = txt_getDetialResultOfGsmr;
				} else if (useCase.getBillType().trim().equals("2")) {
					txt_getDetialResult = txt_getDetialResultOfGprs;
				} else if (useCase.getBillType().trim().equals("3")) {
					txt_getDetialResult = txt_getDetialResultOfMsg;
				} else if (useCase.getBillType().trim().equals("4")) {
					txt_getDetialResult = txt_getDetialResultOfWlan;
				}

				// 记录日志
				logUtils.writeLog(realPath, sysUser,
						"第8步,获取详单执行结果,调用脚本将详单信息写到详单文件,具体脚本参见下面的执行命令,脚本路径配置:" + path_ShellOfDetialResult.toString(),
						operation, fileName);
				logUtils.writeLog(realPath, sysUser,
						"第8步,获取详单执行结果,调用脚本将详单信息写到详单文件,详单文件配置:" + txt_getDetialResult.toString(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第8步,获取详单执行结果,调用脚本将详单信息写到详单文件,文件名:工作人员用户名_filed2,即"
						+ sysUser.getUser_code() + "_" + txt_getDetialResult.getField2(), operation, fileName);

				// 执行
				try {

					detailResult = executor_path_getDetialResult.getDetailResult(useCase,
							txt_getDetialResult.getField1() + "/" + sysUser.getUser_code() + "_"
									+ txt_getDetialResult.getField2(),
							txt_getDetialResult.getIpAddress(), Integer.parseInt(ftp_ftptype.getField2()),
							txt_getDetialResult.getHostName(), txt_getDetialResult.getHostPassWord(),
							path_ShellOfDetialResult.getField1(), sysUser, logUtils, ftp_ftptype.getField1(), realPath,
							operation, fileName);

				} catch (Exception e) {
					wrongMangger(e, 2, useCase);
					throw e;
				}
				long stop = System.currentTimeMillis();
				logUtils.writeLog(realPath, sysUser, "第8步,获取详单执行结果,历时(毫秒)" + (stop - start), operation, fileName);
			} catch (Exception e) {
				wrongMangger(e, 8, useCase);
				logUtils.writeLog(realPath, sysUser, "第8步,获取详单执行结果,执行报错,错误描述信息:" + e.getMessage(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第8步,获取详单执行结果,执行报错,错误原因:" + e.getCause(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第8步,获取详单执行结果,执行报错,错误路径:" + getStringTrace(e.getStackTrace()),
						operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第8步,获取详单执行结果,执行报错,错误LocalizedMessage:" + e.getLocalizedMessage(),
						operation, fileName);
				useCaseService.saveUseCase(useCase);
				throw e;
			}

			// 第9步,获取免费资源执行结果
			Map<String, Map<String, String>> resourceResultToMap = null;
			if(useCase.getHaveSource()==1){
			try {
				long start = System.currentTimeMillis();
				// 将免费资源写到文件中
				logUtils.writeLog(realPath, sysUser,
						"第9步,获取免费资源执行结果,调用脚本获取免费资源结果到免费资源结果文件,脚本配置:" + shell_getResourceResult, operation, fileName);
				try {
					long starttime = System.currentTimeMillis();
					executor_getResourceResult.getResourceResult(useCase, shell_getResourceResult.getField1(),
							shell_getResourceResult.getField2(), sysUser, logUtils, realPath, operation, fileName);
					long endtime = System.currentTimeMillis();
					long time = endtime - starttime;
					logUtils.writeLog(realPath, sysUser, "时间统计，第9步,获取免费资源执行结果,调用脚本，历时:" + time, operation, fileName);
				} catch (Exception e) {
					wrongMangger(e, 2, useCase);
					throw e;
				}

				// 日志记录
				logUtils.writeLog(realPath, sysUser, "第9步,获取免费资源执行结果,下载免费资源结果文件,文件配置:" + txt_getResourceResult,
						operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第9步,获取免费资源执行结果,下载免费资源结果文件,文件名:工作人员用户名_filed2,即"
						+ sysUser.getUser_code() + "_" + txt_getResourceResult.getField2(), operation, fileName);

				// 下载免费资源结果文件
				Thread.sleep(0);
				long starttime = System.currentTimeMillis();
				FtpUtils.download("txts" + File.separator + useCase.getId() + "ResourceResult.txt",
						txt_getResourceResult.getField1() + "/" + sysUser.getUser_code() + "_"
								+ txt_getResourceResult.getField2(),
						txt_getResourceResult.getIpAddress(), Integer.parseInt(ftp_ftptype.getField2()),
						txt_getResourceResult.getHostName(), txt_getResourceResult.getHostPassWord(),
						ftp_ftptype.getField1());
				long endtime = System.currentTimeMillis();
				long time = endtime - starttime;
				logUtils.writeLog(realPath, sysUser, "时间统计，第9步,获取免费资源执行结果,下载文件，历时:" + time, operation, fileName);
				// 删除远程免费资源结果文件
				/*
				 * FtpUtils.deleteFtpFile(txt_getResourceResult.getIpAddress(),
				 * Integer.parseInt(ftp_ftptype.getField2()),
				 * txt_getResourceResult.getHostName(),
				 * txt_getResourceResult.getHostPassWord(),
				 * txt_getResourceResult.getField1(),
				 * txt_getResourceResult.getField2());
				 */

				// 解析免费资源结果文件
				resourceResultToMap = txtFileUtil
						.txtResourceResultToMap("txts" + File.separator + useCase.getId() + "ResourceResult.txt");
				logUtils.writeLog(realPath, sysUser, "第9步,获取免费资源执行结果,解析结果到map集合,结果:" + resourceResultToMap, operation,
						fileName);
				long stop = System.currentTimeMillis();
				logUtils.writeLog(realPath, sysUser, "第9步,获取免费资源执行结果,历时(毫秒):" + (stop - start), operation, fileName);

			} catch (Exception e) {
				wrongMangger(e, 9, useCase);
				logUtils.writeLog(realPath, sysUser, "第9步,获取免费资源执行结果,执行报错,错误描述信息:" + e.getMessage(), operation,
						fileName);
				logUtils.writeLog(realPath, sysUser, "第9步,获取免费资源执行结果,执行报错,错误原因:" + e.getCause(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第9步,获取免费资源执行结果,执行报错,错误路径:" + getStringTrace(e.getStackTrace()),
						operation, fileName);
				logUtils.writeLog(realPath, sysUser,
						"第9步,获取免费资源执行结果,执行报错,错误LocalizedMessage:" + e.getLocalizedMessage(), operation, fileName);
				useCaseService.saveUseCase(useCase);
				throw e;
			}
			}

			// 第10步,获取累积量执行结果
			String resultTotal = null;
			try {
				// resultTotal = resultTotal(useCase);
				resultTotal = "";// 暂时的处理
			} catch (Exception e) {
				wrongMangger(e, 10, useCase);
				logUtils.writeLog(realPath, sysUser, "第10步,获取累积量执行结果,执行报错,错误描述信息:" + e.getMessage(), operation,
						fileName);
				logUtils.writeLog(realPath, sysUser, "第10步,获取累积量执行结果,执行报错,错误原因:" + e.getCause(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第10步,获取累积量执行结果,执行报错,错误路径:" + getStringTrace(e.getStackTrace()),
						operation, fileName);
				logUtils.writeLog(realPath, sysUser,
						"第10步,获取累积量执行结果,执行报错,错误LocalizedMessage:" + e.getLocalizedMessage(), operation, fileName);
				useCaseService.saveUseCase(useCase);
				throw e;
			}

			// 第11步,比较详单计算结果,并处理结果
			boolean resultFlg[] = { true };// 用以统计是否通过，使用数组因为是对象，所以可以做到传参是是传址，该数组将只有一个元素
			String resultDetail = null;
			try {
				long start = System.currentTimeMillis();
				logUtils.writeLog(realPath, sysUser, "第11步,比较详单计算结果,并处理结果,计算结果:" + detailResult, operation, fileName);

				// 当前用例执行后的详单结果（JSON字符窜）
				resultDetail = resultDetail(useCase, detailResult, resultFlg);

				long stop = System.currentTimeMillis();
				logUtils.writeLog(realPath, sysUser, "第11步,比较详单计算结果,并处理结果,历时(毫秒):" + (stop - start), operation,
						fileName);

			} catch (Exception e) {
				wrongMangger(e, 11, useCase);
				logUtils.writeLog(realPath, sysUser, "第11步,比较详单计算结果,并处理结果,执行报错,错误描述信息:" + e.getMessage(), operation,
						fileName);
				logUtils.writeLog(realPath, sysUser, "第11步,比较详单计算结果,并处理结果,执行报错,错误原因:" + e.getCause(), operation,
						fileName);
				logUtils.writeLog(realPath, sysUser,
						"第11步,比较详单计算结果,并处理结果,执行报错,错误路径:" + getStringTrace(e.getStackTrace()), operation, fileName);
				logUtils.writeLog(realPath, sysUser,
						"第11步,比较详单计算结果,并处理结果,执行报错,错误LocalizedMessage:" + e.getLocalizedMessage(), operation, fileName);
				useCaseService.saveUseCase(useCase);
				throw e;
			}

			// 第12步,比较免费资源计算结果,并处理结果
			String resultResource = "";
			if(useCase.getHaveSource()==1){
			try {
				long start = System.currentTimeMillis();
				logUtils.writeLog(realPath, sysUser, "第12步,比较免费资源计算结果,并处理结果,计算结果:" + resourceResultToMap, operation,
						fileName);

				// 当用例执行后的免费资源结果
				resultResource = resultResource(useCase, resourceResultToMap, resultFlg);

				long stop = System.currentTimeMillis();
				logUtils.writeLog(realPath, sysUser, "第12步,比较免费资源计算结果,并处理结果,历时(毫秒):" + (stop - start), operation,
						fileName);

			} catch (Exception e) {
				wrongMangger(e, 12, useCase);
				logUtils.writeLog(realPath, sysUser, "第12步,比较免费资源计算结果,并处理结果,执行报错,错误描述信息:" + e.getMessage(), operation,
						fileName);
				logUtils.writeLog(realPath, sysUser, "第12步,比较免费资源计算结果,并处理结果,执行报错,错误原因:" + e.getCause(), operation,
						fileName);
				logUtils.writeLog(realPath, sysUser,
						"第12步,比较免费资源计算结果,并处理结果,执行报错,错误路径:" + getStringTrace(e.getStackTrace()), operation, fileName);
				logUtils.writeLog(realPath, sysUser,
						"第12步,比较免费资源计算结果,并处理结果,执行报错,错误LocalizedMessage:" + e.getLocalizedMessage(), operation,
						fileName);
				useCaseService.saveUseCase(useCase);
				throw e;
			}
			}

			// 第13步,比较累积量计算结果,并处理结果
			try {
				// String resultTotal = resultTotal(useCase);
			} catch (Exception e) {
				wrongMangger(e, 13, useCase);
				logUtils.writeLog(realPath, sysUser, "第13步,比较累积量计算结果,并处理结果,执行报错,错误描述信息:" + e.getMessage(), operation,
						fileName);
				logUtils.writeLog(realPath, sysUser, "第13步,比较累积量计算结果,并处理结果,执行报错,错误原因:" + e.getCause(), operation,
						fileName);
				logUtils.writeLog(realPath, sysUser,
						"第13步,比较累积量计算结果,并处理结果,执行报错,错误路径:" + getStringTrace(e.getStackTrace()), operation, fileName);
				logUtils.writeLog(realPath, sysUser,
						"第13步,比较累积量计算结果,并处理结果,执行报错,错误LocalizedMessage:" + e.getLocalizedMessage(), operation, fileName);
				useCaseService.saveUseCase(useCase);
				throw e;
			}

			// 第14步,统计执行结果
			ResultRecord record = new ResultRecord();
			try {
				if (operation.trim().equals("projectExecute")) {// 项目执行用例的结果处理
					logUtils.writeLog(realPath, sysUser, "第14步,统计执行结果,", operation, fileName);
					logUtils.writeLog(realPath, sysUser, "第14步,统计执行结果,校验执行结果是否存在", operation, fileName);
					ResultRecord resultRecordForQuery = new ResultRecord();
					resultRecordForQuery.setuCID(useCase.getId());
					List<ResultRecord> recordList = resultRecordService.queryResultRecordListAll(resultRecordForQuery);
					int uCItemNumber = useCase.getExecuteNum();
					/*
					 * 统计项目执行批次，2017/08/01后调整为由调用者提供以确保统一
					 * int proExecuteBatch = -1;
					if (recordList.size() > 0)
					for (ResultRecord resultRecord : recordList) {
						if (resultRecord == null  || resultRecord.getProExecuteBatch() == null) {
							continue;
						}
						if (resultRecord.getProExecuteBatch() > proExecuteBatch) {
							proExecuteBatch = resultRecord.getProExecuteBatch();
						}
					}*/
					record = new ResultRecord();
					// 3.8.2.写入执行结果记录表并回写当前用例的执行结果包含executeNum（执行总次数）
					record.setResultType(1);// 结果类型 1.项目执行结果 2.用例执行,执行用例结果
					record.setProID(useCase.getProID());// 项目标识
					record.setuCID(useCase.getId());
					record.setuCItemNumber(uCItemNumber+1);
					record.setProExecuteBatch(proExecuteBatch);
					record.setResultDetail(resultDetail.getBytes("GBK"));
					record.setResultResource(resultResource.getBytes("GBK"));
					record.setResultTotal(resultTotal.getBytes("GBK"));
					if (resultFlg[0]) {
						useCase.setIsPass(18);// isPass（是否通过 17.待执行 18.是 19.否）
						record.setIsPass(0);// isPass 是否通过 0.是 1.否
					} else {
						useCase.setIsPass(19);
						record.setIsPass(1);
					}
					record.setProductId(useCase.getProductId());
					record.setuCUserID(useCase.getuCUserID());
					record.setResultType(1);// 结果类型 1.项目执行结果 2.用例执行结果
					logUtils.writeLog(realPath, sysUser, "第14步,统计执行结果,统计结果数据,结果:" + record.toString2(), operation,
							fileName);
					resultRecordService.saveResultRecord(record, useCase);// 保存执行结果
					logUtils.writeLog(realPath, sysUser, "第14步,统计执行结果,统计结果数据,保存结果完成", operation,
							fileName);

				} else {// 单个用例执行结果处理
					logUtils.writeLog(realPath, sysUser, "第14步,统计执行结果", operation, fileName);
					int uCItemNumber = useCase.getExecuteNum();
					logUtils.writeLog(realPath, sysUser, "第14步,统计执行结果,校验用例执行次数,当前用例已执行次数" + uCItemNumber, operation,
							fileName);
					record.setResultType(2);// 结果类型 1.项目执行结果 2.用例执行结果
					record.setProID(useCase.getProID());// 项目标识
					record.setuCID(useCase.getId());
					record.setuCItemNumber(uCItemNumber + 1);
					record.setProExecuteBatch(-1);
					record.setResultDetail(resultDetail.getBytes("GBK"));
					record.setResultResource(resultResource.getBytes("GBK"));
					record.setResultTotal(resultTotal.getBytes("GBK"));
					if (resultFlg[0]) {
						useCase.setIsPass(18);// isPass（是否通过 17.待执行 18.是
						record.setIsPass(0);// isPass 是否通过 0.是 1.否
					} else {
						useCase.setIsPass(19);
						record.setIsPass(1);
					}
					record.setProductId(useCase.getProductId());
					record.setuCUserID(useCase.getuCUserID());
					useCase.setExecuteNum(uCItemNumber + 1);
					logUtils.writeLog(realPath, sysUser, "第14步,统计执行结果,统计结果数据,结果:" + record.toString2(), operation,
							fileName);
					resultRecordService.saveResultRecord(record, useCase);// 保存执行结果
				}

			} catch (Exception e) {
				wrongMangger(e, 14, useCase);
				logUtils.writeLog(realPath, sysUser, "第14步,统计执行结果,错误描述信息:" + e.getMessage(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第14步,统计执行结果,执行报错,错误原因:" + e.getCause(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第14步,统计执行结果,执行报错,错误路径:" + getStringTrace(e.getStackTrace()),
						operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第14步,统计执行结果,执行报错,错误LocalizedMessage:" + e.getLocalizedMessage(),
						operation, fileName);
				useCaseService.saveUseCase(useCase);
				throw e;
			}

			// 第15步 恢复免费资源
			try {
				/*
				 * logUtils.writeLog(request, getSessionUser(request),
				 * "用例执行,调用脚本恢复免费资源,脚本:" + shell_setResourceOfUserInput);
				 * List<ResourceFiledForParseJson> margeResource =
				 * margeResource(list, resourceTemp); for
				 * (ResourceFiledForParseJson source : list) {// 逐一进行设置
				 * executor_setResourceOfUserInput.sendAndExecuteResource(
				 * source, useCase.getuCUserID(),
				 * shell_setResourceOfUserInput.getField1(),
				 * shell_setResourceOfUserInput.getField2(), request,
				 * getSessionUser(request), logUtils); }
				 */
			} catch (Exception e) {
				wrongMangger(e, 15, useCase);
				logUtils.writeLog(realPath, sysUser, "第15步 恢复免费资源,执行报错,错误描述信息:" + e.getMessage(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第15步 恢复免费资源,执行报错,错误原因:" + e.getCause(), operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第15步 恢复免费资源,执行报错,错误路径:" + getStringTrace(e.getStackTrace()),
						operation, fileName);
				logUtils.writeLog(realPath, sysUser, "第15步 恢复免费资源,执行报错,错误原因LocalizedMessage:" + e.getLocalizedMessage(),
						operation, fileName);
				useCaseService.saveUseCase(useCase);
				throw e;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			return useCase.getIsPass();
		}
	}

	/**
	 * 执行者
	 */
	private SysUser sysUser;
	/**
	 * 操作
	 */
	private String operation;
	/**
	 * 日志记录工具
	 */
	private LogUtils logUtils;
	/**
	 * 日志记录文件名
	 */
	private String fileName;
	/**
	 * 项目id,只有项目执行时有值
	 */
	private Integer proID;

	/**
	 * 路径
	 */
	/**
	 * 上传话单路径
	 */
	CommConfig path_uploadTxtBillForShell;
	/**
	 * 详单脚本路径
	 */
	CommConfig path_ShellOfDetialResult;

	/**
	 * 获取详单执行结果,对应配置没有脚本配置,只有脚本路径配置
	 */
	ExecutorOfShell executor_path_getDetialResult;

	/**
	 * 脚本
	 */
	/**
	 * 初始化免费资源,设置免费资源时调用的脚本
	 */
	CommConfig shell_setResourceOfUserInput;
	/**
	 * 初始化免费资源,设置免费资源时调用的脚本
	 */
	ExecutorOfShell executor_setResourceOfUserInput;
	/**
	 * 初始化免费资源,获取免费资源时调用的脚本
	 */
	CommConfig shell_queryResourceByShell;
	/**
	 * 初始化免费资源,获取免费资源时调用的脚本
	 */
	ExecutorOfShell executor_queryResourceByShell;
	/**
	 * 执行用例,搬移标准话单时调用的脚本
	 */
	CommConfig shell_moveBill;
	/**
	 * 执行用例,搬移标准话单时调用的脚本
	 */
	ExecutorOfShell executor_moveBill;
	/**
	 * 构造话单,生成标准话单时调用的脚本
	 */
	CommConfig shell_toStandardBill;
	/**
	 * 构造话单,生成标准话单时调用的脚本
	 */
	ExecutorOfShell executor_toStandardBill;
	/**
	 * 执行后,获取免费资源执行结果时调用的脚本
	 */
	CommConfig shell_getResourceResult;
	/**
	 * 执行后,获取免费资源执行结果时调用的脚本
	 */
	ExecutorOfShell executor_getResourceResult;

	/**
	 * 文件
	 */
	/**
	 * 生成标准话单后,存放标准话单文件名的文件
	 */
	CommConfig txt_cdr_filename;
	/**
	 * 获取免费资源计算结果的文件
	 */
	CommConfig txt_getResourceResult;
	/**
	 * 执行后,获取语音详单计算结果的文件
	 */
	CommConfig txt_getDetialResultOfGsmr;
	/**
	 * 执行后,获取gprs详单计算结果的文件
	 */
	CommConfig txt_getDetialResultOfGprs;
	/**
	 * 执行后,获取短彩信详单计算结果的文件
	 */
	CommConfig txt_getDetialResultOfMsg;
	/**
	 * 执行后,获取Wlan详单计算结果的文件
	 */
	CommConfig txt_getDetialResultOfWlan;

	/**
	 * 其他
	 */
	/**
	 * ftp 配置
	 */
	CommConfig ftp_ftptype;

	public void prepareCommConfig() {

		// 路径
		path_uploadTxtBillForShell = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(servletContext,
				CommConfigKeys.path_uploadTxtBillForShell, commConfigService, sysUser);
		path_ShellOfDetialResult = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(servletContext,
				CommConfigKeys.path_ShellOfDetialResult, commConfigService, sysUser);

		// 脚本
		shell_setResourceOfUserInput = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(servletContext,
				CommConfigKeys.shell_setResourceOfUserInput, commConfigService, sysUser);
		shell_queryResourceByShell = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(servletContext,
				CommConfigKeys.shell_queryResourceByShell, commConfigService, sysUser);
		shell_moveBill = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(servletContext,
				CommConfigKeys.shell_moveBill, commConfigService, sysUser);
		shell_toStandardBill = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(servletContext,
				CommConfigKeys.shell_toStandardBill, commConfigService, sysUser);
		shell_getResourceResult = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(servletContext,
				CommConfigKeys.shell_getResourceResult, commConfigService, sysUser);

		// 文件
		txt_cdr_filename = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(servletContext,
				CommConfigKeys.txt_cdr_filename, commConfigService, sysUser);
		txt_getResourceResult = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(servletContext,
				CommConfigKeys.txt_getResourceResult, commConfigService, sysUser);
		txt_getDetialResultOfGprs = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(servletContext,
				CommConfigKeys.txt_getDetialResultOfGprs, commConfigService, sysUser);
		txt_getDetialResultOfMsg = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(servletContext,
				CommConfigKeys.txt_getDetialResultOfMsg, commConfigService, sysUser);
		txt_getDetialResultOfWlan = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(servletContext,
				CommConfigKeys.txt_getDetialResultOfWlan, commConfigService, sysUser);
		txt_getDetialResultOfGsmr = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(servletContext,
				CommConfigKeys.txt_getDetialResultOfGsmr, commConfigService, sysUser);

		// 其他
		ftp_ftptype = CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(servletContext,
				CommConfigKeys.ftp_ftptype, commConfigService, sysUser);

		// 创建执行工具
		ExecutorOfShell2 executorOfShell2 = new ExecutorOfShell2();
		executor_setResourceOfUserInput = executorOfShell2.getExecutorOfShell(
				shell_setResourceOfUserInput.getIpAddress(), shell_setResourceOfUserInput.getHostName(),
				shell_setResourceOfUserInput.getHostPassWord());
		executor_queryResourceByShell = executorOfShell2.getExecutorOfShell(shell_queryResourceByShell.getIpAddress(),
				shell_queryResourceByShell.getHostName(), shell_queryResourceByShell.getHostPassWord());
		executor_moveBill = executorOfShell2.getExecutorOfShell(shell_moveBill.getIpAddress(),
				shell_moveBill.getHostName(), shell_moveBill.getHostPassWord());
		executor_toStandardBill = executorOfShell2.getExecutorOfShell(shell_toStandardBill.getIpAddress(),
				shell_toStandardBill.getHostName(), shell_toStandardBill.getHostPassWord());
		executor_getResourceResult = executorOfShell2.getExecutorOfShell(shell_getResourceResult.getIpAddress(),
				shell_getResourceResult.getHostName(), shell_getResourceResult.getHostPassWord());
		executor_path_getDetialResult = executorOfShell2.getExecutorOfShell(path_ShellOfDetialResult.getIpAddress(),
				path_ShellOfDetialResult.getHostName(), path_ShellOfDetialResult.getHostPassWord());
	}

	/**
	 * 合并免费资源数据，把备份到字符串集合的免费资源数据设置到免费资源对象中，以备使用
	 */
	public List<ResourceFiledForParseJson> margeResource(List<ResourceFiledForParseJson> list,
			Map<String, Map<String, String>> resourceTemp) {
		for (ResourceFiledForParseJson resourceFiledForParseJson : list) {
			Map<String, String> map = resourceTemp.get(resourceFiledForParseJson.getProName());
			if (map == null) {
				continue;
			}
			if (resourceFiledForParseJson.getType().equals("2")) {
				String value_residue = map.get("value_residue");
				if (value_residue != null && !value_residue.trim().equals("") && value_residue.contains("_")) {
					String[] split = value_residue.split("_");
					resourceFiledForParseJson.setVal(split[0]);
					resourceFiledForParseJson.setTxtProCarrVal(split[1]);
				}
			} else {
				resourceFiledForParseJson.setVal(map.get("value_residue"));
			}
		}
		return list;
	}

	/**
	 * 获取用例执行记录的详单结果
	 * 
	 * @param useCase
	 * @return
	 * @throws Exception
	 */
	public String resultDetail(UseCase useCase, Map<String, Map<String, String>> map, boolean[] resultFlg)
			throws Exception {
		String resultDetail = new String(useCase.getuCExpDetail(), "GBK");// 获取用的免费资源预期
		JSONObject expDetailJson = JSONObject.fromObject(resultDetail);
		JSONArray expDetailJsonArray = expDetailJson.getJSONArray("expDetailJson");
		logUtils.writeLog(realPath, sysUser, "第11步,比较详单计算结果,预期:" + expDetailJson, operation, fileName);
		JSONArray arrayExpDetailJson = new JSONArray();
		for (int i = 0; i < expDetailJsonArray.size(); i++) {
			JSONObject j = expDetailJsonArray.getJSONObject(i);
			String fieldNames = j.getString("fieldNames");// 需要查询的字段，以及字段的预设结果值("fieldNames":"Mob_fee@570^Toll_fee@0^dis_id@0^duration@1")
			Map<String, String> map2 = map.get(fieldNames);// 获取对应字段的结果值集合
			logUtils.writeLog(realPath, sysUser,
					"第11步,比较详单计算结果,详单预期:" + expDetailJson + ";计算结果" + map2 + "比较前resultFlg=" + resultFlg[0], operation,
					fileName);
			String[] arrayFied = fieldNames.split("\\^");// proId@1^proName@2
			String newArrayFied = "";
			for (int o = 0; o < arrayFied.length; o++) {
				String fielsValue = arrayFied[o];
				String[] f = fielsValue.split("@");// proId@1
				String k = f[0].toString();// 查询的字段
				String v = "";
				try {
					v = f[1].toString();// 查询的字段预期的设定的结果值
				} catch (Exception e) {
				}
				String resultV = map2.get(k);// 执行后的实际值
				if (!compare(v, resultV)) {
					resultFlg[0] = false;
				}

				newArrayFied += k + "@" + v + "|" + resultV + "^";// proId@1|1^
			}
			newArrayFied = Convert.withCha(newArrayFied, "^");
			j.put("fieldNames", newArrayFied);
			arrayExpDetailJson.add(j);
			logUtils.writeLog(realPath, sysUser, "第11步,比较详单计算结果,比较后resultFlg=" + resultFlg[0], operation, fileName);
		}
		expDetailJson.put("expDetailJson", arrayExpDetailJson);

		return expDetailJson.toString();
	}

	/**
	 * 获取当前用例执行记录的免费资源结果
	 * 
	 * @param useCase
	 * @return
	 * @throws Exception
	 */
	public String resultResource(UseCase useCase, Map<String, Map<String, String>> resourceResultToMap,
			boolean[] resultFlg) throws Exception {
		String resultResource = new String(useCase.getuCExpect(), "GBK");// 免费资源结果预期json字符串
		logUtils.writeLog(realPath, sysUser, "第12步,比较免费资源计算结果,并处理结果,免费资源预期:" + resultResource, operation, fileName);
		JSONObject jsonObject = JSONObject.fromObject(resultResource);// 免费资源预期结果json对象
		JSONArray jsonArray = jsonObject.getJSONArray("uCExpect");// 免费资源预期结果中的详细集合
		JSONArray array = new JSONArray();
		for (int i = 0; i < jsonArray.size(); i++) {// 遍历免费资源预期的每一个产品
			JSONObject proOfExp = jsonArray.getJSONObject(i);// 获取免费资源预期的每一个产品
			String type = (String) proOfExp.get("type");// 获取预期结果的产品类型：0 本月套餐
														// 1上月结转 2 本月和上月结转都有
			String proId = (String) proOfExp.get("name");// 获取预期产品的产品id
			Map<String, String> proOfResult = resourceResultToMap.get(proId.trim());// 获取结果集中的相应产品id的产品
			logUtils.writeLog(realPath, sysUser, "第12步,比较免费资源计算结果,并处理结果,比较产品:" + proId + ",预期" + proOfExp + ";计算结果:"
					+ proOfResult + "比较前resultFlg=" + resultFlg[0], operation, fileName);
			if (proOfResult == null) {// 结果中没有该产品
				throw new RoamProjectException(useCase.toString2() + ",预期免费资源有产品:" + proId + ",而结果中没有该产品");
			} else {
				String value_residue = proOfResult.get("value_residue");// 获取产品执行结果的值，可能是本月_上月，本月，上月
				value_residue = value_residue.trim();
				if (type.equals("0")) {// 本月套餐
					String val = (String) proOfExp.get("val");// 获取预期值
					if (val == null) {
						if (value_residue != null) {
							resultFlg[0] = false;
						}
					} else if (val.trim().equals("")) {
						if (value_residue.trim().equals("")) {
							resultFlg[0] = false;
						}
					} else {
						if (!compare(val, value_residue)) {
							resultFlg[0] = false;
						}
					}
					proOfExp.put("realityVal", value_residue);
				} else if (type.equals("1")) {// 上月结转
					String val = (String) proOfExp.get("val");// 获取预期值
					if (val == null) {
						if (value_residue != null) {
							resultFlg[0] = false;
						}
					} else if (val.trim().equals("")) {
						if (value_residue.trim().equals("")) {
							resultFlg[0] = false;
						}
					} else {
						if (!compare(val, value_residue)) {
							resultFlg[0] = false;
						}
					}
					proOfExp.put("realityVal", value_residue);
				} else if (type.equals("2")) {// 本月套餐和上月结转都有
					if (!value_residue.contains("_")) {
						throw new RoamProjectException(
								useCase.toString2() + ",预期免费资源有产品:" + proId + ",该产品预期既有本月产品值,又有上月结转值,而结果中仅有一个值,无法进行比较");
					} else {
						String[] split = value_residue.split("_");
						String val = (String) proOfExp.get("val");// 获取本月预期值
						if (val == null) {
							if (split[0] != null) {
								resultFlg[0] = false;
							}
						} else if (val.trim().equals("")) {
							if (split[0].trim().equals("")) {
								resultFlg[0] = false;
							}
						} else {
							if (!compare(val, split[0])) {
								resultFlg[0] = false;
							}
						}
						proOfExp.put("realityVal", split[1]);
						String carryVal = proOfExp.getString("carryVal");// 获取结转预期值
						if (carryVal == null) {
							if (split[1] != null) {
								resultFlg[0] = false;
							}
						} else if (carryVal.trim().equals("")) {
							if (split[1].trim().equals("")) {
								resultFlg[0] = false;
							}
						} else {
							if (!compare(carryVal, split[1])) {
								resultFlg[0] = false;
							}
						}
						proOfExp.put("realityTextProCarrVal", split[1]);
					}
				}
				array.add(proOfExp);
			}
			logUtils.writeLog(realPath, sysUser,
					"第12步,比较免费资源计算结果,并处理结果,比较产品:" + proId + ",比较后resultFlg=" + resultFlg[0], operation, fileName);
		}
		jsonObject.put("uCExpect", array);
		return jsonObject.toString();
	}

	/**
	 * 获取当前用例执行记录的累积量结果
	 * 
	 * @param useCase
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String resultTotal(UseCase useCase) throws UnsupportedEncodingException {
		if (useCase.getuCExpAmount() == null || useCase.getuCExpAmount().length <= 0) {
			return "";
		}
		String resultTotal = new String(useCase.getuCExpAmount(), "GBK");// 免费资源结果
		if (Convert.isEmpty(resultTotal)) {
			return "";
		}
		JSONObject jsonObject = JSONObject.fromObject(resultTotal);
		JSONArray jsonArray = jsonObject.getJSONArray("uCExpAmount");
		JSONArray array = new JSONArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject json_to_string = jsonArray.getJSONObject(i);
			json_to_string.put("realityVal", 1);
			array.add(json_to_string);
		}
		jsonObject.put("uCExpAmount", array);
		return jsonObject.toString();
	}

	/**
	 * 比较
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	private boolean compare(String s1, String s2) {
		/*if ((s1 == null && s2 != null) || (s2 == null && s1 != null)) {
			return false;
		}*/
		//处理存在空值的比较
		if(s1 == null || s2 == null ){
			if (s1 == null) {
				if (s2 == null ) {
					return true;
				}else if (s2.trim().equals("")||s2.trim().equals("0")) {
					return true;
				}
			}
			if (s2 == null) {
				if (s1 == null) {
					return true;
				} else if (s1.trim().equals("") || s1.trim().equals("0")) {
					return true;
				}
			}
			return false;
		}
		s1 = s1.trim();
		s2 = s2.trim();
		try {
			if (Integer.parseInt(s1) == Integer.parseInt(s2)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			try {
				if (Double.parseDouble(s1) == Double.parseDouble(s2)) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e2) {
				try {
					if (s1.equals(s2)) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e3) {
					return false;
				}
			}
		}
	}

	private void wrongMangger(Exception e, int step, UseCase useCase) throws UnsupportedEncodingException {
		useCase.setIsPass(21);

		if (e instanceof RoamProjectException) {
			// 自定义类型异常处理
			RoamProjectException roamProjectException = (RoamProjectException) e;

			useCase.setExceptionStep(step);

			// 异常信息
			String message = roamProjectException.getMessage();
			useCase.setExceptionMessageByte(message.getBytes("GBK"));
			useCase.setExceptionMessage(message);

			// 异常原因
			Throwable cause = roamProjectException.getCause();
			String causeStr = cause == null ? "" : cause.toString();
			useCase.setExceptionCause(causeStr);
			useCase.setExceptionCauseByte(causeStr.getBytes("GBK"));

			// 异常路径
			StackTraceElement[] stackTrace = roamProjectException.getStackTrace();
			String trace = "";
			for (StackTraceElement stackTraceElement : stackTrace) {
				String str = stackTraceElement.getFileName() + "." + stackTraceElement.getClassName() + "."
						+ stackTraceElement.getMethodName() + "." + stackTraceElement.getLineNumber();
				trace = trace + str + "\r\n";
			}
			useCase.setExceptionTrace(trace);
		} else {
			// 非自定义异常处理
			useCase.setExceptionStep(step);

			// 异常信息
			String message = e.getMessage();
			useCase.setExceptionMessage(message);

			// 异常原因
			Throwable cause = e.getCause();
			useCase.setExceptionCause(cause == null ? null : cause.toString());

			// 异常路径
			StackTraceElement[] stackTrace = e.getStackTrace();
			String trace = "";
			for (StackTraceElement stackTraceElement : stackTrace) {
				String str = stackTraceElement.getFileName() + "." + stackTraceElement.getClassName() + "."
						+ stackTraceElement.getMethodName() + "." + stackTraceElement.getLineNumber();
				if (str.contains("com.guoll") || str.contains("util")) {
					trace = trace + str + "\r\n";
				}
			}
			useCase.setExceptionTrace(trace);
		}

	}

	private String getStringTrace(StackTraceElement[] stackTrace) {
		if (stackTrace == null) {
			return "";
		}
		String trace = "";
		for (StackTraceElement stackTraceElement : stackTrace) {
			String str = stackTraceElement.getFileName() + "." + stackTraceElement.getClassName() + "."
					+ stackTraceElement.getMethodName() + "." + stackTraceElement.getLineNumber();
			trace = trace + str + "\r\n";
		}
		return trace;
	}
	
	/**
	 * 检验是否为空
	 * @param commConfig
	 * @param use
	 */
	private void testConfigIsNull(CommConfig commConfig,String use){
		if (commConfig==null) {
			throw new RoamProjectException("第1步，验证执行环境,存在配置错误,有配置获取不到,找不到的配置："+use);
		}
	}
}
