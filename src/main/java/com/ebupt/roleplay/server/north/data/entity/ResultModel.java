package com.ebupt.roleplay.server.north.data.entity;

public class ResultModel {
	private String status;
	private String msg;
	private BasicContent content;
	public ResultModel(){
		
	}
	public ResultModel(BasicContent content){
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public BasicContent getContent() {
		return content;
	}
	public void setContent(BasicContent content) {
		this.content = content;
	}

}
