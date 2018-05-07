package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONArray;
import com.guoll.modules.useCase.bean.UseCase;

import util.fields.BillFieldForParseJson;

/**
 * txt文本文件操作工具类
 * 
 * @author wangb
 *
 */
public class TxtFileUtil {
	public static void main(String[] args) {
	}

	/**
	 * 将用例的话单写成txt文件
	 * 
	 * @param useCase
	 * @return
	 */
	@SuppressWarnings("resource")
	public void makeTxtFileFromBill(UseCase useCase) {
		try {
			String billString = new String(useCase.getuCTicket(),"GBK");
			String listString = billString.substring(billString.indexOf("["), billString.indexOf("]") + 1);
			List<BillFieldForParseJson> list = JSONArray.parseArray(listString, BillFieldForParseJson.class);
			File parent = new File("txts");
			if (!parent.exists()) {
				parent.mkdirs();
			}
			File child = new File(parent, useCase.getId() + ".txt");
			child.createNewFile();

			BufferedWriter writer = new BufferedWriter(new FileWriter(child));
			for (BillFieldForParseJson billFieldForParseJson : list) {
				if (billFieldForParseJson.getChoiceaway().equals("12")) {
					String time = billFieldForParseJson.getsTime();
					time = time.replace(":", "");
					time = time.replace("/", "");
					time = time.replace("\\", "");
					writer.write(billFieldForParseJson.getName() + "=" + time);
				} else {
					writer.write(billFieldForParseJson.getName() + "=" + billFieldForParseJson.getVal());
				}
				writer.newLine();
				writer.flush();
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * 将键值对格式的免费资源结果txt文件读取为map 解析后格式： map={产品1 id：{字段1：字段1值，字段2：字段2值,...}，产品2
	 * id：{字段1：字段1值，字段2：字段2值,...}}
	 * 
	 * @param txtFile
	 * @return
	 * @throws Exception
	 */
	public Map<String, Map<String, String>> txtResourceResultToMap(String txtFile) {
		try {
			Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
			File file = new File(txtFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				String prodid = null;//用以保存产品id
				temp = temp.trim();// temp格式：tid=252100004161252619,prodid=prod.10086000002484,amt=1024，代表一个产品的计算结果
				Map<String, String> map2 = new HashMap<String, String>();
				String[] split = temp.split(",");// 分割后每一段格式：tid=252100004161252619，代表一个产品的计算结果的一个属性
				for (String string : split) {
					String[] split2 = string.split("=");// 再分割即得到每一个产品的计算结果每一个属性的属性名和属性值
					if (split2.length > 1) {
						map2.put(split2[0].trim(), split2[1].trim());
					} else {
						map2.put(split2[0].trim(), "");
					}
					if(split2[0].trim().equals("product_id")){//如果该属性是产品id，就保留下来，用以存储本产品
						prodid = split2[1];
					}
				}
				if (prodid != null) {//将解析后的产品存储到map
					map.put(prodid.trim(), map2);
				}
			}
			file.delete();
			return map;//返回map
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取标准话单文件名
	 * 该方法有修改，现在返回的为： 标准话单名;话单开始时间
	 * @return 
	 */
	public String getStandardFillName(String filePathName) {
		try {
			File file = new File(filePathName);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String temp = br.readLine();
			br.close();
			String[] split = temp.split("=");
			file.delete();
			return split[0].trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
