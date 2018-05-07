package com.guoll.modules.useCase.controller;

import java.io.File;

public class SysUtils {
	/**
	 * 获取当前系统的分隔符
	 * 如果是Linux,返回File获取的
	 * 否则返回"/"
	 * @return
	 */
	public static String getFileSeparator(){
		String os = System.getProperties().getProperty("os.name");
		if (os != null && os.toLowerCase().indexOf("linux") > -1) {
			return"/";// File.separator;
		} else {
			return "/";
		}
	}
}
