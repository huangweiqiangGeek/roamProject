package com.guoll.modules.billtemplate.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.guoll.modules.billtemplate.bean.BillTemplate;
import com.guoll.modules.billtemplate.service.BillTemplateService;
import com.guoll.modules.commType.bean.CommType;
import com.guoll.modules.commType.service.CommTypeService;
import com.guoll.modules.framework.base.BaseController;
import com.guoll.modules.framework.util.SessionUtils;
import com.guoll.modules.sysmanage.bean.SysUser;

import net.sf.json.JSONArray;
import util.DateUtil;

/**
 * 话单模版管理 注意,因最初开发所致,本类中所有转换成字节数组和字节数组转换会字符串都采用GBK编码
 * 
 * @author lukas 414024003@qq.com
 * @Date 2017年3月15日 14:09:47
 * @version 1.0
 */
@Controller
@RequestMapping("/billTemplate")
public class BillTemplateController extends BaseController {

	@Autowired(required = false)
	BillTemplateService billTemplateService;
	@Autowired(required = false)
	CommTypeService commTypeService;
	@Autowired(required = false)
	private  String  templateAttributes;  
	public String getTemplateAttributes() {
		return templateAttributes;
	}

	public void setTemplateAttributes(String templateAttributes) {
		this.templateAttributes = templateAttributes;
	}

