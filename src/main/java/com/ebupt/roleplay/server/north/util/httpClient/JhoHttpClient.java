package com.ebupt.roleplay.server.north.util.httpClient;

import javax.annotation.PreDestroy;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.springframework.stereotype.Component;




/**
 * JhoHttpClient对习以为常http请求的通用部分进行了封装。该类继承自DefaultHttpClient
 * 部分属性参数由配置文件进行配置
 * @author shenxueqing
 * @see DefaultHttpClient
 */
@Component
public class JhoHttpClient extends DefaultHttpClient{
	private static JhoHttpClient instance = null;
//	@Value("#{srmmb_httpClient_Config['httpPort']}")
	private int httpPort = 80;
	
//	@Value("#{srmmb_httpClient_Config['httpsPort']}")
	private int httpsPort = 443;
	
//	@Value("#{srmmb_httpClient_Config['maxTotal']}")
	private int maxTotal = 10;
	
//	@Value("#{srmmb_httpClient_Config['maxPerRoute']}")
	private int maxPerRoute = 10;
	
//	@Value("#{srmmb_httpClient_Config['conTimeOut']}")
	private static int conTimeOut = 20000;
	
//	@Value("#{srmmb_httpClient_Config['soTimeOut']}")
	private static int soTimeOut = 20000;
	
//	@Value("#{srmmb_httpClient_Config['retryCount']}")
	private static int retryCount = 3;
	
//	@Value("#{srmmb_httpClient_Config['idleTime']}")
	private int idleTime = 60000;
	
//	@Value("#{srmmb_httpClient_Config['sleepInterval']}")
	private int sleepInterval = 10000;
	
	public void setSleepInterval(int sleepInterval) {
		this.sleepInterval = sleepInterval;
	}

	public void setIdleTime(int idleTime) {
		this.idleTime = idleTime;
	}
	private IdleConnectionMonitorThread monitorThread;
	
	 
    public void setHttpPort(int httpPort) {
		this.httpPort = httpPort;
	}

	public void setHttpsPort(int httpsPort) {
		this.httpsPort = httpsPort;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public void setMaxPerRoute(int maxPerRoute) {
		this.maxPerRoute = maxPerRoute;
	}

	public  void setConTimeOut(int conTimeOut) {
		JhoHttpClient.conTimeOut = conTimeOut;
	}

	public  void setSoTimeOut(int soTimeOut) {
		JhoHttpClient.soTimeOut = soTimeOut;
	}

	public  void setRetryCount(int retryCount) {
		JhoHttpClient.retryCount = retryCount;
	}


	/**
	 * @param conman  连接管理器
	 * @param params  Http参数
	 */
	public JhoHttpClient(
            final ClientConnectionManager conman,
            final HttpParams params) {
        super(conman, params);
        startMonitor();
}

    public JhoHttpClient(
            final ClientConnectionManager conman) {
        super(conman, null);
    }

    public JhoHttpClient(final HttpParams params) {
        super(null, params);
    }

    public JhoHttpClient() {
        super(null, null);
    }

    /** 创建http参数
     * @see org.apache.http.impl.client.AbstractHttpClient#createHttpParams()
     */
    @Override
    protected HttpParams createHttpParams() {
        HttpParams params = new SyncBasicHttpParams();
        setDefaultHttpParams(params);
        HttpConnectionParams.setConnectionTimeout(params, conTimeOut);
        HttpConnectionParams.setSoTimeout(params, soTimeOut);
        return params;
    }

    
    /**
     * 创建连接池管理器
     */
    @Override
    public ClientConnectionManager createClientConnectionManager() {
	   SchemeRegistry registry = new SchemeRegistry();
       registry.register(
               new Scheme("http", httpPort, PlainSocketFactory.getSocketFactory()));
       registry.register(
               new Scheme("https", httpsPort, SSLSocketFactory.getSocketFactory()));
       PoolingClientConnectionManager connManager  = new PoolingClientConnectionManager(registry);
       connManager.setMaxTotal(maxTotal);
       connManager.setDefaultMaxPerRoute(maxPerRoute);
       return connManager;
    }
   
    /**
     * 开启监控线程，定时关闭空闲或者过期的连接
     */
    public void startMonitor(){
	   monitorThread =  new IdleConnectionMonitorThread(getConnectionManager(),idleTime,sleepInterval);
       monitorThread.start();
   }
   
   /**
    * 创建请求重试处理器
    */
   @Override
   public HttpRequestRetryHandler createHttpRequestRetryHandler() {
	   
       return new JhoHttpRequestRetryHandler(retryCount,false);
   }

   /**
    * Spring 销毁bean时调用，关闭监控线程，关闭连接管理器
    */

    @PreDestroy
    public void destroy(){
    	if(monitorThread!= null &&monitorThread.isAlive()){
    		monitorThread.shutdown();
    	}
    	this.getConnectionManager().shutdown();
    }
}

