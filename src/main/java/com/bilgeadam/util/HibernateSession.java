package com.bilgeadam.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bilgeadam.entity.Role;
import com.bilgeadam.entity.Rule;
import com.bilgeadam.entity.User;
import com.bilgeadam.entity.UserDetail;

public class HibernateSession {

	private static SessionFactory sessionFactory = sessionFactory() ;
	
	private static SessionFactory sessionFactory() {
		
		Configuration configuration = new Configuration();
		
		configuration.addAnnotatedClass(User.class);
		configuration.addAnnotatedClass(UserDetail.class);
		configuration.addAnnotatedClass(Rule.class);
		configuration.addAnnotatedClass(Role.class);

		
		SessionFactory factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
		
		return factory;
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
