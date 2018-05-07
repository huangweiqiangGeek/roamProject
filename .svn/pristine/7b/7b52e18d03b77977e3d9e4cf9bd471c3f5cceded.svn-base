package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;
import sun.net.ftp.FtpReplyCode;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * 文件操作工具类
 * 
 * @author wangb
 *
 */
public class FtpUtils {

	private static Map<String, FtpClient> mapOfFtpClient = null;

	/**
	 * ftp登录
	 * 
	 * @param url
	 * @param port
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception 
	 */
	public static FtpClient connectFTP(String url, int port, String username, String password) throws Exception {
		if (mapOfFtpClient == null) {
			mapOfFtpClient = new HashMap<String, FtpClient>();
		}
		if (mapOfFtpClient.containsKey(url + port + username + password)) {
			return mapOfFtpClient.get(url + port + username + password);
		}
		// 创建ftp
		FtpClient ftp = null;
		try {
			// 创建地址
			SocketAddress addr = new InetSocketAddress(url, port);
			// 连接
			ftp = FtpClient.create();
			ftp.setConnectTimeout(100000);
			ftp.connect(addr);
			// 登陆
			ftp.login(username, password.toCharArray());
			ftp.setBinaryType();
		} catch (FtpProtocolException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		mapOfFtpClient.put(url + port + username + password, ftp);
		return ftp;
	}

	/**
	 * 上传
	 * 
	 * @param localFile
	 *            : 本地目标
	 * @param ftpFile
	 *            : 远程目标
	 * @param url
	 *            : ftp ip
	 * @param port
	 *            : ftp端口
	 * @param username
	 *            : ftp用户
	 * @param password
	 *            : ftp用户密码
	 * @throws Exception 
	 */
	public static void upload(String localFile, String ftpFile, String url, int port, String username, String password,
			String ftps) throws Exception {
		try {

			if (ftps.equals("ftp")) {
				FtpClient ftp = connectFTP(url, port, username, password);
				OutputStream os = null;
				FileInputStream fis = null;
				try {
					// 将ftp文件加入输出流中。输出到ftp上
					os = ftp.putFileStream(ftpFile);
					File file = new File(localFile);

					// 创建一个缓冲区
					fis = new FileInputStream(file);
					byte[] bytes = new byte[1024];
					int c;
					while ((c = fis.read(bytes)) != -1) {
						os.write(bytes, 0, c);
					}
					System.out.println("upload success!!");
				} catch (FtpProtocolException e) {
					e.printStackTrace();
					throw e;
				} catch (IOException e) {
					e.printStackTrace();
					throw e;
				} finally {
					try {
						if (os != null) {
							os.close();
						}
						if (fis != null) {
							fis.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
						throw e;
					}
				}

			} else if (ftps.equals("sftp")) {
				Session session = null;
				Channel channel = null;
				JSch jsch = new JSch();
				//System.out.println("111111111111111111");
				if (port <= 0) {
					// 连接服务器，采用默认端口
					session = jsch.getSession(username, url);
				} else {
					// 采用指定的端口连接服务器
					session = jsch.getSession(username, url, port);
				}
				//System.out.println("222222222222222222");
				// 如果服务器连接不上，则抛出异常
				if (session == null) {
					throw new Exception("session is null");
				}
				//System.out.println("33333333333333333");
				// 设置登陆主机的密码
				session.setPassword(password);// 设置密码
				// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
				// session.setConfig("StrictHostKeyChecking", "no");
				java.util.Properties properties = new Properties();
				//properties.setProperty("kex", "");
				
				//properties.put("kex", "diffie-hellman-group-exchange-sha1,diffie-hellman-group14-sha1,diffie-hellman-group1-sha1");
				//config.put("kex", "diffie-hellman-group-exchange-sha256"); //tried this individually also 
				//config.put("kex", "diffie-hellman-group1-sha1"); //tried this individually also
				session.setConfig("kex", "diffie-hellman-group1-sha1");
				properties.put("StrictHostKeyChecking", "no");
				//System.out.println("4444444444444444444444");
				session.setConfig(properties);
				//System.out.println("555555555555555555555");
				// 设置登陆超时时间
				session.connect(30000);
				//System.out.println("66666666666666666666");
				try {
					// 创建sftp通信通道
					channel = (Channel) session.openChannel("sftp");
					//System.out.println("777777777777777777777");
					channel.connect(1000);
					//System.out.println("888888888888888888888");
					ChannelSftp sftp = (ChannelSftp) channel;
					//System.out.println("999999999999999999999");
					// 以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了
					InputStream instream = new FileInputStream(new File(localFile));
					//System.out.println("10101010101011011010101");
					OutputStream outstream = sftp.put(ftpFile);
					//System.out.println("121212121212121212121212");
					byte b[] = new byte[1024];
					int n;
					while ((n = instream.read(b)) != -1) {
						outstream.write(b, 0, n);
					}
					//System.out.println("13131313131313131313131313");
					outstream.flush();
					outstream.close();
					instream.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					session.disconnect();
					channel.disconnect();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 下载
	 * 
	 * @param localFile
	 *            : 本地目标
	 * @param ftpFile
	 *            : 远程目标
	 * @param url
	 *            : ftp ip
	 * @param port
	 *            : ftp端口
	 * @param username
	 *            : ftp用户
	 * @param password
	 *            : ftp用户密码
	 * @throws Exception
	 */
	public static void download(String localFile, String ftpFile, String url, int port, String username,
			String password, String ftps) throws Exception {

		try {
			if (ftps.equals("ftp")) {

				InputStream is = null;
				FileOutputStream fos = null;
				FtpClient ftp = connectFTP(url, port, username, password);
				try {
					// 以下为判断文件是否存在
					/*long st = System.currentTimeMillis();
					while (true) {
						if (isFTPFileExist(ftp, ftpFile)) {
							break;
						} else {
							if (System.currentTimeMillis() > (st + 5000)) {
								throw new RoamProjectException("待下载的文件不存在,文件:" + ftpFile);
							} else {
								Thread.sleep(500);
							}
						}
					}*/

					// 以下为下载
					File file0 = new File("txts");
					if (!file0.exists()) {
						file0.mkdir();
					}
					// 获取ftp上的文件
					is = ftp.getFileStream(ftpFile);
					File file = new File(localFile);
					if (!file.exists()) {
						file.createNewFile();
					}
					byte[] bytes = new byte[1024];
					int i;
					fos = new FileOutputStream(file);
					while ((i = is.read(bytes)) != -1) {
						fos.write(bytes, 0, i);
					}
					System.out.println("download success!!");

				} catch (FtpProtocolException e) {
					e.printStackTrace();
					throw e;
				} catch (IOException e) {
					e.printStackTrace();
					throw e;
				} finally {
					try {
						if (fos != null) {
							fos.close();
						}
						if (is != null) {
							is.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
						throw e;
					}
				}
			} else if (ftps.equals("sftp")) {
				File file0 = new File("txts");
				if (!file0.exists()) {
					file0.mkdir();
				}
				Session session = null;
				Channel channel = null;
				JSch jsch = new JSch();
				session = jsch.getSession(username, url, port);
				session.setPassword(password);
				session.setTimeout(100000);
				Properties config = new Properties();
				config.put("StrictHostKeyChecking", "no");
				session.setConfig("kex", "diffie-hellman-group1-sha1");
				session.setConfig(config);
				session.connect();
				channel = session.openChannel("sftp");
				channel.connect();
				ChannelSftp chSftp = (ChannelSftp) channel;

				try {
					chSftp.get(ftpFile, localFile);
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					chSftp.quit();
					channel.disconnect();
					session.disconnect();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * <删除FTP上的文件>
	 * 
	 * @param url
	 *            FTP服务器IP地址
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP服务器登录名
	 * @param password
	 *            FTP服务器密码
	 * @param remotePath
	 *            远程文件路径
	 * @param fileName
	 *            待删除的文件名
	 * @return
	 * @throws Exception
	 */
	public static boolean deleteFtpFile(String url, int port, String username, String password, String remotePath,
			String fileName, String ftps) throws Exception {
		try {
			if (ftps.equals("ftp")) {

				FtpClient ftp = connectFTP(url, port, username, password);
				ftp.deleteFile(remotePath + "/" + fileName);
				return true;

			} else if (ftps.equals("sftp")) {
				JSch jsch = new JSch();
				Session sshSession = jsch.getSession(username, url, port);
				ChannelSftp sftp = null;
				sshSession.setPassword(password);
				Properties sshConfig = new Properties();
				sshConfig.put("StrictHostKeyChecking", "no");
				sshSession.setConfig(sshConfig);
				sshSession.connect(20000);
				Channel channel = sshSession.openChannel("sftp");
				channel.connect();
				sftp = (ChannelSftp) channel;

				sftp.cd(remotePath);
				sftp.rm(fileName);

				return true;
			}
			return false;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 检验指定路径的文件是否存在ftp服务器中
	 * 
	 * @param filePath--指定绝对路径的文件
	 * @param user--ftp服务器登陆用户名
	 * @param passward--ftp服务器登陆密码
	 * @param ip--ftp的IP地址
	 * @param port--ftp的端口号
	 * @return
	 */

	public static boolean isFTPFileExist(FtpClient ftp, String filePath) {
		try {

			// 提取绝对地址的目录以及文件名
			// filePath = filePath.replace("ftp://" + ip + ":" + port + "/",
			// "");
			String dir = filePath.substring(0, filePath.lastIndexOf("/"));
			String file = filePath.substring(filePath.lastIndexOf("/") + 1);

			// 进入文件所在目录，注意编码格式，以能够正确识别中文目录
			ftp.changeDirectory(new String(dir.getBytes("GBK")));

			// 检验文件是否存在
			InputStream is = ftp.getFileStream(new String(file.getBytes("UTF-8")));
			if (is == null || ftp.getLastReplyCode() == FtpReplyCode.FILE_UNAVAILABLE) {
				return false;
			}

			if (is != null) {
				is.close();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
