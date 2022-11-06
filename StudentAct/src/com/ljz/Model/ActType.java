package com.ljz.Model;

public class ActType {
	private int id;
	private String actTypeName;
	private String actTypeDesc;
	
	public ActType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActType(int id, String actTypeName, String actTypeDesc) {
		super();
		this.id = id;
		this.actTypeName = actTypeName;
		this.actTypeDesc = actTypeDesc;
	}

	public ActType(String actTypeName, String actTypeDesc) {
		super();
		this.actTypeName = actTypeName;
		this.actTypeDesc = actTypeDesc;
	}

	public int getId() {
		return id;
	}

	public String getActTypeName() {
		return actTypeName;
	}

	public String getActTypeDesc() {
		return actTypeDesc;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setActTypeName(String bookTypeName) {
		this.actTypeName = bookTypeName;
	}

	public void setActTypeDesc(String actTypeDesc) {
		this.actTypeDesc = actTypeDesc;
	}

	@Override
	public String toString() {
		return actTypeName;
	}
	
	
}
