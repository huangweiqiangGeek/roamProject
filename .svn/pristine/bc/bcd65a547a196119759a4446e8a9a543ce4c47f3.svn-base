package com.guoll.modules.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class MyXMLReader {

	public static String getXMLRoot(File file) {
		try {
			
			Document doc = parse2Document(file);
			Element root = doc.getRootElement();
			return root.getName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public static Document parse2Document(File file){
		SAXReader reader = new SAXReader();
		reader.setEncoding("UTF-8");
		Document document = null;
		try {
			InputStream in = new FileInputStream(file);
			InputStreamReader strInStream = new InputStreamReader(in, "UTF-8");
			document = reader.read(strInStream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out
					.println("读取classpath下xmlFileName文件发生异常，请检查CLASSPATH和文件名是否存在！");
			e.printStackTrace();
		}
		return document;
	}

}
