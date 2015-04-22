package com.ebupt.justholdon.server.database.service.test;

import static org.junit.Assert.*;


import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ebupt.roleplay.server.database.entity.Rule;
import com.ebupt.roleplay.server.database.service.RuleService;


public class RuleServiceImplTest {
	static RuleService ruleService;
	

	static	{
		ApplicationContext ctx = new FileSystemXmlApplicationContext("D:/eclipse/role-play/src/test/resources/bean.xml");
		ruleService = (RuleService) ctx.getBean("ruleService");
		
	}
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testGetRulelist()
	{
		List<Rule>  ruleList = ruleService.findAll();
		
		assertEquals(3,ruleList.size());
		
		
	}
	
	private void print(List<Rule>rets){
		for (Rule ret : rets) {
			System.out.println("rule: " + ret.getId() + " "
					+ ret.getActivity().size());
		}
	}
 
}
