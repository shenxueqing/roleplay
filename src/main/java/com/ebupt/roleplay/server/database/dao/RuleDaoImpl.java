package com.ebupt.roleplay.server.database.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ebupt.roleplay.server.database.entity.Rule;



@Repository("ruleDao")
@Transactional
public class RuleDaoImpl extends GenericHibernateDaoImpl<Rule, Long>
		implements RuleDao {

	public RuleDaoImpl(Class<Rule> type) {
		super(type);
	}

	public RuleDaoImpl() {
		this(Rule.class);
	}
}
