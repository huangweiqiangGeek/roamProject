package com.guoll.modules.framework.util;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import com.guoll.modules.framework.MQ_Constants;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;

public class ReportUtil {


	/**
	 * 生成XML
	 * 
	 * @param clazzMap
	 *            别名-类名映射Map
	 * @return XStream对象
	 */
	public static void makeReportFile(Object bean) {
		MQQueueManager qMgr;
		MQQueue queue;
		try {

			String qMgrName = MQ_Constants.UPLOAD_MQMGR_NAME_1;
		    String qName = MQ_Constants.UPLOAD_MQ_NAME_1;
			MQEnvironment.hostname = MQ_Constants.UPLOAD_HOSTNAME_1;
			MQEnvironment.port = MQ_Constants.UPLOAD_HOSTPORT_1;
			MQEnvironment.channel = MQ_Constants.UPLOAD_HOSTCHANNEL_1;
			qMgr = new MQQueueManager(qMgrName);
			int openOptions = MQConstants.MQOO_OUTPUT
					| MQConstants.MQOO_FAIL_IF_QUIESCING
					| MQConstants.MQOO_BIND_NOT_FIXED;
			// int openOptions = MQConstants.MQOO_OUTPUT ;
			queue = qMgr.accessQueue(qName, openOptions);
			System.out.println("Open successful!!!");

			MQPutMessageOptions pmo = new MQPutMessageOptions();
			MQMessage msg = new MQMessage();
			msg.writeObject(bean);
			queue.put(msg, pmo);

			queue.close();
			qMgr.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Object> getObject() {
		MQQueueManager qMgr;
		MQQueue queue;
		List<Object> reObj = new ArrayList<Object>();
		try {

			String qMgrName = MQ_Constants.DOWNLOAD_MQMGR_NAME_1;
		    String qName = MQ_Constants.DOWNLOAD_MQ_NAME_1;
			MQEnvironment.hostname = MQ_Constants.DOWNLOAD_HOSTNAME_1;
			MQEnvironment.port = MQ_Constants.DOWNLOAD_HOSTPORT_1;
			MQEnvironment.channel = MQ_Constants.DOWNLOAD_HOSTCHANNEL_1;
			qMgr = new MQQueueManager(qMgrName);

			// Set the queue options for output
			int openOptions = MQConstants.MQOO_INPUT_AS_Q_DEF
					| MQConstants.MQOO_FAIL_IF_QUIESCING | MQConstants.MQOO_INQUIRE ;

			queue = qMgr.accessQueue(qName, openOptions);
			while(queue.getCurrentDepth()>0){

				System.out.println("Open successful!!!");

				MQGetMessageOptions gmo = new MQGetMessageOptions();
				gmo.matchOptions = MQConstants.MQMO_NONE;
				System.out.println("Start to get messages!");
				MQMessage msg = new MQMessage();
				queue.get(msg, gmo);

				reObj.add(msg.readObject());
			}

			queue.close();
			qMgr.disconnect();
		} catch (MQException mqe) {
            System.out.println(mqe.getReason());
		} catch (InvalidClassException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OptionalDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reObj;
	}


}
