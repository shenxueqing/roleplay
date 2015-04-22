package com.ebupt.roleplay.server.north.data.entity;

import java.util.List;

public class  BasicListContent<T> implements BasicContent{
	
	private List<T> list;
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

}
