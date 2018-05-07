package com.guoll.modules.roammanager.common;

import java.io.File;

public class Constants {
	
	public static final String Post = "xitongguanliyuan";
	/**
	 * 必选字段取值异常
	 */
	public static final String Required_Value_Error = "01";
	/**
	 * 可选字段取值异常
	 */
	public static final String Optional_Value_Error = "02";
	/**
	 * 消息包结构异常
	 */
	public static final String Format_Error = "03";
	/**
	 * 必选字段缺失
	 */
	public static final String Lost_Required = "04";
	/**
	 * 必不携带字段
	 */
	public static final String Canot_Carry = "05";
	/**
	 * 其他问题
	 */
	public static final String Others_Error = "06";
	
	/**
	 * MSCC code
	 */
	public static final String MSCC_AVP_CODE = "456";
	
	/**
	 * USU code
	 */
	public static final String USU = "446";
	
	/**
	 * GSU code
	 */
	public static final String GSU = "431";
	
	/**
	 * FUI code
	 */
	public static final String FUI = "430";
	
	/**
	 * SI code
	 */
	public static final String SI = "873";
	
	/**
	 * SI code
	 */
	public static final String PSI = "874";
	/**
	 * SI code
	 */
	public static final String FCI = "865";
	
	
	/**
	 * MSCC NAME
	 */
	public static final String MSCC_AVP_NAME = "MSCC";
	
	/**
	 * Trigger code
	 */
	public static final String Trigger_AVP_CODE = "1264";
	
	/**
	 * Trigger-Type code
	 */
	public static final String Trigger_Type_AVP_CODE = "870";
	
	/**
	 * Destination_Host code
	 */
	public static final String Destination_Host_AVP_CODE = "293";
	
	/**
	 * Origin-Host
	 */
	public static final String Origin_Host_AVP_CODE = "264";
	
	/**
	 * Result-Code
	 */
	public static final String Result_Code_AVP_CODE = "268";
	
	/**
	 * Tariff_change_Usage code
	 */
	public static final String Tariff_change_Usage_AVP_CODE = "452";
	
	/**
	 * Session-Id
	 */
	public static final String Session_Id_AVP_CODE = "263";
	
	/**
	 * CC-Request-Type
	 */
	public static final String CC_Request_Type_AVP_CODE = "416";
	
	/**
	 * Granted_Service_Unit_AVP_CODE
	 */
	public static final String Granted_Service_Unit_AVP_CODE = "431";
	
	/**
	 * INITIAL_REQUEST
	 */
	public static final String INITIAL_REQUEST = "1";
	
	/**
	 * UPDATE_REQUEST
	 */
	public static final String UPDATE_REQUEST = "2";
	
	/**
	 * TERMINATION_REQUEST
	 */
	public static final String TERMINATION_REQUEST = "3";
	
	/**
	 * 开始测试
	 */
	public static final String Has_test = "是";
	
	/**
	 * 没开始测试
	 */
	public static final String Not_test = "否";
	
	/**
	 * 测试通过
	 */
	public static final String Pass = "通过";
	
	/**
	 * 测试通过整数表示
	 */
	public static final Integer Integer_Pass = 1;
	
	/**
	 * 测试失败
	 */
	public static final String Fail = "不通过";
	
	/**
	 * 测试失败整数表示
	 */
	public static final Integer Integer_Fail = 0;
	
	/**
	 * 未测试
	 */
	public static final String Un_Test = "未测试";
	
	/**
	 * 第六类
	 */
	public static final String The_Six_Category_Pro= "第六类.可选字段缺失问题";
	
	/**
	 * 出现问题情况
	 */
	public static final String Pro_AVP_Code_Situation= "出现问题情况";
	
	/**
	 * 问题名称情况
	 */
	public static final String Pro_AVP_Name_Situation= "问题名称情况";
	
	/**
	 * 字段编码详情
	 */
	public static final String AVP_Code_Detail= "字段编码详情";
	
	/**
	 * 字段名称详情
	 */
	public static final String AVP_Name_Detail= "字段名称详情";
	
	/**
	 * 共性问题名称详情
	 */
	public static final String Commonality_Avp_Detail= "共性问题名称详情";
	
	/**
	 * 省份个数(字符串)
	 */
	public static final String Province_Count= "省份个数";
	
	/**
	 * 省份个数
	 */
	public static final int Province_Count_Integer= 31;
	
	/**
	 * 省份详情
	 */
	public static final String Province_Name_Detail= "省份详情";
	
