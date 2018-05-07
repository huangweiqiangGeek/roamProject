package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import com.guoll.modules.sysmanage.bean.SysUser;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import util.log.LogUtils;

public class RemoteShellExecutor {

	private Connection conn;
	/** 远程机器IP */
	private String ip;
	/** 用户名 */
	private String osUsername;
	/** 密码 */
	private String password;
	private String charset = Charset.defaultCharset().toString();

	private static final int TIME_OUT = 1000 * 5 * 60;

	/**
	 * 构造函数
	 * 
	 * @param ip
	 * @param usr
	 * @param pasword
	 */
	public RemoteShellExecutor(String ip, String usr, String pasword) {
		this.ip = ip;
		this.osUsername = usr;
		this.password = pasword;
	}

	/**
	 * 登录
	 * 
	 * @return
	 * @throws IOException
	 */
	private boolean login() throws IOException {
		conn = new Connection(ip);
	
		conn.connect();
		return conn.authenticateWithPassword(osUsername, password);
	}

	/**
	 * 执行脚本
	 * 
	 * @param cmds
	 * @return
	 */
	// public int exec(String cmds) throws Exception {
	public String exec(String cmds, HttpServletRequest request, SysUser sysUser, LogUtils logUtils){
		InputStream stdOut = null;
		InputStream stdErr = null;
		String outStr = "";
		String outErr = "";
		int ret = -1;
		try {
			if (login()) {
				// Open a new {@link Session} on this connection
				Session session = conn.openSession();// 开启会话
				// Execute a command on the remote machine.
				logUtils.writeLog(request, sysUser, "用例执行,调用脚本命令:" + cmds);
				session.execCommand(cmds);

				stdOut = new StreamGobbler(session.getStdout());
				outStr = processStream(stdOut, "UTF-8");

				stdErr = new StreamGobbler(session.getStderr());
				outErr = processStream(stdErr, "UTF-8");

				session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);

				logUtils.writeLog(request, sysUser, "执行脚本,outStr=" + outStr);
				logUtils.writeLog(request, sysUser, "执行脚本,outErr=" + outErr);

				ret = session.getExitStatus();
				return outStr;
			} else {
				throw new RoamProjectException("登录远程机器失败" + ip); // 自定义异常类 
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RoamProjectException(e); // 自定义异常类 
		} finally {
			if (conn != null) {
				conn.close();
			}
			IOUtils.closeQuietly(stdOut);
			IOUtils.closeQuietly(stdErr);
		}
	}

	/**
	 * 执行脚本
	 * @param cmds 脚本命令
	 * @param sysUser 执行用户
	 * @param logUtils 日志工具
	 * @param realPath 项目绝对路径
	 * @param operation 操作
	 * @param fileName 日志文件名
	 * @return
	 */
	public String exec(String cmds, SysUser sysUser, LogUtils logUtils,String realPath,String operation,String fileName){
		InputStream stdOut = null;
		InputStream stdErr = null;
		String outStr = "";
		String outErr = "";
		int ret = -1;
		try {
			if (login()) {
				
				logUtils.writeLog(realPath, sysUser, "调用脚本,执行命令:"+cmds, operation, fileName);
				
				// Open a new {@link Session} on this connection
				Session session = conn.openSession();// 开启会话
				// Execute a command on the remote machine.
				session.execCommand(cmds);
				stdOut = new StreamGobbler(session.getStdout());
				outStr = processStream(stdOut, "UTF-8");
				stdErr = new StreamGobbler(session.getStderr());
				outErr = processStream(stdErr, "UTF-8");
				session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
				logUtils.writeLog(realPath, sysUser, "调用脚本,outStr= "+outStr, operation, fileName);
				logUtils.writeLog(realPath, sysUser, "调用脚本,outErr= "+outErr, operation, fileName);
				if (outErr!=null&& !outErr.trim().equals("")) {
					//throw new RoamProjectException("调用脚本抛出异常,outErr= "+outErr);
				}
				ret = session.getExitStatus();
				return outStr;
			} else {
				logUtils.writeLog(realPath, sysUser, "登录远程机器失败" + ip, operation, fileName);
				throw new RoamProjectException("登录远程机器失败" + ip); // 自定义异常类 
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RoamProjectException(e.getMessage()); // 自定义异常类 
		} finally {
			if (conn != null) {
				conn.close();
			}
			IOUtils.closeQuietly(stdOut);
			IOUtils.closeQuietly(stdErr);
		}
	}

	/**
	 * @param in
	 * @param charset
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private String processStream(InputStream in, String charset) throws Exception {
		byte[] buf = new byte[1024];
		StringBuilder sb = new StringBuilder();
		while (in.read(buf) != -1) {
			sb.append(new String(buf, charset));
		}
		return sb.toString();
	}

	public void deleFtp(String ftp_ip, String ftp_port) {

	}

	public static void main(String args[]) throws Exception {
		RemoteShellExecutor executor = new RemoteShellExecutor("192.168.56.19", "zftest", "zftest");
		// 执行myTest.sh 参数为java Know dummy
		// executor.exec(" rm -f /zftest/t.txt\r\n");
	}
}