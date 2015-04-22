package com.ebupt.roleplay.server.database.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "rule"/*, uniqueConstraints = @UniqueConstraint(columnNames = {
		"id", "shopName" ,"shopAddr","ruleDes","startTime","endTime"})*/)
@JsonIgnoreProperties(ignoreUnknown = true)  
public class Rule implements BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToMany(mappedBy = "rule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.EXTRA)
	@OrderBy("startTime DESC")
	private List<Activity> activity = new ArrayList<Activity>();

	private String shopName =null;
	private String shopAddr = null;
	private String ruleDes = null;
	private String shopDes = null;
	private Date startTime = null;
	private Date endTime = null ;
	
	
	public Rule() {
	};


	public Rule(String shopName, String shopAddr, String ruleDes, Date startTime,
			Date endTime) {
		this.shopAddr = shopAddr;
		this.shopName = shopName;
		this.ruleDes = ruleDes;
		this.startTime = startTime;
		this.endTime = endTime;
		
		
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddr() {
		return shopAddr;
	}

	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}

	public String getRuleDes() {
		return ruleDes;
	}

	public void setRuleDes(String ruleDes) {
		this.ruleDes = ruleDes;
	}
	

	public String getShopDes() {
		return shopDes;
	}

	public void setShopDes(String shopDes) {
		this.shopDes = shopDes;
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

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	@JsonIgnore  
	public List<Activity> getActivity() {
		return activity;
	}


	public void setActivity(List<Activity> activity) {
		this.activity = activity;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!((null != obj) && (obj.getClass() == this.getClass())))
			return false;
		final Rule other = (Rule) obj;
		return other.getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		int result = 31;
		result += this.getId() == null ? 0 : this.getId().hashCode();
		return result;
	}



	
	
}
