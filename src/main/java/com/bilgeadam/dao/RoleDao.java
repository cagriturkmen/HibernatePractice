package com.bilgeadam.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.entity.Role;

import jakarta.persistence.TypedQuery;

public class RoleDao implements IRepository<Role>{

	@Override
	public void create(Role entity) {
		
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
			Role deleteAddress = find(id);
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
	public void update(long id, Role entity) {
		Session session = null;
		try {
			Role role = find(id);
			if(role != null) {				
				
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(role);
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
		String hql = "select usr from Role as usr";
		TypedQuery<Role> typedQuery = session.createQuery(hql,Role.class);
		List<Role> roleList =  typedQuery.getResultList();
		
		for (Role role : roleList) {
			System.out.println(role);
		}
		
	}

	@Override
	public Role find(long id) {
		
		Session session = databaseConnectionHibernate();
		Role role;
		try {
			role =session.find(Role.class, id);
			
			if(role != null) {
				System.out.println("role Found: " + role);
				return role;
			}else {
				System.out.println("role not Found!");
				return role;
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
