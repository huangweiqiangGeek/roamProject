package util;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.commons.lang.math.NumberUtils;

import oracle.sql.CHAR;



/**
 * @describe 数据类型转换
 * @author Lukas 414024003@qq.com
 * @date 2015年8月3日14:23:13
 * @version 1.0
 */
public class Convert
{
	public static String withCha (String str, String cha){
		if(str.startsWith(cha)){
			str = str.substring(1,str.length());
		}
		if(str.endsWith(cha)){
			str = str.substring(0,str.length()-1);
		}
		return str;
	}
	
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	
	/**
	 * string转int
	 * @param str 字符窜
	 * @param defaultValue 默认int类型
	 * @return
	 */
  public static int strToInt(String str, int defaultValue)
  {
    int Result = defaultValue;
    try
    {
      Result = Integer.parseInt(str);
    }
    catch (Exception localException) {
    }
    return Result;
  }

  /**
   * string转long
   * @param str 字符窜
   * @param defaultValue 默认long
   * @return
   */
  public static long strToLong(String str, long defaultValue) {
    long Result = defaultValue;
    try
    {
      Result = Long.parseLong(str);
    }
    catch (Exception localException) {
    }
    return Result;
  }

  /**
   * string转float
   * @param str
   * @param defaultValue
   * @return
   */
  public static float strToFloat(String str, float defaultValue) {
    float Result = defaultValue;
    try
    {
      Result = Float.parseFloat(str);
    }
    catch (Exception localException) {
    }
    return Result;
  }

  public static double strToDouble(String str, double defaultValue) {
    double Result = defaultValue;
    try
    {
      Result = Double.parseDouble(str);
    }
    catch (Exception localException) {
    }
    return Result;
  }
  
  public static BigDecimal strToBigDecimal(String str,BigDecimal defaultValue) {
		BigDecimal dec =defaultValue;
		if(str==null || str==""){
			return dec;
		}
		BigDecimal bd = new BigDecimal(str);
		dec = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return dec;
	}
  
  
  /**
	 * String类型转Double类型，保留2位小数
	 * @param doubleStr
	 * @return double
	 */
	public  static double strToDouble(String doubleStr){
		DecimalFormat   df = new DecimalFormat("#.00");
		return NumberUtils.toDouble(df.format(doubleStr), 0.00);
	}
	
	/**
	 * Double类型（保留2位小数）
	 * @param doubleStr
	 * @return double
	 */
	public  static double strToDouble(Double doubleStr){
		DecimalFormat   df = new DecimalFormat("#.00");
		return  NumberUtils.toDouble(df.format(doubleStr), 0.00) ;
	}

  public static boolean strToBoolean(String str, boolean defaultValue) {
    boolean Result = defaultValue;
    try
    {
      Result = Boolean.parseBoolean(str);
    }
    catch (Exception localException) {
    }
    return Result;
  }

  public static java.util.Date strToDate(String str, java.util.Date defaultValue)
  {
    return strToDate(str, "yyyy-MM-dd HH:mm:ss", defaultValue);
  }

  public static java.util.Date strToDate(String str, String format, java.util.Date defaultValue) {
    java.util.Date Result = defaultValue;
    SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
    try
    {
      Result = formatter.parse(str);
    }
    catch (Exception localException) {
    }
    return Result;
  }

  public static String dateToStr(java.util.Date date, String defaultValue)
  {
    return dateToStr(date, "yyyy-MM-dd HH:mm:ss", defaultValue);
  }

  public static String dateToStr(java.util.Date date, String format, String defaultValue) {
    String Result = defaultValue;
    SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
    try
    {
      Result = formatter.format(date);
    }
    catch (Exception localException) {
    }
    return Result;
  }

  public static String strToStr(String str, String defaultValue) {
    String Result = defaultValue;

    if ((str != null) && (!(str.isEmpty()))) {
      Result = str;
    }

    return Result;
  }

  public static java.sql.Date dateToSqlDate(java.util.Date date) {
    return new java.sql.Date(date.getTime());
  }

  public static java.util.Date sqlDateToDate(java.sql.Date date) {
    return new java.util.Date(date.getTime());
  }

  public static Timestamp dateToSqlTimestamp(java.util.Date date) {
    return new Timestamp(date.getTime());
  }

  public static java.util.Date qlTimestampToDate(Timestamp date) {
    return new java.util.Date(date.getTime());
  }

  public static int strtoAsc(String st)
  {
    byte[] gc = st.getBytes();
    int asnum = gc[0];
    return asnum;
  }

  public static char intToChar(int backnum)
  {
    char stchar = (char)backnum;
    return stchar;
  }
}