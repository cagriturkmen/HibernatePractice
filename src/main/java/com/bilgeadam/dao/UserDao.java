package com.bilgeadam.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.entity.User;

import jakarta.persistence.TypedQuery;

public class UserDao implements IRepository<User>{

	@Override
	public void create(User entity) {
		
		Session session = null;
		try {
			
			session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("User data is added to DB.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding user data.");		
		} finally {
			session.close();
		}
		
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			User deleteAddress = find(id);
			if(deleteAddress != null) {
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deleteAddress);
				session.getTransaction().commit();

				System.out.println("Successfully deleted.");
					
			}
			
		} catch (Exception e) {
		System.out.println("Some problem occured while DELETE opertaion.");
		e.printStackTrace();
		} finally {
			session.close();
		}		
	}

	@Override
	public void update(long id, User entity) {
		Session session = null;
		try {
			User updateUser = find(id);
			if(updateUser != null) {				
				updateUser.setEmail(entity.getEmail());
				updateUser.setPassword(entity.getPassword());
				updateUser.setUserDetail(entity.getUserDetail());			
				updateUser.setRole(entity.getRole());
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(updateUser);
				session.getTransaction().commit();
				System.out.println("Successfully updated. Welldone.");
				
			}
			
		} catch (Exception e) {
			System.out.println("Some problem has occured while UPDATE.");
			e.printStackTrace();
		} finally {
		session.close();
		}		
	}

	@Override
	public void listAll() {
		
		Session session = databaseConnectionHibernate();
		String hql = "select usr from User as usr";
		TypedQuery<User> typedQuery = session.createQuery(hql,User.class);
		List<User> userList =  typedQuery.getResultList();
		
		for (User user : userList) {
			System.out.println(user);
		}
		
	}

	@Override
	public User find(long id) {
		
		Session session = databaseConnectionHibernate();
		User user;
		try {
			user =session.find(User.class, id);
			
			if(user != null) {
				System.out.println("User Found: " + user);
				return user;
			}else {
				System.out.println("User not Found!");
				return user;
			}
			
		} catch (Exception e) {
			System.out.println("Some problems has occured during find operation.");	
			e.printStackTrace();
		}finally {
			session.close();
		}		
		return null;
	}

}
