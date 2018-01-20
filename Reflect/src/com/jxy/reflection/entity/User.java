package com.jxy.reflection.entity;
/** 
* @author 焦祥宇 
* @version 创建时间：2017年3月12日 上午8:35:57 
* @description  
*/
public class User {
	private String id;
	private String name;
	private String sex;
	private String age;
	
		
	public User() {
		
	}


	public User(String id, String name, String sex, String age) {
		
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getAge() {
		return age;
	}



	public void setAge(String age) {
		this.age = age;
	}



	@Override
	public String toString() {
		return "User [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
	
}
