package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.guoll.modules.commConfig.bean.CommConfig;
import com.guoll.modules.commConfig.service.CommConfigService;
import com.guoll.modules.framework.util.SessionUtils;
import com.guoll.modules.sysmanage.bean.SysUser;

public class CommConfigUtils {
	

	/**
	 * 获取本省配置指定的配置
	 * @param HttpServletRequest request 请求
	 * @param CommConfigKeys key CommConfigUtis的静态内部枚举,每一个值表示一种用途标志,值的格式:shell脚本配置: "shell_"+用途,文件"file_"+用途
	 * @return
	 */
	public static CommConfig getCommConfigByProvinceWithEnumOfCommConfigKeys(HttpServletRequest request ,CommConfigKeys key,CommConfigService service){
		Map<String, CommConfig> provinceConfig = getProvinceConfig(request, service);//获取本省配置集合
		CommConfig commConfig =provinceConfig.get(key.toString());
		//path_uploadTxtBillForShell
		return commConfig;//返回本省指定配置
		
	}
	/**
	 * 获取本省配置指定的配置
	 * @param ServletContext servletContext 应用域
	 * @param CommConfigKeys key CommConfigUtis的静态内部枚举,每一个值表示一种用途标志,值的格式:shell脚本配置: "shell_"+用途,文件"file_"+用途
	 * @param SysUser sysUser 用户
	 * @return
	 */
	public static CommConfig getCommConfigByProvinceWithEnumOfCommConfigKeys(ServletContext servletContext ,CommConfigKeys key,CommConfigService service,SysUser sysUser){
		Map<String, CommConfig> provinceConfig = getProvinceConfig(servletContext, service, sysUser);//获取本省配置集合
		CommConfig commConfig =provinceConfig.get(key.toString());
		//path_uploadTxtBillForShell
		return commConfig;//返回本省指定配置
		
	}
	public static void main(String[] args) {
		//System.out.println(CommConfigKeys.path_uploadTxtBillForShell);
	}
	/**
	 * 获取本省的指定配置时的key,为CommConfigKeys的用途标志
	 * @author wangb
	 *
	 */
	public static enum CommConfigKeys {
		
		/**
		 * 脚本
		 */
		/**
		 * 初始化免费资源,设置免费资源时调用的脚本
		 */
		shell_setResourceOfUserInput,
		/**
		 * 初始化免费资源,获取免费资源时调用的脚本
		 */
		shell_queryResourceByShell,
		/**
		 * 执行用例,搬移标准话单时调用的脚本
		 */
		shell_moveBill,
		/**
		 * 构造话单,生成标准话单时调用的脚本
		 */
		shell_toStandardBill,
		/**
		 * 执行后,获取免费资源执行结果时调用的脚本
		 */
		shell_getResourceResult,
		/**
		 * 自动新增产品组
		 */
		shell_getAutoProductGroup,
		
		
		/**
		 * 路径
		 */
		/**
		 * 执行用例后,获取详单结果调用的脚本所在的路径
		 */
		path_ShellOfDetialResult,
		/**
		 * 执行用例,上传txt话单的路径
		 */
		path_uploadTxtBillForShell,
		
		
		
		/**
		 * 文件
		 */
		/**
		 * 生成标准话单后,存放标准话单文件名的文件
		 */
		txt_cdr_filename,
		/**
		 * 执行后,获取免费资源执行结果的文件
		 */
		txt_getResourceResult,
		/**
		 * 获取语音详单计算结果的文件
		 */
		txt_getDetialResultOfGsmr,
		/**
		 * 获取gprs详单计算结果的文件
		 */
		txt_getDetialResultOfGprs,
		/**
		 * 获取短彩信详单计算结果的文件
		 */
		txt_getDetialResultOfMsg,
		/**
		 * 获取Wlan详单计算结果的文件
		 */
		txt_getDetialResultOfWlan,
		/**
		 * 自动新增产品组结果的文件
		 */
		txt_getAutoProductGroup,
		
