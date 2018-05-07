package com.guoll.modules.roammanager.common;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonClass {

	public CommonClass(){

	}
	/**
	 * 
	 * 
	 * @param path
	 * @return
	 */
	public static List<File> getFileSort(String path) {
		List<File> list = getFiles(path);
		if (list != null && list.size() > 0) {
			Collections.sort(list, new Comparator<File>() {
				public int compare(File file, File newFile) {
					if (file.lastModified() < newFile.lastModified()) {
						return 1;
					} else if (file.lastModified() == newFile.lastModified()) {
						return 0;
					} else {
						return -1;
					}
				}
			});
		}
		return list;
	}
	public static List<File> getFiles(String realpath) {

		File realFile = new File(realpath);
		return Arrays.asList(realFile.listFiles());
	}
	public static File getFile(String realpath) {

		File realFile = new File(realpath);
		return realFile;
	}
	/***
	 * 
	 */
	public static String getChineseString(String chineseString){
		try{
			if(chineseString.equals(new String(chineseString.getBytes("GB2312"), "GB2312"))){  
				chineseString = new String(chineseString.getBytes("GB2312"),"utf-8");   
			}else  if(chineseString.equals(new String(chineseString.getBytes("iso-8859-1"), "iso-8859-1"))){  
				chineseString = new String(chineseString.getBytes("iso-8859-1"),"utf-8");                 
			}else if(chineseString.equals(new String(chineseString.getBytes("UTF-8"), "UTF-8"))){  
				chineseString = new String(chineseString.getBytes("UTF-8"),"utf-8");   
			}else if(chineseString.equals(new String(chineseString.getBytes("GBK"), "GBK"))){  
				chineseString = new String(chineseString.getBytes("GBK"),"utf-8");   
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return chineseString;
	}

	public static String getResultString(Integer result){
		if(null == result)
			return Constants.Un_Test;
		else if(result == 0)
			return Constants.Fail;
		else
			return Constants.Pass;
	}
	public static String getSupplyString(String result){
		if(null == result)
			return Constants.Not_Supply_Str;
		else if(result.equals(Constants.Not_Supply))
			return Constants.Not_Supply_Str;
		else
			return Constants.Is_Supply_Str;
	}

	public static String getUName(String fileName){
		if(fileName.contains(Constants.CCR_I)){
			return getUname(fileName, Constants.CCR_I, Constants.Credit_Control_Uname_Length);
		}
		else if(fileName.contains(Constants.CCR_U)){
			return getUname(fileName, Constants.CCR_U, Constants.Credit_Control_Uname_Length);
		}
		else if(fileName.contains(Constants.CCR_T)){
			return getUname(fileName, Constants.CCR_T, Constants.Credit_Control_Uname_Length);
		}
		else if(fileName.contains(Constants.CCA_I)){
			return getUname(fileName, Constants.CCA_I, Constants.Credit_Control_Uname_Length);
		}
		else if(fileName.contains(Constants.CCA_U)){
			return getUname(fileName, Constants.CCA_U, Constants.Credit_Control_Uname_Length);
		}
		else if(fileName.contains(Constants.CCA_T)){
			return getUname(fileName, Constants.CCA_T, Constants.Credit_Control_Uname_Length);
		}
		else if(fileName.contains(Constants.DWR)){
			return getUname(fileName, Constants.DWR, Constants.Device_Watchdog_Uname_Length);
		}
		else if(fileName.contains(Constants.DWA)){
			return getUname(fileName, Constants.DWA, Constants.Device_Watchdog_Uname_Length);
		}
		return "";
	}
	private static String getUname(String fileName, String Messagetype, int unameLength){
		int index = fileName.indexOf(Messagetype);
		String uname = fileName.substring(index, index + unameLength);//用例名称
		String digit_Num = fileName.substring(index + unameLength -Constants.Digit_Num_Length , index + unameLength);//数字编码
		if(digit_Num.matches("[0-9]+")){
			return uname;
		}
		return "";
	}
	public static InetAddress getInetAddress(){
		try{  
			return InetAddress.getLocalHost();  
		}catch(UnknownHostException e){  
			System.out.println("unknown host!");  
		}  
		return null;  

	}
	public static String getHostIp(InetAddress netAddress){  
		if(null == netAddress){  
			return null;  
		}  
		String ip = netAddress.getHostAddress(); //get the ip address  
		return ip;  
	}

	/**
	 * 获取业务流程用例中的网元侧用例
	 * @param name
	 * @return
	 */
	public static List<String> getNEUsecase(String name){
		if(name.indexOf(Constants.CCR) == -1 && name.indexOf(Constants.DWR) == -1)//不包含网元侧用例
			return null;
		if(name.indexOf(Constants.CCA) != -1 || name.indexOf(Constants.DWA) != -1){//包含BOSS侧用例
			String[] names = name.split(",");
			List<String> ls = new ArrayList<String>();
			for(int i = 0; i < names.length; i++){
				String _name2 = names[i];
				if(_name2.indexOf(Constants.CCA) == -1 && _name2.indexOf(Constants.DWA) == -1)//该用例是网元侧
					ls.add(_name2);
			}
			return ls;
		}
		else
			return getAllUseCaseBusiness(name);
	}
	/**
	 * 获取业务流程用例中的BOSS侧用例
	 * @param name
	 * @return
	 */
	public static List<String> getBOSSUsecase(String name){
		if(name.indexOf(Constants.CCA) == -1 && name.indexOf(Constants.DWA) == -1)//不包含BOSS侧用例
			return null;
		if(name.indexOf(Constants.CCR) != -1 || name.indexOf(Constants.DWR) != -1){//包含网元侧用例
			String[] names = name.split(",");
			List<String> ls = new ArrayList<String>();
			for(int i = 0; i < names.length; i++){
				String _name2 = names[i];
				if(_name2.indexOf(Constants.CCR) == -1 && _name2.indexOf(Constants.DWR) == -1)//该用例是BOSS侧
					ls.add(_name2);
			}
			return ls;
		}
		else
			return getAllUseCaseBusiness(name);
	}
	/**
	 * 处理业务流程的用例，加上单引号
	 * @param name
	 * @return
	 */
	public static List<String> getAllUseCaseBusiness(String name){
		String[] names = name.split(",");
		return Arrays.asList(names);
	}
	private static boolean isChinese(char c) {  
	    Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
	    if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
	        || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
	        || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
	        || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
	        || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
	        || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
	      return true;  
	    }  
	    return false;  
	  } 
	/**
	 * 判断是否为乱码
	 * @param strName
	 * @return
	 */
	public static boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*"); 
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {
				if (!isChinese(c)) {
					count = count + 1;
				}
			}
		}
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		} else {
			return false;
		}
	}
}
