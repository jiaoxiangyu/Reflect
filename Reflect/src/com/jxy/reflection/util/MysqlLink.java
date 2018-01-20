package com.jxy.reflection.util;

import java.sql.Connection;
import java.sql.DriverManager;

/** 
* @author 焦祥宇 
* @version 创建时间：2017年3月13日 下午9:03:30 
* @description  
*/
public class MysqlLink {
	public static Connection conn=null;	
	public void getLink(){			
		try {
			 String driver="com.mysql.jdbc.Driver";
	    	 String url="jdbc:mysql://localhost/reflect?useUnicode=true&characterEncoding=utf8";
	    	 String user="root";
	    	 String password="root";	
	    	 Class.forName(driver);//加载数据库驱动
	         conn=DriverManager.getConnection(url,user,password);//获取数据库连接
											
			 if (conn != null) {
					System.out.println("数据库连接成功");
			 }
		  } 
		  catch (Exception e) {
			  System.out.println("数据库连接失败");
			  e.printStackTrace();
		  }
	}	
}
