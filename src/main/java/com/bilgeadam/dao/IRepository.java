package com.bilgeadam.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.util.HibernateSession;

public interface IRepository<T> {
	
	public void create(T entity);
	
	public void delete(long id);
	
	public void update(long id, T entity);
	
	public void listAll();
	
	public T find(long id);
	
	
	default Session databaseConnectionHibernate() {
		
		return HibernateSession.getSessionFactory().openSession();
	}
	
}
