package com.bilgeadam.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.entity.UserDetail;

import jakarta.persistence.TypedQuery;

public class UserDetailDao implements IRepository<UserDetail>{

	@Override
	public void create(UserDetail entity) {
		
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
			UserDetail deleteAddress = find(id);
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
	public void update(long id, UserDetail entity) {
		Session session = null;
		try {
			UserDetail updateUserDetail = find(id);
			if(updateUserDetail != null) {				
				
				updateUserDetail.setFirstName(entity.getFirstName());
				updateUserDetail.setLastName(entity.getLastName());
				updateUserDetail.setBio(entity.getBio());
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(updateUserDetail);
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
		String hql = "select usr from UserDetail as usr";
		TypedQuery<UserDetail> typedQuery = session.createQuery(hql,UserDetail.class);
		List<UserDetail> userList =  typedQuery.getResultList();
		
		for (UserDetail user : userList) {
			System.out.println(user);
		}
		
	}

	@Override
	public UserDetail find(long id) {
		
		Session session = databaseConnectionHibernate();
		UserDetail userDetail;
		try {
			userDetail =session.find(UserDetail.class, id);
			
			if(userDetail != null) {
				System.out.println("User Found: " + userDetail);
				return userDetail;
			}else {
				System.out.println("User not Found!");
				return userDetail;
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
