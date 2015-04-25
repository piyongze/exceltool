package com.pyz.testtool.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pyz.testtool.entity.Student;
import com.pyz.testtool.tool.Dom4jTool;
import com.pyz.testtool.tool.ExcelHandle;
import com.pyz.testtool.tool.HibernateUtil;

public class ExcelService implements ExcelHandle{

	private Class beanClass;
	private SessionFactory sessionFactory;
	public ExcelService() throws Exception{
		this.beanClass=Class.forName((String)Dom4jTool.handle().get("entity"));
		this.sessionFactory=HibernateUtil.getSessionFactory();
	}
	@Override
	public void handle(Map map) throws Exception{
		// TODO Auto-generated method stub
		//通过反射 设定属性 持久化
		Object obj=beanClass.newInstance();
		Iterator it=map.keySet().iterator();
		while(it.hasNext()){
			String colname=(String)it.next();
			System.out.println("colname:"+colname);
			if(colname.equals("entity")) continue;
			Field field = beanClass.getDeclaredField(colname);
			field.setAccessible(true);
			field.set(obj,map.get(colname));
		}
		Session session=sessionFactory.openSession();
		session.getTransaction().begin();
		session.persist(obj);
		session.getTransaction().commit();
		session.close();
	}

}
