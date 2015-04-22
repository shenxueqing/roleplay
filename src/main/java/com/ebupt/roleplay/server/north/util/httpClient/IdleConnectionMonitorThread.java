package com.ebupt.roleplay.server.north.util.httpClient;

import java.util.concurrent.TimeUnit;

import org.apache.http.conn.ClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdleConnectionMonitorThread extends Thread {
	private Logger logger = LoggerFactory.getLogger(IdleConnectionMonitorThread .class);
	
	private final ClientConnectionManager connMgr;
	private volatile boolean shutdown = false;
	//空闲时间：空闲时间超过该时间的连接将被关闭
	private int idleTime;
	//检查间隔时间
	private int sleepInterval;
	
	public IdleConnectionMonitorThread(ClientConnectionManager connMgr,int idleTime,int sleepInterval) {
		super();
		this.connMgr = connMgr;
		this.idleTime = idleTime;
		this.sleepInterval = sleepInterval;
	}
	
	@Override
	public void run() {
		try {
			while (!shutdown) {
					sleep(sleepInterval);
					connMgr.closeExpiredConnections();
					connMgr.closeIdleConnections(idleTime, TimeUnit.MILLISECONDS);			
			}
		} catch (InterruptedException ex) {
			logger.debug("Monitor Thread has been interrupt:",ex);
			//其他异常处理
		}
	}
	
	public void shutdown(){
		shutdown = true;
		
	}


}
