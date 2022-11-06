package com.ljz.Model;

public class Activity {
	private int id;
	private String actName;
	private String leadername;
	private String sex;
	private String acttime;
	private Integer acttype;
	private String actTypeName;
	private String actDesc;
	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Activity(int id, String actName, String leadername, String sex, String acttime, Integer acttype, String actDesc) {
		super();
		this.id = id;
		this.actName = actName;
		this.leadername = leadername;
		this.sex = sex;
		this.acttime = acttime;
		this.acttype = acttype;
		this.actDesc = actDesc;
	}

	public Activity(String actName, String leadername, String sex, String acttime, Integer acttype, String actDesc) {
		super();
		this.actName = actName;
		this.leadername = leadername;
		this.sex = sex;
		this.acttime = acttime;
		this.acttype = acttype;
		this.actDesc = actDesc;
	}
	
	
	public Activity(String actName, String leadername, Integer acttype) {
		super();
		this.actName = actName;
		this.leadername = leadername;
		this.acttype = acttype;
	}

	public int getId() {
		return id;
	}
	public String getActName() {
		return actName;
	}
	public String getLeaderName() {
		return leadername;
	}
	public String getSex() {
		return sex;
	}
	public String getActTime() {
		return acttime;
	}
	public Integer getActTypeid() {
		return acttype;
	}
	public String getActTypeName() {
		return actTypeName;
	}
	public String getActDesc() {
		return actDesc;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public void setLeadername(String leadername) {
		this.leadername = leadername;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setActtime(String acttime) {
		this.acttime = acttime;
	}
	public void setActtype(Integer acttype) {
		this.acttype = acttype;
	}
	public void setActTypeName(String actTypeName) {
		this.actTypeName = actTypeName;
	}
	public void setActDesc(String actDesc) {
		this.actDesc = actDesc;
	}
	
	
}
