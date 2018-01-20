package com.jxy.reflection.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxy.reflection.dao.UserDao;
import com.jxy.reflection.entity.User;
import com.jxy.reflection.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String tag=request.getParameter("tag");
		int id=Integer.valueOf(request.getParameter("id"));
		if(tag.equals("find")){
			try {
				User user=new UserService().fingById(id);
				request.setAttribute("user", user);			
				request.getRequestDispatcher("find.jsp").forward(request, response);
			} catch (Exception e) {			
				e.printStackTrace();
			}
		}else if(tag.equals("delete")){
			boolean bool=false;
			try {
				bool = new UserService().deleteById(id);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			if(bool==true){
				request.setAttribute("meg", "删除成功");			
			}else{
				request.setAttribute("meg", "删除失败");	
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else if(tag.equals("update")){
			try {
				User user=new UserDao().fingById(id);
				request.setAttribute("user", user);			
				request.getRequestDispatcher("update.jsp").forward(request, response);
			} catch (Exception e) {			
				e.printStackTrace();
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String tag=request.getParameter("tag");
		if(tag.equals("add")){
			String name=request.getParameter("name");
			String sex=request.getParameter("sex");
			String age=request.getParameter("age");
			User user=new User(null,name,sex,age);
			boolean bool=false;
			try {
		        bool=new UserService().add(user);
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			if (bool==true){
				request.setAttribute("meg", "添加成功");
			}else{
				request.setAttribute("meg", "添加失败");
			}
			request.getRequestDispatcher("add.jsp").forward(request, response);
		}else if(tag.equals("update")){			
			String id=request.getParameter("id");
			String name=request.getParameter("name");
			String sex=request.getParameter("sex");
			String age=request.getParameter("age");
			User user=new User(id,name,sex,age);
			boolean bool=false;
			try {				
		        bool=new UserService().update(user);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			if (bool==true){
				request.setAttribute("meg", "添加成功");
			}else{
				request.setAttribute("meg", "添加失败");
			}
			request.getRequestDispatcher("update.jsp").forward(request, response);
		}
		
	}

}
