package com.guoll.modules.framework.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author wanggh
 * 
 *         TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class FileUtil {

	public static final String SEPERATOR = "/";// System.getProperty("file.separator");
												// // 系统路径分隔符
	private static final String UPLOAD = "workplan_accressory";
	private static final String CATALINA_HOME = "catalina.home";

	/**
	 * 得到文件目录
	 */
	public static String getFilePath(String fullpathname) {
		String rtn = fullpathname;
		int index = 0;
		if (fullpathname != null
				&& (index = fullpathname.lastIndexOf(FileUtil.SEPERATOR)) != -1) {
			rtn = fullpathname.substring(0, index);
		}
		return rtn;
	}

	/**
	 * 检查文件夹是否已经建立,如果没有则创建
	 * 
	 * @param newFileName
	 *            物理路径
	 */
	public static boolean makeDir(String newFileName) {
		boolean returnBoolean = false;

		if (newFileName == null)
			return returnBoolean;
		File file = new File(newFileName);
		if (!file.exists()) {
			boolean md = file.mkdirs();
			if (md)
				returnBoolean = true;

		}
		return returnBoolean;
	}

	/**
	 * 根据物理文件名称，判断似是否存在。
	 * 
	 * @param newFile
	 *            String 含物理路径
	 * @return boolean
	 */
	public static boolean existFile(String newFile) {
		try {
			File nf = new File(newFile);

			return nf.exists();
		} catch (Exception e) {

			return false;
		}
	}

	/**
	 * 检查文件是否已经建立,如果没有则创建
	 * 
	 * @param newFileName
	 *            物理路径
	 */
	public static boolean makeFile(String newFileName) {

		File file = null;
		if (newFileName == null)
			return false;
		try {
			file = new File(newFileName);
			if (!file.exists()) {
				boolean mf = false;
				mf = file.createNewFile();

				if (!mf)
					return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}

	/**
	 * 根据物理文件名称，判断似是否存在。
	 * 
	 * @param newFile
	 *            String 含物理路径
	 * @return boolean
	 */
	public static boolean delFile(String newFile) {
        boolean returnBoolean = false;
            File nf = new File(newFile);

            if (nf.exists()) {
                returnBoolean = nf.getAbsoluteFile().delete();
            }
        return returnBoolean;

    }
	/**
	 * 检查文件夹是否已经建立,如果有则删除
	 * 
	 * @param newFileName
	 *            物理路径
	 */
	public static boolean delDir(String newFileName) {

		System.out.println("-------delDir------newFileName--" + newFileName);

		boolean returnBoolean = false;

		if (newFileName == null)
			return returnBoolean;
		File file = new File(newFileName);

		System.out.println("-------delDir------file--" + file.getAbsolutePath());
		deletFile(file);
		file = new File(newFileName);
		if (!file.exists())
			returnBoolean = true;
		System.out.println("-------delDir------returnBoolean--" + returnBoolean);

		return returnBoolean;
	}

	/**
	 * 删除文件
	 * @param file
	 */
	public static void deletFile(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (File fileItem : files) {
					deletFile(fileItem);
				}
			}
			file.delete();
		}
	}
	
	

	public static String makeWorkPlanFile(MultipartFile mfile,String f_department_id,String f_workname_id,String workplanmonth) throws Exception {
		String fileName = mfile.getOriginalFilename();
		// 判断对应的企业目录是否生成，如生成，则直接保存文件，否则创建目录后，再保存文件
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String saveName = fileName.substring(0, fileName.lastIndexOf(".")) + "_" + sdf.format(new Date()) + fileName.substring(fileName.lastIndexOf("."));
		String path = getCatalinaHome() + File.separator + UPLOAD
				+ File.separator;
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		//部门ID
		String depapath = path + f_department_id + File.separator;
		File depafilepath = new File(depapath);
		if (!depafilepath.exists()) {
			depafilepath.mkdirs();
		}
		//工作名称ID
		String worknamepath = depapath + f_workname_id + File.separator;
		File worknamefilepath = new File(worknamepath);
		if (!worknamefilepath.exists()) {
			worknamefilepath.mkdirs();
		}
		//月份
		String workplanmonthpath = worknamepath + workplanmonth + File.separator;
		File workplanmonthfilepath = new File(workplanmonthpath);
		if (!workplanmonthfilepath.exists()) {
			workplanmonthfilepath.mkdirs();
		}
		String filePath = workplanmonthpath + saveName;
		File savepath = new File(filePath);
		String content = "";
		StringBuilder sbu = new StringBuilder();
		sbu.append(fileName);
		sbu.append("@@");
		sbu.append(saveName);
		sbu.append("@@");
		sbu.append(filePath);
		// 转存文件
		mfile.transferTo(savepath);
		content = sbu.toString();
		return content;
	}
	
	public static String getCatalinaHome() {
		return System.getProperty(CATALINA_HOME);
	}

	
	 public static void main(String[] args) {
			// System.out.println(FileUtil.getPath("C:\\",
			// "E:\\project\\bms-ssh\\web\\WEB-INF\\lib"));
		}

}