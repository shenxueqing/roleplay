package com.ebupt.roleplay.server.north.util.httpClient;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;



public class JhoHttpRequestRetryHandler extends DefaultHttpRequestRetryHandler implements HttpRequestRetryHandler {
    private  int retryCount;
    private  boolean requestSentRetryEnabled;
  

    /**
     * @param retryCount 重试次数
     * @param requestSentRetryEnabled 已成功发送的请求是否需要重试
     */
    public JhoHttpRequestRetryHandler(int retryCount, boolean requestSentRetryEnabled){
        super(retryCount,requestSentRetryEnabled);
    }

    public JhoHttpRequestRetryHandler() {
    	super();
        this.requestSentRetryEnabled = false;
        this.retryCount = 3;
    }
    /**
     * 决定是否需要重试该请求
     */
    public boolean retryRequest(
            final IOException exception,
            int executionCount,
            final HttpContext context) {
    	
        if (exception == null) {
            throw new IllegalArgumentException("Exception parameter may not be null");
        }
        if (context == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
        if (executionCount > this.retryCount) {
            return false;
        }
        // 通信超时：服务器端socket在read或者accept状态时超时
        if (exception instanceof SocketTimeoutException) {
            return true;
        }
        //连接超时：连接服务器时超时或者从连接管理器取得连接时超时
        if (exception instanceof ConnectTimeoutException) {
            return true;
        }
       
        //未知主机：主机ip无法解析
        if (exception instanceof UnknownHostException) {
            return false;
        }
        //连接异常：连接地址和端口时的异常，一般情况是，服务器端没有这样的ipAddress和port的socket在监听请求
        if (exception instanceof ConnectException) {
            
            return true;
        }
        //SSL异常
        if (exception instanceof SSLException) {
            return false;
        }

        HttpRequest request = (HttpRequest)
            context.getAttribute(ExecutionContext.HTTP_REQUEST);
        
        //请求的执行已经中止
        if(super.requestIsAborted(request)){
            return false;
        }

        //幂等的请求，重试
        //request是幂等的：该请求无论发送一次或者多次,得到的结果相同：如GET，HEAD,PUT,DELETE,TRACE和OPTIONS方法。
        //而POST则是非幂等的：例如订单Post请求多次发送可能导致重复下单。

        if (super.handleAsIdempotent(request)) {
            return true;
        }

        Boolean b = (Boolean)
            context.getAttribute(ExecutionContext.HTTP_REQ_SENT);
        
        boolean sent = (b != null && b.booleanValue());
        //如果当前请求没发送成功或者允许重新发送已成功的请求，则重试
        if (!sent || this.requestSentRetryEnabled) {
            return true;
        }
        // 其他情况 不重试
        return false;
    }


}
