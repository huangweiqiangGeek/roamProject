package util;

import java.util.HashMap;
import java.util.Map;


/**
 * 相关执行shell工具类管理类
 * 
 * @author wangb
 *
 */
public class ExecutorOfShell2 {
	Map<String, ExecutorOfShell> map;//ExecutorOfShell的管理容器,类似线程池
	
	public ExecutorOfShell2() {
		map = new HashMap<String, ExecutorOfShell>();
	}


	public ExecutorOfShell getExecutorOfShell(String ip, String usr, String pasword) {
		if (map.containsKey(ip + usr + pasword)) {
			return map.get(ip + usr + pasword);
		} else {
			ExecutorOfShell executorOfShell = new ExecutorOfShell(ip, usr, pasword);
			map.put(ip + usr + pasword, executorOfShell);
			return executorOfShell;
		}
	}
}