	/**
	 * 拷贝话单
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/copyTemp")
	@ResponseBody
	public Map<String, Object> copyTemp(HttpServletRequest request, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] ids = id.split(",");
		for (String anId : ids) {
			try {
				BillTemplate querySysBillTemplateById = billTemplateService
						.querySysBillTemplateById(Integer.parseInt(id));
				querySysBillTemplateById.setId(null);
				billTemplateService.saveBillTemplate(querySysBillTemplateById);
				map.put("success", 1);
			} catch (Exception e) {
				map.put("success", 0);
				e.printStackTrace();
			}
		}
		// return "billtemplate/list";
		return map;
	}

	/**
	 * 上传话单模板
	 */
	@RequestMapping("/uploadBillTemplate")
	@ResponseBody
	public Map<String, String> uploadBillTemplate(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {

		// 获得原始文件名
		//文件命名规则:****_1
		String fileName = file.getOriginalFilename();
		//判断模板类型
		int billType=0;
		try {
//			int k=fileName.lastIndexOf("_")+1;
//			int m=fileName.lastIndexOf(".");
//			String uu=fileName.substring(k,m);
			billType = Integer.parseInt(fileName.substring(fileName.lastIndexOf("_")+1, fileName.lastIndexOf(".")));
		} catch (Exception e) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("err", false+"");
			map.put("errMsg","文件名格式错误，完整的文件名格式：名字_类型数字.xls，如：新建表格_1.xls");
			return map;
		}
		
		// 新文件名,DateUtil为自定义的返回各种格式时间的工具类
		String newFileName = DateUtil.getSdfTimes3() + "_" + fileName;

		// 获得项目的路径
		String realPath = request.getSession().getServletContext().getRealPath("/");// 返回的是:E:\tomcat7\webapps\roamProject\
		String realPath2 = realPath.substring(0, realPath.indexOf("webapps") - 1);// 返回的是:E:\tomcat7
		String path = realPath2 + File.separator + "Excels" + File.separator + DateUtil.getDay() + File.separator
				+ getSessionUser(request).getUser_code() + File.separator; // 设定文件保存的目录

		// 保存文件
		File f = new File(path);
		if (!f.exists())
			f.mkdirs();
		if (!file.isEmpty()) {
			try {
				FileOutputStream fos = new FileOutputStream(path + newFileName);
				InputStream in = file.getInputStream();
				int b = 0;
				while ((b = in.read()) != -1) {
					fos.write(b);
				}
				fos.close();
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 调用解析Excel并保存到数据库的方法
		//{9=6,7,8, 10=6,7,8, 11=6,7,8,11,20}
		//Map<String, String> map = importIntoDb(path + newFileName,billType,request);
		Map<String, String> map = importIntoDb(path + newFileName,billType,request);
		return map;
		
		/*Map<String, Object> result = new HashMap<String, Object>();
		if (map.size()>0) {//有异常
			result.put("status", false);
			String str = "以下数据存在问题:</br>";
			for (Integer row : map.keySet()) {
				str=str+"第" + row +"行,第"+map.get(row)+"列;</br>";
			}
			result.put("msg", str);
			int size = map.size();
			result.put("errsum",size );
			
		}else {//无异常
			result.put("status", true);
		}
		request.setAttribute("errTemps", map);*/

		// 返回需要的页面
	}
	/**
	 * 导入模板到数据库
	 * @param fileName
	 * @param billType
	 * @param request
	 * @return
	 */
	private Map<String, String> importIntoDb(String fileName,int billType,HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		int success = 0;
		try {
			// 第1步,读取文件
			  Workbook workbook = null;
		        try {
		        	workbook = new XSSFWorkbook(fileName);
		        } catch (Exception ex) {
		        	workbook = new HSSFWorkbook(new FileInputStream(fileName));
		        }
	//		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileName));
			String realPath = request.getServletContext().getRealPath("/");
			String operation = "importBillTemplate";
			SysUser sysUser = getSessionUser(request);
			String fileName2 = DateUtil.getSdfTimes3();
			logUtils.writeLog(realPath, sysUser, "第1步,接收Excel文件保存到本地,本地文件:"+fileName, operation, fileName2);

			// 第2步,遍历每一个sheet
			int numberOfSheets = workbook.getNumberOfSheets();
			Sheet sheet = null;
			int sheetNo = 0;
			while (sheetNo < numberOfSheets) {
				logUtils.writeLog(realPath, sysUser, "第2步,遍历每一个sheet", operation, fileName2);
			/*
			 *下面被注释掉的代码为每一行是一个模板时的处理代码
			 * 
				if ((sheet = workbook.getSheetAt(sheetNo)) == null) {
					sheetNo++;
					continue;
				} else {
					sheetNo++;
				}
				// 第3步,读取第一行的表头
				Map<Integer, String> names = new HashMap<Integer, String>();
				HSSFRow nameRow = sheet.getRow(0);
				if (nameRow == null) {
					continue;
				}
				short nameLastCellNum = nameRow.getLastCellNum();
				short nameFirstcellNum = 5;
				for (int i = nameFirstcellNum; i < nameLastCellNum; i++) {
					names.put(i, nameRow.getCell(i).getStringCellValue());
				}
				// 第4步,读取第二行汉语解释
				Map<Integer, String> chineseNames = new HashMap<Integer, String>();
				HSSFRow chineseNameRow = sheet.getRow(1);
				short chineseNameLastCellNum = chineseNameRow.getLastCellNum();
				short chineseNameFirstcellNum = 5;
				for (int i = chineseNameFirstcellNum; i < chineseNameLastCellNum; i++) {
					chineseNames.put(i, chineseNameRow.getCell(i).getStringCellValue());
				}

				// 第5步,遍历每一行模版
				int firstRowNum = 8;
				int lastRowNum = sheet.getLastRowNum();
				for (int i = firstRowNum; i <= lastRowNum; i++) {
					// 错误列记录
					String wrongColums = "";
					try {
						// 当前行
						HSSFRow row = sheet.getRow(i);
						// 最大
						short lastCellNum = row.getLastCellNum();
						// 当前列号
						int j = 0;//
						// 数据封装对象
						BillTemplate billTemplate = new BillTemplate();
						// 字段json拼接对象
						// 格式:
						// {“templateAttribute“:[
						// {“fieldName“:“EventFormatType“,“choiceAway“:“20“,“hide“:“是“,“range“:““,“scriptUrl“:““,“explain“:“标准事件格式类型“,“default“:“01“},
						// {“fieldName“:“roll_flag“,“choiceAway“:“10“,“hide“:“是“,“range“:““,“scriptUrl“:““,“explain“:“冲销标记“,“default“:“0“}
						// ]}
						String attr = "{“templateAttribute“:[";
						// 第6步,设置模版基本信息
						try {// 省份
							billTemplate.setProvinceName(row.getCell(j++).getStringCellValue());
						} catch (Exception e) {
							wrongColums = wrongColums(j, wrongColums);
						}
						try {// 名字
							billTemplate.setTemplateName(row.getCell(j++).getStringCellValue());
						} catch (Exception e) {
							wrongColums = wrongColums(j, wrongColums);
						}
						try {// 备注
							billTemplate.setTemplateRemark(row.getCell(j++).getStringCellValue());
						} catch (Exception e) {
							wrongColums = wrongColums(j, wrongColums);
						}
						try {// 类型
							billTemplate.setTemplateType((int) row.getCell(j++).getNumericCellValue());
						} catch (Exception e) {
							wrongColums = wrongColums(j, wrongColums);
						}
						j++;//跳过前几行作为表头的第E列
						// 第7步,拼接模板字符串,每一次拼进去如下格式内容
						// {“fieldName“:“EventFormatType“,“choiceAway“:“20“,“hide“:“是“,“range“:““,“scriptUrl“:““,“explain“:“标准事件格式类型“,“default“:“01“},
						for (; j < lastCellNum;j++) {
							try {
								// 7.1获取当前单元格的内容
								String cell = row.getCell(j).getStringCellValue();
								// 7.2拆分
								String[] split = cell.split(",");
								attr = attr + "{“fieldName“:“" + names.get(j) + "“,“choiceAway“:“" + split[0]
										+ "“,“hide“:“" + split[4] + "“,“range“:“" + split[1] + "“,“scriptUrl“:“"
										+ split[2] + "“,“explain“:“" + chineseNames.get(j) + "“,“default“:“" + split[3]
										+ "“},";
							} catch (Exception e) {
								wrongColums = wrongColums(j, wrongColums);
							}
						}
						if (!wrongColums.trim().equals("")) {
							throw new RoamProjectException("本模板有用例拼接错误");
						}
						attr = attr.substring(0, attr.length()-1)+"]}";
						billTemplate.setTemplateAttribute(attr.getBytes("GBK"));
						billTemplateService.saveBillTemplate(billTemplate);
						success++;
					} catch (Exception e) {
						map.put(i + 1, wrongColums);
					}
				}
			*/
				if ((sheet = workbook.getSheetAt(sheetNo)) == null) {
					sheetNo++;
					continue;
				} else {
					sheetNo++;
				}
				
				//第3步,读取第一行,获取模板名字
				logUtils.writeLog(realPath, sysUser, "第3步,读取第一行,获取模板名字集合", operation, fileName2);
				Row rowOfBillNames = sheet.getRow(0);
				if (rowOfBillNames==null) {
					logUtils.writeLog(realPath, sysUser, "第3步,读取第一行,获取模板名字集合,空sheet,结束本次循环", operation, fileName2);
					continue;
				}
				short lastCellNum = rowOfBillNames.getLastCellNum();
				Map<Integer, String> billnames = new HashMap<Integer, String>();
				for(int i = 4;i<lastCellNum;i++){
					Cell cell = rowOfBillNames.getCell(i);
					if (cell==null) {
						break;
					}else {
						billnames.put(i, getStringValue(cell));
					}
				}
				logUtils.writeLog(realPath, sysUser, "第3步,读取第一行,获取到的模板名字集合:"+rowOfBillNames, operation, fileName2);
				//第4步,读取第2列,获取是否隐藏,读取第3列,获取中文字段,读取第4列,获取英文字段
				logUtils.writeLog(realPath, sysUser, "第4步,读取第2列,获取是否隐藏,读取第3列,获取中文字段,读取第4列,获取英文字段", operation, fileName2);
				int lastRowNum = sheet.getLastRowNum();
				Map<Integer, String> isHidens = new HashMap<Integer, String>();
				Map< Integer, String> chineses = new HashMap<Integer, String>();
				Map<Integer, String> englishes = new HashMap<Integer, String>();
				for(int i = 1;i<=lastRowNum;i++){
					Row row = sheet.getRow(i);
					Cell isHidenCell = row.getCell(1);
					if (isHidenCell==null) {
						isHidens.put(i, "");
					}else {
						isHidens.put(i, getStringValue(isHidenCell));
					}
					Cell chineseCell = row.getCell(2);
					if (chineseCell==null) {
						chineses.put(i, "");
					}else {
						chineses.put(i, getStringValue(chineseCell));
					}
					Cell englishCell = row.getCell(3);
					if (englishCell==null) {
						englishes.put(i, "");
					}else{
						englishes.put(i, getStringValue(englishCell));
						
					}
				}
				logUtils.writeLog(realPath, sysUser, "第4步,读取第2列,获取到的是否隐藏集合:"+isHidens, operation, fileName2);
				logUtils.writeLog(realPath, sysUser, "第4步,读取第2列,获取到的中文字段集合:"+chineses, operation, fileName2);
				logUtils.writeLog(realPath, sysUser, "第4步,读取第2列,获取到的英文字段集合:"+englishes, operation, fileName2);
				//第5步,遍历每一列,拼接每一个模板
				logUtils.writeLog(realPath, sysUser, "第5步,遍历每一列,拼接每一个模板", operation, fileName2);
				for (int i = 4; i < lastCellNum; i++) {
					logUtils.writeLog(realPath, sysUser, "第5步,遍历每一列,拼接第"+(i+1)+"列模板", operation, fileName2);
					//拼接字符串
					String attr = "{“templateAttribute“:[";
					for (int j = 6; j <= lastRowNum; j++) {
						Row row = sheet.getRow(j);
						 String flag=getStringValue(row.getCell(2));
						if("".equals(flag)){
							break;
						}
						Cell cell = row.getCell(i);
						if(cell==null){
							break;
						}
					String erlik=	englishes.get(j); 
						attr = attr + "{“fieldName“:“" + englishes.get(j) + "“,“choiceAway“:“" + 20
								+ "“,“hide“:“" + isHidens.get(j) + "“,“range“:“" + "" + "“,“scriptUrl“:“"
								+ "" + "“,“explain“:“" + chineses.get(j) + "“,“default“:“" + 
								(cell == null ? "" : getStringValue(cell)) + "“},";
					}
					attr = attr.substring(0, attr.length()-1)+"]}";
					logUtils.writeLog(realPath, sysUser, "第5步,遍历每一列,拼接到的第"+(i+1)+"列模板json字符串:"+attr, operation, fileName2);
					//创建一个模板设定值
					BillTemplate billTemplate = new BillTemplate();
					billTemplate.setTemplateName(billnames.get(i));
					billTemplate.setTemplateType(billType);
					billTemplate.setTemplateRemark("");//目前没有备注
			 		billTemplate.setTemplateStatus(1);
					billTemplate.setProvinceName(getSessionUser(request).getPost_name());
					billTemplate.setTemplateAttribute(attr.getBytes("GBK"));
					billTemplateService.saveBillTemplate(billTemplate);
					success++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", true+"");
		}
		//第6步,统计结果
		map.put("status", true+"");
		map.put("number", success+"");
		map.put("err", true+"");
		return map;
	}
	
	


	/**
	 * 带指定场景上传语音话单模板
	 */
	@RequestMapping("/uploadBillTemplate2")
	@ResponseBody
	public Map<String, String> uploadBillTemplate2(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {

		// 获得原始文件名
		//文件命名规则:****_1
		String fileName = file.getOriginalFilename();
		//判断模板类型
		int billType=0;
		try {
			billType = Integer.parseInt(fileName.substring(fileName.lastIndexOf("_")+1, fileName.lastIndexOf(".")));
		} catch (Exception e) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("err", false+"");
			map.put("errMsg","文件名格式错误，完整的文件名格式：名字_类型数字.xls，如：新建表格_1.xls");
			return map;
		}
		
		// 新文件名,DateUtil为自定义的返回各种格式时间的工具类
		String newFileName = DateUtil.getSdfTimes3() + "_" + fileName;

		// 获得项目的路径
		String realPath = request.getSession().getServletContext().getRealPath("/");// 返回的是:E:\tomcat7\webapps\roamProject\
		String realPath2 = realPath.substring(0, realPath.indexOf("webapps") - 1);// 返回的是:E:\tomcat7
		String path = realPath2 + File.separator + "Excels" + File.separator + DateUtil.getDay() + File.separator
				+ getSessionUser(request).getUser_code() + File.separator; // 设定文件保存的目录

		// 保存文件
		File f = new File(path);
		if (!f.exists())
			f.mkdirs();
		if (!file.isEmpty()) {
			try {
				FileOutputStream fos = new FileOutputStream(path + newFileName);
				InputStream in = file.getInputStream();
				int b = 0;
				while ((b = in.read()) != -1) {
					fos.write(b);
				}
				fos.close();
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 调用解析Excel并保存到数据库的方法
		//{9=6,7,8, 10=6,7,8, 11=6,7,8,11,20}
		//Map<String, String> map = importIntoDb(path + newFileName,billType,request);
		Map<String, String> map = importIntoDb2(path + newFileName,billType,request);
		return map;
		
		/*Map<String, Object> result = new HashMap<String, Object>();
		if (map.size()>0) {//有异常
			result.put("status", false);
			String str = "以下数据存在问题:</br>";
			for (Integer row : map.keySet()) {
				str=str+"第" + row +"行,第"+map.get(row)+"列;</br>";
			}
			result.put("msg", str);
			int size = map.size();
			result.put("errsum",size );
			
		}else {//无异常
			result.put("status", true);
		}
		request.setAttribute("errTemps", map);*/

		// 返回需要的页面
	}
	
	private Map<String, String> importIntoDb2(String fileName,int billType,HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		int success = 0;
		try {
			// 第1步,读取文件
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileName));
			String realPath = request.getServletContext().getRealPath("/");
			String operation = "importBillTemplate";
			SysUser sysUser = getSessionUser(request);
			String fileName2 = DateUtil.getSdfTimes3();
			logUtils.writeLog(realPath, sysUser, "第1步,接收Excel文件保存到本地,本地文件:"+fileName, operation, fileName2);

			// 第2步,遍历每一个sheet
			int numberOfSheets = workbook.getNumberOfSheets();
			HSSFSheet sheet = null;
			int sheetNo = 0;
			while (sheetNo < numberOfSheets) {
				logUtils.writeLog(realPath, sysUser, "第2步,遍历每一个sheet", operation, fileName2);
				if ((sheet = workbook.getSheetAt(sheetNo)) == null) {
					sheetNo++;
					continue;
				} else {
					sheetNo++;
				}
				
				//第3步,读取第一行,获取模板名字
				logUtils.writeLog(realPath, sysUser, "第3步,读取第一行,获取模板名字集合", operation, fileName2);
				HSSFRow rowOfBillNames = sheet.getRow(0);
			
				if (rowOfBillNames==null) {
					//logUtils.writeLog(realPath, sysUser, "第3步,读取第一行,获取模板名字集合,空sheet,结束本次循环", operation, fileName2);
					continue;
				}
				short lastCellNum = rowOfBillNames.getLastCellNum();
				/*
				 *Map<Integer, String> billnames = new HashMap<Integer, String>();
				for(int i = 4;i<lastCellNum;i++){
					HSSFCell cell = rowOfBillNames.getCell(i);
					if (cell==null) {
						billnames.put(i, "");
					}else {
						billnames.put(i, getStringValue(cell));
					}
				}
				logUtils.writeLog(realPath, sysUser, "第3步,读取第一行,获取到的模板名字集合:"+rowOfBillNames, operation, fileName2);
				*/
				//第4步,读取第2列,获取是否隐藏,读取第3列,获取中文字段,读取第4列,获取英文字段
				logUtils.writeLog(realPath, sysUser, "第4步,读取第2列,获取是否隐藏,读取第3列,获取中文字段,读取第4列,获取英文字段", operation, fileName2);
				int lastRowNum = sheet.getLastRowNum();
				Map<Integer, String> isHidens = new HashMap<Integer, String>();
				Map< Integer, String> chineses = new HashMap<Integer, String>();
				Map<Integer, String> englishes = new HashMap<Integer, String>();
				for(int i = 1;i<=lastRowNum;i++){
					HSSFRow row = sheet.getRow(i);
					HSSFCell isHidenCell = row.getCell(1);
					if (isHidenCell==null) {
						isHidens.put(i, "");
					}else {
						isHidens.put(i, getStringValue(isHidenCell));
					}
					HSSFCell chineseCell = row.getCell(2);
					if (chineseCell==null) {
						chineses.put(i, "");
					}else {
						chineses.put(i, getStringValue(chineseCell));
					}
					HSSFCell englishCell = row.getCell(3);
					if (englishCell==null) {
						englishes.put(i, "");
					}else{
						englishes.put(i, getStringValue(englishCell));
						
					}
				}
				logUtils.writeLog(realPath, sysUser, "第4步,读取第2列,获取到的是否隐藏集合:"+isHidens, operation, fileName2);
				logUtils.writeLog(realPath, sysUser, "第4步,读取第2列,获取到的中文字段集合:"+chineses, operation, fileName2);
				logUtils.writeLog(realPath, sysUser, "第4步,读取第2列,获取到的英文字段集合:"+englishes, operation, fileName2);
				//第5步,遍历每一列,拼接每一个模板
				logUtils.writeLog(realPath, sysUser, "第5步,遍历每一列,拼接每一个模板", operation, fileName2);
				for (int i = 4; i < lastCellNum; i++) {
					logUtils.writeLog(realPath, sysUser, "第5步,遍历每一列,拼接第"+(i+1)+"列模板", operation, fileName2);
					//拼接字符串
					String attr = "{“templateAttribute“:[";
					for (int j = 6; j <= lastRowNum; j++) {
						HSSFRow row = sheet.getRow(j);
						HSSFCell cell = row.getCell(i);
						attr = attr + "{“fieldName“:“" + englishes.get(j) + "“,“choiceAway“:“" + 20
								+ "“,“hide“:“" + isHidens.get(j) + "“,“range“:“" + "" + "“,“scriptUrl“:“"
								+ "" + "“,“explain“:“" + chineses.get(j) + "“,“default“:“" + 
								(cell == null ? "" : getStringValue(cell)) + "“},";
					}
					attr = attr.substring(0, attr.length()-1)+"]}";
					logUtils.writeLog(realPath, sysUser, "第5步,遍历每一列,拼接到的第"+(i+1)+"列模板json字符串:"+attr, operation, fileName2);
					//创建一个模板设定值
					BillTemplate billTemplate = new BillTemplate();
					//拼接模版名
					String billTmpName = ""
							+getStringValue(sheet.getRow(1).getCell(i))+"-"//场景1
							+getStringValue(sheet.getRow(2).getCell(i))+"-"//场景2
							+getStringValue(sheet.getRow(3).getCell(i))+"-"//场景3
							+getStringValue(sheet.getRow(4).getCell(i))+"-"//场景4
							+getStringValue(sheet.getRow(5).getCell(i));//特殊场景
					billTemplate.setTemplateName(billTmpName);
					billTemplate.setTemplateType(billType);
					billTemplate.setTemplateRemark("");//目前没有备注
			 		billTemplate.setTemplateStatus(1);
					billTemplate.setProvinceName(getSessionUser(request).getPost_name());
					billTemplate.setTemplateAttribute(attr.getBytes("GBK"));
					billTemplateService.saveBillTemplate(billTemplate);
					success++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", true+"");
		}
		//第6步,统计结果
		map.put("status", true+"");
		map.put("number", success+"");
		map.put("err", true+"");
		return map;
	}

	private String wrongColums(int j, String wrongColums) {
		if (wrongColums.trim().equals("")) {
			wrongColums = wrongColums + (j + 1);
		} else {
			wrongColums = wrongColums + "," + (j + 1);
		}
		return wrongColums;
	}

	
	private String getStringValue(Cell cell){
		if(cell!=null)
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String value;
		try {
			value = cell.getStringCellValue()+"";
			return (value.trim());
		} catch (Exception e) {
		}
		try {
			value = cell.getDateCellValue()+"";
			return (value.trim());
		} catch (Exception e) {
		}
		try {
			value = cell.getNumericCellValue()+"";
			return (value.trim());
		} catch (Exception e) {
		}
		return "";
	}
	
	/**
	 * 跳转到上传
	 */
	@RequestMapping("/uploadInit")
	public String uploadInit(HttpServletRequest request) {
		return "billtemplate/importExcelOfBillTemplet";
	}

	/**
	 * 话单模版入口
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/listInit")
	public String listInit(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("GBK");
		SysUser sysUser = (SysUser) SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		List<CommType> commTypes = new ArrayList<CommType>();
		CommType c = new CommType();
		c.setAffiliationType(1);
		commTypes = commTypeService.queryCommTypeListAll(c);
		request.setAttribute("commTypes", commTypes);
		request.setAttribute("provinceName", sysUser.getPost_name());
		return "billtemplate/list";
	}

	/**
	 * 查询话单模版列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/listPage")
	@ResponseBody
	public Map<String, Object> listPage(BillTemplate billTemplate, HttpServletRequest request)
			throws UnsupportedEncodingException {
		billTemplate.setProvinceName(getProvinceName(request));
		billTemplate.setPages();// 设置分页信息
		Map<String, Object> pageData = new HashMap<String, Object>();
		List<BillTemplate> list = new ArrayList<BillTemplate>();
		List<BillTemplate> tempList = new ArrayList<BillTemplate>();
		tempList = billTemplateService.queryBillTemplateList(billTemplate);
		for (BillTemplate t : tempList) {
			String attrJson = new String(t.getTemplateAttribute(), "GBK").replace('“', '"').replace("/", "|");
			t.setPid(t.getId());
			t.setAttrJson(attrJson);
			list.add(t);
		}
		pageData.put("total", billTemplateService.queryBillTemplateSum(billTemplate));
		pageData.put("rows", list);// billTemplateService.queryBillTemplateList(billTemplate)
		return pageData;
	}

	/**
	 * 选择话单模版类别入口
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/queryListInit")
	public String queryListInit(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("GBK");
		SysUser sysUser = (SysUser) SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		request.setAttribute("provinceName", sysUser.getPost_name());
		return "useCase/bill_list";
	}

	/**
	 * 选择话单模版列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/queryListPage")
	@ResponseBody
	public Map<String, Object> queryListPage(BillTemplate billTemplate, HttpServletRequest request)
			throws UnsupportedEncodingException {
		billTemplate.setProvinceName(getProvinceName(request));
		billTemplate.setPages();// 设置分页信息
		request.setCharacterEncoding("GBK");
		Map<String, Object> pageData = new HashMap<String, Object>();
		List<BillTemplate> list = new ArrayList<BillTemplate>();
		List<BillTemplate> tempList = new ArrayList<BillTemplate>();
		tempList = billTemplateService.queryBillTemplateList(billTemplate);
		for (BillTemplate t : tempList) {
			String attrJson = new String(t.getTemplateAttribute(), "GBK").replace('“', '#').replace("/", "|");
			t.setAttrJson(attrJson);
			list.add(t);
		}
		pageData.put("total", billTemplateService.queryBillTemplateSum(billTemplate));
		pageData.put("rows", list);
		return pageData;
	}

	/**
	 * 修改话单模版初始化
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/updateInit")
	public String updateBillTemplate(Integer id, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("GBK");
		List<CommType> commTypes = new ArrayList<CommType>();
		CommType c = new CommType();
		c.setAffiliationType(1);
		commTypes = commTypeService.queryCommTypeListAll(c);
		request.setAttribute("commTypes", commTypes);
		c.setAffiliationType(6);
		List<CommType> satusList = new ArrayList<CommType>();
		satusList = commTypeService.queryCommTypeListAll(c);
		request.setAttribute("satusList", satusList);
		if (id != null) {
			BillTemplate b = billTemplateService.querySysBillTemplateById(id);
			request.setAttribute("billTemplate", b);
			String s = new String(b.getTemplateAttribute(), "GBK").replace('“', '"');
			request.setAttribute("templateAttributes", s);// 设置模板
			return "billtemplate/edit";
		}
		return "billtemplate/list";
	}

	/**
	 * 新增话单模版初始化
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/addInit")
	public String addBillTemplate(Integer org_id, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("GBK");
		SysUser sysUser = (SysUser) SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		BillTemplate billTemplate = new BillTemplate();
		billTemplate.setProvinceName(sysUser.getPost_name());
		request.setAttribute("billTemplate", billTemplate);
		List<CommType> commTypes = new ArrayList<CommType>();
		CommType c = new CommType();
		c.setAffiliationType(1);
		commTypes = commTypeService.queryCommTypeListAll(c);
		request.setAttribute("commTypes", commTypes);
		c.setAffiliationType(6);
		List<CommType> satusList = new ArrayList<CommType>();
		satusList = commTypeService.queryCommTypeListAll(c);
		request.setAttribute("satusList", JSONArray.fromObject(satusList));
		return "billtemplate/edit";
	}

	/**
	 * 删除话单模版
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(BillTemplate b, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("GBK");
		billTemplateService.deleteBillTemplate(b);
	}
	
	/*
	 * 批量删除模板
	 * 
	 */
	@RequestMapping("/deleteList")
	@ResponseBody
	public  Map<String,Object> deleteList(String ids, HttpServletRequest request) {
		Map<String,Object>  map=new HashMap();
		try {
			request.setCharacterEncoding("GBK");
			if(ids !=null&&!"".equals(ids)){
				//取id数组,遍历
				String[]  item=ids.split(",");
					billTemplateService.deleteBillTemplateAll(item);
				map.put("result","0");//成功返回0
			}
		} catch (Exception e) {
			map.put("result","1");//失败返回1
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	/**
	 * 保存新增/修改话单模版
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/saveBillTemplate")
	public String saveBillTemplate(BillTemplate b, HttpServletRequest request) throws UnsupportedEncodingException {
             try {  
            	 byte[]  k=b.getTemplateAttribute();
            		String templateAttributes = request.getParameter("templateAttributes");
            		templateAttributes = templateAttributes.replaceAll("＜", "<").replaceAll("＞", ">");
            		b.setTemplateAttribute(templateAttributes.getBytes("GBK"));
            		billTemplateService.saveBillTemplate(b);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "billtemplate/list";
	}

	/**
	 * 同步模板与用例
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping("/updateUseCaseTemplate")
	@ResponseBody
	public Map<String, Object> updateUseCaseTemplate(Integer id) {
		Map<String, Object> map = new HashMap<>();
		try {
		billTemplateService.updateUseCaseTemplate(id);
		map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		return map;
	}
	
}