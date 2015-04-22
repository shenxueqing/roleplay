package com.ebupt.justholdon.server.database.service.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ebupt.roleplay.server.database.entity.Activity;
import com.ebupt.roleplay.server.database.entity.Rule;
import com.ebupt.roleplay.server.database.service.ActivityService;
import com.ebupt.roleplay.server.database.service.RuleService;



public class ActivityServiceImplTest {
	static ActivityService activityService;
	static RuleService ruleService;
	
	static {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("D:/eclipse/role-play/src/test/resources/bean.xml");
		activityService = (ActivityService) ctx.getBean("activityService");
		ruleService = (RuleService) ctx.getBean("ruleService");
 

	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		// for(Integer cid:cids)
		// checkInService.deleteCheckIn(cid);
		// cids.clear();
	}

	 
 	@Test
	public void testCreate() {
 		Rule rule = new Rule();
 		rule.setRuleDes("lalala");
 		rule.setShopAddr("222");
 		rule.setShopDes("ccc");
 		rule.setShopName("dddd");
 		rule.setStartTime(new Date());
 		rule.setEndTime(new Date());
 	
 		Activity e =new Activity();
 		e.setMax(1);
 		e.setPlayerNum(2);
 		e.setEndTime(new Date());
 		e.setStartTime(new Date());
 		e.setStatus(1);
 		e.setRule(rule);
 		e.getRule().getActivity().add(e);
 		ruleService.save(rule);
 		
		
		
		
	}
	
	
}
