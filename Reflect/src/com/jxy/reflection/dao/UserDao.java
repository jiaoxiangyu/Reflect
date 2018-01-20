package com.jxy.reflection.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.jxy.reflection.base.DaoBase;
import com.jxy.reflection.entity.User;


/** 
* @author 焦祥宇 
* @version 创建时间：2017年3月13日 下午9:37:12 
* @description  
*/
public class UserDao extends DaoBase<User>{
	public boolean add(User user) throws SQLException{
		boolean bool=this.addEntity(user);
		return bool; 
	}
	public User fingById(int id) throws Exception{
		User user=null;				
		user= this.findEntityById(id);		
		return user;
	}
	public boolean deleteById(int id) throws Exception{
		return this.deleteEntity(id);
	}
	public boolean update(User user) throws Exception{
		return this.updateEntity(user);
	}
}
