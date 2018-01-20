package com.jxy.reflection.service;

import java.sql.SQLException;

import com.jxy.reflection.dao.UserDao;
import com.jxy.reflection.entity.User;

/** 
* @author 焦祥宇 
* @version 创建时间：2017年3月14日 下午9:51:51 
* @description  
*/
public class UserService {
	public boolean add(User user) throws SQLException {
		return new UserDao().add(user); 
	}
	public User fingById(int id) throws Exception{	
		return new UserDao().fingById(id);
	}
	public boolean deleteById(int id) throws Exception{
		return new UserDao().deleteById(id);
	}
	public boolean update(User user) throws Exception{
		return new UserDao().update(user);
	}
}
