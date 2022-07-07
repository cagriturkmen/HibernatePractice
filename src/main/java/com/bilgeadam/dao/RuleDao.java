package com.bilgeadam.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.entity.Rule;

import jakarta.persistence.TypedQuery;

public class RuleDao implements IRepository<Rule>{

	@Override
	public void create(Rule entity) {
		
		Session session = null;
		try {
			
			session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("User data is added to DB.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Rule data.");		
		} finally {
			session.close();
		}
		
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Rule deleteAddress = find(id);
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
	public void update(long id, Rule entity) {
		Session session = null;
		try {
			Rule updatedRule = find(id);
			if(updatedRule != null) {				
				
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(updatedRule);
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
		TypedQuery<Rule> typedQuery = session.createQuery(hql,Rule.class);
		List<Rule> ruleList =  typedQuery.getResultList();
		
		for (Rule rule : ruleList) {
			System.out.println(rule);
		}
		
	}

	@Override
	public Rule find(long id) {
		
		Rule rule;
		try (Session session = databaseConnectionHibernate();
				){
			rule =session.find(Rule.class, id);
			
			if(rule != null) {
				System.out.println("rule Found: " + rule);
				return rule;
			}else {
				System.out.println("rule not Found!");
				return rule;
			}
			
		} catch (Exception e) {
			System.out.println("Some problems has occured during find operation.");	
			e.printStackTrace();
		}	
		return null;
	}

}
