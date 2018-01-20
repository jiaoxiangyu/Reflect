package com.jxy.reflection.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.jxy.reflection.dao.UserDao;
import com.jxy.reflection.entity.User;

/**
 * @author 焦祥宇
 * @version 创建时间：2017年3月2日 下午8:31:52
 * @description
 */
public class Test {
	public void test() throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		Class<User> clazz = User.class;
		/* Class<User> clazz=Class.forName("com.jxy.reflection.entity.User"); */
		User user = clazz.newInstance();
		Field f1 = clazz.getDeclaredField("name");
		f1.setAccessible(true);//设置为可访问
		f1.set(user, "jxy");
		Field f2 = clazz.getDeclaredField("sex");
		f2.setAccessible(true);
		f2.set(user, "男");
		Method m1 = clazz.getMethod("setAge", int.class);
		m1.invoke(user, 22);
		System.out.println(user);
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		Test t = new Test();
		t.test();
		
	}
}
