package com.guoll.modules.framework.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.guoll.modules.framework.util.SessionUtils;
import com.guoll.modules.sysmanage.bean.SysUser;
import com.thoughtworks.xstream.alias.ClassMapper.Null;

import util.log.LogUtils;

public class BaseController{
	
	public final static String SUCCESS ="success";  
	public final static String MSG ="msg";  
	public final static String DATA ="data";  
	public final static String LOGOUT_FLAG = "logoutFlag";  
	public LogUtils logUtils = null;//日志工具
	/**
	 * ftp服务器的协议
	 */
   @InitBinder  
   protected void initBinder(WebDataBinder binder) {  
		 binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));  
		 logUtils = new LogUtils();
   }
   /**
	 * 
	 * @description 打印ruquest值栈
	 * 
	 * @return null
	 * 
	 * @author guoll
	 * 
	 * @date 2011-06-25
	 */
	public void printRequestParameter(HttpServletRequest request){
		
		/*Map<String,Object> map = request.getParameterMap();
		Set<String> mapSet = map.keySet();
		for(String key:mapSet){
			System.out.println("key-->:"+key+",value-->:"+request.getParameter(key));
		}*/
	}
	/**
	 * 获取当前登录者
	 * @param request
	 * @return
	 */
	public SysUser getSessionUser(HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		return sysUser;
	}
	
	
	public void provinceIDInit(HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		request.setAttribute("provinceID", sysUser.getPost_id());
		request.setAttribute("provinceName", sysUser.getPost_name());
	}
	
	public String getProvinceName(HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		return sysUser.getPost_name();
	}
	
	public  String createProNumber(HttpServletRequest request){
		SysUser sysUser = (SysUser)SessionUtils.getAttr(request, SessionUtils.SESSION_USER);
		String proNumber=sysUser.getPost_remark()+util.DateUtil.getyyMMdd();
		return proNumber;
	}
}