package com.guoll.modules.framework.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ReportFileUtil {


	/**
	 * 生成XML
	 * 
	 * @param clazzMap
	 *            别名-类名映射Map
	 * @return XStream对象
	 */
	public static void makeReportFile(Object bean) {
		Class clazz = bean.getClass();
		Map<String, Class> cm = new HashMap<String, Class>();
		cm.put(clazz.getSimpleName(), clazz);
		String str = XMLBeanUtils.bean2xml(cm, bean);
		try {
			createFileFromXMLBean(str, bean);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createFileFromXMLBean(String str, Object bean)
			throws IOException {
		StringBuffer path = new StringBuffer("D:/xmlFilesUpload/");
		String filename = "";
		Calendar cal = Calendar.getInstance();
		String dataName = "_" + cal.get(Calendar.HOUR_OF_DAY)
				+ cal.get(Calendar.MINUTE) + cal.get(Calendar.SECOND) + ".xml";
//		if (bean.getClass().equals(CopDistItem.class)) {
//			CopDistItem item = (CopDistItem) bean;
//			filename = "dist_" + item.getHead().getDistSeq() + dataName;
//		} else if (bean.getClass().equals(BusVehicles.class)) {
//			BusVehicles item = (BusVehicles) bean;
//			filename = "car_" + item.getPlat_no() + dataName;
//		} else {
//			BusCardsIC item = (BusCardsIC) bean;
//			filename = "ic_" + item.getPlat_no() + dataName;
//		}
		File file = new File(path.toString() + filename);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		file.createNewFile();
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.newLine();
		bw.write(str);
		bw.close();
		fw.close();
	}
}
