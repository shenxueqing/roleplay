package com.ebupt.roleplay.server.north.servlet;


import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;







import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;







import com.ebupt.roleplay.server.north.ability.base.BaseAbility;

import com.ebupt.roleplay.server.north.data.entity.ResultModel;

import com.ebupt.roleplay.server.north.exception.JhoNorthResultStatus;
import com.ebupt.roleplay.server.north.exception.JhoServerException;
import com.ebupt.roleplay.server.north.exception.JhoServerServiceException;
import com.ebupt.roleplay.server.north.util.FileUtil;
import com.ebupt.roleplay.server.north.util.JsonParser;



public class RouterServlet extends HttpServlet { 

	private static final long serialVersionUID = 7549500487475377516L;
	private static final Logger logger = LoggerFactory.getLogger(RouterServlet.class);
	private WebApplicationContext wac;

	public void init(){
         wac =WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
     }

    /**
     * shenxueqing 2013-12-03 20:21
     * @param request
     * @param response
     */
    public void doGet ( HttpServletRequest request, HttpServletResponse response ){
        ResultModel result = new ResultModel();
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        String url = request.getRequestURL().toString().substring(7);
        String methodName = url.split("/")[2];
        long startTime = System.currentTimeMillis();
        logger.error("get Request of method:{}",methodName);
        try{

            result = ((BaseAbility)wac.getBean(methodName)).doAbility(getReqVarMap(request), null);

            result.setMsg("成功");
            result.setStatus(JhoNorthResultStatus.success);
        }catch (Exception e3) {
            logger.error("e:{}",e3);
            result.setStatus(JhoNorthResultStatus.Server_unknow_error);
            result.setMsg("啊哦~ 服务器傲娇了，请稍后再试");
        }
        try{
            ServletOutputStream output = response.getOutputStream();
            logger.error("the whole operation {} costs time:{}",methodName,System.currentTimeMillis()-startTime);
            output.write(JsonParser.getInstance().JavaBeanToJson(result).getBytes(Charset.forName("utf-8")));
        }catch(Exception e){
            logger.error("e:{}",e);
            response.setStatus(500);
        }
    }
	
	public void doPost ( HttpServletRequest request, HttpServletResponse response ){
		 	ResultModel result = new ResultModel();
	        response.setContentType("text/plain");
	        response.setCharacterEncoding("utf-8");

	        String url = request.getRequestURL().toString().substring(7);
	        String methodName = url.split("/")[2];
	        long startTime = System.currentTimeMillis();
	        logger.error("get Request of method:{}",methodName);
	        try{

	            result = ((BaseAbility)wac.getBean(methodName)).doAbility(getReqVarMap(request), null);

	            result.setMsg("成功");
	            result.setStatus(JhoNorthResultStatus.success);
	        }catch (Exception e3) {
	            logger.error("e:{}",e3);
	            result.setStatus(JhoNorthResultStatus.Server_unknow_error);
	            result.setMsg("啊哦~ 服务器傲娇了，请稍后再试");
	        }
	        try{
	            ServletOutputStream output = response.getOutputStream();
	            logger.error("the whole operation {} costs time:{}",methodName,System.currentTimeMillis()-startTime);
	            output.write(JsonParser.getInstance().JavaBeanToJson(result).getBytes(Charset.forName("utf-8")));
	        }catch(Exception e){
	            logger.error("e:{}",e);
	            response.setStatus(500);
	        }
	
	}	 
	
    private HashMap<String,String> getReqVarMap(HttpServletRequest request) {

        Map<String,String[]> reqVarMap = request.getParameterMap();

        Set<Entry<String, String[]>> entrySet = reqVarMap.entrySet();
        Iterator<Entry<String, String[]>> it = entrySet.iterator();
        HashMap<String,String> varsMap = new HashMap<String,String>();
        varsMap.put("User-Agent", request.getHeader("User-Agent"));
        while(it.hasNext()){
            Entry<String, String[]> e = it.next();
            if(!(e.getValue()== null) &&!(e.getValue().length == 0)){
                logger.debug(e.getKey()+":"+e.getValue()[0]);
                varsMap.put(e.getKey(), e.getValue()[0]);
            }
        }

        return varsMap;
    }
	
}
