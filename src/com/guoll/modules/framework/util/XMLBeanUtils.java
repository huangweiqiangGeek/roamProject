package com.guoll.modules.framework.util;

import com.thoughtworks.xstream.XStream; 
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map; 
import java.util.Iterator; 

/** 
* XML与JavaBean相互转换工具类 
* File: XMLBeanUtils.java 
* User: leizhimin 
* Date: 2008-3-5 14:28:29 
*/ 
public final class XMLBeanUtils { 
    /** 
     * 将Bean转换为XML 
     * 
     * @param clazzMap 别名-类名映射Map 
     * @param bean     要转换为xml的bean对象 
     * @return XML字符串 
     */ 
    public static String bean2xml(Map<String, Class> clazzMap, Object bean) { 
        XStream xstream = new XStream(); 
        for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext();) { 
            Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next(); 
            xstream.alias(m.getKey(), m.getValue()); 
        } 
        String xml = xstream.toXML(bean); 
        return xml; 
    } 

    /** 
     * 将XML转换为Bean 
     * 
     * @param clazzMap 别名-类名映射Map 
     * @param xml      要转换为bean对象的xml字符串 
     * @return Java Bean对象 
     */ 
    public static Object xml2Bean(Map<String, Class> clazzMap, String xml) { 
        XStream xstream = new XStream(); 
        for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext();) { 
            Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next(); 
            xstream.alias(m.getKey(), m.getValue()); 
        } 
        Object bean = xstream.fromXML(xml); 
        return bean; 
    } 

    /** 
     * 将XML转换为Bean 
     * 
     * @param clazzMap 别名-类名映射Map 
     * @param xml      要转换为bean对象的xml字符串 
     * @return Java Bean对象 
     */ 
    public static Object xml2Bean(Map<String, Class> clazzMap, File file)throws Exception { 
    	String xml = readFile(file);
        XStream xstream = new XStream(new DomDriver()); 
        for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext();) { 
            Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next(); 
            xstream.alias(m.getKey(), m.getValue()); 
        } 
        Object bean = xstream.fromXML(xml); 
        return bean; 
    } 
    
    public static String readFile(File f) throws Exception {   
       String fileContent = "";   
       FileReader fileReader = new FileReader(f);   
       BufferedReader reader = new BufferedReader(fileReader);   
       String line = "";   
       while ((line = reader.readLine()) != null)   
       {   
            fileContent = fileContent + line;   
       }   
       reader.close();   
       return fileContent;   
   }   
    /** 
     * 获取XStream对象 
     * 
     * @param clazzMap 别名-类名映射Map 
     * @return XStream对象 
     */ 
    public static XStream getXStreamObject(Map<String, Class> clazzMap) { 
        XStream xstream = new XStream(); 
        for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext();) { 
            Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next(); 
            xstream.alias(m.getKey(), m.getValue()); 
        } 
        return xstream; 
    } 
}