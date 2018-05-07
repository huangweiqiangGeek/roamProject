package com.guoll.modules.sysmanage.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guoll.modules.billtemplate.service.BillTemplateService;
import com.guoll.modules.commConfig.service.CommConfigService;
import com.guoll.modules.framework.Constants;
import com.guoll.modules.framework.util.Auth;
import com.guoll.modules.framework.util.SessionUtils;
import com.guoll.modules.resultRecord.service.ResultRecordService;
import com.guoll.modules.sysmanage.bean.SysPost;
import com.guoll.modules.sysmanage.bean.SysProvince;
import com.guoll.modules.sysmanage.bean.SysUser;
import com.guoll.modules.sysmanage.service.PassagewayService;
import com.guoll.modules.sysmanage.service.SysPostService;
import com.guoll.modules.sysmanage.service.SysProvinceService;
import com.guoll.modules.useCase.controller.UseCaseExecutorController;
import com.guoll.modules.useCase.service.UseCaseService;

import util.CommConfigUtils;
import util.CommConfigUtils.CommConfigKeys;

@Controller
@RequestMapping("/passageway")
public class PassagewayController {

	@Autowired(required=false)
	PassagewayService passagewayService;
	@Autowired(required=false)
	SysProvinceService sysProvinceService;
	@Autowired(required=false)
	CommConfigService commConfigService;
	@Autowired(required=false)
	UseCaseService useCaseService;
	@Autowired(required=false)
	ResultRecordService resultRecordService;
	
	@Autowired(required=false)
	SysPostService sysPostService;
	
	@Autowired(required=false)
	BillTemplateService billTemplateService;
	/**
	 * 系统入口
	 * @return
	 */
	@Auth(verifyLogin=false)
    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        return "login";
    }
	
	/**
	 * 登陆方法
	 * 
	 * @return
	 * @throws SQLException 
	 * @throws UnsupportedEncodingException 
	 */
	@Auth(verifyLogin = false)
	@RequestMapping("/land")
	public String land(SysUser sysUser, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
		if (StringUtils.isEmpty(sysUser.getUser_code())
				|| StringUtils.isEmpty(sysUser.getUser_pass())) {
			return "login";
		}

//	    String IdfCode = request.getParameter("identifyingCode");                              // 用户输入的验证码
//		String randomCode = (String) request.getSession().getAttribute("randCheckCode");       //随机生成的验证码
//		if (IdfCode == null || IdfCode.equals("")) {                            // 验证码非空判断
//			request.setAttribute("nullIdfCode", "2");
//			return "login";
//		}
//		if (!IdfCode.equalsIgnoreCase(randomCode)) {                            // 验证码输入是否一致判断
//			request.setAttribute("isRight", "3");
//			return "login";
//		}
		
		sysUser.setUser_pass(request.getParameter("user_pass"));
	    SysUser sysUser_query = passagewayService.queryLogin(sysUser);
	    
		if(sysUser_query==null){
	        request.setAttribute("loginError","1");
	        request.setAttribute("user_name",sysUser.getUser_code());
	        return "login";
	    }
		
		SysPost sysPost = new SysPost();
	    sysPost.setUser_id(sysUser_query.getId());
	    sysPost = (sysPostService.queryUserSysPostList(sysPost)).get(0);
	    sysUser_query.setPost_id(sysPost.getId());//省份标识
	    sysUser_query.setPost_name(sysPost.getName());//省份名称
	    sysUser_query.setPost_remark(sysPost.getRemark());//省份编码
	    SysProvince sysProvince = sysProvinceService.findSysProvinceBySpell(sysPost.getProvinceSpell());
	    sysUser_query.setSysProvince(sysProvince);
	    sysUser_query.setProvinceSpell(sysProvince.getProvinceSpell());
		SessionUtils.setAttr(request, SessionUtils.SESSION_USER, sysUser_query);
		
		//为了执行，校验用例执行公里中的几个服务层对象是否存在
		ServletContext servletContext = request.getServletContext();
		UseCaseExecutorController.testStatic(commConfigService, useCaseService, servletContext, resultRecordService);
		//获取一下本省配置到内存
		CommConfigUtils.getCommConfigByProvinceWithEnumOfCommConfigKeys(request, CommConfigKeys.ftp_ftptype, commConfigService);
		return "main";
		
		
	}
	
	
	/**
	 * 退出/注销
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		SessionUtils.removeUser(request);
		return "login";
	}
	/**
	 * session失效中转
	 * @return
	 */
	@Auth(verifyLogin=false)
	@RequestMapping("/logoutForTimeOutOrRemoveAccess")
	public String logoutForTimeOutOrRemoveAccess(HttpServletRequest request){
		SessionUtils.removeUser(request);
		return "logout";
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/leftSysMenu")
	public String leftSysMenu(HttpServletRequest request){
		return "layout/leftSysMenu_"+Constants.menu_tree_type_id;
	}
}