		/**
		 * ftp服务器协议配置,请填写ftp或sftp
		 */
		ftp_ftptype
	}
	
	/**
	 * 更新本省配置集合
	 */
	@SuppressWarnings("unchecked")
	public static void updateProvinceConfig(HttpServletRequest request,CommConfig commConfig,CommConfigService service) {
		
		
		// 获取登录者信息
		SysUser user = (SysUser) SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		// 获取省份信息
		String postName = user.getPost_name();
		// 获取应用上下文
		ServletContext servletContext = request.getServletContext();
		// 获取应用上下文中的本省配置集合对象
		Object object = servletContext.getAttribute("commCfg" + postName);
		// 判断是否存在
		Map<String, CommConfig> map=null;
		if (object != null) {// 如果存在,转换为集合后返回
			map= (Map<String, CommConfig>) object;
		} else {// 去后台查询本省配置对象集合,查询后放入到集合应用上下文中
			CommConfig commConfig2 = new CommConfig();
			commConfig2.setProvinceName(postName);
			List<CommConfig> queryConfigList = service.queryAllConfigList(commConfig2);
			 map = new HashMap<String, CommConfig>();
			for(CommConfig c : queryConfigList) {
				if(c.getField3()!= null) {//如果有用途标志,说明是公共配置,存到集合中去
					map.put(c.getField3(), c);
				}
			}
		}
		map.put(commConfig.getField3(), commConfig);
		servletContext.setAttribute("commCfg" + postName, map);
	}
	/**
	 * 获取本省配置集合
	 */
	public static Map<String, CommConfig> getProvinceConfig(HttpServletRequest request,CommConfigService service) {
		
		// 获取登录者信息
		SysUser user = (SysUser) SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		// 获取省份信息
		String post_id = user.getPost_name();
		// 获取应用上下文
		ServletContext servletContext = request.getServletContext();
		// 获取应用上下文中的本省配置集合对象
		Object object = servletContext.getAttribute("commCfg" + post_id);
		// 判断是否存在
		if (object != null) {// 如果存在,转换为集合后返回
			return (Map<String, CommConfig>) object;
		} else {// 去后台查询本省配置对象集合,查询后放入到集合应用上下文中
			CommConfig commConfig = new CommConfig();
			commConfig.setProvinceName(post_id);
			List<CommConfig> queryConfigList = service.queryAllConfigList(commConfig);
			Map<String, CommConfig> map = new HashMap<String, CommConfig>();
			for(CommConfig c : queryConfigList) {
				if(c.getField3()!= null) {//如果有用途标志,说明是公共配置,存到集合中去
					map.put(c.getField3(), c);
				}
			}
			servletContext.setAttribute("commCfg" + post_id, map);
			return map;
		}
	}
	/**
	 * 获取本省配置集合
	 */
	@SuppressWarnings("unused")
	public static Map<String, CommConfig> getProvinceConfig(ServletContext servletContext,CommConfigService service,SysUser user) {
		
		// 获取省份信息
		String post_name = user.getPost_name();
		// 获取应用上下文中的本省配置集合对象
		Object object = servletContext.getAttribute("commCfg" + post_name);
		// 判断是否存在
		if (object != null) {// 如果存在,转换为集合后返回
			return (Map<String, CommConfig>) object;
		} else {// 去后台查询本省配置对象集合,查询后放入到集合应用上下文中
			CommConfig commConfig = new CommConfig();
			commConfig.setProvinceName(post_name);
			List<CommConfig> queryConfigList = service.queryAllConfigList(commConfig);
			Map<String, CommConfig> map = new HashMap<String, CommConfig>();
			for(CommConfig c : queryConfigList) {
				if(c.getField3()!= null) {//如果有用途标志,说明是公共配置,存到集合中去
					map.put(c.getField3(), c);
				}
			}
			servletContext.setAttribute("commCfg" + post_name, map);
			return map;
		}
	}

}