	/**
	 * 找到消息
	 */
	public static final String Find_Dcc_Message= "√";
	
	/**
	 * 没找到消息
	 */
	public static final String Not_Find_Dcc_Message= "×";
	
	
	
	/**
	 * 实现
	 */
	public static final String Realize= "√";
	
	/**
	 * 没实现
	 */
	public static final String Not_Realize= "×";
	
	/**
	 * NONE
	 */
	public static final String NONE= "-";
	
	/**
	 * 信用控制消息命令码
	 */
	public static final String Credit_Control_Command = "272";
	
	/**
	 * 信用控制用例的长度
	 */
	public static final Integer Credit_Control_Uname_Length = 10;
	
	/**
	 * 心跳消息命令码
	 */
	public static final String Device_Watchdog_Command = "280";
	
	/**
	 * 心跳用例的长度
	 */
	public static final Integer Device_Watchdog_Uname_Length = 8;
	
	/**
	 * CCR-I
	 */
	public static final String CCR_I = "CCR-I";
	
	/**
	 * CCR-U
	 */
	public static final String CCR_U = "CCR-U";
	
	/**
	 * CCR-T
	 */
	public static final String CCR_T = "CCR-T";
	
	/**
	 * CCA-I
	 */
	public static final String CCA_I = "CCA-I";
	
	/**
	 * CCA-U
	 */
	public static final String CCA_U = "CCA-U";
	
	/**
	 * CCA-T
	 */
	public static final String CCA_T = "CCA-T";
	
	/**
	 * CCR
	 */
	public static final String CCR = "CCR";
	
	/**
	 * CCA
	 */
	public static final String CCA = "CCA";
	
	/**
	 * DWR
	 */
	public static final String DWR = "DWR";
	
	/**
	 * DWA
	 */
	public static final String DWA = "DWA";
	
	/**
	 * 用例名称数字编码的长度
	 */
	public static final Integer Digit_Num_Length = 4;
	
	/**
	 * 自动执行消息
	 */
	public static final String Auto_Exe_Value = "1";
	
	/**
	 * 不自动执行消息
	 */
	public static final String Not_Auto_Exe_value = "0";
	
	/**
	 * 放开下拉框选择
	 */
	public static final String Can_Match_Info_Value = "1";
	
	
	//------------------------三个特殊用例---------------------
	/**
	 * CCA-I-1001
	 */
	public static final String CCA_I_1001 = "CCA-I-1001";
	
	/**
	 * CCA-U-1001
	 */
	public static final String CCA_U_1001 = "CCA-U-1001";
	
	/**
	 * CCA-T-1001
	 */
	public static final String CCA_T_1001 = "CCA-T-1001";
	
	/**
	 * 没找到特殊用例
	 */
	public static final String Not_Find_User_case_1001 = "N";
	
	/**
	 * 超出结果码描述
	 */
	public static final String Over_Result_Code = "本省使用了超出规范范围的CCA错误结果码:";
	
	/**
	 * 用例实际使用
	 */
	public static final String In_Use_Flag = "1";
	
	/**
	 * 版本实际使用
	 */
	public static final String Use_Flag = "1";
	
	/**
	 * 删除
	 */
	public static final String Has_Delete = "1";
	/**
	 * 未删除
	 */
	public static final String Not_Delete = "0";
	
	/**
	 * 存放压缩包的路径
	 */
	public static final String Zip_File_Path = "C:" + File.separator +"ZipFile";
	
	/**
	 * txt格式
	 */
	public static final String TXT = ".txt";
	
	/**
	 * zip格式
	 */
	public static final String ZIP = ".zip";
	
	/**
	 *"/"字符forward slash
	 */
	public static final String Forward_Slash = "/";
	
	/**
	 *"/"字符||
	 */
	public static final String OR = "||";
	
	/**
	 * 自动匹配
	 */
	public static final String MATCH_INFO = "MATCH_INFO";
	
	/**
	 * 自动执行
	 */
	public static final String AUTO_EXE = "AUTO_EXE";
	
	/**
	 * 共性问题占比
	 */
	public static final String COM_PERCENT = "COM_PERCENT";
	
	/**
	 * 当前任务
	 */
	public static final String CURRENT_TASK = "CURRENT_TASK";
	
	/**
	 * CCR和CCA的配置文件
	 */
	public static final String CCR_AND_CCA_CONFIGXML = "dcc_avp_config_cc.xml";
	
