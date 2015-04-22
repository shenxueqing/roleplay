package com.ebupt.roleplay.server.database.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "activity", uniqueConstraints = @UniqueConstraint(columnNames = {
		"id", "ruleId" ,"organizerId","status","startTime","endTime"}))
public class Activity implements BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ruleId")
	private Rule rule;
	private String organizerId = null;
	private Date startTime = null ;
	private Date endTime = null;
	private Integer max = 0;
	private Integer playerNum ;
	private Integer status;
	

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Rule getRule() {
		return rule;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
		if(null!=rule){
			rule.getActivity().add(this);
		}
	}
	public String getOrganizerId() {
		return organizerId;
	}
	public void setOrganizerId( String organizerId) {
		this.organizerId = organizerId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public Integer getPlayerNum() {
		return playerNum;
	}
	public void setPlayerNum(Integer playerNum) {
		this.playerNum = playerNum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
