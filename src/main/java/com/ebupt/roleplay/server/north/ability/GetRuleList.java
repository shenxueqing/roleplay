package com.ebupt.roleplay.server.north.ability;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ebupt.roleplay.server.database.entity.Rule;
import com.ebupt.roleplay.server.database.service.RuleService;
import com.ebupt.roleplay.server.north.ability.base.BaseAbility;
import com.ebupt.roleplay.server.north.data.entity.BasicListContent;
import com.ebupt.roleplay.server.north.data.entity.ResultModel;
import com.ebupt.roleplay.server.north.exception.JhoServerException;




@Component("getRuleList")
@Scope("prototype")
public class GetRuleList implements BaseAbility{ 

	
	//private static Logger logger = LoggerFactory.getLogger(GetRuleList.class);
	
	@Autowired
	private RuleService ruleService;
	
	public ResultModel doAbility(HashMap<String, String> varsMap,List<FileItem> fileList)
	throws JhoServerException {

		BasicListContent<Rule> content = new BasicListContent<Rule>();
		List<Rule> ruleList = ruleService.findAll();
		content.setList(ruleList);	
		ResultModel resultModel = new ResultModel();
		resultModel.setContent(content);
		return resultModel;
	}
}
	
	
		
	

