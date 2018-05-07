package util.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.servlet.http.HttpServletRequest;

import com.guoll.modules.sysmanage.bean.SysUser;

import util.DateUtil;

@SuppressWarnings("resource")
public class LogUtils {

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
		}
	}

	public  void writeLog(HttpServletRequest request, SysUser sysUser, String logs) throws Exception {
		try {
			String realPath = request.getSession().getServletContext().getRealPath("/");// 返回的是:E:\tomcat7\webapps\roamProject\
			String realPath2 = realPath.substring(0, realPath.indexOf("webapps") - 1);// 返回的是:E:\tomcat7
			String logPath = realPath2 + File.separator + "logs" + File.separator + DateUtil.getDay();// 返回的是:E:\tomcat7\logs\2017-05-08
			
			File pFile = new File(logPath);
			if (!pFile.exists()) {
				pFile.mkdirs();
			}
			
			//file 路径格式: E:\tomcat7\logs\2017-05-08\sz-209.log.txt
			File file = new File(pFile, sysUser.getUser_code() + "-" + sysUser.getId() + ".log.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
			bufferedWriter.write("[" + DateUtil.getTime2() + "]:");
			bufferedWriter.write(logs);
			bufferedWriter.newLine();
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * 记录日志
	 * 日志目录:日期/用户名-用户id/操作/
	 * 日志文件名称:时间
	 * @param request 请求
	 * @param sysUser 用户
	 * @param logs 日志内容
	 * @param operation 日志操作
	 * @param fileName 日志文件名
	 * @throws Exception
	 */
	public  void writeLog(HttpServletRequest request, SysUser sysUser, String logs,String operation,String fileName) throws Exception {
		try {
			String realPath = request.getSession().getServletContext().getRealPath("/");// 返回的是:E:\tomcat7\webapps\roamProject\
			String realPath2 = realPath.substring(0, realPath.indexOf("webapps") - 1);// 返回的是:E:\tomcat7
			String logPath = realPath2 + File.separator + "logs" + File.separator + DateUtil.getDay();// 返回的是:E:\tomcat7\logs\2017-05-08
			logPath = logPath+File.separator+sysUser.getUser_code()+"-"+sysUser.getId()+File.separator+operation;
			File pFile = new File(logPath);
			if (!pFile.exists()) {
				pFile.mkdirs();
			}
			
			File file = new File(pFile, fileName+ ".log.txt");
			System.out.println("日志名称:"+file);
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
			bufferedWriter.write("[" + DateUtil.getTime2() + "]:");
			bufferedWriter.write(logs);
			bufferedWriter.newLine();
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * 记录日志
	 * 日志目录:日期/用户名-用户id/操作/
	 * 日志文件名称:时间
	 * @param request 请求
	 * @param sysUser 用户
	 * @param logs 日志内容
	 * @param operation 日志操作
	 * @param fileName 日志文件名
	 * @throws Exception
	 */
	public  void writeLog(String realPath, SysUser sysUser, String logs,String operation,String fileName) throws Exception {
		try {
			String realPath2 = realPath.substring(0, realPath.indexOf("webapps") - 1);// 返回的是:E:\tomcat7
			String logPath = realPath2 + File.separator + "logs" + File.separator + DateUtil.getDay();// 返回的是:E:\tomcat7\logs\2017-05-08
			logPath = logPath+File.separator+sysUser.getUser_code()+"-"+sysUser.getId()+File.separator+operation;
			File pFile = new File(logPath);
			if (!pFile.exists()) {
				pFile.mkdirs();
			}
			
			File file = new File(pFile, fileName+ ".log.txt");
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
			bufferedWriter.write("[" + DateUtil.getTime2() + "]:");
			bufferedWriter.write(logs);
			bufferedWriter.newLine();
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (Exception e) {
			throw e;
		}
	}
}
