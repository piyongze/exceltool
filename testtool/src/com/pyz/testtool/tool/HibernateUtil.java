package com.pyz.testtool.tool;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	 private static final SessionFactory sessionFactory = buildSessionFactory();

	    private static SessionFactory buildSessionFactory() {
	        try {
	        	Configuration cfg = new Configuration().configure();
	            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                    .applySettings(cfg.getProperties()).build();
	            SessionFactory sf = cfg.buildSessionFactory(serviceRegistry);
	            return sf;
	        }
	        catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	    
	    public static void closeSessionFactory() {
	    	sessionFactory.close();
	    }
}
