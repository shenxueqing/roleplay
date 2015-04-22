package com.ebupt.roleplay.server.database.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericService<T, PK extends Serializable> {
	/** Persist the newInstance object into database */
	//used
	PK save(T newInstance);

	/**
	 * Retrieve an object that was previously persisted to the database using
	 * the indicated id as primary key
	 */
	//used
	T get(PK id);

	/** Save changes made to a persistent object. */
	//used
	void update(T transientObject);
	//used
	int update(PK id, Map<String, Object> infos);
	
	
	//used
	List<T> findAll();

	
}
