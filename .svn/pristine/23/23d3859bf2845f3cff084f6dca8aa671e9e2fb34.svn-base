package util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

/** 
 * 说明：日期处理
 * 创建人：lukas 414024003@qq.com
 * 修改时间：2015年11月24日
 * @version
 */
public class DateUtil {
	
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat yymmdd = new SimpleDateFormat("yyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfTime2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
	private final static SimpleDateFormat sdfTime3 = new SimpleDateFormat("HH-mm-ss");
	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");
	private final static SimpleDateFormat sdfTimeMiss = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getSdfTimes() {
		return sdfTimes.format(new Date());
	}
	/**
	 * 获取HH-mm-ss格式
	 * @return
	 */
	public static String getSdfTimes3() {
		return sdfTime3.format(new Date());
	}
	
	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}
	
	/**
	 * 
	 * @param date HH:ss 的时间格式
	 * @return 1,现在的时间 比 date要大
	 */
	public static int comparaDateTime(String date){
		String toDayTime = sdfTimeMiss.format(new Date());
		String date1 = getDay()+" "+date;
		Date toDay =  fomatDate2(toDayTime);
		Date dateDay = fomatDate2(date1);
		long t1 = toDay.getTime();
		long t2 = dateDay.getTime();
		if(t1>=t2){
			return 1;
		}else{
			return 0;
		}
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	public static String getDay(Date d) {
		return sdfDay.format(d);
	}
	/**
	 * 获取YYYYMMDD格式
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}
	/**
	 * 获取YYYY-MM-DD HH:mm:ss:SS格式
	 * @return
	 */
	public static String getTime2() {
		return sdfTime.format(new Date());
	}
	
	/**
	 * 获取yymmdd格式
	 * @return
	 */
	public static String getyyMMdd() {
		return yymmdd.format(new Date());
	}


	
	
	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author fh
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 格式化日期
	 * @return
	 */
	public static Date fomatDate1(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 格式化日期
	 * @return
	 */
	public static Date fomatDate2(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 校验日期是否合法
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	
	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	 
	/**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 时间相减得到分钟
     * @param beginDateStr 前一个时间
     * @param endDateStr 后一个时间
     * @return
     */
    public static long getDaySubMin(String beginDateStr,String endDateStr){
        long min=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
         try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        min=(endDate.getTime()-beginDate.getTime())/(1000*60);
        return min;
    }
    
    /**
     * 功能描述：时间相减得到天数只能用于微信返回时间计算
     * @param beginDateStr 微信返回时间戳
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr){
    	beginDateStr = formatTime(beginDateStr);
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = new Date();
            try {
				beginDate = format.parse(beginDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /** 
     * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss） 
     *  
     * @param createTime 消息创建时间 
     * @return 
     */  
    public static String formatTime(String createTime) {  
        // 将微信传入的CreateTime转换成long类型，再乘以1000  
        long msgCreateTime = Long.parseLong(createTime) * 1000L;  
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        return format.format(new Date(msgCreateTime));  

    }  
    
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    
    /**
     * 获取Timestamp类型时间
     * @return
     */
    public static Timestamp getTimestamp(){
    	return new Timestamp(System.currentTimeMillis()); 
    } 
    
    public static void main(String[] args) throws ParseException, InterruptedException {
//    	System.out.println(getDaySubMin("2016-08-30 16:10", "2016-08-31 16:12"));
//    	Cache cache = CacheManager.getCacheInfo("test1");
//		if ((cache == null) || (cache.isExpired())) {
//			System.err.println("没有缓存");
//			cache = new Cache();
//			cache.setValue("j_slU218fP66HjiF84MJYEkIVkMo-hv8Ext9oBijMsfK3F5SJ3UMnDrHjYQnCAByTJmR73gW0ISZn6BLPvHI0WBqUY316GYyDYUrs337FVA");
//			CacheManager.putCacheInfo("test1", cache,5L);
//			System.err.println("缓存中的AccessToken为：---" + cache.getValue().toString());
//		}
//		System.err.println("AccessToken为：---" + cache.getValue().toString());
//		Thread.sleep(5);  
//		Cache c = CacheManager.getCacheInfo("test1");
//		if ((c == null) || (c.isExpired())) {
//			System.err.println("缓存过期拉");
//		}
//		System.err.println("结束");、
    }

}
