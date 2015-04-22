package com.ebupt.roleplay.server.database.service;

import java.util.List;
//import java.util.Set;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebupt.roleplay.server.database.dao.ActivityDao;

import com.ebupt.roleplay.server.database.entity.Activity;



@Service("activityService")
@Transactional
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityDao activityDao;
	
	//@Override
	public Long save(Activity newInstance) {
		return activityDao.save(newInstance);
	}

	//@Override
	public Activity get(Long id) {
		return activityDao.get(id);
	}

	//@Override
	public void update(Activity transientObject) {
		activityDao.update(transientObject);
	}




	//@Override
	public List<Activity> findAll() {
		return activityDao.findAll();
	}




	public int update(Long id, Map<String, Object> infos) {
		
			return activityDao.update(id, infos);
	}








}
