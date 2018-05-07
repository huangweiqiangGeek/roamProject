package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NetStatusTest {
	public static boolean isConnect(String host) {
		boolean connect = false;
		Runtime runtime = Runtime.getRuntime();
		Process process;
		try {
			// 第一步,判断所在的操作系统,进行命令拼接,之所以这么做,是因为Linux系统ping时如果不给控制次数的参数会默认一直ping下去
			String osName = System.getProperty("os.name");// 获取操作系统类型
			String command = "";
			if (osName.toLowerCase().contains("linux")) {// linux
				command = "ping -c 3 -i 1 " + host;
			} else {// windows
				command = "ping " + host;
			}

			// 第二步,调用命令获取输出
			process = runtime.exec(command);

			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			isr.close();
			br.close();
			String str = sb.toString().toLowerCase();
			// 第三步,分析结果
			if (str != null && !str.toString().equals("")) {
				if (str.toString().indexOf("ttl") > 0) { // 网络畅通
					connect = true;
				} else { // 网络未连通
					connect = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connect;
	}

	public static void main(String[] args) {
		NetStatusTest netState = new NetStatusTest();
		// System.out.println(netState.isConnect());
	}
}