	/**
	 * CCR和CCA的配置文件
	 */
	public static final String DWR_AND_DWA_CONFIGXML = "dcc_avp_config_dw.xml";
	
	/**
	 * MSCC里面是group属性的avpcode
	 */
	public static final String MSCC_Group_Code = "456,1264,446,1276,431,430,434";
	
	/**
	 * 基础用例
	 */
	public static final String Basic_UseCase = "CCR-I-0001,CCR-U-0001,CCR-T-0001,CCA-I-0001,CCA-U-0001,CCA-T-0001";
	
	/**
	 * 在线计费ASCII
	 */
	public static final String PS_Free_Format_Data_Online_ASCII = "3136";
	/**
	 * 在线计费十进制
	 */
	public static final String PS_Free_Format_Data_Online_Decimal = "16";
	/**
	 * 流量提醒ASCII
	 */
	public static final String PS_Free_Format_Data_Remind_ASCII = "3135";
	/**
	 * 流量提醒十进制
	 */
	public static final String PS_Free_Format_Data_Remind_Decimal = "15";
	/**
	 * 离线计费ASCII
	 */
	public static final String PS_Free_Format_Data_Outline_ASCII = "3131";
	/**
	 * 离线计费十进制
	 */
	public static final String PS_Free_Format_Data_Outline_Decimal = "11";
	
	public static final Integer PS_Free_Format_Data_Integer = 866;
	
	public static final String PS_Free_Format_Data_Code = "866";
	
	/**
	 * AVP字段的OctetString类型
	 */
	public static final String AVP_Type_OctetString = "OctetString";
	/**
	 * AVP字段的Integer32类型
	 */
	public static final String AVP_Type_Integer32 = "Integer32";
	/**
	 * AVP字段的Integer64类型
	 */
	public static final String AVP_Type_Integer64 = "Integer64";
	/**
	 * AVP字段的Unsigned32类型
	 */
	public static final String AVP_Type_Unsigned32 = "Unsigned32";
	/**
	 * AVP字段的Unsigned64类型
	 */
	public static final String AVP_Type_Unsigned64 = "Unsigned64";
	/**
	 * AVP字段的Float32类型
	 */
	public static final String AVP_Type_Float32 = "Float32";
	/**
	 * AVP字段的Float64类型
	 */
	public static final String AVP_Type_Float64 = "Float64";
	/**
	 * AVP字段的IPAddress类型
	 */
	public static final String AVP_Type_IPAddress = "IPAddress";
	/**
	 * AVP字段的Time类型
	 */
	public static final String AVP_Type_Time = "Time";
	/**
	 * AVP字段的UTF8String类型
	 */
	public static final String AVP_Type_UTF8String = "UTF8String";
	/**
	 * AVP字段的DiameterIdentity类型
	 */
	public static final String AVP_Type_DiameterIdentity = "DiameterIdentity";
	/**
	 * AVP字段的Enumerated类型
	 */
	public static final String AVP_Type_Enumerated = "Enumerated";
	/**
	 * 网元标识
	 */
	public static final String NF_Flag = "NF";
	/**
	 * BOSS标识
	 */
	public static final String BOSS_Flag = "BOSS";
	
	/**
	 * 厂商类型_网元
	 */
	public static final String Type_NF = "网元";
	/**
	 * 厂商类型_BOSS
	 */
	public static final String Type_Boss = "BOSS";
	
	/**
	 * 陕西中文名称
	 */
	public static final String SHAANXI_NAME = "陕西";
	/**
	 * 陕西拼音
	 */
	public static final String SHAANXI_PINYIN = "shaanxi";
	
	/**
	 * 山西中文名称
	 */
	public static final String SHANXI_NAME = "山西";
	/**
	 * 山西拼音
	 */
	public static final String SHANXI_PINYIN = "shanxi";
	
	/**
	 * 格式异常-真正格式异常
	 */
	public static final Integer Format_Real_Error = 0;
	/**
	 * 格式异常-取值异常
	 */
	public static final Integer Format_Value_Error = 1;
	/**
	 * 格式异常-必不携带
	 */
	public static final Integer Format_Carry_Error = 2;
	
	/**
	 * 非补充测试
	 */
	public static final String Not_Supply = "0";
	
	/**
	 * 非补充测试
	 */
	public static final String Not_Supply_Str = "否";
	/**
	 * 补充测试
	 */
	public static final String Is_Supply = "1";
	/**
	 * 补充测试
	 */
	public static final String Is_Supply_Str = "是";
	
}
