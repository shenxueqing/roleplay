package com.ebupt.roleplay.server.database.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ebupt.roleplay.server.database.entity.Activity;




@Repository("activityDao")
@Transactional
public class ActivityDaoImpl extends GenericHibernateDaoImpl<Activity, Long>
		implements ActivityDao {

	public ActivityDaoImpl(Class<Activity> type) {
		super(type);
	}

	public ActivityDaoImpl() {
		this(Activity.class);
	}
}
