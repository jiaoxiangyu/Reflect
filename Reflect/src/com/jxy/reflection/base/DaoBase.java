package com.jxy.reflection.base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.jstl.sql.Result;

import org.springframework.jdbc.core.metadata.TableMetaDataContext;

import com.jxy.reflection.util.MysqlLink;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/** 
* @author 焦祥宇 
* @version 创建时间：2017年3月13日 下午8:17:06 
* @description  
*/
public class DaoBase<T> {		
	private Class <? extends DaoBase> clazz;
	private String tableName;//表名
	@SuppressWarnings("unchecked")
	public DaoBase(){
		//获得该类type
		Type type=this.getClass().getGenericSuperclass();
		//判断该类是否继承parameterizedType接口
		if(!(type instanceof ParameterizedType)){
			type=Object.class;
		}
		//如果实现该接口 就获去该类的第一个泛型参数(子类class)		
		clazz =  (Class<? extends DaoBase>) ((ParameterizedType) type).getActualTypeArguments()[0];
		//获得表名
		tableName=clazz.getSimpleName().toLowerCase();
	}
	
	
	//插入add
	public boolean addEntity(T t) throws SQLException{
		//获得子类所有的属性
		Field[] fields=clazz.getDeclaredFields();	
		String attribute="";//属性名
		String placeholder="";//占位符
		for(int i=0;i<fields.length;i++){
			attribute=attribute+fields[i].getName();
			placeholder=placeholder+"?";
			if(i==fields.length-1){
				continue;
			}
			attribute=attribute+",";
			placeholder=placeholder+",";
		}
		String sql="insert into "+tableName+" ("+attribute+") values("+placeholder+")";
		PreparedStatement  ps=(PreparedStatement) MysqlLink.conn.prepareStatement(sql);
		//给占位符赋值
		for(int i=0;i<fields.length;i++){
			try {
				Method method=clazz.getMethod(getGetOrSetMethodName("get", fields[i]));				
				ps.setString(i+1,(String)method.invoke(t));
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		int ok=ps.executeUpdate();
		if(ok==1){
			return true;
		}else{
			return false;
		}		
	}
	
	//根据id查询find
	@SuppressWarnings("unchecked")
	public T findEntityById(int id) throws Exception{
		//获得子类所有的属性
		Field[] fields=clazz.getDeclaredFields();				
		String sql="select * from "+tableName+" where "+fields[0].getName()+"=?";	
		List<T> list=executeFindSql(sql, id);
		if(list.size()>0){
			return (T)list.toArray()[0];
		}else{
			return null;
		}
		
	}
	
	//删除delete
	public boolean deleteEntity(int id){
		//获得子类所有的属性
		Field[] fields=clazz.getDeclaredFields();				
		String sql="delete from "+tableName+" where "+fields[0].getName()+"=?";
		if(executeSql(sql, id)==1){
			return true;
		}else{
			return false;
		}		
	}
	
	//修改update
	public boolean updateEntity(T t) throws Exception{
		System.out.println("update");
		int count=0;
		//获得子类所有的属性
		Field[] fields = clazz.getDeclaredFields();		
		String setSentence="";
		for(int i=1;i<fields.length;i++){
			setSentence=setSentence+fields[i].getName()+"=?";			
			if(i==fields.length-1){
				continue;
			}
			setSentence=setSentence+",";
		}
		String sql = "update " + tableName + " set "+setSentence+" where " + fields[0].getName() + "=?";		
		//给占位符赋值		
		try {
			PreparedStatement ps= (PreparedStatement) MysqlLink.conn.prepareStatement(sql);
			for (int i = 1; i < fields.length; i++) {			
				Method method = clazz.getMethod(getGetOrSetMethodName("get", fields[i]));
				ps.setString(i, (String) method.invoke(t));				
			}
			Method method = clazz.getMethod(getGetOrSetMethodName("get", fields[0]));
			ps.setString(fields.length, (String) method.invoke(t));	
			count=ps.executeUpdate();
		} catch (SQLException e1) {			
			e1.printStackTrace();
		}
		if(count==1){
			return true;
		}else{
			return false;
		}
		
	}
	
	//根据preMethod（方法前缀get或set）获得属性的get或set方法名称,
	public String getGetOrSetMethodName(String preMethod, Field field) {
		String backName = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
		return preMethod + backName;
	}
	
	//执行查询sql语句
	@SuppressWarnings("unchecked")
	public List<T> executeFindSql(String sql,Object...objects) throws Exception {
		List<T> list=new ArrayList<>();
		try {
			PreparedStatement ps=(PreparedStatement) MysqlLink.conn.prepareStatement(sql);
			for(int i=0;i<objects.length;i++){
				ps.setString(i+1, objects[i].toString());
			}
			ResultSet  rs=ps.executeQuery();
			Field[] fields=clazz.getDeclaredFields();
			T t = null;								
			t =  (T) clazz.newInstance();
			while(rs.next()){																								
				for(int i=0;i<fields.length;i++){
					String str=rs.getString(fields[i].getName());					
					fields[i].setAccessible(true);//设置为可访问					
					fields[i].set(t, str);										
				}
				list.add(t);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		return list;
	}
	//执行增删改sql语句
	public int executeSql(String sql,Object...objects){
		int count=0;
		try {
			PreparedStatement ps=(PreparedStatement) MysqlLink.conn.prepareStatement(sql);
			for(int i=0;i<objects.length;i++){
				ps.setString(i+1, objects[i].toString());
			}
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
