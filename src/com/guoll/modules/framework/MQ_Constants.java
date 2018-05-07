package com.guoll.modules.framework;

public class MQ_Constants {

	public final static int MAX_UPLOAD_CONNECT_NUM = 1;  //最大连接数
	
	//上传服务器1
	public final static String UPLOAD_MQMGR_NAME_1 = "CUST";
	public final static String UPLOAD_MQ_NAME_1 = "CUST_TEAM1";
	public final static String UPLOAD_HOSTNAME_1 = "127.0.0.1";
//	public final static String UPLOAD_HOSTNAME_1 = "192.168.0.103";

	public final static int UPLOAD_HOSTPORT_1 = 1414;
	public final static String UPLOAD_HOSTCHANNEL_1 = "SYSTEM.DEF.SVRCONN";
	

	//下载服务器1
	public final static String DOWNLOAD_MQMGR_NAME_1 = "CUST";
	public final static String DOWNLOAD_MQ_NAME_1 = "COMP_TEAM1";
	public final static String DOWNLOAD_HOSTNAME_1 = "127.0.0.1";
//	public final static String DOWNLOAD_HOSTNAME_1 = "192.168.0.103";
	public final static int DOWNLOAD_HOSTPORT_1 = 1414;
	public final static String DOWNLOAD_HOSTCHANNEL_1 = "SYSTEM.DEF.SVRCONN";
	
}
