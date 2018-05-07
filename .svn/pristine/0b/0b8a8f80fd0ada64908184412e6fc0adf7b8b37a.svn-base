package listener;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import server.CompLocationReport;

public class CompListener implements javax.servlet.ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DAY_OF_MONTH, 1);
//		cal.set(Calendar.HOUR_OF_DAY, 0);
//		cal.set(Calendar.MINUTE, 1);
//		cal.set(Calendar.SECOND, 0);
//		cal.add(Calendar.SECOND, 30);
		
//		ServletContext s1= arg0.getServletContext(); 
		final StringBuffer path= new StringBuffer("D:");
		path.append("/xmlFilesDownLoad/");
		
	       try {
	           Timer timer = new Timer();
	           System.out.println("-----------企业定时间器已经启动。。。。。。");
	           timer.schedule(new TimerTask() {//scheduleAtFixedRate
	             @Override
	             public void run() {
	            	 
                    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
                    CompLocationReport LocationReport = (CompLocationReport)ac.getBean("locationReport");
                    LocationReport.getAllUpdateItem(path);
	  	            System.out.println("-----------企业计划已经执行。。。。。。");
	             }
	         }, cal.getTime() , 60*1000);
	     } catch (Exception e) {
	        e.printStackTrace();
	     }
	}

}
