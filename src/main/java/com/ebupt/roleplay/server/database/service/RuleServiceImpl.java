package com.ebupt.roleplay.server.database.service;

import java.util.List;
//import java.util.Set;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebupt.roleplay.server.database.dao.RuleDao;
import com.ebupt.roleplay.server.database.entity.Rule;


@Service("ruleService")
@Transactional
public class RuleServiceImpl implements RuleService {

	@Autowired
	private RuleDao ruleDao;
	
	//@Override
	public Long save(Rule newInstance) {
		return ruleDao.save(newInstance);
	}

	//@Override
	public Rule get(Long id) {
		return ruleDao.get(id);
	}

	//@Override
	public void update(Rule transientObject) {
		ruleDao.update(transientObject);
	}




	//@Override
	public List<Rule> findAll() {
		return ruleDao.findAll();
		
		
	}




	public int update(Long id, Map<String, Object> infos) {
		
			return ruleDao.update(id, infos);
	}






}
