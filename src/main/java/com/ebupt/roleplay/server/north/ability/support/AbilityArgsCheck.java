package com.ebupt.roleplay.server.north.ability.support;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



import com.ebupt.roleplay.server.north.exception.JhoServerServiceException;


//@Aspect
public class AbilityArgsCheck {
	/*private Logger logger = LoggerFactory.getLogger(AbilityArgsCheck.class);
	@Autowired
	private Authorize authorize;
	@Pointcut("execution (* com.ebupt.justholdon.server.north.ability.base.NeedAuthAbility+.doAbility(java.util.HashMap<java.lang.String,java.lang.String>,java.util.List<org.apache.commons.fileupload.FileItem>))&& args(varsMap,items)" )
	public void doAb(java.util.HashMap<java.lang.String,java.lang.String> varsMap,java.util.List<org.apache.commons.fileupload.FileItem>items){
	}
	@Before("doAb(varsMap,items)")
	public void isVarsValid(java.util.HashMap<java.lang.String,java.lang.String> varsMap,java.util.List<org.apache.commons.fileupload.FileItem>items) throws JhoServerServiceException {	
		logger.debug("enter Aop");
		if(items == null){
			logger.error(varsMap.toString());
			authorize.isValidUserWithStrArgs(varsMap);
		}
		
		
	}*/
}
